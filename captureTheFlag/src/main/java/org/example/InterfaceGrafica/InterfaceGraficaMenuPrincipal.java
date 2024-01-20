package org.example.InterfaceGrafica;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.geometry.Pos;

public class InterfaceGraficaMenuPrincipal extends Application {

    protected static DataManager dataManager;

    public static void setDataManager(DataManager dm) {
        dataManager = dm;
    }

    @Override
    public void start(Stage primaryStage) {

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        Button jogar = new Button("Jogar");

        Button sair = new Button("Sair");

        // Se o botão jogar é premido, retorna true á classe principal
        jogar.setOnAction(event -> {
            primaryStage.close();
            Platform.runLater(() -> new InterfaceGraficaSelecionarMapa(dataManager).start(new Stage()));
        });

        // Se o botão sair é premido, retorna false
        sair.setOnAction(event -> {
            primaryStage.close();
            Platform.exit();
        });

        // Adicionar os botões á janela
        root.getChildren().addAll(jogar, sair);

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Capture the Flag Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
