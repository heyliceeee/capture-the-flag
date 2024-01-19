package org.example.api.implementation;

import org.example.api.interfaces.*;
import org.example.collections.implementation.ArrayOrderedList;

public class Jogo
{

    public static void partida(int quemComeca, IJogador jogador1, IJogador jogador2, RouteNetwork<ILocal> grafo, IRaiz raiz, IRota rota)
    {
        IJogador jogadorComeca = (quemComeca == 1) ? jogador1 : jogador2;//obter o jogador que começa

        ArrayOrderedList<IBot> botsJogador1 = jogador1.getBotsJogador();
        ArrayOrderedList<IBot> botsJogador2 = jogador2.getBotsJogador();

        int turno = 1; // iniciar com o primeiro turno
        int indiceBotJogador1 = 0; // indice do bot do jogador 1
        int indiceBotJogador2 = 0; // indice do bot do jogador 2


        while (true)//loop infinito até que o jogo termine
        {
            System.out.println("Turno " + turno);

            IBot botAtual = null;// Determinar qual bot deve jogar neste turno

            if (turno % 4 == 1 || turno % 4 == 3) //o primeiro jogador joga
            {
                botAtual = jogadorComeca.getBotsJogador().getElementAt(indiceBotJogador1); // Bot a jogar do jogador atual
                indiceBotJogador1 = (indiceBotJogador1 + 1) % jogadorComeca.getBotsJogador().size(); // Avançar para o próximo bot do jogador atual
            }
            else //o segundo jogador joga novamente
            {
                botAtual = (jogadorComeca == jogador1) ? jogador2.getBotsJogador().getElementAt(indiceBotJogador2) : jogador1.getBotsJogador().getElementAt(indiceBotJogador2); // Bot a jogar do jogador oposto
                indiceBotJogador2 = (indiceBotJogador2 + 1) % jogador2.getBotsJogador().size(); // Avançar para o próximo bot do jogador oposto
            }

            // Lógica para mover o bot atual de acordo com as regras
            // Implemente a lógica de movimento aqui


            if (verificaVitoria(jogadorComeca, jogador1, jogador2, botAtual, grafo, raiz, rota)) // Verificar se o bot atual alcançou o campo do inimigo
            {
                System.out.println(botAtual.getNome() + " venceu!");
                break; // Encerrar o loop, o jogo terminou
            }

            turno++; // Avançar para o próximo turno
        }
    }


    /**
     * verifica se o bot atual alcancou a bandeira inimiga
     * @param botAtual
     * @param grafo
     * @param raiz
     * @param rota
     * @return
     */
    private static boolean verificaVitoria(IJogador jogadorComeca, IJogador jogador1, IJogador jogador2, IBot botAtual, RouteNetwork<ILocal> grafo, IRaiz raiz, IRota rota)
    {
        IBandeira bandeiraJogadorOposto = (jogadorComeca == jogador1) ? jogador2.getBandeira() : jogador1.getBandeira();
        ICoordenada coordenadasBot = botAtual.getCoordenada();

        return coordenadasBot.equals(bandeiraJogadorOposto.getCoordenadas());
    }


    public void movimentarBot(){}
}
