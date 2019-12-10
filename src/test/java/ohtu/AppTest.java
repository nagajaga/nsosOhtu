package ohtu;

import ohtu.App;
import ohtu.io.*;
import ohtu.dao.fake.FakeWorkDao;
import static org.junit.Assert.assertEquals;
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
        assertEquals("Add / List / Search / Update current page / Edit / Delete / Quit (A/L/S/U/E/D/Q): ", io.outputs.get(1));
        assertEquals("\nGoodbye!", io.outputs.get(2));
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
        assertEquals("Add / List / Search / Update current page / Edit / Delete / Quit (A/L/S/U/E/D/Q): ", io.outputs.get(1));
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
        assertEquals("Add / List / Search / Update current page / Edit / Delete / Quit (A/L/S/U/E/D/Q): ", io.outputs.get(1));
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
    public void editingBookWorks(){
        io = new StubIO("a", "b", "testAuthor", "testTitle", "200", "testTag", "e","0","b","newAuthor","newTitle","250","newTag","y","l","a","b","q");
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
        io = new StubIO("a", "b", "testAuthor", "testTitle", "200", "testTag", "e","0","b","newAuthor","newTitle","250","newTag","y","l","a","b","l","r","b","q");
        new App(io, dao).run();
        assertEquals("\nRead works:\n", io.outputs.get(34));
        assertEquals("Book\nnewAuthor: newTitle\nPages: 250\nCurrent page: 1\nTags: newTag\n", io.outputs.get(35));
    }
    
    
    @Test
    public void searchingWorks() {
        io = new StubIO("a", "b", "testAuthor", "testTitle", "200", "testTag", "s", "testTag", "q");
        new App(io, dao).run();
        assertEquals("Enter the tag you want to look for (empty string returns):\n", io.outputs.get(9));
    }
    
    
}
