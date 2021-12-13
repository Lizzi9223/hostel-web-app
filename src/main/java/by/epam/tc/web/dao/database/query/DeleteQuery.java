package by.epam.tc.web.dao.database.query;

public interface DeleteQuery {

    String getDeleteQueryWhere(String tableName, String whereClauseColumnName);
    String getDeleteQueryWhereBetween(String tableName, String whereClauseColumnName);
}
