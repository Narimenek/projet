
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.nio.file.Files;

public class VoitureController {

    @FXML
    private TextField couleur;

    @FXML
    private TextField immatriculation;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button selectImageButton;

    @FXML
    private TextField marque;

  

    @FXML
    private ImageView imageView;

    @FXML
    private TextField prix;

    private Connection connection;
    private File selectedImageFile;

    @FXML
    void initialize() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        // Initialisez votre connexion à la base de données ici.
        // Remplacez les informations de connexion avec les vôtres.
        String url = "jdbc:mysql://localhost/luxedrive";
        String user = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'erreur de connexion à la base de données
        }
    }

    @FXML
    private void ajouterVoiture(ActionEvent event) {
        try {
            String marqueValue = marque.getText();
            String couleurValue = couleur.getText();
            String immatriculationValue = immatriculation.getText();
            String prixText = prix.getText();

            // Vérifier si tous les champs sont remplis
            if (marqueValue.isEmpty() || couleurValue.isEmpty() || immatriculationValue.isEmpty() || prixText.isEmpty()) {
                afficherErreur("Veuillez remplir tous les champs.");
                return;
            }

            // Vérifier si le prix est un nombre valide
            double prixValue;
            try {
                prixValue = Double.parseDouble(prixText);
            } catch (NumberFormatException e) {
                afficherErreur("Le champ Prix doit être un nombre valide.");
                return;
            }

            // Vérifier si une photo a été sélectionnée
            if (selectedImageFile == null) {
                afficherErreur("Veuillez sélectionner une photo.");
                return;
            }

            String sql = "INSERT INTO voituredeluxe (marque, couleur, immatriculation, prixLocation, lien_img) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, marqueValue);
                preparedStatement.setString(2, couleurValue);
                preparedStatement.setString(3, immatriculationValue);
                preparedStatement.setDouble(4, prixValue);

                try (FileInputStream inputStream = new FileInputStream(selectedImageFile)) {
                    byte[] imageBytes = new byte[(int) selectedImageFile.length()];
                    inputStream.read(imageBytes);

                    preparedStatement.setBytes(5, imageBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                preparedStatement.executeUpdate();
            }

            // Afficher une confirmation après l'ajout
            afficherInformation("Voiture ajoutée avec succès.");

        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les erreurs d'ajout dans la base de données
            afficherErreur("Une erreur s'est produite lors de l'ajout de la voiture.");
        }
    }
          
    @FXML
    private void MettreAjourVoiture(ActionEvent event) {
        // Ajoutez le code de mise à jour de la voiture ici
    }

    @FXML
    private void SupprimerVoiture(ActionEvent event) {
        // Ajoutez le code de suppression de la voiture ici
    }

    @FXML
    private void onDragDropped(ActionEvent event) {
        // Ajoutez le code de gestion de l'événement "Drag Dropped" ici
    }

    @FXML
    private void onDragOver(ActionEvent event) {
        // Ajoutez le code de gestion de l'événement "Drag Over" ici
    }

    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void afficherInformation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void selectImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.bmp"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        selectedImageFile = fileChooser.showOpenDialog(new Stage());
        if (selectedImageFile != null) {
            try {
                javafx.scene.image.Image image = new javafx.scene.image.Image(selectedImageFile.toURI().toURL().toString());
                imageView.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}