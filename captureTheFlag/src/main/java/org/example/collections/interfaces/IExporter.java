package org.example.collections.interfaces;

import org.example.collections.exceptions.EmptyCollectionException;

import java.util.Iterator;

/**
 * Interface do exportar em grafo
 */
public interface IExporter
{
    /**
     * Sets new file name
     * @param fileName
     */
    void setFileName(String fileName);

    /**
     * Retorna a atual localização do ficheiro a ser exportado
     * @return a atual localização do ficheiro a ser exportado
     */
    String getFileName();

    /**
     * Exporta um grafo em .dot e em .png
     * @param graph grafo a ser exportado
     * @param fileName localização do ficheiro
     * @param <T>
     * @throws EmptyCollectionException
     * @throws InterruptedException
     */
    <T> void exportGraph(GraphADT<T> graph, String fileName) throws EmptyCollectionException, InterruptedException;

    /**
     * Exporta um grafo e uma route em .dot e em .png
     * @param graph grafo a ser exportado
     * @param routeIterator route a ser notado no grafo
     * @param fileName localização do ficheiro
     * @param <T>
     * @throws InterruptedException
     * @throws EmptyCollectionException
     */
    <T> void exportRouteGraph(GraphADT<T> graph, Iterator<T> routeIterator, String fileName) throws InterruptedException, EmptyCollectionException;

    /**
     * Exporta uma route descrita em um iterador em .dot e em .png
     * @param routeIterator route a ser desenhada
     * @param fileName localização do ficheiro
     * @param <T>
     * @throws EmptyCollectionException
     * @throws InterruptedException
     */
    <T> void exportRoute(Iterator<T> routeIterator, String fileName) throws EmptyCollectionException, InterruptedException;
}
