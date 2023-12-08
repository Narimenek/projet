package Package1;

import java.sql.Date;

public class Facture {
	
	    private int idFacture;
	    private Date dateEmission;
	    private double montantTotal;
	    private boolean estPayee;
	    private Paiement paiement; // Supposant que chaque facture est associée à un paiement

	    // Constructeur
	    public Facture(int idFacture, Date dateEmission, double montantTotal, Paiement paiement) {
	        this.idFacture = idFacture;
	        this.dateEmission = dateEmission;
	        this.montantTotal = montantTotal;
	        this.paiement = paiement;
	        this.estPayee = false; // Par défaut, la facture n'est pas payée lors de sa création
	    }

	    // Getters et Setters
	    public int getIdFacture() {
	        return idFacture;
	    }

	    public Date getDateEmission() {
	        return dateEmission;
	    }

	    public double getMontantTotal() {
	        return montantTotal;
	    }

	    public boolean isEstPayee() {
	        return estPayee;
	    }

	    public void setEstPayee(boolean estPayee) {
	        this.estPayee = estPayee;
	    }

	    public Paiement getPaiement() {
	        return paiement;
	    }

	    // Méthode pour marquer la facture comme payée
	    public void marquerCommePayee() {
	        if (estPayee) {
	            System.out.println("La facture " + idFacture + " a déjà été payée.");
	        } else {
	            System.out.println("La facture " + idFacture + " a été marquée comme payée.");
	            estPayee = true;
	        }
	    }

	    public Facture genererFacture() {
	        // Logique pour générer une facture associée à ce paiement
	        Facture nouvelleFacture = new Facture(idFacture, dateEmission, montantTotal, paiement/* paramètres de la facture */);
	        return nouvelleFacture;
	    }  
	
}
