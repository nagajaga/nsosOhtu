/*
 * @author londes
 */
package ohtu.commands;

import java.util.List;
import java.util.regex.Pattern;
import ohtu.dao.Dao;
import ohtu.domain.Work;
import ohtu.domain.WorkType;
import ohtu.io.IO;

public abstract class Command {

    protected IO io;
    protected Dao<Work, Integer> dao;

    protected Command(IO io, Dao<Work, Integer> dao) {
        this.io = io;
        this.dao = dao;
    }

    public abstract void run();

    protected int askId(String method) {
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

    protected WorkType askType(boolean editing, Work copy) {
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

    protected String askAuthor(boolean editing, Work copy) {
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

    protected String askTitle(boolean editing, Work copy) {
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

    protected String askUrl(boolean editing, Work copy) {
        String url = "";
        io.print("URL: ");
        if (editing) {
            io.print("(" + ((copy.getCode() == null) ? "" : copy.getCode()) + ") ");
        }
        url = io.nextLine();
        if (editing && url.equalsIgnoreCase("")) {
            url = copy.getCode();
        }
        if (!editing && url.isEmpty()) {
            return null;
        }
        return url;
    }

    protected String askTags(boolean editing, Work copy) {
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

    protected boolean askRead(boolean editing, Work copy) {
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

    protected Integer askPages(boolean editing, Work copy) {
        int pages = 0;
        io.print("Enter total number of pages: ");
        if (editing) {
            io.print("(" + copy.getPages() + ") ");
        }
        String input = io.nextLine();
        Pattern pattern = Pattern.compile("\\d+");
        if (editing && input.equalsIgnoreCase("")) {
            pages = copy.getPages();
        } else if (pattern.matcher(input).matches()) {
            pages = Integer.parseInt(input);
        }
        return pages;
    }

    protected void createWork(boolean editing, String author, String title, String tags, WorkType type, String url, int pages, boolean read, int id) {
        if (editing) {
            Work work = new Work(author, title, tags, pages, type);
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
                dao.create(new Work(author, title, url, pages, tags, type));
            } else if (type.equals(WorkType.BOOK)) {
                dao.create(new Work(author, title, tags, pages, type));
            }
        }
    }
}
