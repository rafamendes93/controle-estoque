package br.com.rafael.controleestoque.controller;

import br.com.rafael.controleestoque.mappers.MovimentoEstoqueMapper;
import br.com.rafael.controleestoque.services.MovimentoEstoqueService;
import br.com.rafael.controleestoque.utils.MovimentoEstoqueFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Movimento Estoque Controller")
@WebMvcTest(MovimentoEstoqueController.class)
class MovimentoEstoqueControllerTest {

    @MockBean
    private MovimentoEstoqueService service;

    @MockBean
    private MovimentoEstoqueMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        when(service.criarMovimento(any())).thenReturn(MovimentoEstoqueFactory.build());
        when(mapper.toDto(any())).thenReturn(MovimentoEstoqueFactory.buildDto());
    }

    @Test
    @DisplayName("Deve acessar endpoint de criar movimento com sucesso")
    void testEndpointCriarMovimento() throws Exception {
        String json = objectMapper.writeValueAsString(MovimentoEstoqueFactory.buildDto());
        mockMvc.perform(post("/movimento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoMovimento").value("E"))
                .andExpect(jsonPath("$.valorVenda").value("1500.0"));
    }

}
