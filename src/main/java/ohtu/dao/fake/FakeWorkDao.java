package ohtu.dao.fake;

import ohtu.dao.WorkDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ohtu.domain.Work;
import ohtu.dao.Dao;

/**
 * A non-persistent placeholder for WorkDao Implemented as a singleton
 */
public class FakeWorkDao implements WorkDao {

    private static FakeWorkDao INSTANCE;
    private ArrayList<Work> works;

    public FakeWorkDao() {
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
        Work stored = new Work(work.getAuthor(), work.getTitle(), work.getCode(), work.getPages(), work.getTags(), work.getType());
        Integer id = 0;
        if (!works.isEmpty()) {
            id = works.get(works.size() - 1).getId() + 1;
        }
        stored.setId(id);
        work.setId(id);
        works.add(stored);
        return work;
    }

    @Override
    public Work read(Integer key) {
        Work stored = get(key);
        if (stored != null) {
            Work copy = new Work(stored.getAuthor(), stored.getTitle(), stored.getCode(), stored.getPages(), stored.getTags(), stored.getType());
            copy.setId(stored.getId());
            copy.setRead(stored.getRead());
            return copy;
        }
        return null;
    }

    /**
     * Updates the copy stored by the dao to match the new object
     * @param work the work, identified by the id attribute
     * @return a copy of the work object in the database
     */
    @Override
    public Work update(Work work) {
        Work toUpdate = get(work.getId());
        if (toUpdate != null) {
            toUpdate.setAuthor(work.getAuthor());
            toUpdate.setTitle(work.getTitle());
            toUpdate.setTags(work.getTags());
            toUpdate.setUrl(work.getCode());
            toUpdate.setRead(work.getRead());
            toUpdate.setType(work.getType());
            toUpdate.setPages(work.getPages());
            Work copy = new Work(toUpdate.getAuthor(), toUpdate.getTitle(), toUpdate.getCode(), toUpdate.getPages(), toUpdate.getTags(), toUpdate.getType());
            return copy;
        }
        return null;
    }

    @Override
    public boolean delete(Integer key) {
        Work toRemove = get(key);
        if (toRemove != null) {
            works.remove(toRemove);
            return true;
        }
        return false;
    }

    @Override
    public List<Work> list() {
        List<Work> ret = new ArrayList<>();
        for (Work stored : works) {
            Work copy = new Work(stored.getAuthor(), stored.getTitle(), stored.getCode(), stored.getPages(), stored.getTags(), stored.getType());
            copy.setId(stored.getId());
            copy.setRead(stored.getRead());
            copy.setType(stored.getType());
            ret.add(copy);
        }
        return ret;
    }

    @Override
    public List<Work> searchByTag(String tag) {
        List<String> list = new ArrayList<String>();
        list.add(tag);
        List<Work> results = searchByTag(list);
        return results;
    }

    @Override
    public List<Work> searchByTag(List<String> tags) {
        List<Work> results = new ArrayList<>();
        for (Work stored : works) {
            if (containsSubstrings(tags, stored.getTags())) {
                Work copy = new Work(stored.getAuthor(), stored.getTitle(), stored.getCode(), stored.getPages(), stored.getTags(), stored.getType());
                copy.setId(stored.getId());
                results.add(copy);
            }
        }
        return results;
    }

    /*
     * helper methods
     */

    /**
     * A logical AND substring search
     * @param needles the list of substrings to look for
     * @param haystack the string to search
     */
    private boolean containsSubstrings(List<String> needles, String haystack) {
        for (String needle : needles) {
            if (!haystack.contains(needle)) {
                return false;
            }
        }
        return true;
    }

    /**
     * for internal use; gets the work in the database
     * @param id the id of the work to look for
     * @return the internal representation of the work
     */
    private Work get(Integer key) {
        if (key < 0 || works.isEmpty()) {
            return null;
        }
        for (Work stored : works) {
            if (Objects.equals(stored.getId(), key)) {
                return stored;
            }
        }
        return null;
    }
}
