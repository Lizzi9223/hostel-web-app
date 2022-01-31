package by.epam.tc.web.dao.database.query;

public interface UpdateQuery {

	String getUpdateQueryWhere(String tableName, String whereClauseColumnName);

	String getUpdatePasswordQueryWhere(String whereClauseColumnName);
}
