package org.example.api.interfaces;


import org.example.api.implementation.Coordenada;

/**
 * Interface da bandeira/localizacao
 */
public interface ILocal
{

    /**
     * retorna o identificador unico da bandeira/localizacao
     * @return o identificador unico da bandeira/localizacao
     */
    int getId();

    /**
     * define o identificador unico da bandeira/localizacao
     * @param id o identificador unico da bandeira/localizacao
     */
    void setId(int id);


    /**
     * retorna o tipo de local (bandeira/localizacao)
     * @return o tipo de local (bandeira/localizacao)
     */
    String getTipo();

    /**
     * define o tipo de local (bandeira/localizacao)
     * @param tipo o tipo de local (bandeira/localizacao)
     */
    void setTipo(String tipo);


    /**
     * retorna as coordenadas do (bandeira/localizacao)
     * @return as coordenadas do (bandeira/localizacao)
     */
    public Coordenada getCoordenadas();

    /**
     * define as coordenadas do (bandeira/localizacao)
     * @param coordenadas as coordenadas do (bandeira/localizacao)
     */
    public void setCoordenadas(Coordenada coordenadas);
}
