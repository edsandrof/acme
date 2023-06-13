package br.edu.infnet.acme.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Assinatura {

    private Integer id;
    private BigDecimal mensalidade;

    private LocalDateTime begin;

    private LocalDateTime end;

    private Cliente cliente;

    public Assinatura(Integer id, BigDecimal mensalidade, LocalDateTime begin, Cliente cliente) {
        this.id = id;
        this.mensalidade = mensalidade;
        this.begin = begin;
        this.cliente = cliente;
    }

    public Assinatura(Integer id, BigDecimal mensalidade, LocalDateTime begin, LocalDateTime end, Cliente cliente) {
        this(id, mensalidade, begin, cliente);
        this.end = end;
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

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
