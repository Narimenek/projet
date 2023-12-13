package Package1;

import java.sql.Date;
import java.sql.SQLException;

public class PaiementTest {
	 public static void main(String[] args) {
	        // Création d'une instance de la classe Client 
	        Client client = new Client();

	        // Création d'une instance de la classe Paiement
	        Paiement paiement = new Paiement(1, 100.0, new Date(0, 0, 0), 123, "Carte de crédit", 456, client);

	        // Appel de la méthode insertPaiement pour insérer le paiement dans la base de données
	        paiement.insertPaiement();

	        // Appel de la méthode getPaiementById pour récupérer le paiement par son ID
	        try {
	            Paiement paiementRecupere = Paiement.getPaiementById(1);
	            System.out.println("Paiement récupéré : " + paiementRecupere.toString());
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        // Appel de quelques autres méthodes pour tester
	        paiement.ValiderPaiement();
	        paiement.AnnulerPaiement();

	        try {
	            paiement.verifierPaiement();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        // Génération d'une facture associée au paiement
	        Facture facture = paiement.genererFacture();
	        System.out.println("Facture générée : " + facture.toString());
	    }
	}

