package br.com.rafael.controleestoque.exceptions.handlers;

import br.com.rafael.controleestoque.exceptions.*;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NaoEncontradoException.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .errorMessage(NOT_FOUND.getReasonPhrase())
                .statusCode(NOT_FOUND.value())
                .statusDescription(List.of(ex.getLocalizedMessage()))
                .build();

        return handleExceptionInternal(ex, error,
                new HttpHeaders(), NOT_FOUND, request);
    }

    @ExceptionHandler(value = {EstoqueInsuficienteException.class, RegistrosDependentesException.class, IllegalArgumentException.class})
    protected ResponseEntity<Object> handleEstoqueInsuficiente(RuntimeException ex, WebRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .errorMessage(BAD_REQUEST.getReasonPhrase())
                .statusCode(BAD_REQUEST.value())
                .statusDescription(List.of(ex.getLocalizedMessage()))
                .build();

        return handleExceptionInternal(ex, error,
                new HttpHeaders(), BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatus status,
                                                                  @NonNull WebRequest request) {
        List<String> errorsMessage = new ArrayList<>();
        for (ObjectError oe : ex.getAllErrors()) {
            errorsMessage.add(oe.getDefaultMessage());
        }

        ErrorResponse error = ErrorResponse.builder()
                .errorMessage(BAD_REQUEST.getReasonPhrase())
                .statusCode(BAD_REQUEST.value())
                .statusDescription(errorsMessage)
                .build();
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), BAD_REQUEST, request);
    }
}
