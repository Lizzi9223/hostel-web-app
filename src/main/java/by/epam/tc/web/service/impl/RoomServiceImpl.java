package by.epam.tc.web.service.impl;

import java.util.List;

import by.epam.tc.web.dao.DAOException;
import by.epam.tc.web.dao.DAOFactory;
import by.epam.tc.web.dao.RoomDAO;
import by.epam.tc.web.entity.room.Image;
import by.epam.tc.web.entity.room.Room;
import by.epam.tc.web.service.RoomService;
import by.epam.tc.web.service.ServiceException;

public class RoomServiceImpl implements RoomService {
	
	private final RoomDAO roomDAO;
	private final List<Room> rooms;
	
	public RoomServiceImpl() throws ServiceException {
		try {
			roomDAO = DAOFactory.getInstance().getRoomDAO();
			rooms = roomDAO.getAllRooms();
			for(Room room : rooms) {
				List<Image> images = roomDAO.getAllRoomImages(room.getRoomNumber());
				room.setImages(images);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<Room> getAllRooms(){
		return rooms;
	}
	
	@Override
	public Room getRoomByNumber(int roomNumber) throws ServiceException{
		Room room = null;
		try {
			room = roomDAO.findRoomByNumber(roomNumber);
			List<Image> images = roomDAO.getAllRoomImages(room.getRoomNumber());
			room.setImages(images);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		return room;
	}

	@Override
	public int getMinCost(){
		int minCost = Integer.MAX_VALUE;
		for(Room room : rooms) {
			if(room.getCost()<minCost) {
				minCost = room.getCost();
			}
		}
		return minCost;
	}
	
	@Override
	public int getMaxCost(){
		int maxCost = Integer.MIN_VALUE;
		for(Room room : rooms) {
			if(room.getCost()>maxCost) {
				maxCost = room.getCost();
			}
		}
		return maxCost;
	}
	
	@Override
	public int getMinCapacity(){
		int minCapacity = Integer.MAX_VALUE;
		for(Room room : rooms) {
			if(room.getCapacity()<minCapacity) {
				minCapacity = room.getCapacity();
			}
		}
		return minCapacity;
	}
	
	@Override
	public int getMaxCapacity() {
		int maxCapacity = Integer.MIN_VALUE;
		for(Room room : rooms) {
			if(room.getCapacity()>maxCapacity) {
				maxCapacity = room.getCapacity();
			}
		}
		return maxCapacity;
	}

}
