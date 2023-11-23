package ProjetGL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ListeVoitures {

	 private static final String JDBC_URL = "jdbc:mysql://localhost:3306/luxedrive";
	    private static final String USER = "root";
	    private static final String PASSWORD = "root";

	    public List<Voiture> getVoitures() {
	        List<Voiture> listeVoitures = new ArrayList<>();

	        try {
	            // Pour établir la connexion à la base de données
	            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

	            // requête SQL
	            String sql = "SELECT * FROM voituredeluxe";
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);

	            // Exécuter la requête
	            ResultSet resultSet = preparedStatement.executeQuery();

	            // Parcourir les résultats et ajouter les voitures à la liste
	            while (resultSet.next()) {
	                int idVoiture = resultSet.getInt("idVoiture");
	                String couleur = resultSet.getString("Couleur");
	                String immatriculation = resultSet.getString("immatriculation");
	                String marque = resultSet.getString("marque");
	                float prixLocation = resultSet.getFloat("prixLocation");
	                String lienImg = resultSet.getString("lien_img");

	                // Créer un objet Voiture et l'ajouter à la liste
	                Voiture voiture = new Voiture(idVoiture, couleur, immatriculation, marque, prixLocation, lienImg);
	                listeVoitures.add(voiture);
	            }

	            // Fermer les ressources
	            resultSet.close();
	            preparedStatement.close();
	            connection.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return listeVoitures;
	    }

	    public static void main(String[] args) {
	        ListeVoitures listeVoitures = new ListeVoitures();
	        List<Voiture> voitures = listeVoitures.getVoitures();

	        // Faites quelque chose avec la liste de voitures, par exemple l'afficher
	        for (Voiture voiture : voitures) {
	            System.out.println(voiture);
	        }
	    }
	}