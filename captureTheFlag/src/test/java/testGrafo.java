import org.example.collections.exceptions.EmptyCollectionException;
import org.example.collections.implementation.Graph;
import org.example.collections.interfaces.GraphADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
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
    @DisplayName("Adicionar um vertice no grafo")
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


    @Test
    @DisplayName("Adicionar uma aresta ao grafo")
    public void testAdicionarArestaGrafo_Valid()
    {
        assertTrue(grafo.addVertex(1)); //retorna true se consegue adicionar vertice 1
        assertTrue(grafo.addVertex(2)); //retorna true se consegue adicionar vertice 2
        assertTrue(grafo.addVertex(3)); //retorna true se consegue adicionar vertice 3

        assertEquals(3, grafo.size()); //o tamanho do grafo tem que ser 3

        assertThrows(IllegalArgumentException.class, () -> grafo.addEdge(null, null)); //retorna IllegalArgumentException porque o grafo nao consegue adicionar aresta com 2 nodes nulos
        assertThrows(IllegalArgumentException.class, () -> grafo.addEdge(null, 1)); //retorna IllegalArgumentException porque o grafo nao consegue adicionar aresta com 1 node nulo
        assertThrows(IllegalArgumentException.class, () -> grafo.addEdge(1, null)); //retorna IllegalArgumentException porque o grafo nao consegue adicionar aresta com 1 node nulo

        assertDoesNotThrow(() -> grafo.addEdge(1, 1)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(1, 2)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(1, 3)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(2, 1)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(2, 2)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(2, 3)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(3, 1)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(3, 2)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(3, 3)); //nao retorna nada porque foi adicionado uma aresta com sucesso

        assertEquals(3, grafo.size()); //o tamanho do grafo tem que ser 3
    }


    @Test
    @DisplayName("Remover uma aresta ao grafo")
    public void testRemoverArestaGrafo_Valid()
    {
        assertTrue(grafo.addVertex(1)); //retorna true se consegue adicionar vertice 1
        assertTrue(grafo.addVertex(2)); //retorna true se consegue adicionar vertice 2
        assertTrue(grafo.addVertex(3)); //retorna true se consegue adicionar vertice 3

        assertEquals(3, grafo.size()); //o tamanho do grafo tem que ser 3

        assertThrows(IllegalArgumentException.class, () -> grafo.removeEdge(1, 2)); //retorna IllegalArgumentException porque o grafo nao consegue remover aresta que nao foi criada
        assertThrows(IllegalArgumentException.class, () -> grafo.removeEdge(2, 3)); //retorna IllegalArgumentException porque o grafo nao consegue remover aresta que nao foi criada
        assertThrows(IllegalArgumentException.class, () -> grafo.removeEdge(3, 1)); //retorna IllegalArgumentException porque o grafo nao consegue remover aresta que nao foi criada

        assertDoesNotThrow(() -> grafo.addEdge(1, 2)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(2, 3)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(3, 1)); //nao retorna nada porque foi adicionado uma aresta com sucesso

        assertDoesNotThrow(() -> grafo.removeEdge(1, 2)); //nao retorna nada porque foi removido uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.removeEdge(2, 3)); //nao retorna nada porque foi removid uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.removeEdge(3, 1)); //nao retorna nada porque foi removido uma aresta com sucesso

        assertEquals(3, grafo.size()); //o tamanho do grafo tem que ser 3
    }


    @Test
    @DisplayName("Verificar se a aresta esta conectada ao grafo")
    public void testVerificarConexaoVertice1Grafo_Valid()
    {
        assertFalse(grafo.isConnected()); //retorna false porque o grafo é vazio

        assertTrue(grafo.addVertex(1)); //retorna true se consegue adicionar vertice 1
        assertTrue(grafo.isConnected()); //retorna true se o grafo nao tem nem precisa de aresta para conetar o vertice 1

        assertTrue(grafo.addVertex(2)); //retorna true se consegue adicionar vertice 2
        assertFalse(grafo.isConnected()); //retorna false se o grafo tem uma aresta a conetar os vertices 1 e 2

        assertTrue(grafo.addVertex(3)); //retorna true se consegue adicionar vertice 3
        assertFalse(grafo.isConnected()); //retorna false se o grafo tem uma aresta a conetar os vertices 1 e 2 e 3

        assertDoesNotThrow(() -> grafo.addEdge(1, 2)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertFalse(grafo.isConnected()); //retorna false se o grafo tem uma aresta a conetar os vertices 1 e 2 e 3

        assertDoesNotThrow(() -> grafo.addEdge(2, 3)); //nao retorna nada porque foi removido uma aresta com sucesso
        assertTrue(grafo.isConnected()); //retorna true se o grafo tem uma aresta a conetar os vertices 1 e 2 e 3
    }


    @Test
    @DisplayName("BFS")
    public void testBFS_Valid()
    {
        assertTrue(grafo.addVertex(1)); //retorna true se consegue adicionar vertice 1
        assertTrue(grafo.addVertex(2)); //retorna true se consegue adicionar vertice 2
        assertTrue(grafo.addVertex(3)); //retorna true se consegue adicionar vertice 3
        assertTrue(grafo.addVertex(4)); //retorna true se consegue adicionar vertice 4
        assertTrue(grafo.addVertex(5)); //retorna true se consegue adicionar vertice 5
        assertTrue(grafo.addVertex(6)); //retorna true se consegue adicionar vertice 6

        assertDoesNotThrow(() -> grafo.addEdge(1, 1)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(1, 2)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(2, 2)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(2, 3)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(2, 4)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(2, 6)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(3, 3)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(4, 5)); //nao retorna nada porque foi adicionado uma aresta com sucesso

        assertThrows(IllegalArgumentException.class, () -> grafo.iteratorBFS(-222)); //retorna IllegalArgumentException porque o grafo nao consegue iterar com um vertice negativo

        Iterator<Integer> iterator = grafo.iteratorBFS(1);
        assertDoesNotThrow(() -> iterator); //nao retorna erro porque foi iterado com um vertice valido

        int count = 0;
        while (iterator.hasNext())
        {
            Integer temp = iterator.next();
            count++;
        }

        assertEquals(6, count); //se o count == 6
    }



    @Test
    @DisplayName("DFS")
    public void testDFS_Valid()
    {
        assertTrue(grafo.addVertex(1)); //retorna true se consegue adicionar vertice 1
        assertTrue(grafo.addVertex(2)); //retorna true se consegue adicionar vertice 2
        assertTrue(grafo.addVertex(3)); //retorna true se consegue adicionar vertice 3
        assertTrue(grafo.addVertex(4)); //retorna true se consegue adicionar vertice 4
        assertTrue(grafo.addVertex(5)); //retorna true se consegue adicionar vertice 5
        assertTrue(grafo.addVertex(6)); //retorna true se consegue adicionar vertice 6

        assertDoesNotThrow(() -> grafo.addEdge(1, 1)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(1, 2)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(2, 2)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(2, 3)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(2, 4)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(2, 6)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(3, 3)); //nao retorna nada porque foi adicionado uma aresta com sucesso
        assertDoesNotThrow(() -> grafo.addEdge(4, 5)); //nao retorna nada porque foi adicionado uma aresta com sucesso

        assertThrows(IllegalArgumentException.class, () -> grafo.iteratorDFS(-222)); //retorna IllegalArgumentException porque o grafo nao consegue iterar com um vertice negativo

        Iterator<Integer> iterator = grafo.iteratorDFS(1);
        assertDoesNotThrow(() -> iterator); //nao retorna erro porque foi iterado com um vertice valido

        int count = 0;
        while (iterator.hasNext())
        {
            Integer temp = iterator.next();
            count++;
        }

        assertEquals(6, count); //se o count == 6
    }
}
