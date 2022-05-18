package br.com.rafael.controleestoque.mappers;

import br.com.rafael.controleestoque.entities.movimentoestoque.*;
import br.com.rafael.controleestoque.utils.MovimentoEstoqueFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DisplayName("Movimento Estoque Mapper")
class MovimentoEstoqueMapperTest {

    @InjectMocks
    private MovimentoEstoqueMapper mapper;

    @DisplayName("Deve fazer map de dto para entidade")
    @Test
    void testToEntity() throws Throwable {
        MovimentoEstoqueDto dto = MovimentoEstoqueFactory.buildDto();
        MovimentoEstoque entidade = mapper.toEntity(dto);
        assertEquals(1, entidade.getId());
        assertEquals(1, entidade.getProduto().getId());
        assertEquals(BigDecimal.valueOf(1500.00), entidade.getValorVenda());
        assertEquals(TipoMovimento.E, entidade.getTipoMovimento());
    }

    @DisplayName("Deve fazer map de entidade para dto")
    @Test
    void testToDto() {
        MovimentoEstoque entidade = MovimentoEstoqueFactory.build();
        MovimentoEstoqueDto dto = mapper.toDto(entidade);
        assertEquals(1, dto.getId());
        assertEquals(1, dto.getProdutoId());
        assertEquals(BigDecimal.valueOf(1500.00), dto.getValorVenda());
        assertEquals(TipoMovimento.E, dto.getTipoMovimento());
    }
}
