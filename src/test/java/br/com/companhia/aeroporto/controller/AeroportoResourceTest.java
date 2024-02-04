package br.com.companhia.aeroporto.controller;

import br.com.companhia.aeroporto.AeroportoApplication;
import br.com.companhia.aeroporto.dto.AeroportoDTO;
import br.com.companhia.aeroporto.service.AeroportoService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
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
        long aeroportoId = 1L;
        AeroportoDTO aeroporto = new AeroportoDTO();

        when(aeroportoService.findById(aeroportoId)).thenReturn(aeroporto);

        mockMvc.perform(get("/api/aeroportos/aeroporto/{id}", aeroportoId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    /*@Test
    void testFindById() throws Exception {
        long aeroportoId = 1L;
        AeroportoDTO aeroporto = new AeroportoDTO();

        when(aeroportoService.findById(aeroportoId)).thenReturn(aeroporto);

        mockMvc.perform(get("/api/aeroportos/aeroporto/{id}", aeroportoId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap());

        verify(aeroportoService, times(1)).findById(aeroportoId);
        verifyNoMoreInteractions(aeroportoService);
    }*/

}