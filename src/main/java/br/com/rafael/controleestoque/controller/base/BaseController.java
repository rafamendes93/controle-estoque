package br.com.rafael.controleestoque.controller.base;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController<S, M> {

    @Autowired
    protected S service;

    @Autowired
    protected M mapper;
}
