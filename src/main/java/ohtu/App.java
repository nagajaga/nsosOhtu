package ohtu;

import java.util.List;
import ohtu.dao.Dao;
import ohtu.io.IO;

public class App {

    private IO io;
    private Dao dao;

    public App(IO io, Dao dao) {
        this.io = io;
        this.dao = dao;
    }

    public void run() {

        io.println("Hello!");

        while (true) {
            io.print("Add/List/Edit/Delete/Quit (A/L/E/D/Q): ");
            String input = io.nextLine();
            if (input.equalsIgnoreCase("Q")) {
                break;
            } else if (input.equalsIgnoreCase("A")) {
                handleAdding(-1, false);
            } else if (input.equalsIgnoreCase("L")) {
                handleListing();
            } else if (input.equalsIgnoreCase("E")) {
                handleEditing();
            } else if (input.equalsIgnoreCase("D")) {
                handleDeleting();
            }
        }
        io.println("\nGoodbye!");
    }

    private void handleAdding(int id, boolean editing) {
        while (true) {
            io.print("Author: ");
            String author = io.nextLine();
            if (author.isEmpty()) {
                break;
            }
            io.print("Title: ");
            String title = io.nextLine();
            if (title.isEmpty()) {
                break;
            }
            io.print("URL (enter \"-\" if empty): ");
            String url = io.nextLine();
            if (url.isEmpty()) {
                break;
            }
            io.print("Tags (separate by \",\" , enter \"-\" if empty): ");
            String tags = io.nextLine();
            if (tags.isEmpty()) {
                break;
            }
            if (editing) {
                Work work = new Work(author, title, url, tags);
                if (dao.update(work, id) == null) {
                    io.println("Unexpected error\n");
                }
            } else {
                dao.create(new Work(author, title, url, tags));
            }
            io.println("Item saved succesfully\n");
            return;
        }
        io.println("Field must not be blank\n");
    }

    private void handleListing() {
        List<Work> list = dao.list();
        if (list.isEmpty()) {
            io.println("No works yet\n");
        } else {
            io.println("\nAll works:\n");
            for (Work work : list) {
                io.println(work + "\n");
            }
        }
    }

    private void handleEditing() {
        int id = askId("edit");
        if (id >= 0 && dao.read(id) != null) {
            handleAdding(id, true);
        } else if (!dao.list().isEmpty()) {
            io.println("Item not found\n");
        }
    }

    private void handleDeleting() {
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

    private int askId(String method) {
        List<Work> list = dao.list();
        if (list.isEmpty()) {
            io.println("No works yet\n");
        } else {
            io.println("\nEnter the id of the item you want to " + method + ":\n");
            for (Work work : list) {
                io.println("id: " + work.getId() + "\n" + work + "\n");
            }
            io.print("id: ");
            int id = -1;
            try {
                id = Integer.parseInt(io.nextLine());
            } catch (Exception e) {
                io.println("Incorrect input\n");
            }
            return id;
        }
        return -1;
    }
}
