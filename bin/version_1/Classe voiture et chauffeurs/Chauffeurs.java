package ProjetGL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Chauffeurs {

    // Les détails de la connexion à la base de données
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/luxedrive";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Méthode pour récupérer la liste des chauffeurs
    public static void getChauffeurs() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM chauffeurs";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int numChauffeur = resultSet.getInt("numChauffeur");
                        String nomChauffeur = resultSet.getString("nomChauffeur");
                        String prenom = resultSet.getString("Prenom");
                        Date dateNaissance = resultSet.getDate("dateDeNaissance");
                        int idUtilisateur = resultSet.getInt("idUtilisateur");

                        // Faire quelque chose avec les données récupérées
                        System.out.println(
                                "Chauffeur : " + numChauffeur + ", Nom : " + nomChauffeur + ", Prénom : " + prenom
                                        + ", Date de Naissance : " + dateNaissance + ", ID Utilisateur : " + idUtilisateur);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour vérifier la disponibilité d'un chauffeur
    public static boolean isChauffeurDisponible(int numChauffeur, Date dateDebut, Date dateFin) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM location WHERE numChauffeur = ? AND ((? BETWEEN dateDebut AND dateFin) OR (? BETWEEN dateDebut AND dateFin))";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, numChauffeur);
                preparedStatement.setDate(2, dateDebut);
                preparedStatement.setDate(3, dateFin);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return !resultSet.next(); // Renvoie true si le chauffeur est disponible, sinon false
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Exemple d'utilisation
    public static void main(String[] args) {
        getChauffeurs();

        int numChauffeurTest = 1;
        Date dateDebutTest = Date.valueOf("2023-11-24");
        Date dateFinTest = Date.valueOf("2023-11-30");

        boolean chauffeurDisponible = isChauffeurDisponible(numChauffeurTest, dateDebutTest, dateFinTest);
        System.out.println("Chauffeur disponible pour la période spécifiée : " + chauffeurDisponible);
    }
    
    
 // Méthode pour ajouter un nouveau chauffeur
    public static void ajouterChauffeur(String nomChauffeur, String prenom, Date dateNaissance, int idUtilisateur) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO chauffeurs (nomChauffeur, Prenom, dateDeNaissance, idUtilisateur) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nomChauffeur);
                preparedStatement.setString(2, prenom);
                preparedStatement.setDate(3, dateNaissance);
                preparedStatement.setInt(4, idUtilisateur);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
 // Méthode pour récupérer les détails d'un chauffeur par son numéro
    public static void getDetailsChauffeur(int numChauffeur) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM chauffeurs WHERE numChauffeur = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, numChauffeur);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String nomChauffeur = resultSet.getString("nomChauffeur");
                        String prenom = resultSet.getString("Prenom");
                        Date dateNaissance = resultSet.getDate("dateDeNaissance");
                        int idUtilisateur = resultSet.getInt("idUtilisateur");

                        System.out.println(
                                "Chauffeur : " + numChauffeur + ", Nom : " + nomChauffeur + ", Prénom : " + prenom
                                        + ", Date de Naissance : " + dateNaissance + ", ID Utilisateur : " + idUtilisateur);
                    } else {
                        System.out.println("Aucun chauffeur trouvé avec le numéro : " + numChauffeur);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour les détails d'un chauffeur
    public static void updateDetailsChauffeur(int numChauffeur, String nomChauffeur, String prenom, Date dateNaissance, int idUtilisateur) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "UPDATE chauffeurs SET nomChauffeur = ?, Prenom = ?, dateDeNaissance = ?, idUtilisateur = ? WHERE numChauffeur = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nomChauffeur);
                preparedStatement.setString(2, prenom);
                preparedStatement.setDate(3, dateNaissance);
                preparedStatement.setInt(4, idUtilisateur);
                preparedStatement.setInt(5, numChauffeur);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Les détails du chauffeur ont été mis à jour avec succès.");
                } else {
                    System.out.println("Aucun chauffeur trouvé avec le numéro : " + numChauffeur);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un chauffeur par son numéro
    public static void deleteChauffeur(int numChauffeur) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "DELETE FROM chauffeurs WHERE numChauffeur = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, numChauffeur);

                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Le chauffeur a été supprimé avec succès.");
                } else {
                    System.out.println("Aucun chauffeur trouvé avec le numéro : " + numChauffeur);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
}

