package org.example.api.implementation;

import org.example.api.interfaces.IBot;
import org.example.api.interfaces.ICoordenada;
import org.json.simple.JSONObject;

public class Bot implements IBot, Comparable<Bot>
{
    /**
     * nome do bot
     */
    private String nome;


    /**
     * nome jogador associado ao bot
     */
    private String nomeJogador;


    /**
     * coordenadas do bot
     */
    private ICoordenada coordenada;


    /**
     * algortimo de movimento do bot
     */
    private String algoritmoMovimento;


    public Bot(String nome, String nomeJogador, ICoordenada coordenada, String algoritmoMovimento)
    {
        this.nome = nome;
        this.nomeJogador = nomeJogador;
        this.coordenada = coordenada;
        this.algoritmoMovimento = algoritmoMovimento;
    }

    /**
     * retorna o nome do bot
     *
     * @return o nome do bot
     */
    @Override
    public String getNome()
    {
        return nome;
    }


    /**
     * define o nome do bot
     *
     * @param nome
     */
    @Override
    public void setNome(String nome)
    {
        this.nome = nome;
    }


    /**
     * retorna o nome do jogador associado
     *
     * @return o nome do jogador associado
     */
    @Override
    public String getNomeJogador()
    {
        return nomeJogador;
    }


    /**
     * define o nome do jogador associado
     *
     * @param nomeJogador
     */
    @Override
    public void setNomeJogador(String nomeJogador)
    {
        this.nomeJogador = nomeJogador;
    }


    /**
     * retorna as coordenada do bot
     *
     * @return as coordenada do bot
     */
    @Override
    public Coordenada getCoordenada()
    {
        return (Coordenada) coordenada;
    }


    /**
     * define as coordenada do bot
     *
     * @param coordenada
     */
    @Override
    public void setCoordenada(Coordenada coordenada)
    {
        this.coordenada = coordenada;
    }


    /**
     * retorna o algoritmo de movimento do bot
     *
     * @return o algoritmo de movimento do bot
     */
    @Override
    public String getAlgoritmoMovimento()
    {
        return algoritmoMovimento;
    }


    /**
     * define o algoritmo de movimento do bot
     *
     * @param algoritmoMovimento algoritmo de movimento do bot
     */
    @Override
    public void setAlgoritmoMovimento(String algoritmoMovimento)
    {
        this.algoritmoMovimento = algoritmoMovimento;
    }


    /**
     * retorna o objeto JSON do bot
     *
     * @return o objeto JSON do bot
     */
    @Override
    public JSONObject botParaObjetoJson()
    {
        JSONObject raiz = new JSONObject();

        raiz.put("nome", this.nome);
        raiz.put("nomeJogador", this.nomeJogador);
        raiz.put("algoritmoMovimento", this.algoritmoMovimento);
        raiz.put("coordenadas", getCoordenadasObjetoJSON());

        return raiz;
    }

    private Object getCoordenadasObjetoJSON()
    {
        JSONObject coordenadas = new JSONObject();

        try
        {
            coordenadas.put("longitude", this.coordenada.getLongitude());

        } catch (Exception e)
        {
            coordenadas.put("longitude", 0);
        }

        try
        {
            coordenadas.put("latitude", this.coordenada.getLatitude());

        } catch (Exception e)
        {
            coordenadas.put("latitude", 0);
        }

        return coordenadas;
    }


    @Override
    public String toString() {
        return "Bot{" +
                "nome='" + nome + '\'' +
                ", nomeJogador='" + nomeJogador + '\'' +
                ", coordenada=" + coordenada +
                ", algoritmoMovimento='" + algoritmoMovimento + '\'' +
                '}';
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Bot o)
    {
        return this.getNome().compareTo(o.getNome());

    }
}
