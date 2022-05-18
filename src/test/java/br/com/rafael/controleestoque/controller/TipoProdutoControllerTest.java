package br.com.rafael.controleestoque.controller;

import br.com.rafael.controleestoque.mappers.TipoProdutoMapper;
import br.com.rafael.controleestoque.services.TipoProdutoService;
import br.com.rafael.controleestoque.utils.TipoProdutoFactory;
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

@DisplayName("Tipo Produto Controller")
@WebMvcTest(TipoProdutoController.class)
class TipoProdutoControllerTest {

    @MockBean
    private TipoProdutoService service;

    @MockBean
    private TipoProdutoMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() throws Throwable {
        when(mapper.toDto(any())).thenReturn(TipoProdutoFactory.buildDto());
        when(service.findById(any())).thenReturn(TipoProdutoFactory.build());
    }

    @Test
    @DisplayName("Deve acessar endpoint de busca de produto por id com sucesso")
    void testBuscarPorId() throws Exception {
        mockMvc.perform(get("/tipo-produto/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Tipo produto teste"));
    }

    @Test
    @DisplayName("Deve acessar endpoint de inserir produto com sucesso")
    void testInserir() throws Exception {
        String json = objectMapper.writeValueAsString(TipoProdutoFactory.build());
        mockMvc.perform(post("/tipo-produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Tipo produto teste"));
    }

    @Test
    @DisplayName("Deve acessar endpoint de delete de produto com sucesso")
    void testDelete() throws Exception {
        mockMvc.perform(delete("/tipo-produto/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
