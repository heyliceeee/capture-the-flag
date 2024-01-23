package org.example.InterfaceGrafica;

import org.example.Demo;
import org.example.api.implementation.Bandeira;
import org.example.api.interfaces.IBandeira;
import org.example.api.interfaces.ILocalizacao;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InterfaceGraficaSelecionarBandeira extends Application {

    private ComboBox<ILocalizacao> comboBox1;
    private ComboBox<ILocalizacao> comboBox2;
    private Label playerLabel;
    private ILocalizacao localJogador1Escolheu;
    private ILocalizacao localJogador2Escolheu;

    protected DataManager dataManager;

    public InterfaceGraficaSelecionarBandeira(DataManager dManager) {
        this.dataManager = dManager;
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
            this.logicaBotaoOk(stage);
        });

        root.getChildren().add(nextButton);

        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Escolha da Bandeira");
        stage.setScene(scene);
        stage.show();
    }

    private VBox formularioInput(int playerNumber)
    {

        VBox form = new VBox(5); // Espaçamento entre elementos
        form.setAlignment(Pos.CENTER);

        playerLabel = new Label("Jogador " + playerNumber);

        if (playerNumber == 1)
        {
            comboBox1 = new ComboBox<>();
            this.preencherComboBox(this.comboBox1);
            form.getChildren().addAll(playerLabel, comboBox1);
        }

        else
        {
            comboBox2 = new ComboBox<>();
            this.preencherComboBox(this.comboBox2);
            form.getChildren().addAll(playerLabel, comboBox2);
        }

        return form;
    }

    private void logicaBotaoNext(VBox root, Button okButton) {

        // Guardar a localização selecionada na variável localJogador1Escolheu
        localJogador1Escolheu = comboBox1.getValue();

        // Verificar se uma localização foi realmente selecionada
        if (localJogador1Escolheu == null) {
            // Mostrar um alerta ou lidar com a situação de nenhuma seleção
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selecao Incompleta");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma localizacao.");
            alert.showAndWait();
            return;
        }

        dataManager.raiz.removerLocal(localJogador1Escolheu); // remover localizacao selecionada

        IBandeira bandeiraJogador1 = new Bandeira(localJogador1Escolheu.getId(), "Bandeira", localJogador1Escolheu.getNome(), localJogador1Escolheu.getCoordenadas()); // criar bandeira

        dataManager.jogador1.setBandeira(bandeiraJogador1);
        Demo.raiz.adicionarLocal(bandeiraJogador1);

        root.getChildren().clear();
        root.getChildren().add(formularioInput(2));
        root.getChildren().add(okButton);

    }

    private void logicaBotaoOk(Stage stage) {

        // Guardar a localização selecionada na variável localJogador1Escolheu
        localJogador2Escolheu = comboBox2.getValue();

        // Verificar se uma localização foi realmente selecionada
        if (localJogador2Escolheu == null) {
            // Mostrar um alerta ou lidar com a situação de nenhuma seleção
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleção Incompleta");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma localizacao.");
            alert.showAndWait();
            return;
        }

        dataManager.raiz.removerLocal(localJogador2Escolheu); // remover localizacao selecionada

        IBandeira bandeiraJogador2 = new Bandeira(localJogador2Escolheu.getId(), "Bandeira", localJogador2Escolheu.getNome(), localJogador2Escolheu.getCoordenadas()); // criar bandeira

        dataManager.jogador2.setBandeira(bandeiraJogador2);
        Demo.raiz.adicionarLocal(bandeiraJogador2);

        Platform.runLater(() -> new InterfaceGraficaNumeroBots(this.dataManager).start(new Stage()));
        stage.close();


        //menu para selecionar os bots e o seu algoritmo
        //jogo (mostrar grafo com os bots)
    }

    private void preencherComboBox(ComboBox<ILocalizacao> comboBox) {

        comboBox.setCellFactory(lv -> new ListCell<ILocalizacao>() {
            @Override
            protected void updateItem(ILocalizacao item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getNome());
            }
        });

        comboBox.setButtonCell(new ListCell<ILocalizacao>() {

            @Override
            protected void updateItem(ILocalizacao item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getNome());
            }
        });

        for (ILocalizacao localizacaoObj : dataManager.raiz.getListaLocalizacoes())
        {
            comboBox.getItems().add(localizacaoObj);
        }
    }
}
