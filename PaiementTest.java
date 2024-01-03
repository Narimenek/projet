package Package1;

import java.util.Date;

public class PaiementTest {

    public static void main(String[] args) {
        // Création d'un client pour le paiement
        Client client = new Client(1, "John", "Doe", "john.doe@email.com", "123456");

        // Création d'une instance de la classe Paiement
        Paiement paiement = new Paiement(1, 100.0, new Date(), 123, "Carte de crédit", 1, client);

        // Insertion du paiement dans la base de données
        paiement.insertPaiement();

        // Récupération du paiement par son ID
        try {
            Paiement paiementRecupere = Paiement.getPaiementById(1);
            System.out.println("Paiement récupéré : " + paiementRecupere.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
