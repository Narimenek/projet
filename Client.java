package Package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Client extends Utilisateurs {
	 int compteur=0;
	   private int idClient;
	   private int idUtilisateur;
	    private String nomClient;
	    private String tel;
	    private String email;
	    private String motDePasse;
	    public Location location;
	    private Connection connection;
	     static String url = "jdbc:mysql://localhost/luxedrive";
	   static   String user = "root";
	    static  String passwd = "root";
	    public Client (int id,String n, String t,String email, String mdp) {
	    	this.email=email;
	    	this.motDePasse=mdp;
	    	this.nomClient=n;
	    	this.tel=t;
	    	this.idClient=id;
	    	this.compteur++;

                       
	    }  
	    
	    public void insererClient() {
	       

	        try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
	            String SQL = "INSERT INTO `client`(`idClient`, `nomClient`, `tel`, `email`, `motDePasse`, `idUtilisateur`) " +
	                         "VALUES (?, ?, ?, ?, ?, NULL)";

	            // Création de la requête préparée
	            try (PreparedStatement statement = connection.prepareStatement(SQL)) {
	                // Définition des valeurs à insérer
	                statement.setInt(1, this.idClient); 
	                statement.setString(2, this.nomClient); 
	                statement.setString(3, this.tel); 
	                statement.setString(4, this.email);
	                statement.setString(5, this.motDePasse); 
	                //statement.setNull(idUtilisateur, compteur); 

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
	
	        
	    
	       
	   
	    public boolean ConnexionClient(String email, String motDePasse) {
	    	    try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
	    	        String query = "SELECT * FROM client WHERE email = ? AND motDePasse = ?";
	    	        PreparedStatement statement = connection.prepareStatement(query);
	    	        statement.setString(1, email);
	    	        statement.setString(2, motDePasse);
	    	        ResultSet resultSet = statement.executeQuery();
	    	        String updateQuery = "UPDATE client SET estConnecte = true WHERE email = ?";
	    	        PreparedStatement statement2 = connection.prepareStatement(updateQuery);
	    	        return resultSet.next(); 
	    	   
	    	    } catch (Exception e) {
	    	        e.printStackTrace();
	    	    }
	    	    return false;
	    }
	    public void miseJourInfoClient() {
	    }
	  
	    	public boolean deconnexionClient() {
	    	    try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
	    	        String updateQuery = "UPDATE client SET estConnecte = ? WHERE email = ?";
	    	        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
	    	        updateStatement.setBoolean(1, false); // Marquer le client comme déconnecté
	    	        updateStatement.setString(2, this.email);

	    	        int rowsAffected = updateStatement.executeUpdate();
	    	        return rowsAffected > 0; // Si au moins une ligne a été affectée, la déconnexion a réussi
	    	    } catch (SQLException e) {
	    	        e.printStackTrace();
	    	    }
	    	    return false;
	    	}	    	
	    	
	    public String getNomClient() {
			return nomClient;
		}
		public void setNomClient(String nomClient) {
			this.nomClient = nomClient;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public int getIdClient() {
			return idClient;
		}
		public void setIdClient(int idClient) {
			this.idClient = idClient;
		}
		public String getMotDePasse() {
			return motDePasse;
		}
		public void setMotDePasse(String motDePasse) {
			this.motDePasse = motDePasse;
		}
		public Location getLocation() {
			return location;
		}
		public void setLocation(Location location) {
			this.location = location;
		}
		
		public void SupprimerCompte() {
	        try(Connection connection = DriverManager.getConnection(url, user, passwd)) {
	            String query = "DELETE FROM `client` WHERE `idClient`= ?";
	            PreparedStatement statement = connection.prepareStatement(query);
	            statement.setInt(1, this.getIdClient()); 
	            statement.executeUpdate();
	            System.out.println("Compte client supprimé !");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


