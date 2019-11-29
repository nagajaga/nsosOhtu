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
            System.out.println("Add/List/Edit/Delete/Quit (A/L/E/D/Q)");
            String input = io.nextLine();
            if (input.equals("Q")) {
                break;
            } else if (input.equals("A")) {
                handleAdding();
            } else if (input.equals("L")) {
                handleListing();
            } else if (input.equals("E")) {
                handleEditing();
            } else if (input.equals("D")) {
                handleDeleting();
            }
        }
        System.out.println("Goodbye!");
    }

    private void handleAdding() {
        System.out.println("Author: ");
        String author = io.nextLine();
        System.out.println("Title: ");
        String title = io.nextLine();
        System.out.println("URL: (enter \"-\" if empty)");
        String url = io.nextLine();
        System.out.println("Tags: (separate by \",\" , enter \"-\" if empty)");
        String tags = io.nextLine();
        if (!author.isEmpty() && !title.isEmpty() && !url.isEmpty() && !tags.isEmpty()) {
            dao.create(new Work(author, title, url, tags));
        } else {
            System.out.println("Title and author should not be empty");
        }
    }

    private void handleListing() {
        List<Work> list = dao.list();
        if (list.isEmpty()) {
            System.out.println("No works yet");
        } else {
            System.out.println("All works:\n");
            for (Work work : list) {
                System.out.println(work);
            }
        }
    }
    
    private void handleEditing() {
        //edit functionality here
    }
    
    private void handleDeleting() {
        //delete functionality here
    }
   
}
