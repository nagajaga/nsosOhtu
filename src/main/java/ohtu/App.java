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

        System.out.println("Hello!");

        while (true) {
            System.out.print("Add/List/Edit/Delete/Quit (A/L/E/D/Q): ");
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
        System.out.println("\nGoodbye!");
    }

    private void handleAdding(int id, boolean editing) {
        while (true) {
            System.out.print("Author: ");
            String author = io.nextLine();
            if (author.isEmpty()) {
                break;
            }
            System.out.print("Title: ");
            String title = io.nextLine();
            if (title.isEmpty()) {
                break;
            }
            System.out.print("URL (enter \"-\" if empty): ");
            String url = io.nextLine();
            if (url.isEmpty()) {
                break;
            }
            System.out.print("Tags (separate by \",\" , enter \"-\" if empty): ");
            String tags = io.nextLine();
            if (tags.isEmpty()) {
                break;
            }
            if (editing) {
                Work work = new Work(author, title, url, tags);
                if (dao.update(work, id) == null) {
                    System.out.println("Unexpected error\n");
                }
            } else {
                dao.create(new Work(author, title, url, tags));
            }
            System.out.println("Item saved succesfully\n");
            return;
        }
        System.out.println("Field must not be blank\n");
    }

    private void handleListing() {
        List<Work> list = dao.list();
        if (list.isEmpty()) {
            System.out.println("No works yet\n");
        } else {
            System.out.println("\nAll works:\n");
            for (Work work : list) {
                System.out.println(work + "\n");
            }
        }
    }

    private void handleEditing() {
        int id = askId("edit");
        if (id >= 0 && dao.read(id) != null) {
            handleAdding(id, true);
        } else if (!dao.list().isEmpty()) {
            System.out.println("Item not found\n");
        }
    }

    private void handleDeleting() {
        int id = askId("delete");
        if (id >= 0 && dao.read(id) != null) {
            if (dao.delete(id)) {
                System.out.println("Item removed succesfully\n");
            } else {
                System.out.println("Unexpected error\n");
            }
        } else if (!dao.list().isEmpty()) {
            System.out.println("Item not found\n");
        }
    }

    private int askId(String method) {
        List<Work> list = dao.list();
        if (list.isEmpty()) {
            System.out.println("No works yet\n");
        } else {
            System.out.println("\nEnter the item you want to " + method + " by id:\n");
            for (Work work : list) {
                System.out.println("id: " + work.getId() + "\n" + work + "\n");
            }
            System.out.print("id: ");
            int id = -1;
            try {
                id = Integer.parseInt(io.nextLine());
            } catch (Exception e) {
                System.out.println("Incorrect input\n");
            }
            return id;
        }
        return -1;
    }
}
