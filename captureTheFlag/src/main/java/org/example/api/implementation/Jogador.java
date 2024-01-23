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
     * construtor
     * @param nome
     * @param bots
     */
    public Jogador(String nome, int bots, IBandeira bandeira)
    {
        if(nome == null || nome.equals(""))
        {
            throw new IllegalArgumentException("Nome nao pode ser vazio ou nulo!");
        }

        this.nome = nome;
        this.bots = bots;
        this.bandeira = bandeira;
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
        raiz.put("bandeira", getBandeiraObjetoJson());

        return raiz;
    }


    /**
     * retorna a bandeira do jogador em objeto JSON
     * @return
     */
    private JSONObject getBandeiraObjetoJson()
    {
        JSONObject bandeira = new JSONObject();


        try
        {
            bandeira.put("id", this.bandeira.getId());
            bandeira.put("nome", this.bandeira.getNome());
            bandeira.put("tipo", this.bandeira.getTipo());
            bandeira.put("coordenada", getCoordenadaObjetoJson());

        }
        catch (Exception e)
        {
            bandeira.put("id", 0);
            bandeira.put("nome", "");
            bandeira.put("tipo", "Bandeira");
            bandeira.put("coordenada", null);
        }


        return bandeira;
    }


    /**
     * retorna a coordenada da bandeira em objeto JSON
     * @return
     */
    private JSONObject getCoordenadaObjetoJson()
    {
        JSONObject coordenada = new JSONObject();

        try
        {
            coordenada.put("longitude", this.bandeira.getCoordenadas().getLongitude());
            coordenada.put("latitude", this.bandeira.getCoordenadas().getLatitude());
        }
        catch (Exception e)
        {
            coordenada.put("longitude", 0);
            coordenada.put("latitude", 0);
        }

        return coordenada;
    }


    @Override
    public String toString() {
        return "Jogador{" +
                "nome='" + nome + '\'' +
                ", bots=" + bots +
                ", bandeira=" + bandeira +
                '}';
    }
}
