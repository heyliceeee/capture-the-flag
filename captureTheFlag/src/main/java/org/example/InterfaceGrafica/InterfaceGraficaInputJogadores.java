package org.example.InterfaceGrafica;

import javafx.stage.Stage;

import org.example.api.implementation.Mapa;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class InterfaceGraficaInputJogadores extends Application {

    private Label playerLabel, locationLabel, directionLabel, densidadeLabel;
    private TextField locationTextField, densidadeTextField;
    private ToggleGroup directionToggleGroup;
    private RadioButton directedRadioButton, bidirectedRadioButton;

    int locExistentesJogador1, tipoCaminhoJogador1, densidadeArestasJogador1;

    int locExistentesJogador2, tipoCaminhoJogador2, densidadeArestasJogador2;

    protected DataManager dataManager;

    public InterfaceGraficaInputJogadores(DataManager dManager) {
        this.dataManager = dManager;
        this.locExistentesJogador1 = 0;
        this.locExistentesJogador2 = 0;
        this.tipoCaminhoJogador1 = 1;
        this.tipoCaminhoJogador2 = 1;
        this.densidadeArestasJogador1 = 1;
        this.densidadeArestasJogador2 = 1;
    }

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(10); // Espaçamento entre elementos

        // Adiciona o formulário do Jogador 1 inicialmente
        root.getChildren().add(formularioInput(1));

        Button nextButton = new Button("Proximo");

        Button okButton = new Button("Ok");

        nextButton.setOnAction(event -> {
            this.logicaBotaoNext(root, okButton);
        });

        okButton.setOnAction(event -> {
            this.logicaBotaoOk(stage);
        });

        root.getChildren().add(nextButton);

        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Capture the Flag Game");
        stage.setScene(scene);
        stage.show();
    }

    private VBox formularioInput(int playerNumber) {

        VBox form = new VBox(5); // Espaçamento entre elementos

        // Numero de localizações
        playerLabel = new Label("Jogador " + playerNumber);
        locationLabel = new Label("Numero de Localizaçoes:");
        locationTextField = new TextField();
        locationTextField.setPromptText("Insira um numero maior que 6");

        // Tipo de Campo
        directionLabel = new Label("Tipo de Campo:");
        directionToggleGroup = new ToggleGroup();
        directedRadioButton = new RadioButton("Direcionado");
        directedRadioButton.setToggleGroup(directionToggleGroup);
        bidirectedRadioButton = new RadioButton("Bidirecionado");
        bidirectedRadioButton.setToggleGroup(directionToggleGroup);

        // Numero de bots
        densidadeLabel = new Label("Densidade das arestas:");
        densidadeTextField = new TextField();
        densidadeTextField.setPromptText("Em % (1 - 100)");

        form.getChildren().addAll(playerLabel, locationLabel, locationTextField, directionLabel, directedRadioButton,
                bidirectedRadioButton, densidadeLabel, densidadeTextField);

        return form;
    }

    private void logicaBotaoNext(VBox root, Button okButton) {

        this.locExistentesJogador1 = Integer.parseInt(locationTextField.getText());
        this.densidadeArestasJogador1 = Integer.parseInt(densidadeTextField.getText());
        this.tipoCaminhoJogador1 = this.getTipoCaminho();

        if (locExistentesJogador1 > 6 && densidadeArestasJogador1 > 0 && densidadeArestasJogador1 <= 100) {
            root.getChildren().clear();
            root.getChildren().add(formularioInput(2));
            root.getChildren().add(okButton);
        }

        else {
            // Mostrar uma mensagem de erro ou uma indicação de que os valores inseridos são
            // inválidos
            // Por exemplo, você pode usar um Alert para isso
            Alert alert = new Alert(Alert.AlertType.ERROR, "Retificar numero de localizaçoes ou densidade");
            alert.showAndWait();
        }

    }

    private void logicaBotaoOk(Stage stage) {

        this.locExistentesJogador2 = Integer.parseInt(locationTextField.getText());
        this.densidadeArestasJogador2 = Integer.parseInt(densidadeTextField.getText());
        this.tipoCaminhoJogador2 = this.getTipoCaminho();

        if (locExistentesJogador2 > 6 && densidadeArestasJogador2 > 0 && densidadeArestasJogador2 <= 100) {

            Mapa.gerarMapa(this.dataManager.grafo, this.dataManager.raiz, this.dataManager.rota,
                    locExistentesJogador1, locExistentesJogador2, tipoCaminhoJogador1, tipoCaminhoJogador2,
                    densidadeArestasJogador1, densidadeArestasJogador2);

            Platform.runLater(() -> new InterfaceGraficaSelecionarBandeira(this.dataManager).start(new Stage()));
            stage.close();
        }

        else {
            // Mostrar uma mensagem de erro ou uma indicação de que os valores inseridos são
            // inválidos
            // Por exemplo, você pode usar um Alert para isso
            Alert alert = new Alert(Alert.AlertType.ERROR, "Retificar numero de localizaçoes ou densidade");
            alert.showAndWait();
        }

    }

    private int getTipoCaminho() {

        RadioButton dirSelecionada = (RadioButton) this.directionToggleGroup.getSelectedToggle();

        if (dirSelecionada == directedRadioButton) {
            return 1; // Direcionado
        }

        else {
            return 2; // Bidirecionado
        }
    }
}