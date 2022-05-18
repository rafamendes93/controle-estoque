package br.com.rafael.controleestoque.services;

import br.com.rafael.controleestoque.entities.movimentoestoque.*;
import br.com.rafael.controleestoque.exceptions.EstoqueInsuficienteException;
import br.com.rafael.controleestoque.messages.MessagesHandler;
import br.com.rafael.controleestoque.repositories.MovimentoEstoqueRepository;
import br.com.rafael.controleestoque.utils.MovimentoEstoqueFactory;
import br.com.rafael.controleestoque.utils.MovimentoEstoqueProjectionImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("Movimento Estoque Service")
class MovimentoEstoqueTest {

    @InjectMocks
    private MovimentoEstoqueService service;

    @Mock
    private MovimentoEstoqueRepository repository;

    @Mock
    private MessagesHandler messagesHandler;

    private MovimentoEstoque entrada = MovimentoEstoqueFactory.buildEntrada();
    private MovimentoEstoque saida = MovimentoEstoqueFactory.buildSaida();

    @BeforeEach
    void setUp() {
        when(repository.findTotalLucroByProduto(1))
                .thenReturn(MovimentoEstoqueProjectionImpl.builder()
                        .lucro(BigDecimal.valueOf(1500.00))
                        .totalEntrada(10L)
                        .totalSaida(5L)
                        .build());

        when(repository.findTotalLucroByProduto(2))
                .thenReturn(MovimentoEstoqueProjectionImpl.builder()
                        .build());

        entrada.setId(null);
        saida.setId(null);
        when(repository.save(entrada)).thenReturn(MovimentoEstoqueFactory.buildEntrada());
        when(repository.save(saida)).thenReturn(MovimentoEstoqueFactory.buildSaida());
    }

    @Test
    @DisplayName("Deve calcular o valores de estoque, entrada e saída")
    void testShouldCalculaEstoque() {
        TotalizadorMovimentosDto quantidadeByProduto = service.getQuantidadeByProduto(1);
        assertEquals(10L, quantidadeByProduto.getQuantidadeEntrada());
        assertEquals(5L, quantidadeByProduto.getQuantidadeSaida());
        assertEquals(BigDecimal.valueOf(1500.00), quantidadeByProduto.getLucro());
        assertEquals(5L, quantidadeByProduto.getQuantidadeEstoque());
    }

    @Test
    @DisplayName("Deve calcular estoque quando zerado")
    void testShouldCalculaEstoqueZeraod() {
        TotalizadorMovimentosDto quantidadeByProduto = service.getQuantidadeByProduto(2);
        assertEquals(0L, quantidadeByProduto.getQuantidadeEntrada());
        assertEquals(0L, quantidadeByProduto.getQuantidadeSaida());
        assertEquals(BigDecimal.ZERO, quantidadeByProduto.getLucro());
        assertEquals(0, quantidadeByProduto.getQuantidadeEstoque());
    }

    @Test
    @DisplayName("Deve criar movimento de estoque de entrada")
    void testShouldCriarMovimentoEntrada() {
        MovimentoEstoque movimentoEstoque = service.criarMovimento(entrada);
        assertEquals(TipoMovimento.E, movimentoEstoque.getTipoMovimento());
        assertEquals(1, movimentoEstoque.getId());
    }

    @Test
    @DisplayName("Deve criar movimento de estoque de saida")
    void testShouldCriarMovimentoSaida() {
        MovimentoEstoque movimentoEstoque = service.criarMovimento(saida);
        assertEquals(TipoMovimento.S, movimentoEstoque.getTipoMovimento());
        assertEquals(1, movimentoEstoque.getId());
    }

    @Test
    @DisplayName("Deve lançar um erro em caso de estoque insuficiente")
    void testShouldExceptionEstoqueInsuficiente() {
        MovimentoEstoque saida = MovimentoEstoqueFactory.buildSaida();
        saida.setId(null);
        saida.setQuantidade(99999);
        when(repository.save(saida)).thenReturn(MovimentoEstoqueFactory.buildSaida());
        assertThrows(EstoqueInsuficienteException.class, () -> service.criarMovimento(saida));
    }

}
