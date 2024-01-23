import org.example.api.implementation.Bandeira;
import org.example.api.implementation.Coordenada;
import org.example.api.implementation.Localizacao;
import org.example.api.interfaces.IBandeira;
import org.example.api.interfaces.ILocalizacao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testLocalizacao
{
    @DisplayName("Construtor correto da Localizacao")
    @Test
    public void testConstrutorCorretoLocalizacao_Valid()
    {
        assertDoesNotThrow(() -> { ILocalizacao localizacao = new Localizacao(0, "Localizacao", "T Ramp", new Coordenada(0.0, 0.0)); }); //nao retorna nada porque foi criado contrutor com sucesso

        ILocalizacao localizacao = new Localizacao(0, "Localizacao", "T Ramp", new Coordenada(0.0, 0.0));

        assertEquals(0, localizacao.getId()); //se o id == 0
        assertEquals("Localizacao", localizacao.getTipo()); //se o tipo == "Localizacao"
        assertEquals("T Ramp", localizacao.getNome()); //se o nome == "T Ramp"

        assertEquals(0.0, localizacao.getCoordenadas().getLatitude()); //se o coordenadas.latitude == 0.0
        assertEquals(0.0, localizacao.getCoordenadas().getLongitude()); //se o coordenadas.longitude == 0.0
    }


    @DisplayName("Getters e Setters da Localizacao")
    @Test
    public void testGettersSettersLocalizacao_Valid()
    {
        ILocalizacao localizacao = new Localizacao(0, "Localizacao", "T Ramp", new Coordenada(0.0, 0.0));

        assertEquals(0, localizacao.getId()); //se o id == 0
        localizacao.setId(1);
        assertEquals(1, localizacao.getId()); //se o id == 1

        assertEquals("Localizacao", localizacao.getTipo()); //se o tipo == "Localizacao"
        localizacao.setTipo("Bandeira");
        assertEquals("Bandeira", localizacao.getTipo()); //se o tipo == "Bandeira"

        assertEquals("T Ramp", localizacao.getNome()); //se o nome == "T Ramp"
        localizacao.setNome("Connector");
        assertEquals("Connector", localizacao.getNome()); //se o nome == "Connector"

        assertEquals(0.0, localizacao.getCoordenadas().getLatitude()); //se o coordenadas.latitude == 0.0
        assertEquals(0.0, localizacao.getCoordenadas().getLongitude()); //se o coordenadas.longitude == 0.0
        localizacao.setCoordenadas(new Coordenada(1.0, 1.0));
        assertEquals(1.0, localizacao.getCoordenadas().getLatitude()); //se o coordenadas.latitude == 1.0
        assertEquals(1.0, localizacao.getCoordenadas().getLongitude()); //se o coordenadas.longitude == 1.0
    }
}
