package org.example.InterfaceGrafica;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class InterfaceInputJogadores extends Application {

    private VBox formularioInput(int playerNumber) {

        VBox form = new VBox(5); // Espaçamento entre elementos

        // Numero de localizações
        Label playerLabel = new Label("Jogador " + playerNumber);
        Label locationLabel = new Label("Número de Localizações:");
        TextField locationTextField = new TextField();
        locationTextField.setPromptText("Insira um número >= 6");

        // Tipo de Campo
        Label directionLabel = new Label("Tipo de Campo:");
        ToggleGroup directionToggleGroup = new ToggleGroup();
        RadioButton directedRadioButton = new RadioButton("Direcionado");
        directedRadioButton.setToggleGroup(directionToggleGroup);
        RadioButton bidirectedRadioButton = new RadioButton("Bidirecionado");
        bidirectedRadioButton.setToggleGroup(directionToggleGroup);

        // Numero de bots
        Label botsLabel = new Label("Número de Bots:");
        TextField botsTextField = new TextField();

        form.getChildren().addAll(playerLabel, locationLabel, locationTextField, directionLabel, directedRadioButton,
                bidirectedRadioButton, botsLabel, botsTextField);

        return form;
    }

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(10); // Espaçamento entre elementos

        Button playButton = new Button("Jogar");

        playButton.setOnAction(event -> {
            VBox playerForms = new VBox(10);
            for (int i = 1; i <= 2; i++) {
                playerForms.getChildren().add(formularioInput(i));
            }
            root.getChildren().add(playerForms);
        });

        Button exitButton = new Button("Sair");
        exitButton.setOnAction(event -> Platform.exit());

        root.getChildren().addAll(playButton, exitButton);

        Scene scene = new Scene(root, 400, 500);
        stage.setTitle("Capture the Flag Game");
        stage.setScene(scene);
        stage.show();
    }

}