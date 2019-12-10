package ohtu.commands;

import java.util.regex.Pattern;
import ohtu.dao.Dao;
import ohtu.domain.Work;
import ohtu.domain.WorkType;
import ohtu.io.IO;

public class Update extends Command {

    public Update(IO io, Dao<Work, Integer> dao) {
        super(io, dao);
    }

    @Override
    public void run() {
        int id = askId("update the current page for");
        if (id >= 0 && dao.read(id) != null) {
            Work copy = dao.read(id);
            if (copy == null) {
                io.println("Invalid id\n");
                return;
            } else if (copy.getType() != WorkType.BOOK) {
                io.println("That is not a book :)\n");
                return;
            }
            int updateWith = askCurrentPage(copy);
            if (updateWith != copy.getCurrentPage()) {
                copy.setCurrentPage(updateWith);
                if (dao.update(copy) == null) {
                    io.println("Unexpected error\n");
                    return;
                }
                io.println("Current page successfully updated\n");
            } else {
                io.println("Failed updating the current page\n");
            }
            
        } else {
            io.println("Item not found\n");
        }
    }

    private Integer askCurrentPage(Work copy) {
        int page = copy.getCurrentPage();
        io.print("Last time you were on page " + copy.getCurrentPage() + "/" + copy.getPages() + ". Leaving the field empty cancels. Enter a new page number: ");
        String input = io.nextLine();
        Pattern pattern = Pattern.compile("\\d+");
        if (input.equalsIgnoreCase("")) {
            page = copy.getCurrentPage();
        } else if (pattern.matcher(input).matches()) {
            page = Integer.parseInt(input);
            if (page > copy.getPages() && page >= 0) {
                io.println("Invalid page number");
                page = copy.getCurrentPage();
            }
        }
        return page;
    }

}
