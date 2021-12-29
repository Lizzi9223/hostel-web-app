
import by.epam.tc.web.dao.DAOException;
import by.epam.tc.web.dao.DAOFactory;
import by.epam.tc.web.dao.RoomDAO;
import by.epam.tc.web.dao.StaysDAO;
import by.epam.tc.web.dao.UserDAO;
import by.epam.tc.web.dao.database.metadata.Metadata;
import by.epam.tc.web.dao.database.query.impl.SelectQueryImpl;
import by.epam.tc.web.entity.room.Image;
import by.epam.tc.web.entity.room.Room;
import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.entity.stay.Stay;
import by.epam.tc.web.entity.user.*;
import by.epam.tc.web.service.RoomService;
import by.epam.tc.web.service.ServiceFactory;
import by.epam.tc.web.service.UserService;

import java.awt.print.Book;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Runner {

    public static void main(String[] args) {
        try{
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            RoomDAO roomDAO = daoFactory.getRoomDAO();
            StaysDAO staysDAO = daoFactory.getStaysDAO();
            
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            UserService userService = serviceFactory.getUserService();
            RoomService roomService = serviceFactory.getRoomService();
            
            
//            ResourceBundle resourceBundle;
//            String key = "prop.key1";
//            
//            resourceBundle = ResourceBundle.getBundle("prop",new Locale("ru"));
//            System.out.println(resourceBundle.getString(key));
//            
//            resourceBundle = ResourceBundle.getBundle("prop",new Locale("en"));
//            System.out.println(resourceBundle.getString(key));
//            
//            resourceBundle = ResourceBundle.getBundle("prop",new Locale("fr","FR"));
//            System.out.println(resourceBundle.getString(key));
            
//            
//            
//            List<Room> rooms = roomService.getAllRooms();
//            for(Room room : rooms) {
//            	System.out.println(room.toString());
//            	System.out.println("Images of room #" + room.getRoomNumber());
//            	System.out.println(room.getImages().toString());
//            	System.out.println("--------------------------");
//            }


            
            
            //System.out.println(userService.signIn("user10", "qwe12345"));
            
            
//--------StaysDAO Test-----------
//            Booking newBooking = new Booking(17,3,LocalDate.parse("2021-12-20"),
//                    LocalDate.parse("2021-12-25"),3,false,null,false);
//            //staysDAO.addBooking(newBooking);
//
//            newBooking.setApproved(true);
//            newBooking.setApproveDate(LocalDate.parse("2021-12-08"));
//            newBooking.setPaid(true);
//            //staysDAO.updateBooking(4, newBooking);
//            //staysDAO.deleteBooking(4);
//
//
//            Stay newStay = new Stay(8,4,LocalDate.parse("2019-12-20"),
//                    LocalDate.parse("2019-12-25"),null);
//            //staysDAO.addStay(newStay);
//
//            newStay.setNotes("notes");
//            newStay.setToDate(LocalDate.parse("2020-12-25"));
//            //staysDAO.updateStay(5, newStay);
//            //staysDAO.deleteStay(5);
//
//
//
//            List<Booking> bookings = staysDAO.getAllBookings();
//            for(Booking booking : bookings){
//                System.out.println(booking.toString());
//            }
//            System.out.println("-----------------------------------");
//            List<Stay> stays = staysDAO.getAllStays();
//            for(Stay stay : stays){
//                System.out.println(stay.toString());
//            }

//            System.out.println("-----------------------------------");
//            System.out.println(staysDAO.findBookingById(1));
//            System.out.println("-----------------------------------");
//            System.out.println(staysDAO.findStayById(1));


//--------RoomDAO Test-----------
//            Room newRoom = new Room(19,15,10,"мж",false,"notes");
//            //roomDAO.addRoom(newRoom);
//
//            newRoom.setBathroomInRoom(true);
//            newRoom.setCost(100);
//            //roomDAO.updateRoom(19,newRoom);
//
//            //roomDAO.deleteRoom(19);
//
//
//            Image newImage = new Image("some_path_NEW",2);
//            //roomDAO.addImage(newImage);
//
//            newImage.setRoomNumber(3);
//            int id = roomDAO.findImageByPath("some_path_NEW").getImgId();
//            System.out.println("ID = " + id);
//            //roomDAO.updateImage(id,newImage);
//
//            //roomDAO.deleteImage(id);
//
//
//
//            List<Room> rooms = roomDAO.getAllRooms();
//            for (Room room : rooms){
//                System.out.println(room.toString());
//            }
//
//            System.out.println("--------------------------------------");
//
//            List<Image> images = roomDAO.getAllRoomImages(2);
//            for (Image image : images){
//                System.out.println(image.toString());
//            }
//
//            System.out.println("--------------------------------------");
//
//            System.out.println(roomDAO.findRoomByNumber(1).toString());



//-----UserDAO Test---------
//            Client newUserClient = new Client("masha","furs","rte748", LocalDate.parse("2021-12-06"),
//                    "Belarus", "+37529384", "email@mail.ru");
//            newUserClient.setLogin("mashaClient_UPDATE");
//            newUserClient.setPassword("pa$$w0rd");
//            //userDAO.addUser(newUserClient);
//
//            int id = userDAO.getUserId(newUserClient);
//            newUserClient.setLogin("mashaClient_UPDATE");
//            userDAO.updateUser(id, newUserClient);
//
//            newUserClient.setUserId(id);
//            userDAO.deleteUser(newUserClient);



//            Admin newAdmin = new Admin("Mashs");
//            newAdmin.setLogin("mashaAdmin");
//            newAdmin.setPassword("pa$$w0rd");
//            //userDAO.addUser(newAdmin);
//
//            int id2 = userDAO.getUserId(newAdmin);
//            newAdmin.setName("Masha_a");
//            userDAO.updateAdmin(id2, newAdmin);
//
//            newAdmin.setUserId(id2);
//            userDAO.deleteUser(newAdmin);



//            Client newClient = new Client("mashaCl","fursCl","wwgfdsf3452", LocalDate.parse("2021-12-06"),
//                    "Belarus", "+37529384", "email@mail.ru");
//            //userDAO.addClient(newClient);
//
//            int id3 = userDAO.getClientId(newClient);
//            newClient.setFirstName("mashaCl_UPDATE");
//            userDAO.updateClient(id3, newClient);
//
//            newClient.setClientId(id3);
//            userDAO.deleteClient(newClient);


//            BlackListClient blackListClient = new BlackListClient(userDAO.getClientId(newClient),
//                    "reason", LocalDate.parse("2021-12-06"));
//            //userDAO.addToBlackList(blackListClient);
//
//            int id4 = userDAO.getClientId(newClient);
//            blackListClient.setReason("reason_UPDATE");
//            userDAO.updateBlacklistClient(id4, blackListClient);
//
//            userDAO.deleteFromBlackList(blackListClient);
//
//
//            RegularClient regularClient = new RegularClient(userDAO.getClientId(newUserClient),
//                    LocalDate.parse("2021-12-06"), 10, "notes");
//            //userDAO.addToRegularCustomers(regularClient);
//
//            int id5 = userDAO.getClientId(newUserClient);
//            regularClient.setNote("notes_UPDATE");
//            userDAO.updateRegularClient(id5, regularClient);
//
//            userDAO.deleteFromRegularCustomers(regularClient);




//            System.out.println("-------------------ALL_USERS-------------------------");
//
//            List<User> list = userDAO.getAllUsers();
//            for (User user : list){
//                System.out.println(user.getUserId() + " " + user.getLogin());
//            }
//
//            System.out.println("--------------------ALL_ADMINS------------------------");
//
//            List<Admin> list2 = userDAO.getAllAdmins();
//            for (Admin user : list2){
//                System.out.println(user.getUserId() + " " + user.getLogin() + " " + user.getName());
//            }
//
//            System.out.println("------------------ALL_CLIENT_USERS--------------------------");
//
//            List<Client> list3 = userDAO.getAllClientUsers();
//            for (Client user : list3){
//                System.out.println(user.getClientId() + " " + user.getUserId() + " " + user.getLogin() + " " + user.getPassportId());
//            }
//
//            System.out.println("------------------ALL_CLIENTS--------------------------");
//
//            List<Client> clients = userDAO.getAllClients();
//            for (Client user : clients){
//                System.out.println(user.getClientId() + " " + user.getUserId() + " " + user.getFirstName() + " " + user.getPassportId());
//            }
//
//            System.out.println("-----------------ALL_BLACK_LIST---------------------------");
//
//            List<BlackListClient> list4 = userDAO.getAllBlackListClients();
//            for (BlackListClient user : list4){
//                System.out.println(user.getClientId() + " " + user.getReason() + " " + user.getSinceDate());
//            }
//
//            System.out.println("---------------ALL_REGULAR_CUSTOMERS-----------------------------");
//
//            List<RegularClient> list5 = userDAO.getAllRegularClients();
//            for (RegularClient user : list5){
//                System.out.println(user.getClientId() + " " + user.getSinceDate() + " " + user.getNote());
//            }


//            System.out.println(userDAO.findClientByClientId(5).toString());
//            System.out.println("---------------------");
//            System.out.println(userDAO.findClientByUserId(16).toString());
//            System.out.println("---------------------");
//            System.out.println(userDAO.findAdminById(13).toString());
//            System.out.println("---------------------");
//            System.out.println(userDAO.findInBlacklistById(7).toString());
//            System.out.println("---------------------");
//            System.out.println(userDAO.findInRegularCustomersById(5).toString());
//            System.out.println("---------------------");



        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
