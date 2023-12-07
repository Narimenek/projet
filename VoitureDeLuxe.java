package Package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VoitureDeLuxe {

    private int idVoiture;
    private String couleur;
    private String immatriculation;
    private  String marque;
    private String lien_img;
	private double prixLocation;
    private String statutDeDisponibilite;
    static String url = "jdbc:mysql://localhost/luxedrive";
	static   String user = "root";
	static  String passwd = "root";
    public  VoitureDeLuxe(int id, String couleur, String immatriculation, String marque, double d, String img) {
        this.idVoiture = id;
        this.couleur = couleur;
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.prixLocation = d;
        this.lien_img = img;
      
    }
    // get et set
    public String getLien_img() {
		return lien_img;
	}
	public void setLien_img(String lien_img) {
		this.lien_img = lien_img;
	}
	public int getIdVoiture() {
		return idVoiture;
	}
	public void setIdVoiture(int idVoiture) {
		this.idVoiture = idVoiture;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public String getImmatriculation() {
		return immatriculation;
	}
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public double getPrixLocation() {
		return prixLocation;
	}
	public void setPrixLocation(float prixLocation) {
		this.prixLocation = prixLocation;
	}
	public String getStatutDeDisponibilite() {
		return statutDeDisponibilite;
	}
	public void setStatutDeDisponibilite(String statutDeDisponibilite) {
		this.statutDeDisponibilite = statutDeDisponibilite;
	}
	// fin des get et set
    public void insererVoiture() {
	       

        try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
            String SQL = "INSERT INTO `voituredeluxe`(`idVoiture`, `Couleur`, `immatriculation`, `marque`, `prixLocation`, `lien_img`)"
            		+ " VALUES (?,?,?,?,?,?)";
            
            

            // Création de la requête préparée
            try (PreparedStatement statement = connection.prepareStatement(SQL)) {
                // Définition des valeurs à insérer
                statement.setInt(1, this.idVoiture); 
                statement.setString(2, this.couleur); 
                statement.setString(3, this.immatriculation); 
                statement.setString(4, this.marque);
                statement.setDouble(5, this.prixLocation);
                statement.setString(6, this.lien_img); 

                // Exécution de la requête
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Données insérées avec succès !");
                } else {
                    System.out.println("Aucune donnée n'a été insérée.");
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
   
	 public void SupprimerVoiture(int id) { 
		 try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
			 Connection connection1 = TestConn.connexionDB();
	            String sql = "DELETE FROM voituredeluxe WHERE idVoiture = ?";
	            try (PreparedStatement statement = connection1.prepareStatement(sql)) {
	                statement.setInt(1, idVoiture);
	                int rowsDeleted = statement.executeUpdate();
	                if (rowsDeleted > 0) {
	                    System.out.println("La voiture a été supprimée avec succès !");
	                } else {
	                    System.out.println("La voiture avec cet ID n'a pas été trouvée.");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 public void RechercheVoiture(int idVoiture) {
		    String sql = "SELECT * FROM voituredeluxe WHERE idVoiture = ?";
		    Connection connection = null;
		    try {
		        connection = DriverManager.getConnection(url, user, passwd);
		        PreparedStatement statement = connection.prepareStatement(sql);

		        statement.setInt(1, this.idVoiture);

		        ResultSet resultSet = statement.executeQuery();
		        while (resultSet.next()) {

		            idVoiture = resultSet.getInt("idVoiture");
		            String marque = resultSet.getString("marque");
		            String Couleur = resultSet.getString("Couleur");
		            float prixLocation = resultSet.getFloat("prixLocation");

		            System.out.println("ID: " + idVoiture + ", Marque: " + marque + ", Couleur: " + Couleur + ", prixLocation: " + prixLocation);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (connection != null) {
		                connection.close(); // Fermer la connexion après utilisation
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		}

	 
	 /*public void RechercheVoiture() {
    	 String sql = "SELECT * FROM voituredeluxe WHERE idVoiture = ?";
         Connection connection = null;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
             ResultSet resultSet = statement.executeQuery();
             while (resultSet.next()) {
                 // Traitez les résultats de la recherche
                 int idVoiture = resultSet.getInt("id");
                 String marque = resultSet.getString("marque");
                 String Couleur=  resultSet.getString("Couleur");
                 float prixLocation= resultSet.getFloat("prixLocation");
        
                 System.out.println("ID: " + idVoiture + ", Marque: " + marque+ ", Couleur: " + Couleur +", prixLocation: " +prixLocation );
             }
         }
     catch (SQLException e) {
         e.printStackTrace();}
     }
    public void VerifierDisponibilitee() {
    	
    	
    }

  */

}

