package by.epam.tc.test.DAOTest.testData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.epam.tc.web.entity.stay.Booking;
import by.epam.tc.web.entity.stay.Stay;

public final class StaysDAOTestData {
	public final static List<Booking> expectedAllBooking = new ArrayList<Booking>(Arrays.asList(
			new Booking(1, 5, 1, LocalDate.parse("2021-12-20"), LocalDate.parse("2021-12-23"), 2, false, null, false),
			new Booking(2, 6, 4, LocalDate.parse("2021-12-20"), LocalDate.parse("2021-12-30"), 1, true, LocalDate.parse("2021-12-06"), true),
			new Booking(3, 8, 5, LocalDate.parse("2021-12-18"), LocalDate.parse("2021-12-19"), 4, false, LocalDate.parse("2021-12-05"), false)));
	
	public final static List<Booking> expectedUser5Bookings = new ArrayList<Booking>(Arrays.asList(
			new Booking(1, 5, 1, LocalDate.parse("2021-12-20"), LocalDate.parse("2021-12-23"), 2, false, null, false)));
	
	public final static List<Stay> expectedAllStays = new ArrayList<Stay>(Arrays.asList(
			new Stay(1, 1, 8, LocalDate.parse("2021-07-10"), LocalDate.parse("2021-07-14"), "не хватило полотенца"),
			new Stay(2, 4, 5, LocalDate.parse("2021-09-05"), LocalDate.parse("2021-09-14"), "все понравилось"),
			new Stay(3, 5, 3, LocalDate.parse("2019-01-13"), LocalDate.parse("2019-01-15"), "все понравилось"),
			new Stay(4, 6, 3, LocalDate.parse("2019-01-13"), LocalDate.parse("2019-01-15"), "все понравилось")));
	
	public final static List<Stay> expectedClient4Stays = new ArrayList<Stay>(Arrays.asList(
			new Stay(2, 4, 5, LocalDate.parse("2021-09-05"), LocalDate.parse("2021-09-14"), "все понравилось")));
	
	public final static List<Booking> expectedRoom5Bookings = new ArrayList<Booking>(Arrays.asList(
			new Booking(3, 8, 5, LocalDate.parse("2021-12-18"), LocalDate.parse("2021-12-19"), 4, false, LocalDate.parse("2021-12-05"), false)));
	
	public final static List<Stay> expectedRoom3Stays = new ArrayList<Stay>(Arrays.asList(
			new Stay(3, 5, 3, LocalDate.parse("2019-01-13"), LocalDate.parse("2019-01-15"), "все понравилось"),
			new Stay(4, 6, 3, LocalDate.parse("2019-01-13"), LocalDate.parse("2019-01-15"), "все понравилось")));
	
	public final static Booking bookingToAdd = new Booking(5, 3, LocalDate.parse("2022-02-01"), LocalDate.parse("2022-02-05"), 1, false, null, false);
	
	public final static Stay stayToAdd = new Stay(6, 4, LocalDate.parse("2022-02-01"), LocalDate.parse("2022-02-05"), "testData");
	
	public final static Booking bookingToUpdate = new Booking(4, 5, 3, LocalDate.parse("2022-01-29"), LocalDate.parse("2022-02-05"), 1, true, LocalDate.parse("2022-02-01"), true);
	
	public final static Stay stayToUpdate = new Stay(5, 6, 5, LocalDate.parse("2022-02-04"), LocalDate.parse("2022-02-15"), "testDataUpdate");
}
