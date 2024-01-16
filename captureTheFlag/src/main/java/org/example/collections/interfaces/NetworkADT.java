package org.example.collections.interfaces;

import org.example.collections.exceptions.UnknownPathException;
import org.example.collections.implementation.ArrayUnorderedList;

public interface NetworkADT<T> extends GraphADT<T>
{
    /**
     * adiciona uma aresta entre 2 vertices do grafo
     * @param vertex1 primeira aresta
     * @param vertex2 segunda aresta
     * @param weight o peso/custo da aresta
     * @param tipoCaminho bidirecional ou direcional
     */
    public void addEdge(T vertex1, T vertex2, double weight, String tipoCaminho);


    /**
     * retorna o peso/custo do caminho mais curto da rede
     *
     * @param vertex1 primeira aresta
     * @param vertex2 segunda aresta
     * @return o peso/custo do caminho mais curto da rede
     */
    public ArrayUnorderedList<T> shortestPathWeight(T vertex1, T vertex2) throws UnknownPathException;
}
