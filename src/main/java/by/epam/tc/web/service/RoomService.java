package by.epam.tc.web.service;

import java.math.BigDecimal;
import java.util.List;

import by.epam.tc.web.entity.room.Room;
import by.epam.tc.web.service.exception.ServiceException;

public interface RoomService {
	List<Room> getAllRooms();

	Room getRoomByNumber(int roomNumber) throws ServiceException;

	BigDecimal getMinCost();

	BigDecimal getMaxCost();

	int getMinCapacity();

	int getMaxCapacity();
}
