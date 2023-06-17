package br.edu.infnet.acme.model;

public class Cliente {

    private Integer id;
    private String nome;

    public Cliente() {}

    public Cliente(Integer id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome;
    }
}
