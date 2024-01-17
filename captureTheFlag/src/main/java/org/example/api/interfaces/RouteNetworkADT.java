package org.example.api.interfaces;

import org.example.api.exceptions.NotLocalInstanceException;
import org.example.collections.interfaces.NetworkADT;

import java.text.ParseException;
import java.util.Iterator;

/**
 * Contrato de um gráfico de caminho no campo de caminhos a jogadores
 * @param <T> tipo de locais no grafo
 */
public interface RouteNetworkADT<T> extends NetworkADT<T>
{
    /**
     * retorna o numero {@link org.example.api.implementation.Localizacao localizações} do grafo
     * @return o numero {@link org.example.api.implementation.Localizacao localizações} do grafo
     */
    int getNumeroDeLocalizacoes();

    /**
     * retorna o numero {@link org.example.api.implementation.Bandeira bandeiras} do grafo
     * @return o numero {@link org.example.api.implementation.Bandeira bandeiras} do grafo
     */
    int getNumeroDeBandeiras();

    /**
     * retorna as localizacoes do grafo
     * @return iterador com localizacoes
     */
    Iterator<ILocalizacao> getLocalizacoes();

    /**
     * retorna as bandeiras do grafo
     * @return iterador com bandeiras
     */
    Iterator<IBandeira> getBandeiras();


    /**
     * retorna as rotas existentes no grafo
     * @return interador com as rotas
     */
    Iterator<IRota<ILocal>> getRotas();


    /**
     * Rota mais curta do ponto de partida até a bandeira do inimigo do grafo
     * @param pontoDePartida ponto de partida
     * @return interador com a rota
     * @throws NotLocalInstanceException se o ponto de partida não for uma instância {@link ILocal local}
     */
    Iterator<ILocal> caminhoMaisCurtoABandeira(IRaiz raiz, T pontoDePartida) throws NotLocalInstanceException, ParseException;
}
