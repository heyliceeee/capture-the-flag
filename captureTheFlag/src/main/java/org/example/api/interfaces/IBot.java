package org.example.api.interfaces;

import org.example.api.implementation.Coordenada;
import org.json.simple.JSONObject;

/**
 * Interface do bot
 */
public interface IBot
{
    /**
     * retorna o nome do bot
     * @return o nome do bot
     */
    String getNome();


    /**
     * define o nome do bot
     * @param nome
     */
    void setNome(String nome);


    /**
     * retorna o nome do jogador associado
     * @return o nome do jogador associado
     */
    String getNomeJogador();


    /**
     * define o nome do jogador associado
     * @param nomeJogador
     */
    void setNomeJogador(String nomeJogador);


    /**
     * retorna as coordenada do bot
     * @return as coordenada do bot
     */
    Coordenada getCoordenada();


    /**
     * define as coordenada do bot
     * @param coordenada
     */
    void setCoordenada(Coordenada coordenada);


    /**
     * retorna o algoritmo de movimento do bot
     * @return o algoritmo de movimento do bot
     */
    String getAlgoritmoMovimento();


    /**
     * define o algoritmo de movimento do bot
     * @param algoritmoMovimento algoritmo de movimento do bot
     */
    void setAlgoritmoMovimento(String algoritmoMovimento);


    /**
     * retorna o objeto JSON do bot
     * @return
     */
    JSONObject botParaObjetoJson();
}
