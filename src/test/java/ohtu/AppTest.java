package ohtu;

import ohtu.io.*;
import ohtu.dao.fake.FakeWorkDao;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

    private FakeWorkDao dao;
    private StubIO io;

    @Before
    public void before() {
        dao = new FakeWorkDao();
        io = new StubIO("");
    }

    @Test
    public void appStartsAndStops() {
        io = new StubIO("q");
        new App(io, dao).run();
        assertEquals("Hello!", io.outputs.get(0));
        assertTrue(containsIgnoreCase(new String[]{"Add", "List", "Search", "Update current page", "Edit", "Delete", "Quit"}, io.outputs.get(1)));
        assertEquals("\nGoodbye!", io.outputs.get(2));
    }

    @Test
    public void appDoesNotRunInvalidCommands() {
        io = new StubIO("abc", "q");
        new App(io, dao).run();
        assertEquals("Hello!", io.outputs.get(0));
        assertTrue(containsIgnoreCase(new String[]{"Add", "List", "Search", "Update current page", "Edit", "Delete", "Quit"}, io.outputs.get(1)));
        assertTrue(containsIgnoreCase(new String[]{"Add", "List", "Search", "Update current page", "Edit", "Delete", "Quit"}, io.outputs.get(2)));
    }

    @Test
    public void noWorksStored() {
        io = new StubIO("l", "e", "d", "q");
        new App(io, dao).run();
        assertEquals("No works yet\n", io.outputs.get(2));
        assertEquals("No works yet\n", io.outputs.get(4));
        assertEquals("No works yet\n", io.outputs.get(6));
    }

    @Test
    public void addingWebsiteWorks() {
        io = new StubIO("a", "w", "testAuthor", "testTitle", "testUrl", "testTag", "q");
        new App(io, dao).run();
        assertTrue(containsIgnoreCase(new String[]{"Add", "List", "Search", "Update current page", "Edit", "Delete", "Quit"}, io.outputs.get(1)));
        assertEquals("Which category? Website/Book (W/B): ", io.outputs.get(2));
        assertEquals("Author: ", io.outputs.get(3));
        assertEquals("Title: ", io.outputs.get(4));
        assertEquals("URL: ", io.outputs.get(5));
        assertEquals("Tags (separate by \",\" , enter \"-\" if empty): ", io.outputs.get(6));
        assertEquals("Item saved succesfully\n", io.outputs.get(7));

    }

    @Test
    public void addingBookWorks() {
        io = new StubIO("a", "b", "testAuthor", "testTitle", "200", "testTag", "q");
        new App(io, dao).run();
        assertTrue(containsIgnoreCase(new String[]{"Add", "List", "Search", "Update current page", "Edit", "Delete", "Quit"}, io.outputs.get(1)));
        assertEquals("Which category? Website/Book (W/B): ", io.outputs.get(2));
        assertEquals("Author: ", io.outputs.get(3));
        assertEquals("Title: ", io.outputs.get(4));
        assertEquals("Enter total number of pages: ", io.outputs.get(5));
        assertEquals("Tags (separate by \",\" , enter \"-\" if empty): ", io.outputs.get(6));
        assertEquals("Item saved succesfully\n", io.outputs.get(7));
    }

    @Test
    public void listingAllWorks() {
        io = new StubIO("a", "w", "testAuthor", "testTitle", "testUrl", "testTag", "l", "a", "a", "q");
        new App(io, dao).run();
        assertEquals("[All]/Read/Unread/Cancel ([A]/R/U/C): ", io.outputs.get(9));
        assertEquals("Which category? [Any]/Website/Book/Cancel ([A]/W/B/C): ", io.outputs.get(10));
        assertEquals("Website\ntestAuthor: testTitle\nURL: testUrl\nTags: testTag\n", io.outputs.get(12));
    }

    @Test
    public void editingBookWorks() {
        io = new StubIO("a", "b", "testAuthor", "testTitle", "200", "testTag", "e", "0", "b", "newAuthor", "newTitle", "250", "newTag", "y", "l", "a", "b", "q");
        new App(io, dao).run();
        assertEquals("\nEnter the id of the item you want to edit:\n", io.outputs.get(9));
        assertEquals("Book\nnewAuthor: newTitle\nPages: 250\nCurrent page: 1\nTags: newTag\n", io.outputs.get(30));
    }

    @Test
    public void listingUnreadWorks() {
        io = new StubIO("a", "w", "testAuthor", "testTitle", "testUrl", "testTag", "l", "u", "w", "q");
        new App(io, dao).run();
        assertEquals("[All]/Read/Unread/Cancel ([A]/R/U/C): ", io.outputs.get(9));
        assertEquals("Which category? [Any]/Website/Book/Cancel ([A]/W/B/C): ", io.outputs.get(10));
        assertEquals("\nUnread works:\n", io.outputs.get(11));
        assertEquals("Website\ntestAuthor: testTitle\nURL: testUrl\nTags: testTag\n", io.outputs.get(12));
    }

    @Test
    public void listingReadBookWorks() {
        io = new StubIO("a", "b", "testAuthor", "testTitle", "200", "testTag", "e", "0", "b", "newAuthor", "newTitle", "250", "newTag", "y", "l", "a", "b", "l", "r", "b", "q");
        new App(io, dao).run();
        assertEquals("\nRead works:\n", io.outputs.get(34));
        assertEquals("Book\nnewAuthor: newTitle\nPages: 250\nCurrent page: 1\nTags: newTag\n", io.outputs.get(35));
    }

    @Test
    public void searchByTagWorks() {
        io = new StubIO("a", "b", "testAuthor", "testTitle", "200", "testTag", "s", "t", "testTag", "q");
        new App(io, dao).run();
        assertEquals("Enter your search terms (empty string returns):\n", io.outputs.get(10));
    }

    @Test
    public void updatingCurrentPageWorks() {
        io = new StubIO("a", "b", "testAuthor", "testTitle", "200", "testTag", "u", "0", "90", "q");
        new App(io, dao).run();
        assertEquals("\nEnter the id of the item you want to update the current page for:\n", io.outputs.get(9));
        assertEquals("Last time you were on page 1/200. Leaving the field empty cancels. Enter a new page number: ", io.outputs.get(12));

    }

    @Test
    public void updatingCurrentPageWithInvalidNumberWorks() {
        io = new StubIO("a", "b", "testAuthor", "testTitle", "200", "testTag", "u", "0", "-1", "q");
        new App(io, dao).run();
        assertEquals("Failed updating the current page\n", io.outputs.get(13));
    }

    private boolean containsIgnoreCase(String[] needles, String haystack) {
        for (String needle : needles) {
            if (!containsIgnoreCase(needle, haystack)) {
                return false;
            }
        }
        return true;
    }

    private boolean containsIgnoreCase(String needle, String haystack) {
        return haystack.toLowerCase().contains(needle.toLowerCase());
    }
}
