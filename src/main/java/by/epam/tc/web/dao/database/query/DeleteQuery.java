package by.epam.tc.web.dao.database.query;

/**
 * Provides with methods to receive delete queries for database
 * 
 * @author Lizzi9223
 *
 */
public interface DeleteQuery {

	/**
	 * Gets query do delete records from database where records correspond to the specific condition
	 * 
	 * @param tableName name of the table to delete a record from
	 * @param whereClauseColumnName name of the column to set condition for choosing records
	 * @return delete query
	 */
	String getDeleteQueryWhere(String tableName, String whereClauseColumnName);

	/**
	 * Gets query to delete records from database where records correspond to the specific range
	 * 
	 * @param tableName name of the table to delete a record from
	 * @param whereClauseColumnName name of the column to set range for choosing records
	 * @return delete query
	 */
	String getDeleteQueryWhereBetween(String tableName, String whereClauseColumnName);
}
