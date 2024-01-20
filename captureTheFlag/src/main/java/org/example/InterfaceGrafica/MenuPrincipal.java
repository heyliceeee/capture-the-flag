package org.example.InterfaceGrafica;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.application.Platform;

public class MenuPrincipal extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);

        Button playButton = new Button("Jogar");
        playButton.setOnAction(event -> new InterfaceInputJogadores().start(new Stage()));

        Button exitButton = new Button("Sair");
        exitButton.setOnAction(event -> Platform.exit());

        root.getChildren().addAll(playButton, exitButton);

        Scene scene = new Scene(root, 300, 100);
        primaryStage.setTitle("Capture the Flag Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
