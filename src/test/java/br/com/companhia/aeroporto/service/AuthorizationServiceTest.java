package br.com.companhia.aeroporto.service;

import br.com.companhia.aeroporto.domain.Usuario;
import br.com.companhia.aeroporto.enums.UsuarioRole;
import br.com.companhia.aeroporto.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.*;

class AuthorizationServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    AuthorizationService authorizationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername() {
        when(usuarioRepository.findByUsername(anyString())).thenReturn(new Usuario("id", "username", "password", UsuarioRole.ADMIN));

        UserDetails result = authorizationService.loadUserByUsername("username");
        Assertions.assertEquals(new Usuario("id", "username", "password", UsuarioRole.ADMIN), result);
    }
}