package ohtu;

import static org.junit.Assert.*;
import org.junit.Test;

public class WorkTest {

    @Test
    public void getAuthorWorks(){
        Work testWork = new Work("Joel","Potato");
        assertEquals("Joel",testWork.getAuthor());
    }
    
    @Test
    public void getTitleWorks(){
        Work testWork = new Work("Joel","Potato");
        assertEquals("Potato",testWork.getTitle());
    }
    
    @Test
    public void setAuthorWorks(){
        Work testWork = new Work("Joel","Potato");
        testWork.setAuthor("Joel1");
        assertEquals("Joel1",testWork.getAuthor());
    }
    
    @Test
    public void setTitleWorks(){
        Work testWork = new Work("Joel","Potato");
        testWork.setTitle("Potato1");
        assertEquals("Potato1",testWork.getTitle());
    }
}