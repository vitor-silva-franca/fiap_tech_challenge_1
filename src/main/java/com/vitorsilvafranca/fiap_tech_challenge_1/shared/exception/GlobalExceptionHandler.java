package com.vitorsilvafranca.fiap_tech_challenge_1.shared.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.vitorsilvafranca.fiap_tech_challenge_1.domain.model.usuario.RoleUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException ex) {
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(ApplicationException ex) {
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJsonParseError(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException invalidFormat && invalidFormat.getTargetType().isEnum()) {
            String fieldName = invalidFormat.getPath().get(0).getFieldName();
            String valorInvalido = String.valueOf(invalidFormat.getValue());
            String valoresValidos = Arrays.toString(RoleUsuario.values());

            String msg = "Valor inválido para o campo '" + fieldName + "': '" + valorInvalido + "'. Valores aceitos: " + valoresValidos;
            return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), msg), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Erro ao interpretar o JSON"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), mensagem), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioNotFound(UsuarioNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PedidoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePedidoNotFound(PedidoNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsuarioJaExisteException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioJaExiste(UsuarioJaExisteException ex) {
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidStatus(InvalidStatusException ex) {
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno no servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}