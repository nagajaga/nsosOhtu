package ohtu.commands;

import ohtu.dao.Dao;
import ohtu.domain.Work;
import ohtu.io.IO;

public class Search extends Command {

    public Search(IO io, Dao<Work, Integer> dao) {
        super(io, dao);
    }

    @Override
    public void run() {
        java.util.List<Work> list = findWorks("tag");
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

}
