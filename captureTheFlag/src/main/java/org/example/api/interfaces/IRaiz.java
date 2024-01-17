package org.example.api.interfaces;

import org.example.api.exceptions.ElementAlreadyExistsException;
import org.example.api.exceptions.ElementNotFoundException;
import org.example.api.exceptions.NotLocalInstanceException;
import org.example.api.implementation.Coordenada;
import org.example.api.implementation.Interacao;
import org.example.api.implementation.TipoOrdenacao;
import org.example.collections.exceptions.EmptyCollectionException;

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
     * retorna "sucesso" se foi adicionado uma localizacao/bandeira ao grafo
     * @param local a ser adicionado
     * @return "sucesso" se foi adicionado uma localizacao/bandeira ao grafo
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
    String adicionarRota(ILocal local1, ILocal local2, double weight, String tipoCaminho) throws EmptyCollectionException;


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
     * retorna em string uma listagem dos localizacoes
     * @return em string uma listagem dos localizacoes
     */
    String getListaLocalizacoes();

    /**
     * retorna em string uma listagem das bandeiras
     * @return em string uma listagem das bandeiras
     */
    String getListaBandeiras();


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
     * atualiza o ID do localizacao
     * @param id atual ID do localizacao
     * @param newID novo ID do localizacao
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum localizacao
     * @throws ElementAlreadyExistsException se já existe um localizacao com o novo ID
     */
    void setLocalizacaoID(int id, int newID) throws ElementNotFoundException, ElementAlreadyExistsException;


    /**
     * atualiza o nome da localizacao
     * @param id ID da localizacao
     * @param nome novo nome do localizacao
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum localizacao
     */
    void setLocalizacaoNome(int id, String nome) throws ElementNotFoundException;


    /**
     * atualiza as coordenadas do localizacao
     * @param id ID do localizacao
     * @param coordenada novas coordenadas do localizacao
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum localizacao
     */
    void setLocalizacaoCoordenadas(int id, Coordenada coordenadas) throws ElementNotFoundException;


    /**
     * atualiza as interações do localizacao com o bot do jogador
     * @param id ID do localizacao
     * @param interacao novas interações do localizacao
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum localizacao
     */
    void setLocalizacaoInteracao(int id, List<Interacao> interacao) throws ElementNotFoundException;


    /**
     * retorna o bandeira com o id dado
     * @param id que vai ser procurado
     * @return o bandeira se foi encontrado, null se não foi encontrado
     */
    IBandeira getBandeiraPorID(int id);


    /**
     * atualiza o ID do bandeira
     * @param id atual ID do bandeira
     * @param newID novo ID do bandeira
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum bandeira
     * @throws ElementAlreadyExistsException se já existe um bandeira com o novo ID
     */
    void setBandeiraID(int id, int newID) throws ElementNotFoundException, ElementAlreadyExistsException;


    /**
     * atualiza o nome da bandeira
     * @param id ID da bandeira
     * @param nome novo nome do bandeira
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum bandeira
     */
    void setBandeiraNome(int id, String nome) throws ElementNotFoundException;


    /**
     * atualiza as coordenadas do bandeira
     * @param id ID do bandeira
     * @param coordenada novas coordenadas do bandeira
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum bandeira
     */
    void setBandeiraCoordenadas(int id, Coordenada coordenadas) throws ElementNotFoundException;


    /**
     * atualiza as interações do bandeira com o bot do jogador
     * @param id ID do bandeira
     * @param interacao novas interações do bandeira
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum bandeira
     */
    void setBandeiraInteracao(int id, List<Interacao> interacao) throws ElementNotFoundException;


    /**
     * retorna toda a informação do jogador pelo nome
     * @param nome nome do jogador a ser pesquisado
     * @return toda a informação do jogador se for encontrado, null caso contrário
     */
    IJogador getJogadorPorNome(String nome);


    /**
     * atualiza a quantidade de bandeiras conquistados pelo jogador
     * @param name nome do jogador
     * @param bandeirasConquistadas quantidade de bandeiras conquistados
     */
    void setJogadorBandeirasConquistadas(String name, int bandeirasConquistadas);


    /**
     * retorna toda a informação do bot pelo nome
     * @param nome nome do bot a ser pesquisado
     * @return toda a informação do bot se for encontrado, null caso contrário
     */
    IBot getBotPorNome(String nome);


    /**
     * atualiza as coordenadas do bot
     * @param name nome do bot
     * @param coordenadas coordenadas
     */
    void setBotCoordenadas(String name, Coordenada coordenadas);


    /**
     * retorna o iterador com os localizacoes ordenados por algum parâmetro
     * @param tipoOrdenacao tipo de ordenação a ser aplicado
     * @return iterador com os dados ordenados
     */
    Iterator<ILocalizacao> getLocalizacoesOrdenadosPor(TipoOrdenacao tipoOrdenacao);


    /**
     * retorna o iterador com os bandeiras ordenados por algum parâmetro
     * @param tipoOrdenacao tipo de ordenação a ser aplicado
     * @return iterador com os dados ordenados
     */
    Iterator<IBandeira> getBandeirasOrdenadosPor(TipoOrdenacao tipoOrdenacao);


    /**
     * retorna o iterador com os jogadores ordenados por algum parâmetro
     * @param tipoOrdenacao tipo de ordenação a ser aplicado
     * @return iterador com os dados ordenados
     */
    Iterator<IJogador> getJogadoresOrdenadosPor(TipoOrdenacao tipoOrdenacao);


    /**
     * retorna o iterador com os bots ordenados por algum parâmetro
     * @param tipoOrdenacao tipo de ordenação a ser aplicado
     * @return iterador com os dados ordenados
     */
    Iterator<IBot> getBotsOrdenadosPor(TipoOrdenacao tipoOrdenacao);


    /**
     * retorna o iterador com os rotas ordenados por algum parâmetro
     * @param tipoOrdenacao tipo de ordenação a ser aplicado
     * @return iterador com os dados ordenados
     */
    Iterator<IRota<ILocal>> getRotasOrdenadosPor(TipoOrdenacao tipoOrdenacao);
}
