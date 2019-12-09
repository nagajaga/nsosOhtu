/*
 * @author londes
 */
package ohtu.commands;

import ohtu.dao.Dao;
import ohtu.domain.Work;
import ohtu.io.IO;

public class Delete extends Command {

    public Delete(IO io, Dao<Work, Integer> dao) {
        super(io, dao);
    }

    @Override
    public void run() {
        int id = askId("delete");
        if (id >= 0 && dao.read(id) != null) {
            if (dao.delete(id)) {
                io.println("Item removed succesfully\n");
            } else {
                io.println("Unexpected error\n");
            }
        } else if (!dao.list().isEmpty()) {
            io.println("Item not found\n");
        }
    }

}
