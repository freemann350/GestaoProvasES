package pt.ipleiria.estg.dei.ei.esoft;

import java.io.Serializable;
import java.util.Objects;

public class Prova implements Serializable {

    private String nome;
    private Evento evento;
    private String Genero;
    private int escalaoPeso;
    private String faixaEtaria;
    private int tapetes;
    private boolean eliminado;

    public Prova(String nome, Evento evento, String genero, int escalaoPeso, String faixaEtaria, int tapetes) {
        this.nome = nome;
        this.evento = evento;
        Genero = genero;
        this.escalaoPeso = escalaoPeso;
        this.faixaEtaria = faixaEtaria;
        this.tapetes = tapetes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public int getEscalaoPeso() {
        return escalaoPeso;
    }

    public void setEscalaoPeso(int escalaoPeso) {
        this.escalaoPeso = escalaoPeso;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public int getTapetes() {
        return tapetes;
    }

    public void setTapetes(int tapetes) {
        this.tapetes = tapetes;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prova prova = (Prova) o;
        return Objects.equals(evento, prova.evento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(evento);
    }

    @Override
    public String toString() {
        return "Prova{" +
                "nome='" + nome + '\'' +
                ", evento=" + evento +
                ", Genero='" + Genero + '\'' +
                ", escalaoPeso=" + escalaoPeso +
                ", faixaEtaria='" + faixaEtaria + '\'' +
                ", tapetes=" + tapetes +
                ", eliminado=" + eliminado +
                '}';
    }
}
