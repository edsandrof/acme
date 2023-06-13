package br.edu.infnet.acme.model;

import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class Pagamento implements Comparable<Pagamento> {

    private Integer id;
    private List<Produto> produtos;

    private LocalDateTime dataCompra;

    private Cliente cliente;

    public Pagamento() {
    }

    public Pagamento(Integer id, List<Produto> produtos, LocalDateTime dataCompra, Cliente cliente) {
        this.id = id;
        this.produtos = produtos;
        this.dataCompra = dataCompra;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int compareTo(Pagamento o) {
        return dataCompra.compareTo(o.getDataCompra());
    }

    @Override
    public String toString() {
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter()).create().toJson(this);
    }
}
