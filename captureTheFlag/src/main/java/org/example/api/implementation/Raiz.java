package org.example.api.implementation;

import org.example.Demo;
import org.example.api.exceptions.ElementAlreadyExistsException;
import org.example.api.exceptions.ElementNotFoundException;
import org.example.api.exceptions.NotLocalInstanceException;
import org.example.api.interfaces.*;
import org.example.collections.exceptions.EmptyCollectionException;
import org.example.collections.implementation.ArrayUnorderedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

/**
 * representacao da classe da raiz do JSON
 */
public class Raiz implements IRaiz
{
    /**
     * grafo de rede que contém informações sobre os locais e rotas entre eles
     */
    public static RouteNetworkADT<ILocal> routeNetwork = new RouteNetwork<>();

    /**
     * Lista de jogadores associados ao jogo
     */
    public static UnorderedListADT<IJogador> jogadores = new ArrayUnorderedList<>();

    /**
     * Lista de jogadores associados ao jogo
     */
    public static UnorderedListADT<IBot> bots = new ArrayUnorderedList<>();




    /**
     * retorna "Successful" se foi adicionado uma localizacao/bandeira ao grafo
     *
     * @param local a ser adicionado
     * @return "Successful" se foi adicionado uma localizacao/bandeira ao grafo
     * @throws ElementAlreadyExistsException
     */
    @Override
    public boolean addLocal(ILocal local)
    {
        return this.routeNetwork.addVertex(local);
    }

    /**
     * retorna "Successful" se foi adicionado um jogador á lista dos jogadores
     *
     * @param jogador a ser adicionado
     * @return "Successful" se foi adicionado um jogador á lista dos jogadores
     */
    @Override
    public String adicionarJogador(IJogador jogador)
    {
        if(jogador == null)
        {
            throw new IllegalArgumentException("Jogador nao pode ser nulo");
        }

        String s = "Falhou";

        if(this.jogadores.isEmpty() || !this.jogadores.contains(jogador)) //se a lista de jogadores estiver vazia ou não conter o jogador a ser adicionado, adiciona-o á lista
        {
            this.jogadores.addToRear(jogador); //adiciona o player no fim da lista
            s = "Sucesso";
        }

        return s;
    }

    /**
     * remove jogador
     * @param jogador a ser removido
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public String removerJogador(IJogador jogador) throws EmptyCollectionException
    {
        if(jogador == null)
        {
            throw new IllegalArgumentException("Jogador nao pode ser nulo");
        }

        String s = "Falhou";

        if(this.jogadores.isEmpty() || this.jogadores.contains(jogador)) //se a lista de jogadores estiver vazia ou não conter o jogador a ser adicionado, adiciona-o á lista
        {
            this.jogadores.remove(jogador); //remove o player no fim da lista
            s = "Sucesso";
        }

        return s;
    }

    /**
     * retorna "Successful" se foi adicionado um bot á lista dos bots
     * @param bot a ser adicionado
     * @return "Successful" se foi adicionado um bot á lista dos bots
     */
    @Override
    public String adicionarBot(IBot bot)
    {
        if(bot == null)
        {
            throw new IllegalArgumentException("Bot nao pode ser nulo");
        }

        String s = "Falhou";

        if(this.bots.isEmpty() || this.bots.contains(bot)) //se a lista de bots estiver vazia ou não conter o bot a ser adicionado, adiciona-o á lista
        {
            this.bots.remove(bot); //remove o player no fim da lista
            s = "Sucesso";
        }

        return s;
    }

    /**
     * remove bot
     *
     * @param bot a ser removido
     * @return
     * @throws EmptyCollectionException
     */
    @Override
    public String removerBot(IBot bot) throws EmptyCollectionException
    {
        if(bot == null)
        {
            throw new IllegalArgumentException("Bot nao pode ser nulo");
        }

        String s = "Falhou";

        if(this.bots.isEmpty() || this.jogadores.contains(bot)) //se a lista de bots estiver vazia ou não conter o bot a ser adicionado, adiciona-o á lista
        {
            this.bots.remove(bot); //remove o bot no fim da lista
            s = "Sucesso";
        }

        return s;
    }

