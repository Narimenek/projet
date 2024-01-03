package Package1;
import java.util.ArrayList;
import java.util.List;

public class Factures {
    private List<Facture> listeFactures;

    public  Factures() {
        this.listeFactures = new ArrayList<>();
    }

    // Méthode pour ajouter une facture à la liste
    public void ajouterFacture(Facture facture) {
        // Vérifier si une facture avec le même ID existe déjà
        if (!factureExiste(facture.getIdFacture())) {
            listeFactures.add(facture);
        } else {
            System.out.println("Une facture avec le même ID existe déjà.");
        }
    }

    // Méthode pour récupérer toutes les factures
    public List<Facture> getListeFactures() {
        return listeFactures;
    }
    
    // Méthode pour vérifier si une facture avec l'ID spécifié existe déjà
    private boolean factureExiste(int idFacture) {
        for (Facture f : listeFactures) {
            if (f.getIdFacture() == idFacture) {
                return true;
            }
        }
        return false;
    }

    
}
