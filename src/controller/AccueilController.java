package controller;
/*
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import classeBase.Voiture;
public class AccueilController {
  @FXML
    private ListView<Voiture> voitureListView;  // Modifiez le type de la ListView
    // Méthode d'initialisation du contrôleur
    public void initialize() {
        // Récupérer les données depuis la base de données
        List<Voiture> voitureList = getVoituresFromDatabase();
        // Afficher la liste dans la ListView avec images
        voitureListView.setItems(FXCollections.observableArrayList(voitureList));
        voitureListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Voiture> call(ListView<Voiture> param) {
                return new ListCell<>() {
                    private final ImageView imageView = new ImageView();
                    @Override
                    protected void updateItem(Voiture voiture, boolean empty) {
                        super.updateItem(voiture, empty);
                        if (empty || voiture == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(voiture.getMarque() + " - $" + voiture.getPrixLocation());
                            Image image = new Image(voiture.getLienImg());
                            imageView.setImage(image);
                            imageView.setFitWidth(100);  // Ajustez la largeur de l'image comme vous le souhaitez
                            imageView.setFitHeight(100); // Ajustez la hauteur de l'image comme vous le souhaitez
                            setGraphic(imageView);
                        }
                    }
                };
            }
        });
    }
    private List<Voiture> getVoituresFromDatabase() {
        List<Voiture> voitureList = new ArrayList<>();
        // Remplacez les informations de connexion à la base de données
        String jdbcUrl = "jdbc:mysql://localhost/luxedrive";
        String user = "root";
        String password = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {
            String query = "SELECT * FROM voituredeluxe";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Remplacez les noms de colonnes par ceux de votre base de données
                    String immatriculation = resultSet.getString("immatriculation");
                    String marque = resultSet.getString("marque");
                    int idVoiture = resultSet.getInt("idVoiture");
                    String couleur = resultSet.getString("couleur");
                    String lienImg = resultSet.getString("lien_img");
                    double prixLocation = resultSet.getDouble("prixLocation");
                    Voiture voiture = new Voiture(idVoiture, couleur, immatriculation, marque, prixLocation, lienImg);
                    voitureList.add(voiture);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voitureList;
    }*/

	/*  String jdbcUrl = "jdbc:mysql://localhost/luxedrive";
      String user = "root";
      String password = "";
		public void start(Stage primaryStage) {
	        FlowPane root = new FlowPane();
	        Scene scene = new Scene(root, 800, 600);
	        primaryStage.setTitle("Image Display Test");
	        primaryStage.setScene(scene);
	        // Charger les images depuis la base de données
	        loadImages(root);
	        primaryStage.show();
	    }
	    private void loadImages(FlowPane root) {
	        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {
	            String query = "SELECT lien_img FROM voituredeluxe";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
	                 ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    // Lire les données binaires de l'image
	                    byte[] imageData = resultSet.getBytes("lien_img");
	                    // Convertir les données binaires en une image
	                    Image image = new Image(new java.io.ByteArrayInputStream(imageData));
	                    // Créer une ImageView pour afficher l'image
	                    ImageView imageView = new ImageView(image);
	                    imageView.setFitWidth(200); // Ajuster la largeur selon vos besoins
	                    imageView.setFitHeight(200); // Ajuster la hauteur selon vos besoins
	                    // Ajouter l'ImageView au conteneur
	                    root.getChildren().add(imageView);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }*/


