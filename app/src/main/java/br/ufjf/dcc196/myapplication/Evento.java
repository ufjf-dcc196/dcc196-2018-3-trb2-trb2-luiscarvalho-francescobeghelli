package br.ufjf.dcc196.myapplication;

public class Evento {

    private String titulo;
    private String dia;
    private String horario;
    private String facilitador;
    private String descricao;

    public Evento(String titulo, String dia, String horario, String facilitador, String descricao) {
        this.titulo = titulo;
        this.dia = dia;
        this.horario = horario;
        this.facilitador = facilitador;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getFacilitador() {
        return facilitador;
    }

    public void setFacilitador(String facilitador) {
        this.facilitador = facilitador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
