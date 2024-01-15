package org.example;

import org.example.api.implementation.Mapa;
import org.example.api.interfaces.IMapa;
import org.example.collections.exceptions.EmptyCollectionException;

import java.util.Scanner;

public class Demo
{
    public static void main(String[] args)
    {
        mostrarMenuInicial();
    }


    //region MENUS DO JOGO

    /**
     * mostrar o menu incial
     * @throws EmptyCollectionException
     */
    public static void mostrarMenuInicial() throws EmptyCollectionException
    {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;

        do
        {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|         CAPTURAR A BANDEIRA          |");
            System.out.println("+--------------------------------------+");
            System.out.println("seleciona uma opcao: *                  ");
            System.out.println("+--------------------------------------+");
            System.out.println(
                    "| 01. Jogar                            |\n" +
                            "| 99. Sair                             |"
            );
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
                    System.out.println("opcao invalida, opcao selecionada entre 1 e 2 ou 99 para sair.");
                    break;
            }
        } while (!exit);
    }


    /**
     * mostrar o menu do jogo {criar mapa, importar mapa}
     */
    private static void mostrarMenuJogo()
    {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;

        do
        {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|                  MAPA                |");
            System.out.println("+--------------------------------------+");
            System.out.println("seleciona uma opcao: *                  ");
            System.out.println("+--------------------------------------+");
            System.out.println(
                               "| 01. Criar mapa                       |\n" +
                               "| 02. Importar mapa                    |\n" +
                               "| 99. Sair                             |"
            );
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
                    //Mapa.importarMapa("captureTheFlag/docs/import/map.json");
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



    private static void mostrarMenuCriarMapa()
    {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;

        int locExistentesJogador1 = 0, tipoCaminhoJogador1 = 0, densidadeArestasJogador1 = 0, locBandeiraJogador1 = 0;

        int locExistentesJogador2 = 0, tipoCaminhoJogador2 = 0, densidadeArestasJogador2 = 0, locBandeiraJogador2 = 0;

        do
        {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|        CRIAR MAPA - JOGADOR 1        |");
            System.out.println("+--------------------------------------+");
            System.out.println("introduza os seguintes dados: *         ");
            System.out.println("+--------------------------------------+");
            System.out.println("| Quantidade localizacoes existentes:|\n");

            locExistentesJogador1 = scanner.nextInt();

            System.out.println("| Tipo de caminhos que são gerados:  |");
            System.out.println("| 1. direcionado                     |");
            System.out.println("| 2. bidirecional                    |\n");

            tipoCaminhoJogador1 = scanner.nextInt();

            System.out.println("| Densidade das arestas (%):         |\n");

            densidadeArestasJogador1 = scanner.nextInt();

            System.out.println("+--------------------------------------+");

        } while (locExistentesJogador1 == 0 || tipoCaminhoJogador1 == 0 || densidadeArestasJogador1 == 0.0);

        do
        {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|        CRIAR MAPA - JOGADOR 2        |");
            System.out.println("+--------------------------------------+");
            System.out.println("introduza os seguintes dados: *         ");
            System.out.println("+--------------------------------------+");
            System.out.println("| Quantidade localizacoes existentes:|\n");

            locExistentesJogador2 = scanner.nextInt();

            System.out.println("| Tipo de caminhos que são gerados:  |");
            System.out.println("| 1. direcionado                     |");
            System.out.println("| 2. bidirecional                    |\n");

            tipoCaminhoJogador1 = scanner.nextInt();

            System.out.println("| Densidade das arestas (%):         |\n");

            densidadeArestasJogador2 = scanner.nextInt();

            System.out.println("+--------------------------------------+");

        } while (locExistentesJogador2 == 0 || tipoCaminhoJogador2 == 0 || densidadeArestasJogador2 == 0.0);


        //criar mapa
        Mapa.gerarMapa(locExistentesJogador1, locExistentesJogador2, tipoCaminhoJogador1, tipoCaminhoJogador2, densidadeArestasJogador1, densidadeArestasJogador2);


        do
        {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|        CRIAR MAPA - JOGADOR 1        |");
            System.out.println("+--------------------------------------+");
            System.out.println("selecione uma das opcoes: *             ");
            System.out.println("+--------------------------------------+");
            System.out.println("| Localizacao da Bandeira:           |\n");

            //ciclo para mostrar todas os nodes

            locBandeiraJogador1 = scanner.nextInt();

            System.out.println("+--------------------------------------+");

        } while (locBandeiraJogador1 == 0);


        do
        {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|        CRIAR MAPA - JOGADOR 2        |");
            System.out.println("+--------------------------------------+");
            System.out.println("selecione uma das opcoes: *             ");
            System.out.println("+--------------------------------------+");
            System.out.println("| Localizacao da Bandeira:           |\n");

            //ciclo para mostrar todas os nodes sem ser aquela selecionada pelo jogador1

            locBandeiraJogador2 = scanner.nextInt();

            System.out.println("+--------------------------------------+");

        } while (locBandeiraJogador2 == 0);

    }

    //endregion
}