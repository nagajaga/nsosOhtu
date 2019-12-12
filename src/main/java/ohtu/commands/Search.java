package ohtu.commands;

import java.util.ArrayList;
import java.util.List;
import ohtu.dao.Dao;
import ohtu.dao.WorkDao;
import ohtu.domain.Work;
import ohtu.io.IO;

public class Search extends Command {

    public Search(IO io, Dao<Work, Integer> dao) {
        super(io, dao);
    }

    @Override
    public void run() {
        io.println("What do you want to look for? Tag / Author / Title / URL (T/A/I/U): \n");
        String input = io.nextLine();
        if (!(input.equalsIgnoreCase("t")
                || input.equalsIgnoreCase("a")
                || input.equalsIgnoreCase("i")
                || input.equalsIgnoreCase("u"))) {
            return;
        }
        java.util.List<Work> list = findWorks(input);
        if (list == null) {
            return;
        } else if (list.isEmpty()) {
            io.println("No results were returned");
        } else {
            io.println("Results:\n");
            for (Work work : list) {
                io.println(work + "\n");
            }
        }
    }

    private List<Work> findWorks(String type) {
        io.println("Enter your search terms (empty string returns):\n");
        List<Work> results = null;
        String query = io.nextLine();
        if (query.isEmpty()) {
            return null;
        }
        if (type.equalsIgnoreCase("t")) {
            results = ((WorkDao) dao).searchByTag(query);
        } else if (type.equalsIgnoreCase("a")) {
            results = ((WorkDao) dao).searchByAuthor(query);
        } else if (type.equalsIgnoreCase("i")) {
            results = ((WorkDao) dao).searchByTitle(query);
        } else if (type.equalsIgnoreCase("u")) {
            results = ((WorkDao) dao).searchByUrl(query);
        } else {
            results = new ArrayList<>();
        }
        return results;
    }
}
