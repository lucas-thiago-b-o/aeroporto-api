package br.com.companhia.aeroporto.controller;

import br.com.companhia.aeroporto.dto.PassagemDTO;
import br.com.companhia.aeroporto.service.PassagemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class PassagemResourceTest {

    @Mock
    private PassagemService passagemService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        PassagemResource passagemResource = new PassagemResource(passagemService);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(passagemResource)
                .build();
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/api/passagens/passagem"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindAllByUuidUsuario() throws Exception {
        String uuid = anyString();

        when(passagemService.findAllByUuidUsuario(uuid)).thenReturn(List.of(new PassagemDTO()));

        mockMvc.perform(get("/api/passagens/passagem/{uuidUsuario}", uuid))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testComprarPassagem() throws Exception {
        when(passagemService.comprarPassagem(new PassagemDTO())).thenReturn(anyString());

        mockMvc.perform(post("/api/passagens/passagem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(new PassagemDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void testCancelarPassagem() throws Exception {
        when(passagemService.cancelarPassagem(new PassagemDTO())).thenReturn(anyString());

        mockMvc.perform(put("/api/passagens/passagem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(new PassagemDTO())))
                .andExpect(status().isOk());
    }

}
