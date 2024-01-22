import org.example.api.implementation.Bandeira;
import org.example.api.implementation.Coordenada;
import org.example.api.interfaces.IBandeira;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testBandeira
{
    @DisplayName("Construtor correto da Bandeira")
    @Test
    public void testConstrutorCorretoBandeira_Valid()
    {
        assertDoesNotThrow(() -> { IBandeira bandeira = new Bandeira(0, "Bandeira", "Bandeira do Jogador 1", new Coordenada(0.0, 0.0)); }); //nao retorna nada porque foi criado contrutor com sucesso

        IBandeira bandeira = new Bandeira(0, "Bandeira", "Bandeira do Jogador 1", new Coordenada(0.0, 0.0));

        assertEquals(0, bandeira.getId()); //se o id == 6
        assertEquals("Bandeira", bandeira.getTipo()); //se o tipo == "Bandeira"
        assertEquals("Bandeira do Jogador 1", bandeira.getNome()); //se o nome == "Bandeira do Jogador 1"

        assertEquals(0.0, bandeira.getCoordenadas().getLatitude()); //se o coordenadas.latitude == 0.0, 0.0
        assertEquals(0.0, bandeira.getCoordenadas().getLongitude()); //se o coordenadas.longitude == 0.0, 0.0
    }
}
