package org.example.InterfaceGrafica;

import java.text.ParseException;
import java.util.Iterator;

import org.example.api.exceptions.NotLocalInstanceException;
import org.example.api.implementation.Jogo;
import org.example.api.interfaces.*;
import org.example.collections.implementation.ArrayOrderedList;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import javafx.application.Application;
import javafx.stage.Stage;

public class InterfaceGraficaJogo extends Application {

    private DataManager dataManager;
    private Graph graph;

    public InterfaceGraficaJogo(DataManager dataManager, int quemComeca) {
        this.dataManager = dataManager;
    }

    @Override
    public void start(Stage stage) throws NotLocalInstanceException, ParseException {

        graph = new SingleGraph("Jogo");

        graph.setAttribute("ui.stylesheet", styleSheet());

        graph = new SingleGraph("Jogo");
        graph.setAttribute("ui.stylesheet", styleSheet());

        desenharJanela();
        Jogo.partida(this, dataManager.mapa.gerarNumeroRandom(1, 2), dataManager.jogador1,
                dataManager.jogador2, dataManager.grafo, dataManager.raiz, dataManager.rota);
        graph.display();
    }

    public void desenharJanela() {
        adicionarNosLocalizacaoEBandeira();
        desenharArestas(dataManager);
        atualizarBots(graph);
    }

    private void adicionarNosLocalizacaoEBandeira() {

        ArrayOrderedList<ILocalizacao> iloc = dataManager.raiz.getListaLocalizacoes();
        ArrayOrderedList<IBandeira> iban = dataManager.raiz.getListaBandeiras();

        //Iterator<ILocalizacao> iloc = dataManager.grafo.getLocalizacoes();
        //Iterator<IBandeira> iban = dataManager.grafo.getBandeiras();

        for (ILocalizacao localizacao : iloc)
        {
            //ILocalizacao localizacao = iloc.next();
            if (graph.getNode(localizacao.getNome()) == null)
                graph.addNode(localizacao.getNome());

            System.out.println("Nó adicionado: " + localizacao.getNome());
        }

        for (IBandeira bandeira : iban)
        {
            //IBandeira bandeira = iban.next();
            if (graph.getNode(bandeira.getNome()) == null)
                graph.addNode(bandeira.getNome());

            System.out.println("Nó adicionado: " + bandeira.getNome());
        }
    }

     private void desenharArestas(DataManager dmanager) {

        Iterator<IRota<ILocal>> ir = dataManager.grafo.getRotas();

        while (ir.hasNext())
        {
            IRota<ILocal> rota = ir.next();

            System.out.println(rota);

            String idOrigem = String.valueOf(rota.getDe());
            String idDestino = String.valueOf(rota.getPara());
            String idAresta = rota.getDe() + " - " + rota.getPara();

            boolean isDirecional = dmanager.grafo.getTipoDirection().contains("direcionado");

            if(graph.getNode(idOrigem) == null)
            {
                graph.addNode(idOrigem);
            }

            if(graph.getNode(idDestino) == null)
            {
                graph.addNode(idDestino);
            }

            if(graph.getEdge(idAresta) == null)
            {
                graph.addEdge(idAresta, idOrigem, idDestino, isDirecional);
            }
        }
     }

    private void atualizarBots(Graph graph) {

        Node node = null;
        String name = "";

        for (IBot bot : dataManager.jogador1.getBotsJogador()) {
            node = graph.getNode(this.getLocalizacaoBot(bot));
            if (node != null)
                node.setAttribute("ui.label", bot.getNome());
        }

        for (IBot bot : dataManager.jogador2.getBotsJogador()) {
            node = graph.getNode(this.getLocalizacaoBot(bot));
            if (node != null)
                node.setAttribute("ui.label", bot.getNome());
        }

    }

    private String styleSheet() {
        return "node { " +
                "fill-color: red; " +
                "text-alignment: at-right; " +
                "text-size: 12px; " +
                "text-color: white; " +
                "}" +
                "edge { " +
                "fill-color: grey; " +
                "size: 2px; " +
                "}";
    }

    private String getLocalizacaoBot(IBot bot) {

        String name = null;
        ILocalizacao localizacao = null;
        IBandeira bandeira = null;

        Iterator<ILocalizacao> il = dataManager.grafo.getLocalizacoes();
        Iterator<IBandeira> ib = dataManager.grafo.getBandeiras();

        while (il.hasNext()) {
            localizacao = il.next();
            if (bot.getCoordenada().equals(localizacao.getCoordenadas()))
                name = localizacao.getNome();

        }

        while (ib.hasNext()) {
            bandeira = ib.next();
            if (bot.getCoordenada().equals(bandeira.getCoordenadas()))
                name = bandeira.getNome();
        }

        return name;
    }

    private String getNomeLocalizacao(int id) {

        String name = null;
        ILocalizacao localizacao = null;
        IBandeira bandeira = null;

        Iterator<ILocalizacao> il = dataManager.grafo.getLocalizacoes();
        Iterator<IBandeira> ib = dataManager.grafo.getBandeiras();

        while (il.hasNext()) {
            localizacao = il.next();
            if (localizacao.getId() == id)
                name = localizacao.getNome();

        }

        while (ib.hasNext()) {
            bandeira = ib.next();
            if (bandeira.getId() == id)
                name = bandeira.getNome();

        }

        return name;
    }

}
