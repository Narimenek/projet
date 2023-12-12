package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * Le contrôleur HomeController gère les actions liées à la page d'accueil.
 */

public class HomeController implements Initializable {
	
	private Parent fxml;
	
		@FXML
		private AnchorPane root;
		
		/**
	     * Action associée au clic sur le bouton "Accueil".
	     * Charge la vue d'accueil.
	     */
	
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
	    
	    /**
	     * Action associée au clic sur le bouton "contrat".
	     * Charge la vue de la page Contrat.
	     */


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
	    
	    /**
	     * Action associée au clic sur le bouton "facture".
	     * Charge la vue de la page facture.
	     */

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
	    
	    /**
	     * Action associée au clic sur le bouton "historiques".
	     * Charge la vue de la page Historique.
	     */
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
	    /**
	     * Action associée au clic sur le bouton "locataire".
	     * Charge la vue de la page Locataire.
	     */

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
	    /**
	     * Action associée au clic sur le bouton "Location".
	     * Charge la vue de la page Location.
	     */

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
	    /**
	     * Action associée au clic sur le bouton "Logement".
	     * Charge la vue de la page logement.
	     */


	    @FXML
	    void logement(MouseEvent event) {
	    	try {
				fxml = FXMLLoader.load(getClass().getResource("/interfaces/Voiture.fxml"));
				root.getChildren().removeAll();
				root.getChildren().setAll(fxml);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }

	


	    /**
	     * Initialisation du contrôleur lors de son chargement.
	     */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
