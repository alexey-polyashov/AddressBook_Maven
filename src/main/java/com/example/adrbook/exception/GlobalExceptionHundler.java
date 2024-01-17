package com.example.adrbook.exception;

import com.example.adrbook.dto.MyErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHundler {

    @ExceptionHandler(value
            = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody MyErrorInfo handlerNotFoundException(Exception e){
        return new MyErrorInfo(e.getMessage(), "");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody String otherException(Exception e){
        e.printStackTrace();
        return e.toString();
    }

}
