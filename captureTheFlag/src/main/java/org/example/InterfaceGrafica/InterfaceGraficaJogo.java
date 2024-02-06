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

        for (ILocalizacao localizacao : iloc)
        {
            if (graph.getNode(""+localizacao.getId()) == null)
            {
                Node n = graph.addNode(""+localizacao.getId());
                n.addAttribute("ui.class", "localizacao");
            }
        }

        for (IBandeira bandeira : iban) {
            if (graph.getNode("" + bandeira.getId()) == null)
            {
                Node n = graph.addNode("" + bandeira.getId());
                n.addAttribute("ui.class", "bandeira");
            }
        }
    }

     private void desenharArestas(DataManager dmanager) {

        Iterator<IRota<ILocal>> ir = dataManager.grafo.getRotas();

        while (ir.hasNext())
        {
            IRota<ILocal> rota = ir.next();

            String idOrigem = String.valueOf(rota.getDe());
            String idDestino = String.valueOf(rota.getPara());
            String idAresta = rota.getDe() + " - " + rota.getPara();

            boolean isDirecional = dmanager.grafo.getTipoDirection().contains("direcionado");

            if(graph.getEdge(idAresta) == null)
            {
                graph.addEdge(idAresta, idOrigem, idDestino, isDirecional);
            }
        }
     }

    private void atualizarBots(Graph graph) {

        Node node = null;

        for (IBot bot1 : dataManager.jogador1.getBotsJogador())
        {
            node = graph.getNode(this.getLocalizacaoBot(bot1));

            if (graph.getNode(this.getLocalizacaoBot(bot1)) != null)
                node.setAttribute("ui.label", bot1.getNome());
        }

        for (IBot bot2 : dataManager.jogador2.getBotsJogador())
        {
            node = graph.getNode(this.getLocalizacaoBot(bot2));

            if (node != null)
                node.setAttribute("ui.label", bot2.getNome());
        }
    }

    private String styleSheet() {
        return "node { " +
                "   fill-color: red; " +
                "   text-alignment: at-right; " +
                "   text-size: 12px; " +
                "   text-color: white; " +
                "}" +
                "node.localizacao { " +
                "   fill-color: red; " +
                "}" +
                "node.bandeira { " +
                "   fill-color: blue; " +
                "}" +
                "edge { " +
                "   fill-color: grey; " +
                "   size: 2px; " +
                "}";
    }

    private String getLocalizacaoBot(IBot bot) {

        String id = null;
        ILocalizacao localizacao = null;
        IBandeira bandeira = null;

        ArrayOrderedList<ILocalizacao> il = dataManager.raiz.getListaLocalizacoes();
        ArrayOrderedList<IBandeira> ib = dataManager.raiz.getListaBandeiras();


        for (ILocalizacao loc : il)
        {
            if (bot.getCoordenada().equals(loc.getCoordenadas()))
                id = String.valueOf(loc.getId());
        }


        for (IBandeira ban : ib)
        {
            if (bot.getCoordenada().equals(ban.getCoordenadas()))
                id = String.valueOf(ban.getId());
        }

        return id;
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
