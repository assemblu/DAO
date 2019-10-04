package DAO;

public interface dbmInterface {
    public void runQuery(String query);
    public boolean resultNext();
    public String getResultString(String columnLabel);
}
