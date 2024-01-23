package org.example.InterfaceGrafica;

import java.util.Iterator;

import org.example.api.interfaces.IBandeira;
import org.example.api.interfaces.IBot;
import org.example.api.interfaces.ILocal;
import org.example.api.interfaces.ILocalizacao;
import org.example.api.interfaces.IRota;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import javafx.application.Application;
import javafx.stage.Stage;

public class InterfaceGraficaJogo extends Application {

    private DataManager dataManager;
    private Graph graph;

    public InterfaceGraficaJogo(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void start(Stage stage) {

        graph = new SingleGraph("Jogo");

        graph.setAttribute("ui.stylesheet", styleSheet());

        Iterator<ILocalizacao> iloc = dataManager.grafo.getLocalizacoes();
        Iterator<IBandeira> iban = dataManager.grafo.getBandeiras();

        // Adicionar nós localização baseados em DataManager
        while (iloc.hasNext()) {
            ILocalizacao localizacao = iloc.next();
            Node node = graph.addNode(localizacao.getNome());
            // Adicionar arestas (rotas)...
        }

        // Adicionar nós localização baseados em DataManager
        while (iban.hasNext()) {
            IBandeira bandeira = iban.next();
            Node node = graph.addNode(bandeira.getNome());
            // Adicionar arestas (rotas)...
        }

        // Atualizar posições dos bots...
        // atualizarBots(graph);

        graph.display();
    }

    private void desenharArestas(DataManager dmanager) {

        Iterator<IRota<ILocal>> ir = dataManager.grafo.getRotas();

        while (ir.hasNext()) {

            IRota<ILocal> rota = ir.next();

            String idOrigem = (rota.getDe() instanceof ILocalizacao) ? ((ILocalizacao) rota.getDe()).getNome()
                    : ((IBandeira) rota.getDe()).getNome();

            String idDestino = (rota.getPara() instanceof ILocalizacao) ? ((ILocalizacao) rota.getPara()).getNome()
                    : ((IBandeira) rota.getPara()).getNome();

            String idAresta = idOrigem + " - " + idDestino;

            boolean isDirecional = dmanager.grafo.getTipoDirection().contains("direcionado");

            graph.addEdge(idAresta, idOrigem, idDestino, isDirecional);
        }

    }

    private String styleSheet() {
        return "node { " +
                "fill-color: black; " +
                "text-alignment: at-right; " +
                "text-size: 12px; " +
                "text-color: white; " +
                "}" +
                "edge { " +
                "fill-color: grey; " +
                "size: 2px; " +
                "}";
    }

    private String getLocalizacaoBot(IBot bot, DataManager dmanager) {

        return "";
    }

}
