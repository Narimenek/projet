package Package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//une classe appelée PaiementDAO qui agit comme un objet d'accès aux données (DAO) pour effectuer des opérations de base de données liées à l'entité "paiement". La classe utilise JDBC pour interagir avec une base de données MySQL. 
public class PaiementDAO {

    private static final String url = "jdbc:mysql://localhost:3306/luxedrive";
    private static final String user = "root";
    private static final String passwd = "root";

        public void insertPaiement(Paiement paiement) {
            try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
                String SQL = "INSERT INTO paiement (idPaiement, montant, dateDePaiement, idLocation, typeDePaiement, idClient) " +
                             "VALUES (?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(SQL)) {
                    statement.setInt(1, paiement.getIdPaiement());
                    statement.setDouble(2, paiement.getMontant());
                    statement.setDate(3, new java.sql.Date(paiement.getDateDePaiement().getTime()));
                    statement.setInt(4, paiement.getIdLocation());
                    statement.setString(5, paiement.getTypeDePaiement());
                    statement.setInt(6, paiement.getidClient()); 

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

        public Paiement getPaiementById(int idPaiement) throws SQLException {
            Paiement paiement = null;

            try (Connection connection = DriverManager.getConnection(url, user, passwd)) {
                String SQL = "SELECT * FROM paiement WHERE idPaiement = ?";
                try (PreparedStatement statement = connection.prepareStatement(SQL)) {
                    statement.setInt(1, idPaiement);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            // Créer un objet Paiement à partir des données récupérées de la base de données
                            paiement = new Paiement(
                                    resultSet.getInt("idPaiement"),
                                    resultSet.getDouble("montant"),
                                    resultSet.getDate("dateDePaiement"),
                                    resultSet.getInt("idLocation"),
                                    resultSet.getString("typeDePaiement"),
                                    idPaiement, new Client(resultSet.getInt("idClient"), SQL, SQL, SQL, SQL)
                            );
                        }
                    }
                }
            }

            return paiement;
        }
    }
    

