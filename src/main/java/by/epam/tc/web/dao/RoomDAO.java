package by.epam.tc.web.dao;

import by.epam.tc.web.entity.room.Image;
import by.epam.tc.web.entity.room.Room;

import java.util.List;

/**
 * Provides with methods for work with rooms on DAO layer
 * 
 * @author Lizzi9223
 *
 */
public interface RoomDAO {
	/**
	 * Gets list containing all the existing rooms
	 * 
	 * @return list of room objects
	 * @throws DAOException if exception in connection pool occurs
	 */
	List<Room> getAllRooms() throws DAOException;

	/**
	 * Gets list containing all the images belonging to specific room
	 * 
	 * @param roomNumber number of room which images to find
	 * @return list of image objects
	 * @throws DAOException if exception in connection pool occurs
	 */
	List<Image> getAllRoomImages(int roomNumber) throws DAOException;

	/**
	 * Finds room based on its number
	 * 
	 * @param roomNumber number of the room to find
	 * @return room objects
	 * @throws DAOException if exception in connection pool occurs
	 */
	Room findRoomByNumber(int roomNumber) throws DAOException;

	/**
	 * Finds image based on its path
	 * 
	 * @param path path of the image to find
	 * @return image object
	 * @throws DAOException if exception in connection pool occurs
	 */
	Image findImageByPath(String path) throws DAOException;

	/**
	 * Adds new room
	 * 
	 * @param room room object to add
	 * @throws DAOException if exception in connection pool occurs
	 */
	void addRoom(Room room) throws DAOException;

	/**
	 * Adds new room's image
	 *  
	 * @param image image object to add
	 * @return identifier of the added image
	 * @throws DAOException if exception in connection pool occurs
	 */
	int addImage(Image image) throws DAOException;

	/**
	 * Updates room
	 * 
	 * @param roomNumber number of the room to update
	 * @param room room object with data to update (if you do not update some field, fill it with initial data)
	 * @throws DAOException if exception in connection pool occurs
	 */
	void updateRoom(int roomNumber, Room room) throws DAOException;

	/**
	 * Updates image
	 * 
	 * @param imgId identifier of the room to update
	 * @param image image object with data to update (if you do not update some field, fill it with initial data)
	 * @throws DAOException if exception in connection pool occurs
	 */
	void updateImage(int imgId, Image image) throws DAOException;

	/**
	 * Deletes room
	 * 
	 * @param roomNumber number of the room to delete
	 * @throws DAOException if exception in connection pool occurs
	 */
	void deleteRoom(int roomNumber) throws DAOException;

	/**
	 * Deletes images
	 * 
	 * @param id identifier of the image to delete
	 * @throws DAOException if exception in connection pool occurs
	 */
	void deleteImage(int id) throws DAOException;
}
