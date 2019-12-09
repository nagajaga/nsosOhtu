package main;

import ohtu.dao.fake.FakeWorkDao;
import ohtu.dao.impl.WorkDaoImpl;
import ohtu.io.ConsoleIO;
//import ohtu.db.DatabaseManager;

public class Main {

    public static void main(String[] args) {
        new ohtu.App(new ConsoleIO(), new FakeWorkDao()).run();
        /*DatabaseManager dbm = new DatabaseManager();
        dbm.setup("jdbc:h2:.", "nsos", "snek");
        new ohtu.App(new ConsoleIO(), new WorkDaoImpl(dbm)).run();*/
    }

}
