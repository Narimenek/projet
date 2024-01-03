package Package1;

import java.time.LocalDate;

public class LocationTestMain {


	public static void main(String[] args) {
	    LocationTestMain locationTest = new LocationTestMain();
	    locationTest.testCreeLocation();
	    locationTest.testConfirmerLocation();
	    locationTest.testAnnulerLocation();
	    locationTest.testCalculerLePrix();
	    locationTest.testVerifierDisponibilitee();
	}

		private void testCreeLocation() {
		    Location location = new Location();
		    location.setDatedebut(LocalDate.now());
		    location.setDateFin(LocalDate.now().plusDays(5));
		    location.setAvecChauffeur(true);
		    location.setNumChauffeur("123");
		    location.setIdLocation(1);
		    location.setIdClient("Client1");
		    location.setIdVoitureDeLuxe(101);
		    location.setStatutLocation("EN_ATTENTE");

		    location.CreeLocation();
		}
 


		private void testConfirmerLocation() {
		    Location location = new Location();
		    // Initialisez les propriétés de location nécessaires pour la confirmation
		    location.setIdLocation(1);

		    location.ConfirmerLocation();
		}

		private  void testAnnulerLocation() {
		    Location location = new Location();
		    // Initialisez les propriétés de location nécessaires pour l'annulation
		    location.setIdLocation(1);

		    location.AnnulerLocation();
		}

		private  void testCalculerLePrix() {
		    Location location = new Location();
		    // Initialisez les propriétés de location nécessaires pour le calcul du prix
		    location.setIdVoitureDeLuxe(101);
		    location.setDatedebut(LocalDate.now());
		    location.setDateFin(LocalDate.now().plusDays(5));

		    location.CalculerLePrix();
		}

		private void testVerifierDisponibilitee() {
		    Location location = new Location();
		    // Initialisez les propriétés de location nécessaires pour la vérification de disponibilité
		    location.setIdVoitureDeLuxe(101);
		    location.setDatedebut(LocalDate.now());
		    location.setDateFin(LocalDate.now().plusDays(5));

		    location.VerifierDiponibilitee();
		}
	}


