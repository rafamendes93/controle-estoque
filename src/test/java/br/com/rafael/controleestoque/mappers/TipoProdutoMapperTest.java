package br.com.rafael.controleestoque.mappers;

import br.com.rafael.controleestoque.entities.tipoproduto.TipoProduto;
import br.com.rafael.controleestoque.entities.tipoproduto.TipoProdutoDto;
import br.com.rafael.controleestoque.utils.TipoProdutoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DisplayName("Tipo Produto Mapper")
class TipoProdutoMapperTest {

    @InjectMocks
    private TipoProdutoMapper mapper;

    @DisplayName("Deve fazer map de dto para entidade")
    @Test
    void testToEntity() {
        TipoProdutoDto dto = TipoProdutoFactory.buildDto();
        TipoProduto tipoProduto = mapper.toEntity(dto);
        assertEquals(1, tipoProduto.getId());
        assertEquals("Tipo produto teste", tipoProduto.getDescricao());

    }

    @DisplayName("Deve fazer map de entidade para dto")
    @Test
    void testToDto() {
        TipoProduto entidade = TipoProdutoFactory.build();
        TipoProdutoDto dto = mapper.toDto(entidade);
        assertEquals(1, dto.getId());
        assertEquals("Tipo produto Teste", dto.getDescricao());
    }
}
