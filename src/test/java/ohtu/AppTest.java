
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
        assertEquals("Add/List/Search/Edit/Delete/Quit (A/L/S/E/D/Q): ", io.outputs.get(1));
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
}
