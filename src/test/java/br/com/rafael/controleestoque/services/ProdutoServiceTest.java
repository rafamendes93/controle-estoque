package br.com.rafael.controleestoque.services;

import br.com.rafael.controleestoque.entities.produto.Produto;
import br.com.rafael.controleestoque.exceptions.NaoEncontradoException;
import br.com.rafael.controleestoque.exceptions.RegistrosDependentesException;
import br.com.rafael.controleestoque.messages.MessagesHandler;
import br.com.rafael.controleestoque.repositories.ProdutoRepository;
import br.com.rafael.controleestoque.utils.ProdutoFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Produto Service")
class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService service;

    @Mock
    private ProdutoRepository repository;

    @Mock
    private MessagesHandler messagesHandler;

    @BeforeEach
    void setUp() {
        doNothing().when(repository).deleteById(1);
        doThrow(DataIntegrityViolationException.class).when(repository).deleteById(2);
        doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(3);
        when(repository.findById(4)).thenReturn(Optional.of(ProdutoFactory.build(4, "Teste produto")));
        when(repository.findById(5)).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(ProdutoFactory.build(6, "Teste produto 6"));
        when(repository.findByTipoId(any(), any())).thenReturn(ProdutoFactory.buildPaged());
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
        Produto produto = service.findById(4);
        assertEquals(4, produto.getId());
        assertEquals("Teste produto", produto.getDescricao());
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
    @DisplayName("Deve inserir produto")
    void testShouldInsert() {
        Produto produto = service.insert(ProdutoFactory.build());
        assertEquals(6, produto.getId());
        assertEquals("Teste produto 6", produto.getDescricao());
    }

    @Test
    @DisplayName("Deve buscar lista de produtos por tipo paginada")
    void testShouldFindPaged() {
        Page<Produto> produtoPage = service.findByTipo(1, PageRequest.of(0, 2));
        assertEquals(2, produtoPage.getTotalElements());
    }
}
