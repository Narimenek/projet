package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ClasseBase.Voiture;



public class AccueilController extends Application {
	 private List<Voiture> voitureList = new ArrayList<>();

    @FXML
    private TextField marqueTextField;

    @FXML
    private TextField modeleTextField;

    @FXML
    private TextField regionTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Voiture> voitureTableView;

    @Override
    public void start(Stage primaryStage) {
        // Initialisez votre interface utilisateur ici

        // Associez des méthodes aux événements de bouton
        addButton.setOnAction(event -> ajouterVoiture());
        deleteButton.setOnAction(event -> supprimerVoiture());
        updateButton.setOnAction(event -> modifierVoiture());
        searchButton.setOnAction(event -> rechercherVoiture());
        TableColumn<Voiture, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idVoiture"));

        TableColumn<Voiture, String> marqueColumn = new TableColumn<>("Marque");
        marqueColumn.setCellValueFactory(new PropertyValueFactory<>("marque"));

        TableColumn<Voiture, String> modeleColumn = new TableColumn<>("Modele");
        modeleColumn.setCellValueFactory(new PropertyValueFactory<>("modele"));

        TableColumn<Voiture, String> regionColumn = new TableColumn<>("Region");
        regionColumn.setCellValueFactory(new PropertyValueFactory<>("region"));

        // Ajoutez d'autres colonnes si nécessaire

        voitureTableView.getColumns().addAll(idColumn, marqueColumn, modeleColumn, regionColumn);

        // Vous devrez également configurer votre TableView pour afficher les données
        // (colonnes, modèle de données, etc.)
    }
    private List<Voiture> chargerDonneesDeLaBaseDeDonnees() {
        List<Voiture> listeVoitures = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/luxedrive", "root", "root")) {
            String query = "SELECT * FROM voiture";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int idVoiture = resultSet.getInt("idVoiture");
                        String couleur = resultSet.getString("marque");
                        String marque= resultSet.getString("marque");
                        String immatriculation = resultSet.getString("immatriculation");
                        float prixLocation= resultSet.getFloat("prixLocation");
                        String lienImg= resultSet.getString("lienImg");
                        Voiture voiture = new Voiture(idVoiture,couleur, immatriculation,marque, prixLocation,lienImg);
                        listeVoitures.add(voiture);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listeVoitures;
    }
    // Méthode pour ajouter une voiture à la base de données
    private void ajouterVoiture() {
        String marque = marqueTextField.getText();
        String modele = modeleTextField.getText();
        String region = regionTextField.getText();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/luxedrive", "root", "")) {
            String query = "INSERT INTO voiture (marque, modele, region) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, marque);
                preparedStatement.setString(2, modele);
                preparedStatement.setString(3, region);

                preparedStatement.executeUpdate();

                // Rafraîchissez votre TableView ou effectuez d'autres actions nécessaires après l'ajout
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer une voiture de la base de données
    private void supprimerVoiture() {
        // Obtenez l'élément sélectionné dans votre TableView
        Voiture selectedVoiture = voitureTableView.getSelectionModel().getSelectedItem();

        if (selectedVoiture != null) {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/votre_bdd", "utilisateur", "mot_de_passe")) {
                String query = "DELETE FROM voiture WHERE id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, selectedVoiture.getId());

                    preparedStatement.executeUpdate();

                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows > 0) {
                        // Rafraîchissez votre TableView ou effectuez d'autres actions nécessaires après la suppression

                        // Supprimez la voiture de la liste
                        voitureList.remove(selectedVoiture);

                        // Rafraîchissez le TableView
                        voitureTableView.getItems().setAll(voitureList);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Affichez un message indiquant qu'aucune voiture n'est sélectionnée
        }
    }

    // Méthode pour modifier une voiture dans la base de données
    private void modifierVoiture() {
        // Obtenez l'élément sélectionné dans votre TableView
        Voiture selectedVoiture = voitureTableView.getSelectionModel().getSelectedItem();

        if (selectedVoiture != null) {
            // Vous pouvez afficher une nouvelle fenêtre ou utiliser des champs de texte pour permettre à l'utilisateur de modifier les données
            // Une fois les modifications effectuées, mettez à jour la base de données et rafraîchissez votre TableView
        } else {
            // Affichez un message indiquant qu'aucune voiture n'est sélectionnée
        }
    }

    // Méthode pour rechercher une voiture dans la base de données
    private void rechercherVoiture() {
        // Implémentez la logique de recherche en fonction des critères fournis par l'utilisateur
        // Mettez à jour votre TableView avec les résultats de la recherche
    }

  


    
}