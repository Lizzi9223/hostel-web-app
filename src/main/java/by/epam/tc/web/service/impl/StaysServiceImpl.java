package by.epam.tc.web.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.epam.tc.web.dao.DAOException;
import by.epam.tc.web.dao.DAOFactory;
import by.epam.tc.web.dao.RoomDAO;
import by.epam.tc.web.dao.StaysDAO;
import by.epam.tc.web.dao.UserDAO;
import by.epam.tc.web.entity.room.Room;
import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.entity.stay.Stay;
import by.epam.tc.web.entity.user.Role;
import by.epam.tc.web.service.ServiceException;
import by.epam.tc.web.service.StaysService;

public class StaysServiceImpl implements StaysService {
	
	private final StaysDAO staysDAO;
	private final RoomDAO roomDAO;
	private final UserDAO userDAO;
	
	public StaysServiceImpl() throws ServiceException {
		try {
			staysDAO = DAOFactory.getInstance().getStaysDAO();
			roomDAO = DAOFactory.getInstance().getRoomDAO();
			userDAO = DAOFactory.getInstance().getUserDAO();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<Booking> getAllBookings() throws ServiceException {
		List<Booking> bookings = new ArrayList<Booking>();
		try {
			bookings = staysDAO.getAllBookings();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return bookings;
	}
	
	@Override
	public List<Booking> getAllUserBookings(String userLogin) throws ServiceException {
		List<Booking> bookings = new ArrayList<Booking>();
		try {
			int userId = userDAO.getUserId(userLogin);
			bookings = staysDAO.getAllUserBookings(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return bookings;
	}

	@Override
	public Booking getBookingById(int id) throws ServiceException {
		Booking booking = new Booking();
		try {
			booking = staysDAO.findBookingById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return booking;
	}

	@Override
	public void addBooking(String userLogin, LocalDate fromDate, LocalDate toDate, int guestsNumber, int roomNumber)
			throws ServiceException {
		try {
			Booking booking;
			int userId = userDAO.getUserId(userLogin);
			if(userDAO.getUserRole(userLogin) == Role.CLIENT) {
				booking = new Booking(userId, roomNumber, fromDate, toDate, guestsNumber, null, null, false);
			}else {
				booking = new Booking(userId, roomNumber, fromDate, toDate, guestsNumber, true, LocalDate.now(), false);
			}			
			staysDAO.addBooking(booking);
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

	@Override
	public void approveBooking(int id, boolean isApproved) throws ServiceException {
		try {
			Booking booking = staysDAO.findBookingById(id);
			booking.setApproved(isApproved);
			if(isApproved) {
				booking.setApproveDate(LocalDate.now());
			}
			staysDAO.updateBooking(id, booking);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}
	
	@Override
	public void updateBooking(int id, Booking booking) throws ServiceException {
		try {
			staysDAO.updateBooking(id, booking);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}

	@Override
	public void deleteBooking(int id) throws ServiceException {
		try {
			staysDAO.deleteBooking(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}

	@Override
	public void deleteStay(int id) throws ServiceException {
		try {
			staysDAO.deleteStay(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}
}
