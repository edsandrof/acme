package br.edu.infnet.acme.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    @Override
    public String toString() {
        return "\t> customer: " + customer.getName() + ", " +
                "purchase date: " + purchaseDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
