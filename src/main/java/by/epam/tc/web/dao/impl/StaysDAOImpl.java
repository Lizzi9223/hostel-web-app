package by.epam.tc.web.dao.impl;

import by.epam.tc.web.dao.DAOException;
import by.epam.tc.web.dao.StaysDAO;
import by.epam.tc.web.dao.database.connection_pool.ConnectionPool;
import by.epam.tc.web.dao.database.connection_pool.ConnectionPoolException;
import by.epam.tc.web.dao.database.metadata.Metadata;
import by.epam.tc.web.dao.database.query.*;
import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.entity.stay.Stay;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaysDAOImpl implements StaysDAO {

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();
	private final SelectQuery selectQueryProvider = QueryFactory.getInstance().getSelectQuery();
	private final InsertQuery insertQueryProvider = QueryFactory.getInstance().getInsertQuery();
	private final DeleteQuery deleteQueryProvider = QueryFactory.getInstance().getDeleteQuery();
	private final UpdateQuery updateQueryProvider = QueryFactory.getInstance().getUpdateQuery();

	public StaysDAOImpl() {
	}

	@Override
	public List<Booking> getAllBookings() throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<Booking> bookings = new ArrayList<>();
		try {
			con = connectionPool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(selectQueryProvider.getSelectQuery(Metadata.BOOKING_TABLE));

			while (rs.next()) {
				int id = rs.getInt(Metadata.BookingTableColumn.BOOKING_ID);
				int userId = rs.getInt(Metadata.BookingTableColumn.USER_ID);
				int roomNumber = rs.getInt(Metadata.BookingTableColumn.ROOM_NUMBER);
				LocalDate fromDate = rs.getDate(Metadata.BookingTableColumn.FROM_DATE).toLocalDate();
				LocalDate toDate = rs.getDate(Metadata.BookingTableColumn.TO_DATE).toLocalDate();
				int guestsCount = rs.getInt(Metadata.BookingTableColumn.GUESTS_COUNT);
				Object isApprovedObj = rs.getObject(Metadata.BookingTableColumn.APPROVED);
				Boolean isApproved = null;
				if (isApprovedObj != null) {
					isApproved = rs.getBoolean(Metadata.BookingTableColumn.APPROVED);
				}
				java.sql.Date date = rs.getDate(Metadata.BookingTableColumn.APPROVE_DATE);
				LocalDate approveDate = null;
				if (date != null) {
					approveDate = date.toLocalDate();
				}
				boolean isPaid = rs.getBoolean(Metadata.BookingTableColumn.PAID);
				bookings.add(new Booking(id, userId, roomNumber, fromDate, toDate, guestsCount, isApproved, approveDate,
						isPaid));
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connectionPool.closeConnection(con, st, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return bookings;
	}

	@Override
	public List<Booking> getAllUserBookings(int userId) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Booking> bookings = new ArrayList<>();
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(selectQueryProvider.getSelectQueryWhere(Metadata.BOOKING_TABLE,
					Metadata.BookingTableColumn.USER_ID));
			st.setInt(1, userId);
			rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(Metadata.BookingTableColumn.BOOKING_ID);
				int roomNumber = rs.getInt(Metadata.BookingTableColumn.ROOM_NUMBER);
				LocalDate fromDate = rs.getDate(Metadata.BookingTableColumn.FROM_DATE).toLocalDate();
				LocalDate toDate = rs.getDate(Metadata.BookingTableColumn.TO_DATE).toLocalDate();
				int guestsCount = rs.getInt(Metadata.BookingTableColumn.GUESTS_COUNT);
				Object isApprovedObj = rs.getObject(Metadata.BookingTableColumn.APPROVED);
				Boolean isApproved = null;
				if (isApprovedObj != null) {
					isApproved = rs.getBoolean(Metadata.BookingTableColumn.APPROVED);
				}
				java.sql.Date date = rs.getDate(Metadata.BookingTableColumn.APPROVE_DATE);
				LocalDate approveDate = null;
				if (date != null) {
					approveDate = date.toLocalDate();
				}
				boolean isPaid = rs.getBoolean(Metadata.BookingTableColumn.PAID);
				bookings.add(new Booking(id, userId, roomNumber, fromDate, toDate, guestsCount, isApproved, approveDate,
						isPaid));
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connectionPool.closeConnection(con, st, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return bookings;
	}

	@Override
	public List<Stay> getAllStays() throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<Stay> stays = new ArrayList<>();
		try {
			con = connectionPool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(selectQueryProvider.getSelectQuery(Metadata.ALL_STAYS_TABLE));

			while (rs.next()) {
				int id = rs.getInt(Metadata.AllStaysTableColumn.STAY_ID);
				int clientId = rs.getInt(Metadata.AllStaysTableColumn.CLIENT_ID);
				int roomNumber = rs.getInt(Metadata.AllStaysTableColumn.ROOM_NUMBER);
				LocalDate fromDate = rs.getDate(Metadata.AllStaysTableColumn.FROM_DATE).toLocalDate();
				LocalDate toDate = rs.getDate(Metadata.AllStaysTableColumn.TO_DATE).toLocalDate();
				String notes = rs.getString(Metadata.AllStaysTableColumn.NOTES);
				stays.add(new Stay(id, clientId, roomNumber, fromDate, toDate, notes));
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connectionPool.closeConnection(con, st, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return stays;
	}

	@Override
	public List<Stay> getAllClientStays(int clientId) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Stay> stays = new ArrayList<>();
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(selectQueryProvider.getSelectQueryWhere(Metadata.ALL_STAYS_TABLE,
					Metadata.AllStaysTableColumn.CLIENT_ID));
			st.setInt(1, clientId);
			rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(Metadata.AllStaysTableColumn.STAY_ID);
				int roomNumber = rs.getInt(Metadata.AllStaysTableColumn.ROOM_NUMBER);
				LocalDate fromDate = rs.getDate(Metadata.AllStaysTableColumn.FROM_DATE).toLocalDate();
				LocalDate toDate = rs.getDate(Metadata.AllStaysTableColumn.TO_DATE).toLocalDate();
				String notes = rs.getString(Metadata.AllStaysTableColumn.NOTES);
				stays.add(new Stay(id, clientId, roomNumber, fromDate, toDate, notes));
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connectionPool.closeConnection(con, st, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return stays;
	}

	@Override
	public Booking findBookingById(int id) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Booking booking = null;
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(selectQueryProvider.getSelectQueryWhere(Metadata.BOOKING_TABLE,
					Metadata.BookingTableColumn.BOOKING_ID));
			st.setInt(1, id);
			rs = st.executeQuery();

			while (rs.next()) {
				int userId = rs.getInt(Metadata.BookingTableColumn.USER_ID);
				int roomNumber = rs.getInt(Metadata.BookingTableColumn.ROOM_NUMBER);
				LocalDate fromDate = rs.getDate(Metadata.BookingTableColumn.FROM_DATE).toLocalDate();
				LocalDate toDate = rs.getDate(Metadata.BookingTableColumn.TO_DATE).toLocalDate();
				int guestsCount = rs.getInt(Metadata.BookingTableColumn.GUESTS_COUNT);
				Object isApprovedObj = rs.getObject(Metadata.BookingTableColumn.APPROVED);
				Boolean isApproved = null;
				if (isApprovedObj != null) {
					isApproved = rs.getBoolean(Metadata.BookingTableColumn.APPROVED);
				}
				java.sql.Date date = rs.getDate(Metadata.BookingTableColumn.APPROVE_DATE);
				LocalDate approveDate = null;
				if (date != null) {
					approveDate = date.toLocalDate();
				}
				boolean isPaid = rs.getBoolean(Metadata.BookingTableColumn.PAID);
				booking = new Booking(id, userId, roomNumber, fromDate, toDate, guestsCount, isApproved, approveDate,
						isPaid);
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connectionPool.closeConnection(con, st, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return booking;
	}

	@Override
	public List<Booking> findAllBookingsByRoomNumber(int roomNumber) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Booking> bookings = new ArrayList<Booking>();
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(selectQueryProvider.getSelectQueryWhere(Metadata.BOOKING_TABLE,
					Metadata.BookingTableColumn.ROOM_NUMBER));
			st.setInt(1, roomNumber);
			rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(Metadata.BookingTableColumn.BOOKING_ID);
				int userId = rs.getInt(Metadata.BookingTableColumn.USER_ID);
				LocalDate fromDate = rs.getDate(Metadata.BookingTableColumn.FROM_DATE).toLocalDate();
				LocalDate toDate = rs.getDate(Metadata.BookingTableColumn.TO_DATE).toLocalDate();
				int guestsCount = rs.getInt(Metadata.BookingTableColumn.GUESTS_COUNT);
				Object isApprovedObj = rs.getObject(Metadata.BookingTableColumn.APPROVED);
				Boolean isApproved = null;
				if (isApprovedObj != null) {
					isApproved = rs.getBoolean(Metadata.BookingTableColumn.APPROVED);
				}
				java.sql.Date date = rs.getDate(Metadata.BookingTableColumn.APPROVE_DATE);
				LocalDate approveDate = null;
				if (date != null) {
					approveDate = date.toLocalDate();
				}
				boolean isPaid = rs.getBoolean(Metadata.BookingTableColumn.PAID);
				bookings.add(new Booking(id, userId, roomNumber, fromDate, toDate, guestsCount, isApproved, approveDate,
						isPaid));
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connectionPool.closeConnection(con, st, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return bookings;
	}

	@Override
	public Stay findStayById(int id) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Stay stay = null;
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(selectQueryProvider.getSelectQueryWhere(Metadata.ALL_STAYS_TABLE,
					Metadata.AllStaysTableColumn.STAY_ID));
			st.setInt(1, id);
			rs = st.executeQuery();

			while (rs.next()) {
				int clientId = rs.getInt(Metadata.AllStaysTableColumn.CLIENT_ID);
				int roomNumber = rs.getInt(Metadata.AllStaysTableColumn.ROOM_NUMBER);
				LocalDate fromDate = rs.getDate(Metadata.AllStaysTableColumn.FROM_DATE).toLocalDate();
				LocalDate toDate = rs.getDate(Metadata.AllStaysTableColumn.TO_DATE).toLocalDate();
				String notes = rs.getString(Metadata.AllStaysTableColumn.NOTES);
				stay = new Stay(id, clientId, roomNumber, fromDate, toDate, notes);
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connectionPool.closeConnection(con, st, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return stay;
	}

	@Override
	public List<Stay> findAllStaysByRoomNumber(int roomNumber) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Stay> stays = new ArrayList<Stay>();
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(selectQueryProvider.getSelectQueryWhere(Metadata.ALL_STAYS_TABLE,
					Metadata.AllStaysTableColumn.ROOM_NUMBER));
			st.setInt(1, roomNumber);
			rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(Metadata.AllStaysTableColumn.STAY_ID);
				int clientId = rs.getInt(Metadata.AllStaysTableColumn.CLIENT_ID);
				LocalDate fromDate = rs.getDate(Metadata.AllStaysTableColumn.FROM_DATE).toLocalDate();
				LocalDate toDate = rs.getDate(Metadata.AllStaysTableColumn.TO_DATE).toLocalDate();
				String notes = rs.getString(Metadata.AllStaysTableColumn.NOTES);
				stays.add(new Stay(id, clientId, roomNumber, fromDate, toDate, notes));
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connectionPool.closeConnection(con, st, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return stays;
	}

	@Override
	public int addBooking(Booking booking) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int id = 0;
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(insertQueryProvider.getInsertQuery(Metadata.BOOKING_TABLE),
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, booking.getUserId());
			st.setInt(2, booking.getRoomNumber());
			st.setDate(3, java.sql.Date.valueOf(booking.getFromDate()));
			st.setDate(4, java.sql.Date.valueOf(booking.getToDate()));
			st.setInt(5, booking.getGuestsCount());
			if (booking.isApproved() != null) {
				st.setBoolean(6, booking.isApproved());
			} else {
				st.setObject(6, null);
			}
			LocalDate date = booking.getApproveDate();
			if (date != null) {
				st.setDate(7, java.sql.Date.valueOf(date));
			} else {
				st.setDate(7, null);
			}
			st.setBoolean(8, booking.isPaid());
			st.executeUpdate();
			rs = st.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connectionPool.closeConnection(con, st, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return id;
	}

	@Override
	public int addStay(Stay stay) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int id = 0;
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(insertQueryProvider.getInsertQuery(Metadata.ALL_STAYS_TABLE),
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, stay.getClientId());
			st.setInt(2, stay.getRoomNumber());
			st.setDate(3, java.sql.Date.valueOf(stay.getFromDate()));
			st.setDate(4, java.sql.Date.valueOf(stay.getToDate()));
			st.setString(5, stay.getNotes());
			st.executeUpdate();
			rs = st.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connectionPool.closeConnection(con, st, rs);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return id;
	}

	@Override
	public void updateBooking(int id, Booking booking) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(updateQueryProvider.getUpdateQueryWhere(Metadata.BOOKING_TABLE,
					Metadata.BookingTableColumn.BOOKING_ID));
			st.setInt(1, booking.getUserId());
			st.setInt(2, booking.getRoomNumber());
			st.setDate(3, java.sql.Date.valueOf(booking.getFromDate()));
			st.setDate(4, java.sql.Date.valueOf(booking.getToDate()));
			st.setInt(5, booking.getGuestsCount());
			if (booking.isApproved() != null) {
				st.setBoolean(6, booking.isApproved());
			} else {
				st.setObject(6, null);
			}
			LocalDate date = booking.getApproveDate();
			if (date != null) {
				st.setDate(7, java.sql.Date.valueOf(date));
			} else {
				st.setDate(7, null);
			}
			st.setBoolean(8, booking.isPaid());
			st.setInt(9, id);
			st.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connectionPool.closeConnection(con, st);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
	}

	@Override
	public void updateStay(int id, Stay stay) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(updateQueryProvider.getUpdateQueryWhere(Metadata.ALL_STAYS_TABLE,
					Metadata.AllStaysTableColumn.STAY_ID));
			st.setInt(1, stay.getClientId());
			st.setInt(2, stay.getRoomNumber());
			st.setDate(3, java.sql.Date.valueOf(stay.getFromDate()));
			st.setDate(4, java.sql.Date.valueOf(stay.getToDate()));
			st.setString(5, stay.getNotes());
			st.setInt(6, id);
			st.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connectionPool.closeConnection(con, st);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
	}

	@Override
	public void deleteBooking(int id) throws DAOException {
		deleteById(id, Metadata.BOOKING_TABLE, Metadata.BookingTableColumn.BOOKING_ID);
	}

	@Override
	public void deleteStay(int id) throws DAOException {
		deleteById(id, Metadata.ALL_STAYS_TABLE, Metadata.AllStaysTableColumn.STAY_ID);
	}

	private void deleteById(int id, String tableName, String whereClauseColumnName) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(deleteQueryProvider.getDeleteQueryWhere(tableName, whereClauseColumnName));
			st.setInt(1, id);
			st.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connectionPool.closeConnection(con, st);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
	}
}
