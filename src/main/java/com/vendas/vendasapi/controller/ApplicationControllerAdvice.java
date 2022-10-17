package com.vendas.vendasapi.controller;

import com.vendas.vendasapi.exception.ApiErrors;
import com.vendas.vendasapi.exception.PedidoNaoEncontradoException;
import com.vendas.vendasapi.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);

    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException exception) {
        return new ApiErrors(exception.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodsNotValidException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }
}