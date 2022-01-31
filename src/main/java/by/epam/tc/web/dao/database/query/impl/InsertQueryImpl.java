package by.epam.tc.web.dao.database.query.impl;

import by.epam.tc.web.dao.database.metadata.Metadata;
import by.epam.tc.web.dao.database.query.InsertQuery;

public class InsertQueryImpl implements InsertQuery {

	@Override
	public String getInsertQuery(String tableName) {
		String insertQuery = "";
		switch (tableName) {
		case Metadata.USERS_TABLE:
			insertQuery = "insert into users(login,password,role_id) values(?,?,?);";
			break;
		case Metadata.USER_ROLES_TABLE:
			insertQuery = "insert into user_roles(role_name) values(?);";
			break;
		case Metadata.ADMINS_TABLE:
			insertQuery = "insert into admins(user_id,name,photo) values(?,?,?);";
			break;
		case Metadata.ALL_CLIENTS_TABLE:
			insertQuery = "insert into all_clients(user_id, last_name, first_name, passport_id, date_of_birth, "
					+ "country, phone_number, email) values(?,?,?,?,?,?,?,?);";
			break;
		case Metadata.BLACK_LIST_TABLE:
			insertQuery = "insert into blacklist(client_id, reason, since_date) values(?,?,?);";
			break;
		case Metadata.REGULAR_CUSTOMERS_TABLE:
			insertQuery = "insert into regular_customers(client_id, since_date, discount, notes) values(?,?,?,?);";
			break;
		case Metadata.ALL_STAYS_TABLE:
			insertQuery = "insert into all_stays(client_id, room_number, from_date, to_date, notes) "
					+ "values(?,?,?,?,?);";
			break;
		case Metadata.ROOMS_TABLE:
			insertQuery = "insert into rooms(room_number, cost, capacity, gender, bathroom, notes) "
					+ "values(?,?,?,?,?,?);";
			break;
		case Metadata.IMAGES_TABLE:
			insertQuery = "insert into images(img_path, room_number) values(?,?);";
			break;
		case Metadata.BOOKING_TABLE:
			insertQuery = "insert into booking(user_id, room_number, from_date, to_date, guests_count, approved, "
					+ "approve_date, paid) values(?,?,?,?,?,?,?,?);";
			break;
		}
		return insertQuery;
	}
}
