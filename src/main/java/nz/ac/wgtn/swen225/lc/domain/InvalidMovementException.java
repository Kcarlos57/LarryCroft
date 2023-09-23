package nz.ac.wgtn.swen225.lc.domain;

public class InvalidMovementException extends RuntimeException {
    public InvalidMovementException(){
        super();
    }
    public InvalidMovementException(String errorMessage){
        super(errorMessage);
    }
    public InvalidMovementException(String errorMessage, Throwable e){
        super(errorMessage,e);
    }
}
