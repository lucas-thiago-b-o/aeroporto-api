package br.com.companhia.aeroporto.controller;

import br.com.companhia.aeroporto.dto.ClasseDTO;
import br.com.companhia.aeroporto.service.ClasseService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class ClasseResourceTest {

    @Mock
    private ClasseService cidadeService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        ClasseResource cidadeResource = new ClasseResource(cidadeService);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(cidadeResource)
                .build();
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/api/classes/classe"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindById() throws Exception {
        when(cidadeService.findById(1L)).thenReturn(new ClasseDTO());

        mockMvc.perform(get("/api/classes/classe/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindByVooId() throws Exception {
        when(cidadeService.getAllClassesByVooId(1L)).thenReturn(List.of(new ClasseDTO()));

        mockMvc.perform(get("/api/classes/classe/voo/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetQuantPassageirosByVoo() throws Exception {
        when(cidadeService.getQuantPassageirosByVoo(1L)).thenReturn(1);

        mockMvc.perform(get("/api/classes/passageiros/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
