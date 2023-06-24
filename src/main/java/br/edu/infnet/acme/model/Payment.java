package br.edu.infnet.acme.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Payment {

    private List<Product> products;

    private LocalDateTime purchaseDate;

    private Customer customer;

    public Payment(List<Product> products, LocalDateTime purchaseDate, Customer customer) {
        this.products = products;
        this.purchaseDate = purchaseDate;
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Optional<BigDecimal> getOptionalPaymentSum() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add);
    }

    public double getDoublePaymentSum() {
        return getPaymentSum().doubleValue();
    }

    public BigDecimal getPaymentSum() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return "\t> customer: " + customer.getName() + ", " +
                "purchase date: " + purchaseDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
