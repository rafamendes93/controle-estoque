package br.com.rafael.controleestoque.services;

import br.com.rafael.controleestoque.messages.MessagesHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class BaseService<T> {

    @Autowired
    protected T repo;

    @Autowired
    protected MessagesHandler messagesHandler;

}
