package by.epam.tc.web.dao.database.query.impl;

import by.epam.tc.web.dao.database.metadata.Metadata;
import by.epam.tc.web.dao.database.query.UpdateQuery;

public class UpdateQueryImpl implements UpdateQuery {

    @Override
    public String getUpdateQueryWhere(String tableName, String whereClauseColumnName){
        String insertQuery = "update %s set %s where %s = ?;";
        String params = "";
        switch (tableName){
            case Metadata.USERS_TABLE:
                params = "login=?"; break;
            case Metadata.USER_ROLES_TABLE:
                params = "role_name=?"; break;
            case Metadata.ADMINS_TABLE:
                params = "name=?, photo=?"; break;
            case Metadata.ALL_CLIENTS_TABLE:
                params = "last_name=?, first_name=?, passport_id=?, date_of_birth=?, country=?, phone_number=?, email=?"; break;
            case Metadata.BLACK_LIST_TABLE:
                params = "reason=?, since_date=?"; break;
            case Metadata.REGULAR_CUSTOMERS_TABLE:
                params = "since_date=?, discount=?, notes=?"; break;
            case Metadata.ALL_STAYS_TABLE:
                params = "client_id=?, room_number=?, from_date=?, to_date=?, notes=?"; break;
            case Metadata.ROOMS_TABLE:
                params = "room_number=?, cost=?, capacity=?, gender=?, bathroom=?, notes=?"; break;
            case Metadata.IMAGES_TABLE:
                params = "img_path=?, room_number=?"; break;
            case Metadata.BOOKING_TABLE:
                params = "user_id=?, room_number=?, from_date=?, to_date=?, guests_count=?, approved=?, approve_date=?, paid=?"; break;
        }
        return String.format(insertQuery, tableName, params, whereClauseColumnName);
    }
    
    @Override
    public String getUpdatePasswordQueryWhere(String whereClauseColumnName) {
    	String insertQuery = "update %s set %s where %s = ?;";
    	String params = "password=?";
    	return String.format(insertQuery, Metadata.USERS_TABLE, params, whereClauseColumnName);
    }
}
