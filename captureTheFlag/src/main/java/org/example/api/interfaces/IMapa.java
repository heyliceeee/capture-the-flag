package org.example.api.interfaces;

import org.example.api.implementation.RouteNetwork;

public interface IMapa
{
    //GERAR MAPA

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
