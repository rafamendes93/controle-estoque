package br.com.rafael.controleestoque.exceptions.handlers;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse implements Serializable {

    private Integer statusCode;

    @Builder.Default
    private List<String> statusDescription = new ArrayList<>();

    private String errorMessage;

}
