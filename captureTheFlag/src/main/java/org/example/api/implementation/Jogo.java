package org.example.api.implementation;

import org.example.InterfaceGrafica.InterfaceGraficaJogo;
import org.example.api.exceptions.NotLocalInstanceException;
import org.example.api.interfaces.*;
import org.example.collections.implementation.ArrayOrderedList;

import java.text.ParseException;
import java.util.Iterator;

public class Jogo {

    public static void partida(InterfaceGraficaJogo ui, int quemComeca, IJogador jogador1, IJogador jogador2, RouteNetwork<ILocal> grafo, IRaiz raiz, IRota rota) throws NotLocalInstanceException, ParseException {
        IJogador jogadorComeca = (quemComeca == 1) ? jogador1 : jogador2;// obter o jogador que começa

        ArrayOrderedList<IBot> botsJogador1 = jogador1.getBotsJogador();
        ArrayOrderedList<IBot> botsJogador2 = jogador2.getBotsJogador();

        int turno = 1; // iniciar com o primeiro turno
        int indiceBotJogador1 = 0; // indice do bot do jogador 1
        int indiceBotJogador2 = 0; // indice do bot do jogador 2

        setSpawn(botsJogador1, botsJogador2, jogador1, jogador2);// definir que aonde cada bot comeca (na sua bandeira)

        System.out.println("\n\n\n\n\n");

        while (true)// loop infinito até que o jogo termine
        {
            System.out.println("\n\nTurno " + turno + "\n");

            IBot botAtual = null;// Determinar qual bot deve jogar neste turno

            if (turno % 4 == 1 || turno % 4 == 3) // o primeiro jogador joga
            {
                botAtual = jogadorComeca.getBotsJogador().getElementAt(indiceBotJogador1); // Bot a jogar do jogador atual
                indiceBotJogador1 = (indiceBotJogador1 + 1) % jogadorComeca.getBotsJogador().size(); // Avançar para o próximo bot do jogador atual
            }
            else // o segundo jogador joga novamente
            {
                botAtual = (jogadorComeca == jogador1) ? jogador2.getBotsJogador().getElementAt(indiceBotJogador2) : jogador1.getBotsJogador().getElementAt(indiceBotJogador2); // Bot a jogar do jogador oposto
                indiceBotJogador2 = (indiceBotJogador2 + 1) % jogador2.getBotsJogador().size(); // Avançar para o próximo bot do jogador oposto
            }

            movimentarBot(jogador1, jogador2, botAtual, grafo, raiz, rota);

            if (verificaVitoria(jogador1, jogador2, botAtual)) // Verificar se o bot atual alcançou o campo do inimigo
            {
                System.out.println("\n" + botAtual.getNome() + " venceu!");
                break; // Encerrar o loop, o jogo terminou
            }

            if(turno == Mapa.arestasList.size()) //verificar se e empate, visto que pode noa haver caminhos para a bandeira
            {
                System.out.println("\n empate!");
                break; // Encerrar o loop, o jogo terminou
            }


            ui.desenharJanela();
            turno++; // Avançar para o próximo turno
        }
    }

    /**
     * definir que aonde cada bot comeca (na sua bandeira)
     *
     * @param botsJogador1
     * @param botsJogador2
     * @param jogador1
     * @param jogador2
     */
    private static void setSpawn(ArrayOrderedList<IBot> botsJogador1, ArrayOrderedList<IBot> botsJogador2, IJogador jogador1, IJogador jogador2) {
        IBandeira bandeiraJogador1 = jogador1.getBandeira();
        IBandeira bandeiraJogador2 = jogador2.getBandeira();

        // region definir o spawn de cada bot
        for (IBot bot : botsJogador1) {
            bot.setCoordenada(bandeiraJogador1.getCoordenadas());
        }

        for (IBot bot : botsJogador2) {
            bot.setCoordenada(bandeiraJogador2.getCoordenadas());
        }

        // endregion
    }

    /**
     * movimentar o bot de acordo com o seu algoritmo e atualiza as suas coordenadas
     *
     * @param botAtual
     * @param grafo
     * @param raiz
     * @param rota
     */
    private static void movimentarBot(IJogador jogador1, IJogador jogador2, IBot botAtual, RouteNetwork<ILocal> grafo, IRaiz raiz, IRota rota) throws NotLocalInstanceException, ParseException {
        String algoritmo = botAtual.getAlgoritmoMovimento(); // algoritmo do bot
        ILocal pontoA = null;

        ArrayOrderedList<ILocalizacao> localizacoesList = raiz.getListaLocalizacoes();
        ArrayOrderedList<IBandeira> bandeirasList = raiz.getListaBandeiras();

        // region saber qual localizacao/bandeira esta o bot

        for (ILocalizacao localizacaoObj : localizacoesList) {
            if (localizacaoObj.getCoordenadas() != null && localizacaoObj.getCoordenadas().equals(botAtual.getCoordenada())) // saber qual localizacao esta o bot
            {
                pontoA = localizacaoObj; // encontrou uma localizacao com coordenadas iguais às do botAtual
                break; // não precisa continuar a procurar, podemos sair do loop
            }
        }

        for (IBandeira bandeiraObj : bandeirasList) {
            if (bandeiraObj.getCoordenadas() != null && bandeiraObj.getCoordenadas().equals(botAtual.getCoordenada())) // saber qual bandeira esta o bot
            {
                pontoA = bandeiraObj; // encontrou uma bandeira com coordenadas iguais às do botAtual
                break; // não precisa continuar a procurar, podemos sair do loop
            }
        }

        // endregion

        IBandeira pontoB = (botAtual.getNomeJogador().equals(jogador1.getNome())) ? jogador2.getBandeira() : jogador1.getBandeira(); // bandeira inimiga


        if (algoritmo.equals("Dijkstra")) {
            CaminhoMaisCurto(grafo, botAtual, raiz, pontoA, pontoB);
        } else if (algoritmo.equals("BFS")) {
            BuscaPorProfundidade(grafo, botAtual, raiz, pontoA);
        } else if (algoritmo.equals("DFS")) {
            BuscaPorLargura(grafo, botAtual, raiz, pontoA);
        }
    }

