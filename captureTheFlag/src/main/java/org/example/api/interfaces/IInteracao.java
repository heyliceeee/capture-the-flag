package org.example.api.interfaces;

import org.json.simple.JSONObject;

/**
 * Interface da Interacao
 */
public interface IInteracao<T>
{
    /**
     * retorna identificador único da interação
     *
     * @return identificador único da interação
     */
    int getID();

    /**
     * define identificador único da interação
     *
     * @param id
     */
    void setID(int id);

    /**
     * retorna o tipo de interacao do localizacao/bandeira
     *
     * @return o tipo de interacao do localizacao/bandeira
     */
    String getTipo();

    /**
     * define o tipo de interacao do localizacao/bandeira
     *
     * @param tipo
     */
    void setTipo(String tipo);

    /**
     * retornar o nome do jogador que teve interacao com o localizacao/bandeira
     *
     * @return o nome do jogador que teve interacao com o localizacao/bandeira
     */
    String getJogador();

    /**
     * define o nome do jogador que teve interacao com o localizacao/bandeira
     *
     * @param jogador
     */
    void setJogador(String jogador);

    /**
     * retornar o nome do bot que teve interacao com o localizacao/bandeira
     *
     * @return o nome do bot que teve interacao com o localizacao/bandeira
     */
    String getBot();

    /**
     * define o nome do bot que teve interacao com o localizacao/bandeira
     *
     * @param bot
     */
    void setBot(String bot);

    /**
     * retorna os pontos de uma interacao com o localizacao/bandeira
     *
     * @return os pontos de uma interacao com o localizacao/bandeira
     */
    int getPontos();

    /**
     * define os pontos de uma interacao com o localizacao/bandeira
     *
     * @param pontos
     */
    void setPontos(int pontos);


    /**
     * interacao em objeto JSON
     * @return objeto JSON
     */
    JSONObject interacaoParaObjetoJson();
}
