package org.example.api.implementation;

import org.example.api.interfaces.IBandeira;
import org.example.api.interfaces.IBot;
import org.example.api.interfaces.ICoordenada;
import org.example.api.interfaces.IJogador;
import org.example.collections.implementation.ArrayOrderedList;
import org.json.simple.JSONObject;


public class Jogador implements IJogador
{
    /**
     * nome do jogador
     */
    private String nome;


    /**
     * numero de bots do jogador
     */
    private int bots;


    /**
     * bots do jogador
     */
    private ArrayOrderedList<IBot> botsJogador;


    /**
     * bandeira do jogador
     */
    private IBandeira bandeira;



    /**
     * total de bandeiras conquistados pelo jogador
     */
    private int bandeirasConquistadas;


    /**
     * construtor
     * @param nome
     * @param bots
     * @param bandeirasConquistadas
     */
    public Jogador(String nome, int bots, int bandeirasConquistadas)
    {
        this.nome = nome;
        this.bots = bots;
        this.bandeirasConquistadas = bandeirasConquistadas;
        this.botsJogador = new ArrayOrderedList<>();
    }


    /**
     * retorna os bots do jogador
     *
     * @return os bots do jogador
     */
    @Override
    public ArrayOrderedList<IBot> getBotsJogador()
    {
        return botsJogador;
    }

    /**
     * retorna o numero de bots
     *
     * @return o numero de bots
     */
    @Override
    public int getNumeroBots()
    {
        return bots;
    }


    /**
     * define o numero de bots
     *
     * @param bots
     * @return o numero de bots
     */
    @Override
    public void setNumeroBots(int bots)
    {
        this.bots = bots;
    }


    /**
     * retorna o nome do jogador
     *
     * @return o nome do jogador
     */
    @Override
    public String getNome()
    {
        return nome;
    }


    /**
     * define o nome do jogador
     *
     * @param nome
     */
    @Override
    public void setNome(String nome)
    {
        this.nome = nome;
    }


    /**
     * retorna a bandeira do jogador
     *
     * @return a bandeira do jogador
     */
    @Override
    public IBandeira getBandeira()
    {
        return bandeira;
    }


    /**
     * define a bandeira do jogador
     *
     * @param bandeira
     */
    @Override
    public void setBandeira(IBandeira bandeira)
    {
        this.bandeira = bandeira;
    }


    /**
     * retorna o numero de bandeiras conquistados pelo jogador
     *
     * @return o numero de bandeiras conquistados pelo jogador
     */
    @Override
    public int getBandeirasConquistadas()
    {
        return bandeirasConquistadas;
    }


    /**
     * define o numero de bandeiras conquistados pelo jogador
     *
     * @param bandeirasConquistadas
     */
    @Override
    public void setBandeirasConquistadas(int bandeirasConquistadas)
    {
        this.bandeirasConquistadas = bandeirasConquistadas;
    }


    /**
     * retorna o objeto JSON do jogador
     *
     * @return
     */
    @Override
    public JSONObject jogadorParaObjetoJson()
    {
        JSONObject raiz = new JSONObject();

        raiz.put("nome", this.nome);
        raiz.put("numeroBots", this.bots);
        raiz.put("bandeirasConquistadas", this.bandeirasConquistadas);

        return raiz;
    }


    @Override
    public String toString() {
        return "Jogador{" +
                "nome='" + nome + '\'' +
                ", bots=" + bots +
                ", bandeira=" + bandeira +
                ", bandeirasConquistadas=" + bandeirasConquistadas +
                '}';
    }
}
