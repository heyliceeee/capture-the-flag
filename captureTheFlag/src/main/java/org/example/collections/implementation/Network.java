package org.example.collections.implementation;

import org.example.collections.exceptions.EmptyCollectionException;
import org.example.collections.exceptions.UnknownPathException;
import org.example.collections.interfaces.NetworkADT;
import org.example.collections.interfaces.UnorderedListADT;

/**
 * classe que implementa o contrato da rede
 * @param <T>
 */
public class Network<T> extends Graph<T> implements NetworkADT<T>
{
    /**
     * capacidade default da rede
     */
    protected final int DEFAULT_CAPACITY = 10;

    /**
     * matriz adjacência
     */
    protected double[][] adjMatrix;


    /**
     * constructor
     */
    public Network()
    {
        super();
        this.adjMatrix = new double[this.DEFAULT_CAPACITY][this.DEFAULT_CAPACITY];
    }



    /**
     * adicionar aresta bidirecional
     * @param vertex1 primeira aresta
     * @param vertex2 segunda aresta
     * @param weight  o peso/custo da aresta
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight, String tipoCaminho) throws EmptyCollectionException
    {
        if (weight < 0.0D)
        {
            throw new EmptyCollectionException("O peso não pode estar abaixo do padrão.");
        }
        else
        {
            super.addEdge(vertex1, vertex2, tipoCaminho);
            this.setEdgeWeight(vertex1, vertex2, weight);
        }
    }


    /**
     * definir o peso da aresta
     * @param vertex1 o primeiro vértice
     * @param vertex2 o segundo vértice
     * @param weight o peso
     */
    private void setEdgeWeight(T vertex1, T vertex2, double weight)
    {
        if (weight < 0.0D)
        {
            throw new IllegalArgumentException("O peso não pode estar abaixo do padrão.");
        }

        int first = this.getIndex(vertex1);
        int second = this.getIndex(vertex2);

        if (vertex2.equals("exterior") || vertex1.equals("exterior") || vertex2.equals("entrada") || vertex1.equals("entrada"))
        {
            this.adjMatrix[first][second] = 0;
            this.adjMatrix[second][first] = 0;
        }
        else
        {
            this.adjMatrix[first][second] = weight;
        }
    }


    /**
     * @param vertex1 primeira aresta
     * @param vertex2 segunda aresta
     * @return
     */
    @Override
    public ArrayUnorderedList<T> shortestPathWeight(T vertex1, T vertex2) throws EmptyCollectionException, UnknownPathException
    {
        PriorityQueue<Pair<T>> priorityQueue = new PriorityQueue<>();
        UnorderedListADT<T> verticesFromPossiblePath = new ArrayUnorderedList<>();
        ArrayUnorderedList<T> result = new ArrayUnorderedList<>();
        Pair<T> startPair = new Pair<>(null, vertex1, 0.0);

        priorityQueue.addElement(startPair, (int) startPair.cost);

        while (!priorityQueue.isEmpty())
        {
            Pair<T> pair = priorityQueue.removeNext();
            T vertex = pair.vertex;
            double minCost = pair.cost;

            if (vertex.equals(vertex2))
            {
                Pair<T> finalPair = pair;

                while (finalPair != null)
                {
                    result.addToFront(finalPair.vertex);
                    finalPair = finalPair.previous;
                }

                return result;
            }

            verticesFromPossiblePath.addToRear(vertex);

            for (int i = 0; i < numVertices; i++)
            {
                if (super.adjMatrix[getIndex(vertex)][i] == 1 && !verticesFromPossiblePath.contains(vertices[i]))
                {
                    double minCostToVertex = minCost + adjMatrix[getIndex(vertex)][i];
                    Pair<T> tmpPair = new Pair<>(pair, vertices[i], minCostToVertex);
                    priorityQueue.addElement(tmpPair, (int) tmpPair.cost);
                }
            }
        }

        throw new UnknownPathException("Caminho nao existe.");
    }


    /**
     * retorna o indice do node com uma distancia pequena
     * @param shortestDistances lista atual de distancias do ponto de partida
     * @param visited lista que tem a informação sobre um node se foi visitado ou não
     * @return indice do node com distancia pequena
     */
    protected int getDistanciaPequenaNode(double[] shortestDistances, boolean[] visited)
    {
        int index = -1;
        double shortestDistance = Double.MAX_VALUE;

        for(int vertexIndex = 0; vertexIndex < super.numVertices; vertexIndex++)
        {
            if(!visited[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) //pega o node próximo
            {
                index = vertexIndex;
                shortestDistance = shortestDistances[vertexIndex];
            }
        }

        return index;
    }
}
