package Package1;

import java.util.List;

public class FacturesTest {

    public static void main(String[] args) {
        // Création d'une instance de la classe Factures
        Factures factures = new Factures();

        // Création de quelques factures pour les tests
        Facture facture1 = new Facture(1, null, 100.0, null);
        Facture facture2 = new Facture(2, null, 150.0, null);
        Facture facture3 = new Facture(3, null, 200.0, null);

        // Ajout des factures à la liste
        factures.ajouterFacture(facture1);
        factures.ajouterFacture(facture2);
        factures.ajouterFacture(facture3);

        // Affichage de toutes les factures
        afficherToutesLesFactures(factures.getListeFactures());
    }

    // Méthode pour afficher toutes les factures
    private static void afficherToutesLesFactures(List<Facture> factures) {
        System.out.println("Liste des factures :");
        for (Facture facture : factures) {
            System.out.println("ID Facture : " + facture.getIdFacture());
            System.out.println("Montant total : " + facture.getMontantTotal());
            System.out.println("------------------------");
        }
    }
}