    /**
     * retorna "Successful" se conseguir adicionar uma rota entre 2 localizacoes e/ou bandeiras
     *
     * @param local1 uma localizacao/bandeira
     * @param local2 outra localizacao/bandeira
     * @param weight peso do vértice entre o local1 e local2
     * @return "Successful" se conseguir adicionar uma rota entre 2 localizacoes e/ou bandeiras
     */
    @Override
    public String adicionarRota(ILocal local1, ILocal local2, double weight) throws EmptyCollectionException
    {
        this.routeNetwork.addEdge(local1, local2, weight);

        return "Successful";
    }

    /**
     * exporta o grafo acerca das rotas em .dot e em .png
     *
     * @throws EmptyCollectionException se o grafo for vazio
     * @throws InterruptedException
     */
    @Override
    public void exportarGrafo() throws EmptyCollectionException, InterruptedException
    {
        Demo.exportar.exportarGrafo(this.routeNetwork, "rota");
    }

    /**
     * retorna o interador com a rota mais curta
     *
     * @param raiz
     * @param opcao ponto de chegada (localizacao ou bandeira)
     * @param local localizacao ou bandeira
     * @return o interador com a rota mais curta
     * @throws EmptyCollectionException
     */
    @Override
    public Iterator<ILocal> getRota(IRaiz raiz, int opcao, ILocal local) throws EmptyCollectionException, NotLocalInstanceException, ParseException
    {
        Iterator<ILocal> localsIterator;

        switch (opcao)
        {
            case 1:
                localsIterator = routeNetwork.caminhoMaisCurtoABandeira(raiz, local);
                return localsIterator;
        }

        return null;
    }

    /**
     * retorna em string uma listagem dos jogadores
     *
     * @return em string uma listagem dos jogadores
     */
    @Override
    public String getListaJogadores()
    {
        String s = "Jogadores: {\n";

        if(!this.jogadores.isEmpty())
        {
            Iterator<IJogador> iterator = jogadores.iterator();

            while (iterator.hasNext())
            {
                s += iterator.next().toString() + "\n";
            }
        }
        else
        {
            s += "nao existe jogadores na lista!\n";
        }

        s += "}";

        return s;
    }

    /**
     * retorna em string uma listagem dos bots dos jogadores
     *
     * @return em string uma listagem dos bots dos jogadores
     */
    @Override
    public String getListaBots()
    {
        String s = "Bots: {\n";

        if(!this.bots.isEmpty())
        {
            Iterator<IBot> iterator = bots.iterator();

            while (iterator.hasNext())
            {
                s += iterator.next().toString() + "\n";
            }
        }
        else
        {
            s += "nao existe bots na lista!\n";
        }

        s += "}";

        return s;
    }

    /**
     * retorna em string uma listagem dos localizacoes
     *
     * @return em string uma listagem dos localizacoes
     */
    @Override
    public String getListaLocalizacoes()
    {
        String s = "Localizacoes: {\n";

        if (this.routeNetwork.getNumeroDeLocalizacoes() != 0)
        {
            Iterator<ILocalizacao> iteratorLocalizacao = this.routeNetwork.getLocalizacoes();

            while (iteratorLocalizacao.hasNext())
            {
                s += iteratorLocalizacao.next().toString() + "\n";
            }
        }
        else
        {
            s += "nao existe localizacoes na lista!\n";
        }

        s += "}";

        return s;
    }

    /**
     * retorna em string uma listagem das bandeiras
     *
     * @return em string uma listagem das bandeiras
     */
    @Override
    public String getListaBandeiras()
    {
        String s = "Bandeiras: {\n";

        if (this.routeNetwork.getNumeroDeBandeiras() != 0)
        {
            Iterator<IBandeira> iteratorBandeira = this.routeNetwork.getBandeiras();

            while (iteratorBandeira.hasNext())
            {
                s += iteratorBandeira.next().toString() + "\n";
            }
        }
        else
        {
            s += "nao existe bandeiras na lista!\n";
        }

        s += "}";

        return s;
    }

