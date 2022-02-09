package by.epam.tc.web.service.impl;

import java.math.BigDecimal;
import java.util.List;

import by.epam.tc.web.dao.DAOException;
import by.epam.tc.web.dao.DAOFactory;
import by.epam.tc.web.dao.RoomDAO;
import by.epam.tc.web.entity.room.Image;
import by.epam.tc.web.entity.room.Room;
import by.epam.tc.web.service.RoomService;
import by.epam.tc.web.service.exception.ServiceException;

/** 
 * The class {@code RoomServiceImpl} implements {@code RoomService}
 * 
 * @author Lizzi9223
 *
 */
public class RoomServiceImpl implements RoomService {

	private final RoomDAO roomDAO = DAOFactory.getInstance().getRoomDAO();
	private final List<Room> rooms;

	public RoomServiceImpl() throws ServiceException {
		try {
			rooms = roomDAO.getAllRooms();
			for (Room room : rooms) {
				List<Image> images = roomDAO.getAllRoomImages(room.getRoomNumber());
				room.setImages(images);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Room> getAllRooms() {
		return rooms;
	}

	@Override
	public Room getRoomByNumber(int roomNumber) throws ServiceException {
		Room room = null;
		try {
			room = roomDAO.findRoomByNumber(roomNumber);
			List<Image> images = roomDAO.getAllRoomImages(room.getRoomNumber());
			room.setImages(images);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return room;
	}

	@Override
	public BigDecimal getMinCost() {
		BigDecimal minCost = BigDecimal.valueOf(Integer.MAX_VALUE);
		for (Room room : rooms) {
			if (room.getCost().compareTo(minCost) < 0) {
				minCost = room.getCost();
			}
		}
		return minCost;
	}

	@Override
	public BigDecimal getMaxCost() {
		BigDecimal maxCost = BigDecimal.valueOf(Integer.MIN_VALUE);
		for (Room room : rooms) {
			if (room.getCost().compareTo(maxCost) > 0) {
				maxCost = room.getCost();
			}
		}
		return maxCost;
	}

	@Override
	public int getMinCapacity() {
		int minCapacity = Integer.MAX_VALUE;
		for (Room room : rooms) {
			if (room.getCapacity() < minCapacity) {
				minCapacity = room.getCapacity();
			}
		}
		return minCapacity;
	}

	@Override
	public int getMaxCapacity() {
		int maxCapacity = Integer.MIN_VALUE;
		for (Room room : rooms) {
			if (room.getCapacity() > maxCapacity) {
				maxCapacity = room.getCapacity();
			}
		}
		return maxCapacity;
	}

}
