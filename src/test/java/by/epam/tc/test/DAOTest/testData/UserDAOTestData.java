package by.epam.tc.test.DAOTest.testData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.BlackListClient;
import by.epam.tc.web.entity.user.Client;
import by.epam.tc.web.entity.user.RegularClient;
import by.epam.tc.web.entity.user.Role;
import by.epam.tc.web.entity.user.User;

public final class UserDAOTestData {
	public final static List<User> expectedUsers = new ArrayList<User>(Arrays.asList(
			new User(1,"admin1","qwe12345",Role.ADMIN), 
			new User(2,"admin2","user2",Role.ADMIN),
			new User(3,"admin3","user2",Role.ADMIN),
			new User(4,"ADMIN","ADMIN",Role.ADMIN),
			new User(5,"user1","qwe12345",Role.CLIENT),
			new User(6,"user2","user2",Role.CLIENT),
			new User(7,"user3","system",Role.CLIENT),
			new User(8,"user4","system",Role.CLIENT)));
	
	public final static List<Admin> expectedAdmins = new ArrayList<Admin>(Arrays.asList(
			new Admin(1,"admin1","ADMIN_1",""),
			new Admin(2,"admin2","Liza",""),
			new Admin(3,"admin3","Valera",""),
			new Admin(4,"ADMIN","BOSS","")));
	
	public final static List<Client> expectedClientUsers = new ArrayList<Client>(Arrays.asList(
			new Client(5,"user1",1, "Мария", "Фурс","A46B3J754O", LocalDate.parse("2001-01-01"), "Беларусь", "+3752911111111", "maria@mail.ru"),
			new Client(6,"user2",2, "Иванов", "Иван","B46TE43", LocalDate.parse("2002-02-02"), "Беларусь", "+375292222222", "ivan@gmail.com"),
			new Client(7,"user3",3, "Smith", "Jack","QXCD23", LocalDate.parse("1980-10-20"), "UK", "+90234211", "jack@gmail.com"),
			new Client(8,"user4",4, "Russo", "Marco","I87I09", LocalDate.parse("1995-03-07"), "Italia", "+675286132", "marco@gmail.com")));
	
	public final static List<Client> expectedClients = new ArrayList<Client>(Arrays.asList(
			new Client(5,1,"Мария","Фурс",  "A46B3J754O", LocalDate.parse("2001-01-01"), "Беларусь", "+3752911111111", "maria@mail.ru"),
			new Client(6,2,"Иванов","Иван", "B46TE43", LocalDate.parse("2002-02-02"), "Беларусь", "+375292222222", "ivan@gmail.com"),
			new Client(7,3,"Smith", "Jack", "QXCD23", LocalDate.parse("1980-10-20"), "UK", "+90234211", "jack@gmail.com"),
			new Client(8,4, "Russo", "Marco","I87I09", LocalDate.parse("1995-03-07"), "Italia", "+675286132", "marco@gmail.com"),
			new Client(0, 5, "Петр", "Александров","GHG34567", LocalDate.parse("1960-11-14"), "Россия", "+80454321213", "alex@mail.ru"),
			new Client(0, 6, "Blanchet", "Lucas","FR7654J", LocalDate.parse("1978-07-26"), "France", "+2312435253", "lucas@mail.ru")));
	
	public final static List<BlackListClient> expectedBlackListClients = new ArrayList<BlackListClient>(Arrays.asList(
			new BlackListClient(2,"драка",LocalDate.parse("2021-03-10"),"Иван","Иванов","B46TE43",LocalDate.parse("2002-02-02"),"Беларусь","+375292222222","ivan@gmail.com"),
			new BlackListClient(3,"курение в комнате",LocalDate.parse("2020-12-12"),"Jack", "Smith", "QXCD23", LocalDate.parse("1980-10-20"), "UK", "+90234211", "jack@gmail.com")));
	
	public final static List<RegularClient> expectedRegularClients = new ArrayList<RegularClient>(Arrays.asList(
			new RegularClient(1, LocalDate.parse("2019-05-20"), 3, "2 бутылки воды", "Мария", "Фурс", "A46B3J754O", LocalDate.parse("2001-01-01"), "Беларусь", "+3752911111111", "maria@mail.ru"),
			new RegularClient(5, LocalDate.parse("2021-10-10"), 5, "заезд всегда после 20:00", "Петр", "Александров", "GHG34567", LocalDate.parse("1960-11-14"), "Россия", "+80454321213", "alex@mail.ru")));
	
	public final static Admin adminToAdd = new Admin("testAdmin","12345","TEST_ADMIN","");
	
	public final static User userToAdd = new User("testUser","12345",Role.CLIENT);
	
	public final static Client clientToAdd = new Client("testName","testSurname", "testPassport", LocalDate.parse("2000-01-01"), "Belarus", "+11111", "test@gmail.com");
	
	public final static Client userClientToAdd = new Client("testUserClient","12345","clientName", "clientSurname", "clientPassport", LocalDate.parse("1990-07-12"), "Ukraine", "+234556", "client@gmail.com");
	
	public final static BlackListClient blackListClientToAdd = new BlackListClient(4, "testReason", LocalDate.parse("2022-02-01"));
	
	public final static RegularClient regularClientToAdd = new RegularClient(6, LocalDate.parse("2022-01-01"), 10, "testNotes");
	
	public final static User userToFind = new User(12,"testUser",Role.CLIENT);
	
	public final static Client userClientToFind = new Client(5,"user1",1, "Мария", "Фурс","A46B3J754O", LocalDate.parse("2001-01-01"), "Беларусь", "+3752911111111", "maria@mail.ru");
	
	public final static User userToUpdate = new User(12,"testUserUpdate", Role.CLIENT);
	
	public final static Admin adminToUpdate = new Admin(11, "testAdmin", "nameUpdate", "photoUpdate");
	
	public final static Client clientToUpdate = new Client(7,"testNameUpdate","testSurnameUpdate", "testPassportUpdate", LocalDate.parse("2000-01-01"), "Belarus", "+11111", "test@gmail.com");
	
	public final static BlackListClient blackListClientToUpdate = new BlackListClient(2,"дракаUpdateReason",LocalDate.parse("2021-03-12"),"Иван","Иванов","B46TE43",LocalDate.parse("2002-02-02"),"Беларусь","+375292222222","ivan@gmail.com");
	
	public final static RegularClient regularClientToUpdate = new RegularClient(1, LocalDate.parse("2019-06-20"), 4, "2 бутылки воды UPDATE", "Мария", "Фурс", "A46B3J754O", LocalDate.parse("2001-01-01"), "Беларусь", "+3752911111111", "maria@mail.ru");
	
	public final static String passwordToUpdate = "newPassword";

}
