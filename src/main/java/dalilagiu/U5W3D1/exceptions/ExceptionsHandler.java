package dalilagiu.U5W3D1.exceptions;

import dalilagiu.U5W3D1.payloads.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    public ErrorResponseDTO handleBadRequest(BadRequestException ex){
        if(ex.getErrorList() != null) {
            String message = ex.getErrorList().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));
            return new ErrorResponseDTO(message, LocalDateTime.now());
        } else {
            return new ErrorResponseDTO(ex.getMessage(), LocalDateTime.now());
        }
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)//404
    public ErrorResponseDTO handleNotFound(NotFoundException ex) {
        return new ErrorResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
    public ErrorResponseDTO handleGenericErrors(Exception ex) {
        ex.printStackTrace();
        return new ErrorResponseDTO("Problem server side", LocalDateTime.now());
    }
}
