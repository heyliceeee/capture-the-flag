import org.example.collections.implementation.Graph;
import org.example.collections.interfaces.GraphADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

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
        assertEquals(0, grafo.size()); //o tamanho do grafo tem que ser == 0
        assertTrue(grafo.isEmpty()); //retorna true se for um grafo vazio
    }


    @Test
    @DisplayName("Adicionar um novo vertice no grafo")
    public void testAdicionarVerticeGrafo_Valid()
    {
        assertEquals(0, grafo.size()); //o tamanho do grafo tem que ser == 0

        assertTrue(grafo.addVertex(1)); //retorna true se consegue adicionar vertice 1
        assertEquals(1, grafo.size()); //o tamanho do grafo tem que ser 1

        assertTrue(grafo.addVertex(2)); //retorna true se consegue adicionar vertice 2
        assertEquals(2, grafo.size()); //o tamanho do grafo tem que ser 2

        assertTrue(grafo.addVertex(3)); //retorna true se consegue adicionar vertice 3
        assertEquals(3, grafo.size()); //o tamanho do grafo tem que ser 3

        assertTrue(grafo.addVertex(4)); //retorna true se consegue adicionar vertice 4
        assertEquals(4, grafo.size()); //o tamanho do grafo tem que ser 4

        assertFalse(grafo.isEmpty()); //retorna false porque o grafo nao esta vazio

        assertThrows(IllegalArgumentException.class, () -> grafo.addVertex(null)); //retorna IllegalArgumentException porque o grafo nao consegue adicionar nodes nulos
    }


    @Test
    @DisplayName("Remover um vertice no grafo")
    public void testRemoverVerticeGrafo_Valid()
    {
        assertTrue(grafo.addVertex(1)); //retorna true se consegue adicionar vertice 1
        assertTrue(grafo.addVertex(2)); //retorna true se consegue adicionar vertice 2
        assertTrue(grafo.addVertex(3)); //retorna true se consegue adicionar vertice 3
        assertEquals(3, grafo.size()); //o tamanho do grafo tem que ser 3

        assertThrows(NoSuchElementException.class, () -> grafo.removeVertex(null)); //retorna NoSuchElementException porque o grafo nao consegue remover nodes nulos

        grafo.removeVertex(1); //remove vertice 1
        assertEquals(2, grafo.size()); //o tamanho do grafo tem que ser 2

        grafo.removeVertex(2); //remove vertice 2
        assertEquals(1, grafo.size()); //o tamanho do grafo tem que ser 1

        grafo.removeVertex(3); //remove vertice 3
        assertEquals(0, grafo.size()); //o tamanho do grafo tem que ser 0

        assertTrue(grafo.addVertex(1)); //retorna true se consegue adicionar vertice 1
        assertEquals(1, grafo.size()); //o tamanho do grafo tem que ser 1

        grafo.removeVertex(1); //remove vertice 1
        assertEquals(0, grafo.size()); //o tamanho do grafo tem que ser 0
    }
}
