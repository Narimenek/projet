package Package1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Location {

	private LocalDate datedebut; 
	private LocalDate dateFin;
    private boolean avecChauffeur;
    private String numChauffeur;
    private int idLocation;
    private String idClient;
    private int idVoitureDeLuxe;
    public String StatutLocation;
    
    private static final String url = "jdbc:mysql://localhost:3306/luxedrive";
    private static final String user = "root";
    private static final String passwd = "root";
    
    public void CreeLocation() {
    	try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
            
             String query = "INSERT INTO location (datedebut, datefin, avecchauffeur, numchauffeur, idlocation, idclient, idvoituredeluxe, statutlocation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
             
             try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                 // Set values for the parameters
            	 preparedStatement.setDate(1, Date.valueOf(datedebut));
                 preparedStatement.setDate(2, Date.valueOf(dateFin));
                 preparedStatement.setBoolean(3, avecChauffeur);
                 preparedStatement.setString(4, numChauffeur);
                 preparedStatement.setInt(5, idLocation);
                 preparedStatement.setString(6, idClient);
                 preparedStatement.setInt(7, idVoitureDeLuxe);
                 preparedStatement.setString(8, StatutLocation);

                        preparedStatement.executeUpdate();
             }

         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
 
    public void ConfirmerLocation() {
    	 try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
    	        // Implémentation de la confirmation de location avec JDBC
    	        // Utilisez des requêtes SQL et des PreparedStatement

    	        String query = "UPDATE location SET statutlocation = 'CONFIRMEE' WHERE idlocation = ?";
    	        
    	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    	            // Set the value for the parameter
    	            preparedStatement.setInt(1, idLocation);

    	            // Execute the update
    	            preparedStatement.executeUpdate();
    	        }

    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	}
    public void AnnulerLocation() {
    	 try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
    	        // Implémentation de l'annulation de location avec JDBC
    	        // Utilisez des requêtes SQL et des PreparedStatement

    	        String query = "UPDATE location SET statutlocation = 'ANNULEE' WHERE idlocation = ?";
    	        
    	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    	            // Set the value for the parameter
    	            preparedStatement.setInt(1, idLocation);

    	            // Execute the update
    	            preparedStatement.executeUpdate();
    	        }

    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	}
    public void CalculerLePrix() {
   	 try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
             String tarifQuery = "SELECT prixLocation FROM voituredeluxe WHERE idvoituredeluxe = ?";
             try (PreparedStatement priceStatement = connection.prepareStatement(tarifQuery)) {
                 priceStatement.setInt(1, idVoitureDeLuxe);
                 try (ResultSet resultSet = priceStatement.executeQuery()) {
                     if (resultSet.next()) {
                         double prixJournalier = resultSet.getDouble("prixLocation");

                         long dureeEnJours = ChronoUnit.DAYS.between(datedebut, dateFin);

                         double prixTotal = prixJournalier * dureeEnJours;

                         // Vous pouvez faire ce que vous voulez avec le prix total (par exemple, l'afficher)
                         System.out.println("Le prix total de la location est : " + prixTotal);
                     } else {
                         System.out.println("Tarif non trouvé pour la voiture de luxe avec l'ID : " + idVoitureDeLuxe);
                     }
                 }
             }

         } catch (SQLException e) {
             e.printStackTrace();
         }
     } 
    
    public void VerifierDiponibilitee() {
   	 try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
             // Implémentation de la vérification de disponibilité avec JDBC
             // Utilisez des requêtes SQL et des PreparedStatement

             // Supposons que vous avez une table "locations" dans votre base de données
             String disponibiliteQuery = "SELECT COUNT(*) FROM locations " +
                                        "WHERE idvoituredeluxe = ? " +
                                        "AND ((datedebut >= ? AND datedebut <= ?) OR (datefin >= ? AND datefin <= ?))";
             
             try (PreparedStatement disponibiliteStatement = connection.prepareStatement(disponibiliteQuery)) {
                 // Set values for the parameters
                 disponibiliteStatement.setInt(1, idVoitureDeLuxe);
                 disponibiliteStatement.setDate(2, java.sql.Date.valueOf(datedebut));
                 disponibiliteStatement.setDate(3, java.sql.Date.valueOf(dateFin));
                 disponibiliteStatement.setDate(4, java.sql.Date.valueOf(datedebut));
                 disponibiliteStatement.setDate(5, java.sql.Date.valueOf(dateFin));

                 // Execute the query to check availability
                 try (ResultSet resultSet = disponibiliteStatement.executeQuery()) {
                     if (resultSet.next()) {
                         int count = resultSet.getInt(1);

                         if (count == 0) {
                             System.out.println("La voiture de luxe est disponible pour la période spécifiée.");
                         } else {
                             System.out.println("La voiture de luxe n'est pas disponible pour la période spécifiée.");
                         }
                     }
                 }
             }

         } catch (SQLException e) {
             e.printStackTrace();
         }
    }
}
