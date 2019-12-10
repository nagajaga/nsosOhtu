package ohtu;

import java.util.HashMap;
import ohtu.domain.Work;
import ohtu.dao.Dao;
import ohtu.io.IO;
import ohtu.commands.Add;
import ohtu.commands.Command;
import ohtu.commands.Delete;
import ohtu.commands.Edit;
import ohtu.commands.ListWorks;
import ohtu.commands.Search;
import ohtu.commands.Update;

public class App {

    private IO io;
    private Dao<Work, Integer> dao;
    private HashMap<String, Command> commands;

    public App(IO io, Dao<Work, Integer> dao) {
        this.io = io;
        this.dao = dao;
        commands = new HashMap<>();
        commands.put("A", new Add(io, dao));
        commands.put("L", new ListWorks(io, dao));
        commands.put("S", new Search(io, dao));
        commands.put("U", new Update(io, dao));
        commands.put("E", new Edit(io, dao));
        commands.put("D", new Delete(io, dao));
    }

    public void run() {

        io.println("Hello!");

        while (true) {
            io.print("Add / List / Search / Update current page / Edit / Delete / Quit (A/L/S/U/E/D/Q): ");
            String input = io.nextLine();
            if (input.equalsIgnoreCase("Q")) {
                break;
            } else {
                Command command = commands.get(input.toUpperCase());
                if (command != null) {
                    command.run();
                }
            }

        }
        io.println("\nGoodbye!");
    }
}
