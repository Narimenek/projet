package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class updateVoitureController {
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
	    @FXML
	    private void retourner(ActionEvent event) {
	            loadPage("/interfaces/optionVoiture.fxml", event);
	        }

}
