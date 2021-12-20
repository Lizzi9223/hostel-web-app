package by.epam.tc.web.service;

import java.util.List;

import by.epam.tc.web.entity.room.Room;

public interface RoomService {

	List<Room> getAllRooms() throws ServiceException;
	
}
