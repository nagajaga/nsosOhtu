/*
 * @author londes
 */
package ohtu.dao.fake;

import java.util.ArrayList;
import java.util.List;
import ohtu.Work;
import ohtu.dao.Dao;

/**
 * A non-persistent placeholder for WorkDao Implemented as a singleton
 */
public class FakeWorkDao implements Dao<Work, Integer> {

    private static FakeWorkDao INSTANCE;
    private ArrayList<Work> works;

    private FakeWorkDao() {
        works = new ArrayList<>();
    }

    public static FakeWorkDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeWorkDao();
        }
        return INSTANCE;
    }

    /**
     * creates a copy of the object given to it and stores that into the list,
     * then updates the id on both the copy in use and the stored copy
     *
     * @param work the work to be stored in the database
     * @return
     */
    @Override
    public Work create(Work work) {
        Work newWork = new Work(work.getAuthor(), work.getTitle());
        Integer id = works.size();
        newWork.setId(id);
        work.setId(id);
        works.add(newWork);
        return work;
    }

    @Override
    public Work read(Integer key) {
        if (key < 0 || key > works.size()) {
            return null;
        }
        Work stored = works.get(key);
        Work copy = new Work(stored.getAuthor(), stored.getTitle());
        copy.setId(stored.getId());
        return copy;
    }

    @Override
    public Work update(Work work) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Work> list() {
        List<Work> ret = new ArrayList();
        for (Work stored : works) {
            Work copy = new Work(stored.getAuthor(), stored.getTitle());
            copy.setId(stored.getId());
        }
        return ret;
    }

}
