package by.epam.tc.web.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.epam.tc.web.dao.DAOException;
import by.epam.tc.web.dao.DAOFactory;
import by.epam.tc.web.dao.RoomDAO;
import by.epam.tc.web.dao.StaysDAO;
import by.epam.tc.web.entity.room.Room;
import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.entity.stay.Stay;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.StaysService;

public class StaysServiceImpl implements StaysService {
	
	private final StaysDAO staysDAO;
	private final RoomDAO roomDAO;
	
	public StaysServiceImpl() throws ServiceException {
		try {
			staysDAO = DAOFactory.getInstance().getStaysDAO();
			roomDAO = DAOFactory.getInstance().getRoomDAO();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public boolean areAvailablePlaces(int roomNumber, LocalDate fromDate, LocalDate toDate, int guestsNumber) throws ServiceException {
		try {
			List<Booking> bookings = staysDAO.findAllBookingsByRoomNumber(roomNumber);
			List<Stay> stays = staysDAO.findAllStaysByRoomNumber(roomNumber);
			for(LocalDate i = fromDate; i.isBefore(toDate); i=i.plusDays(1)) {
				int bookedPlacesAmount = 0;
				for(Booking booking : bookings) {
					if((i.isAfter(booking.getFromDate()) || i.equals(booking.getFromDate())) 
							&& (i.isBefore(booking.getToDate()) || i.equals(booking.getToDate()))) {
						bookedPlacesAmount += booking.getGuestsCount();
					}
				}	
				for(Stay stay : stays) {
					if(i.isBefore(stay.getToDate())) {
						bookedPlacesAmount += 1;
					}
				}	
				if(roomDAO.findRoomByNumber(roomNumber).getCapacity() - bookedPlacesAmount < guestsNumber) {
					return false;
				}
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}		
		return true;
	}

	@Override
	public List<Room> areAvailablePlaces(LocalDate fromDate, LocalDate toDate, int guestsNumber) throws ServiceException {
		List<Room> availableRooms = new ArrayList<Room>();
		try {
			List<Room> rooms = roomDAO.getAllRooms();			
			for(Room room : rooms) {
				if(areAvailablePlaces(room.getRoomNumber(), fromDate, toDate, guestsNumber)) {
					availableRooms.add(room);
				}
			}			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}		
		return availableRooms;
	}
}