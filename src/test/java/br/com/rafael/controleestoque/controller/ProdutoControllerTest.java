package br.com.rafael.controleestoque.controller;

import br.com.rafael.controleestoque.mappers.ProdutoMapper;
import br.com.rafael.controleestoque.services.ProdutoService;
import br.com.rafael.controleestoque.utils.ProdutoFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Produto Controller")
@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @MockBean
    private ProdutoService service;

    @MockBean
    private ProdutoMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Throwable {
        when(service.findById(1)).thenReturn(ProdutoFactory.build());
        when(service.findByTipo(any(), any())).thenReturn(ProdutoFactory.buildPaged());
        when(mapper.toDto(any())).thenReturn(ProdutoFactory.buildDto());
        when(service.insert(any())).thenReturn(ProdutoFactory.build());
    }

    @Test
    @DisplayName("Deve acessar endpoint de busca de produto por id com sucesso")
    void testBuscarPorId() throws Exception {
        mockMvc.perform(get("/produto/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Produto teste"))
                .andExpect(jsonPath("$.valorCusto").value("1500.0"));
    }

    @Test
    @DisplayName("Deve acessar endpoint de inserir produto com sucesso")
    void testInserir() throws Exception {
        String json = objectMapper.writeValueAsString(ProdutoFactory.build());
        mockMvc.perform(post("/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Produto teste"))
                .andExpect(jsonPath("$.valorCusto").value("1500.0"));
    }

    @Test
    @DisplayName("Deve acessar endpoint de delete de produto com sucesso")
    void testDelete() throws Exception {
        mockMvc.perform(delete("/produto/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve acessar endpoint de busca de produto por tipo com sucesso")
    void testBuscarPorTipo() throws Exception {
        mockMvc.perform(get("/produto/tipo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
