package org.example.api.implementation;

import org.example.api.interfaces.*;
import org.example.collections.implementation.LinkedList;
import org.example.collections.interfaces.IExporter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public static void gerarMapa(RouteNetwork grafo, IRaiz raiz, int locExistentesJogador1, int locExistentesJogador2, int tipoCaminhoJogador1, int tipoCaminhoJogador2, int densidadeArestasJogador1, int densidadeArestasJogador2)
    {
        String tipoCaminhoString = "";
        int arestas = 0, locExistentes = 0, densidadeArestas = 0, tipoCaminho = 0;

        //region validacoes dos argumentos

        if(locExistentesJogador1 > locExistentesJogador2)
        {
            locExistentes = fazerMedia(locExistentesJogador2, locExistentesJogador1); //decidir entre as 2 opcoes dos jogadores, quantas localizacoes existentes vai ter o mapa
        }
        else
        {
            locExistentes = fazerMedia(locExistentesJogador1, locExistentesJogador2); //decidir entre as 2 opcoes dos jogadores, quantas localizacoes existentes vai ter o mapa
        }


        if(densidadeArestasJogador1 > densidadeArestasJogador2)
        {
            densidadeArestas = gerarNumeroRandom(densidadeArestasJogador2, densidadeArestasJogador1); //decidir entre as 2 opcoes dos jogadores, qual é a densidade de restas que vai ter o mapa
        }
        else
        {
            densidadeArestas = gerarNumeroRandom(densidadeArestasJogador1, densidadeArestasJogador2); //decidir entre as 2 opcoes dos jogadores, qual é a densidade de restas que vai ter o mapa
        }


        if(tipoCaminhoJogador1 > tipoCaminhoJogador2)
        {
            tipoCaminho = gerarNumeroRandom(tipoCaminhoJogador2, tipoCaminhoJogador1); //decidir entre as 2 opcoes dos jogadores, qual é o tipo de caminho vai ter o mapa
        }
        else
        {
            tipoCaminho = gerarNumeroRandom(tipoCaminhoJogador1, tipoCaminhoJogador2); //decidir entre as 2 opcoes dos jogadores, qual é o tipo de caminho vai ter o mapa
        }


        if(tipoCaminho == 1)
        {
            tipoCaminhoString = "direcionado";
        }
        else
        {
            tipoCaminhoString = "bidirecionado";
        }


        //densidade das arestas
        if(tipoCaminhoString.equals("direcionado"))
        {
            arestas = Math.round((locExistentes * (locExistentes - 1) * ((float) densidadeArestas / 100)));
        }
        else
        {
            arestas = Math.round(((float) (locExistentes * (locExistentes - 1) * (densidadeArestas / 100)) /2));
        }

        //endregion


        //region criar o grafo && adicionar os dados ás classes
        List<String> nomeSpots = new ArrayList<>(Arrays.asList(
                "CT Stairs", "Ticket", "Jungle", "Market", "B Apartments",
                "Top Mid", "Connector", "T Ramp", "Palace", "Fire Box",
                "Window", "Kitchen", "Chair", "Stairs", "Sandwich"
        ));



        for (int i = 0; i < locExistentes; i++) //criar todos os vertices
        {
            grafo.addVertex(i); //adiciona um novo vertice ao grafo

            ILocalizacao localizacao = new Localizacao(i, "localizacao", nomeSpots.get(i), null);

            raiz.adicionarLocal(localizacao); //adiciona um novo local
        }

        for (int j = 0; j < arestas; j++) //percorrer todas arestas
        {
            int x = gerarNumeroRandom(0, locExistentes); //gerar um ponto random entre 0 e o locExistentes
            int y = gerarNumeroRandom(0, locExistentes); //gerar um ponto random entre 0 e o locExistentes
            int peso = gerarNumeroRandom(1, 15); //gerar um peso random em cada ponto

            grafo.addEdge(x, y, peso, tipoCaminhoString); //adiciona uma nova aresta


            Coordenada coordenadas = new Coordenada(x, y);

            ILocalizacao localizacao = raiz.getLocalizacaoPorID(x);//procurar a localizacao por id
            localizacao.setCoordenadas(coordenadas); //adicionar coordenadas da localizacao

            raiz.removerLocal(localizacao); //eliminar o local existente
            raiz.adicionarLocal(localizacao); //adiciona um novo local
        }

        //endregion

        //region adicionar rotas criadas pelo grafo

        for(int i=0; i < arestas; i++)
        {
            adicionarRota(raiz);
        }

        //endregion
    }


    /**
     * retorna um valor aleatorio entre o min e o max
     * @param min valor minimo
     * @param max valor maximo
     * @return um valor aleatorio entre o min e o max
     */
    private static int gerarNumeroRandom(int min, int max)
    {
        Random random = new Random();

        return random.nextInt(max - min) + min;
    }


    /**
     * retorna a media entre 2 valores
     * @param value1
     * @param value2
     * @return a media entre 2 valores
     */
    public static int fazerMedia(double value1, double value2)
    {
        return (int) Math.round((value1 + value2)/2);
    }


    /**
     * adicionar rota criada no grafo
     * @param raiz
     */
    public static void adicionarRota(IRaiz raiz)
    {
        //ILocal deLocal = raiz.getLocalByID();
    }




    /**
     * @param bidirecional true se o grafo for bidirecionado, caso contrário false
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
    public int obterDistancia(int localizacaoA, int localizacaoB)
    {
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
