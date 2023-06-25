package br.edu.infnet.acme.service;

import br.edu.infnet.acme.application.exception.PaymentIndexNotFoundException;
import br.edu.infnet.acme.model.Customer;
import br.edu.infnet.acme.model.Payment;
import br.edu.infnet.acme.model.Product;

import java.math.BigDecimal;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PaymentService {
    private final List<Payment> payments;

    public PaymentService(List<Payment> payments) {
        this.payments = payments;
    }

    public void sortAndPrint(Comparator<Payment> comparator) {
        payments.stream()
                .sorted(comparator)
                .map(p -> String.format("\t> %s's payment purchase date: %s", p.getCustomer().getName(), p.getPurchaseDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))))
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

    public Map<String, List<Product>> getMapCustomerProducts() {
        return payments.stream()
                .collect(Collectors.groupingBy(
                        payment -> payment.getCustomer().getName(),
                        Collectors.mapping(Payment::getProducts,
                                Collectors.flatMapping(List::stream, Collectors.toList())
                        )
                ));
    }

    public Map<Customer, BigDecimal> getTopCustomers() {
        Function<Payment, BigDecimal> reducing = payment -> payment.getProducts().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return payments.stream()
                .collect(Collectors.groupingBy(Payment::getCustomer,
                        Collectors.reducing(BigDecimal.ZERO, reducing, BigDecimal::add)));
    }

    public BigDecimal howMuchBilledMonth(Month month, int year) {
        return payments.stream()
                .filter(payment -> payment.getPurchaseDate().getYear() == year)
                .filter(payment -> payment.getPurchaseDate().getMonth().equals(month))
                .flatMap(payment -> payment.getProducts().stream())
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void validateIndex(int index) {

        if (index > payments.size()) {
            throw new PaymentIndexNotFoundException("Error: Payment with index " + index + " was not found!");
        }
    }
}
