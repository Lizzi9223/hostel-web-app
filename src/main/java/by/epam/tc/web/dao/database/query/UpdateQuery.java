package by.epam.tc.web.dao.database.query;

/**
 * Provides with methods to receive update queries for database
 * 
 * @author Lizzi9223
 *
 */
public interface UpdateQuery {

	/**
	 * Gets query to update table record
	 * 
	 * @param tableName name of the table to update
	 * @param whereClauseColumnName name of the column to get certain records in database for update
	 * @return update query
	 */
	String getUpdateQueryWhere(String tableName, String whereClauseColumnName);

	/**
	 * Gets query to update user's password
	 * 
	 * @param whereClauseColumnName name of the column to get certain records in database for update 
	 * @return update query
	 */
	String getUpdatePasswordQueryWhere(String whereClauseColumnName);
	
	/**
	 * Gets query to set certain user to certain client
	 * 
	 * @param whereClauseColumnName name of the column to get certain records in database for update
	 * @return update query
	 */
	String getUpdateClientsUserIdQueryWhere(String whereClauseColumnName);
}
