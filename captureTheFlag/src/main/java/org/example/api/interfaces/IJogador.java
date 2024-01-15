package org.example.api.interfaces;

import org.example.api.implementation.Coordenada;
import org.json.simple.JSONObject;

/**
 * Interface do jogador
 */
public interface IJogador
{
    /**
     * retorna o numero de bots
     * @return o numero de bots
     */
    int getNumeroBots();


    /**
     * define o numero de bots
     * @param bots
     * @return o numero de bots
     */
    void setNumeroBots(int bots);


    /**
     * retorna o nome do jogador
     * @return o nome do jogador
     */
    String getNome();


    /**
     * define o nome do jogador
     * @param nome
     */
    void setNome(String nome);


    /**
     * retorna as coordenadas do jogador
     * @return as coordenadas do jogador
     */
    Coordenada getCoordenadas();


    /**
     * define as coordenadas do jogador
     * @param coordenadas
     */
    void setCoordenadas(Coordenada coordenadas);


    /**
     * retorna o numero de bandeiras conquistados pelo jogador
     * @return o numero de bandeiras conquistados pelo jogador
     */
    int getBandeirasConquistadas();


    /**
     * define o numero de bandeiras conquistados pelo jogador
     * @param bandeirasConquistadas
     */
    void setBandeirasConquistadas(int bandeirasConquistadas);


    /**
     * retorna o objeto JSON do jogador
     * @return
     */
    JSONObject jogadorParaObjetoJson();
}
