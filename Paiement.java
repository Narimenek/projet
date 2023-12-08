package Package1;

import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Paiement {
    private int idPaiement;
    private double montant;
    private Date dateDePaiement;
    private int idLocation;
    private String typeDePaiement;
    private int  idClient;
    
    private static final String url = "jdbc:mysql://localhost:3306/luxedrive";
    private static final String user = "root";
    private static final String passwd = "root";
    
    
    public Paiement(int idPaiement, double montant, Date dateDePaiement, int idLocation, String typeDePaiement, int idClient) {
        this.idPaiement = idPaiement;
        this.montant = montant;
        this.dateDePaiement = dateDePaiement;
        this.idLocation = idLocation;
        this.typeDePaiement = typeDePaiement;
        this.idClient =idClient;
    }
    
    public void insertPaiement() {
    	 PaiementDAO paiementDAO = new PaiementDAO();
         paiementDAO.insertPaiement(this);
     
	        try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
	            String SQL = "INSERT INTO paiement (idPaiement, montant, dateDePaiement, idLocation, typeDePaiement, client) " +
	                         "VALUES (?, ?, ?, ?, ?, ?)";

	            // Création de la requête préparée
	            try (PreparedStatement statement = connection.prepareStatement(SQL)) {
	                // Définition des valeurs à insérer
	                statement.setInt(1, this.idPaiement); 
	                statement.setDouble(2, this.montant); 
	                statement.setDate(3, (java.sql.Date) this.dateDePaiement); 
	                statement.setInt(4, this.idLocation);
	                statement.setString(5, this.typeDePaiement); 
	                statement.setInt(5, this.idClient); 

	                // Exécution de la requête
	                int rowsAffected = statement.executeUpdate();
	                if (rowsAffected > 0) {
	                    System.out.println("Données insérées avec succès !");
	                } else {
	                    System.out.println("Aucune donnée n'a été insérée.");
	                }
	            }
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	    }
    
    public static Paiement getPaiementById(int idPaiement) {
        PaiementDAO paiementDAO = new PaiementDAO();
        return paiementDAO.getPaiementById(idPaiement);
    }
    
    
    public int getIdPaiement() {
		return idPaiement;
	} 

	public void setIdPaiement(int idPaiement) {
		this.idPaiement = idPaiement;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Date getDateDePaiement() {
		return dateDePaiement;
	}

	public void setDateDePaiement(Date dateDePaiement) {
		this.dateDePaiement = dateDePaiement;
	}

	public int getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}

	public String getTypeDePaiement() {
		return typeDePaiement;
	}

	public void setTypeDePaiement(String typeDePaiement) {
		this.typeDePaiement = typeDePaiement;
	}

	public int getidClient() {
		return idClient;
	}

	public void setidClient(int client) {
		this.idClient = client;
	}
	
	 public void ValiderPaiement() {
		 System.out.println("Validation du paiement " + idPaiement + " effectuée avec succès.");
	    }
	    
	    public void AnnulerPaiement() {
	    	System.out.println("Annulation du paiement " + idPaiement + " effectuée avec succès.");
	    }
	    
	    public boolean verifierPaiement() {
	        // Récupérer les informations du paiement depuis la base de données
	        // (Ceci est une simulation, vous devriez remplacer cela par une vraie requête à votre base de données)
	        PaiementDAO paiementDAO = new PaiementDAO(); // Suppose une classe DAO (Data Access Object) pour accéder à la base de données
	        Paiement paiementEnBase = paiementDAO.getPaiementById(idPaiement);

	        // Vérifier si le paiement existe en base de données
	        if (paiementEnBase == null) {
	            System.out.println("Le paiement " + idPaiement + " n'existe pas en base de données.");
	            return false;
	        }

	        // Vérifier si le paiement est déjà validé ou annulé
	        if (paiementEnBase.isValide() || paiementEnBase.isAnnule()) {
	            System.out.println("Le paiement " + idPaiement + " a déjà été traité (validé ou annulé).");
	            return false;
	        }

	        // Vérifier si la date de paiement est antérieure à la date actuelle
	        Date dateActuelle = new Date();
	        if (paiementEnBase.getDateDePaiement().after(dateActuelle)) {
	            System.out.println("La date de paiement du paiement " + idPaiement + " est dans le futur.");
	            return false;
	        }

	        // Si toutes les vérifications passent, le paiement est considéré comme vérifié
	        System.out.println("Le paiement " + idPaiement + " a été vérifié avec succès.");
	        return true;
	    }
	    
	    private boolean isValide() {
						return false;
		}

		private boolean isAnnule() {
						return false;
		}

		public Facture genererFacture() {
	        // Logique pour générer une facture associée à ce paiement
	        Facture nouvelleFacture = new Facture(idLocation, null, montant, null/* paramètres de la facture */);
	        return nouvelleFacture;
	    }

		

		
}
	    
	    

	  

