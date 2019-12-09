package ohtu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.Work;
import ohtu.dao.WorkDao;
import ohtu.db.DatabaseManager;

public class WorkDaoImpl implements WorkDao {

    private DatabaseManager db;

    public WorkDaoImpl(DatabaseManager db) {
        this.db = db;
    }

    @Override
    public Work create(Work work) {
        try {
            Connection conn = db.openConnection();
            PreparedStatement s = prepareStatement(conn, work);
            s.executeUpdate();
            ResultSet keys = s.getGeneratedKeys();
            setWorkId(keys, work);
            return work;
        } catch (SQLException ex) {
            Logger.getLogger(WorkDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return work;
    }

    private PreparedStatement prepareStatement(Connection conn, Work work) throws SQLException {
        PreparedStatement s = conn.prepareStatement("insert into Work ("
                + " author,"
                + " title,"
                + " code,"
                + " tags,"
                + " type,"
                + " read,"
                + " pages,"
                + " current_page"
                + ") values (?, ?, ?, ?, ?, ?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS);
        s.setString(1, work.getAuthor());
        s.setString(2, work.getTitle());
        s.setString(3, work.getCode());
        s.setString(4, work.getTags());
        s.setString(5, work.getType().toString());
        s.setBoolean(6, work.getRead());
        s.setInt(7, work.getPages());
        s.setInt(8, work.getCurrentPage());
        return s;
    }

    @Override
    public boolean delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Work> list() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Work read(Integer key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Work> searchByTag(String tag) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Work> searchByTag(List<String> tags) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Work update(Work work) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void setWorkId(ResultSet keys, Work work) throws SQLException {
        int id = -1;
        if (keys.next()) {
            id = keys.getInt(1);
        }
        work.setId(id);
    }
}