    /**
     * exporta todos os jogadores associados ao jogo em JSON
     *
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    @Override
    public void exportarJogadoresParaJson() throws IOException
    {
        Demo.iEJson.exportarParaFicheiroJSON(getJogadoresArrayJSON().toJSONString(), "Jogadores");
    }

    /**
     * exporta todos os bots dos jogadores associados ao jogo em JSON
     *
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    @Override
    public void exportarBotsParaJson() throws IOException
    {
        Demo.iEJson.exportarParaFicheiroJSON(getBotsArrayJSON().toJSONString(), "Bots");
    }

    /**
     * exporta todos as localizacoes associados ao jogo em JSON
     *
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    @Override
    public void exportarLocalizacoesParaJson() throws IOException
    {
        Demo.iEJson.exportarParaFicheiroJSON(getLocalizacoesArrayJSON().toJSONString(), "Localizacoes");
    }

    /**
     * exporta todas as bandeiras associados ao jogo em JSON
     *
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    @Override
    public void exportarBandeirasParaJson() throws IOException
    {
        Demo.iEJson.exportarParaFicheiroJSON(getBandeirasArrayJSON().toJSONString(), "Bandeiras");
    }

    /**
     * exporta todos os rotas associados ao jogo em JSON
     *
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    @Override
    public void exportarRotasParaJson() throws IOException
    {
        Demo.iEJson.exportarParaFicheiroJSON(getRotasArrayJSON().toJSONString(), "Rotas");
    }

    /**
     * exporta toda a informação associados ao jogo em JSON
     *
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    @Override
    public void exportarRaizParaJson() throws IOException
    {
        JSONObject raiz = new JSONObject();

        raiz.put("localizacoes", getLocalizacoesArrayJSON());
        raiz.put("bandeiras", getBandeirasArrayJSON());
        raiz.put("rotas", getRotasArrayJSON());
        raiz.put("jogadores", getJogadoresArrayJSON());
        raiz.put("bots", getBotsArrayJSON());

        Demo.iEJson.exportarParaFicheiroJSON(raiz.toJSONString(), "Raiz");
    }



    /**
     * retorna todos os rotas associados ao jogo em uma array json
     * @return todos os rotas associados ao jogo em uma array json
     */
    private JSONArray getRotasArrayJSON()
    {
        JSONArray rotasArray = new JSONArray();
        Iterator<IRota<ILocal>> iteratorRota = this.routeNetwork.getRotas();

        while (iteratorRota.hasNext())
        {
            IRota<ILocal> rota = iteratorRota.next();
            rotasArray.add(rotaParaObjetoJSON(rota));
        }

        return rotasArray;
    }


    /**
     * retorna todos os bandeiras associados ao jogo em uma array json
     * @return todos os bandeiras associados ao jogo em uma array json
     */
    private JSONArray getBandeirasArrayJSON()
    {
        JSONArray bandeirasArray = new JSONArray();
        Iterator<IBandeira> iteratorBandeira = this.routeNetwork.getBandeiras();

        while (iteratorBandeira.hasNext())
        {
            bandeirasArray.add(iteratorBandeira.next().bandeiraParaObjetoJSON());
        }

        return bandeirasArray;
    }


    /**
     * retorna todos os localizacoes associados ao jogo em uma array json
     * @return todos os localizacoes associados ao jogo em uma array json
     */
    private JSONArray getLocalizacoesArrayJSON()
    {
        JSONArray localizacoesArray = new JSONArray();
        Iterator<ILocalizacao> iteratorLocalizacao = this.routeNetwork.getLocalizacoes();

        while (iteratorLocalizacao.hasNext())
        {
            localizacoesArray.add(iteratorLocalizacao.next().localizacaoParaObjetoJSON());
        }

        return localizacoesArray;
    }


    /**
     * retorna todos os bots associados ao jogo em uma array json
     * @return todos os bots associados ao jogo em uma array json
     */
    private JSONArray getBotsArrayJSON()
    {
        JSONArray botsArray = new JSONArray();
        Iterator<IBot> iteratorBot = this.bots.iterator();

        while (iteratorBot.hasNext())
        {
            botsArray.add(iteratorBot.next().botParaObjetoJson());
        }

        return botsArray;
    }


