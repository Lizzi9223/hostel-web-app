package by.epam.tc.web.dao.database.query;

/**
 * Provides with methods to receive insert queries for database
 * 
 * @author Lizzi9223
 *
 */
public interface InsertQuery {

	/**
	 * Gets query to insert new record in table
	 * 
	 * @param tableName name of the table to insert a record to
	 * @return insert query
	 */
	String getInsertQuery(String tableName);
}
