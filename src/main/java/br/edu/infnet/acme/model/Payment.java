package br.edu.infnet.acme.model;

import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class Payment implements Comparable<Payment> {

    private Integer id;
    private List<Product> products;

    private LocalDateTime dataCompra;

    private Customer customer;

    public Payment() {
    }

    public Payment(Integer id, List<Product> products, LocalDateTime dataCompra, Customer customer) {
        this.id = id;
        this.products = products;
        this.dataCompra = dataCompra;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getProdutos() {
        return products;
    }

    public void setProdutos(List<Product> products) {
        this.products = products;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Customer getCliente() {
        return customer;
    }

    public void setCliente(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int compareTo(Payment o) {
        return dataCompra.compareTo(o.getDataCompra());
    }

    @Override
    public String toString() {
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter()).create().toJson(this);
    }
}