    /**
     * busca por largura
     *
     * @param grafo
     * @param botAtual
     * @param raiz
     * @param pontoA
     */
    private static void BuscaPorLargura(RouteNetwork<ILocal> grafo, IBot botAtual, IRaiz raiz, ILocal pontoA) {
        Iterator<ILocal> dfs = grafo.iteratorDFS(pontoA.getId());

        if (dfs.hasNext()) // existe vertices no caminho
        {
            System.out.print(botAtual.getNome() + ": " + dfs.next() + " => ");

            while (dfs.hasNext()) // percorre todos os restantes vertices
            {
                if (dfs.hasNext()) // existe vertices no caminho
                {
                    Object resultadoObjeto = dfs.next();
                    System.out.print(resultadoObjeto + " => ");

                    String resultadoString = resultadoObjeto.toString();
                    int resultadoInt = Integer.parseInt(resultadoString);

                    ILocal localAtual = raiz.getLocalByID(resultadoInt); // descobrir qual a localizacao/bandeira atual

                    botAtual.setCoordenada(localAtual.getCoordenadas()); // coordenadas da localizacao/bandeira para
                    // onde se moveu
                }

                break; // porque queremos percorrer um vertice de cada vez
            }
        }
    }

    /**
     * busca por profundidade
     *
     * @param grafo
     * @param botAtual
     * @param raiz
     * @param pontoA
     */
    private static void BuscaPorProfundidade(RouteNetwork<ILocal> grafo, IBot botAtual, IRaiz raiz, ILocal pontoA) {
        Iterator<ILocal> bfs = grafo.iteratorBFS(pontoA.getId());

        if (bfs.hasNext()) // existe vertices no caminho
        {
            System.out.print(botAtual.getNome() + ": " + bfs.next() + " => ");

            while (bfs.hasNext()) // percorre todos os restantes vertices
            {
                if (bfs.hasNext()) // existe vertices no caminho
                {
                    Object resultadoObjeto = bfs.next();
                    System.out.print(resultadoObjeto + " => ");

                    String resultadoString = resultadoObjeto.toString();
                    int resultadoInt = Integer.parseInt(resultadoString);

                    ILocal localAtual = raiz.getLocalByID(resultadoInt); // descobrir qual a localizacao/bandeira atual

                    botAtual.setCoordenada(localAtual.getCoordenadas()); // coordenadas da localizacao/bandeira para
                    // onde se moveu
                }

                break; // porque queremos percorrer um vertice de cada vez
            }
        }
    }

    /**
     * caminho mais curto
     *
     * @param grafo
     * @param botAtual
     * @param raiz
     */
    private static void CaminhoMaisCurto(RouteNetwork<ILocal> grafo, IBot botAtual, IRaiz raiz, ILocal pontoA, IBandeira pontoB) throws NotLocalInstanceException, ParseException {

        Iterator<ILocal> caminhoMaisCurto = grafo.iteratorShortestPath(pontoA.getId(), pontoB.getId());

        if (caminhoMaisCurto.hasNext()) // existe vertices no caminho
        {
            System.out.print(botAtual.getNome() + ": " + caminhoMaisCurto.next() + " => ");

            while (caminhoMaisCurto.hasNext()) // percorre todos os restantes vertices
            {
                if (caminhoMaisCurto.hasNext()) // existe vertices no caminho
                {
                    Object resultadoObjeto = caminhoMaisCurto.next();
                    System.out.print(resultadoObjeto + " => ");

                    String resultadoString = resultadoObjeto.toString();
                    int resultadoInt = Integer.parseInt(resultadoString);

                    ILocal localAtual = raiz.getLocalByID(resultadoInt); // descobrir qual a localizacao/bandeira atual

                    botAtual.setCoordenada(localAtual.getCoordenadas()); // coordenadas da localizacao/bandeira para
                    // onde se moveu
                }

                break; // porque queremos percorrer um vertice de cada vez
            }
        }
    }

    /**
     * verifica se o bot atual alcancou a bandeira inimiga
     *
     * @param jogador1
     * @param jogador2
     * @param botAtual
     * @return
     */
    private static boolean verificaVitoria(IJogador jogador1, IJogador jogador2, IBot botAtual)
    {
        IBandeira bandeiraJogadorOposto = (botAtual.getNomeJogador().equals(jogador1.getNome())) ? jogador2.getBandeira() : jogador1.getBandeira();
        ICoordenada coordenadasBot = botAtual.getCoordenada();

        return coordenadasBot.equals(bandeiraJogadorOposto.getCoordenadas());
    }
}