package br.com.companhia.aeroporto.controller;

import br.com.companhia.aeroporto.service.CidadeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class CidadeResourceTest {

    @Mock
    private CidadeService cidadeService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        CidadeResource cidadeResource = new CidadeResource(cidadeService);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(cidadeResource)
                .build();
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/api/cidades/cidade"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
