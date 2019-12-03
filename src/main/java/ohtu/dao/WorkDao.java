/*
 * @author londes
 */

package ohtu.dao;

import java.util.List;
import ohtu.domain.Work;
import ohtu.dao.Dao;

public interface WorkDao extends Dao<Work, Integer> {

    /**
     * creates the data in the the object work into the database,
     * then updates its id to match that returned by the database
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
