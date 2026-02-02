package com.salesianostriana.dam.precioustime.error;

import com.salesianostriana.dam.precioustime.category.exception.CategoryDuplicatedException;
import com.salesianostriana.dam.precioustime.shared.exception.BadRequestException;
import com.salesianostriana.dam.precioustime.shared.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

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

}
