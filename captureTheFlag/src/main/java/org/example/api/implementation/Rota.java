package org.example.api.implementation;

import org.example.api.interfaces.IRota;
import org.json.simple.JSONObject;

/**
 * Classe que representa a rota entre localizacoes e/ou bandeiras
 * @param <T>
 */
public class Rota<T> implements IRota<T>
{
    /**
     * o localizacao/bandeira de partida da rota
     */
    private T de;

    /**
     * o localizacao/bandeira de chegada da rota
     */
    private T para;

    /**
     * o peso entre os dois localizacoes e/ou bandeiras
     */
    private double peso;


    /**
     * construtor
     * @param de localizacao/bandeira de partida
     * @param para localizacao/bandeira de chegada
     * @param peso o peso entre os 2 localizacoes e/ou bandeiras
     */
    public Rota(T de, T para, double peso)
    {
        this.de = de;
        this.para = para;
        this.peso = peso;
    }




    /**
     * retorna o localizacao/bandeira de partida da rota
     *
     * @return o o localizacao/bandeira de partida da rota
     */
    @Override
    public T getDe()
    {
        return de;
    }

    /**
     * retorna o localizacao/bandeira de chegada da rota
     *
     * @return o localizacao/bandeira de chegada da rota
     */
    @Override
    public T getPara()
    {
        return para;
    }

    /**
     * retorna o peso entre o localizacao/bandeira de partida e o localizacao/bandeira de chegada
     *
     * @return o peso
     */
    @Override
    public double getPeso()
    {
        return peso;
    }

    /**
     * define a peso entre 2 pontos
     *
     * @param peso
     */
    @Override
    public void setPeso(int peso)
    {
        this.peso = peso;
    }

    /**
     * calcula o peso entre os 2 pontos apartir das coordenadas dos 2 pontos
     * peso = raizQuadrada((x2-x1)^2 + (y2-y1)^2)
     *
     * @param x1 coordenadas do ponto de partida
     * @param y1 coordenadas do ponto de partida
     * @param x2 coordenadas do ponto de chegada
     * @param y2 coordenadas do ponto de chegada
     */
    @Override
    public double calcularPesoDasCoordenadas(double x1, double y1, double x2, double y2)
    {
        return (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }


    @Override
    public String toString() {
        return "Rota{" +
                "de=" + de +
                ", para=" + para +
                ", peso=" + peso +
                '}';
    }
}
