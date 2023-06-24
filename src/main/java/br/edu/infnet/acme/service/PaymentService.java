package br.edu.infnet.acme.service;

import br.edu.infnet.acme.application.exception.PaymentIndexNotFoundException;
import br.edu.infnet.acme.model.Payment;
import br.edu.infnet.acme.model.Product;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

    public Optional<BigDecimal> getOptionalPaymentSum(int index) {
        try {
            return payments.get(index).getProducts()
                    .stream()
                    .map(Product::getPrice)
                    .reduce(BigDecimal::add);

        } catch (IndexOutOfBoundsException e) {
            throw new PaymentIndexNotFoundException("Error: Payment with index " + index + " was not found!" + e.getMessage());
        }
    }
}
