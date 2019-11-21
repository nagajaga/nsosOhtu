package ohtu;

import static org.junit.Assert.*;
import org.junit.Test;

public class WorkTest {

    @Test
    public void getAuthorWorks(){
        Work testWork = new Work("Joel","Potato");
        assertEquals("Joel",testWork.getAuthor());
    }
}