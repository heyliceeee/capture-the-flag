package org.example.api.interfaces;

import org.example.api.exceptions.ElementAlreadyExistsException;
import org.example.api.exceptions.ElementNotFoundException;
import org.example.api.exceptions.NotLocalInstanceException;
import org.example.api.implementation.Coordenada;
import org.example.api.implementation.TipoOrdenacao;
import org.example.collections.exceptions.EmptyCollectionException;
import org.example.collections.implementation.ArrayOrderedList;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

/**
 * interface da raiz do JSON
 */
public interface IRaiz
{
    /**
     * retorna "sucesso" se foi adicionado uma localizacao/bandeira ao grafo
     * @param local a ser adicionado
     * @return "sucesso" se foi adicionado uma localizacao/bandeira ao grafo
     * @throws org.example.api.exceptions.ElementAlreadyExistsException
     */
    boolean adicionarLocal(ILocal local);


    /**
     * retorna "sucesso" se foi removido uma localizacao/bandeira ao grafo
     * @param local a ser removido
     * @return "sucesso" se foi removido uma localizacao/bandeira ao grafo
     * @throws org.example.api.exceptions.ElementAlreadyExistsException
     */
    boolean removerLocal(ILocal local);


    /**
     * retorna "sucesso" se foi adicionado um jogador á lista dos jogadores
     * @param jogador a ser adicionado
     * @return "sucesso" se foi adicionado um jogador á lista dos jogadores
     */
    String adicionarJogador(IJogador jogador);

    /**
     * remove jogador
     * @param jogador a ser removido
     * @return
     * @throws EmptyCollectionException
     */
    String removerJogador(IJogador jogador) throws EmptyCollectionException;


    /**
     * retorna "sucesso" se foi adicionado um bot á lista dos bots
     * @param bot a ser adicionado
     * @return "sucesso" se foi adicionado um bot á lista dos bots
     */
    String adicionarBot(IBot bot);

    /**
     * remove bot
     * @param bot a ser removido
     * @return
     * @throws EmptyCollectionException
     */
    String removerBot(IBot bot) throws EmptyCollectionException;


    /**
     * retorna "sucesso" se foi adicionado um mapa á lista de mapas
     * @param mapa a ser adicionado
     * @return "sucesso" se foi adicionado um mapa á lista de mapas
     */
    String adicionarMapa(IMapa mapa);


    /**
     * retorna "sucesso" se conseguir adicionar uma rota entre 2 localizacoes e/ou bandeiras
     * @param local1 uma localizacao/bandeira
     * @param local2 outra localizacao/bandeira
     * @param weight peso do vértice entre o local1 e local2
     * @return "sucesso" se conseguir adicionar uma rota entre 2 localizacoes e/ou bandeiras
     */
    String adicionarRota(ILocal local1, ILocal local2, double weight) throws EmptyCollectionException;


    /**
     * exporta o grafo acerca das rotas em .dot e em .png
     * @throws EmptyCollectionException se o grafo for vazio
     * @throws InterruptedException
     */
    void exportarGrafo() throws EmptyCollectionException, InterruptedException;


    /**
     * retorna o interador com a rota mais curta
     * @param opcao ponto de chegada (localizacao ou bandeira)
     * @param local localizacao ou bandeira
     * @return o interador com a rota mais curta
     * @throws EmptyCollectionException
     */
    Iterator<ILocal> getRota(IRaiz raiz, int opcao, ILocal local) throws EmptyCollectionException, NotLocalInstanceException, ParseException;


    /**
     * retorna em string uma listagem dos jogadores
     * @return em string uma listagem dos jogadores
     */
    String getListaJogadores();

    /**
     * retorna em string uma listagem dos bots dos jogadores
     * @return em string uma listagem dos bots dos jogadores
     */
    String getListaBots();

    /**
     * retorna a listagem dos localizacoes
     * @return a listagem dos localizacoes
     */
    ArrayOrderedList<ILocalizacao> getListaLocalizacoes();

    /**
     * retorna a listagem das bandeiras
     * @return a listagem das bandeiras
     */
    ArrayOrderedList<IBandeira> getListaBandeiras();


    /**
     * exporta todos os jogadores associados ao jogo em JSON
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    void exportarJogadoresParaJson() throws IOException;

    /**
     * exporta todos os bots dos jogadores associados ao jogo em JSON
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    void exportarBotsParaJson() throws IOException;


    /**
     * exporta todos as localizacoes associados ao jogo em JSON
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    void exportarLocalizacoesParaJson() throws IOException;


    /**
     * exporta todas as bandeiras associados ao jogo em JSON
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    void exportarBandeirasParaJson() throws IOException;


    /**
     * exporta todos os rotas associados ao jogo em JSON
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    void exportarRotasParaJson() throws IOException;


    /**
     * exporta toda a informação associados ao jogo em JSON
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    void exportarRaizParaJson() throws IOException;


    /**
     * retorna o {@link ILocal localizacao/bandeira} com o ID enviado por parâmetro
     * @param id do localizacao/bandeira a ser procurado
     * @return o localizacao/bandeira se foi encontrado ou null se não foi encontrado
     */
    ILocal getLocalByID(int id);


    /**
     * retorna o localizacao com o id dado
     * @param id que vai ser procurado
     * @return o localizacao se foi encontrado, null se não foi encontrado
     */
    ILocalizacao getLocalizacaoPorID(int id);


    /**
     * retorna o bandeira com o id dado
     * @param id que vai ser procurado
     * @return o bandeira se foi encontrado, null se não foi encontrado
     */
    IBandeira getBandeiraPorID(int id);


    /**
     * retorna toda a informação do bot pelo nome
     * @param nome nome do bot a ser pesquisado
     * @return toda a informação do bot se for encontrado, null caso contrário
     */
    IBot getBotPorNome(String nome);


    /**
     * retorna toda a informação do jogador pelo nome
     *
     * @param nome nome do jogador a ser pesquisado
     * @return toda a informação do jogador se for encontrado, null caso contrário
     */
    public IJogador getJogadorPorNome(String nome);
}
