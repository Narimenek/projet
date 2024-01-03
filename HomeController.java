package controller;

import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class HomeController implements Initializable {

	private Parent fxml;
	
		@FXML
		private StackPane root;

	    @FXML
	    private ImageView carImageView; // Ajout d'un ImageView pour afficher les photos de voiture

	    private Connection connection;

	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	        // Connexion à la base de données
	        String url = "jdbc:mysql://localhost/luxedrive";
	        String user = "root";
	        String passwd = "";
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/Home.fxml"));
	        try {
	            connection = DriverManager.getConnection(url, user, passwd);

	            // Charger une photo de voiture depuis la base de données (vous devez adapter cette requête à votre modèle de données)
	            String query = "SELECT lien_img FROM voituredeluxe WHERE idVoiture = ?";
	            int voitureId = 3; // Remplacez par l'ID de la voiture que vous souhaitez afficher
	            PreparedStatement statement = connection.prepareStatement(query);
	            statement.setInt(1, voitureId);

	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                // Récupérer l'image depuis la base de données
	                Blob blob = resultSet.getBlob("lien_img");
	                if (blob != null) {
	                    byte[] imageBytes = blob.getBytes(1, (int) blob.length());
	                    Image carImage = new Image(new ByteArrayInputStream(imageBytes));
	                    carImageView.setImage(carImage);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	    @FXML
	    void accueil(MouseEvent event) {
	    	try {
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/Accueil.fxml"));
				root.getChildren().removeAll();
				root.getChildren().setAll(fxml);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	

	    }

	    @FXML
	    void contrat(MouseEvent event) {
	    	try {
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/Contrat.fxml"));
				root.getChildren().removeAll();
				root.getChildren().setAll(fxml);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }

	    @FXML
	    void facture(MouseEvent event) {
	    	try {
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/Facture.fxml"));
				root.getChildren().removeAll();
				root.getChildren().setAll(fxml);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }

	    @FXML
	    void historiques(MouseEvent event) {
	    	try {
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/Historique.fxml"));
				root.getChildren().removeAll();
				root.getChildren().setAll(fxml);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }

	    @FXML
	    void locataire(MouseEvent event) {
	    	try {
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/Locataire.fxml"));
				root.getChildren().removeAll();
				root.getChildren().setAll(fxml);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }

	    @FXML
	    void location(MouseEvent event) {
	    	try {
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/Location.fxml"));
				root.getChildren().removeAll();
				root.getChildren().setAll(fxml);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }

	    @FXML
	    void voiture(MouseEvent event) {
	    	try {
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/Voiture.fxml"));
				root.getChildren().removeAll();
				root.getChildren().setAll(fxml);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }

	


	


}
