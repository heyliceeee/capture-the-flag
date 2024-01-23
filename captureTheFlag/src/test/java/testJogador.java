import org.example.api.implementation.Bandeira;
import org.example.api.implementation.Coordenada;
import org.example.api.implementation.Jogador;
import org.example.api.interfaces.IBandeira;
import org.example.api.interfaces.IJogador;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testJogador
{
    @DisplayName("Construtor correto do Jogador")
    @Test
    public void testConstrutorCorretoJogador_Valid()
    {
        IBandeira bandeira = new Bandeira(0, "Bandeira", "Stairs", new Coordenada(0.0, 0.0));

        assertDoesNotThrow(() -> { IJogador jogador = new Jogador("Jogador 1", 0, bandeira); }); //nao retorna nada porque foi criado contrutor com sucesso

        IJogador jogador = new Jogador("Jogador 1", 0, bandeira);

        assertEquals("Jogador 1", jogador.getNome()); //se o nome == "Jogador 1"
        assertEquals(0, jogador.getNumeroBots()); //se o bots == 0
        assertEquals(bandeira, jogador.getBandeira());
    }


    @DisplayName("Construtor incorreto do Jogador")
    @Test
    public void testConstrutorIncorretoJogador_Valid()
    {
        assertThrows(IllegalArgumentException.class, () -> { IJogador jogador = new Jogador(null, 0, null);  }); //retorna erro porque foi criado contrutor sem sucesso
    }


    @DisplayName("Getters e Setters da Jogador")
    @Test
    public void testGettersSettersJogador_Valid()
    {
        IBandeira bandeira = new Bandeira(0, "Bandeira", "Stairs", new Coordenada(0.0, 0.0));
        IJogador jogador = new Jogador("Jogador 1", 0, bandeira);

        assertEquals("Jogador 1", jogador.getNome()); //se o nome == "Jogador 1"
        jogador.setNome("Jogador 2");
        assertEquals("Jogador 2", jogador.getNome()); //se o nome == "Jogador 2"

        assertEquals(0, jogador.getNumeroBots()); //se o bots == 0
        jogador.setNumeroBots(1);
        assertEquals(1, jogador.getNumeroBots()); //se o bots == 1

        assertEquals(bandeira, jogador.getBandeira());
        IBandeira bandeira1 = new Bandeira(0, "Bandeira", "CT Stairs", new Coordenada(0.0, 0.0));
        jogador.setBandeira(bandeira1);
        assertEquals(bandeira1, jogador.getBandeira());
    }
}
