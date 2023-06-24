package br.edu.infnet.acme.application.exception;

public class PaymentIndexNotFoundException extends RuntimeException {

    public PaymentIndexNotFoundException(String message) {
        super(message);
    }
}
