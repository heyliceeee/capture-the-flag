import org.example.api.implementation.Bandeira;
import org.example.api.implementation.Coordenada;
import org.example.api.interfaces.IBandeira;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testBandeira
{
    @DisplayName("Construtor correto da Bandeira")
    @Test
    public void testConstrutorCorretoBandeira_Valid()
    {
        assertDoesNotThrow(() -> { IBandeira bandeira = new Bandeira(0, "Bandeira", "Stairs", new Coordenada(0.0, 0.0)); }); //nao retorna nada porque foi criado contrutor com sucesso

        IBandeira bandeira = new Bandeira(0, "Bandeira", "Stairs", new Coordenada(0.0, 0.0));

        assertEquals(0, bandeira.getId()); //se o id == 0
        assertEquals("Bandeira", bandeira.getTipo()); //se o tipo == "Bandeira"
        assertEquals("Stairs", bandeira.getNome()); //se o nome == "Stairs"

        assertEquals(0.0, bandeira.getCoordenadas().getLatitude()); //se o coordenadas.latitude == 0.0
        assertEquals(0.0, bandeira.getCoordenadas().getLongitude()); //se o coordenadas.longitude == 0.0
    }


    @DisplayName("Getters e Setters da Bandeira")
    @Test
    public void testGettersSettersBandeira_Valid()
    {
        IBandeira bandeira = new Bandeira(0, "Bandeira", "Stairs", new Coordenada(0.0, 0.0));

        assertEquals(0, bandeira.getId()); //se o id == 0
        bandeira.setId(1);
        assertEquals(1, bandeira.getId()); //se o id == 1

        assertEquals("Bandeira", bandeira.getTipo()); //se o tipo == "Bandeira"
        bandeira.setTipo("Localizacao");
        assertEquals("Localizacao", bandeira.getTipo()); //se o tipo == "Localizacao"

        assertEquals("Stairs", bandeira.getNome()); //se o nome == "Stairs"
        bandeira.setNome("Connector");
        assertEquals("Connector", bandeira.getNome()); //se o nome == "Connector"

        assertEquals(0.0, bandeira.getCoordenadas().getLatitude()); //se o coordenadas.latitude == 0.0
        assertEquals(0.0, bandeira.getCoordenadas().getLongitude()); //se o coordenadas.longitude == 0.0
        bandeira.setCoordenadas(new Coordenada(1.0, 1.0));
        assertEquals(1.0, bandeira.getCoordenadas().getLatitude()); //se o coordenadas.latitude == 1.0
        assertEquals(1.0, bandeira.getCoordenadas().getLongitude()); //se o coordenadas.longitude == 1.0
    }
}
