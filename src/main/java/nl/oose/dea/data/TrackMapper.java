//package nl.oose.dea.data;
//
//import nl.oose.dea.domain.DomainObject;
//import nl.oose.dea.domain.Track;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Date;
//import java.util.List;
//
//public class TrackMapper extends AbstractMapper {
//private static final String COLUMNS = " id, title, performer, duration, album, playcount, publicationDate, description";
//
//    @Override
//    protected String findStatement() {
//        return "SELECT " + COLUMNS + " FROM tracks" + " WHERE id IN" + " (SELECT trackid FROM playlisttracks WHERE playlistid != ?";
//    }
//
//
//    public List<Track> findAllTracksFromPlaylist(int id) {
//        return findMany(new AllTracksFromPlaylist(id));
//    }
//
//    static class AllTracksFromPlaylist implements StatementSource {
//        private int playlistid;
//        public AllTracksFromPlaylist(int playlistid) {
//            this.playlistid = playlistid;
//        }
//
//        @Override
//        public String sql() {
//            return "SELECT * FROM tracks WHERE id IN (SELECT trackid FROM playlisttracks WHERE playlistid = ?)";
//        }
//
//        @Override
//        public Object[] parameters() {
//            Object[] result = {playlistid};
//            return result;
//        }
//    }
//
//    @Override
//    protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
//        String titleArg = rs.getString(2);
//        String performerArg = rs.getString(3);
//        int durationArg = rs.getInt(4);
//        String albumArg = rs.getString(5);
//        int playcountArg = rs.getInt(6);
//        String publicationDateArg = rs.getString(7);
//        String descriptionArg = rs.getString(8);
//        boolean offlineAvailableArg = rs.getBoolean(9);
//        return new Track(id, titleArg, performerArg, durationArg, albumArg, playcountArg, publicationDateArg, descriptionArg, offlineAvailableArg);
//    }
//
//    @Override
//    protected String insertStatement() {
//        return null;
//    }
//
//    @Override
//    protected void doInsert(DomainObject subject, PreparedStatement insertStatement) throws SQLException {
//
//    }
//}
