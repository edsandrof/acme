package br.edu.infnet.acme.service;

import br.edu.infnet.acme.model.Payment;

import java.util.Comparator;
import java.util.List;

public class PaymentService {
    private final List<Payment> payments;

    public PaymentService(List<Payment> payments) {
        this.payments = payments;
    }

    public void sortAndPrint(Comparator<Payment> comparator) {
        payments.stream()
                .sorted(comparator)
                .forEach(System.out::println);
    }
}
