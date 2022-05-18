package br.com.rafael.controleestoque.mappers;

import br.com.rafael.controleestoque.entities.produto.Produto;
import br.com.rafael.controleestoque.entities.produto.ProdutoDto;
import br.com.rafael.controleestoque.services.MovimentoEstoqueService;
import br.com.rafael.controleestoque.services.TipoProdutoService;
import br.com.rafael.controleestoque.utils.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("Produto Mapper")
class ProdutoMapperTest {

    @InjectMocks
    private ProdutoMapper mapper;

    @Mock
    private TipoProdutoService tipoProdutoService;

    @Mock
    private MovimentoEstoqueService movimentoEstoqueService;

    @BeforeEach
    void setUp() throws Throwable {
        when(tipoProdutoService.findById(any())).thenReturn(TipoProdutoFactory.build());
        when(movimentoEstoqueService.getQuantidadeByProduto(any()))
                .thenReturn(MovimentoEstoqueFactory.buildTotalizador());
    }

    @DisplayName("Deve fazer map de dto para entidade")
    @Test
    void testToEntity() throws Throwable {
        ProdutoDto produtoDto = ProdutoFactory.buildDto();
        Produto entidade = mapper.toEntity(produtoDto);
        assertEquals(1, entidade.getId());
        assertEquals("Produto teste", entidade.getDescricao());
        assertEquals(1, entidade.getTipo().getId());
        assertEquals("Tipo produto Teste", entidade.getTipo().getDescricao());
    }

    @DisplayName("Deve fazer map de entidade para dto")
    @Test
    void testToDto() {
        Produto entidade = ProdutoFactory.build();
        ProdutoDto produtoDto = mapper.toDto(entidade);
        assertEquals(1, produtoDto.getId());
        assertEquals("Produto teste", produtoDto.getDescricao());
        assertEquals(5L, produtoDto.getQuantidadeEstoque());
        assertEquals(10L, produtoDto.getQuantidadeEntrada());
        assertEquals(5L, produtoDto.getQuantidadeVenda());
    }
}

