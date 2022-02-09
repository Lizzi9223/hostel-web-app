package by.epam.tc.web.dao.database.query;

/**
 * Provides with methods to receive select queries for database
 * 
 * @author Lizzi9223
 *
 */
public interface SelectQuery {

	/**
	 * Gets a select query from database for specific table
	 * 
	 * @param tableName name of the table to select records from
	 * @return select query
	 */
	String getSelectQuery(String tableName);

	/**
	 * Gets query to select records from database where records correspond to the specific condition
	 * 
	 * @param tableName name of the table to select records from
	 * @param whereClauseColumn name of the column to set condition for choosing records
	 * @return select query
	 */
	String getSelectQueryWhere(String tableName, String whereClauseColumn);

	/**
	 * Gets query to select records from database where records correspond to both condition
	 * 
	 * @param tableName name of the table to select records from
	 * @param whereClauseColumn1 name of the column to set 1st condition for choosing records
	 * @param whereClauseColumn2 name of the column to set 2nd condition for choosing records
	 * @return select query
	 */
	String getSelectQueryDoubleWhere(String tableName, String whereClauseColumn1, String whereClauseColumn2);

	/**
	 * Gets query to select records from database where records correspond to the specific range
	 * 
	 * @param tableName name of the table to select records from
	 * @param whereClauseColumn name of the column to set range for choosing records
	 * @return select query
	 */
	String getSelectQueryWhereBetween(String tableName, String whereClauseColumn);

	/**
	 * Gets a select query from database to receive records joined from two tables
	 * 
	 * @param mainTableName main table to get data from
	 * @param joinTableName name of the table to join
	 * @param joinFieldName name of the column to join tables basing on
	 * @return select inner join query
	 */
	String getInnerJoinSelectQuery(String mainTableName, String joinTableName, String joinFieldName);

	/**
	 * Gets a select query from database to receive records joined from two that correspond to the specific condition
	 * (joinFieldName and whereClauseColumn belong to the same table)
	 * 
	 * @param mainTableName main table to get data from
	 * @param joinTableName name of the table to join
	 * @param joinFieldName name of the column to join tables basing on
	 * @param whereClauseColumn name of the column to set condition for choosing records
	 * @return select inner join query
	 */
	String getInnerJoinSelectQueryWhere(String mainTableName, String joinTableName, String joinFieldName,
			String whereClauseColumn);

	/**
	 * (joinFieldName and whereClauseColumn belong to different tables)
	 * 
	 * @param mainTableName main table to get data from
	 * @param joinTableName name of the table to join
	 * @param joinFieldName name of the column to join tables basing on
	 * @param whereClauseTable name of the table to set condition for choosing records
	 * @param whereClauseColumn name of the column to set condition for choosing records
	 * @return select inner join query
	 */
	String getInnerJoinSelectQueryWhere(String mainTableName, String joinTableName, String joinFieldName,
			String whereClauseTable, String whereClauseColumn);

	/**
	 * Gets a select query from database to receive records joined from two that correspond to both condition
	 * 
	 * @param mainTableName main table to get data from
	 * @param joinTableName name of the table to join
	 * @param joinFieldName name of the column to join tables basing on
	 * @param whereClauseColumn1 name of the column to set 1st condition for choosing records
	 * @param whereClauseColumn2 name of the column to set 2nd condition for choosing records
	 * @return select inner join query
	 */
	String getInnerJoinSelectQueryDoubleWhere(String mainTableName, String joinTableName, String joinFieldName,
			String whereClauseColumn1, String whereClauseColumn2);
}
