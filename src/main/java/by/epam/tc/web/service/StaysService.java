package by.epam.tc.web.service;

import java.time.LocalDate;
import java.util.List;

import by.epam.tc.web.entity.room.Room;

public interface StaysService {
	boolean areAvailablePlaces(int roomNumber, LocalDate fromDate, LocalDate toDate, int guestsNumber) throws ServiceException;
	List<Room> areAvailablePlaces(LocalDate fromDate, LocalDate toDate, int guestsNumber) throws ServiceException;
}
