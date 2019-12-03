package ohtu;

import ohtu.Domain.Website;
import ohtu.Domain.Work;
import ohtu.Domain.WorkType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WorkTest {

    @Test
    public void getAuthorWorks(){
        Work testWork = new Website("Joel","Potato","-","-", WorkType.WEBSITE);
        assertEquals("Joel",testWork.getAuthor());
    }
    
    @Test
    public void getTitleWorks(){
        Work testWork = new Website("Joel","Potato","-","-",WorkType.WEBSITE);
        assertEquals("Potato",testWork.getTitle());
    }
    
    @Test
    public void setAuthorWorks(){
        Work testWork = new Website("Joel","Potato","-","-",WorkType.WEBSITE);
        testWork.setAuthor("Joel1");
        assertEquals("Joel1",testWork.getAuthor());
    }
    
    @Test
    public void setTitleWorks(){
        Work testWork = new Website("Joel","Potato","-","-",WorkType.WEBSITE);
        testWork.setTitle("Potato1");
        assertEquals("Potato1",testWork.getTitle());
    }
}