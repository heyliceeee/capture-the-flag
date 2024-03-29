package org.example.InterfaceGrafica;

import java.io.IOException;
import java.text.ParseException;

import org.example.Demo;
import org.example.api.exceptions.NotLocalInstanceException;
import org.example.api.implementation.Bot;
import org.example.api.implementation.Mapa;
import org.example.api.interfaces.IBot;
import org.example.api.interfaces.IJogador;
import org.example.collections.implementation.DoubleLinkedUnorderedList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InterfaceGraficaAlgoritmoBots extends Application {

    private DataManager dataManager;
    private int numeroBots, quemComeca;
    private VBox root;
    private DoubleLinkedUnorderedList<ComboBox<String>> comboBoxJogador1;
    private DoubleLinkedUnorderedList<ComboBox<String>> comboBoxJogador2;

    public InterfaceGraficaAlgoritmoBots(DataManager dManager, int numeroBots) {
        this.dataManager = dManager;
        this.numeroBots = numeroBots;
        comboBoxJogador1 = new DoubleLinkedUnorderedList<ComboBox<String>>();
        comboBoxJogador2 = new DoubleLinkedUnorderedList<ComboBox<String>>();
        this.quemComeca = Mapa.gerarNumeroRandom(1, 2);
    }

    @Override
    public void start(Stage stage) {

        root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        // Adicionar ComboBoxes e Labels para o Jogador 1
        for (int i = 1; i <= (numeroBots / 2); i++) {
            root.getChildren().add(formularioBot(i, "Jogador 1", comboBoxJogador1));
        }

        Button proximoButton = new Button("Proximo");

        Button okButton = new Button("Ok");

        Button iniciarButton = new Button("Iniciar Partida");

        proximoButton.setOnAction(event -> {
            this.logicaBotaoNext(okButton);
        });

        okButton.setOnAction(event -> {
            try {
                this.logicaBotaoOk(iniciarButton);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        iniciarButton.setOnAction(event -> {
            try {
                this.logicaIniciarButton(stage);
            } catch (NotLocalInstanceException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        root.getChildren().add(proximoButton);

        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Escolha do Algoritmo dos Bots");
        stage.setScene(scene);
        stage.show();
    }

    private VBox formularioBot(int botNum, String jogador, DoubleLinkedUnorderedList<ComboBox<String>> comboBoxes) {

        VBox form = new VBox(5);
        form.setAlignment(Pos.CENTER);
        Label label = new Label("Para o bot nr " + botNum + " (" + jogador + ")");
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Dijkstra", "BFS", "DFS");
        comboBoxes.addToRear(comboBox);
        form.getChildren().addAll(label, comboBox);
        return form;

    }

    private void mostrarFormularioJogador2() {
        // Adicionar ComboBoxes e Labels para o Jogador 2
        for (int i = 1; i <= (numeroBots / 2); i++) {
            root.getChildren().add(formularioBot(i, "Jogador 2", comboBoxJogador2));
        }
    }

    private void mostrarPrimeiroJogador() {

        Label primeiro = new Label("Primeiro a jogar: " + this.quemComeca);
        root.getChildren().add(primeiro);

    }

    private void logicaBotaoNext(Button okButton) {

        // Verificar se os campos foram preenchidos
        if (!this.verificarAlgoritmosEscolhidos(comboBoxJogador1)) {
            // Mostrar um alerta ou lidar com a situação de falhas na seleção
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleção Incorreta");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione corretamente os algoritmos para bots. (Minimo um de cada)");
            alert.showAndWait();
            return;
        }

        this.criarBots(dataManager.jogador1.getNome(), comboBoxJogador1, dataManager.jogador1);

        for (IBot bot : dataManager.jogador1.getBotsJogador()) {
            dataManager.raiz.adicionarBot(bot);
        }

        dataManager.raiz.adicionarJogador(dataManager.jogador1);

        root.getChildren().clear();

        this.mostrarFormularioJogador2();
        root.getChildren().add(okButton);

    }

    private void logicaBotaoOk(Button iniciarButton) throws IOException {

        // Verificar se os campos foram preenchidos
        if (!this.verificarAlgoritmosEscolhidos(comboBoxJogador2)) {
            // Mostrar um alerta ou lidar com a situação de falhas na seleção
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleção Incorreta");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione corretamente os algoritmos para bots. (Minimo um de cada)");
            alert.showAndWait();
            return;
        }

        this.criarBots(dataManager.jogador2.getNome(), comboBoxJogador2, dataManager.jogador2);

        // Demo.raiz.adicionarJogador(dataManager.jogador2);
        // Demo.raiz.exportarRaizParaJson();

        root.getChildren().clear();
        this.mostrarPrimeiroJogador();
        root.getChildren().add(iniciarButton);
    }

    private void logicaIniciarButton(Stage stage) throws NotLocalInstanceException, ParseException {

        Platform.runLater(
                () -> {
                    try {
                        new InterfaceGraficaJogo(this.dataManager, this.quemComeca).start(new Stage());
                    } catch (NotLocalInstanceException | ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                });
        stage.close();

        //Demo.iniciarPartida(dataManager.jogador1, dataManager.jogador2, dataManager.grafo, dataManager.raiz, dataManager.rota);
    }

    private boolean verificarAlgoritmosEscolhidos(DoubleLinkedUnorderedList<ComboBox<String>> comboBoxJogador) {

        int dijkstra = 0, bfs = 0, dfs = 0; // Os 3 algoritmos

        for (ComboBox<String> comboBox : comboBoxJogador) {

            if (comboBox.getValue() == null || comboBox.getValue().isEmpty()) {
                return false; // Caixa Vazia, tem de selecionar um algoritmo para todos os bots
            }

            else {
                if (comboBox.getValue().contains("Dijkstra")) {
                    dijkstra++;
                }

                if (comboBox.getValue().contains("BFS")) {
                    bfs++;
                }

                if (comboBox.getValue().contains("DFS")) {
                    dfs++;
                }

            }
        }

        return this.testaNumeroAlgoritmosEscolhidos(dijkstra, bfs, dfs);

    }

    private boolean testaNumeroAlgoritmosEscolhidos(int a, int b, int c) {

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        if (a < min)
            min = a;
        if (a > max)
            max = a;

        if (b < min)
            min = b;
        if (b > max)
            max = b;

        if (c < min)
            min = c;
        if (c > max)
            max = c;

        if ((max - min) > 1)
            return false;
        else
            return true;

    }

    private void criarBots(String nomeJogador, DoubleLinkedUnorderedList<ComboBox<String>> comboBoxes,
            IJogador jogador) {
        int size = comboBoxes.size();

        for (int i = 0; i < size; i++) {
            String nomeBot = "Bot " + (i + 1) + " - " + nomeJogador;
            String algoritmo = comboBoxes.removeFirst().getValue();
            IBot bot = new Bot(nomeBot, nomeJogador, jogador.getBandeira().getCoordenadas(), algoritmo);
            jogador.getBotsJogador().add(bot);

            dataManager.raiz.adicionarBot(bot);
        }

        jogador.setNumeroBots(size);
    }

}
