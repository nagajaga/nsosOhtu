package ohtu;

import ohtu.domain.Work;
import ohtu.domain.WorkType;
import static org.junit.Assert.*;
import org.junit.Test;

public class WorkTest {

    @Test
    public void getAuthorWorks(){
        Work testWork = new Work("Joel","Potato","-",1,"-",WorkType.WEBSITE);
        assertEquals("Joel",testWork.getAuthor());
    }
    
    @Test
    public void getTitleWorks(){
        Work testWork = new Work("Joel","Potato","-",1,"-",WorkType.WEBSITE);
        assertEquals("Potato",testWork.getTitle());
    }
    
    @Test
    public void setAuthorWorks(){
        Work testWork = new Work("Joel","Potato","-",1,"-",WorkType.WEBSITE);
        testWork.setAuthor("Joel1");
        assertEquals("Joel1",testWork.getAuthor());
    }
    
    @Test
    public void setTitleWorks(){
        Work testWork = new Work("Joel","Potato","-",1,"-",WorkType.WEBSITE);
        testWork.setTitle("Potato1");
        assertEquals("Potato1",testWork.getTitle());
    }
}