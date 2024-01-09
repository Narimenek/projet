package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SuppressionController {
	 @FXML
	    private Parent fxml;

	    @FXML
	    private AnchorPane root;

	@FXML
     TextField immatriculationField;

    @FXML
     TextField idField;

    @FXML
    Button deleteButton;

    @FXML
    void supprimerVoiture(ActionEvent event) {
        String immatriculation = immatriculationField.getText();
        String id = idField.getText();

        if (immatriculation.isEmpty() && id.isEmpty()) {
            afficherErreur("Veuillez saisir l'immatriculation ou l'ID de la voiture Ã  supprimer.");
            return;
        }

        String url = "jdbc:mysql://localhost/luxedrive";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql;
            if (!immatriculation.isEmpty()) {
                sql = "DELETE FROM voituredeluxe WHERE immatriculation = ?";
            } else {
                sql = "DELETE FROM voituredeluxe WHERE idVoiture = ?";
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                if (!immatriculation.isEmpty()) {
                    preparedStatement.setString(1, immatriculation);
                } else {
                    preparedStatement.setString(1, id);
                }

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    afficherInfo("La voiture a été supprimée avec succés.");
                } else {
                    afficherErreur("Aucune voiture trouvée avec les informations fournies.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            afficherErreur("Erreur lors de la suppression de la voiture. Vérifiez la console pour plus d'informations.");
        }
    }

     void afficherErreur(String message) {
        // Afficher une boÃ®te de dialogue d'alerte
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

     void afficherInfo(String message) {
        // Afficher une boÃ®te de dialogue d'information
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
     @FXML
     private void retourner(ActionEvent event) {
     	   try {
                fxml = FXMLLoader.load(getClass().getResource("/interfaces/optionVoiture.fxml"));
                root.getChildren().removeAll();
                root.getChildren().setAll(fxml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void loadFXML(String fxmlPath) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                Parent loadedFXML = loader.load();
                root.getChildren().setAll(loadedFXML);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }}

