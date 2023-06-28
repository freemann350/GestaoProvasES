import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.dei.ei.esoft.Evento;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class EventoTestCase {
    private LinkedList<Evento> eventos;
    private Evento evento1,evento2,evento3;
    @BeforeEach
    public void setUp() {
        eventos = new LinkedList<>();
        evento1 = new Evento("Evento Europeu","01-10-2022","01-11-2022","12:12","ESPANHA","ANDALUZIA");
        evento2 = new Evento("Evento Alemão","03-04-2021","05-04-2021","13:13","ALEMANHA","BERLIM");
        evento3 = new Evento("Evento Europeu","01-10-2022","01-11-2022","14:14","FRANÇA","BORDEAUX");
        evento2.setEliminado(true);
        eventos.add(evento1);
        eventos.add(evento2);
        eventos.add(evento3);
    }
    @Test
    public void testCriarEvento() {
        Evento evento = new Evento("Evento Europeu","01-10-2022","01-11-2022","12:12","ESPANHA","ANDALUZIA");
        assertEquals("Evento Europeu", evento.getNome());
        assertEquals("01-10-2022", evento.getDataInicio());
        assertEquals("01-11-2022", evento.getDataFim());
        assertEquals("12:12", evento.getHora());
        assertEquals("ESPANHA", evento.getPais());
        assertEquals("ANDALUZIA", evento.getLocal());
    }

    @Test
    public void testEditarEvento() {
        evento1.setNome("Evento Europeu V2");
        evento1.setDataInicio("02-10-2022");
        evento1.setDataFim("06-10-2022");
        evento1.setHora("15:15");
        evento1.setPais("BÉLGICA");
        evento1.setLocal("BRUXELAS");

        assertEquals("Evento Europeu V2", evento1.getNome());
        assertEquals("02-10-2022", evento1.getDataInicio());
        assertEquals("06-10-2022", evento1.getDataFim());
        assertEquals("15:15", evento1.getHora());
        assertEquals("BÉLGICA", evento1.getPais());
        assertEquals("BRUXELAS", evento1.getLocal());
    }

    @Test
    public void testEliminarEvento() {
        int eventosAtivos=0;
        for (Evento evento: eventos) {
            if (!evento.isEliminado())
                eventosAtivos++;
        }
        assertEquals(2,eventosAtivos);

        evento3.setEliminado(true);

        eventosAtivos=0;
        for (Evento evento: eventos) {
            if (!evento.isEliminado())
                eventosAtivos++;
        }
        assertEquals(1,eventosAtivos);

    }
}
