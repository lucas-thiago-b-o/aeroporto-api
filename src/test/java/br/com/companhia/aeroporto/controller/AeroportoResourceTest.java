package br.com.companhia.aeroporto.controller;

import br.com.companhia.aeroporto.dto.AeroportoDTO;
import br.com.companhia.aeroporto.service.AeroportoService;
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

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class AeroportoResourceTest {

    @Mock
    private AeroportoService aeroportoService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        AeroportoResource aeroportoResource = new AeroportoResource(aeroportoService);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(aeroportoResource)
                .build();
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/api/aeroportos/aeroporto"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindById() throws Exception {
        when(aeroportoService.findById(1L)).thenReturn(new AeroportoDTO());

        mockMvc.perform(get("/api/aeroportos/aeroporto/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindAllByCidadeId() throws Exception {
        when(aeroportoService.findAllByCidadeId(1L)).thenReturn(List.of(new AeroportoDTO()));

        mockMvc.perform(get("/api/aeroportos/aeroporto/cidade/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindAllByEstadoId() throws Exception {
        when(aeroportoService.findAllByUfId(1L)).thenReturn(List.of(new AeroportoDTO()));

        mockMvc.perform(get("/api/aeroportos/aeroporto/estado/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}