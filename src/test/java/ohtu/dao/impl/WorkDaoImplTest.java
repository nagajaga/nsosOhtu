package ohtu.dao.impl;

import java.sql.SQLException;
import ohtu.domain.Work;
import ohtu.dao.Dao;

import static org.junit.Assert.*;
import java.util.List;
import ohtu.db.DatabaseManager;
import ohtu.domain.WorkType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WorkDaoImplTest {

    private Dao<Work, Integer> dao;
    private DatabaseManager dbm;

    @Before
    public void before() throws SQLException {
        dbm = new DatabaseManager();
        dbm.setup("jdbc:sqlite:testapp.db", "nsos", "snek");
        dbm.createTablesIfAbsent();
        dbm.openConnection().prepareStatement("DELETE FROM Work;").executeUpdate();
        dao = new WorkDaoImpl(dbm);
    }

    @After
    public void after() throws SQLException {
        dbm.openConnection().prepareStatement("DELETE FROM Work;").executeUpdate();
    }

    @Test
    public void createAndRead() {
        create();
        assertTrue(dao.list().size() == 3);
        Work work1 = dao.read(1);
        Work work2 = dao.read(2);
        Work work3 = dao.read(3);
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
        Work testWork = new Work("aa", "bb", "cc", 1, "dd", WorkType.WEBSITE);
        testWork.setId(1);
        dao.update(testWork);
        Work work = dao.read(1);
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
        assertTrue(dao.delete(1));
        assertFalse(dao.delete(-1));
        assertFalse(dao.delete(4));
    }

    @Test
    public void searchBySingleTagReturnsEmptyWhenNoResults() {
        create();
        WorkDaoImpl testDao = (WorkDaoImpl) dao;
        List<Work> results = testDao.searchByTag("fnord");
        assertTrue(results.isEmpty());
    }

    @Test
    public void searchBySingleTagReturnsCorrectResults() {
        create();
        WorkDaoImpl testDao = (WorkDaoImpl) dao;
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
        dao.create(new Work("a1", "b1", "c1", 1, "d1", WorkType.WEBSITE));
        dao.create(new Work("a2", "b2", "c2", 1, "d2", WorkType.WEBSITE));
        dao.create(new Work("a3", "b3", "c3", 1, "d3", WorkType.WEBSITE));
    }
}
