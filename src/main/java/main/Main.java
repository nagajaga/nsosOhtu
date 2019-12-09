package main;

import ohtu.dao.fake.FakeWorkDao;
import ohtu.io.ConsoleIO;

public class Main {

    public static void main(String[] args) {
//        try {
//            DatabaseManager dbm = new DatabaseManager();
//            dbm.setup("jdbc:sqlite:resources/app.db", "nsos", "snek");
//            dbm.createTablesIfAbsent();
//            new ohtu.App(new ConsoleIO(), new WorkDaoImpl(dbm)).run();
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
        new ohtu.App(new ConsoleIO(), new FakeWorkDao()).run();
    }

}
