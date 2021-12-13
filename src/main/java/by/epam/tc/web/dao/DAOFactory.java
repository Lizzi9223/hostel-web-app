package by.epam.tc.web.dao;

import by.epam.tc.web.dao.impl.RoomDAOImpl;
import by.epam.tc.web.dao.impl.StaysDAOImpl;
import by.epam.tc.web.dao.impl.UserDAOImpl;

public final class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();

    private UserDAO userDAO;
    private StaysDAO staysDAO;
    private RoomDAO roomDAO;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return instance;
    }

    public UserDAO getUserDAO() throws DAOException {
        try{
            if(userDAO == null){
                userDAO = new UserDAOImpl();
            }
        }
        catch (DAOException e){
            throw e;
        }
        return userDAO;
    }

    public StaysDAO getStaysDAO() throws DAOException{
        try{
            if(staysDAO == null){
                staysDAO = new StaysDAOImpl();
            }
        }
        catch (DAOException e){
            throw e;
        }
        return staysDAO;
    }

    public RoomDAO getRoomDAO() throws DAOException{
        try{
            if(roomDAO == null){
                roomDAO = new RoomDAOImpl();
            }
        }
        catch (DAOException e){
            throw e;
        }
        return roomDAO;
    }
}
