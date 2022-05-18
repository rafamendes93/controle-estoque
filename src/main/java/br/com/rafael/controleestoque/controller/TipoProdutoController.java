package br.com.rafael.controleestoque.controller;

import br.com.rafael.controleestoque.controller.base.CrudController;
import br.com.rafael.controleestoque.entities.tipoproduto.TipoProdutoDto;
import br.com.rafael.controleestoque.mappers.TipoProdutoMapper;
import br.com.rafael.controleestoque.services.TipoProdutoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipo-produto")
public class TipoProdutoController extends CrudController<TipoProdutoDto, TipoProdutoService, TipoProdutoMapper, Integer> {


}
