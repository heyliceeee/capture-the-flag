package org.example.api.implementation;

import org.example.api.exceptions.NotLocalInstanceException;
import org.example.api.interfaces.*;
import org.example.collections.implementation.ArrayOrderedList;

import java.text.ParseException;
import java.util.Iterator;

public class Jogo
{

    public static void partida(int quemComeca, IJogador jogador1, IJogador jogador2, RouteNetwork<ILocal> grafo, IRaiz raiz, IRota rota) throws NotLocalInstanceException, ParseException
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


            movimentarBot(jogadorComeca, jogador1, jogador2, botAtual, grafo, raiz, rota);


            // Lógica para mover o bot atual de acordo com as regras
            // Implemente a lógica de movimento aqui


            if (verificaVitoria(jogadorComeca, jogador1, jogador2, botAtual)) // Verificar se o bot atual alcançou o campo do inimigo
            {
                System.out.println(botAtual.getNome() + " venceu!");
                break; // Encerrar o loop, o jogo terminou
            }

            turno++; // Avançar para o próximo turno
        }
    }


    /**
     * movimentar o bot de acordo com o seu algoritmo e atualiza as suas coordenadas
     * @param botAtual
     * @param grafo
     * @param raiz
     * @param rota
     */
    private static void movimentarBot(IJogador jogadorComeca, IJogador jogador1, IJogador jogador2, IBot botAtual, RouteNetwork<ILocal> grafo, IRaiz raiz, IRota rota) throws NotLocalInstanceException, ParseException
    {
        String algoritmo = botAtual.getAlgoritmoMovimento(); //algoritmo do bot
        ILocal pontoA = null;

        ArrayOrderedList<ILocalizacao> localizacoesList = raiz.getListaLocalizacoes();
        ArrayOrderedList<IBandeira> bandeirasList = raiz.getListaBandeiras();


        //region saber qual localizacao/bandeira esta o bot
        for (ILocalizacao localizacaoObj : localizacoesList)
        {
            if(localizacaoObj.getCoordenadas().equals(botAtual.getCoordenada())) //saber qual localizacao esta o bot
            {
                pontoA = localizacaoObj; //encontrou uma localizacao com coordenadas iguais às do botAtual
                break; //não precisa continuar a procurar, podemos sair do loop
            }
        }


        for (IBandeira bandeiraObj : bandeirasList)
        {
            if(bandeiraObj.getCoordenadas().equals(botAtual.getCoordenada())) //saber qual bandeira esta o bot
            {
                pontoA = bandeiraObj; //encontrou uma bandeira com coordenadas iguais às do botAtual
                break; //não precisa continuar a procurar, podemos sair do loop
            }
        }
        //endregion

        ILocal pontoB = (jogadorComeca == jogador1) ? jogador2.getBandeira() : jogador1.getBandeira(); //bandeira inimiga


        if(algoritmo.equals("Caminho mais curto"))
        {
            Iterator<ILocal> caminhoMaisCurto = grafo.iteratorShortestPath(pontoA.getId(), pontoB.getId());

            if(caminhoMaisCurto.hasNext()) //existe vertices no caminho
            {
                caminhoMaisCurto.next(); //skip do primeiro localizacao/bandeira
                
                while (caminhoMaisCurto.hasNext()) //percorre todos os restantes vertices
                {
                    if(caminhoMaisCurto.hasNext()) //existe vertices no caminho
                    {
                        String localAtualString = ""+caminhoMaisCurto.next();
                        ILocal localAtual = raiz.getLocalByID(Integer.parseInt(localAtualString)); //descobrir qual a localizacao/bandeira atual

                        botAtual.setCoordenada(localAtual.getCoordenadas()); //coordenadas da localizacao/bandeira para onde se moveu
                    }

                    break; //porque queremos percorrer um vertice de cada vez
                }
            }
        }
        else if(algoritmo.equals("Caminho mais longo"))
        {

        }
    }


    /**
     * verifica se o bot atual alcancou a bandeira inimiga
     * @param jogadorComeca
     * @param jogador1
     * @param jogador2
     * @param botAtual
     * @return
     */
    private static boolean verificaVitoria(IJogador jogadorComeca, IJogador jogador1, IJogador jogador2, IBot botAtual)
    {
        IBandeira bandeiraJogadorOposto = (jogadorComeca == jogador1) ? jogador2.getBandeira() : jogador1.getBandeira();
        ICoordenada coordenadasBot = botAtual.getCoordenada();

        return coordenadasBot.equals(bandeiraJogadorOposto.getCoordenadas());
    }
}
