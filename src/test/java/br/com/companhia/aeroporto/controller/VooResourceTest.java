package br.com.companhia.aeroporto.controller;

import br.com.companhia.aeroporto.dto.VooDTO;
import br.com.companhia.aeroporto.service.VooService;
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
public class VooResourceTest {

    @Mock
    private VooService vooService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        VooResource vooResource = new VooResource(vooService);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(vooResource)
                .build();
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/api/voos/voo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindById() throws Exception {
        when(vooService.findById(1L)).thenReturn(new VooDTO());

        mockMvc.perform(get("/api/voos/voo/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindByAeroportoId() throws Exception {
        when(vooService.findAllByAeroporoId(1L)).thenReturn(List.of(new VooDTO()));

        mockMvc.perform(get("/api/voos/voo/aeroporto/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindByCidadeId() throws Exception {
        when(vooService.findAllByCidadeId(1L)).thenReturn(List.of(new VooDTO()));

        mockMvc.perform(get("/api/voos/voo/aeroporto/cidade/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindByEstadId() throws Exception {
        when(vooService.findAllByUfId(1L)).thenReturn(List.of(new VooDTO()));

        mockMvc.perform(get("/api/voos/voo/aeroporto/estado/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindByCidadeIds() throws Exception {
        when(vooService.findAllByCidadesIds(1L, 1L)).thenReturn(List.of(new VooDTO()));

        mockMvc.perform(get("/api/voos/voo/aeroporto/cidade_origem/{idOrigem}/cidade_destino/{idDestino}", 1L, 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCancelarPassagem() throws Exception {
        when(vooService.cancelarVoo(1L)).thenReturn(anyString());

        mockMvc.perform(put("/api/voos/voo/cancelar/{idVoo}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(1L)))
                .andExpect(status().isOk());
    }

}
