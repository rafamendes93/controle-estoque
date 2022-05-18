package br.com.rafael.controleestoque.services;

import br.com.rafael.controleestoque.entities.tipoproduto.TipoProduto;
import br.com.rafael.controleestoque.repositories.TipoProdutoRepository;
import br.com.rafael.controleestoque.services.base.CrudService;
import org.springframework.stereotype.Service;

@Service
public class TipoProdutoService extends CrudService<TipoProduto, TipoProdutoRepository, Integer> {
}
