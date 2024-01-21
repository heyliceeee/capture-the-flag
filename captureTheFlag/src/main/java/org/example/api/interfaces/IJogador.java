package org.example.api.interfaces;

import org.example.api.implementation.Coordenada;
import org.example.collections.implementation.ArrayOrderedList;
import org.json.simple.JSONObject;

/**
 * Interface do jogador
 */
public interface IJogador
{
    /**
     * retorna os bots do jogador
     * @return os bots do jogador
     */
    ArrayOrderedList<IBot> getBotsJogador();


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
     * retorna a bandeira do jogador
     * @return a bandeira do jogador
     */
    IBandeira getBandeira();


    /**
     * define a bandeira do jogador
     * @param bandeira
     */
    void setBandeira(IBandeira bandeira);


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
