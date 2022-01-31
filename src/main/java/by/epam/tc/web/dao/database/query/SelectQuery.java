package by.epam.tc.web.dao.database.query;

public interface SelectQuery {

	String getSelectQuery(String tableName);

	String getSelectQueryWhere(String tableName, String whereClauseColumn);

	String getSelectQueryDoubleWhere(String tableName, String whereClauseColumn1, String whereClauseColumn2);

	String getSelectQueryWhereBetween(String tableName, String whereClauseColumn);

	String getInnerJoinSelectQuery(String mainTableName, String joinTableName, String joinFieldName);

	String getInnerJoinSelectQueryWhere(String mainTableName, String joinTableName, String joinFieldName,
			String whereClauseColumn);

	String getInnerJoinSelectQueryWhere(String mainTableName, String joinTableName, String joinFieldName,
			String whereClauseTable, String whereClauseColumn);

	String getInnerJoinSelectQueryDoubleWhere(String mainTableName, String joinTableName, String joinFieldName,
			String whereClauseColumn1, String whereClauseColumn2);
}
