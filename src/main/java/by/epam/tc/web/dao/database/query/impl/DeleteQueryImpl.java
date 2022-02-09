package by.epam.tc.web.dao.database.query.impl;

import by.epam.tc.web.dao.database.query.DeleteQuery;

/** 
 * The class {@code DeleteQueryImpl} implements {@code DeleteQuery}
 * 
 * @author Lizzi9223
 *
 */
public class DeleteQueryImpl implements DeleteQuery {

	@Override
	public String getDeleteQueryWhere(String tableName, String whereClauseColumnName) {
		return String.format("delete from %s where %s = ?", tableName, whereClauseColumnName);
	}

	@Override
	public String getDeleteQueryWhereBetween(String tableName, String whereClauseColumnName) {
		return String.format("delete from %s where %s between ? and ?", tableName, whereClauseColumnName);
	}
}
