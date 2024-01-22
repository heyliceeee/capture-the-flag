import org.example.collections.implementation.Graph;
import org.example.collections.interfaces.GraphADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testGrafo
{
    GraphADT<Integer> grafo = new Graph<>();

    @BeforeEach
    public void beforeEach()
    {
        grafo = new Graph<>();
    }


    @Test
    @DisplayName("Verificar se o grafo esta vazio")
    public void testVerificarGrafoVazio_Valid()
    {
        assertEquals(0, grafo.size()); //se o tamanho dos nodes == 0
        assertTrue(grafo.isEmpty()); //retorna true se for um grafo vazio
    }


    @Test
    @DisplayName("Adicionar um novo vertice no grafo")
    public void testAdicionarVerticeGrafo_Valid()
    {
        assertEquals(0, grafo.size());

        assertTrue(grafo.addVertex(1));
        assertEquals(1, grafo.size());

        assertTrue(grafo.addVertex(2));
        assertEquals(2, grafo.size());

        assertTrue(grafo.addVertex(3));
        assertEquals(3, grafo.size());

        assertTrue(grafo.addVertex(4));
        assertEquals(4, grafo.size());

        assertFalse(grafo.isEmpty());

        assertThrows(IllegalArgumentException.class, () -> grafo.addVertex(null));
    }
}
