package org.example.api.implementation;

import org.example.api.interfaces.ICoordenada;
import org.example.api.interfaces.IJogador;
import org.json.simple.JSONObject;


public class Jogador implements IJogador
{
    /**
     * nome do jogador
     */
    private String nome;


    /**
     * numero de bots do jogador
     */
    private int bots;


    /**
     * coordenadas do jogador
     */
    private ICoordenada coordenadas;


    /**
     * total de bandeiras conquistados pelo jogador
     */
    private int bandeirasConquistadas;


    /**
     * construtor
     * @param nome
     * @param bots
     * @param coordenadas
     * @param bandeirasConquistadas
     */
    public Jogador(String nome, int bots, ICoordenada coordenadas, int bandeirasConquistadas)
    {
        this.nome = nome;
        this.bots = bots;
        this.coordenadas = coordenadas;
        this.bandeirasConquistadas = bandeirasConquistadas;
    }


    /**
     * retorna o numero de bots
     *
     * @return o numero de bots
     */
    @Override
    public int getNumeroBots()
    {
        return bots;
    }


    /**
     * define o numero de bots
     *
     * @param bots
     * @return o numero de bots
     */
    @Override
    public void setNumeroBots(int bots)
    {
        this.bots = bots;
    }


    /**
     * retorna o nome do jogador
     *
     * @return o nome do jogador
     */
    @Override
    public String getNome()
    {
        return nome;
    }


    /**
     * define o nome do jogador
     *
     * @param nome
     */
    @Override
    public void setNome(String nome)
    {
        this.nome = nome;
    }


    /**
     * retorna as coordenadas do jogador
     *
     * @return as coordenadas do jogador
     */
    @Override
    public Coordenada getCoordenadas()
    {
        return (Coordenada) coordenadas;
    }


    /**
     * define as coordenadas do jogador
     *
     * @param coordenadas
     */
    @Override
    public void setCoordenadas(Coordenada coordenadas)
    {
        this.coordenadas = coordenadas;
    }


    /**
     * retorna o numero de bandeiras conquistados pelo jogador
     *
     * @return o numero de bandeiras conquistados pelo jogador
     */
    @Override
    public int getBandeirasConquistadas()
    {
        return bandeirasConquistadas;
    }


    /**
     * define o numero de bandeiras conquistados pelo jogador
     *
     * @param bandeirasConquistadas
     */
    @Override
    public void setBandeirasConquistadas(int bandeirasConquistadas)
    {
        this.bandeirasConquistadas = bandeirasConquistadas;
    }


    /**
     * retorna o objeto JSON do jogador
     *
     * @return
     */
    @Override
    public JSONObject jogadorParaObjetoJson()
    {
        JSONObject coordenadas = new JSONObject();

        try
        {
            coordenadas.put("longitude", this.coordenadas.getLongitude());

        } catch (Exception e)
        {
            coordenadas.put("longitude", 0);
        }

        try
        {
            coordenadas.put("latitude", this.coordenadas.getLatitude());

        } catch (Exception e)
        {
            coordenadas.put("latitude", 0);
        }

        return coordenadas;
    }


    @Override
    public String toString() {
        return "Jogador{" +
                "nome='" + nome + '\'' +
                ", bots=" + bots +
                ", coordenadas=" + coordenadas +
                ", bandeirasConquistadas=" + bandeirasConquistadas +
                '}';
    }
}
