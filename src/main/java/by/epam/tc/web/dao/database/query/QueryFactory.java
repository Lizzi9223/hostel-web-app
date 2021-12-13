package by.epam.tc.web.dao.database.query;

import by.epam.tc.web.dao.database.query.impl.*;

public final class QueryFactory {
    private final static QueryFactory instance = new QueryFactory();

    private SelectQuery selectQuery = new SelectQueryImpl();
    private InsertQuery insertQuery = new InsertQueryImpl();
    private UpdateQuery updateQuery = new UpdateQueryImpl();
    private DeleteQuery deleteQuery = new DeleteQueryImpl();

    private QueryFactory(){}

    public static QueryFactory getInstance(){
        return instance;
    }

    public SelectQuery getSelectQuery() {
        return selectQuery;
    }

    public InsertQuery getInsertQuery() {
        return insertQuery;
    }

    public UpdateQuery getUpdateQuery() {
        return updateQuery;
    }

    public DeleteQuery getDeleteQuery() {
        return deleteQuery;
    }
}
