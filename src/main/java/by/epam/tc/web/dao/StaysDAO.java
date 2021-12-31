package by.epam.tc.web.dao;

import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.entity.stay.Stay;

import java.util.List;

public interface StaysDAO {
    List<Booking> getAllBookings() throws DAOException;
    List<Booking> getAllUserBookings(int userId) throws DAOException;
    List<Stay> getAllStays() throws DAOException;
    List<Stay> getAllClientStays(int clientId) throws DAOException;
    Booking findBookingById(int id) throws DAOException;
    List<Booking> findAllBookingsByRoomNumber(int roomNumber) throws DAOException;
    Stay findStayById(int id) throws DAOException;
    List<Stay> findAllStaysByRoomNumber(int roomNumber) throws DAOException;
    void addBooking(Booking booking) throws DAOException;
    void addStay(Stay stay) throws DAOException;
    void updateBooking(int id, Booking booking) throws DAOException;
    void updateStay(int id, Stay stay) throws DAOException;
    void deleteBooking(int id) throws DAOException;
    void deleteStay(int id) throws DAOException;
}
