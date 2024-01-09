package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class OptionVoitureController {

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;
    @FXML
    private Button rechercheboutton;
    @FXML
    private Button deleteButton;

    @FXML
    void addVoiture(ActionEvent event) {
        loadPage("/interfaces/voiture.fxml", event);
    }

    @FXML
    void updateVoiture(ActionEvent event) {
        loadPage("/interfaces/updateVoiture.fxml", event);
    }

    @FXML
    void deleteVoiture(ActionEvent event) {
        loadPage("/interfaces/suppression.fxml", event);
    }
    @FXML
    void rechercheVoiture(ActionEvent event) {
        loadPage("/interfaces/RechercheClient.fxml", event);
    }
    private void loadPage(String fxmlFileName, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'erreur lors du chargement de la page FXML
        }
    }


}