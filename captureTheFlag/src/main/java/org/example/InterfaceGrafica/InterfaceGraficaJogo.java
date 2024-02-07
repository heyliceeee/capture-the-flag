package org.example.InterfaceGrafica;

import java.text.ParseException;
import java.util.Iterator;

import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;
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
    public Graph graph;

    private Button btnProximoTurno;
    private int turnoAtual = 0;
    private boolean jogoEmAndamento = true;

    public InterfaceGraficaJogo(DataManager dataManager, int quemComeca) {
        this.dataManager = dataManager;
    }

    @Override
    public void start(Stage stage) throws NotLocalInstanceException, ParseException {

        graph = new SingleGraph("Jogo");
        graph.setAttribute("ui.stylesheet", styleSheet());

        graph = new SingleGraph("Jogo");
        graph.setAttribute("ui.stylesheet", styleSheet());

        //desenharJanela();

        Jogo.partida(this, dataManager.mapa.gerarNumeroRandom(1, 2), dataManager.jogador1, dataManager.jogador2, dataManager.grafo, dataManager.raiz, dataManager.rota);

        graph.display();
    }

    /**
     * desenhar a janela com as localizacoes/bandeiras (nodes), rotas (arestas) e as legendas
     */
    public void desenharJanela() {
        adicionarNosLocalizacaoEBandeira();
        desenharArestas(dataManager);
        atualizarBots(graph);
        adicionarLegenda();
    }

    /**
     * adicionar legenda ao mapa (localizacao ou bandeira)
     */
    private void adicionarLegenda()
    {
        // Defina a coordenada y comum para todos os nós de legenda.
        double yCoord = -1;  // Substitua com o valor de y desejado para alinhamento horizontal.

        // Cria um nó para a legenda de localização.

        if(graph.getNode("LegendaLocalizacao") == null)
        {
            Node nodeLocalizacao = graph.addNode("LegendaLocalizacao");
            nodeLocalizacao.addAttribute("ui.label", "Localizacao - Vermelho");
            nodeLocalizacao.addAttribute("ui.style", "fill-color: red; size: 15px;"); // Tamanho do nó para visibilidade na legenda.
            nodeLocalizacao.addAttribute("xy", 1, yCoord); // Posição x será 1, y é o mesmo para todos.
            nodeLocalizacao.addAttribute("ui.fixed", true);
        }


        // Cria um nó para a legenda de bandeira.
        if(graph.getNode("LegendaBandeira") == null)
        {
            Node nodeBandeira = graph.addNode("LegendaBandeira");
            nodeBandeira.addAttribute("ui.label", "Bandeira - Azul");
            nodeBandeira.addAttribute("ui.style", "fill-color: blue; size: 15px;");
            nodeBandeira.addAttribute("xy", 3, yCoord); // Posição x é incrementada para alinhar horizontalmente.
            nodeBandeira.addAttribute("ui.fixed", true);
        }
    }

    public void atualizarJanela()
    {
        //desenharJanela();
        //graph.display();
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
                n.addAttribute("ui.label", localizacao.getNome()); // Adicionando o rótulo
            }
        }

        for (IBandeira bandeira : iban) {
            if (graph.getNode("" + bandeira.getId()) == null)
            {
                Node n = graph.addNode("" + bandeira.getId());
                n.addAttribute("ui.class", "bandeira");
                n.addAttribute("ui.label", bandeira.getNome()); // Adicionando o rótulo
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
            String localizacaoId = getLocalizacaoBot(bot1);
            node = graph.getNode(localizacaoId);

            if (node != null)
            {
                String nomeLocalizacao = getNomeLocalizacao(Integer.parseInt(localizacaoId));

                // Atualizar os atributos do nó
                node.addAttribute("ui.label", bot1.getNome() + "/" + nomeLocalizacao);
                node.addAttribute("ui.class", "bot1"); // Atualizar a classe do nó se necessário
            }
        }

        for (IBot bot2 : dataManager.jogador2.getBotsJogador())
        {
            String localizacaoId = getLocalizacaoBot(bot2);
            node = graph.getNode(localizacaoId);

            if (node != null)
            {
                String nomeLocalizacao = getNomeLocalizacao(Integer.parseInt(localizacaoId));

                // Atualizar os atributos do nó
                node.addAttribute("ui.label", bot2.getNome() + "/" + nomeLocalizacao);
                node.addAttribute("ui.class", "bot2"); // Atualizar a classe do nó se necessário
            }
        }

        // Atualizar a visualização do GraphStream
        graph.addAttribute("ui.refresh"); // Isso força o GraphStream a atualizar a visualização
    }

    private String styleSheet() {
        return "node { " +
                "   text-alignment: at-right; " +
                "   text-size: 12px; " +
                "   text-color: black; " +
                "   text-background-mode: plain; " + // Isso pode ajudar a tornar o texto mais legível
                "   text-background-color: white; " + // Fundo do texto para contraste
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

        ArrayOrderedList<ILocalizacao> il = dataManager.raiz.getListaLocalizacoes();
        ArrayOrderedList<IBandeira> ib = dataManager.raiz.getListaBandeiras();

        for (ILocalizacao loc : il)
        {
            if (loc.getId() == id)
                name = loc.getNome();
        }

        for (IBandeira ban : ib)
        {
            if (ban.getId() == id)
                name = ban.getNome();
        }

        return name;
    }
}
