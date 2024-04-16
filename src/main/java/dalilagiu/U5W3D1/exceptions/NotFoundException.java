package dalilagiu.U5W3D1.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(long id) {
        super("Element with id " + id + " not found!");
    }
}
