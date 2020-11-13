package main.exception;

import main.exception.YoutubeDataException;
import main.service.YoutubeErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice

public class ErrorHandler extends ResponseEntityExceptionHandler {
    @Autowired
    YoutubeErrorService youtubeErrorService;



    @ExceptionHandler(value = {YoutubeDataException.class})
    protected ResponseEntity<Object> handleConflict(YoutubeDataException ex, WebRequest request) {
        return handleExceptionInternal(ex,youtubeErrorService.getErrors(),new HttpHeaders(),youtubeErrorService.getMainStatus(), request);
    }
}
