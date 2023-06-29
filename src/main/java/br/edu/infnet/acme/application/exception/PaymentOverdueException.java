package br.edu.infnet.acme.application.exception;

public class PaymentOverdueException extends RuntimeException {

    public PaymentOverdueException(String message) {
        super(message);
    }
}
