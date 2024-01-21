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
     * converter a bandeira para objeto JSON
     * @return o objeto JSON
     */
    JSONObject bandeiraParaObjetoJSON();
}
