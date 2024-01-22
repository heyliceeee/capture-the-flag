import org.example.api.implementation.Bot;
import org.example.api.implementation.Coordenada;
import org.example.api.implementation.Jogador;
import org.example.api.interfaces.IBot;
import org.example.api.interfaces.IJogador;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testBot
{
    @DisplayName("Construtor correto do Bot")
    @Test
    public void testConstrutorCorretoBot_Valid()
    {
        assertDoesNotThrow(() -> { IBot bot = new Bot("Bot 1 do Jogador 1", "Jogador 1", new Coordenada(0.0, 0.0), "BFS"); }); //nao retorna nada porque foi criado contrutor com sucesso

        IBot bot = new Bot("Bot 1 do Jogador 1", "Jogador 1", new Coordenada(0.0, 0.0), "BFS");

        assertEquals("Bot 1 do Jogador 1", bot.getNome()); //se o nome == "Bot 1 do Jogador 1"
        assertEquals("Jogador 1", bot.getNomeJogador()); //se o nomeJogador == "Jogador 1"

        assertEquals(0.0, bot.getCoordenada().getLatitude()); //se o coordenada.latitude == 0.0
        assertEquals(0.0, bot.getCoordenada().getLongitude()); //se o coordenada.longitude == 0.0

        assertEquals("BFS", bot.getAlgoritmoMovimento()); //se o algoritmoMovimento == "BFS"
    }


    @DisplayName("Construtor incorreto do Bot")
    @Test
    public void testConstrutorIncorretoBot_Valid()
    {
        assertThrows(IllegalArgumentException.class, () -> { IBot bot = new Bot(null, null, null, null);  }); //retorna erro porque foi criado contrutor sem sucesso
    }


    @DisplayName("Getters e Setters da Bot")
    @Test
    public void testGettersSettersBot_Valid()
    {
        IBot bot = new Bot("Bot 1 do Jogador 1", "Jogador 1", new Coordenada(0.0, 0.0), "BFS");

        assertEquals("Bot 1 do Jogador 1", bot.getNome()); //se o nome == "Bot 1 do Jogador 1"
        bot.setNome("Bot 2 do Jogador 2");
        assertEquals("Bot 2 do Jogador 2", bot.getNome()); //se o nome == "Bot 2 do Jogador 2"

        assertEquals("Jogador 1", bot.getNomeJogador()); //se o nome == "Jogador 1"
        bot.setNomeJogador("Jogador 2");
        assertEquals("Jogador 2", bot.getNomeJogador()); //se o nome == "Jogador 2"

        assertEquals(0.0, bot.getCoordenada().getLatitude()); //se o coordenada.latitude == 0.0
        assertEquals(0.0, bot.getCoordenada().getLongitude()); //se o coordenada.longitude == 0.0
        bot.setCoordenada(new Coordenada(1.0, 1.0));
        assertEquals(1.0, bot.getCoordenada().getLatitude()); //se o coordenada.latitude == 1.0
        assertEquals(1.0, bot.getCoordenada().getLongitude()); //se o coordenada.longitude == 1.0

        assertEquals("BFS", bot.getAlgoritmoMovimento()); //se o algoritmoMovimento == "BFS"
        bot.setAlgoritmoMovimento("DFS");
        assertEquals("DFS", bot.getAlgoritmoMovimento()); //se o nome == "DFS"
    }
}
