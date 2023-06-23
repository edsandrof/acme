package br.edu.infnet.acme.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class Subscription {

    private Integer id;
    private BigDecimal mensalidade;

    private LocalDateTime begin;

    private Optional<LocalDateTime> end;

    private Cliente cliente;

    public Subscription(Integer id, BigDecimal mensalidade, LocalDateTime begin, Cliente cliente) {
        this.id = id;
        this.mensalidade = mensalidade;
        this.begin = begin;
        this.cliente = cliente;
        this.end = Optional.empty();
    }

    public Subscription(Integer id, BigDecimal mensalidade, LocalDateTime begin, LocalDateTime end, Cliente cliente) {
        this(id, mensalidade, begin, cliente);
        this.end = Optional.of(end);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(BigDecimal mensalidade) {
        this.mensalidade = mensalidade;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public Optional<LocalDateTime> getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = Optional.of(end);
    }

    public Cliente getCliente() {
        return cliente;
    }
}
