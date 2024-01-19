package org.example;

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
import java.util.Scanner;

public class Demo {
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


    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        mostrarMenuInicial();
    }

    //region MENUS DO JOGO

    /**
     * mostrar o menu incial
     * 
     * @throws EmptyCollectionException
     */
    public static void mostrarMenuInicial() throws EmptyCollectionException, IOException, ParseException, InterruptedException {
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
    private static void mostrarMenuJogo() throws IOException, ParseException, InterruptedException {
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

    private static void mostrarMenuCriarMapa() throws IOException, InterruptedException
    {
        boolean exit = false;
        int option = 0;

        int locExistentesJogador1 = 0, tipoCaminhoJogador1 = 0, densidadeArestasJogador1 = 0, locBandeiraJogador1 = 0;

        int locExistentesJogador2 = 0, tipoCaminhoJogador2 = 0, densidadeArestasJogador2 = 0, locBandeiraJogador2 = 0;

        do {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|        CRIAR MAPA - JOGADOR 1        |");
            System.out.println("+--------------------------------------+");
            System.out.println("introduza os seguintes dados: *         ");
            System.out.println("+--------------------------------------+");
            System.out.println("| Quantidade localizacoes existentes:  |\n");

            locExistentesJogador1 = scanner.nextInt();

            System.out.println("| Tipo de caminhos que são gerados:    |");
            System.out.println("| 1. direcionado                       |");
            System.out.println("| 2. bidirecional                      |\n");

            tipoCaminhoJogador1 = scanner.nextInt();

            System.out.println("| Densidade das arestas (%):           |\n");

            densidadeArestasJogador1 = scanner.nextInt();

            System.out.println("+-------------------------------------+");

        } while (locExistentesJogador1 <= 0 || tipoCaminhoJogador1 < 1 || tipoCaminhoJogador1 > 2 || densidadeArestasJogador1 < 1 || densidadeArestasJogador1 > 100);

        do {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|        CRIAR MAPA - JOGADOR 2        |");
            System.out.println("+--------------------------------------+");
            System.out.println("introduza os seguintes dados: *         ");
            System.out.println("+--------------------------------------+");
            System.out.println("| Quantidade localizacoes existentes:  |\n");

            locExistentesJogador2 = scanner.nextInt();

            System.out.println("| Tipo de caminhos que são gerados:    |");
            System.out.println("| 1. direcionado                       |");
            System.out.println("| 2. bidirecional                      |\n");

            tipoCaminhoJogador2 = scanner.nextInt();

            System.out.println("| Densidade das arestas (%):           |\n");

            densidadeArestasJogador2 = scanner.nextInt();

            System.out.println("+---------------------------------------+");

        } while (locExistentesJogador2 <= 0 || tipoCaminhoJogador2 < 1 || tipoCaminhoJogador2 > 2
                || densidadeArestasJogador2 < 1 || densidadeArestasJogador2 > 100);

        //region criar mapa
        Mapa.gerarMapa(grafo, raiz, rota, locExistentesJogador1, locExistentesJogador2, tipoCaminhoJogador1, tipoCaminhoJogador2, densidadeArestasJogador1, densidadeArestasJogador2);
        //endregion

        //region escolher bandeira
        ArrayOrderedList<ILocalizacao> localizacaoList = raiz.getListaLocalizacoes();

        System.out.println("\n");
        System.out.println("+--------------------------------------+");
        System.out.println("|        CRIAR MAPA - JOGADOR 1        |");
        System.out.println("+--------------------------------------+");
        System.out.println("selecione uma das opcoes: *             ");
        System.out.println("+--------------------------------------+");
        System.out.println("| Localizacao da Bandeira:             |\n");

        //region ciclo para mostrar todas os nodes
        for (ILocalizacao localizacaoObj : localizacaoList)
        {
            System.out.println("| " + localizacaoObj.getId() + ". " + localizacaoObj.getNome() + "\n");
        }
        //endregion


        locBandeiraJogador1 = scanner.nextInt();

        System.out.println("+--------------------------------------+");

        ILocalizacao localJogador1Escolheu = raiz.getLocalizacaoPorID(locBandeiraJogador1); //localizacao selecionada
        raiz.removerLocal(localJogador1Escolheu); // remover localizacao selecionada
        IBandeira bandeiraJogador1 = new Bandeira(0, "Bandeira", localJogador1Escolheu.getNome(), localJogador1Escolheu.getCoordenadas()); //criar bandeira

        jogador1.setBandeira(bandeiraJogador1);

        localizacaoList = raiz.getListaLocalizacoes();


        System.out.println("\n");
        System.out.println("+--------------------------------------+");
        System.out.println("|        CRIAR MAPA - JOGADOR 2        |");
        System.out.println("+--------------------------------------+");
        System.out.println("selecione uma das opcoes: *             ");
        System.out.println("+--------------------------------------+");
        System.out.println("| Localizacao da Bandeira:           |\n");

        //region ciclo para mostrar todas os nodes sem ser aquela selecionada pelo jogador1
        for (ILocalizacao localizacaoObj : localizacaoList)
        {
            System.out.println("| " + localizacaoObj.getId() + ". " + localizacaoObj.getNome() + "\n");
        }
        //endregion

        locBandeiraJogador2 = scanner.nextInt();

        System.out.println("+--------------------------------------+");

        ILocalizacao localJogador2Escolheu = raiz.getLocalizacaoPorID(locBandeiraJogador2); //localizacao selecionada
        raiz.removerLocal(localJogador2Escolheu); // remover localizacao selecionada
        IBandeira bandeiraJogador2 = new Bandeira(1, "Bandeira", localJogador2Escolheu.getNome(), localJogador2Escolheu.getCoordenadas()); //criar bandeira

        jogador2.setBandeira(bandeiraJogador2);

        localizacaoList = raiz.getListaLocalizacoes(); //localizacoes atualizadas apos selecionar a bandeira
        //endregion

        mostrarMenuSelecionarBots();
    }

    private static void mostrarMenuSelecionarBots()
    {
        int botsJogador1 = 0, botsJogador2 = 0;

        do
        {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|        CRIAR MAPA - JOGADOR 1        |");
            System.out.println("+--------------------------------------+");
            System.out.println("introduza os seguintes dados: *         ");
            System.out.println("+--------------------------------------+");
            System.out.println("| Numero de bots (3 - 5):            |\n");

            botsJogador1 = scanner.nextInt();
        }
        while (botsJogador1 < 3 || botsJogador1 > 5);


        do
        {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|        CRIAR MAPA - JOGADOR 2        |");
            System.out.println("+--------------------------------------+");
            System.out.println("introduza os seguintes dados: *         ");
            System.out.println("+--------------------------------------+");
            System.out.println("| Numero de bots (3 - 5):            |\n");

            botsJogador2 = scanner.nextInt();
        }
        while (botsJogador2 < 3 || botsJogador2 > 5);


        int nBots = 0;

        //region fazer media de bots
        if(botsJogador1 > botsJogador2)
        {
            nBots = Mapa.fazerMedia(botsJogador2, botsJogador1);
        }
        else if(botsJogador2 > botsJogador1)
        {
            nBots = Mapa.fazerMedia(botsJogador1, botsJogador2);
        }
        else
        {
            nBots = botsJogador1;
        }
        //endregion

        //region selecionar algoritmo
        int algoritmoBotJogador1 = 0, algoritmoBotJogador2 = 0, i=-1;
        String stringAlgortimoBotJogador1 = "", stringAlgortimoBotJogador2 = "";

        do
        {
            i++;

            System.out.println("\n");
            System.out.println("+-----------------------------------------------+");
            System.out.println("|              CRIAR MAPA - JOGADOR 1           |");
            System.out.println("+-----------------------------------------------+");
            System.out.println("selecione uma das opcoes: *                      ");
            System.out.println("+-----------------------------------------------+");
            System.out.println("| Para o bot "+(i+1)+", seleciona o algoritmo desejado: |");
            System.out.println("| 1. Caminho mais curto                         |");
            System.out.println("| 2. Caminho mais longo                         |");
            System.out.println("| 3. Caminho x                         |");


            algoritmoBotJogador1 = scanner.nextInt();


            if(algoritmoBotJogador1 == 1)
            {
                stringAlgortimoBotJogador1 = "Caminho mais curto";
            }
            else if(algoritmoBotJogador1 == 2)
            {
                stringAlgortimoBotJogador1 = "Caminho mais longo";
            }


            String nomeBot = "Bot "+(i+1)+" do Jogador 1";

            IBot bot = new Bot(nomeBot, "Jogador 1", jogador1.getBandeira().getCoordenadas(), stringAlgortimoBotJogador1); //adicionar bot ao jogador

            jogador1.setNumeroBots(nBots);

        }
        while (algoritmoBotJogador1 < 1 || algoritmoBotJogador1 > 3 ||  i < (nBots-1));


        i=-1;
        do
        {
            i++;

            System.out.println("\n");
            System.out.println("+-----------------------------------------------+");
            System.out.println("|              CRIAR MAPA - JOGADOR 2           |");
            System.out.println("+-----------------------------------------------+");
            System.out.println("selecione uma das opcoes: *                      ");
            System.out.println("+-----------------------------------------------+");
            System.out.println("| Para o bot "+(i+1)+", seleciona o algoritmo desejado: |");
            System.out.println("| 1. Caminho mais curto                         |");
            System.out.println("| 2. Caminho mais longo                         |");
            System.out.println("| 3. Caminho x                         |");


            algoritmoBotJogador2 = scanner.nextInt();


            if(algoritmoBotJogador2 == 1)
            {
                stringAlgortimoBotJogador2 = "Caminho mais curto";
            }
            else if(algoritmoBotJogador2 == 2)
            {
                stringAlgortimoBotJogador2 = "Caminho mais longo";
            }


            String nomeBot = "Bot "+(i+1)+" do Jogador 2";

            IBot bot = new Bot(nomeBot, "Jogador 2", jogador2.getBandeira().getCoordenadas(), stringAlgortimoBotJogador2); //adicionar bot ao jogador

            jogador2.setNumeroBots(nBots);
        }
        while (algoritmoBotJogador2 < 1 || algoritmoBotJogador2 > 3 ||  i < (nBots-1));
        //endregion
    }

    // endregion
}