package Package1;
import java.sql.Date;

public class FactureTest {


	    public static void main(String[] args) {
	        // Création d'une instance de la classe Paiement pour associer à la facture
	        Paiement paiement = new Paiement(1, 100.0, new Date(System.currentTimeMillis()), 123, "Carte de crédit", 456, new Client(0, null, null, null, null));

	        // Création d'une instance de la classe Facture
	        Facture facture = new Facture(1, new Date(System.currentTimeMillis()), 100.0, paiement);

	        // Affichage des informations de la facture avant le paiement
	        afficherInfoFacture(facture);

	        // Marquer la facture comme payée
	        facture.marquerCommePayee();

	        // Affichage des informations de la facture après le paiement
	        afficherInfoFacture(facture);

	        // Génération d'une nouvelle facture associée à ce paiement
	        Facture nouvelleFacture = facture.genererFacture();
	        System.out.println("Nouvelle facture générée : " + nouvelleFacture.toString());
	    }

	    // Méthode pour afficher les informations de la facture
	    private static void afficherInfoFacture(Facture facture) {
	        System.out.println("ID Facture : " + facture.getIdFacture());
	        System.out.println("Date d'émission : " + facture.getDateEmission());
	        System.out.println("Montant total : " + facture.getMontantTotal());
	        System.out.println("Est payée : " + facture.isEstPayee());
	        System.out.println("ID Paiement associé : " + facture.getPaiement().getIdPaiement());
	      //  System.out.println("------------------------");
	    }
	}


