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
    private Client Client;
    
    private static final String url = "jdbc:mysql://localhost:3306/luxedrive";
    private static final String user = "root";
    private static final String passwd = "root";
    
    // Constructeur de la classe
    public Paiement(int idPaiement, double montant, Date dateDePaiement, int idLocation, String typeDePaiement, int idClient,Client client) {
        this.idPaiement = idPaiement;
        this.montant = montant;
        this.dateDePaiement = dateDePaiement;
        this.idLocation = idLocation;
        this.typeDePaiement = typeDePaiement;
        this.idClient =idClient;
        this.Client= client;
        
    }
    
    public void insertPaiement() {
        // Création d'une instance de PaiementDAO pour effectuer l'insertion
    	 PaiementDAO paiementDAO = new PaiementDAO();
         paiementDAO.insertPaiement(this);
     
	        try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
	            // Définition de la requête SQL pour l'insertion des données de paiement

	            String SQL = "INSERT INTO paiement (idPaiement, montant, dateDePaiement, idLocation, typeDePaiement, client) " +
	                         "VALUES (?, ?, ?, ?, ?, ?)";

	            // Création de la requête
	            try (PreparedStatement statement = connection.prepareStatement(SQL)) {
	                // Définition des valeurs à insérer
	                statement.setInt(1, this.idPaiement); 
	                statement.setDouble(2, this.montant); 
	                statement.setDate(3, (java.sql.Date) this.dateDePaiement); 
	                statement.setInt(4, this.idLocation);
	                statement.setString(5, this.typeDePaiement); 
	                statement.setInt(6, this.idClient); 

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
    
    public static Paiement getPaiementById(int idPaiement) throws SQLException {
        PaiementDAO paiementDAO = new PaiementDAO();
        // Utilisation de la méthode getPaiementById de PaiementDAO pour récupérer le paiement par son ID

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
	    
	    public boolean verifierPaiement() throws SQLException {
	        // Récupérer les informations du paiement depuis la base de données
	        PaiementDAO paiementDAO = new PaiementDAO(); //  une classe DAO pour accéder à la base de données
	        Paiement paiementEnBase = paiementDAO.getPaiementById(idPaiement);

	        // Vérifier si le paiement existe dans la base de données
	        if (paiementEnBase == null) {
	            System.out.println("Le paiement " + idPaiement + " n'existe pas dans base de données.");
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
			//  établir une liaison entre la classe Paiement et la classe Facture 
	        //  générer une facture associée au paiement
	        Facture nouvelleFacture = new Facture(idLocation, null, montant, null/* paramètres de la facture */);
	        return nouvelleFacture;
	    }

		

		
}
	    
	    

	  

