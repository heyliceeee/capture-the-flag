package org.example.InterfaceGrafica;

import org.example.api.implementation.Bandeira;
import org.example.api.interfaces.IBandeira;
import org.example.api.interfaces.ILocalizacao;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InterfaceGraficaSelecionarBandeira extends Application {

    private ComboBox<String> comboBox;
    private Label playerLabel;

    protected DataManager dataManager;

    public InterfaceGraficaSelecionarBandeira(DataManager dManager) {
        this.dataManager = dManager;
    }

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(10);

        root.getChildren().add(formularioInput(1));

        Button nextButton = new Button("Proximo");

        Button okButton = new Button("Ok");

        nextButton.setOnAction(event -> {

            root.getChildren().add(okButton);
        });

        okButton.setOnAction(event -> {

        });

        root.getChildren().addAll(comboBox, nextButton);

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Escolha da Bandeira");
        stage.setScene(scene);
        stage.show();
    }

    private VBox formularioInput(int playerNumber) {

        VBox form = new VBox(5); // Espaçamento entre elementos

        playerLabel = new Label("Jogador " + playerNumber);
        this.preencherComboBox();

        form.getChildren().addAll(playerLabel, comboBox);

        return form;
    }

    private void logicaBotaoNext(VBox root) {

        ILocalizacao localJogador1Escolheu = raiz.getLocalizacaoPorID(locBandeiraJogador1); // localizacao selecionada
        raiz.removerLocal(localJogador1Escolheu); // remover localizacao selecionada
        IBandeira bandeiraJogador1 = new Bandeira(0, "Bandeira", localJogador1Escolheu.getNome(),
                localJogador1Escolheu.getCoordenadas()); // criar bandeira

        jogador1.setBandeira(bandeiraJogador1);

        root.getChildren().clear();
        root.getChildren().add(formularioInput(2));

    }

    private void preencherComboBox() {

        for (ILocalizacao localizacaoObj : dataManager.raiz.getListaLocalizacoes()) {
            comboBox.getItems().add(localizacaoObj.getNome());
        }

    }

}
