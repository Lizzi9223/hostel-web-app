package by.epam.tc.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.tc.web.dao.DAOException;
import by.epam.tc.web.dao.DAOFactory;
import by.epam.tc.web.dao.RoomDAO;
import by.epam.tc.web.dao.UserDAO;
import by.epam.tc.web.entity.room.Image;
import by.epam.tc.web.entity.room.Room;
import by.epam.tc.web.service.RoomService;
import by.epam.tc.web.service.ServiceException;

public class RoomServiceImpl implements RoomService {
	
private final RoomDAO roomDAO;
	
	public RoomServiceImpl() throws ServiceException {
		try {
			roomDAO = DAOFactory.getInstance().getRoomDAO();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Room> getAllRooms() throws ServiceException {
		List<Room> rooms = null;
		try {
			rooms = roomDAO.getAllRooms();
			for(Room room : rooms) {
				List<Image> images = roomDAO.getAllRoomImages(room.getRoomNumber());
				room.setImages(images);
			}
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		return rooms;
	}

}
