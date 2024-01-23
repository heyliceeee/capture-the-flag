package org.example;

import org.example.InterfaceGrafica.DataManager;
import org.example.InterfaceGrafica.InterfaceGraficaMenuPrincipal;
import org.example.api.exceptions.NotLocalInstanceException;
import org.example.api.implementation.*;
import org.example.api.interfaces.*;
import org.example.collections.exceptions.EmptyCollectionException;
import org.example.collections.implementation.ArrayOrderedList;
import org.example.collections.implementation.ExporterGraph;
import org.example.collections.implementation.LinkedList;
import org.example.collections.interfaces.IExporter;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Demo {
    // region instancias
    public static Mapa mapa = new Mapa();
    public static ImportarExportarJson iEJson = new ImportarExportarJson();

    /**
     * Instância do exportar que transforma o grafo em uma imagem
     */
    public static final IExporter exportar = new ExporterGraph("docs/export");

    /**
     * grafo que tem informação acerca dos locais e das rotas entre eles
     */
    public static RouteNetwork<ILocal> grafo = new RouteNetwork<>();

    public static IRaiz raiz = new Raiz();
    public static ILocal local = new Local(0, "", null);
    public static IJogador jogador1 = new Jogador("Jogador 1", 0, 0);
    public static IJogador jogador2 = new Jogador("Jogador 2", 0, 0);
    public static IRota rota = new Rota(null, null, 0);
    static Scanner scanner = new Scanner(System.in);

    // endregion

    static DataManager dataManager = new DataManager();

    public static void main(String[] args) throws IOException, ParseException, InterruptedException,
            NotLocalInstanceException, java.text.ParseException {
        // mostrarMenuInicial();

        InterfaceGraficaMenuPrincipal.setDataManager(dataManager);
        InterfaceGraficaMenuPrincipal.launch(InterfaceGraficaMenuPrincipal.class, args);
    }

    // region MENUS DO JOGO

    /**
     * mostrar o menu incial
     * 
     * @throws EmptyCollectionException
     */
    public static void mostrarMenuInicial() throws EmptyCollectionException, IOException, ParseException,
            InterruptedException, NotLocalInstanceException, java.text.ParseException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;

        do {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|         CAPTURAR A BANDEIRA          |");
            System.out.println("+--------------------------------------+");
            System.out.println("seleciona uma opcao: *                  ");
            System.out.println("+--------------------------------------+");
            System.out.println("| 01. Jogar                            |\n" +
                    "| 99. Sair                             |");
            System.out.println("+--------------------------------------+");

            option = scanner.nextInt();

            /**
             * Depois de selecionar a opcao do menu, faz o que pretende
             */
            switch (option) {
                case 1:
                    mostrarMenuJogo();
                    break;

                case 99:
                    System.out.println("sair do jogo...");
                    exit = true;
                    break;

                default:
                    System.out.println("opcao invalida, opcao selecionada entre 1 ou 99 para sair.");
                    break;
            }
        } while (!exit);
    }

    /**
     * mostrar o menu do jogo {criar mapa, importar mapa}
     */
    private static void mostrarMenuJogo() throws IOException, ParseException, InterruptedException,
            NotLocalInstanceException, java.text.ParseException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;

        do {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|                  MAPA                |");
            System.out.println("+--------------------------------------+");
            System.out.println("seleciona uma opcao: *                  ");
            System.out.println("+--------------------------------------+");
            System.out.println("| 01. Criar mapa                       |\n" +
                    "| 02. Importar mapa                    |\n" +
                    "| 99. Sair                             |");
            System.out.println("+--------------------------------------+");

            option = scanner.nextInt();

            /**
             * Depois de selecionar a opcao do menu, faz o que pretende
             */
            switch (option) {
                case 1:
                    mostrarMenuCriarMapa();
                    break;

                case 2:
                    iEJson.importarDoFicheiroJSON(raiz, local, "docs/import/import.json");
                    break;

                case 99:
                    System.out.println("sair do jogo...");
                    exit = true;
                    break;

                default:
                    System.out.println("opcao invalida, opcao selecionada entre 1 e 2 ou 99 para sair.");
                    break;
            }
        } while (!exit);
    }

    /**
     * mostra o menu de criar o mapa
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    private static void mostrarMenuCriarMapa()
            throws IOException, InterruptedException, NotLocalInstanceException, java.text.ParseException {
        boolean exit = false;
        int option = 0;

        int locExistentesJogador1 = 0, tipoCaminhoJogador1 = 0, densidadeArestasJogador1 = 0, locBandeiraJogador1 = 0;

        int locExistentesJogador2 = 0, tipoCaminhoJogador2 = 0, densidadeArestasJogador2 = 0, locBandeiraJogador2 = 0;

        do {
            System.out.println("\n");
            System.out.println("+-------------------------------------------+");
            System.out.println("|        CRIAR MAPA - JOGADOR 1             |");
            System.out.println("+-------------------------------------------+");
            System.out.println("introduza os seguintes dados: *              ");
            System.out.println("+-------------------------------------------+");
            System.out.println("| Quantidade localizacoes existentes: (>=6) |\n");

            locExistentesJogador1 = scanner.nextInt();

            System.out.println("| Tipo de caminhos que são gerados:         |");
            System.out.println("| 1. direcionado                            |");
            System.out.println("| 2. bidirecional                           |\n");

            tipoCaminhoJogador1 = scanner.nextInt();

            System.out.println("| Densidade das arestas (%):                |\n");

            densidadeArestasJogador1 = scanner.nextInt();

            System.out.println("+-------------------------------------------+");

        } while (locExistentesJogador1 < 6 || tipoCaminhoJogador1 < 1 || tipoCaminhoJogador1 > 2
                || densidadeArestasJogador1 < 1 || densidadeArestasJogador1 > 100);

        do {
            System.out.println("\n");
            System.out.println("+-------------------------------------------+");
            System.out.println("|        CRIAR MAPA - JOGADOR 2             |");
            System.out.println("+-------------------------------------------+");
            System.out.println("introduza os seguintes dados: *              ");
            System.out.println("+-------------------------------------------+");
            System.out.println("| Quantidade localizacoes existentes: (>=6) |\n");

            locExistentesJogador2 = scanner.nextInt();

            System.out.println("| Tipo de caminhos que são gerados:         |");
            System.out.println("| 1. direcionado                            |");
            System.out.println("| 2. bidirecional                           |\n");

            tipoCaminhoJogador2 = scanner.nextInt();

            System.out.println("| Densidade das arestas (%):                |\n");

            densidadeArestasJogador2 = scanner.nextInt();

            System.out.println("+-------------------------------------------+");

        } while (locExistentesJogador2 < 6 || tipoCaminhoJogador2 < 1 || tipoCaminhoJogador2 > 2
                || densidadeArestasJogador2 < 1 || densidadeArestasJogador2 > 100);

        // region criar mapa
        mapa.gerarMapa(grafo, raiz, rota, locExistentesJogador1, locExistentesJogador2, tipoCaminhoJogador1,
                tipoCaminhoJogador2, densidadeArestasJogador1, densidadeArestasJogador2);
        // endregion

        // region escolher bandeira
        Iterator<IRota<ILocal>> rotas = grafo.getRotas();

        System.out.println(grafo);

        ArrayOrderedList<ILocalizacao> localizacaoList = raiz.getListaLocalizacoes();

        System.out.println("\n");
        System.out.println("+-------------------------------------------+");
        System.out.println("|        CRIAR MAPA - JOGADOR 1             |");
        System.out.println("+-------------------------------------------+");
        System.out.println("selecione uma das opcoes: *                  ");
        System.out.println("+-------------------------------------------+");
        System.out.println("| Localizacao da Bandeira:                  |\n");

        // region ciclo para mostrar todas os nodes
        for (ILocalizacao localizacaoObj : localizacaoList) {
            System.out.println("| " + localizacaoObj.getId() + ". " + localizacaoObj.getNome() + "\n");
        }
        // endregion

        locBandeiraJogador1 = scanner.nextInt();

        System.out.println("+-------------------------------------------+");

        ILocalizacao localJogador1Escolheu = raiz.getLocalizacaoPorID(locBandeiraJogador1); // localizacao selecionada
        raiz.removerLocal(localJogador1Escolheu); // remover localizacao selecionada
        IBandeira bandeiraJogador1 = new Bandeira(localJogador1Escolheu.getId(), "Bandeira",
                localJogador1Escolheu.getNome(), localJogador1Escolheu.getCoordenadas()); // criar bandeira

        jogador1.setBandeira(bandeiraJogador1);
        raiz.adicionarLocal(bandeiraJogador1);

        localizacaoList = raiz.getListaLocalizacoes();

        System.out.println("\n");
        System.out.println("+-------------------------------------------+");
        System.out.println("|        CRIAR MAPA - JOGADOR 2             |");
        System.out.println("+-------------------------------------------+");
        System.out.println("selecione uma das opcoes: *                  ");
        System.out.println("+-------------------------------------------+");
        System.out.println("| Localizacao da Bandeira:                  |\n");

        // region ciclo para mostrar todas os nodes sem ser aquela selecionada pelo
        // jogador1
        for (ILocalizacao localizacaoObj : localizacaoList) {
            System.out.println("| " + localizacaoObj.getId() + ". " + localizacaoObj.getNome() + "\n");
        }
        // endregion

        locBandeiraJogador2 = scanner.nextInt();

        System.out.println("+-------------------------------------------+");

        ILocalizacao localJogador2Escolheu = raiz.getLocalizacaoPorID(locBandeiraJogador2); // localizacao selecionada
        raiz.removerLocal(localJogador2Escolheu); // remover localizacao selecionada
        IBandeira bandeiraJogador2 = new Bandeira(localJogador2Escolheu.getId(), "Bandeira",
                localJogador2Escolheu.getNome(), localJogador2Escolheu.getCoordenadas()); // criar bandeira

        jogador2.setBandeira(bandeiraJogador2);
        raiz.adicionarLocal(bandeiraJogador2);

        localizacaoList = raiz.getListaLocalizacoes(); // localizacoes atualizadas apos selecionar a bandeira
        // endregion

        mostrarMenuSelecionarBots();

        iniciarPartida();
    }

    /**
     * mostrar o menu de selecionar os bots
     */
    private static void mostrarMenuSelecionarBots() {
        int botsJogador1 = 0, botsJogador2 = 0;
        int maxBots = mapa.obterMaxBots();

        do {
            System.out.println("\n");
            System.out.println("+-------------------------------------------+");
            System.out.println("|        CRIAR MAPA - JOGADOR 1             |");
            System.out.println("+-------------------------------------------+");
            System.out.println("introduza os seguintes dados: *              ");
            System.out.println("+-------------------------------------------+");
            System.out.println("| Numero de bots (3 - " + (maxBots / 2) + "):                  |\n");

            botsJogador1 = scanner.nextInt();
        } while (botsJogador1 < 3 || botsJogador1 > (maxBots / 2));

        do {
            System.out.println("\n");
            System.out.println("+-------------------------------------------+");
            System.out.println("|        CRIAR MAPA - JOGADOR 2             |");
            System.out.println("+-------------------------------------------+");
            System.out.println("introduza os seguintes dados: *              ");
            System.out.println("+-------------------------------------------+");
            System.out.println("| Numero de bots (3 - " + (maxBots / 2) + "):                  |\n");

            botsJogador2 = scanner.nextInt();
        } while (botsJogador2 < 3 || botsJogador2 > (maxBots / 2));

        int nBots = 0;

        // region fazer media de bots
        if (botsJogador1 > botsJogador2) {
            nBots = mapa.fazerMedia(botsJogador2, botsJogador1);

            // Garante que o resultado é um valor inteiro e par, mas não maior que maxBots
            if (nBots % 2 != 0) {
                nBots--;
            }
        } else if (botsJogador2 > botsJogador1) {
            nBots = mapa.fazerMedia(botsJogador1, botsJogador2);

            // Garante que o resultado é um valor inteiro e par, mas não maior que maxBots
            if (nBots % 2 != 0) {
                nBots--;
            }
        } else {
            nBots = botsJogador1;

            // Garante que o resultado é um valor inteiro e par, mas não maior que maxBots
            if (nBots % 2 != 0) {
                nBots--;
            }
        }
        // endregion

        // region selecionar algoritmo
        int algoritmoBotJogador1 = 0, algoritmoBotJogador2 = 0, i = -1;
        String stringAlgortimoBotJogador1 = "", stringAlgortimoBotJogador2 = "";

        System.out.println("| há " + (nBots / 2) + " bots disponíveis                       \n|");

        do {
            i++;

            System.out.println("\n");
            System.out.println("+-----------------------------------------------+");
            System.out.println("|              CRIAR MAPA - JOGADOR 1           |");
            System.out.println("+-----------------------------------------------+");
            System.out.println("selecione uma das opcoes: *                      ");
            System.out.println("+-----------------------------------------------+");
            System.out.println("| Para o bot " + (i + 1) + ", seleciona o algoritmo desejado: |");
            System.out.println("| 1. Dijkstra                                   |");
            System.out.println("| 2. BFS                                        |");
            System.out.println("| 3. DFS                                        |");

            algoritmoBotJogador1 = scanner.nextInt();

            if (algoritmoBotJogador1 == 1) {
                stringAlgortimoBotJogador1 = "Dijkstra";
            } else if (algoritmoBotJogador1 == 2) {
                stringAlgortimoBotJogador1 = "BFS";
            } else if (algoritmoBotJogador1 == 3) {
                stringAlgortimoBotJogador1 = "DFS";
            }

            String nomeBot = "Bot " + (i + 1) + " do Jogador 1";

            ArrayOrderedList<IBot> listaBotsJogador1 = jogador1.getBotsJogador();
            IBot bot = new Bot(nomeBot, "Jogador 1", jogador1.getBandeira().getCoordenadas(),
                    stringAlgortimoBotJogador1); // adicionar bot ao jogador

            listaBotsJogador1.add(bot);// Adicione o bot à lista de bots do jogador 1

            jogador1.setNumeroBots(nBots / 2);

        } while (algoritmoBotJogador1 < 1 || algoritmoBotJogador1 > 3 || i < ((nBots / 2) - 1));

        System.out.println("| há " + (nBots / 2) + " bots disponíveis                         \n|");

        i = -1;
        do {
            i++;

            System.out.println("\n");
            System.out.println("+-----------------------------------------------+");
            System.out.println("|              CRIAR MAPA - JOGADOR 2           |");
            System.out.println("+-----------------------------------------------+");
            System.out.println("selecione uma das opcoes: *                      ");
            System.out.println("+-----------------------------------------------+");
            System.out.println("| Para o bot " + (i + 1) + ", seleciona o algoritmo desejado: |");
            System.out.println("| 1. Dijkstra                                   |");
            System.out.println("| 2. BFS                                        |");
            System.out.println("| 3. DFS                                        |");

            algoritmoBotJogador2 = scanner.nextInt();

            if (algoritmoBotJogador2 == 1) {
                stringAlgortimoBotJogador2 = "Dijkstra";
            } else if (algoritmoBotJogador2 == 2) {
                stringAlgortimoBotJogador2 = "BFS";
            } else if (algoritmoBotJogador2 == 3) {
                stringAlgortimoBotJogador2 = "DFS";
            }

            String nomeBot = "Bot " + (i + 1) + " do Jogador 2";

            ArrayOrderedList<IBot> listaBotsJogador2 = jogador2.getBotsJogador();
            IBot bot = new Bot(nomeBot, "Jogador 2", jogador2.getBandeira().getCoordenadas(),
                    stringAlgortimoBotJogador2); // adicionar bot ao jogador

            listaBotsJogador2.add(bot);// Adicione o bot à lista de bots do jogador 2

            jogador2.setNumeroBots(nBots / 2);
        } while (algoritmoBotJogador2 < 1 || algoritmoBotJogador2 > 3 || i < ((nBots / 2) - 1));
        // endregion
    }

    /**
     * mostrar o menu de iniciar a partida
     */
    private static void iniciarPartida() throws NotLocalInstanceException, java.text.ParseException {
        int quemComeca = mapa.gerarNumeroRandom(1, 2);

        System.out.println("\n");
        System.out.println("+-------------------------------------------+");
        System.out.println("|        quem comeca: JOGADOR " + quemComeca + "               |");
        System.out.println("+---------------------------------------------+");

        Jogo.partida(quemComeca, jogador1, jogador2, grafo, raiz, rota);
    }
    // endregion
}