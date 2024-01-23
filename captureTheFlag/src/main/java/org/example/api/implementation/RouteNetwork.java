package org.example.api.implementation;

import org.example.api.exceptions.NotLocalInstanceException;
import org.example.api.interfaces.*;
import org.example.collections.implementation.ArrayUnorderedList;
import org.example.collections.implementation.DoubleLinkedUnorderedList;
import org.example.collections.implementation.Network;
import org.example.collections.interfaces.UnorderedListADT;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 * classe que implementa o contracto de um grafo de rede de route
 *
 * @param <T> tipo de locais no grafo
 */
public class RouteNetwork<T> extends Network<T> implements RouteNetworkADT<T> {

    private String tipoDirection;

    /**
     * constructor
     */
    public RouteNetwork() {
        super();
        tipoDirection = "";
    }

    public String getTipoDirection() {
        return this.tipoDirection;
    }

    public void setTipoDirection(String direction) {
        this.tipoDirection = direction;
    }

    public int getNumVertices() {
        return super.numVertices;
    }

    public double[][] getAdjMatrix() {
        return super.adjMatrix;
    }

    /**
     * enumeração interna para identificar o tipo de local
     */
    private enum SEARCH_TYPE {
        FLAG_ANY, // indica uma pesquisa para qualquer bandeira inimiga mais próximo
        LOCATIONS_ANY, // indica uma pesquisa para qualquer localizacao (todos os sitios exceto as
                       // bandeiras) mais próximo
    }

    /**
     * Classe interna que contém as informações necessárias para suportar um
     * algoritmo dijkstra
     */
    private class DijkstraSupport {
        /**
         * array do node anterior mais próximo
         */
        private final int[] prev;

        /**
         * array das distâncias mais curtas para o ponto de partida
         */
        private final double[] distance;

        /**
         * array que diz se um node foi visitado ou não
         */
        private final boolean[] visited;

        /**
         * constructor
         *
         * @param numberOfNodes número de nodes
         */
        private DijkstraSupport(int numberOfNodes) {
            this.prev = new int[numberOfNodes];
            this.distance = new double[numberOfNodes];
            this.visited = new boolean[numberOfNodes];
        }
    }

    @Override
    public int getNumeroDeLocalizacoes() {
        return this.getNumeroDe(new Localizacao(0, "", "", null));
    }

    @Override
    public int getNumeroDeBandeiras() {
        return this.getNumeroDe(new Bandeira(0, "", "", null));
    }

    @Override
    public Iterator<ILocalizacao> getLocalizacoes() {
        UnorderedListADT<ILocalizacao> resultList = new ArrayUnorderedList<>();

        for (int i = 0; i < super.numVertices; i++) {
            if (super.vertices[i] instanceof ILocalizacao) {
                resultList.addToRear((ILocalizacao) super.vertices[i]); // adiciona no fim da lista
            }
        }

        return resultList.iterator();
    }

    @Override
    public Iterator<IBandeira> getBandeiras() {
        UnorderedListADT<IBandeira> resultList = new ArrayUnorderedList<>();

        for (int i = 0; i < super.numVertices; i++) {
            if (super.vertices[i] instanceof IBandeira) {
                resultList.addToRear((IBandeira) super.vertices[i]); // adiciona no fim da lista
            }
        }

        return resultList.iterator();
    }

    @Override
    public Iterator<IRota<ILocal>> getRotas() {
        UnorderedListADT<IRota<ILocal>> resultList = new ArrayUnorderedList<>();

        for (int i = 0; i < super.numVertices; i++) {
            for (int j = i; j < super.numVertices; j++) {
                if (super.adjMatrix[i][j] != 0) {
                    IRota<ILocal> route = new Rota(super.vertices[i], super.vertices[j], super.adjMatrix[i][j]);
                    resultList.addToRear(route);
                }

                if (super.adjMatrix[j][i] != 0) {
                    IRota<ILocal> route = new Rota(super.vertices[j], super.vertices[i], super.adjMatrix[j][i]);
                    resultList.addToRear(route);
                }
            }
        }

        return resultList.iterator();
    }

    /**
     * Rota mais curta do ponto de partida até a bandeira do inimigo do grafo
     *
     * @param raiz
     * @param pontoDePartida ponto de partida
     * @return interador com a rota
     * @throws NotLocalInstanceException se o ponto de partida não for uma instância
     *                                   {@link ILocal local}
     */
    @Override
    public Iterator<ILocal> caminhoMaisCurtoABandeira(IRaiz raiz, T pontoDePartida)
            throws ParseException, NotLocalInstanceException {
        DijkstraSupport ds = this.caminhoMaisCurtoDijkstra(pontoDePartida);

        return this.caminhoMaisCurtoPara(raiz, SEARCH_TYPE.FLAG_ANY, ds.prev, ds.distance, ds.visited, pontoDePartida);
    }

