package by.epam.tc.web.dao;

import by.epam.tc.web.entity.room.Image;
import by.epam.tc.web.entity.room.Room;

import java.util.List;

public interface RoomDAO {
	List<Room> getAllRooms() throws DAOException;

	List<Image> getAllRoomImages(int roomNumber) throws DAOException;

	Room findRoomByNumber(int roomNumber) throws DAOException;

	Image findImageByPath(String path) throws DAOException;

	void addRoom(Room room) throws DAOException;

	int addImage(Image image) throws DAOException;

	void updateRoom(int roomNumber, Room room) throws DAOException;

	void updateImage(int imgId, Image image) throws DAOException;

	void deleteRoom(int roomNumber) throws DAOException;

	void deleteImage(int id) throws DAOException;
}
