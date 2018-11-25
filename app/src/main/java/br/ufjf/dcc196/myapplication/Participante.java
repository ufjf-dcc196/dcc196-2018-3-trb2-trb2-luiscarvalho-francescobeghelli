package br.ufjf.dcc196.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Participante {
    private String nome;
    private String cpf;
    private String email;

    private List<Evento> eventos;

    public Participante(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public Participante(String nome, String cpf, String email, List eventos) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.eventos = new ArrayList<Evento>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
