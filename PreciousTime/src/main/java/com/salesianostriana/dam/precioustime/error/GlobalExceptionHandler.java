package com.salesianostriana.dam.precioustime.error;

import com.salesianostriana.dam.precioustime.category.exception.CategoryDuplicatedException;
import com.salesianostriana.dam.precioustime.shared.exception.BadRequestException;
import com.salesianostriana.dam.precioustime.shared.exception.NotFoundException;
import org.jspecify.annotations.Nullable;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("Entidad no encontrada");
        pd.setType(URI.create("https://www.salesianos-triana.com/not-found"));
        return pd;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleBadRequestException(BadRequestException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        pd.setTitle("Parámetros incorrectos");
        pd.setType(URI.create("https://www.salesianos-triana.com/bad-request"));
        return pd;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CategoryDuplicatedException.class)
    public ProblemDetail handleCategoryDuplicatedException(CategoryDuplicatedException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        pd.setTitle("Datos duplicados");
        pd.setType(URI.create("https://www.salesianos-triana.com/conflict"));
        return pd;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ProblemDetail handleUsernameNotFoundException(UsernameNotFoundException ex) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("Username not found");
        pd.setType(URI.create("https://www.salesianos-triana.com/not-found"));
        return pd;
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Error de parámetros");
        List<ApiValidationSubError> subErrors = ex.getAllErrors()
                .stream()
                .map(ApiValidationSubError::from)
                .toList();
        pd.setProperty("invalid-params", subErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(pd);
    }
}
