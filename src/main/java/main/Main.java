package main;

import ohtu.dao.fake.FakeWorkDao;
import ohtu.io.ConsoleIO;

public class Main {

    public static void main(String[] args) {
        new ohtu.App(new ConsoleIO(), new FakeWorkDao()).run();
    }

}
