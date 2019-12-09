package ohtu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class for handling database settings.
 */
public class DatabaseManager {

    private String path;
    private String username;
    private String password;

    public DatabaseManager() {
    }

    /**
     * Sets up the database variables. Run this before openConnection.
     *
     * @param path
     * @param username
     * @param password
     */
    public void setup(String path, String username, String password) {
        this.path = path;
        this.username = username;
        this.password = password;
    }

    /**
     * Sets up the database.
     *
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public void createTablesIfAbsent() throws SQLException, ClassNotFoundException {
        Connection conn = openConnection();
        conn.prepareStatement("create table if not exists Work ("
                + "  id INTEGER PRIMARY KEY,"
                + "  author VARCHAR(128),"
                + "  title VARCHAR(128),"
                + "  code VARCHAR(128),"
                + "  tags VARCHAR(256),"
                + "  type VARCHAR(128),"
                + "  read BOOLEAN,"
                + "  pages INTEGER,"
                + "  current_page INTEGER"
                + ");").executeUpdate();
        conn.close();
    }

    /**
     * Get a connection from DriverManager. Run setup before this.
     *
     * @return
     * @throws SQLException
     */
    public Connection openConnection() throws SQLException, ClassNotFoundException {
        Class.forName ("org.h2.Driver");
        Connection conn = DriverManager.getConnection(path, username, password);
        return conn;
    }
}
