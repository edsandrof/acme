package br.edu.infnet.acme.service;

import br.edu.infnet.acme.application.exception.PaymentIndexNotFoundException;
import br.edu.infnet.acme.model.Payment;
import br.edu.infnet.acme.model.Product;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        validateIndex(index);

        return payments.get(index).getOptionalPaymentSum();
    }

    public double getDoublePaymentSum(int index) {
        validateIndex(index);

        return payments.get(index).getDoublePaymentSum();
    }

    public BigDecimal getAllPaymentSum() {
        return payments.stream()
                .map(Payment::getPaymentSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<Product, Long> getAmountProductSold() {
        return payments.stream()
                .flatMap(payment -> payment.getProducts().stream())
                .collect(Collectors.groupingBy(product -> product, Collectors.counting()));
    }

    private void validateIndex(int index) {

        if (index > payments.size()) {
            throw new PaymentIndexNotFoundException("Error: Payment with index " + index + " was not found!");
        }
    }
}
