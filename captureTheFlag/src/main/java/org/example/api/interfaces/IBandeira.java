package org.example.api.interfaces;

import org.json.simple.JSONObject;

import java.io.IOException;

public interface IBandeira extends ILocal
{
    /**
     * retorna o nome da bandeira
     * @return o nome da bandeira
     */
    String getNome();

    /**
     * define o nome da bandeira
     * @param nome
     */
    void setNome(String nome);


    /**
     * define o tipo de interação com a bandeira
     * @param id identificador único da interação
     * @param type novo tipo de interação
     */
    void setTipoInteracao(int id, String type);


    /**
     * define o nome do bot do jogador de interação com a bandeira
     * @param id identificador único da interação
     * @param nomeBot novo nome do bot do jogador de interação
     */
    void setInteracaoBot(int id, String nomeBot);


    /**
     * define os pontos de interação com a bandeira
     * @param id identificador único da interação
     * @param pontos os pontos de interação
     */
    void setPontosInteracao(int id, int pontos);


    /**
     * exportar as interacoes para JSON
     * @throws IOException
     */
    void exportInteracoesParaJson() throws IOException;


    /**
     * converter a bandeira para objeto JSON
     * @return o objeto JSON
     */
    JSONObject bandeiraParaObjetoJSON();
}
