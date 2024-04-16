package dalilagiu.U5W3D1.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class BadRequestException extends RuntimeException{

    //ATTRIBUTES LIST:
    private List<ObjectError> errorList;

    //CONSTRUCTORS:
    public BadRequestException(String message){
        super(message);
    }

    public BadRequestException(List<ObjectError> errorList) {
        super("There were been validation errors in the payload!");
        this.errorList = errorList;
    }
}
