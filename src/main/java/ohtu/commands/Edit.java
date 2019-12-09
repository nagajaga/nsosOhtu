/*
 * @author londes
 */
package ohtu.commands;

import ohtu.dao.Dao;
import ohtu.domain.Work;
import ohtu.domain.WorkType;
import ohtu.io.IO;

public class Edit extends Command {

    public Edit(IO io, Dao<Work, Integer> dao) {
        super(io, dao);
    }

    
    @Override
    public void run() {
        int id = askId("edit");
        if (id >= 0 && dao.read(id) != null) {
            io.println("Keep the old information between \"()\" by pressing enter or type new information to update.");
            handleAdding(id, true);
        } else if (!dao.list().isEmpty()) {
            io.println("Item not found\n");
        }
    }

    private void handleAdding(int id, boolean editing) {
        while (true) {
            Work copy = null;
            WorkType type = null;
            if (editing) {
                copy = dao.read(id);
                if (copy == null) {
                    io.println("Invalid id");
                    break;
                }
            }
            type = askType(editing, copy);
            String author = askAuthor(editing, copy);
            if (author == null) {
                break;
            }
            String title = askTitle(editing, copy);
            if (title == null) {
                break;
            }
            String url = "";
            if (type.equals(WorkType.WEBSITE)) {
                url = askUrl(editing, copy);
                if (url == null) {
                    break;
                }
            }
            int pages = 0;
            if (type.equals(WorkType.BOOK)) {
                while (true) {
                    pages = askPages(editing, copy);
                    if (pages > 0) {
                        break;
                    }
                }
            }
            String tags = askTags(editing, copy);
            if (tags == null) {
                break;
            }
            boolean read = false;
            if (editing) {
                read = askRead(editing, copy);
            }
            createWork(editing, author, title, tags, type, url, pages, read, id);
            io.println("Item saved succesfully\n");
            return;
        }
        io.println("Field must not be blank\n");
    }

}
