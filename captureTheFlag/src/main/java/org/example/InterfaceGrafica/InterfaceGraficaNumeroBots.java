package org.example.InterfaceGrafica;

import org.example.Demo;
import org.example.api.exceptions.NotLocalInstanceException;
import org.example.api.implementation.Mapa;
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

import java.text.ParseException;

import static org.example.Demo.iniciarPartida;

public class InterfaceGraficaNumeroBots extends Application {

    private ComboBox<Integer> comboBox1, comboBox2;
    private Label playerLabel;
    private int botsJogador1, botsJogador2, maxBots, numeroBots;

    protected DataManager dataManager;

    public InterfaceGraficaNumeroBots(DataManager dManager) {
        this.dataManager = dManager;
        this.botsJogador1 = 0;
        this.botsJogador2 = 0;
        this.maxBots = Mapa.obterMaxBots();
        this.numeroBots = 0;
    }

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        root.getChildren().add(formularioInput(1));

        Button nextButton = new Button("Proximo");

        Button okButton = new Button("Ok");

        nextButton.setOnAction(event -> {
            this.logicaBotaoNext(root, okButton);
        });

        okButton.setOnAction(event -> {
            try {
                this.logicaBotaoOk(stage);
            } catch (NotLocalInstanceException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        root.getChildren().add(nextButton);

        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Escolha do Numero de Bots");
        stage.setScene(scene);
        stage.show();
    }

    private VBox formularioInput(int playerNumber) {

        VBox form = new VBox(5); // Espaçamento entre elementos
        form.setAlignment(Pos.CENTER);

        playerLabel = new Label("Jogador " + playerNumber);

        if (playerNumber == 1) {
            comboBox1 = new ComboBox<>();
            this.preencherComboBox(this.comboBox1);
            form.getChildren().addAll(playerLabel, comboBox1);
        }

        else {
            comboBox2 = new ComboBox<>();
            this.preencherComboBox(this.comboBox2);
            form.getChildren().addAll(playerLabel, comboBox2);
        }

        return form;
    }

    private void logicaBotaoNext(VBox root, Button okButton) {

        this.botsJogador1 = comboBox1.getValue();

        // Verificar se o numero foi realmente selecionado
        if (botsJogador1 == 0 || comboBox1.getValue() == null) {
            // Mostrar um alerta ou lidar com a situação de nenhuma seleção
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleção Incompleta");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione o numero de bots.");
            alert.showAndWait();
            return;
        }

        root.getChildren().clear();
        root.getChildren().add(formularioInput(2));
        root.getChildren().add(okButton);

    }

    private void logicaBotaoOk(Stage stage) throws NotLocalInstanceException, ParseException {

        this.botsJogador2 = comboBox2.getValue();

        // Verificar se o numero foi realmente selecionado
        if (botsJogador2 == 0) {
            // Mostrar um alerta ou lidar com a situação de nenhuma seleção
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleção Incompleta");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione o numero de bots.");
            alert.showAndWait();
            return;
        }

        this.calcularNumeroBots();

        Platform.runLater(
                () -> new InterfaceGraficaAlgoritmoBots(this.dataManager, this.numeroBots).start(new Stage()));
        stage.close();
        // PROXIMA JANELAAAAAAAAAAA
        Demo.iniciarPartida();
    }

    private void preencherComboBox(ComboBox<Integer> comboBox) {
        for (int i = 3; i <= maxBots; i++) {
            comboBox.getItems().add(i);
        }
    }

    private void calcularNumeroBots() {

        // fazer media de bots
        if (botsJogador1 > botsJogador2) {
            this.numeroBots = Mapa.fazerMedia(botsJogador2, botsJogador1);

            // Garante que o resultado é um valor inteiro e par, mas não maior que maxBots
            if (this.numeroBots % 2 != 0) {
                this.numeroBots--;
            }
        }

        else if (botsJogador2 > botsJogador1) {
            this.numeroBots = Mapa.fazerMedia(botsJogador1, botsJogador2);

            // Garante que o resultado é um valor inteiro e par, mas não maior que maxBots
            if (this.numeroBots % 2 != 0) {
                this.numeroBots--;
            }
        }

        else {
            this.numeroBots = botsJogador1;

            // Garante que o resultado é um valor inteiro e par, mas não maior que maxBots
            if (this.numeroBots % 2 != 0) {
                this.numeroBots--;
            }
        }

    }

}
