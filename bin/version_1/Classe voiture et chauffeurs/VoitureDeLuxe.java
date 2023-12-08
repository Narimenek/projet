package projet2;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class VoitureDeLuxe {

	
	 private static final String JDBC_URL = "jdbc:mysql://localhost:3306/luxedrive";
	    private static final String USER = "votreUtilisateur";
	    private static final String PASSWORD = "votreMotDePasse";

 private int idVoiture;
 private String couleur;
 private String immatriculation;
 private String marque;
 private float prixLocation;
 private String lienImage;

 // Constructeur
 public VoitureDeLuxe(int idVoiture, String couleur, String immatriculation, String marque, float prixLocation, String lienImage) {
     this.idVoiture = idVoiture;
     this.couleur = couleur;
     this.immatriculation = immatriculation;
     this.marque = marque;
     this.prixLocation = prixLocation;
     this.lienImage = lienImage;
 }

 
 
 public static void ajouterVoiture(String couleur, String immatriculation, String marque, float prixLocation, String lienImage) {
     String query = "INSERT INTO voituredeluxe (Couleur, immatriculation, marque, prixLocation, lien_img) VALUES (?, ?, ?, ?, ?)";

     try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
          PreparedStatement preparedStatement = connection.prepareStatement(query)) {

         preparedStatement.setString(1, couleur);
         preparedStatement.setString(2, immatriculation);
         preparedStatement.setString(3, marque);
         preparedStatement.setFloat(4, prixLocation);
         preparedStatement.setString(5, lienImage);

         preparedStatement.executeUpdate();

     } catch (SQLException e) {
         e.printStackTrace();
     }
 }
 
 
 
 // Méthodes get et set
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

 public float getPrixLocation() {
     return prixLocation;
 }

 public void setPrixLocation(float prixLocation) {
     this.prixLocation = prixLocation;
 }

 public String getLienImage() {
     return lienImage;
 }

 public void setLienImage(String lienImage) {
     this.lienImage = lienImage;
 }

 // Méthode pour récupérer la liste des voitures disponibles pour location
 public static List<VoitureDeLuxe> getVoituresDisponibles(Date dateDebut, Date dateFin) {
     List<VoitureDeLuxe> voituresDisponibles = new ArrayList<>();

     try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
         String query = "SELECT * FROM voituredeluxe WHERE idVoiture NOT IN " +
                        "(SELECT idVoiture FROM location WHERE (? BETWEEN dateDebut AND dateFin) OR (? BETWEEN dateDebut AND dateFin))";
         try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setDate(1, dateDebut);
             preparedStatement.setDate(2, dateFin);

             try (ResultSet resultSet = preparedStatement.executeQuery()) {
                 while (resultSet.next()) {
                     int idVoiture = resultSet.getInt("idVoiture");
                     String couleur = resultSet.getString("Couleur");
                     String immatriculation = resultSet.getString("immatriculation");
                     String marque = resultSet.getString("marque");
                     float prixLocation = resultSet.getFloat("prixLocation");
                     String lienImage = resultSet.getString("lien_img");

                     VoitureDeLuxe voiture = new VoitureDeLuxe(idVoiture, couleur, immatriculation, marque, prixLocation, lienImage);
                     voituresDisponibles.add(voiture);
                 }
             }
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return voituresDisponibles;
 }

// Méthode pour obtenir une voiture par son ID
 public static VoitureDeLuxe getVoitureParID(int idVoiture) {
     VoitureDeLuxe voiture = null;

     try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
         String query = "SELECT * FROM voituredeluxe WHERE idVoiture = ?";
         try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setInt(1, idVoiture);

             try (ResultSet resultSet = preparedStatement.executeQuery()) {
                 if (resultSet.next()) {
                     String couleur = resultSet.getString("Couleur");
                     String immatriculation = resultSet.getString("immatriculation");
                     String marque = resultSet.getString("marque");
                     float prixLocation = resultSet.getFloat("prixLocation");
                     String lienImage = resultSet.getString("lien_img");

                     voiture = new VoitureDeLuxe(idVoiture, couleur, immatriculation, marque, prixLocation, lienImage);
                 }
             }
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return voiture;
 }
 
 
