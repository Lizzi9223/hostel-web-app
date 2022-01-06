package by.epam.tc.web.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.tc.web.controller.impl.AddStayCommand;
import by.epam.tc.web.controller.impl.ApproveBookingCommand;
import by.epam.tc.web.controller.impl.BookRoomCommand;
import by.epam.tc.web.controller.impl.ChangeLanguageCommand;
import by.epam.tc.web.controller.impl.ChangePasswordCommand;
import by.epam.tc.web.controller.impl.CheckRoomAvailabilityCommand;
import by.epam.tc.web.controller.impl.ChooseBookingCommand;
import by.epam.tc.web.controller.impl.DeleteAccountCommand;
import by.epam.tc.web.controller.impl.DeleteBookingCommand;
import by.epam.tc.web.controller.impl.EditBookingCommand;
import by.epam.tc.web.controller.impl.EditCommand;
import by.epam.tc.web.controller.impl.LoginationCommand;
import by.epam.tc.web.controller.impl.RegistrationCommand;
import by.epam.tc.web.controller.impl.SubmitBookingCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToBookingsPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToErrorPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToLoginationPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToMyAccountPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToRegistrationPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToRoomsPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToWelcomePageCommand;

public final class CommandProvider {

	private final Map<String, Command> commands = new HashMap<String, Command>();
	
	public CommandProvider() {
		commands.put("Logination", new LoginationCommand());
		commands.put("Registration", new RegistrationCommand());
		commands.put("Edit", new EditCommand());	
		commands.put("ChangePassword", new ChangePasswordCommand());
		commands.put("ChangeLanguage", new ChangeLanguageCommand());
		commands.put("BookRoom", new BookRoomCommand());
		commands.put("DeleteAccount", new DeleteAccountCommand());
		commands.put("SearchRooms", new GoToRoomsPageCommand());
		commands.put("CheckRoomAvailability", new CheckRoomAvailabilityCommand());
		commands.put("SubmitBooking", new SubmitBookingCommand());
		commands.put("ChooseBooking", new ChooseBookingCommand());
		commands.put("ApproveBooking", new ApproveBookingCommand());
		commands.put("EditBooking", new EditBookingCommand());		
		commands.put("DeleteBooking", new DeleteBookingCommand());
		commands.put("AddStay", new AddStayCommand());
		commands.put("AddBooking", new CheckRoomAvailabilityCommand());
		commands.put("EditCheckRoomAvailability", new CheckRoomAvailabilityCommand());
		
		commands.put("GO_TO_ERROR_PAGE", new GoToErrorPageCommand());
		commands.put("GO_TO_REGISTRATION_PAGE", new GoToRegistrationPageCommand());
		commands.put("GO_TO_LOGINATION_PAGE", new GoToLoginationPageCommand());
		commands.put("GO_TO_WELCOME_PAGE", new GoToWelcomePageCommand());
		commands.put("GO_TO_ROOMS_PAGE", new GoToRoomsPageCommand());
		commands.put("GO_TO_MY_ACCOUNT_PAGE", new GoToMyAccountPageCommand());
		commands.put("GO_TO_BOOKINGS_PAGE", new GoToBookingsPageCommand());
	}
	
	public final Command getCommand(String commandName) {
		Command command = commands.get(commandName);
		return command;
	}
}
