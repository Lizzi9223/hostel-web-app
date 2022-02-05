package by.epam.tc.test.DAOTest.testData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.epam.tc.web.entity.room.Image;
import by.epam.tc.web.entity.room.Room;

public final class RoomDAOTestData {
	public final static List<Room> expectedRooms = new ArrayList<Room>(Arrays.asList(
			new Room(1,BigDecimal.valueOf(25),4,"ж",false,"с балконом, есть утюг, стиральная машинка"),
			new Room(2,BigDecimal.valueOf(20),6,"ж",false,"с телевизором, есть утюг, стиральная машинка"),
			new Room(3,BigDecimal.valueOf(25),5,"м",false,"с телевизором, есть утюг, стиральная машинка"),
			new Room(4,BigDecimal.valueOf(20),7,"м",false,"с чайником, есть утюг, стиральная машинка"),
			new Room(5,BigDecimal.valueOf(18),8,"мж",true,"с холодильником, есть утюг, стиральная машинка"),
			new Room(6,BigDecimal.valueOf(18),8,"мж",true,"с холодильником, есть утюг, стиральная машинка"),
			new Room(7,BigDecimal.valueOf(35),2,"",true,"с телевизором, есть утюг, стиральная машинка"),
			new Room(8,BigDecimal.valueOf(35),2,"",true,"с телевизором и чайником, есть утюг, стиральная машинка")));
	
	public final static List<Image> expectedFirstRoomImages = new ArrayList<Image>(Arrays.asList(
			new Image(1,"images/1.jpg",1),
			new Image(2,"images/1_2.jpg",1)));
	
	public final static Room roomToAdd = new Room(10,BigDecimal.valueOf(10),10,"",true,"testData");
	
	public final static Image imageToAdd = new Image("images/1_test.jpg",1);
	
	public final static Room roomToUpdate = new Room(10,BigDecimal.valueOf(11),11,"м",false,"testDataUpdate");
	
	public final static Image imageToUpdate = new Image(14,"images/1_testUpdate.jpg",1);
}
