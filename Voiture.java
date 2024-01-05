package classeBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Voiture {
    private int idVoiture;
    private String couleur;
    private String immatriculation;
    private String marque;
    private double prixLocation;
    private String lienImg;

    // Constructeur
    public Voiture(int idVoiture, String couleur, String immatriculation, String marque, double prixLocation2, String lienImg) {
        this.idVoiture = idVoiture;
        this.couleur = couleur;
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.prixLocation = prixLocation2;
        this.lienImg = lienImg;
    }
   
   

    // Ajoutez les getters et les setters appropriés pour chaque propriété

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

    public String getLienImg() {
        return lienImg;
    }

    public void setLienImg(String lienImg) {
        this.lienImg = lienImg;
    }

    // Méthode statique pour récupérer les informations sur les voitures depuis la base de données
    public static List<Voiture> getInformationsVoitures() {
        List<Voiture> voitures = new ArrayList<>();

        // Utilisez les informations de connexion appropriées
        String url = "jdbc:mysql://localhost:3306/luxedrive";
        String user = "root";
        String passwd = "";

       
       
        try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
            String query = "SELECT idVoiture, lien_img, marque, prixLocation FROM voituredeluxe";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int idVoiture = resultSet.getInt("idVoiture");
                    String lienImg = resultSet.getString("lien_img");
                    String marque = resultSet.getString("marque");
                    float prixLocation = resultSet.getFloat("prixLocation");

                    Voiture voiture = new Voiture(idVoiture, "", "", marque, prixLocation, lienImg);
                    voitures.add(voiture);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return voitures;
    }

    // Méthode principale (main) pour tester la récupération des informations sur les voitures
    public static void main(String[] args) {
        List<Voiture> voitures = getInformationsVoitures();

        for (Voiture voiture : voitures) {
            System.out.println("ID: " + voiture.getIdVoiture());
            System.out.println("Marque: " + voiture.getMarque());
            System.out.println("Prix: " + voiture.getPrixLocation());
            System.out.println("Image: " + voiture.getLienImg());
            System.out.println("---------------");
        }
    }
}
