/*package controller;

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
	            String query = "SELECT lien_img FROM image WHERE idImage = ?";
	            int voitureId = 2; // Remplacez par l'ID de la voiture que vous souhaitez afficher
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
	                    carImageView.setPreserveRatio(false);
	                    carImageView.setFitWidth(200); // Remplacez YourDesiredWidth par la largeur souhaitée
	                    carImageView.setFitHeight(150); // Remplacez YourDesiredHeight par la hauteur souhaitée
	                    carImageView.setStyle("-fx-background-size: cover;");
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

	


	


}*/
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class HomeController implements Initializable {

	private Parent fxml;

		@FXML
		private AnchorPane root;
		
		@FXML
	    private Label labelNomUtilisateur;

	    private String loggedInUserName;

	    public void setLoggedInUserName(String userName) {
	        this.loggedInUserName = userName;
	        labelNomUtilisateur.setText("Bienvenue,\n" + userName);

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
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/Factures.fxml"));
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
	    @FXML
	    void logout(MouseEvent event) {
	        try {
	            // Charger le fichier FXML associé à Main.fxml
	            fxml = FXMLLoader.load(getClass().getResource("/interfaces/Main.fxml"));

	            // Récupérer la scène à partir du nœud racine (root)
	            AnchorPane currentRoot = (AnchorPane) root.getScene().getRoot();

	            // Remplacer le nœud racine actuel par celui du Main.fxml
	            currentRoot.getChildren().setAll(fxml);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }








	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
