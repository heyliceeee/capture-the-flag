package org.example.api.implementation;

import org.example.api.interfaces.*;
import org.example.collections.implementation.ArrayOrderedList;
import org.example.collections.interfaces.IExporter;

import java.util.*;

public class Mapa implements IMapa
{
    /**
     * quantidade de localizacoes existentes no mapa
     */
    private int qttLocExistentes;


    /**
     * tipo de caminho no mapa (bidirecional ou direcional)
     */
    public static String tipoCaminhoString;


    /**
     * densidade das arestas do mapa (10% - 100%)
     */
    private static int densidadeArestas;


    /**
     * localizacoes existentes do mapa
     */
    private static int locExistentes;


    /**
     * tipo de caminho no mapa (1 ou 2)
     */
    private static int tipoCaminho;


    /**
     * arestas no mapa
     */
    private static int arestas;


    /**
     * numero arestas no mapa
     */
    private static int calculoDensidadeArestas;


    static LinkedList<String> arestasList = new LinkedList<>();




    /**
     * constructor
     *
     * @param qttLocExistentes
     * @param tipoCaminhoString
     * @param densidadeArestas
     */
    public Mapa(int qttLocExistentes, String tipoCaminhoString, int densidadeArestas)
    {
        this.qttLocExistentes = qttLocExistentes;
        this.tipoCaminhoString = tipoCaminhoString;
        this.densidadeArestas = densidadeArestas;
    }


    /**
     * constructor
     *
     */
    public Mapa()
    {}


    public void gerarMapa(RouteNetwork grafo, IRaiz raiz, IRota rota, int locExistentesJogador1, int locExistentesJogador2, int tipoCaminhoJogador1, int tipoCaminhoJogador2, int densidadeArestasJogador1, int densidadeArestasJogador2)
    {
        validarArgumentos(locExistentesJogador1, locExistentesJogador2, densidadeArestasJogador1, densidadeArestasJogador2, tipoCaminhoJogador1, tipoCaminhoJogador2, tipoCaminho); // region validacoes dos argumentos

        //region densidade das arestas
        if(tipoCaminhoString.equals("direcionado"))
        {
            double direcionado = (locExistentes * (locExistentes - 1) * ((double) densidadeArestas / 100));
            calculoDensidadeArestas = (int) Math.round(direcionado);
        }
        else
        {
            double bidirecionado = (locExistentes * (locExistentes - 1) * ((double) densidadeArestas / 100))*2;
            calculoDensidadeArestas = (int) Math.round(bidirecionado);
        }

        arestas = calculoDensidadeArestas;

        //endregion

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

            raiz.adicionarLocal(localizacao); // adiciona um novo local á rede
            nomeSpots.removeFirst();
        }

        //region criar arestas

        gerarArestas(raiz, grafo);

