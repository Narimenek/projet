/*package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class LocationController {

    // Remplacez les détails de connexion à la base de données selon votre configuration
    private static final String JDBC_URL = "jdbc:mysql://localhost/luxedrive";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private ComboBox<String> voitureComboBox;

    @FXML
    private ComboBox<String> chauffeurComboBox;

    @FXML
    private DatePicker debutDatePicker;

    @FXML
    private DatePicker finDatePicker;

    @FXML
    private CheckBox avecChauffeurCheckBox;

    @FXML
    private TextField telTextField;

    @FXML
    private Button reserverButton;

    @FXML
    private void initialize() {
        // Initialisation des combobox (voiture et chauffeur) avec des données de votre base de données
        chargerMarques();
       chargerChauffeurs();
    }

    @FXML
    private void handleReserverButton() {
        // Ajouter la logique pour insérer la location dans la base de données
        if (ajouterLocation()) {
            // Afficher un message de succès
            showAlert("Location réservée avec succès !");
        } else {
            // Afficher un message d'erreur
            showAlert("Erreur lors de la réservation de la location.");
        }
    }

    // Les autres méthodes restent inchangées

    private void chargerMarques() {
        // Chargez les marques de la base de données et ajoutez-les à la ComboBox
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT DISTINCT marque FROM voituredeluxe";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                Set<String> marques = new HashSet<>();
                while (resultSet.next()) {
                    marques.add(resultSet.getString("marque"));
                }

                // Ajoutez les marques à la ComboBox
                ObservableList<String> options = FXCollections.observableArrayList(marques);
                voitureComboBox.setItems(options);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de connexion à la base de données
        }
    }


    private boolean ajouterLocation() {
        // Récupérer les valeurs des champs
        String nom = nomTextField.getText();
        String email = emailTextField.getText();
        String voiture = voitureComboBox.getValue();
        String chauffeur = chauffeurComboBox.getValue();
        String tel = telTextField.getText();

        // Vérifier que toutes les informations nécessaires sont remplies
        if (nom.isEmpty() || email.isEmpty() || voiture == null || tel.isEmpty() || debutDatePicker.getValue() == null || finDatePicker.getValue() == null) {
            showAlert("Veuillez remplir tous les champs obligatoires.");
            return false;
        }

        // Récupérer les dates de début et de fin
        String dateDebut = debutDatePicker.getValue().toString();
        String dateFin = finDatePicker.getValue().toString();
        boolean avecChauffeur = avecChauffeurCheckBox.isSelected();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            // Utilisez une requête SQL paramétrée pour éviter les injections SQL
            String sql = "INSERT INTO location (dateDebut, dateFin, avecChauffeur, numChauffeur, idClient, idVoiture, StatutLocation) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, dateDebut);
                preparedStatement.setString(2, dateFin);
                preparedStatement.setBoolean(3, avecChauffeur);

                // Utilisez la logique appropriée pour récupérer les numéros de chauffeur, client, et voiture à partir de leurs noms
                int numChauffeur = obtenirNumChauffeur(chauffeur);
                int idClient = obtenirIdClient(email);
                int idVoiture = obtenirIdVoiture(voiture);

                preparedStatement.setInt(4, numChauffeur);
                preparedStatement.setInt(5, idClient);
                preparedStatement.setInt(6, idVoiture);
                preparedStatement.setString(7, "En attente"); // Statut initial

                // Exécutez la requête
                int rowsAffected = preparedStatement.executeUpdate();

                // Retournez vrai si une ligne a été ajoutée
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthodes auxiliaires pour obtenir les identifiants à partir des noms
    private int obtenirNumChauffeur(String nomChauffeur) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT numChauffeur FROM chauffeur WHERE nomChauffeur = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nomChauffeur);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("numChauffeur");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si aucun chauffeur n'est trouvé, retournez une valeur par défaut
        return -1;
    }

    private int obtenirIdClient(String emailClient) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT idClient FROM client WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, emailClient);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("idClient");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si aucun client n'est trouvé, retournez une valeur par défaut
        return -1;
    }

    private int obtenirIdVoiture(String nomVoiture) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT idVoiture FROM voituredeluxe WHERE marque = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nomVoiture);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("idVoiture");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si aucune voiture n'est trouvée, retournez une valeur par défaut
        return -1;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private String[] getVoituresFromDatabase() {


    	        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
    	            String sql = "SELECT DISTINCT marque FROM voituredeluxe";
    	            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
    	                 ResultSet resultSet = preparedStatement.executeQuery()) {

    	                Set<String> marques = new HashSet<>();
    	                while (resultSet.next()) {
    	                    marques.add(resultSet.getString("marque"));
    	                }

    	                // Convertissez le HashSet en tableau de chaînes
    	                return marques.toArray(new String[0]);

    	            }
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	            // Gérer les erreurs de connexion à la base de données
    	        }

    	        // En cas d'erreur, retournez un tableau vide
    	        return new String[0];
    	    }

    	    // Autres méthodes utiles pour la manipulation de la base de données peuvent être ajoutées ici
    	
    private String[] getChauffeursFromDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT nomChauffeur FROM chauffeurs";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Obtenez la taille du résultat sans utiliser last()
                    int rowCount = 0;
                    while (resultSet.next()) {
                        rowCount++;
                    }

                    // Retournez au début du résultat
                    resultSet.beforeFirst();

                    String[] chauffeurs = new String[rowCount];

                    int i = 0;
                    while (resultSet.next()) {
                        chauffeurs[i++] = resultSet.getString("nomChauffeur");
                    }

                    return chauffeurs;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // En cas d'erreur, retournez un tableau vide
        return new String[0];
    }
    private void chargerChauffeurs() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT nomChauffeur FROM chauffeurs";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                Set<String> chauffeurs = new HashSet<>();
                while (resultSet.next()) {
                    chauffeurs.add(resultSet.getString("nomChauffeur"));
                }

                ObservableList<String> options = FXCollections.observableArrayList(chauffeurs);
                // Charger les chauffeurs dans la ComboBox
                chauffeurComboBox.setItems(options);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

public class LocationController {

    private static final String JDBC_URL = "jdbc:mysql://localhost/luxedrive";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private ComboBox<String> voitureComboBox;

    @FXML
    private ComboBox<String> chauffeurComboBox;

    @FXML
    private DatePicker debutDatePicker;

    @FXML
    private DatePicker finDatePicker;

    @FXML
    private CheckBox avecChauffeurCheckBox;

    @FXML
    private TextField telTextField;

    @FXML
    private Button reserverButton;


private int idClient; // Ajout de la variable pour stocker l'ID du client

@FXML
private void initialize() {
    chargerMarques();
    chargerChauffeurs();
    // Ajout d'un ChangeListener pour la case à cocher
    avecChauffeurCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
        // Mettre à jour la visibilité du ComboBox en fonction de l'état de la case à cocher
        chauffeurComboBox.setVisible(newValue);
        chauffeurComboBox.setManaged(newValue);
    });

}

@FXML
private void handleReserverButton() {
    if (ajouterLocation()) {
        showAlert("Location réservée avec succès !");
    } else {
        showAlert("Erreur lors de la réservation de la location.");
    }
}

// ... (le reste du code)

private boolean ajouterLocation() {
    // ... (le reste du code)

    String nom = nomTextField.getText();
    String email = emailTextField.getText();
    String voiture = voitureComboBox.getValue();
    String chauffeur = chauffeurComboBox.getValue();
    String tel = telTextField.getText();

    // Vérification du format du numéro de téléphone
    if (!tel.matches("\\d{10}")) {
        showAlert("Format de numéro de téléphone incorrect. Veuillez utiliser un format à 10 chiffres.");
        return false;
    }

    // Vérification du format de l'adresse e-mail
    if (!email.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b")) {
        showAlert("Format d'adresse e-mail incorrect.");
        return false;
    }

    if (nom.isEmpty() || email.isEmpty() || voiture == null || tel.isEmpty() || debutDatePicker.getValue() == null || finDatePicker.getValue() == null) {
        showAlert("Veuillez remplir tous les champs obligatoires.");
        return false;
    }

    // Connexion du client pour obtenir son ID
    idClient = obtenirIdClient(email);
    if (idClient == -1) {
        showAlert("Le client n'est pas trouvé. Veuillez vous connecter.");
        return false;
    }

    String dateDebut = debutDatePicker.getValue().toString();
    String dateFin = finDatePicker.getValue().toString();
    boolean avecChauffeur = avecChauffeurCheckBox.isSelected();

    // Vérifier la disponibilité du chauffeur
    if (avecChauffeur && !verifierDisponibiliteChauffeur(chauffeur, dateDebut, dateFin)) {
        showAlert("Le chauffeur n'est pas disponible aux dates spécifiées.");
        return false;
    }

    // Vérifier la disponibilité de la voiture
    if (!verifierDisponibiliteVoiture(voiture, dateDebut, dateFin)) {
        showAlert("La voiture n'est pas disponible aux dates spécifiées.");
        return false;
    }

    try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
        // Ajouter la location dans la table location
        String sqlLocation = "INSERT INTO location (dateDebut, dateFin, avecChauffeur, numChauffeur, idClient, idVoiture, StatutLocation) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatementLocation = connection.prepareStatement(sqlLocation)) {
            preparedStatementLocation.setString(1, dateDebut);
            preparedStatementLocation.setString(2, dateFin);
            preparedStatementLocation.setBoolean(3, avecChauffeur);

            int numChauffeur = obtenirNumChauffeur(chauffeur);
            int idVoiture = obtenirIdVoiture(voiture);

            preparedStatementLocation.setInt(4, numChauffeur);
            preparedStatementLocation.setInt(5, idClient); // Utilisation de l'ID du client récupéré
            preparedStatementLocation.setInt(6, idVoiture);
            preparedStatementLocation.setString(7, "En attente");

            int rowsAffectedLocation = preparedStatementLocation.executeUpdate();

            // Mise à jour de la table occupevoiture avec les nouvelles dates
            String sqlUpdateVoiture = "INSERT INTO occupevoiture (idVoiture, dateDebut, dateFin) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatementUpdateVoiture = connection.prepareStatement(sqlUpdateVoiture)) {
                preparedStatementUpdateVoiture.setInt(1, idVoiture);
                preparedStatementUpdateVoiture.setString(2, dateDebut);
                preparedStatementUpdateVoiture.setString(3, dateFin);
                preparedStatementUpdateVoiture.executeUpdate();
            }

            // Mise à jour de la table occupechauffeur avec les nouvelles dates si avecChauffeur est vrai
            if (avecChauffeur) {
                String sqlUpdateChauffeur = "INSERT INTO occupechauffeur (numChauffeur, dateDebut, dateFin) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatementUpdateChauffeur = connection.prepareStatement(sqlUpdateChauffeur)) {
                    preparedStatementUpdateChauffeur.setInt(1, numChauffeur);
                    preparedStatementUpdateChauffeur.setString(2, dateDebut);
                    preparedStatementUpdateChauffeur.setString(3, dateFin);
                    preparedStatementUpdateChauffeur.executeUpdate();
                }
            }

            return rowsAffectedLocation > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    private void chargerMarques() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT DISTINCT marque FROM voituredeluxe";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                Set<String> marques = new HashSet<>();
                while (resultSet.next()) {
                    marques.add(resultSet.getString("marque"));
                }

                ObservableList<String> options = FXCollections.observableArrayList(marques);
                voitureComboBox.setItems(options);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void chargerChauffeurs() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT nomChauffeur FROM chauffeurs";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                Set<String> chauffeurs = new HashSet<>();
                while (resultSet.next()) {
                    chauffeurs.add(resultSet.getString("nomChauffeur"));
                }

                ObservableList<String> options = FXCollections.observableArrayList(chauffeurs);
                chauffeurComboBox.setItems(options);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    private void mettreAJourOccupeChauffeur(String chauffeur, String dateDebut, String dateFin) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO occupechauffeur (numChauffeur, dateDebut, dateFin) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                int numChauffeur = obtenirNumChauffeur(chauffeur);
                preparedStatement.setInt(1, numChauffeur);
                preparedStatement.setString(2, dateDebut);
                preparedStatement.setString(3, dateFin);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void mettreAJourOccupeVoiture(String voiture, String dateDebut, String dateFin) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO occupevoiture (idVoiture, dateDebut, dateFin) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                int idVoiture = obtenirIdVoiture(voiture);
                preparedStatement.setInt(1, idVoiture);
                preparedStatement.setString(2, dateDebut);
                preparedStatement.setString(3, dateFin);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean verifierDisponibiliteChauffeur(String chauffeur, String dateDebut, String dateFin) {
    
    	  try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
    	        LocalDate dateDebutFormatted = LocalDate.parse(dateDebut, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	        LocalDate dateFinFormatted = LocalDate.parse(dateFin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    	        String sql = "SELECT * FROM occupechauffeur WHERE numChauffeur = ? AND dateDebut <= ? AND dateFin >= ?";
    	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    	            int numChauffeur = obtenirNumChauffeur(chauffeur);
    	            preparedStatement.setInt(1, numChauffeur);
    	            preparedStatement.setDate(2, java.sql.Date.valueOf(dateFinFormatted));
    	            preparedStatement.setDate(3, java.sql.Date.valueOf(dateDebutFormatted));

    	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
    	                if (resultSet.next()) {
    	                    showAlert("Le chauffeur n'est pas disponible aux dates spécifiées.");
    	                    return false;
    	                }
    	            }
    	        }
    	    } catch (SQLException | DateTimeParseException e) {
    	        e.printStackTrace();
    	        return false;
    	    }
    	    return true;
    	}
    private boolean verifierDisponibiliteVoiture(String voiture, String dateDebut, String dateFin) {
    	try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            LocalDate dateDebutFormatted = LocalDate.parse(dateDebut, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dateFinFormatted = LocalDate.parse(dateFin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String sql = "SELECT * FROM occupevoiture WHERE idVoiture = ? AND dateDebut <= ? AND dateFin >= ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, obtenirIdVoiture(voiture));
                preparedStatement.setDate(2, java.sql.Date.valueOf(dateFinFormatted));
                preparedStatement.setDate(3, java.sql.Date.valueOf(dateDebutFormatted));

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        showAlert("La voiture n'est pas disponible aux dates spécifiées.");
                        return false;
                    }
                }
            }
        } catch (SQLException | DateTimeParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
	   
    private int obtenirNumChauffeur(String nomChauffeur) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT numChauffeur FROM chauffeurs WHERE nomChauffeur = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nomChauffeur);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("numChauffeur");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si aucun chauffeur n'est trouvé, retournez une valeur par défaut
        return -1;
    }

    private int obtenirIdClient(String emailClient) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT idClient FROM client WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, emailClient);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("idClient");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si aucun client n'est trouvé, retournez une valeur par défaut
        return -1;
    }

    private int obtenirIdVoiture(String nomVoiture) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT idVoiture FROM voituredeluxe WHERE marque = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nomVoiture);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("idVoiture");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si aucune voiture n'est trouvée, retournez une valeur par défaut
        return -1;
    }

  

    public void initData(String marque, double prix) {
        // TODO Auto-generated method stub
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}