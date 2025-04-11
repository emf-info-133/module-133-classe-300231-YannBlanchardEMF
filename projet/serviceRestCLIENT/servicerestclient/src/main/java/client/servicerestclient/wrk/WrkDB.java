package client.servicerestclient.wrk;

import client.servicerestclient.beans.Menu;
import client.servicerestclient.beans.User;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class WrkDB {
    private final String URL = "jdbc:mysql://localhost:3308/mydb";
    private final String USER = "root";
    private final String PASSWORD = "emf123";

    private Connection connection;

    private BCryptPasswordEncoder passwordEncoder;

    public WrkDB() {
        dbConnect();
        passwordEncoder = new BCryptPasswordEncoder();
    }

    private void dbConnect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion à la DB réussie !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dbDisconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connexion DB fermée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User readUser(String login, String password) {
        if (connection == null) {
            return null;
        }

        try {
            String query = "SELECT * FROM T_Users WHERE login = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashed = rs.getString("mdp");
                if (passwordEncoder.matches(password, hashed)) {
                    User user = new User();
                    user.setPK(rs.getInt("PK_Users"));
                    user.setNom(rs.getString("nom"));
                    user.setPrenom(rs.getString("prenom"));
                    user.setAdmin(rs.getBoolean("isAdmin"));
                    user.setFKEntreprise(rs.getInt("FK_Entreprise"));
                    user.setLogin(rs.getString("login"));
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User getUser(String pk) {
        if (connection == null) {
            return null;
        }

        try {
            String query = "SELECT * FROM T_Users WHERE PK_Users = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, pk);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                User user = new User();
                user.setPK(rs.getInt("PK_Users"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setFKEntreprise(rs.getInt("FK_Entreprise"));
                user.setLogin(rs.getString("login"));
                return user;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        if (connection == null) {
            return users;
        }

        try {
            String query = "SELECT * FROM T_Users";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setPK(rs.getInt("PK_Users"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setAdmin(rs.getBoolean("isAdmin"));
                user.setFKEntreprise(rs.getInt("FK_Entreprise"));
                user.setLogin(rs.getString("login"));
                user.setPassword(null);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User addUser(User user) {
        if (connection == null) {
            return null;
        }

        try {
            connection.setAutoCommit(false);

            // hash du mot de passe
            String hashed = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashed);

            String query = "INSERT INTO T_Users (nom, prenom, isAdmin, FK_Entreprise, mdp, login) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getPrenom());
            stmt.setBoolean(3, user.isAdmin());

            stmt.setNull(4, user.getFKEntreprise());

            stmt.setString(5, user.getPassword()); // stocke le hash
            stmt.setString(6, user.getLogin());

            int rows = stmt.executeUpdate();
            if (rows != 1) {
                connection.rollback();
                return null;
            }

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setPK(rs.getInt(1));
            }

            connection.commit();
            user.setPassword(null);
            return user;

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean ajouterCommande(String login, ArrayList<Menu> menus, float total) {
        if (connection == null) {
            return false;
        }

        try {

            // Démarrer transaction
            connection.setAutoCommit(false);

            // Récupérer l'ID utilisateur
            String sqlGetUserId = "SELECT PK_Users FROM T_Users WHERE login = ?";
            PreparedStatement stmtUser = connection.prepareStatement(sqlGetUserId);
            stmtUser.setString(1, login);
            ResultSet rsUser = stmtUser.executeQuery();

            if (!rsUser.next()) {
                connection.rollback();
                System.out.println("Utilisateur introuvable.");
                return false;
            }

            int userId = rsUser.getInt("PK_Users");

            // Insérer dans T_Commande
            String sqlInsertCommande = "INSERT INTO T_Commande (prix, date, FK_User) VALUES (?, NOW(), ?)";
            PreparedStatement stmtCommande = connection.prepareStatement(sqlInsertCommande,
                    Statement.RETURN_GENERATED_KEYS);
            stmtCommande.setFloat(1, total);
            stmtCommande.setInt(2, userId);
            stmtCommande.executeUpdate();

            // Récupérer l’ID de la commande générée
            ResultSet rsCommande = stmtCommande.getGeneratedKeys();
            if (!rsCommande.next()) {
                connection.rollback();
                System.out.println("Erreur : ID commande non récupéré");
                return false;
            }

            int commandeId = rsCommande.getInt(1);

            // Insérer chaque menu dans TR_CommandeMenu
            String sqlInsertTR = "INSERT INTO TR_CommandeMenus (PFK_Commande, PFK_Menu, quantite) VALUES (?, ?, ?)";
            PreparedStatement stmtTR = connection.prepareStatement(sqlInsertTR);

            for (Menu menu : menus) {
                System.out.println("CommandeId = " + commandeId + ", MenuId = " + menu.getPkMenu() + ", Quantité = "
                        + menu.getQuantite());
                stmtTR.setInt(1, commandeId);
                stmtTR.setInt(2, menu.getPkMenu());
                stmtTR.setInt(3, menu.getQuantite());
                stmtTR.executeUpdate();
            }

            // Valider la transaction
            connection.commit();
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
