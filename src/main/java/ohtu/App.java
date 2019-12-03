package ohtu;

import java.util.List;
import java.util.ArrayList;

import ohtu.Domain.Book;
import ohtu.Domain.Website;
import ohtu.Domain.Work;
import ohtu.dao.Dao;
import ohtu.Domain.WorkType;
import ohtu.dao.fake.FakeWorkDao;
import ohtu.io.IO;

public class App {

    private IO io;
    private Dao<Work, Integer> dao;

    public App(IO io, Dao<Work, Integer> dao) {
        this.io = io;
        this.dao = dao;
    }

    public void run() {

        io.println("Hello!");

        while (true) {
            io.print("Add/List/Search/Edit/Delete/Quit (A/L/S/E/D/Q): ");
            String input = io.nextLine();
            if (input.equalsIgnoreCase("Q")) {
                break;
            } else if (input.equalsIgnoreCase("A")) {
                handleAdding(-1, false);
            } else if (input.equalsIgnoreCase("L")) {
                handleListing();
            } else if (input.equalsIgnoreCase("S")) {
                handleSearching();
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
            String tags = askTags(editing, copy);
            if (tags == null) {
                break;
            }
            boolean read = false;
            if (editing) {
                read = askRead(editing, copy);
            }
            createWork(editing, author, title, tags, type, url, read, id);
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
            io.print("All/Read/Unread (A/R/U): ");
            String subList = io.nextLine();
            WorkType type = WorkType.NULL;
            boolean anyType = false;
            io.print("Which category? Any/Website/Book (A/W/B): ");
            String typeString = io.nextLine();
            if (typeString.equalsIgnoreCase("W")) {
                type = WorkType.WEBSITE;
            } else if (typeString.equalsIgnoreCase("B")) {
                type = WorkType.BOOK;
            } else if (typeString.equalsIgnoreCase("A")) {
                anyType = true;
            } else {
                return;
            }
            if (subList.equalsIgnoreCase("A")) {
                io.println("\nAll works:\n");
                for (Work work : list) {
                    if (anyType) {
                        io.println(work + "\n");
                    } else if (work.getType() == type) {
                        io.println(work + "\n");
                    }
                }
            } else if (subList.equalsIgnoreCase("R")) {
                io.println("\nRead works:\n");
                for (Work work : list) {
                    if (work.getRead()) {
                        if (anyType) {
                            io.println(work + "\n");
                        } else if (work.getType() == type) {
                            io.println(work + "\n");
                        }
                    }
                }
            } else if (subList.equalsIgnoreCase("U")) {
                io.println("\nUnread works:\n");
                for (Work work : list) {
                    if (!work.getRead()) {
                        if (anyType) {
                            io.println(work + "\n");
                        } else if (work.getType() == type) {
                            io.println(work + "\n");
                        }
                    }
                }
            }
        }
    }

    private void handleSearching() {
        List<Work> list = findWorks("tag");
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

    private void handleEditing() {
        int id = askId("edit");
        if (id >= 0 && dao.read(id) != null) {
            io.println("Keep the old information between \"()\" by pressing enter or type new information to update.");
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

    /*
     * TODO: create interface WorkDao for FakeWorkDao and PersistentWorkDao
     */
    private List<Work> findWorks(String type) {
        io.println("Enter the " + type + " you want to look for (empty string returns):\n");
        List<Work> results = null;
        String query = io.nextLine();
        if (query.isEmpty()) {
            return null;
        }
        if (type.equals("tag")) {
            results = ((FakeWorkDao) dao).searchByTag(query);
        } else {
            results = new ArrayList<>();
        }
        return results;
    }

    private WorkType askType(boolean editing, Work copy) {
        WorkType type = null;
        while (true) {
            io.print("Which category? Website/Book (W/B): ");
            if (editing) {
                if (copy.getType().equals(WorkType.WEBSITE)) {
                    io.print("(W) ");
                } else if (copy.getType().equals(WorkType.BOOK)) {
                    io.print("(B) ");
                }
            }
            String typeString = io.nextLine();
            if (editing && typeString.equalsIgnoreCase("")) {
                type = copy.getType();
                break;
            }
            if (typeString.equalsIgnoreCase("W")) {
                type = WorkType.WEBSITE;
                break;
            } else if (typeString.equalsIgnoreCase("B")) {
                type = WorkType.BOOK;
                break;
            }
        }
        return type;
    }

    private String askAuthor(boolean editing, Work copy) {
        io.print("Author: ");
        if (editing) {
            io.print("(" + copy.getAuthor() + ") ");
        }
        String author = io.nextLine();
        if (editing && author.equalsIgnoreCase("")) {
            author = copy.getAuthor();
        }
        if (!editing && author.isEmpty()) {
            return null;
        }
        return author;
    }

    private String askTitle(boolean editing, Work copy) {
        io.print("Title: ");
        if (editing) {
            io.print("(" + copy.getTitle() + ") ");
        }
        String title = io.nextLine();
        if (editing && title.equalsIgnoreCase("")) {
            title = copy.getTitle();
        }
        if (!editing && title.isEmpty()) {
            return null;
        }
        return title;
    }

    private String askUrl(boolean editing, Work copy) {
        String url = "";
        io.print("URL: ");
        if (editing) {
            io.print("(" + ((copy.getUrl() == null) ? "" : copy.getUrl()) + ") ");
        }
        url = io.nextLine();
        if (editing && url.equalsIgnoreCase("")) {
            url = copy.getUrl();
        }
        if (!editing && url.isEmpty()) {
            return null;
        }
        return url;
    }

    private String askTags(boolean editing, Work copy) {
        io.print("Tags (separate by \",\" , enter \"-\" if empty): ");
        if (editing) {
            io.print("(" + copy.getTags() + ") ");
        }
        String tags = io.nextLine();
        if (editing && tags.equalsIgnoreCase("")) {
            tags = copy.getTags();
        }
        if (!editing && tags.isEmpty()) {
            return null;
        }
        return tags;
    }

    private boolean askRead(boolean editing, Work copy) {
        boolean read = false;
        io.print("Mark as read? (enter \"Y\" if read): ");
        if (editing) {
            io.print(copy.getRead() ? "(Y) " : "(NO) ");
        }
        String input = io.nextLine();
        if (editing && input.equalsIgnoreCase("")) {
            read = copy.getRead();
        } else if (input.equalsIgnoreCase("Y")) {
            read = true;
        }
        return read;
    }

    private void createWork(boolean editing, String author, String title, String tags, WorkType type, String url, boolean read, int id) {
        if (editing) {
            Work work = new Book(author, title, tags, type);
            if (type.equals(WorkType.WEBSITE)) {
                work.setUrl(url);
            }
            work.setRead(read);
            work.setId(id);
            if (dao.update(work) == null) {
                io.println("Unexpected error\n");
            }
        } else {
            if (type.equals(WorkType.WEBSITE)) {
                dao.create(new Website(author, title, url, tags, type));
            } else if (type.equals(WorkType.BOOK)) {
                dao.create(new Book(author, title, tags, type));
            }
        }
    }
}
