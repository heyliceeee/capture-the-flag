package org.example.collections.implementation;

/**
 * Classe do par
 * @param <T>
 */
public class Pair<T>
{
    /**
     * anterior do par
     */
    protected Pair<T> previous;

    /**
     * vértice do par
     */
    protected T vertex;

    /**
     * custo do par
     */
    protected double cost;

    /**
     * constructor
     * @param previous anterior
     * @param vertex vértice
     * @param cost custo
     */
    public Pair(Pair<T> previous, T vertex, double cost)
    {
        this.previous = previous;
        this.vertex = vertex;
        this.cost = cost;
    }

    /**
     * Retorna o par anterior
     * @return o par anterior
     */
    public Pair<T> getPrevious()
    {
        return previous;
    }

    /**
     * Define o par anterior
     * @param previous
     */
    public void setPrevious(Pair<T> previous)
    {
        this.previous = previous;
    }

    /**
     * Retorna o vértice do par
     * @return o vértice do par
     */
    public T getVertex()
    {
        return vertex;
    }

    /**
     * Define o vértice do par
     * @param vertex
     */
    public void setVertex(T vertex)
    {
        this.vertex = vertex;
    }

    /**
     * Retorna o custo do par
     * @return o custo do par
     */
    public double getCost()
    {
        return cost;
    }

    /**
     * Define o custo do par
     * @param cost
     */
    public void setCost(double cost)
    {
        this.cost = cost;
    }
}
