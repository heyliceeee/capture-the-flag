package org.example.api.implementation;

import org.example.api.interfaces.IInteracao;
import org.json.simple.JSONObject;

/**
 * Representacao da classe de uma interacao com um localizacao/bandeira
 */
public class Interacao<T> implements IInteracao<T>
{
    /**
     * identificador único da interação
     */
    private int id;

    /**
     * tipo de interacao com o localizacao/bandeira
     */
    private String tipo;

    /**
     * nome do jogador que teve interacao com o localizacao/bandeira
     */
    private String jogador;

    /**
     * nome do bot que teve interacao com o localizacao/bandeira
     */
    private String bot;

    /**
     * pontos de uma interacao com o localizacao/bandeira
     */
    private int pontos;



    /**
     * constructor
     * @param id
     * @param tipo
     * @param jogador
     * @param bot
     * @param pontos
     */
    public Interacao(int id, String tipo, String jogador, String bot, int pontos)
    {
        this.id = id;
        this.tipo = tipo;
        this.jogador = jogador;
        this.bot = bot;
        this.pontos = pontos;
    }



    /**
     * retorna identificador único da interação
     *
     * @return identificador único da interação
     */
    @Override
    public int getID()
    {
        return id;
    }


    /**
     * define identificador único da interação
     *
     * @param id
     */
    @Override
    public void setID(int id)
    {
        this.id = id;
    }


    /**
     * retorna o tipo de interacao do localizacao/bandeira
     *
     * @return o tipo de interacao do localizacao/bandeira
     */
    @Override
    public String getTipo()
    {
        return tipo;
    }


    /**
     * define o tipo de interacao do localizacao/bandeira
     *
     * @param tipo
     */
    @Override
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }


    /**
     * retornar o nome do jogador que teve interacao com o localizacao/bandeira
     *
     * @return o nome do jogador que teve interacao com o localizacao/bandeira
     */
    @Override
    public String getJogador()
    {
        return jogador;
    }


    /**
     * define o nome do jogador que teve interacao com o localizacao/bandeira
     *
     * @param jogador
     */
    @Override
    public void setJogador(String jogador)
    {
        this.jogador = jogador;
    }


    /**
     * retornar o nome do bot que teve interacao com o localizacao/bandeira
     *
     * @return o nome do bot que teve interacao com o localizacao/bandeira
     */
    @Override
    public String getBot()
    {
        return bot;
    }


    /**
     * define o nome do bot que teve interacao com o localizacao/bandeira
     *
     * @param bot
     */
    @Override
    public void setBot(String bot)
    {
        this.bot = bot;
    }


    /**
     * retorna os pontos de uma interacao com o localizacao/bandeira
     *
     * @return os pontos de uma interacao com o localizacao/bandeira
     */
    @Override
    public int getPontos()
    {
        return pontos;
    }


    /**
     * define os pontos de uma interacao com o localizacao/bandeira
     *
     * @param pontos
     */
    @Override
    public void setPontos(int pontos)
    {
        this.pontos = pontos;
    }


    /**
     * interacao em objeto JSON
     *
     * @return objeto JSON
     */
    @Override
    public JSONObject interacaoParaObjetoJson()
    {
        JSONObject raiz = new JSONObject();

        raiz.put("id", this.id);
        raiz.put("tipo", this.tipo);
        raiz.put("jogador", this.jogador);
        raiz.put("bot", this.bot);
        raiz.put("pontos", this.pontos);

        return raiz;
    }


    @Override
    public String toString() {
        return "Interacao{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", jogador='" + jogador + '\'' +
                ", bot='" + bot + '\'' +
                ", pontos=" + pontos +
                '}';
    }
}