    /**
     * retorna todos os jogadores associados ao jogo em uma array json
     * @return todos os jogadores associados ao jogo em uma array json
     */
    private JSONArray getJogadoresArrayJSON()
    {
        JSONArray jogadoresArray = new JSONArray();
        Iterator<IJogador> iteratorJogador = this.jogadores.iterator();

        while (iteratorJogador.hasNext())
        {
            jogadoresArray.add(iteratorPlayer.next().jogadorParaObjetoJson());
        }

        return jogadoresArray;
    }

    /**
     * retorna o {@link ILocal localizacao/bandeira} com o ID enviado por parâmetro
     *
     * @param id do localizacao/bandeira a ser procurado
     * @return o localizacao/bandeira se foi encontrado ou null se não foi encontrado
     */
    @Override
    public ILocal getLocalByID(int id)
    {
        ILocalizacao localizacao = getLocalizacaoPorID(id);
        IBandeira bandeira = getBandeiraPorID(id);

        if(localizacao != null)
        {
            return localizacao;
        }
        else if(bandeira != null)
        {
            return bandeira;
        }
        else
        {
            return null;
        }
    }

    /**
     * retorna o localizacao com o id dado
     *
     * @param id que vai ser procurado
     * @return o localizacao se foi encontrado, null se não foi encontrado
     */
    @Override
    public ILocalizacao getLocalizacaoPorID(int id)
    {
        Iterator<ILocalizacao> iterator = this.routeNetwork.getLocalizacoes();
        ILocalizacao localizacao;

        while (iterator.hasNext())
        {
            localizacao = iterator.next();

            if(localizacao.getId() == id)
            {
                return localizacao;
            }
        }

        return null;
    }

    /**
     * atualiza o ID do localizacao
     *
     * @param id    atual ID do localizacao
     * @param newID novo ID do localizacao
     * @throws ElementNotFoundException      se o ID enviado por parametro não corresponde a nenhum localizacao
     * @throws ElementAlreadyExistsException se já existe um localizacao com o novo ID
     */
    @Override
    public void setLocalizacaoID(int id, int newID) throws ElementNotFoundException, ElementAlreadyExistsException
    {

    }

    /**
     * atualiza o nome da localizacao
     *
     * @param id   ID da localizacao
     * @param nome novo nome do localizacao
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum localizacao
     */
    @Override
    public void setLocalizacaoNome(int id, String nome) throws ElementNotFoundException {

    }

    /**
     * atualiza as coordenadas do localizacao
     *
     * @param id         ID do localizacao
     * @param coordenada novas coordenadas do localizacao
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum localizacao
     */
    @Override
    public void setLocalizacaoCoordenadas(int id, Coordenada coordenada) throws ElementNotFoundException {

    }

    /**
     * atualiza as interações do localizacao com o bot do jogador
     *
     * @param id        ID do localizacao
     * @param interacao novas interações do localizacao
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum localizacao
     */
    @Override
    public void setLocalizacaoInteracao(int id, List<Interacao> interacao) throws ElementNotFoundException {

    }

    /**
     * retorna o bandeira com o id dado
     *
     * @param id que vai ser procurado
     * @return o bandeira se foi encontrado, null se não foi encontrado
     */
    @Override
    public IBandeira getBandeiraPorID(int id)
    {
        Iterator<IBandeira> iterator = this.routeNetwork.getBandeiras();
        IBandeira bandeira;

        while (iterator.hasNext())
        {
            bandeira = iterator.next();

            if(bandeira.getId() == id)
            {
                return bandeira;
            }
        }

        return null;
    }

    /**
     * atualiza o ID do bandeira
     *
     * @param id    atual ID do bandeira
     * @param newID novo ID do bandeira
     * @throws ElementNotFoundException      se o ID enviado por parametro não corresponde a nenhum bandeira
     * @throws ElementAlreadyExistsException se já existe um bandeira com o novo ID
     */
    @Override
    public void setBandeiraID(int id, int newID) throws ElementNotFoundException, ElementAlreadyExistsException
    {

    }

    /**
     * atualiza o nome da bandeira
     *
     * @param id   ID da bandeira
     * @param nome novo nome do bandeira
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum bandeira
     */
    @Override
    public void setBandeiraNome(int id, String nome) throws ElementNotFoundException {

    }

