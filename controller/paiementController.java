package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class paiementController {

    @FXML
    private TextField montantTextField;

    @FXML
    private TextField numeroCarteTextField;

    @FXML
    private DatePicker dateExpirationDatePicker;

    @FXML
    private TextField codeSecuriteTextField;

    @FXML
    private Button effectuerPaiementButton;

    private int idVoiture;
    private int idLocation;
    private double montantTotal;
    private boolean avecChauffeur; 
    // Ajouter un constructeur
    public paiementController() {
    }
    public paiementController(int idLocation, double montantTotal, int idVoiture, boolean avecChauffeur) {
        this.idLocation = idLocation;
        this.montantTotal = montantTotal;
        this.idVoiture = idVoiture;
        this.avecChauffeur = avecChauffeur;
    }


    String JDBC_URL = "jdbc:mysql://localhost/luxedrive"; // Mettez votre URL de base de données
    String DB_USER = "root"; // Mettez votre nom d'utilisateur
    String DB_PASSWORD = ""; // Mettez votre mot de passe

    @FXML
    private void initialize() {
        // Afficher un exemple de format de numéro de carte avant de commencer à écrire
        numeroCarteTextField.setPromptText("Ex. 1234 5678 9012 3456");

        // Masquer le texte dans le champ de code de sécurité
        codeSecuriteTextField.setPromptText("VVV");
        montantTextField.setEditable(false);
        
       

        // Appeler la méthode pour initialiser le montant
        initializeMontant();
    System.out.println("Avec chauffeur : " + avecChauffeur);}
        private void initializeMontant() {
        	double montantAffiche1=montantTotal + 150.0;
            // Calculer le montant total en ajoutant 150 si le chauffeur est sélectionné
            double montantAffiche = avecChauffeur ? montantAffiche1   :montantTotal;
            montantTextField.setText(new DecimalFormat("0.00").format(montantAffiche));
        }
       
     
   
   
    @FXML
    private void effectuerPaiement() {
        // Récupérer les données du formulaire
        String montant = montantTextField.getText();
        String numeroCarte = numeroCarteTextField.getText();
        LocalDate dateExpiration = dateExpirationDatePicker.getValue();
        String codeSecurite = codeSecuriteTextField.getText();
        LocalDate dateDePaiement = LocalDate.now(); // Utiliser la date actuelle

        // Valider les données
        if (validerMontant(montant) && validerNumeroCarte(numeroCarte) && validerCodeSecurite(codeSecurite)) {
            // Effectuer le paiement et enregistrer dans la base de données
            if (enregistrerPaiement(montant, dateDePaiement, idLocation)) {
                System.out.println("Paiement effectué avec succès !");

                // Mettre à jour le statut de la location
                mettreAJourStatutLocation();
            } else {
                System.out.println("Erreur lors de l'enregistrement du paiement.");
            }
        } else {
            System.out.println("Veuillez remplir correctement tous les champs.");
        }
    }

    private void mettreAJourStatutLocation() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE location SET statueLocation = 'validé' WHERE idLocation = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idLocation);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Statut de la location mis à jour avec succès !");
                } else {
                    System.out.println("Erreur lors de la mise à jour du statut de la location.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean validerMontant(String montant) {
        return Pattern.matches("^\\d+(\\.\\d{1,2})?$", montant);
    }

    private boolean validerNumeroCarte(String numeroCarte) {
        return Pattern.matches("^\\d{4} \\d{4} \\d{4} \\d{4}$", numeroCarte);
    }

    private boolean validerCodeSecurite(String codeSecurite) {
        return Pattern.matches("^\\d{3}$", codeSecurite);
    }

    private boolean enregistrerPaiement(String montant, LocalDate dateDePaiement, int idLocation) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO paiement (Montant, dateDePaiement, idLocation) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, montant);
                preparedStatement.setDate(2, java.sql.Date.valueOf(dateDePaiement));
                preparedStatement.setInt(3, idLocation);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}