package main;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.io.ConsoleIO;
import ohtu.dao.fake.*;
import ohtu.dao.impl.WorkDaoImpl;
import ohtu.db.*;

public class Main {

    public static void main(String[] args) {
        try {
            DatabaseManager dbm = new DatabaseManager();
            dbm.setup("jdbc:sqlite:app.db", "nsos", "snek");
            dbm.createTablesIfAbsent();
            new ohtu.App(new ConsoleIO(), new WorkDaoImpl(dbm)).run();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        new ohtu.App(new ConsoleIO(), new FakeWorkDao()).run();
    }

}