    /**
     * atualiza as coordenadas do bandeira
     *
     * @param id         ID do bandeira
     * @param coordenada novas coordenadas do bandeira
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum bandeira
     */
    @Override
    public void setBandeiraCoordenadas(int id, Coordenada coordenada) throws ElementNotFoundException {

    }

    /**
     * atualiza as interações do bandeira com o bot do jogador
     *
     * @param id        ID do bandeira
     * @param interacao novas interações do bandeira
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum bandeira
     */
    @Override
    public void setBandeiraInteracao(int id, List<Interacao> interacao) throws ElementNotFoundException {

    }

    /**
     * retorna toda a informação do jogador pelo nome
     *
     * @param nome nome do jogador a ser pesquisado
     * @return toda a informação do jogador se for encontrado, null caso contrário
     */
    @Override
    public IJogador getJogadorPorNome(String nome)
    {
        Iterator<IJogador> iterator = this.jogadores.iterator();
        IJogador jogador;

        while (iterator.hasNext())
        {
            jogador = iterator.next();

            if(jogador.getNome().equals(nome))
            {
                return jogador;
            }
        }

        return null;
    }

    /**
     * atualiza a quantidade de bandeiras conquistados pelo jogador
     *
     * @param name                  nome do jogador
     * @param bandeirasConquistadas quantidade de bandeiras conquistados
     */
    @Override
    public void setJogadorBandeirasConquistadas(String name, int bandeirasConquistadas)
    {

    }

    /**
     * retorna toda a informação do bot pelo nome
     *
     * @param nome nome do bot a ser pesquisado
     * @return toda a informação do bot se for encontrado, null caso contrário
     */
    @Override
    public IBot getBotPorNome(String nome)
    {
        Iterator<IBot> iterator = this.bots.iterator();
        IBot bot;

        while (iterator.hasNext())
        {
            bot = iterator.next();

            if(bot.getNome().equals(nome))
            {
                return bot;
            }
        }

        return null;
    }

    /**
     * atualiza as coordenadas do bot
     *
     * @param name        nome do bot
     * @param coordenadas coordenadas
     */
    @Override
    public void setBotCoordenadas(String name, Coordenada coordenadas) {

    }

    /**
     * retorna o iterador com os localizacoes ordenados por algum parâmetro
     *
     * @param tipoOrdenacao tipo de ordenação a ser aplicado
     * @return iterador com os dados ordenados
     */
    @Override
    public Iterator<ILocalizacao> getLocalizacoesOrdenadosPor(tipoOrdenacao tipoOrdenacao) {
        return null;
    }

    /**
     * retorna o iterador com os bandeiras ordenados por algum parâmetro
     *
     * @param tipoOrdenacao tipo de ordenação a ser aplicado
     * @return iterador com os dados ordenados
     */
    @Override
    public Iterator<IBandeira> getBandeirasOrdenadosPor(tipoOrdenacao tipoOrdenacao) {
        return null;
    }

    /**
     * retorna o iterador com os jogadores ordenados por algum parâmetro
     *
     * @param tipoOrdenacao tipo de ordenação a ser aplicado
     * @return iterador com os dados ordenados
     */
    @Override
    public Iterator<IJogador> getJogadoresOrdenadosPor(tipoOrdenacao tipoOrdenacao) {
        return null;
    }

    /**
     * retorna o iterador com os bots ordenados por algum parâmetro
     *
     * @param tipoOrdenacao tipo de ordenação a ser aplicado
     * @return iterador com os dados ordenados
     */
    @Override
    public Iterator<IBot> getBotsOrdenadosPor(tipoOrdenacao tipoOrdenacao) {
        return null;
    }

    /**
     * retorna o iterador com os rotas ordenados por algum parâmetro
     *
     * @param tipoOrdenacao tipo de ordenação a ser aplicado
     * @return iterador com os dados ordenados
     */
    @Override
    public Iterator<IRota<ILocal>> getRotasOrdenadosPor(tipoOrdenacao tipoOrdenacao) {
        return null;
    }
}