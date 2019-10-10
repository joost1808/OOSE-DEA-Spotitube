package nl.oose.dea.data;

import javax.ejb.ApplicationException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMapper {
//    protected Map loadedMap = new HashMap();
//    abstract protected String findStatement();
//    protected DomainObject abstractFind(Long id);
//    DomainObject result = (DomainObject) loadedMap.get(id);
//    if (result != null) return result;
//    PreparedStatement findStatement = null;
//    try {
//        findStatement = connection.prepare(findStatement());
//        findStatement.setLong(1, id.longValue());
//        ResultSet rs = findStatement.executeQuery();
//        rs.next();
//        result = load(rs);
//        return result;
//    } catch (SQLException e) {
//        throw new ApplicationException(e);
//    } finally {
//        connection.cleanUp(findStatement);
//    }
}
