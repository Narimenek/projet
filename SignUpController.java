package controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {
	 @FXML
	    private TextField usernameTextField;

	    @FXML
	    private TextField emailTextField;

	    @FXML
	    private PasswordField passwordTextField;

	    @FXML
	    private Button inscriptionButton;
	    
	    @FXML
	    private TextField phoneTextField;

	    @FXML
	    public void initialize() {
	        // Vous pouvez effectuer des initialisations ici si nécessaire
	    }

	    /**
	     * Vérifie si l'adresse e-mail est valide.
	     *
	     * @param email Adresse e-mail à vérifier.
	     * @return True si l'adresse e-mail est valide, sinon False.
	     */
	    private boolean isValidEmail(String email) {
	        // Utilisez une expression régulière pour valider l'adresse e-mail
	        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	        Pattern pattern = Pattern.compile(emailRegex);
	        Matcher matcher = pattern.matcher(email);
	        return matcher.matches();
	    }
	    @FXML
	    public void handleInscription() {
	        // Logique pour gérer l'inscription
	        String username = usernameTextField.getText();
	        String email = emailTextField.getText();
	        String password = passwordTextField.getText();
	        String phoneNumber = phoneTextField.getText(); 

	        // Vérifier si l'adresse e-mail est valide
	        if (!isValidEmail(email)) {
	            // Afficher une boîte de dialogue d'erreur si l'adresse e-mail n'est pas valide
	            showErrorDialog("Adresse e-mail invalide");
	            return;
	        }
	        if (!isValidPhoneNumber(phoneNumber)) {
	            // Afficher une boîte de dialogue d'erreur si le numéro de téléphone n'est pas valide
	            showErrorDialog("Numéro de téléphone invalide");
	            return;
	        }

	        // Connexion à la base de données
	        String url = "jdbc:mysql://localhost/luxedrive";
	        String user = "root";
	        String passwd = "";

	        try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
	            // Vérifier si l'e-mail existe déjà dans la base de données
	            String checkQuery = "SELECT COUNT(*) FROM client WHERE email = ?";
	            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
	            checkStatement.setString(1, email);

	            ResultSet resultSet = checkStatement.executeQuery();
	            resultSet.next();

	            int count = resultSet.getInt(1);

	            if (count > 0) {
	                // L'e-mail existe déjà, afficher une boîte de dialogue d'erreur
	                showErrorDialog("L'adresse e-mail est déjà utilisée");
	            } else {
	                // L'e-mail n'existe pas encore, procéder à l'inscription
	                String insertQuery = "INSERT INTO client ( nomClient, tel,email,motDePasse) VALUES ( ?, ?, ?,?)";
	                PreparedStatement statement = connection.prepareStatement(insertQuery);
	                statement.setString(1, username);
	                statement.setString(2, phoneNumber);
	                statement.setString(3, email);
	                statement.setString(4, password);

	                // Exécution de la requête
	                int rowsAffected = statement.executeUpdate();

	                if (rowsAffected > 0) {
	                    // L'utilisateur a été ajouté avec succès, afficher une boîte de dialogue de succès
	                    showSuccessDialog("Utilisateur inscrit : " + username);
	                } else {
	                    // Une erreur s'est produite lors de l'ajout de l'utilisateur, afficher une boîte de dialogue d'erreur
	                    showErrorDialog("Erreur lors de l'inscription");
	                }
	            }

	            }catch (SQLException e) {
	            // Une erreur s'est produite lors de la connexion à la base de données, afficher une boîte de dialogue d'erreur
	            showErrorDialog("Erreur de base de données");
	            e.printStackTrace();
	        }
	    
	    }
	    private void showErrorDialog(String message) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Erreur");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }

	    private void showSuccessDialog(String message) {
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Succès");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
	    private boolean isValidPhoneNumber(String phoneNumber) {
	        // Utilisez une expression régulière pour valider le format du numéro de téléphone
	        String phoneRegex = "^(0[1-9])(\\d{8})$";
	        Pattern pattern = Pattern.compile(phoneRegex);
	        Matcher matcher = pattern.matcher(phoneNumber);
	        return matcher.matches();
	    }}