        //endregion
    }


    /**
     * validar argumentos que cada jogador inseriu para gerar o mapa
     * @param locExistentesJogador1
     * @param locExistentesJogador2
     * @param densidadeArestasJogador1
     * @param densidadeArestasJogador2
     * @param tipoCaminhoJogador1
     * @param tipoCaminhoJogador2
     * @param tipoCaminho
     */
    private static void validarArgumentos(int locExistentesJogador1, int locExistentesJogador2, int densidadeArestasJogador1, int densidadeArestasJogador2, int tipoCaminhoJogador1, int tipoCaminhoJogador2, int tipoCaminho)
    {
        if (locExistentesJogador1 > locExistentesJogador2)
        {
            locExistentes = fazerMedia(locExistentesJogador2, locExistentesJogador1); // decidir entre as 2 opcoes dos jogadores, quantas localizacoes existentes vai ter o mapa
        }
        else if (locExistentesJogador2 > locExistentesJogador1)
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
        else if (densidadeArestasJogador2 > densidadeArestasJogador1)
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
    }


    /**
     * retorna um valor aleatorio entre o min e o max
     * 
     * @param min valor minimo
     * @param max valor maximo
     * @return um valor aleatorio entre o min e o max
     */
    public static int gerarNumeroRandom(int min, int max) {
        Random random = new Random();

        return random.nextInt(max - min) + min;
    }


    /**
     * retorna a media entre 2 valores
     * 
     * @param value1
     * @param value2
     * @return a media entre 2 valores
     */
    public static int fazerMedia(double value1, double value2)
    {
        return (int) Math.round((value1 + value2) / 2);
    }


    /**
     * retorna o numero maximo de bots que cada jogador pode ter, dependendo do tamanho do mapa
     * @return o numero maximo de bots que cada jogador pode ter
     */
    public static int obterMaxBots()
    {
        return locExistentes;
    }


    /**
     * gera arestas no mapa com base na densidade de arestas dada
     *
     * @param raiz
     * @param grafo
     */
    public static void gerarArestas(IRaiz raiz, RouteNetwork grafo)
    {
        //region garantir que todos os vertices tem uma aresta
        int de=0, para=0, countArestas=0;

        //cria as rotas
        for (de = 0; (de < locExistentes-1 && countArestas < arestas); de++)
        {
            for (para = de + 1; (para < locExistentes && countArestas < arestas); para++)
            {
                // Gerar valores aleatórios para "de" e "para"
                de = gerarNumeroRandom(0, locExistentes);
                para = gerarNumeroRandom(0, locExistentes);

                while (de == para)// Garantir que "de" e "para" sejam diferentes
                {
                    para = gerarNumeroRandom(0, locExistentes);
                }

                String aresta = de + ", " + para;
                String inversa = para + ", " + de;


                if(tipoCaminhoString.equals("bidirecionado"))
                {
                    if(!arestasList.contains(aresta) && !arestasList.contains(inversa))
                    {
                        arestasList.add(aresta);
                        arestasList.add(inversa);

                        countArestas += 2;
                    }
                }
                else if (tipoCaminhoString.equals("direcionado"))
                {
                    if(!arestasList.contains(aresta) && !arestasList.contains(inversa))
                    {
                        arestasList.add(aresta);

                        countArestas++;
                    }
                }
            }
        }


        //criar rotas
        criarRotas(raiz, grafo);
    }


    /**
     * criar rotas
     */
    private static void criarRotas(IRaiz raiz, RouteNetwork grafo)
    {
        for(String aresta : arestasList)
        {
            String[] vertices = aresta.split(", ");

            int de = Integer.parseInt(vertices[0]);//obter de
            int para = Integer.parseInt(vertices[1]);//obter para


            Coordenada coordenadasDe = new Coordenada(de * 100, para * 100); // definir coordenadas
            Coordenada coordenadasPara = new Coordenada(para * 100, de * 100); // definir coordenadas

            ILocalizacao localizacaoDe = raiz.getLocalizacaoPorID(de);// procurar a localizacao por id
            ILocalizacao localizacaoPara = raiz.getLocalizacaoPorID(para);// procurar a localizacao por id

            raiz.removerLocal(localizacaoDe); // eliminar o local existente
            raiz.removerLocal(localizacaoPara); // eliminar o local existente

            ILocalizacao localizacaoDeNova = new Localizacao(localizacaoDe.getId(), localizacaoDe.getTipo(), localizacaoDe.getNome(), coordenadasDe); // localizacao com as coordenadas atualizadas
            ILocalizacao localizacaoParaNova = new Localizacao(localizacaoPara.getId(), localizacaoPara.getTipo(), localizacaoPara.getNome(), coordenadasPara); // localizacao com as coordenadas atualizadas

            raiz.adicionarLocal(localizacaoDeNova); // adiciona um novo local
            raiz.adicionarLocal(localizacaoParaNova); // adiciona um novo local

            double distancia = gerarNumeroRandom(1, 15);

            ILocal localDe = raiz.getLocalByID(de); // procurar a local por id
            ILocal localPara = raiz.getLocalByID(para); // procurar a local por id


            if (tipoCaminhoString.equals("direcionado"))
            {
                grafo.addEdge(localDe.getId(), localPara.getId(), distancia); //adicionar aresta com peso ao grafo
                raiz.adicionarRota(localDe, localPara, distancia); //adicionar aresta com peso á rede
            }
            else
            {
                grafo.addEdge(localDe.getId(), localPara.getId(), distancia); //adicionar aresta com peso ao grafo
                //grafo.addEdge(localPara.getId(), localDe.getId(), distancia);

                raiz.adicionarRota(localDe, localPara, distancia); //adicionar aresta com peso á rede
                //raiz.adicionarRota(localPara, localDe, distancia);
            }
        }
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
