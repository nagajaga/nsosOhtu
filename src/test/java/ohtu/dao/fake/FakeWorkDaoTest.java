package ohtu.dao.fake;

import ohtu.Work;
import ohtu.dao.Dao;

import static org.junit.Assert.*;
import java.util.List;
import ohtu.WorkType;
import org.junit.Before;
import org.junit.Test;

public class FakeWorkDaoTest {

    private Dao<Work, Integer> dao;

    @Before
    public void before() {
        dao = new FakeWorkDao();
    }

    @Test
    public void createAndRead() {
        create();
        assertTrue(dao.list().size() == 3);
        Work work1 = dao.read(0);
        Work work2 = dao.read(1);
        Work work3 = dao.read(2);
        assertEquals(work1.getAuthor(), "a1");
        assertEquals(work1.getTitle(), "b1");
        assertEquals(work2.getAuthor(), "a2");
        assertEquals(work2.getTitle(), "b2");
        assertEquals(work3.getTitle(), "b3");
    }

    @Test
    public void readReturnsNullWithIncorrectInput() {
        assertTrue(dao.list().isEmpty());
        assertEquals(dao.read(-1), null);
        assertEquals(dao.read(0), null);
        assertEquals(dao.read(1), null);
    }

    @Test
    public void update() {
        create();
        Work testWork = new Work("aa", "bb", "cc", "dd",WorkType.WEBSITE);
        dao.update(testWork, 1);
        Work work = dao.read(1);
        System.out.println(work.getAuthor());
        assertTrue(work.getAuthor().equals("aa"));
    }

    @Test
    public void delete() {
        create();
        assertTrue(dao.list().size() == 3);
        assertTrue(dao.read(1) != null);
        assertTrue(dao.delete(1));
        assertTrue(dao.list().size() == 2);
        assertTrue(dao.read(1) == null);
    }

    @Test
    public void deleteReturnsFalseWithIncorrectInput() {
        assertFalse(dao.delete(0));
        create();
        assertTrue(dao.delete(0));
        assertFalse(dao.delete(-1));
        assertFalse(dao.delete(3));
    }

    @Test
    public void searchBySingleTagReturnsEmptyWhenNoResults() {
        create();
        FakeWorkDao testDao = (FakeWorkDao) dao;
        List<Work> results = testDao.searchByTag("fnord");
        assertTrue(results.isEmpty());
    }

    @Test
    public void searchBySingleTagReturnsCorrectResults() {
        create();
        FakeWorkDao testDao = (FakeWorkDao) dao;
        for (String tag : new String[]{"d1", "d2", "d3"}) {
            List<Work> results = testDao.searchByTag(tag);
            if (results.isEmpty()) {
                fail("search \"" + tag + " returned an empty list");
            } else {
                for (Work work : results) {
                    if (!work.getTags().contains(tag)) {
                        fail("search results contained a false positive");
                    }
                }
            }
        }
    }

    private void create() {
        dao.create(new Work("a1", "b1", "c1", "d1",WorkType.WEBSITE));
        dao.create(new Work("a2", "b2", "c2", "d2",WorkType.WEBSITE));
        dao.create(new Work("a3", "b3", "c3", "d3",WorkType.WEBSITE));
    }
}
