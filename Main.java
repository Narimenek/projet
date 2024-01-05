package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;  

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/interfaces/Main.fxml"));
            Scene scene = new Scene(root);
            
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);  
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
/*public class Main extends Application {

	   public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	    public void start(Stage primaryStage) {
	        // Remplacez les informations de connexion à la base de données
	        String jdbcUrl = "jdbc:mysql://localhost/luxedrive";
	        String user = "root";
	        String password = "";

	        // Remplacez la requête pour extraire un lien d'image de la base de données
	        String query = "SELECT donnees_image FROM images LIMIT 1";

	        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
	             PreparedStatement preparedStatement = connection.prepareStatement(query);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            if (resultSet.next()) {
	                String imageUrl = resultSet.getString("donnees_image");

	                Image image = new Image(imageUrl);
	                ImageView imageView = new ImageView(image);

	                // Utilisez un StackPane pour afficher l'ImageView
	                StackPane root = new StackPane(imageView);

	                Scene scene = new Scene(root, 800, 600);
	                primaryStage.setTitle("Image Display Test");
	                primaryStage.setScene(scene);
	                primaryStage.show();
	            } else {
	                System.out.println("Aucun lien d'image trouvé dans la base de données.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }*/
	/*  String jdbcUrl = "jdbc:mysql://localhost/luxedrive";
      String user = "root";
      String password = "";
	    public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
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
	            String query = "SELECT donnees_image FROM images";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
	                 ResultSet resultSet = preparedStatement.executeQuery()) {

	                while (resultSet.next()) {
	                    // Lire les données binaires de l'image
	                    byte[] imageData = resultSet.getBytes("donnees_image");

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
	

