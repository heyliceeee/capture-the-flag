package org.example.api.implementation;

import org.example.api.interfaces.IBot;
import org.example.api.interfaces.ICoordenada;
import org.json.simple.JSONObject;

public class Bot implements IBot
{
    /**
     * nome do bot
     */
    private String nome;


    /**
     * nome jogador associado ao bot
     */
    private String nomeJogador;


    /**
     * coordenadas do bot
     */
    private ICoordenada coordenadas;


    /**
     * algortimo de movimento do bot
     */
    private String algoritmoMovimento;



    /**
     * retorna o nome do bot
     *
     * @return o nome do bot
     */
    @Override
    public String getNome()
    {
        return nome;
    }


    /**
     * define o nome do bot
     *
     * @param nome
     */
    @Override
    public void setNome(String nome)
    {
        this.nome = nome;
    }


    /**
     * retorna o nome do jogador associado
     *
     * @return o nome do jogador associado
     */
    @Override
    public String getNomeJogador()
    {
        return nomeJogador;
    }


    /**
     * define o nome do jogador associado
     *
     * @param nomeJogador
     */
    @Override
    public void setNomeJogador(String nomeJogador)
    {
        this.nomeJogador = nomeJogador;
    }


    /**
     * retorna as coordenadas do bot
     *
     * @return as coordenadas do bot
     */
    @Override
    public Coordenada getCoordenadas()
    {
        return (Coordenada) coordenadas;
    }


    /**
     * define as coordenadas do bot
     *
     * @param coordenadas
     */
    @Override
    public void setCoordenadas(Coordenada coordenadas)
    {
        this.coordenadas = coordenadas;
    }


    /**
     * retorna o algoritmo de movimento do bot
     *
     * @return o algoritmo de movimento do bot
     */
    @Override
    public String getAlgoritmoMovimento()
    {
        return algoritmoMovimento;
    }


    /**
     * define o algoritmo de movimento do bot
     *
     * @param algoritmoMovimento algoritmo de movimento do bot
     */
    @Override
    public void setAlgoritmoMovimento(String algoritmoMovimento)
    {
        this.algoritmoMovimento = algoritmoMovimento;
    }


    /**
     * retorna o objeto JSON do bot
     *
     * @return o objeto JSON do bot
     */
    @Override
    public JSONObject botParaObjetoJson()
    {
        JSONObject raiz = new JSONObject();

        raiz.put("nome", this.nome);
        raiz.put("nomeJogador", this.nomeJogador);
        raiz.put("algoritmoMovimento", this.algoritmoMovimento);
        raiz.put("coordenadas", getCoordenadasObjetoJSON());

        return raiz;
    }

    private Object getCoordenadasObjetoJSON()
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
        return "Bot{" +
                "nome='" + nome + '\'' +
                ", nomeJogador='" + nomeJogador + '\'' +
                ", coordenadas=" + coordenadas +
                ", algoritmoMovimento='" + algoritmoMovimento + '\'' +
                '}';
    }
}
