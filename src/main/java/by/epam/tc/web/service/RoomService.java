package by.epam.tc.web.service;

import java.math.BigDecimal;
import java.util.List;

import by.epam.tc.web.entity.room.Room;
import by.epam.tc.web.service.exception.ServiceException;

/**
 * Provides with methods for work with rooms on server layer
 * 
 * @author Lizzi9223
 *
 */
public interface RoomService {
	/**
	 * Gets list containing all the existing rooms
	 * 
	 * @return list of room objects
	 */
	List<Room> getAllRooms();

	/**
	 * Gets the room object based on room number
	 * 
	 * @param roomNumber the number of the room you want to find
	 * @return the room object by number
	 * @throws ServiceException if exception in DAO layer happens
	 */
	Room getRoomByNumber(int roomNumber) throws ServiceException;

	/**
	 * Gets lowest cost per night among all rooms
	 * 
	 * @return lowest room cost
	 */
	BigDecimal getMinCost();

	/**
	 * Gets highest cost per night among all rooms
	 * 
	 * @return highest room cost
	 */
	BigDecimal getMaxCost();

	/**
	 * Gets lowest room capacity among all rooms
	 * 
	 * @return lowest room capacity
	 */
	int getMinCapacity();

	/**
	 * Gets highest room capacity among all rooms
	 * 
	 * @return highest room capacity
	 */
	int getMaxCapacity();
}
