package controller;



import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;




import javafx.scene.control.TextField;

import javafx.stage.Stage;



/**
 * Contrôleur pour la fenêtre de connexion (SignIn).
 * Cette classe gère les interactions de l'utilisateur lorsqu'il se connecte.
 * Elle utilise JavaFX pour l'interface utilisateur.
 *
 * @author YANIS BOUSSADIA
 * @version 1.0
 */
public class SignInController implements Initializable{

    @FXML
    private TextField txt_userName;

    @FXML
    private TextField txt_password;

    @FXML
    private Button btn_passwordForgoten;

    @FXML
    private Button btn_seconnecter;
   
    @FXML
    private VBox VBox;
    private Parent fxml;
   
   
    @FXML
    
    /**
     * Ouvre la page d'accueil après la connexion réussie.
     */
    
    void openHome() {
     
    String url = "jdbc:mysql://localhost/luxedrive";
    String user = "root";
    String passwd = "";
try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
       String query = "SELECT * FROM client WHERE email = ? AND motDePasse = ?";
       PreparedStatement statement = connection.prepareStatement(query);
       String email = null;
statement.setString(1, email);
       String motDePasse = null;
statement.setString(2, motDePasse);
       statement.executeQuery();
       String updateQuery = "UPDATE client SET estConnecte = true WHERE email = ?";
       connection.prepareStatement(updateQuery);
       VBox.getScene().getWindow().hide();
            Stage home = new Stage();
       fxml = FXMLLoader.load(getClass().getResource("/interfaces/Home.fxml"));
            Scene scene = new Scene(fxml);
            home.setScene(scene);
            home.show();
 
   } catch (Exception e) {
       e.printStackTrace();
   }
       System.out.println("Erreur");
    }
    
    
   @FXML
    void SendPassword() {

    }


@Override
public void initialize(URL arg0, ResourceBundle arg1){
// TODO Auto-generated method stub



}

}


