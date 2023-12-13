package controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignUpController {
	 @FXML
	    private TextField usernameTextField;

	    @FXML
	    private TextField emailTextField;

	    @FXML
	    private TextField passwordTextField;

	    @FXML
	    private Button inscriptionButton;

	    @FXML
	    public void initialize() {
	        // Vous pouvez effectuer des initialisations ici si nécessaire
	    }

	    @FXML
	    public void handleInscription() {
	        // Logique pour gérer l'inscription
	        String username = usernameTextField.getText();
	        String email = emailTextField.getText();
	        String password = passwordTextField.getText();

	        // Ajoutez ici la logique pour traiter les données d'inscription (par exemple, envoyer à une base de données)
	        System.out.println("Utilisateur inscrit : " + username);
	        System.out.println("Email : " + email);
	        System.out.println("Mot de passe : " + password);

	        // Ajoutez ici la logique pour afficher un message de confirmation ou rediriger l'utilisateur vers une autre page
	    }
	}
