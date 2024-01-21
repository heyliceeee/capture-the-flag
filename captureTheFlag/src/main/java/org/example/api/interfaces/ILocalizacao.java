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
     * converter a localizacao para objeto JSON
     * @return o objeto JSON
     */
    JSONObject localizacaoParaObjetoJSON();
}
