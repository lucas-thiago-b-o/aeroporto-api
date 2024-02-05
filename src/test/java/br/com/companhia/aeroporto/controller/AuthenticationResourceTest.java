package br.com.companhia.aeroporto.controller;

import br.com.companhia.aeroporto.domain.Usuario;
import br.com.companhia.aeroporto.dto.AuthenticationDTO;
import br.com.companhia.aeroporto.dto.RegisterDTO;
import br.com.companhia.aeroporto.enums.UsuarioRole;
import br.com.companhia.aeroporto.repository.UsuarioRepository;
import br.com.companhia.aeroporto.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collection;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class AuthenticationResourceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TokenService tokenService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        AuthenticationResource aeroportoResource = new AuthenticationResource(authenticationManager, usuarioRepository, tokenService);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(aeroportoResource)
                .build();
    }

    @Test
    public void testPingUser() throws Exception {
        when(usuarioRepository.existsById("uuid")).thenReturn(Boolean.TRUE);

        mockMvc.perform(get("/api/auth/pingUser/{uuid}", "uuid"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testLogin() throws Exception {
        AuthenticationDTO authenticationDTO = new AuthenticationDTO("username", "password");
        Usuario user = new Usuario("username", "encryptedPassword", UsuarioRole.USER);
        when(usuarioRepository.findByUsername(authenticationDTO.username())).thenReturn(user);

        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
        var auth = getAuthenticationTest();

        when(authenticationManager.authenticate(usernamePassword)).thenReturn(auth);

        var token = anyString();

        when(tokenService.generateToken((Usuario) auth.getPrincipal())).thenReturn(token);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authenticationDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRegisterSuccess() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO("newUser", "password", UsuarioRole.USER);

        when(usuarioRepository.findByUsername(registerDTO.username())).thenReturn(null);

        ResultActions resultActions = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(registerDTO)))
                .andExpect(status().isOk());

        verify(usuarioRepository, times(1)).findByUsername(registerDTO.username());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));

        resultActions.andExpect(content().string("Cadastro efetuado com sucesso"));
    }

    @Test
    public void testRegisterUserAlreadyExists() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO("existingUser", "password", UsuarioRole.USER);

        when(usuarioRepository.findByUsername(registerDTO.username())).thenReturn(new Usuario());

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(registerDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Usuário já cadastrado no sistema"));

        verify(usuarioRepository, times(1)).findByUsername(registerDTO.username());
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    public Authentication getAuthenticationTest() {
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return new Usuario("username", "encryptedPassword", UsuarioRole.USER);
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        };
    }
}
