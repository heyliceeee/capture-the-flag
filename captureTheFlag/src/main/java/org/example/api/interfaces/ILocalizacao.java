package org.example.api.interfaces;

import java.io.IOException;
import org.json.simple.JSONObject;


public interface ILocalizacao extends ILocal
{
    /**
     * retorna o nome da localizacao
     * @return o nome da localizacao
     */
    String getNome();

    /**
     * define o nome da localizacao
     * @param nome
     */
    void setNome(String nome);


    /**
     * define o tipo de interação com a localizacao
     * @param id identificador único da interação
     * @param type novo tipo de interação
     */
    void setTipoInteracao(int id, String type);


    /**
     * define o nome do bot do jogador de interação com a localizacao
     * @param id identificador único da interação
     * @param nomeBot novo nome do bot do jogador de interação
     */
    void setInteracaoBot(int id, String nomeBot);


    /**
     * exportar as interacoes para JSON
     * @throws IOException
     */
    void exportInteracoesParaJson() throws IOException;


    /**
     * converter a localizacao para objeto JSON
     * @return o objeto JSON
     */
    JSONObject localizacaoParaObjetoJSON();
}
