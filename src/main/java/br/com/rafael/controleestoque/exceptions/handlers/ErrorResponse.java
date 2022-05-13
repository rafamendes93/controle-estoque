package br.com.rafael.controleestoque.exceptions.handlers;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse implements Serializable {

    private Integer statusCode;
    private String statusDescription;
    private String errorMessage;

}
