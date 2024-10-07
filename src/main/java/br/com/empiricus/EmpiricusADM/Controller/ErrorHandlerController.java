package br.com.empiricus.EmpiricusADM.Controller;

import br.com.empiricus.EmpiricusADM.Model.Exceptions.ErrorHandler;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandlerController {
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorHandler> handler(MethodArgumentNotValidException exception){
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ErrorHandler> errorHandlerList = new ArrayList<>();
        fieldErrors.forEach( error->{
            errorHandlerList.add(new ErrorHandler(error.getField(), error.getDefaultMessage()));
        });
        return  errorHandlerList;
    }
}
