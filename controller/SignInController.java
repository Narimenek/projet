package controller;

import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    @FXML
    private TextField txt_userName;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Button btn_passwordForgotten;

    @FXML
    private Button btn_seconnecter;

    @FXML
    private VBox VBox;
    private Parent fxml;

    private String loggedInUserName;

    @FXML
    void openHome() {
        String url = "jdbc:mysql://localhost/luxedrive";
        String user = "root";
        String passwd = "";

        try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
            String query = "SELECT * FROM client WHERE email = ? AND motDePasse = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            String email = txt_userName.getText();
            String motDePasse = txt_password.getText();
            statement.setString(1, email);
            statement.setString(2, motDePasse);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                loggedInUserName = resultSet.getString("nomClient");

                VBox.getScene().getWindow().hide();
                Stage home = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/Home.fxml"));
                Parent fxml = loader.load();
                HomeController homeController = loader.getController();
                homeController.setLoggedInUserName(loggedInUserName);

                Scene scene = new Scene(fxml);
                home.setScene(scene);
                home.show();
            } else {
                showErrorDialog("Erreur d'authentification", "Adresse e-mail ou mot de passe incorrect.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Erreur", "Une erreur s'est produite lors de l'authentification.");
        }
    }

    @FXML
    void SendPassword() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Mot de passe oublié");
        dialog.setHeaderText(null);
        dialog.setContentText("Veuillez entrer votre adresse e-mail :");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(email -> {
            System.out.println("L'utilisateur a demandé la récupération du mot de passe pour l'adresse : " + email);
        });
    }

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

    public String getLoggedInUserName() {
        return loggedInUserName;
    }
}