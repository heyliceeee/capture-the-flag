package org.example.InterfaceGrafica;

import static org.example.Demo.jogador2;

import java.text.ParseException;
import java.util.Iterator;

import org.example.api.exceptions.NotLocalInstanceException;
import org.example.api.implementation.Jogo;
import org.example.api.interfaces.IBandeira;
import org.example.api.interfaces.IBot;
import org.example.api.interfaces.ILocal;
import org.example.api.interfaces.ILocalizacao;
import org.example.api.interfaces.IRota;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class InterfaceGraficaJogo extends Application {

    private DataManager dataManager;
    private Graph graph;
    private int quemComeca;

    public InterfaceGraficaJogo(DataManager dataManager, int quemComeca) {
        this.dataManager = dataManager;
        this.quemComeca = quemComeca;
    }

    @Override
    public void start(Stage stage) throws NotLocalInstanceException, ParseException {

        graph = new SingleGraph("Jogo");
        graph.setAttribute("ui.stylesheet", styleSheet());

        desenharJanela();

        new Thread(() -> {
            try {
                Jogo.partida(this, quemComeca, dataManager.jogador1, dataManager.jogador2,
                        dataManager.grafo, dataManager.raiz, dataManager.rota);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        graph.display();
    }

    public void desenharJanela() {
        Platform.runLater(() -> {
            adicionarNosLocalizacaoEBandeira();
            atualizarBots(graph);
        });
    }

    public synchronized void atualizarInterface() {
        adicionarNosLocalizacaoEBandeira();
        atualizarBots(graph);
    }

    private void adicionarNosLocalizacaoEBandeira() {

        Iterator<ILocalizacao> iloc = dataManager.grafo.getLocalizacoes();
        Iterator<IBandeira> iban = dataManager.grafo.getBandeiras();

        while (iloc.hasNext()) {
            ILocalizacao localizacao = iloc.next();
            if (graph.getNode(localizacao.getNome()) == null)
                graph.addNode(localizacao.getNome());
            System.out.println("Nó adicionado: " + localizacao.getNome());
        }

        while (iban.hasNext()) {
            IBandeira bandeira = iban.next();
            if (graph.getNode(bandeira.getNome()) == null)
                graph.addNode(bandeira.getNome());
            System.out.println("Nó adicionado: " + bandeira.getNome());
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
