package org.example.InterfaceGrafica;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.geometry.Pos;

public class MenuPrincipal extends Application {

    @Override
    public void start(Stage primaryStage) {

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        // Botao jogar, deve iniciar a interface de Inputs dos Jogadores
        Button jogar = new Button("Jogar");
        jogar.setOnAction(event -> new InterfaceInputJogadores().start(new Stage()));

        // Botão sair, encerro a app
        Button sair = new Button("Sair");
        sair.setOnAction(event -> Platform.exit());

        root.getChildren().addAll(jogar, sair);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Capture the Flag Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
