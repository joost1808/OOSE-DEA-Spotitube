package nl.oose.dea.data;

public interface StatementSource {
    String sql();
    Object[] parameters();
}
