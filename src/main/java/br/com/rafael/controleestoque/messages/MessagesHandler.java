package br.com.rafael.controleestoque.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Locale;

@Component
public class MessagesHandler {

    private final MessageSource messageSource;

    @Autowired
    public MessagesHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String message, Object... parameters) {
        return messageSource.getMessage(message, Arrays.stream(parameters).toArray(), Locale.getDefault());
    }

    public String getMessage(String message) {
        return messageSource.getMessage(message, null, Locale.getDefault());
    }

}
