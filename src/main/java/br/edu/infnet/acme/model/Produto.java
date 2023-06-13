package br.edu.infnet.acme.model;

import java.math.BigDecimal;
import java.nio.file.Path;

public class Produto {
    private Integer id;
    private String nome;
    private Path file;
    private BigDecimal preco;

    public Produto(Integer id, String nome, Path file, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.file = file;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Path getFile() {
        return file;
    }

    public void setFile(Path file) {
        this.file = file;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
