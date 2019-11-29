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
                handleAdding();
            } else if (input.equalsIgnoreCase("L")) {
                handleListing();
            } else if (input.equalsIgnoreCase("E")) {
                handleEditing();
            } else if (input.equalsIgnoreCase("D")) {
                handleDeleting();
            }
        }
        System.out.println("Goodbye!");
    }

    private void handleAdding() {
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
            dao.create(new Work(author, title, url, tags));
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
            System.out.println("\nAll works:");
            for (Work work : list) {
                System.out.println(work);
            }
            System.out.println("");
        }
    }

    private void handleEditing() {
        //edit functionality here
    }

    private void handleDeleting() {
        //delete functionality here
    }

}
