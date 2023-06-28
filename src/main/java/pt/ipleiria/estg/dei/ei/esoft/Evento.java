package pt.ipleiria.estg.dei.ei.esoft;

import java.io.Serializable;
import java.util.Objects;

public class Evento implements Serializable {

    private String nome;
    private String dataInicio;
    private String dataFim;
    private String hora;
    private String pais;
    private String local;
    private boolean eliminado;

    public Evento(String nome, String dataInicio, String dataFim, String hora, String pais, String local) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.hora = hora;
        this.pais = pais;
        this.local = local;
        this.eliminado = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
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
        Evento evento = (Evento) o;
        return eliminado == evento.eliminado && Objects.equals(nome, evento.nome) && Objects.equals(dataInicio, evento.dataInicio) && Objects.equals(dataFim, evento.dataFim) && Objects.equals(hora, evento.hora) && Objects.equals(pais, evento.pais) && Objects.equals(local, evento.local);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, dataInicio, dataFim, hora, pais, local, eliminado);
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nome='" + nome + '\'' +
                ", dataInicio='" + dataInicio + '\'' +
                ", dataFim='" + dataFim + '\'' +
                ", hora='" + hora + '\'' +
                ", pais='" + pais + '\'' +
                ", local='" + local + '\'' +
                ", eliminado=" + eliminado +
                '}';
    }
}
