package pt.ipleiria.estg.dei.ei.esoft;

import java.io.Serializable;
import java.util.Objects;

public class Prova implements Serializable {

    private String nome;
    private Evento evento;
    private String genero;
    private int escalaoPeso;
    private String faixaEtaria;
    private int tapetes;
    private boolean eliminado;

    public Prova(String nome, Evento evento, String genero, int escalaoPeso, String faixaEtaria, int tapetes) {
        this.nome = nome;
        this.evento = evento;
        this.genero = genero;
        this.escalaoPeso = escalaoPeso;
        this.faixaEtaria = faixaEtaria;
        this.tapetes = tapetes;
        this.eliminado = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome() {
        String nomeGenero = null;
        if (this.genero.equals("MASCULINO")) {
            nomeGenero = "M";
        } else {
            nomeGenero = "F";
        }

        System.out.println(nomeGenero);
        this.nome = "Prova " +  this.getEscalaoPeso() + "Kg " + this.getFaixaEtaria() +" "+ nomeGenero +"";
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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
                ", Genero='" + genero + '\'' +
                ", escalaoPeso=" + escalaoPeso +
                ", faixaEtaria='" + faixaEtaria + '\'' +
                ", tapetes=" + tapetes +
                ", eliminado=" + eliminado +
                '}';
    }
}