/*	   @FXML
	    private FlowPane rootPane;
	    public void initialize() {
	        // Charger les images depuis la base de données
	        loadImages();
	    }
	    private void loadImages() {
	        String jdbcUrl = "jdbc:mysql://localhost/luxedrive";
	        String user = "root";
	        String password = "";
	        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {
	            String query = "SELECT lien_img FROM voiture";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
	                 ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    byte[] imageData = resultSet.getBytes("lien_img");
	                    if (imageData != null && imageData.length > 0) {
	                        // Affichez le nombre d'octets pour le débogage
	                        System.out.println("Nombre d'octets dans l'image : " + imageData.length);
	                        // Convertir les données binaires en une image
	                        Image image = new Image(new ByteArrayInputStream(imageData));
	                        // Créer une ImageView pour afficher l'image
	                        ImageView imageView = new ImageView(image);
	                        imageView.setFitWidth(200); // Ajuster la largeur selon vos besoins
	                        imageView.setFitHeight(200); // Ajuster la hauteur selon vos besoins
	                        // Ajouter l'ImageView au conteneur
	                        rootPane.getChildren().add(imageView);
	                    } else {
	                        System.out.println("Données d'image vides.");
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
	import javafx.scene.image.ImageView;
	import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.ByteArrayInputStream;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	public class AccueilController {
		  @FXML
		    private FlowPane rootPane;
		    public void initialize() {
		        // Charger les images depuis la base de données
		        loadImages();
		    }
		    private void loadImages() {
		        String jdbcUrl = "jdbc:mysql://localhost/luxedrive";
		        String user = "root";
		        String password = "";
		        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {
		            String query = "SELECT lien_img, marque, prixLocation FROM voiture";
		            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
		                 ResultSet resultSet = preparedStatement.executeQuery()) {
		                int carsPerRow = 3;
		                int carCount = 0;
		                while (resultSet.next()) {
		                    byte[] imageData = resultSet.getBytes("lien_img");
		                    if (imageData != null && imageData.length > 0) {
		                        Image image = new Image(new ByteArrayInputStream(imageData));
		                        // Créer une VBox pour chaque voiture avec marque et prix
		                        VBox carInfo = new VBox();
		                        carInfo.setAlignment(Pos.CENTER);
		                        carInfo.setSpacing(5);
		                        ImageView imageView = new ImageView(image);
		                        imageView.setFitWidth(100);  // Ajuster la largeur de l'image
		                        imageView.setFitHeight(100); // Ajuster la hauteur de l'image
		                        String marque = resultSet.getString("marque");
		                        double prix = resultSet.getDouble("prixLocation");
		                        carInfo.getChildren().addAll(imageView, new Text(marque), new Text("$" + prix));
		                        // Ajouter la VBox au FlowPane
		                        rootPane.getChildren().add(carInfo);
		                        // Espacement horizontal entre les voitures
		                        if (++carCount % carsPerRow != 0) {
		                            rootPane.setHgap(10);
		                        }
		                    } else {
		                        System.out.println("Données d'image vides.");
		                    }
		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
	    */
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccueilController {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox rootPane;

    public void initialize() {
        scrollPane.setContent(rootPane);
        scrollPane.setPannable(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Afficher toujours la barre de défilement verticale

        // Charger les images depuis la base de données
        loadImages();
    }

    private void loadImages() {
        String jdbcUrl = "jdbc:mysql://localhost/luxedrive";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {
            String query = "SELECT lien_img, marque, prixLocation FROM voituredeluxe";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                int carsPerRow = 3;
                int carCount = 0;

                HBox currentHBox = new HBox();
                currentHBox.setAlignment(Pos.CENTER);
                currentHBox.setSpacing(40);
                rootPane.getChildren().add(currentHBox);

                while (resultSet.next()) {
                    byte[] imageData = resultSet.getBytes("lien_img");

                    if (imageData != null && imageData.length > 0) {
                        Image image = new Image(new ByteArrayInputStream(imageData));

                        // Créer une VBox pour chaque voiture avec marque et prix
                        VBox carInfo = new VBox();
                        carInfo.setAlignment(Pos.CENTER);
                        carInfo.setSpacing(10); // Espace supplémentaire

                        ImageView imageView = new ImageView(image);
                        imageView.setFitWidth(100);  // Ajuster la largeur de l'image
                        imageView.setFitHeight(100); // Ajuster la hauteur de l'image

                        String marque = resultSet.getString("marque");
                        double prix = resultSet.getDouble("prixLocation");

                        // Ajouter un événement de clic à l'image
                        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> redirectToLocationPage(marque, prix));

                        carInfo.getChildren().addAll(imageView, new Text(marque), new Text("$" + prix));

                        // Ajoutez la VBox de la voiture à la HBox actuelle
                        currentHBox.getChildren().add(carInfo);

                        carCount++;

                        // Si le nombre actuel de voitures est divisible par carsPerRow,
                        // créez une nouvelle HBox et ajoutez-la à VBox
                        if (carCount % carsPerRow == 0) {
                            currentHBox = new HBox();
                            currentHBox.setAlignment(Pos.CENTER);
                            currentHBox.setSpacing(20);
                            rootPane.getChildren().add(currentHBox);
                        }
                    } else {
                        System.out.println("Données d'image vides.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void redirectToLocationPage(String marque, double prix) {
        try {
            // Charger la page location.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/Location.fxml"));
            Parent locationPage = loader.load();
            LocationController locationController = loader.getController();

            // Passer les données nécessaires au controller de la page location
            locationController.initData(marque, prix);

            // Remplacer la scène actuelle par la nouvelle scène
            Stage primaryStage = (Stage) rootPane.getScene().getWindow();
            primaryStage.setScene(new Scene(locationPage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 }