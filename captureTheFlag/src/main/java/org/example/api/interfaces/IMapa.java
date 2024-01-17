package org.example.api.interfaces;

public interface IMapa
{
    //GERAR MAPA

    /**
     * gera arestas no mapa com base na densidade de arestas dada
     * @param bidirecional true se o grafo for bidirecionado, caso contrário false
     * @param densidadeArestas percentagem de arestas que devem estar presentes no grafo
     */
    void gerarArestas(boolean bidirecional, double densidadeArestas);

    /**
     * gerar a distancia random entre 2 localizações (1km - 15km)
     * @param localizacaoA ponto/vertice A
     * @param localizacaoB ponto/vertice B
     * @return a distancia gerada
     */
    int obterDistancia(int localizacaoA, int localizacaoB);






    void exportarMapa();

    void importarMapa();
}