    /**
     * rota mais curta do local de ponta de partida até a outro local
     *
     * @param searchType     tipo de local a ser procurado
     * @param prev
     * @param distance
     * @param visited
     * @param pontoDePartida local de ponto de partida
     * @return iterador com a localização da rota
     */
    private Iterator<ILocal> caminhoMaisCurtoPara(IRaiz raiz, SEARCH_TYPE searchType, int[] prev, double[] distance,
            boolean[] visited, T pontoDePartida) throws NotLocalInstanceException {
        if (!(pontoDePartida instanceof ILocal)) {
            throw new NotLocalInstanceException("vertice precisa de ser instancia ILocal");
        }

        int minIndexPortal = -1;
        double minDistanceToPortal = Integer.MAX_VALUE;

        int minIndexConnector = -1;
        double minDistanceToConnector = Integer.MAX_VALUE;

        // verifica a distância mínima e atualiza o último indice do anterior
        for (int i = 0; i < distance.length; i++) {
            if (visited[i] && distance[i] != 0) // se foi visitado, e não é o mesmo que ponto de partida
            {
                if (super.vertices[i] instanceof IBandeira) // se for uma bandeira
                {
                    if (distance[i] < minDistanceToPortal) {
                        if (searchType == SEARCH_TYPE.FLAG_ANY) // se for qualquer banderia
                        {
                            minDistanceToPortal = distance[i];
                            minIndexPortal = i;
                        }
                    }
                }
            }
        }

        UnorderedListADT<ILocal> resultList = new DoubleLinkedUnorderedList<>();
        int currentIndex = -1;

        /**
         * após encontra todas as distâncias, faremos a travessia até o grafo, partindo
         * do local mais próximo e percorrendo o grafo até chegarmos ao ponto de partida
         */
        if (searchType == SEARCH_TYPE.FLAG_ANY) {
            currentIndex = minIndexPortal;
        }

        if (currentIndex == -1) // se não existe locals no grafo
        {
            return null;
        }

        while (prev[currentIndex] != -1) {
            resultList.addToFront((ILocal) this.vertices[currentIndex]);
            currentIndex = prev[currentIndex];
        }

        return resultList.iterator();
    }

    /**
     * Retorna toda a informação sobre o algoritmo de Dijkstra, obtem a rota mais
     * curta do ponto de partida até ao todo resto dos nodes
     *
     * @param pontoDePartida ponto de partida
     * @return toda a informação sobre o algoritmo de Dijkstra
     */
    private DijkstraSupport caminhoMaisCurtoDijkstra(T pontoDePartida) {
        if (pontoDePartida == null) {
            throw new IllegalArgumentException("ponto de partida nao pode ser nulo");
        }

        int verticeInicial = getIndex(pontoDePartida), currentIndex;

        if (verticeInicial == -1) {
            throw new IllegalArgumentException("ponto de partida nao existe no grafo");
        }

        DijkstraSupport ds = new DijkstraSupport(super.numVertices);

        for (int vertexIndex = 0; vertexIndex < super.numVertices; vertexIndex++) {
            ds.distance[vertexIndex] = Integer.MAX_VALUE;
            ds.visited[vertexIndex] = false;
        }

        ds.distance[verticeInicial] = 0; // a distância do primeiro node de si mesmo é 0
        ds.prev[verticeInicial] = -1; // o anterior do primeiro node é -1

        double tmpDistance, edgeDistance;

        for (int i = 0; i < super.numVertices; i++) {
            currentIndex = super.getDistanciaPequenaNode(ds.distance, ds.visited); // obter o indice do node com o peso
                                                                                   // baixo
            ds.visited[currentIndex] = true;

            for (int vertexIndex = 0; vertexIndex < super.numVertices; vertexIndex++) {
                edgeDistance = super.adjMatrix[currentIndex][vertexIndex]; // obter a distância entre o node proximo e
                                                                           // todos os outros nodes
                tmpDistance = ds.distance[currentIndex] + edgeDistance; // calcular a distancia

                if (edgeDistance > 0 && tmpDistance < ds.distance[vertexIndex]) {
                    ds.distance[vertexIndex] = tmpDistance;
                    ds.prev[vertexIndex] = currentIndex;
                }
            }
        }

        return ds;
    }

    /**
     * Conta o número de instâncias que existem desse tipo
     *
     * @param type objeto a ser comparado
     * @param <T>  tipo a ser comparado
     * @return número de vezes
     */
    private <T extends ILocal> int getNumeroDe(T type) {
        int count = 0;

        for (int i = 0; i < super.numVertices; i++) {
            if (super.vertices[i].getClass().isInstance(type)) {
                count++;
            }
        }

        return count;
    }
}
