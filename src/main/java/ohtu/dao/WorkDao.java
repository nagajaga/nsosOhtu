/*
 * @author londes
 */

package ohtu.dao;

import java.util.List;
import ohtu.Work;
import ohtu.dao.Dao;

public interface WorkDao extends Dao<Work, Integer> {

    /**
     * creates a copy of the object given to it and stores that into the list,
     * then updates the id on both the copy in use and the stored copy
     *
     * @param work the work to be stored in the database
     * @return
     */
    Work create(Work work);

    boolean delete(Integer key);

    List<Work> list();

    Work read(Integer key);

    List<Work> searchByTag(String tag);

    List<Work> searchByTag(List<String> tags);

    /**
     * Updates the copy stored by the dao to match the new object
     * @param work the work, identified by the id attribute
     * @return a copy of the work object in the database
     */
    Work update(Work work);

}
