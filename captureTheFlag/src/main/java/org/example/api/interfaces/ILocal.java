package org.example.api.interfaces;


/**
 * Interface da bandeira/localizacao
 */
public interface ILocal
{
    /**
     * retorna a adicionacao da iteracao com a (bandeira/localizacao)
     * @param interacao com a (bandeira/localizacao)
     * @return a adicionacao da iteracao com a (bandeira/localizacao)
     */
    String adicionarInteracao(IInteracao interacao);

    String getListaInteracoes();

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


    /**
     * retorna por ID da (bandeira/localizacao) as interacoes
     * @param id da (bandeira/localizacao)
     * @return por ID da (bandeira/localizacao) as interacoes
     */
    IInteracao getInteracaoPorID(int id);


    /**
     * define o tipo de interacao (passear/capturar)
     * @param id da (bandeira/localizacao)
     * @param tipo (passear/capturar)
     */
    void setTipoInteracao(int id, String tipo);


    /**
     * define o bot do jogador que fez a interacao
     * @param id da (bandeira/localizacao)
     * @param nomeBot bot (jogador1 ou jogador2)
     */
    void setBotInteracao(int id, String nomeBot);


    /**
     * retorna o identificador único da última interação inserida
     * @return o identificador único da última interação
     */
    int getIDUltimaInteracao();
}
