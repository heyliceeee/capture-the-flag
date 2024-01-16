package org.example.api.implementation;

import org.example.api.interfaces.IMapa;

import java.util.Random;

public class Mapa implements IMapa
{
    /**
     * quantidade de localizacoes existentes no mapa
     */
    private int qttLocExistentes;


    /**
     * tipo de caminho no mapa (bidirecional ou direcional)
     */
    private String tipoCaminho;


    /**
     * densidade das arestas do mapa (10% - 100%)
     */
    private int densidadeArestas;


    /**
     * constructor
     * @param qttLocExistentes
     * @param tipoCaminho
     * @param densidadeArestas
     */
    public Mapa(int qttLocExistentes, String tipoCaminho, int densidadeArestas)
    {
        this.qttLocExistentes = qttLocExistentes;
        this.tipoCaminho = tipoCaminho;
        this.densidadeArestas = densidadeArestas;
    }

    public static void gerarMapa(int locExistentesJogador1, int locExistentesJogador2, int tipoCaminhoJogador1, int tipoCaminhoJogador2, int densidadeArestasJogador1, int densidadeArestasJogador2)
    {
        int locExistentes = fazerMedia(locExistentesJogador1, locExistentesJogador2); //decidir entre as 2 opcoes dos jogadores, quantas localizacoes existentes vai ter o mapa
        int tipoCaminho = gerarNumeroRandom(tipoCaminhoJogador1, tipoCaminhoJogador2); //decidir entre as 2 opcoes dos jogadores, qual é o tipo de caminho vai ter o mapa
        int densidadeArestas = gerarNumeroRandom(densidadeArestasJogador1, densidadeArestasJogador2); //decidir entre as 2 opcoes dos jogadores, qual é a densidade de restas que vai ter o mapa

        //criar o grafo

        //percorrer todos os pontos
        //gerar uma distancia random em cada ponto
    }

    private static int gerarNumeroRandom(int min, int max)
    {
        Random random = new Random();

        return random.nextInt(max - min) + min;
    }


    public static int fazerMedia(double value1, double value2)
    {
        return (int) Math.round((value1 + value2)/2);
    }

    /**
     * @param bidirecional     true se o grafo for bidirecionado, caso contrário false
     * @param densidadeArestas percentagem de arestas que devem estar presentes no grafo
     */
    @Override
    public void gerarArestas(boolean bidirecional, double densidadeArestas) {

    }

    /**
     * @param localizacaoA ponto/vertice A
     * @param localizacaoB ponto/vertice B
     * @return
     */
    @Override
    public int obterDistancia(int localizacaoA, int localizacaoB) {
        return 0;
    }

    /**
     *
     */
    @Override
    public void exportarMapa() {

    }

    /**
     *
     */
    @Override
    public void importarMapa() {

    }
}
