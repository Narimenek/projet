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
        listeFactures.add(facture);
    }

    // Méthode pour récupérer toutes les factures
    public List<Facture> getListeFactures() {
        return listeFactures;
    }

    
}
