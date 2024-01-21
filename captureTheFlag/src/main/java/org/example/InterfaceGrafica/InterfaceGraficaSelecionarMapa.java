package org.example.InterfaceGrafica;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.geometry.Pos;

public class InterfaceGraficaSelecionarMapa extends Application {

    protected DataManager dataManager;

    public InterfaceGraficaSelecionarMapa(DataManager dm) {
        dataManager = dm;
    }

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        Button criarMapa = new Button("Criar Mapa");

        Button importarMapa = new Button("Importar Mapa");

        Button sair = new Button("Sair");

        // Se o botão criar mapa é premido, avança para a jeanela de introdução dos
        // dados
        criarMapa.setOnAction(event -> {
            stage.close();
            Platform.runLater(() -> new InterfaceGraficaInputJogadores(this.dataManager).start(new Stage()));
        });

        // Se o botao importar mapa é premido, usa a função de importação do mapa
        importarMapa.setOnAction(event -> {
            stage.close();
            try {
                DataManager.iEJson.importarDoFicheiroJSON(dataManager.raiz, dataManager.local,
                        "docs/import/import.json");
            } catch (IOException e) {

                e.printStackTrace();
            } catch (ParseException e) {

                e.printStackTrace();
            }
        });

        // Se o botão sair é premido, fecha a aplicaçao
        sair.setOnAction(event -> {
            stage.close();
            Platform.exit();
        });

        // Adicionar os botões á janela
        root.getChildren().addAll(criarMapa, importarMapa, sair);

        Scene scene = new Scene(root, 400, 200);
        stage.setTitle("Capturar a Bandeira");
        stage.setScene(scene);
        stage.show();
    }
}
