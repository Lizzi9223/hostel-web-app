package by.epam.tc.web.dao.impl;

import by.epam.tc.web.dao.DAOException;
import by.epam.tc.web.dao.RoomDAO;
import by.epam.tc.web.dao.database.connection_pool.ConnectionPool;
import by.epam.tc.web.dao.database.connection_pool.ConnectionPoolException;
import by.epam.tc.web.dao.database.metadata.Metadata;
import by.epam.tc.web.dao.database.query.*;
import by.epam.tc.web.entity.room.Image;
import by.epam.tc.web.entity.room.Room;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();
	private final SelectQuery selectQueryProvider = QueryFactory.getInstance().getSelectQuery();
	private final InsertQuery insertQueryProvider = QueryFactory.getInstance().getInsertQuery();
	private final DeleteQuery deleteQueryProvider = QueryFactory.getInstance().getDeleteQuery();
	private final UpdateQuery updateQueryProvider = QueryFactory.getInstance().getUpdateQuery();

	public RoomDAOImpl() {
	}

	@Override
	public List<Room> getAllRooms() throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<Room> rooms = new ArrayList<>();
		try {
			con = connectionPool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(selectQueryProvider.getSelectQuery(Metadata.ROOMS_TABLE));

			while (rs.next()) {
				int roomNumber = rs.getInt(Metadata.RoomsTableColumn.ROOM_NUMBER);
				BigDecimal cost = rs.getBigDecimal(Metadata.RoomsTableColumn.COST);
				int capacity = rs.getInt(Metadata.RoomsTableColumn.CAPACITY);
				String gender = rs.getString(Metadata.RoomsTableColumn.GENDER);
				boolean isBathroomInRoom = rs.getBoolean(Metadata.RoomsTableColumn.BATHROOM);
				String notes = rs.getString(Metadata.RoomsTableColumn.NOTES);
				rooms.add(new Room(roomNumber, cost, capacity, gender, isBathroomInRoom, notes));
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
		return rooms;
	}

	@Override
	public List<Image> getAllRoomImages(int roomNumber) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Image> images = new ArrayList<>();
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(selectQueryProvider.getSelectQueryWhere(Metadata.IMAGES_TABLE,
					Metadata.ImagesTableColumn.ROOM_NUMBER));
			st.setInt(1, roomNumber);
			rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(Metadata.ImagesTableColumn.IMG_ID);
				String path = rs.getString(Metadata.ImagesTableColumn.IMG_PATH);
				images.add(new Image(id, path, roomNumber));
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
		return images;
	}

	@Override
	public Room findRoomByNumber(int roomNumber) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Room room = null;
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(selectQueryProvider.getSelectQueryWhere(Metadata.ROOMS_TABLE,
					Metadata.RoomsTableColumn.ROOM_NUMBER));
			st.setInt(1, roomNumber);
			rs = st.executeQuery();

			while (rs.next()) {
				BigDecimal cost = rs.getBigDecimal(Metadata.RoomsTableColumn.COST);
				int capacity = rs.getInt(Metadata.RoomsTableColumn.CAPACITY);
				String gender = rs.getString(Metadata.RoomsTableColumn.GENDER);
				boolean isBathroomInRoom = rs.getBoolean(Metadata.RoomsTableColumn.BATHROOM);
				String notes = rs.getString(Metadata.RoomsTableColumn.NOTES);
				room = new Room(roomNumber, cost, capacity, gender, isBathroomInRoom, notes);
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
		return room;
	}

	@Override
	public Image findImageByPath(String path) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Image image = null;
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(selectQueryProvider.getSelectQueryWhere(Metadata.IMAGES_TABLE,
					Metadata.ImagesTableColumn.IMG_PATH));
			st.setString(1, path);
			rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(Metadata.ImagesTableColumn.IMG_ID);
				int roomNumber = rs.getInt(Metadata.ImagesTableColumn.ROOM_NUMBER);
				image = new Image(id, path, roomNumber);
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
		return image;
	}

	@Override
	public void addRoom(Room room) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(insertQueryProvider.getInsertQuery(Metadata.ROOMS_TABLE));
			st.setInt(1, room.getRoomNumber());
			st.setBigDecimal(2, room.getCost());
			st.setInt(3, room.getCapacity());
			st.setString(4, room.getGender());
			st.setBoolean(5, room.isBathroomInRoom());
			st.setString(6, room.getNotes());
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
	public int addImage(Image image) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int id = 0;
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(insertQueryProvider.getInsertQuery(Metadata.IMAGES_TABLE),
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, image.getImgPath());
			st.setInt(2, image.getRoomNumber());
			st.executeUpdate();
			rs = st.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				connectionPool.closeConnection(con, st);
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
		return id;
	}

	@Override
	public void updateRoom(int roomNumber, Room room) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(updateQueryProvider.getUpdateQueryWhere(Metadata.ROOMS_TABLE,
					Metadata.RoomsTableColumn.ROOM_NUMBER));
			st.setInt(1, room.getRoomNumber());
			st.setBigDecimal(2, room.getCost());
			st.setInt(3, room.getCapacity());
			st.setString(4, room.getGender());
			st.setBoolean(5, room.isBathroomInRoom());
			st.setString(6, room.getNotes());
			st.setInt(7, roomNumber);
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
	public void updateImage(int imgId, Image image) throws DAOException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = connectionPool.takeConnection();
			st = con.prepareStatement(
					updateQueryProvider.getUpdateQueryWhere(Metadata.IMAGES_TABLE, Metadata.ImagesTableColumn.IMG_ID));
			st.setString(1, image.getImgPath());
			st.setInt(2, image.getRoomNumber());
			st.setInt(3, imgId);
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
	public void deleteRoom(int roomNumber) throws DAOException {
		deleteById(roomNumber, Metadata.ROOMS_TABLE, Metadata.RoomsTableColumn.ROOM_NUMBER);
	}

	@Override
	public void deleteImage(int id) throws DAOException {
		deleteById(id, Metadata.IMAGES_TABLE, Metadata.ImagesTableColumn.IMG_ID);
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
