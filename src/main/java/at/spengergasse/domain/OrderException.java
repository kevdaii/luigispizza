package at.spengergasse.domain;

public class OrderException extends RuntimeException{
    public OrderException(String message) {
        super(message);
    }
}
