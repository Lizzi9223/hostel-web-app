package by.epam.tc.web.dao.impl;

import by.epam.tc.web.dao.DAOException;
import by.epam.tc.web.dao.UserDAO;
import by.epam.tc.web.dao.database.connection_pool.ConnectionPool;
import by.epam.tc.web.dao.database.connection_pool.ConnectionPoolException;
import by.epam.tc.web.dao.database.metadata.Metadata;
import by.epam.tc.web.dao.database.query.*;
import by.epam.tc.web.entity.user.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserDAOImpl implements UserDAO{

    private final ConnectionPool connectionPool;
    private final SelectQuery selectQueryProvider = QueryFactory.getInstance().getSelectQuery();
    private final InsertQuery insertQueryProvider = QueryFactory.getInstance().getInsertQuery();
    private final DeleteQuery deleteQueryProvide = QueryFactory.getInstance().getDeleteQuery();
    private final UpdateQuery updateQueryProvide = QueryFactory.getInstance().getUpdateQuery();

    public UserDAOImpl() throws DAOException {
        try {
        	connectionPool = ConnectionPool.getInstance();
        }
        catch (ConnectionPoolException e){
            throw new DAOException(e);
        }
    }    
    

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws DAOException {    	
    	Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        User user = null;
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(selectQueryProvider.getInnerJoinSelectQueryWhere(
            		Metadata.USERS_TABLE, Metadata.USER_ROLES_TABLE, Metadata.UsersTableColumn.ROLE_ID, 
            		Metadata.UsersTableColumn.LOGIN));
            st.setString(1, login);
            rs = st.executeQuery();
            while (rs.next()){
            	try {
            		if(BCrypt.checkpw(password, rs.getString(Metadata.UsersTableColumn.PASSWORD))){
                		password = null;
                		int id = rs.getInt(Metadata.UsersTableColumn.USER_ID);
                        String roleName = rs.getString(Metadata.UserRolesTableColumn.ROLE_NAME);
                        Role role = Role.valueOf(roleName.toUpperCase());
                        user = new User(id, login, role);
                	}     
				} catch (Exception e) {
					return user;
				}           
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return  user;
	}
    

	@Override
    public List<User> getAllUsers() throws DAOException {
        List<User> users = new ArrayList<>();
        users.addAll(getAllAdmins());
        users.addAll(getAllClientUsers());
        users.sort(Comparator.comparingInt(User::getUserId));
        return users;
    }

    @Override
    public List<Admin> getAllAdmins() throws DAOException{
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        List<Admin> admins = new ArrayList<>();
        try{
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(selectQueryProvider.getInnerJoinSelectQuery(Metadata.USERS_TABLE,
                    Metadata.ADMINS_TABLE,
                    Metadata.UsersTableColumn.USER_ID));

            while (rs.next()){
                int id = rs.getInt(Metadata.UsersTableColumn.USER_ID);
                String login = rs.getString(Metadata.UsersTableColumn.LOGIN);
                String name = rs.getString(Metadata.AdminsTableColumn.NAME);
                String photo = rs.getString(Metadata.AdminsTableColumn.PHOTO);
                admins.add(new Admin(id, login, name, photo));
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return admins;
    }

    @Override
    public List<Client> getAllClientUsers() throws DAOException{
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        List<Client> clients = new ArrayList<>();
        try{
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(selectQueryProvider.getInnerJoinSelectQuery(Metadata.USERS_TABLE,
                    Metadata.ALL_CLIENTS_TABLE,
                    Metadata.UsersTableColumn.USER_ID));

            while (rs.next()){
                int userId = rs.getInt(Metadata.UsersTableColumn.USER_ID);
                String login = rs.getString(Metadata.UsersTableColumn.LOGIN);
                int clientId = rs.getInt(Metadata.AllClientsTableColumn.CLIENT_ID);
                String name = rs.getString(Metadata.AllClientsTableColumn.FIRST_NAME);
                String surname = rs.getString(Metadata.AllClientsTableColumn.LAST_NAME);
                String passport = rs.getString(Metadata.AllClientsTableColumn.PASSPORT_ID);
                LocalDate birth = rs.getDate(Metadata.AllClientsTableColumn.DATE_OF_BIRTH).toLocalDate();
                String country = rs.getString(Metadata.AllClientsTableColumn.COUNTY);
                String phone = rs.getString(Metadata.AllClientsTableColumn.PHONE_NUMBER);
                String email = rs.getString(Metadata.AllClientsTableColumn.EMAIL);
                clients.add(new Client(userId, login, clientId, name, surname, passport, birth, country, phone, email));
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return clients;
    }

    @Override
    public List<Client> getAllClients() throws DAOException{
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        List<Client> clients = new ArrayList<>();
        try{
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(selectQueryProvider.getSelectQuery(Metadata.ALL_CLIENTS_TABLE));

            while (rs.next()){
                int userId = rs.getInt(Metadata.UsersTableColumn.USER_ID);
                int clientId = rs.getInt(Metadata.AllClientsTableColumn.CLIENT_ID);
                String name = rs.getString(Metadata.AllClientsTableColumn.FIRST_NAME);
                String surname = rs.getString(Metadata.AllClientsTableColumn.LAST_NAME);
                String passport = rs.getString(Metadata.AllClientsTableColumn.PASSPORT_ID);
                LocalDate birth = rs.getDate(Metadata.AllClientsTableColumn.DATE_OF_BIRTH).toLocalDate();
                String country = rs.getString(Metadata.AllClientsTableColumn.COUNTY);
                String phone = rs.getString(Metadata.AllClientsTableColumn.PHONE_NUMBER);
                String email = rs.getString(Metadata.AllClientsTableColumn.EMAIL);
                Client newClient = new Client(clientId, name, surname, passport, birth, country, phone, email);
                newClient.setUserId(userId);
                clients.add(newClient);
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return clients;
    }

    @Override
    public List<BlackListClient> getAllBlackListClients() throws DAOException{
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        List<BlackListClient> clients = new ArrayList<>();
        try{
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(selectQueryProvider.getInnerJoinSelectQuery(Metadata.ALL_CLIENTS_TABLE,
                    Metadata.BLACK_LIST_TABLE,
                    Metadata.AllClientsTableColumn.CLIENT_ID));

            while (rs.next()){
                String reason = rs.getString(Metadata.BlackListTableColumn.REASON);
                LocalDate sinceDate = rs.getDate(Metadata.BlackListTableColumn.SINCE_DATE).toLocalDate();
                int clientId = rs.getInt(Metadata.AllClientsTableColumn.CLIENT_ID);
                String name = rs.getString(Metadata.AllClientsTableColumn.FIRST_NAME);
                String surname = rs.getString(Metadata.AllClientsTableColumn.LAST_NAME);
                String passport = rs.getString(Metadata.AllClientsTableColumn.PASSPORT_ID);
                LocalDate birth = rs.getDate(Metadata.AllClientsTableColumn.DATE_OF_BIRTH).toLocalDate();
                String country = rs.getString(Metadata.AllClientsTableColumn.COUNTY);
                String phone = rs.getString(Metadata.AllClientsTableColumn.PHONE_NUMBER);
                String email = rs.getString(Metadata.AllClientsTableColumn.EMAIL);
                clients.add(new BlackListClient(clientId, reason, sinceDate, name, surname,
                        passport, birth, country, phone, email));
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return clients;
    }

    @Override
    public List<RegularClient> getAllRegularClients() throws DAOException{
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        List<RegularClient> clients = new ArrayList<>();
        try{
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(selectQueryProvider.getInnerJoinSelectQuery(Metadata.ALL_CLIENTS_TABLE,
                    Metadata.REGULAR_CUSTOMERS_TABLE, Metadata.AllClientsTableColumn.CLIENT_ID));

            while (rs.next()){
                LocalDate sinceDate = rs.getDate(Metadata.RegularCustomersTableColumn.SINCE_DATE).toLocalDate();
                int discount = rs.getInt(Metadata.RegularCustomersTableColumn.DISCOUNT);
                String notes = rs.getString(Metadata.RegularCustomersTableColumn.NOTES);
                int clientId = rs.getInt(Metadata.AllClientsTableColumn.CLIENT_ID);
                String name = rs.getString(Metadata.AllClientsTableColumn.FIRST_NAME);
                String surname = rs.getString(Metadata.AllClientsTableColumn.LAST_NAME);
                String passport = rs.getString(Metadata.AllClientsTableColumn.PASSPORT_ID);
                LocalDate birth = rs.getDate(Metadata.AllClientsTableColumn.DATE_OF_BIRTH).toLocalDate();
                String country = rs.getString(Metadata.AllClientsTableColumn.COUNTY);
                String phone = rs.getString(Metadata.AllClientsTableColumn.PHONE_NUMBER);
                String email = rs.getString(Metadata.AllClientsTableColumn.EMAIL);
                clients.add(new RegularClient(clientId, sinceDate, discount, notes, name, surname,
                        passport, birth, country, phone, email));
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return clients;
    }

    @Override
    public int addUser(Admin user) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int id = 0;
        try{
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);

            int roleId = getRoleId(user.getRole());
            st = con.prepareStatement(insertQueryProvider.getInsertQuery(
            		Metadata.USERS_TABLE),
            		Statement.RETURN_GENERATED_KEYS);
            st.setString(1,user.getLogin());            
            st.setString(2,BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            user.setPassword(null);
            st.setInt(3,roleId);
            st.executeUpdate();
            rs = st.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);

            st = con.prepareStatement(insertQueryProvider.getInsertQuery(Metadata.ADMINS_TABLE));
            st.setInt(1, id);
            st.setString(2,user.getName());
            st.setString(3, user.getPhotoPath());
            st.executeUpdate();

            con.commit();
        }
        catch (ConnectionPoolException | SQLException e){
            try{
                con.rollback();
            }
            catch (SQLException ex){
                throw new DAOException(ex);
            }
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return id;
    }

    @Override
    public int addUser(User user) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int id = 0;
        try{
            con = connectionPool.takeConnection();
            int roleId = getRoleId(user.getRole());
            st = con.prepareStatement(insertQueryProvider.getInsertQuery(Metadata.USERS_TABLE),
            		Statement.RETURN_GENERATED_KEYS);
            st.setString(1,user.getLogin());
            st.setString(2,BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            user.setPassword(null);
            st.setInt(3,roleId);
            st.executeUpdate();
            rs = st.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        }
        catch (ConnectionPoolException | SQLException e){
            try{
                con.rollback();
            }
            catch (SQLException ex){
                throw new DAOException(ex);
            }
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return id;
    }

    @Override
    public int addClient(Client client) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int clientId = 0;
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(insertQueryProvider.getInsertQuery(
            		Metadata.ALL_CLIENTS_TABLE),
            		Statement.RETURN_GENERATED_KEYS);            
            int userId = client.getUserId();
            if(userId!=0) {
            	st.setInt(1, userId);
            }else {
            	st.setNull(1, Types.NULL);
            }
            st.setString(2,client.getLastName());
            st.setString(3, client.getFirstName());
            st.setString(4, client.getPassportId());
            st.setDate(5, java.sql.Date.valueOf(client.getBirthDate()));
            st.setString(6, client.getCountry());
            st.setString(7,client.getPhoneNumber());
            st.setString(8, client.getEmail());
            st.executeUpdate();
            rs = st.getGeneratedKeys();
            rs.next();
            clientId = rs.getInt(1);
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return clientId;
    } 
    
    @Override
    public int addUserClient(Client client) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int clientId = 0;
        try{
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);
            
            int roleId = getRoleId(client.getRole());
            st = con.prepareStatement(insertQueryProvider.getInsertQuery(Metadata.USERS_TABLE),
            		Statement.RETURN_GENERATED_KEYS);
            st.setString(1,client.getLogin());
            st.setString(2,BCrypt.hashpw(client.getPassword(), BCrypt.gensalt()));
            client.setPassword(null);
            st.setInt(3,roleId);
            st.executeUpdate();
            rs = st.getGeneratedKeys();
            rs.next();
            client.setUserId(rs.getInt(1));

            st = con.prepareStatement(insertQueryProvider.getInsertQuery(
            		Metadata.ALL_CLIENTS_TABLE),
            		Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, client.getUserId());
            st.setString(2,client.getLastName());
            st.setString(3, client.getFirstName());
            st.setString(4, client.getPassportId());
            st.setDate(5, java.sql.Date.valueOf(client.getBirthDate()));
            st.setString(6, client.getCountry());
            st.setString(7,client.getPhoneNumber());
            st.setString(8, client.getEmail());
            st.executeUpdate();
            rs = st.getGeneratedKeys();
            rs.next();
            clientId = rs.getInt(1);
            
            con.commit();
        }
        catch (ConnectionPoolException | SQLException e){
        	try{
                con.rollback();
            }
            catch (SQLException ex){
                throw new DAOException(ex);
            }
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return clientId;
    }

    @Override
    public void addToBlackList(BlackListClient client) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = connectionPool.takeConnection();

            st = con.prepareStatement(insertQueryProvider.getInsertQuery(Metadata.BLACK_LIST_TABLE));
            st.setInt(1, client.getClientId());
            st.setString(2, client.getReason());
            st.setDate(3, java.sql.Date.valueOf(client.getSinceDate()));
            st.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void addToRegularCustomers(RegularClient client) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = connectionPool.takeConnection();

            st = con.prepareStatement(insertQueryProvider.getInsertQuery(Metadata.REGULAR_CUSTOMERS_TABLE));
            st.setInt(1, client.getClientId());
            st.setDate(2, java.sql.Date.valueOf(client.getSinceDate()));
            st.setInt(3, client.getDiscount());
            st.setString(4, client.getNote());
            st.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
    }

    @Override
    public User findUserById(int userId) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        User user = new User();
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(selectQueryProvider.getInnerJoinSelectQueryWhere(Metadata.USERS_TABLE,
                    Metadata.USER_ROLES_TABLE, Metadata.UsersTableColumn.ROLE_ID, Metadata.UsersTableColumn.USER_ID));
            st.setInt(1, userId);
            rs = st.executeQuery();

            while (rs.next()){
                int id = rs.getInt(Metadata.UsersTableColumn.USER_ID);
                String login = rs.getString(Metadata.UsersTableColumn.LOGIN);
                String roleName = rs.getString(Metadata.UserRolesTableColumn.ROLE_NAME);
                Role role = Role.valueOf(roleName.toUpperCase());
                user = new User(id, login, role);
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return  user;
    }

    @Override
    public User findUserByLogin(String userLogin) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        User user = new User();
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(selectQueryProvider.getInnerJoinSelectQueryWhere(Metadata.USERS_TABLE,
                    Metadata.USER_ROLES_TABLE, Metadata.UsersTableColumn.ROLE_ID, Metadata.UsersTableColumn.LOGIN));
            st.setString(1, userLogin);
            rs = st.executeQuery();

            while (rs.next()){
                int id = rs.getInt(Metadata.UsersTableColumn.USER_ID);
                String login = rs.getString(Metadata.UsersTableColumn.LOGIN);
                String roleName = rs.getString(Metadata.UserRolesTableColumn.ROLE_NAME);
                Role role = Role.valueOf(roleName.toUpperCase());
                user = new User(id, login, role);
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return  user;
    }

    @Override
    public Admin findAdminById(int userId) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Admin admin = new Admin();
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(selectQueryProvider.getInnerJoinSelectQueryWhere(Metadata.USERS_TABLE,
                    Metadata.ADMINS_TABLE, Metadata.UsersTableColumn.USER_ID, Metadata.UsersTableColumn.USER_ID));
            st.setInt(1,userId);
            rs = st.executeQuery();

            while (rs.next()){
                int id = rs.getInt(Metadata.UsersTableColumn.USER_ID);
                String login = rs.getString(Metadata.UsersTableColumn.LOGIN);
                String name = rs.getString(Metadata.AdminsTableColumn.NAME);
                String photo = rs.getString(Metadata.AdminsTableColumn.PHOTO);
                admin = new Admin(id, login, name, photo);
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return admin;
    }
    
    @Override
    public Admin findAdminByLogin(String login) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Admin admin = new Admin();
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(selectQueryProvider.getInnerJoinSelectQueryWhere(Metadata.USERS_TABLE,
                    Metadata.ADMINS_TABLE, Metadata.UsersTableColumn.USER_ID, Metadata.UsersTableColumn.LOGIN));
            st.setString(1,login);
            rs = st.executeQuery();

            while (rs.next()){
                int id = rs.getInt(Metadata.UsersTableColumn.USER_ID);
                String name = rs.getString(Metadata.AdminsTableColumn.NAME);
                String photo = rs.getString(Metadata.AdminsTableColumn.PHOTO);
                admin = new Admin(id, login, name, photo);
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return admin;
    }

    @Override
    public Client findClientByUserId(int id) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Client client = new Client();
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(selectQueryProvider.getInnerJoinSelectQueryWhere(Metadata.ALL_CLIENTS_TABLE,
                    Metadata.USERS_TABLE, Metadata.UsersTableColumn.USER_ID, Metadata.USERS_TABLE, Metadata.UsersTableColumn.USER_ID));
            st.setInt(1,id);
            rs = st.executeQuery();

            while (rs.next()){
                int userId = rs.getInt(Metadata.UsersTableColumn.USER_ID);
                String login = rs.getString(Metadata.UsersTableColumn.LOGIN);
                int clientId = rs.getInt(Metadata.AllClientsTableColumn.CLIENT_ID);
                String name = rs.getString(Metadata.AllClientsTableColumn.FIRST_NAME);
                String surname = rs.getString(Metadata.AllClientsTableColumn.LAST_NAME);
                String passport = rs.getString(Metadata.AllClientsTableColumn.PASSPORT_ID);
                LocalDate birth = rs.getDate(Metadata.AllClientsTableColumn.DATE_OF_BIRTH).toLocalDate();
                String country = rs.getString(Metadata.AllClientsTableColumn.COUNTY);
                String phone = rs.getString(Metadata.AllClientsTableColumn.PHONE_NUMBER);
                String email = rs.getString(Metadata.AllClientsTableColumn.EMAIL);
                client = new Client(userId, login, clientId, name, surname, passport, birth, country, phone, email);
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return client;
    }
    
    @Override
    public Client findClientByLogin(String login) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Client client = new Client();
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(selectQueryProvider.getInnerJoinSelectQueryWhere(Metadata.ALL_CLIENTS_TABLE,
                    Metadata.USERS_TABLE, Metadata.UsersTableColumn.USER_ID, Metadata.USERS_TABLE, Metadata.UsersTableColumn.LOGIN));
            st.setString(1,login);
            rs = st.executeQuery();

            while (rs.next()){
                int userId = rs.getInt(Metadata.UsersTableColumn.USER_ID);
                int clientId = rs.getInt(Metadata.AllClientsTableColumn.CLIENT_ID);
                String name = rs.getString(Metadata.AllClientsTableColumn.FIRST_NAME);
                String surname = rs.getString(Metadata.AllClientsTableColumn.LAST_NAME);
                String passport = rs.getString(Metadata.AllClientsTableColumn.PASSPORT_ID);
                LocalDate birth = rs.getDate(Metadata.AllClientsTableColumn.DATE_OF_BIRTH).toLocalDate();
                String country = rs.getString(Metadata.AllClientsTableColumn.COUNTY);
                String phone = rs.getString(Metadata.AllClientsTableColumn.PHONE_NUMBER);
                String email = rs.getString(Metadata.AllClientsTableColumn.EMAIL);
                client = new Client(userId, login, clientId, name, surname, passport, birth, country, phone, email);
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return client;
    }

    @Override
    public Client findClientByClientId(int id) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Client client = new Client();
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(selectQueryProvider.getSelectQueryWhere(Metadata.ALL_CLIENTS_TABLE,
                    Metadata.AllClientsTableColumn.CLIENT_ID));
            st.setInt(1,id);
            rs = st.executeQuery();

            while (rs.next()){
                int userId = rs.getInt(Metadata.UsersTableColumn.USER_ID);
                int clientId = rs.getInt(Metadata.AllClientsTableColumn.CLIENT_ID);
                String name = rs.getString(Metadata.AllClientsTableColumn.FIRST_NAME);
                String surname = rs.getString(Metadata.AllClientsTableColumn.LAST_NAME);
                String passport = rs.getString(Metadata.AllClientsTableColumn.PASSPORT_ID);
                LocalDate birth = rs.getDate(Metadata.AllClientsTableColumn.DATE_OF_BIRTH).toLocalDate();
                String country = rs.getString(Metadata.AllClientsTableColumn.COUNTY);
                String phone = rs.getString(Metadata.AllClientsTableColumn.PHONE_NUMBER);
                String email = rs.getString(Metadata.AllClientsTableColumn.EMAIL);
                client = new Client(clientId, name, surname, passport, birth, country, phone, email);
                client.setUserId(userId);
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return client;

    }

    @Override
    public BlackListClient findInBlacklistById(int id) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        BlackListClient client = new BlackListClient();
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(selectQueryProvider.getInnerJoinSelectQueryWhere(Metadata.BLACK_LIST_TABLE,
                    Metadata.ALL_CLIENTS_TABLE, Metadata.BlackListTableColumn.CLIENT_ID, Metadata.BlackListTableColumn.CLIENT_ID));
            st.setInt(1,id);
            rs = st.executeQuery();

            while (rs.next()){
                String reason = rs.getString(Metadata.BlackListTableColumn.REASON);
                LocalDate sinceDate = rs.getDate(Metadata.BlackListTableColumn.SINCE_DATE).toLocalDate();
                int clientId = rs.getInt(Metadata.AllClientsTableColumn.CLIENT_ID);
                String name = rs.getString(Metadata.AllClientsTableColumn.FIRST_NAME);
                String surname = rs.getString(Metadata.AllClientsTableColumn.LAST_NAME);
                String passport = rs.getString(Metadata.AllClientsTableColumn.PASSPORT_ID);
                LocalDate birth = rs.getDate(Metadata.AllClientsTableColumn.DATE_OF_BIRTH).toLocalDate();
                String country = rs.getString(Metadata.AllClientsTableColumn.COUNTY);
                String phone = rs.getString(Metadata.AllClientsTableColumn.PHONE_NUMBER);
                String email = rs.getString(Metadata.AllClientsTableColumn.EMAIL);
                client = new BlackListClient(clientId, reason, sinceDate, name, surname,
                        passport, birth, country, phone, email);
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return client;
    }

    @Override
    public RegularClient findInRegularCustomersById(int id) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        RegularClient client = new RegularClient();
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(selectQueryProvider.getInnerJoinSelectQueryWhere(Metadata.REGULAR_CUSTOMERS_TABLE,
                    Metadata.ALL_CLIENTS_TABLE, Metadata.BlackListTableColumn.CLIENT_ID, Metadata.BlackListTableColumn.CLIENT_ID));
            st.setInt(1,id);
            rs = st.executeQuery();

            while (rs.next()){
                LocalDate sinceDate = rs.getDate(Metadata.RegularCustomersTableColumn.SINCE_DATE).toLocalDate();
                int discount = rs.getInt(Metadata.RegularCustomersTableColumn.DISCOUNT);
                String notes = rs.getString(Metadata.RegularCustomersTableColumn.NOTES);
                int clientId = rs.getInt(Metadata.AllClientsTableColumn.CLIENT_ID);
                String name = rs.getString(Metadata.AllClientsTableColumn.FIRST_NAME);
                String surname = rs.getString(Metadata.AllClientsTableColumn.LAST_NAME);
                String passport = rs.getString(Metadata.AllClientsTableColumn.PASSPORT_ID);
                LocalDate birth = rs.getDate(Metadata.AllClientsTableColumn.DATE_OF_BIRTH).toLocalDate();
                String country = rs.getString(Metadata.AllClientsTableColumn.COUNTY);
                String phone = rs.getString(Metadata.AllClientsTableColumn.PHONE_NUMBER);
                String email = rs.getString(Metadata.AllClientsTableColumn.EMAIL);
                client = new RegularClient(clientId, sinceDate, discount, notes, name, surname,
                        passport, birth, country, phone, email);
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return client;
    }

    @Override
    public void updateUser(int userId, User user) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(updateQueryProvide.getUpdateQueryWhere(Metadata.USERS_TABLE, Metadata.UsersTableColumn.USER_ID));
            st.setString(1,user.getLogin());
            st.setInt(2,userId);
            st.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void updateAdmin(int userId, Admin user) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(updateQueryProvide.getUpdateQueryWhere(Metadata.ADMINS_TABLE, Metadata.UsersTableColumn.USER_ID));
            st.setString(1,user.getName());
            st.setString(2,user.getPhotoPath());
            st.setInt(3,userId);
            st.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void updateClient(int clientId, Client user) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(updateQueryProvide.getUpdateQueryWhere(Metadata.ALL_CLIENTS_TABLE, Metadata.AllClientsTableColumn.CLIENT_ID));
            st.setString(1,user.getLastName());
            st.setString(2, user.getFirstName());
            st.setString(3, user.getPassportId());
            st.setDate(4, java.sql.Date.valueOf(user.getBirthDate()));
            st.setString(5, user.getCountry());
            st.setString(6,user.getPhoneNumber());
            st.setString(7, user.getEmail());
            st.setInt(8,clientId);
            st.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void updateBlacklistClient(int clientId, BlackListClient client) throws  DAOException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(updateQueryProvide.getUpdateQueryWhere(Metadata.BLACK_LIST_TABLE, Metadata.BlackListTableColumn.CLIENT_ID));
            st.setString(1,client.getReason());
            st.setDate(2, java.sql.Date.valueOf(client.getSinceDate()));
            st.setInt(3,clientId);
            st.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void updateRegularClient(int clientId, RegularClient client) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(updateQueryProvide.getUpdateQueryWhere(Metadata.REGULAR_CUSTOMERS_TABLE, Metadata.RegularCustomersTableColumn.CLIENT_ID));
            st.setDate(1, java.sql.Date.valueOf(client.getSinceDate()));
            st.setInt(2,client.getDiscount());
            st.setString(3,client.getNote());
            st.setInt(4,clientId);
            st.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
    }
    
    @Override
    public void updatePassword(String login, String password) throws DAOException{
    	Connection con = null;
        PreparedStatement st = null;
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(updateQueryProvide.getUpdatePasswordQueryWhere(Metadata.UsersTableColumn.LOGIN));
            st.setString(1, BCrypt.hashpw(password, BCrypt.gensalt()));
            password = null;
            st.setString(2,login);
            st.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void deleteUser(int userId) throws DAOException{
        deleteById(userId, Metadata.USERS_TABLE, Metadata.UsersTableColumn.USER_ID);
    }

    @Override
    public void deleteClient(int clientId) throws DAOException{
        deleteById(clientId, Metadata.ALL_CLIENTS_TABLE, Metadata.AllClientsTableColumn.CLIENT_ID);
    }

    @Override
    public void deleteFromBlackList(int clientId) throws DAOException{
        deleteById(clientId, Metadata.BLACK_LIST_TABLE, Metadata.BlackListTableColumn.CLIENT_ID);
    }

    @Override
    public void deleteFromRegularCustomers(int clientId) throws DAOException{
        deleteById(clientId, Metadata.REGULAR_CUSTOMERS_TABLE, Metadata.RegularCustomersTableColumn.CLIENT_ID);
    }

    @Override
    public int getUserId(String login) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int id = 0;
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(selectQueryProvider.getSelectQueryWhere(Metadata.USERS_TABLE, Metadata.UsersTableColumn.LOGIN));
            st.setString(1,login);
            rs = st.executeQuery();
            while (rs.next()){
                id = rs.getInt(Metadata.UsersTableColumn.USER_ID);
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return  id;
    }
    
    @Override
    public Role getUserRole(String login) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String role = null;
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(selectQueryProvider.getInnerJoinSelectQueryWhere(Metadata.USERS_TABLE, Metadata.USER_ROLES_TABLE, Metadata.UsersTableColumn.ROLE_ID, Metadata.UsersTableColumn.LOGIN));
            st.setString(1,login);
            rs = st.executeQuery();
            while (rs.next()){
                role = rs.getString(Metadata.UserRolesTableColumn.ROLE_NAME);
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return  Role.valueOf(role.toUpperCase());
    }

    @Override
    public int getClientId(String passportId) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int id = 0;
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(selectQueryProvider.getSelectQueryWhere(Metadata.ALL_CLIENTS_TABLE, Metadata.AllClientsTableColumn.PASSPORT_ID));
            st.setString(1,passportId);
            rs = st.executeQuery();
            while (rs.next()){
                id = rs.getInt(Metadata.AllClientsTableColumn.CLIENT_ID);
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return  id;
    }

    private int getRoleId(Role role) throws DAOException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int id = 0;
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(selectQueryProvider.getSelectQueryWhere(Metadata.USER_ROLES_TABLE, Metadata.UserRolesTableColumn.ROLE_NAME));
            st.setString(1,role.name().toLowerCase());
            rs = st.executeQuery();
            while (rs.next()){
                id = rs.getInt(Metadata.UserRolesTableColumn.ROLE_ID);
            }
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st,rs);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
        return  id;
    }

    private void deleteById(int id, String tableName, String whereClauseColumnName) throws DAOException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = connectionPool.takeConnection();
            st = con.prepareStatement(deleteQueryProvide.getDeleteQueryWhere(tableName, whereClauseColumnName));
            st.setInt(1,id);
            st.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e){
            throw new DAOException(e);
        }
        finally {
            try{
                connectionPool.closeConnection(con,st);
            }
            catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
        }
    }
}
