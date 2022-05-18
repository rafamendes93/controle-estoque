package br.com.rafael.controleestoque.services;

import br.com.rafael.controleestoque.entities.tipoproduto.TipoProduto;
import br.com.rafael.controleestoque.exceptions.NaoEncontradoException;
import br.com.rafael.controleestoque.exceptions.RegistrosDependentesException;
import br.com.rafael.controleestoque.messages.MessagesHandler;
import br.com.rafael.controleestoque.repositories.TipoProdutoRepository;
import br.com.rafael.controleestoque.utils.TipoProdutoFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Tipo Produto Service")
class TipoProdutoServiceTest {

    @InjectMocks
    private TipoProdutoService service;

    @Mock
    private TipoProdutoRepository repository;

    @Mock
    private MessagesHandler messagesHandler;


    @BeforeEach
    void setUp() {
        doNothing().when(repository).deleteById(1);
        doThrow(DataIntegrityViolationException.class).when(repository).deleteById(2);
        doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(3);
        when(repository.findById(4)).thenReturn(Optional.of(TipoProdutoFactory.build(4, "Tipo produto Teste 4")));
        when(repository.findById(5)).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(TipoProdutoFactory.build(6, "Teste tipo produto 6"));
    }

    @Test
    @DisplayName("Deve deletar sem retornar nenhuma exceção")
    void testShouldDelete() {
        Assertions.assertDoesNotThrow(() -> {
            service.deleteById(1);
        });
        Mockito.verify(repository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção na deleção quando produto possui movimentação de estoque")
    void testShouldThrowProdutoComMovimentoExceptionDelete() {
        Assertions.assertThrows(RegistrosDependentesException.class, () -> {
            service.deleteById(2);
        });
        Mockito.verify(repository, times(1)).deleteById(2);
    }

    @Test
    @DisplayName("Deve lançar exceção na deleção quando produto com id não existe")
    void testShouldThrowNaoEncontradoExceptionDelete() {
        Assertions.assertThrows(NaoEncontradoException.class, () -> {
            service.deleteById(3);
        });
        Mockito.verify(repository, times(1)).deleteById(3);
    }

    @Test
    @DisplayName("Deve buscar produto por id quando existente")
    void testShouldFindById() throws Throwable {
        TipoProduto tipoProduto = service.findById(4);
        assertEquals(4, tipoProduto.getId());
        assertEquals("Tipo produto Teste 4", tipoProduto.getDescricao());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar por id inexistente")
    void testShouldExceptionWhenFindById() {
        Assertions.assertThrows(NaoEncontradoException.class, () -> {
            service.findById(5);
        });
        Mockito.verify(repository, times(1)).findById(5);
    }

    @Test
    @DisplayName("Deve inserir tipo produto")
    void testShouldInsert() {
        TipoProduto tipoProduto = service.insert(TipoProdutoFactory.build());
        assertEquals(6, tipoProduto.getId());
        assertEquals("Teste tipo produto 6", tipoProduto.getDescricao());
    }

}
