package org.example.api.implementation;

import org.example.Demo;
import org.example.InterfaceGrafica.DataManager;
import org.example.api.exceptions.ElementAlreadyExistsException;
import org.example.api.exceptions.ElementNotFoundException;
import org.example.api.exceptions.NotLocalInstanceException;
import org.example.api.interfaces.*;
import org.example.collections.exceptions.EmptyCollectionException;
import org.example.collections.implementation.ArrayOrderedList;
import org.example.collections.implementation.ArrayUnorderedList;
import org.example.collections.implementation.LinkedList;
import org.example.collections.interfaces.UnorderedListADT;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import static org.example.api.implementation.Mapa.*;

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
     * Lista de mapas associados ao jogo
     */
    public static UnorderedListADT<IMapa> mapas = new ArrayUnorderedList<>();



    private DataManager dataManager;



    /**
     * retorna "sucesso" se foi adicionado uma localizacao/bandeira ao grafo
     *
     * @param local a ser adicionado
     * @return "sucesso" se foi adicionado uma localizacao/bandeira ao grafo
     * @throws ElementAlreadyExistsException
     */
    @Override
    public boolean adicionarLocal(ILocal local)
    {
        return this.routeNetwork.addVertex(local);
    }

    /**
     * retorna "sucesso" se foi removido uma localizacao/bandeira ao grafo
     *
     * @param local a ser removido
     * @return "sucesso" se foi removido uma localizacao/bandeira ao grafo
     * @throws ElementAlreadyExistsException
     */
    @Override
    public boolean removerLocal(ILocal local)
    {
        return this.routeNetwork.removeVertex(local);
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

        if(this.bots.isEmpty() || !this.bots.contains(bot)) //se a lista de bots estiver vazia ou não conter o bot a ser adicionado, adiciona-o á lista
        {
            this.bots.addToRear(bot); //remove o player no fim da lista
            s = "Sucesso";
        }

        return s;
    }


    @Override
    public String getMapa()
    {
        String s = "Mapa: {\n";

        if(!this.mapas.isEmpty())
        {

        }

        s += "}";

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

        if(this.bots.isEmpty() || this.bots.contains(bot)) //se a lista de bots estiver vazia ou não conter o bot a ser adicionado, adiciona-o á lista
        {
            this.bots.remove(bot); //remove o bot no fim da lista
            s = "Sucesso";
        }

        return s;
    }

    /**
     * retorna "sucesso" se foi adicionado um mapa á lista de mapas
     *
     * @param mapa a ser adicionado
     * @return "sucesso" se foi adicionado um mapa á lista de mapas
     */
    @Override
    public String adicionarMapa(IMapa mapa)
    {
        if(mapa == null)
        {
            throw new IllegalArgumentException("mapa nao pode ser nulo");
        }

        String s = "falhou";

        if(this.mapas.isEmpty() || !this.mapas.contains(mapa)) //se a lista de mapas estiver vazia ou não conter o mapa a ser adicionado, adiciona-o á lista
        {
            this.mapas.addToRear(mapa); //adiciona o mapa no fim da lista
            s = "sucesso";
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
/*    @Override
    public void exportarGrafo() throws EmptyCollectionException, InterruptedException
    {
        dataManager.exportar.exportGraph(this.routeNetwork, "rota");
    }*/

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

        /*switch (opcao)
        {
            case 1:
                localsIterator = routeNetwork.caminhoMaisCurtoABandeira(raiz, local);
                return localsIterator;
        }*/

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
     * retorna a listagem dos localizacoes
     *
     * @return a listagem dos localizacoes
     */
    @Override
    public ArrayOrderedList<ILocalizacao> getListaLocalizacoes()
    {
        ArrayOrderedList<ILocalizacao> lista = new ArrayOrderedList<>();

        if(routeNetwork.getNumeroDeLocalizacoes() != 0)
        {
            Iterator<ILocalizacao> iterator = routeNetwork.getLocalizacoes();

            if (iterator != null)
            {
                while (iterator.hasNext())
                {
                    ILocalizacao localizacao = iterator.next();

                    if (localizacao != null)
                    {
                        lista.add(localizacao);
                    }
                }
            }
        }

        return lista;

        /*String s = "Localizacoes: {\n";

        if (routeNetwork.getNumeroDeLocalizacoes() != 0)
        {
            Iterator<ILocalizacao> iteratorLocalizacao = routeNetwork.getLocalizacoes();

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

        return s;*/
    }

    /**
     * retorna a listagem das bandeiras
     *
     * @return a listagem das bandeiras
     */
    @Override
    public ArrayOrderedList<IBandeira> getListaBandeiras()
    {
        ArrayOrderedList<IBandeira> lista = new ArrayOrderedList<>();

        if(routeNetwork.getNumeroDeBandeiras() != 0)
        {
            Iterator<IBandeira> iterator = routeNetwork.getBandeiras();

            if (iterator != null)
            {
                while (iterator.hasNext())
                {
                    IBandeira bandeira = iterator.next();

                    if (bandeira != null)
                    {
                        lista.add(bandeira);
                    }
                }
            }
        }

        return lista;

        /*String s = "Bandeiras: {\n";

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

        return s;*/
    }

    /**
     * exporta todos os jogadores associados ao jogo em JSON
     *
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    @Override
    public void exportarJogadoresParaJson() throws IOException
    {
        dataManager.getiEJson().exportarParaFicheiroJSON(getJogadoresArrayJSON().toJSONString(), "Jogadores");
    }

    /**
     * exporta todos os bots dos jogadores associados ao jogo em JSON
     *
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    @Override
    public void exportarBotsParaJson() throws IOException
    {
        dataManager.getiEJson().exportarParaFicheiroJSON(getBotsArrayJSON().toJSONString(), "Bots");
    }

    /**
     * exporta todos as localizacoes associados ao jogo em JSON
     *
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    @Override
    public void exportarLocalizacoesParaJson() throws IOException
    {
        dataManager.getiEJson().exportarParaFicheiroJSON(getLocalizacoesArrayJSON().toJSONString(), "Localizacoes");
    }

    /**
     * exporta todas as bandeiras associados ao jogo em JSON
     *
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    @Override
    public void exportarBandeirasParaJson() throws IOException
    {
        dataManager.getiEJson().exportarParaFicheiroJSON(getBandeirasArrayJSON().toJSONString(), "Bandeiras");
    }

    /**
     * exporta todos os rotas associados ao jogo em JSON
     *
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    @Override
    public void exportarRotasParaJson() throws IOException
    {
        ImportarExportarJson.exportarParaFicheiroJSON(getRotasArrayJSON().toJSONString(), "Rotas");
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

        raiz.put("jogadores", getJogadoresArrayJSON());
        raiz.put("bots", getBotsArrayJSON());
        raiz.put("rotas", getRotasArrayJSON());
        raiz.put("bandeiras", getBandeirasArrayJSON());
        raiz.put("localizacoes", getLocalizacoesArrayJSON());
        raiz.put("mapas", getMapasArrayJSON());

        dataManager.getiEJson().exportarParaFicheiroJSON(raiz.toJSONString(), "Raiz");
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

        System.out.println(rotasArray);

        return rotasArray;
    }


    /**
     * transforma a rota (entre 2 locais do grafo) enviados por parametro em JSONObject
     * @param rota
     * @return o JSONObject da rota
     */
    private JSONObject rotaParaObjetoJSON(IRota<ILocal> rota)
    {
        JSONObject rotaObject = new JSONObject();

        rotaObject.put("de", rota.getDe().getId());
        rotaObject.put("para", rota.getPara().getId());
        rotaObject.put("distancia", rota.getPeso());

        return rotaObject;
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
        Iterator<ILocalizacao> iteratorLocalizacao = routeNetwork.getLocalizacoes();

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
            jogadoresArray.add(iteratorJogador.next().jogadorParaObjetoJson());
        }

        return jogadoresArray;
    }

    private JSONArray getMapasArrayJSON()
    {
        JSONArray mapasArray = new JSONArray();
        //Iterator<IMapa> iteratorMapa = this.mapas.iterator();

       // while (iteratorMapa.hasNext())
        //{
            mapasArray.add(getMapaObjetoJSON());
       // }

        return mapasArray;
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
     * retornar o mapa
     * @return
     */
    private JSONObject getMapaObjetoJSON()
    {
        JSONObject mapaObject = new JSONObject();

        mapaObject.put("qttLocExistentes", locExistentes);
        mapaObject.put("tipoCaminho", tipoCaminhoString);
        mapaObject.put("densidadeArestas", densidadeArestas);

        return mapaObject;
    }
}
