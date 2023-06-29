package pt.ipleiria.estg.dei.ei.esoft;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Atleta implements Serializable {
        private String nome;
        private String pais;
        private String genero;
        private String dataNascimento;
        private int peso;
        private String contacto;
        private boolean eliminado;

    public Atleta(String nome, String pais, String genero, String dataNascimento, int peso, String contacto) {
            this.nome = nome;
            this.pais = pais;
            this.genero = genero;
            this.dataNascimento = dataNascimento;
            this.peso = peso;
            this.contacto = contacto;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getPais() {
            return pais;
        }

        public void setPais(String pais) {
            this.pais = pais;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }

        public String getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(String dataNascimento) {
            this.dataNascimento = dataNascimento;
        }

        public String getContacto() {
            return contacto;
        }

        public void setContacto(String contacto) {
            this.contacto = contacto;
        }

        public boolean isEliminado() {
            return eliminado;
        }

        public void setEliminado(boolean eliminado) {
            this.eliminado = eliminado;
        }

        public int getPeso() {
            return peso;
        }

        public void setPeso(int peso) {
            this.peso = peso;
        }

    @Override
    public String toString() {
        return "Atleta{" +
                "nome='" + nome + '\'' +
                ", pais='" + pais + '\'' +
                ", genero='" + genero + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", peso=" + peso +
                ", contacto='" + contacto + '\'' +
                '}';
    }
}
