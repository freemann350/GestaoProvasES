import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.dei.ei.esoft.Atleta;

import static org.junit.jupiter.api.Assertions.*;

public class AtletaTestCase {
    Atleta atleta1;
    @BeforeEach
    public void setUp() {
        atleta1 = new Atleta("João", "BÉLGICA", "MASCULINO","06-02-2006",44,"912345678");
    }

    @Test
    public void testCriarAtleta() {
        Atleta atleta = new Atleta("Maria", "PORTUGAL", "FEMININO","03-03-2003",57,"987654321");
        assertEquals("Maria", atleta.getNome());
        assertEquals("PORTUGAL", atleta.getPais());
        assertEquals("FEMININO", atleta.getGenero());
        assertEquals("03-03-2003", atleta.getDataNascimento());
        assertEquals(57, atleta.getPeso());
        assertEquals("987654321", atleta.getContacto());
    }

    @Test
    public void testEditarAtleta() {
        atleta1.setNome("Manuel Pires");
        atleta1.setPais("FRANÇA");
        atleta1.setGenero("MASCULINO");
        atleta1.setDataNascimento("05-05-2005");
        atleta1.setPeso(39);
        atleta1.setContacto("921321321");


        assertEquals("Manuel Pires", atleta1.getNome());
        assertEquals("FRANÇA", atleta1.getPais());
        assertEquals("MASCULINO", atleta1.getGenero());
        assertEquals("05-05-2005", atleta1.getDataNascimento());
        assertEquals(39, atleta1.getPeso());
        assertEquals("921321321", atleta1.getContacto());
    }
}
