package main;

import java.util.List;
import java.util.Scanner;
import ohtu.Work;
import ohtu.dao.fake.FakeWorkDao;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static FakeWorkDao dao = new FakeWorkDao();

    public static void main(String[] args) {
        System.out.println("Hello!");

        while (true) {
            System.out.println("Add/List/Exit (A/L/E)");
            String input = scanner.nextLine();
            if (input.equals("E")) {
                break;
            } else if (input.equals("A")) {
                handleAdding();
            } else if (input.equals("L")) {
                handleListing();
            }
        }
        System.out.println("Goodbye!");

    }

    private static void handleAdding() {
        System.out.println("Author: ");
        String author = scanner.nextLine();
        System.out.println("Title: ");
        String title = scanner.nextLine();
        if (!author.isEmpty() && !title.isEmpty()) {
            dao.create(new Work(author, title));
        } else {
            System.out.println("Title and author should not be empty");
        }
    }

    private static void handleListing() {
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
}
