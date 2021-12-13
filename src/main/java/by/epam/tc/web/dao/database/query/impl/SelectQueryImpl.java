package by.epam.tc.web.dao.database.query.impl;

import by.epam.tc.web.dao.database.query.SelectQuery;

public class SelectQueryImpl implements SelectQuery {

	@Override
    public String getSelectQuery(String tableName){
        return String.format("select * from %s;", tableName);
    }

    @Override
    public String getSelectQueryWhere(String tableName, String whereClauseColumn){
        return String.format("select * from %s where %s = ?;", tableName, whereClauseColumn);
    }
    
    @Override
    public String getSelectQueryDoubleWhere(String tableName, 
    		String whereClauseColumn1,String whereClauseColumn2){
        return String.format("select * from %s where %s = ? and %s = ?;", 
        		tableName, whereClauseColumn1, whereClauseColumn2);
    }

	@Override
    public String getSelectQueryWhereBetween(String tableName, String whereClauseColumn){
        return String.format("select * from %s where %s between ? and ?;", tableName, whereClauseColumn);
    }

    @Override
    public String getInnerJoinSelectQuery(String mainTableName, String joinTableName, String joinFieldName){
        return String.format("select * from %1$s Inner Join %2$s On %1$s.%3$s=%2$s.%3$s;",
                mainTableName, joinTableName, joinFieldName);
    }

    @Override
    public String getInnerJoinSelectQueryWhere(String mainTableName, String joinTableName, String joinFieldName,
                                               String whereClauseColumn){
        return String.format("select * from %1$s Inner Join %2$s On %1$s.%3$s=%2$s.%3$s where %1$s.%4$s = ?;",
                mainTableName, joinTableName, joinFieldName, whereClauseColumn);
    }

    @Override
    public String getInnerJoinSelectQueryWhere(String mainTableName, String joinTableName, String joinFieldName,
                                                String whereClauseTable, String whereClauseColumn){
        return String.format("select * from %1$s Inner Join %2$s On %1$s.%3$s=%2$s.%3$s where %4$s.%5$s = ?;",
                mainTableName, joinTableName, joinFieldName, whereClauseTable, whereClauseColumn);
    }

	@Override
	public String getInnerJoinSelectQueryDoubleWhere(String mainTableName, String joinTableName, String joinFieldName,
			String whereClauseColumn1, String whereClauseColumn2) {
        return String.format("select * from %1$s Inner Join %2$s On %1$s.%3$s=%2$s.%3$s "
        		+ "where %1$s.%4$s = ? and %1$s.%5$s = ?;",
                mainTableName, joinTableName, joinFieldName, whereClauseColumn1, whereClauseColumn2);
  	}    
    
}
