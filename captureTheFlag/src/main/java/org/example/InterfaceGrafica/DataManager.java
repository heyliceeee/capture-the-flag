package org.example.InterfaceGrafica;

import org.example.api.implementation.*;
import org.example.api.interfaces.IJogador;
import org.example.api.interfaces.ILocal;
import org.example.api.interfaces.IRaiz;
import org.example.api.interfaces.IRota;
import org.example.collections.implementation.ExporterGraph;
import org.example.collections.interfaces.IExporter;

public class DataManager {

    protected static ImportarExportarJson iEJson = new ImportarExportarJson();

    /**
     * Instância do exportar que transforma o grafo em uma imagem
     */
    protected static final IExporter exportar = new ExporterGraph("docs/export");

    /**
     * grafo que tem informação acerca dos locais e das rotas entre eles
     */
    protected RouteNetwork<ILocal> grafo = new RouteNetwork<>();

    protected IRaiz raiz;
    protected ILocal local;
    protected IJogador jogador1;
    protected IJogador jogador2;
    protected IRota rota;
    protected Mapa mapa;

    public DataManager() {
        this.mapa = new Mapa();
        this.raiz = new Raiz();
        this.local = new Local(0, "", null);
        this.jogador1 = new Jogador("Jogador 1", 0, null);
        this.jogador2 = new Jogador("Jogador 2", 0, null);
        this.rota = new Rota(null, null, 0);
    }


    public IRaiz getRaiz() {
        return raiz;
    }

    public void setRaiz(IRaiz raiz) {
        this.raiz = raiz;
    }


    public static ImportarExportarJson getiEJson() {
        return iEJson;
    }

    public static void setiEJson(ImportarExportarJson iEJson) {
        DataManager.iEJson = iEJson;
    }
}
