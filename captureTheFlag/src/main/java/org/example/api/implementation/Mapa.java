package org.example.api.implementation;

import org.example.api.interfaces.*;
import org.example.collections.implementation.ArrayOrderedList;
import org.example.collections.implementation.LinkedList;
import org.example.collections.interfaces.IExporter;

import java.util.Random;

public class Mapa implements IMapa {
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
     * 
     * @param qttLocExistentes
     * @param tipoCaminho
     * @param densidadeArestas
     */
    public Mapa(int qttLocExistentes, String tipoCaminho, int densidadeArestas) {
        this.qttLocExistentes = qttLocExistentes;
        this.tipoCaminho = tipoCaminho;
        this.densidadeArestas = densidadeArestas;
    }

    public static void gerarMapa(RouteNetwork grafo, IRaiz raiz, int locExistentesJogador1, int locExistentesJogador2, int tipoCaminhoJogador1, int tipoCaminhoJogador2, int densidadeArestasJogador1, int densidadeArestasJogador2)
    {
        //region validações

        String tipoCaminhoString = "";
        int arestas = 0, locExistentes = 0, densidadeArestas = 0, tipoCaminho = 0;

        if (locExistentesJogador1 > locExistentesJogador2)
        {
            locExistentes = fazerMedia(locExistentesJogador2, locExistentesJogador1); // decidir entre as 2 opcoes dos jogadores, quantas localizacoes existentes vai ter o mapa
        }
        else if(locExistentesJogador2 > locExistentesJogador1)
        {
            locExistentes = fazerMedia(locExistentesJogador1, locExistentesJogador2); // decidir entre as 2 opcoes dos jogadores, quantas localizacoes existentes vai ter o mapa
        }
        else
        {
            locExistentes = locExistentesJogador1;
        }


        if (densidadeArestasJogador1 > densidadeArestasJogador2)
        {
            densidadeArestas = gerarNumeroRandom(densidadeArestasJogador2, densidadeArestasJogador1); // decidir entre as 2 opcoes dos jogadores, qual é a densidade de restas que vai ter o mapa
        }
        else if(densidadeArestasJogador2 > densidadeArestasJogador1)
        {
            densidadeArestas = gerarNumeroRandom(densidadeArestasJogador1, densidadeArestasJogador2); // decidir entre as 2 opcoes dos jogadores, qual é a densidade de restas que vai ter o mapa
        }
        else
        {
            densidadeArestas = densidadeArestasJogador1;
        }


        if (tipoCaminhoJogador1 > tipoCaminhoJogador2)
        {
            tipoCaminho = gerarNumeroRandom(tipoCaminhoJogador2, tipoCaminhoJogador1); // decidir entre as 2 opcoes dos jogadores, qual é o tipo de caminho vai ter o mapa
        }
        else if (tipoCaminhoJogador2 > tipoCaminhoJogador1)
        {
            tipoCaminho = gerarNumeroRandom(tipoCaminhoJogador1, tipoCaminhoJogador2); // decidir entre as 2 opcoes dos jogadores, qual é o tipo de caminho vai ter o mapa
        }
        else
        {
            tipoCaminho = tipoCaminhoJogador1;
        }


        if (tipoCaminho == 1)
        {
            tipoCaminhoString = "direcionado";
        }
        else
        {
            tipoCaminhoString = "bidirecionado";
        }


        // densidade das arestas
        if (tipoCaminhoString.equals("direcionado"))
        {
            arestas = Math.round((locExistentes * (locExistentes - 1) * ((float) densidadeArestas / 100)));
        }
        else
        {
            arestas = Math.round(((float) (locExistentes * (locExistentes - 1) * (densidadeArestas / 100)) / 2));
        }

        //endregion

        //region criar o grafo && adicionar os dados ás classes
        ArrayOrderedList<String> nomeSpots = new ArrayOrderedList<>();
        nomeSpots.add("CT Stairs");
        nomeSpots.add("Ticket");
        nomeSpots.add("Jungle");
        nomeSpots.add("Market");
        nomeSpots.add("B Apartments");
        nomeSpots.add("Top Mid");
        nomeSpots.add("Connector");
        nomeSpots.add("T Ramp");
        nomeSpots.add("Palace");
        nomeSpots.add("Fire Box");
        nomeSpots.add("Window");
        nomeSpots.add("Kitchen");
        nomeSpots.add("Chair");
        nomeSpots.add("Stairs");
        nomeSpots.add("Sandwich");

        for (int i = 0; i < locExistentes; i++) // criar todos os vertices
        {
            grafo.addVertex(i); // adiciona um novo vertice ao grafo

            ILocalizacao localizacao = new Localizacao(i, "localizacao", nomeSpots.first(), null);

            raiz.adicionarLocal(localizacao); // adiciona um novo local
            nomeSpots.removeFirst();
        }

        for (int i = 0; i < arestas; i++) // percorrer todas arestas
        {
            int pontoA = gerarNumeroRandom(0, locExistentes); // gerar um ponto random entre 1 e o locExistentes
            int pontoB = gerarNumeroRandom(0, locExistentes); // gerar um ponto random entre 1 e o locExistentes
            int peso = gerarNumeroRandom(1, 15); // gerar um peso random em cada ponto

            grafo.addEdge(pontoA, pontoB, peso, tipoCaminhoString);
        }
    }

    private static int gerarNumeroRandom(int min, int max)
    {
        Random random = new Random();

        return random.nextInt(max - min) + min;
    }

    public static int fazerMedia(double value1, double value2)
    {
        return (int) Math.round((value1 + value2) / 2);
    }

    /**
     * @param bidirecional     true se o grafo for bidirecionado, caso contrário
     *                         false
     * @param densidadeArestas percentagem de arestas que devem estar presentes no
     *                         grafo
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
