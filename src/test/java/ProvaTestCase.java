import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.dei.ei.esoft.Evento;
import pt.ipleiria.estg.dei.ei.esoft.Prova;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class ProvaTestCase {
    private LinkedList<Prova> provas;
    private LinkedList<Evento> eventos;
    Evento evento1,evento2,evento3;
    Prova prova1,prova2,prova3;
    @BeforeEach
    public void setUp() {
        provas = new LinkedList<>();
        eventos = new LinkedList<>();
        evento1 = new Evento("Evento Europeu","01-10-2022","01-11-2022","12:12","ESPANHA","ANDALUZIA");
        evento2 = new Evento("Evento Alemão","03-04-2021","05-04-2021","13:13","ALEMANHA","BERLIM");
        evento3 = new Evento("Evento Europeu","01-10-2022","01-11-2022","14:14","FRANÇA","BORDEAUX");
        evento2.setEliminado(true);

        eventos.add(evento1);
        eventos.add(evento2);
        eventos.add(evento3);

        prova1 = new Prova(null,evento1,"FEMININO",65,"JUVENIS",6);
        prova2 = new Prova(null,evento1,"FEMININO",65,"JUVENIS",6);
        prova3 = new Prova(null,evento1,"FEMININO",65,"JUVENIS",6);
        prova2.setEliminado(true);

        provas.add(prova1);
        provas.add(prova2);
        provas.add(prova3);
    }

    @Test
    public void testCriarProva() {
        Prova prova = new Prova(null,evento1,"FEMININO",65,"JUVENIS",6);
        prova.setNome();
        provas.add(prova);

        assertEquals(prova.getNome(),"Prova 65Kg JUVENIS (F)","NOME INCORRETO!");
        assertEquals(prova.getEvento(),evento1,"EVENTO INCORRETO!");
        assertEquals(prova.getGenero(),"FEMININO","GÉNERO INCORRETO!");
        assertEquals(prova.getEscalaoPeso(),65,"ESCALÃO DE PESO INCORRETO!");
        assertEquals(prova.getFaixaEtaria(),"JUVENIS","FAIXA ETÁRIA INCORRETA!");
        assertEquals(prova.getTapetes(),6,"NÚMERO DE TAPETES INCORRETOS!");
    }

    @Test
    public void testEditarProva() {

        prova1.setEvento(evento3);
        prova1.setGenero("MASCULINO");
        prova1.setEscalaoPeso(75);
        prova1.setFaixaEtaria("SENIORES");
        prova1.setTapetes(2);
        prova1.setNome();

        assertEquals(prova1.getNome(),"Prova 75Kg SENIORES (M)","NOME INCORRETO!");
        assertEquals(prova1.getEvento(),evento3,"EVENTO INCORRETO!");
        assertEquals(prova1.getGenero(),"MASCULINO","GÉNERO INCORRETO!");
        assertEquals(prova1.getEscalaoPeso(),75,"ESCALÃO DE PESO INCORRETO!");
        assertEquals(prova1.getFaixaEtaria(),"SENIORES","FAIXA ETÁRIA INCORRETA!");
        assertEquals(prova1.getTapetes(),2,"NÚMERO DE TAPETES INCORRETOS!");
    }

    @Test
    public void testEliminarProva() {
        int provasAtivas=0;
        for (Prova prova: provas) {
            if (!prova.isEliminado())
                provasAtivas++;
        }
        assertEquals(2,provasAtivas);

        prova3.setEliminado(true);

        provasAtivas=0;
        for (Prova prova: provas) {
            if (!prova.isEliminado())
                provasAtivas++;
        }
        assertEquals(1,provasAtivas);
    }

    @Test
    public void testAssociarProvaAEventoEliminado() {
        prova1.setEvento(evento2);

        if (prova1.getEvento().isEliminado())
            System.out.println("EVENTO \"" + prova1.getEvento().getNome() + "\" ENCONTRA-SE ELIMINADO!");

        prova1.setEvento(evento3);

        if (!prova1.getEvento().isEliminado())
            System.out.println("EVENTO \"" + prova1.getEvento().getNome() + "\" NÃO ENCONTRA-SE ELIMINADO!");

        assertNotEquals(true,prova1.getEvento().isEliminado(),"EVENTO ENCONTRA-SE ELIMINADA!");
    }
}
