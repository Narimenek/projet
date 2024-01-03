package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import javafx.scene.control.TextInputDialog;
import java.util.Optional;

import javafx.scene.control.TextField;

import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * Contrôleur pour la vue de connexion (SignIn).
 * Gère l'authentification des utilisateurs et la récupération des mots de passe.
 */
public class SignInController implements Initializable {

    @FXML
    private TextField txt_userName;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Button btn_passwordForgoten;

    @FXML
    private Button btn_seconnecter;

    @FXML
    private VBox VBox;
    private Parent fxml;

    /**
     * Méthode appelée lors du clic sur le bouton de connexion.
     * Vérifie les informations d'identification et affiche la scène d'accueil en cas de succès.
     */
    @FXML
    void openHome() {
        // Connexion à la base de données
        String url = "jdbc:mysql://localhost/luxedrive";
        String user = "root";
        String passwd = "";

        try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
            // Requête de sélection pour vérifier les informations d'identification
            String query = "SELECT * FROM client WHERE email = ? AND motDePasse = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            // Remplacez ces valeurs par les valeurs réelles entrées par l'utilisateur
            String email = txt_userName.getText();
            String motDePasse = txt_password.getText();
            statement.setString(1, email);
            statement.setString(2, motDePasse);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Affichez la nouvelle scène (home) en cas de succès
                System.out.println("Client trouvé : " + resultSet.getString("nomClient"));

                VBox.getScene().getWindow().hide();
                Stage home = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/Home.fxml"));
                Parent fxml = loader.load();
                Scene scene = new Scene(fxml);
                home.setScene(scene);
                home.show();
            } else {
                // Affichez une fenêtre d'erreur si les informations d'identification sont incorrectes
                System.out.println("Aucun client trouvé.");
                showErrorDialog("Erreur d'authentification", "Adresse e-mail ou mot de passe incorrect.");
            }

        } catch (Exception e) {
            // Affichez une fenêtre d'erreur générique en cas d'exception
            e.printStackTrace();
            showErrorDialog("Erreur", "Une erreur s'est produite lors de l'authentification.");
        }
    }

    /**
     * Affiche une boîte de dialogue permettant à l'utilisateur de récupérer son mot de passe.
     */
    @FXML
    void SendPassword() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Mot de passe oublié");
        dialog.setHeaderText(null);
        dialog.setContentText("Veuillez entrer votre adresse e-mail :");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(email -> {
            // Vous pouvez maintenant traiter l'e-mail entré par l'utilisateur
            System.out.println("L'utilisateur a demandé la récupération du mot de passe pour l'adresse : " + email);
            // Ajoutez ici le code pour gérer la récupération du mot de passe, par exemple, en envoyant un e-mail à l'utilisateur avec un lien de réinitialisation.
        });
    }

    /**
     * Affiche une boîte de dialogue d'erreur avec le titre et le message spécifiés.
     *
     * @param title   Titre de la boîte de dialogue d'erreur.
     * @param message Message d'erreur à afficher.
     */
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        System.out.println("Erreur : " + message);
    }

  
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Initialisation, si nécessaire
    }
}