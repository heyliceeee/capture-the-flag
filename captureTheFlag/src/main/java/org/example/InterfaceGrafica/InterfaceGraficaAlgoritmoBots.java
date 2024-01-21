package org.example.InterfaceGrafica;

import java.util.Iterator;

import org.example.api.implementation.Bot;
import org.example.api.interfaces.IBot;
import org.example.api.interfaces.IJogador;
import org.example.collections.implementation.DoubleLinkedUnorderedList;
import org.example.collections.implementation.LinkedQueue;
import org.example.collections.interfaces.UnorderedListADT;

import javafx.application.Application;
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
    private int numeroBots;
    private VBox root;
    private DoubleLinkedUnorderedList<ComboBox<String>> comboBoxJogador1;
    private DoubleLinkedUnorderedList<ComboBox<String>> comboBoxJogador2;

    public InterfaceGraficaAlgoritmoBots(DataManager dManager, int numeroBots) {
        this.dataManager = dManager;
        this.numeroBots = numeroBots;
        comboBoxJogador1 = new DoubleLinkedUnorderedList<ComboBox<String>>();
        comboBoxJogador2 = new DoubleLinkedUnorderedList<ComboBox<String>>();
    }

    @Override
    public void start(Stage stage) {

        root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        // Adicionar ComboBoxes e Labels para o Jogador 1
        for (int i = 1; i <= numeroBots / 2; i++) {
            root.getChildren().add(formularioBot(i, "Jogador 1", comboBoxJogador1));
        }

        Button proximoButton = new Button("Proximo");

        Button okButton = new Button("Ok");

        proximoButton.setOnAction(event -> {
            this.logicaBotaoNext(root, okButton);
        });

        okButton.setOnAction(event -> {
            this.logicaBotaoOk(stage);
        });

        root.getChildren().add(proximoButton);

        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Escolha do Algoritmo dos Bots");
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarFormularioJogador2() {
        // Adicionar ComboBoxes e Labels para o Jogador 2
        for (int i = 1; i <= numeroBots / 2; i++) {
            root.getChildren().add(formularioBot(i, "Jogador 2", comboBoxJogador2));
        }
    }

    private void logicaBotaoNext(VBox root, Button okButton) {

        // Verificar se os campos foram preenchidos
        if (!this.todosCamposPreenchidos(comboBoxJogador1)) {
            // Mostrar um alerta ou lidar com a situação de falhas na seleção
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleção Incorreta");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione corretamente os algoritmos para bots. (Minimo um de cada)");
            alert.showAndWait();
            return;
        }

        this.criarBots(dataManager.jogador1.getNome(), comboBoxJogador1, dataManager.jogador1);
        root.getChildren().clear();
        this.mostrarFormularioJogador2();
        root.getChildren().add(okButton);

    }

    private void logicaBotaoOk(Stage stage) {

        // Verificar se os campos foram preenchidos
        if (!this.todosCamposPreenchidos(comboBoxJogador2)) {
            // Mostrar um alerta ou lidar com a situação de falhas na seleção
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleção Incorreta");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione corretamente os algoritmos para bots. (Minimo um de cada)");
            alert.showAndWait();
            return;
        }

        this.criarBots(dataManager.jogador2.getNome(), comboBoxJogador2, dataManager.jogador2);

        // PROXIMAAAAAA JANELLLAAAAAAAA
        stage.close();

    }

    private boolean todosCamposPreenchidos(DoubleLinkedUnorderedList<ComboBox<String>> comboBoxJogador) {

        boolean Dijkstra = false, BFS = false, DFS = false; // Os 3 algoritmos

        for (ComboBox<String> comboBox : comboBoxJogador) {

            if (comboBox.getValue() == null || comboBox.getValue().isEmpty()) {
                return false; // Caixa Vazia, tem de selecionar um algoritmo para todos os bots
            }

            else {
                if (comboBox.getValue().contains("Dijkstra")) {
                    Dijkstra = true;
                }

                if (comboBox.getValue().contains("BFS")) {
                    BFS = true;
                }

                if (comboBox.getValue().contains("DFS")) {
                    DFS = true;
                }

            }
        }

        if ((comboBoxJogador.size() + 1) <= 1 && (Dijkstra || BFS || DFS))
            return true; // Apenas um bot por jogador

        else if ((comboBoxJogador.size() + 1) == 2) {
            if ((BFS && Dijkstra) || (BFS && DFS) || (Dijkstra && DFS))
                return true; // 2 algoritmos diferentes em 2
            else
                return false; // Em 2, não existem 2 algoritmos diferentes
        }

        else { // 3 ou mais bots por jogador
            if (!BFS || !Dijkstra || !DFS)
                return false; // Nao foram escolhidos os 3 algoritmsos
            else
                return true;
        }

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

    private void criarBots(String nomeJogador, DoubleLinkedUnorderedList<ComboBox<String>> comboBoxes,
            IJogador jogador) {

        for (int i = 0; i < comboBoxes.size(); i++) {
            String nomeBot = "Bot " + (i + 1) + " - " + nomeJogador;
            String algoritmo = comboBoxes.removeFirst().getValue();
            IBot bot = new Bot(nomeBot, nomeJogador, jogador.getBandeira().getCoordenadas(), algoritmo);
            jogador.getBotsJogador().add(bot);
        }

        jogador.setNumeroBots(comboBoxes.size());
    }

}
