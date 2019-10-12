//package nl.oose.dea.data;
//
//import nl.oose.dea.domain.DomainObject;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public abstract class AbstractMapper {
//
//    protected Map<Long, DomainObject> loadedMap = new HashMap<>();
//
//    abstract protected String findStatement();
//
//    protected DomainObject abstractFind(Long id) {
//        DomainObject result = loadedMap.get(id);
//        if (result != null) return result;
//        PreparedStatement findStatement = null;
//        try {
//            Class.forName(DatabaseProperties.getDatabaseProperty("driver"));
//            Connection connection = DriverManager.getConnection(DatabaseProperties.getDatabaseProperty("connectionString"));
//            findStatement = connection.prepareStatement(findStatement());
//            findStatement.setLong(1, id);
//            ResultSet rs = findStatement.executeQuery();
//            rs.next();
//            result = load(rs);
//            return result;
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new IllegalStateException(e);
//        }
//    }
//
//    protected DomainObject load(ResultSet rs) throws SQLException {
//        Long id = rs.getLong(1);
//        if (loadedMap.containsKey(id)) return loadedMap.get(id);
//        DomainObject result = doLoad(id, rs);
//        loadedMap.put(id, result);
//        return result;
//    }
//
//    abstract protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException;
//
//    protected List<DomainObject> loadAll(ResultSet rs) throws SQLException {
//        List<DomainObject> result = new ArrayList<>();
//        while (rs.next()) {
//            result.add(load(rs));
//        }
//        return result;
//    }
//
//    public List<DomainObject> findMany(StatementSource source) {
//        PreparedStatement statement = null;
//        ResultSet rs = null;
//        try {
//            Class.forName(DatabaseProperties.getDatabaseProperty("driver"));
//            Connection connection = DriverManager.getConnection(DatabaseProperties.getDatabaseProperty("connectionString"));
//            statement = connection.prepareStatement(source.sql());
//            for (int i = 0; i < source.parameters().length; i++) {
//                statement.setObject(i + 1, source.parameters()[i]);
//            }
//            rs = statement.executeQuery();
//            return loadAll(rs);
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new IllegalStateException(e);
//        }
//    }
//
//    public Long insert(DomainObject subject) {
//        PreparedStatement insertStatement = null;
//        try {
//            Class.forName(DatabaseProperties.getDatabaseProperty("driver"));
//            Connection connection = DriverManager.getConnection(DatabaseProperties.getDatabaseProperty("connectionString"));
//            insertStatement = connection.prepareStatement(insertStatement());
//            subject.setId(findNextDatabaseId());
//            insertStatement.setInt(1, subject.getId().intValue());
//            doInsert(subject, insertStatement);
//            insertStatement.execute();
//            loadedMap.put(subject.getId(), subject);
//            return subject.getId();
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new IllegalStateException(e);
//        }
//    }
//
//    abstract protected String insertStatement();
//    abstract protected void doInsert (DomainObject subject, PreparedStatement insertStatement) throws SQLException;
//}