// Méthode pour récupérer les voitures d'une certaine marque
 public static List<VoitureDeLuxe> getVoituresParMarque(String marque) {
     List<VoitureDeLuxe> voituresParMarque = new ArrayList<>();

     try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
         String query = "SELECT * FROM voituredeluxe WHERE marque = ?";
         try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setString(1, marque);

             try (ResultSet resultSet = preparedStatement.executeQuery()) {
                 while (resultSet.next()) {
                     int idVoiture = resultSet.getInt("idVoiture");
                     String couleur = resultSet.getString("Couleur");
                     String immatriculation = resultSet.getString("immatriculation");
                     float prixLocation = resultSet.getFloat("prixLocation");
                     String lienImage = resultSet.getString("lien_img");

                     VoitureDeLuxe voiture = new VoitureDeLuxe(idVoiture, couleur, immatriculation, marque, prixLocation, lienImage);
                     voituresParMarque.add(voiture);
                 }
             }
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return voituresParMarque;
 }

 // Méthode pour calculer la moyenne des prix de location
 public static float calculerMoyennePrix() {
     float moyennePrix = 0;

     try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
         String query = "SELECT AVG(prixLocation) FROM voituredeluxe";
         try (Statement statement = connection.createStatement()) {
             try (ResultSet resultSet = statement.executeQuery(query)) {
                 if (resultSet.next()) {
                     moyennePrix = resultSet.getFloat(1);
                 }
             }
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return moyennePrix;
 }
 
 
// Méthode pour rechercher des voitures par couleur
 public static List<VoitureDeLuxe> rechercherParCouleur(String couleurRecherchee) {
     List<VoitureDeLuxe> voituresParCouleur = new ArrayList<>();

     try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
         String query = "SELECT * FROM voituredeluxe WHERE couleur = ?";
         try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setString(1, couleurRecherchee);

             try (ResultSet resultSet = preparedStatement.executeQuery()) {
                 while (resultSet.next()) {
                     int idVoiture = resultSet.getInt("idVoiture");
                     String couleur = resultSet.getString("Couleur");
                     String immatriculation = resultSet.getString("immatriculation");
                     String marque = resultSet.getString("marque");
                     float prixLocation = resultSet.getFloat("prixLocation");
                     String lienImage = resultSet.getString("lien_img");

                     VoitureDeLuxe voiture = new VoitureDeLuxe(idVoiture, couleur, immatriculation, marque, prixLocation, lienImage);
                     voituresParCouleur.add(voiture);
                 }
             }
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return voituresParCouleur;
 }
 
 
// Méthode pour récupérer les voitures par prix inférieur à une certaine valeur
 public static List<VoitureDeLuxe> getVoituresParPrixInferieur(float prixMax) {
     List<VoitureDeLuxe> voituresParPrix = new ArrayList<>();

     try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
         String query = "SELECT * FROM voituredeluxe WHERE prixLocation < ?";
         try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setFloat(1, prixMax);

             try (ResultSet resultSet = preparedStatement.executeQuery()) {
                 while (resultSet.next()) {
                     int idVoiture = resultSet.getInt("idVoiture");
                     String couleur = resultSet.getString("Couleur");
                     String immatriculation = resultSet.getString("immatriculation");
                     String marque = resultSet.getString("marque");
                     float prixLocation = resultSet.getFloat("prixLocation");
                     String lienImage = resultSet.getString("lien_img");

                     VoitureDeLuxe voiture = new VoitureDeLuxe(idVoiture, couleur, immatriculation, marque, prixLocation, lienImage);
                     voituresParPrix.add(voiture);
                 }
             }
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return voituresParPrix;
 }

 // Méthode pour mettre à jour les informations d'une voiture
 public static void mettreAJourVoiture(int idVoiture, String nouvelleCouleur, float nouveauPrix) {
  

     try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
         String query = "UPDATE voituredeluxe SET Couleur = ?, prixLocation = ? WHERE idVoiture = ?";
         try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setString(1, nouvelleCouleur);
             preparedStatement.setFloat(2, nouveauPrix);
             preparedStatement.setInt(3, idVoiture);

             preparedStatement.executeUpdate();
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }
 
// Méthode pour récupérer la voiture la moins chère
 public static VoitureDeLuxe getVoitureMoinsChere() {
     VoitureDeLuxe voitureMoinsChere = null;

     try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
         String query = "SELECT * FROM voituredeluxe ORDER BY prixLocation ASC LIMIT 1";
         try (Statement statement = connection.createStatement()) {
             try (ResultSet resultSet = statement.executeQuery(query)) {
                 if (resultSet.next()) {
                     int idVoiture = resultSet.getInt("idVoiture");
                     String couleur = resultSet.getString("Couleur");
                     String immatriculation = resultSet.getString("immatriculation");
                     String marque = resultSet.getString("marque");
                     float prixLocation = resultSet.getFloat("prixLocation");
                     String lienImage = resultSet.getString("lien_img");

                     voitureMoinsChere = new VoitureDeLuxe(idVoiture, couleur, immatriculation, marque, prixLocation, lienImage);
                 }
             }
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return voitureMoinsChere;
 }
 
 
 // Méthode pour récupérer les voitures par une plage de prix
 public static List<VoitureDeLuxe> getVoituresParPlageDePrix(float prixMin, float prixMax) {
     List<VoitureDeLuxe> voituresParPlageDePrix = new ArrayList<>();

     try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
         String query = "SELECT * FROM voituredeluxe WHERE prixLocation BETWEEN ? AND ?";
         try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setFloat(1, prixMin);
             preparedStatement.setFloat(2, prixMax);

             try (ResultSet resultSet = preparedStatement.executeQuery()) {
                 while (resultSet.next()) {
                     int idVoiture = resultSet.getInt("idVoiture");
                     String couleur = resultSet.getString("Couleur");
                     String immatriculation = resultSet.getString("immatriculation");
                     String marque = resultSet.getString("marque");
                     float prixLocation = resultSet.getFloat("prixLocation");
                     String lienImage = resultSet.getString("lien_img");

                     VoitureDeLuxe voiture = new VoitureDeLuxe(idVoiture, couleur, immatriculation, marque, prixLocation, lienImage);
                     voituresParPlageDePrix.add(voiture);
                 }
             }
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return voituresParPlageDePrix;
 }
 
 
// Méthode pour vérifier la disponibilité d'une voiture par son ID
 public static boolean verifierDisponibilite(int idVoiture) {
     boolean estDisponible = true;
   

     try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
         String query = "SELECT * FROM location WHERE idVoiture = ? AND StatutLocation = 'En cours'";
         try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setInt(1, idVoiture);

             try (ResultSet resultSet = preparedStatement.executeQuery()) {
                 if (resultSet.next()) {
                     estDisponible = false; // La voiture est déjà louée
                 }
             }
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return estDisponible;
 }
	
	
	
	
}
