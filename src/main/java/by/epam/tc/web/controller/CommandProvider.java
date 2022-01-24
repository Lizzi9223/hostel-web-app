package by.epam.tc.web.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.tc.web.controller.constant.Constant;
import by.epam.tc.web.controller.impl.AddClientCommand;
import by.epam.tc.web.controller.impl.ApproveBookingCommand;
import by.epam.tc.web.controller.impl.BookRoomCommand;
import by.epam.tc.web.controller.impl.ChangeLanguageCommand;
import by.epam.tc.web.controller.impl.ChangePasswordCommand;
import by.epam.tc.web.controller.impl.CheckRoomAvailabilityCommand;
import by.epam.tc.web.controller.impl.ChooseBookingCommand;
import by.epam.tc.web.controller.impl.ChooseClientCommand;
import by.epam.tc.web.controller.impl.ChooseStayCommand;
import by.epam.tc.web.controller.impl.DeleteAccountCommand;
import by.epam.tc.web.controller.impl.DeleteBookingCommand;
import by.epam.tc.web.controller.impl.DeleteStayCommand;
import by.epam.tc.web.controller.impl.EditBookingCommand;
import by.epam.tc.web.controller.impl.EditCommand;
import by.epam.tc.web.controller.impl.EditStayCommand;
import by.epam.tc.web.controller.impl.LoginationCommand;
import by.epam.tc.web.controller.impl.RegistrationCommand;
import by.epam.tc.web.controller.impl.SubmitBookingCommand;
import by.epam.tc.web.controller.impl.SubmitStayCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToBookingsPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToClientsPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToContactsPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToErrorPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToLoginationPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToMyAccountPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToRegistrationPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToRoomsPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToStaysPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToWelcomePageCommand;

public final class CommandProvider {

	private final Map<String, Command> commands = new HashMap<String, Command>();
	
	public CommandProvider() {
		commands.put(Constant.Command.LOGINATION, new LoginationCommand());
		commands.put(Constant.Command.REGISTRATION, new RegistrationCommand());
		commands.put(Constant.Command.EDIT_ACCOUNT, new EditCommand());	
		commands.put(Constant.Command.CHANGE_PASSWORD, new ChangePasswordCommand());
		commands.put(Constant.Command.CHANGE_LANGUAGE, new ChangeLanguageCommand());
		commands.put(Constant.Command.BOOK_ROOM, new BookRoomCommand());
		commands.put(Constant.Command.DELETE_ACCOUNT, new DeleteAccountCommand());
		commands.put(Constant.Command.SEARCH_ROOMS, new GoToRoomsPageCommand());
		commands.put(Constant.Command.CHECK_ROOM_AVAILABILITY, new CheckRoomAvailabilityCommand());
		commands.put(Constant.Command.SUBMIT_BOOKING, new SubmitBookingCommand());
		commands.put(Constant.Command.CHOOSE_BOOKING, new ChooseBookingCommand());
		commands.put(Constant.Command.APPROVE_BOOKING, new ApproveBookingCommand());
		commands.put(Constant.Command.EDIT_BOOKING, new EditBookingCommand());		
		commands.put(Constant.Command.DELETE_BOOKING, new DeleteBookingCommand());
		commands.put(Constant.Command.ADD_STAY, new CheckRoomAvailabilityCommand());
		commands.put(Constant.Command.ADD_BOOKING, new CheckRoomAvailabilityCommand());
		commands.put(Constant.Command.EDIT_BOOKING_CHECK, new CheckRoomAvailabilityCommand());
		commands.put(Constant.Command.EDIT_STAY, new EditStayCommand());
		commands.put(Constant.Command.DELETE_STAY, new DeleteStayCommand());
		commands.put(Constant.Command.CHOOSE_STAY, new ChooseStayCommand());
		commands.put(Constant.Command.EDIT_STAY_CHECK, new EditStayCommand());
		commands.put(Constant.Command.SUBMIT_STAY, new SubmitStayCommand());
		commands.put(Constant.Command.CHOOSE_CLIENT, new ChooseClientCommand());
		commands.put(Constant.Command.ADD_CLIENT, new AddClientCommand());
		commands.put(Constant.Command.SEARCH_CLIENT, new AddClientCommand());
		
		commands.put(Constant.Command.GO_TO_ERROR_PAGE, new GoToErrorPageCommand());
		commands.put(Constant.Command.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
		commands.put(Constant.Command.GO_TO_LOGINATION_PAGE, new GoToLoginationPageCommand());
		commands.put(Constant.Command.GO_TO_WELCOME_PAGE, new GoToWelcomePageCommand());
		commands.put(Constant.Command.GO_TO_ROOMS_PAGE, new GoToRoomsPageCommand());
		commands.put(Constant.Command.GO_TO_MY_ACCOUNT_PAGE, new GoToMyAccountPageCommand());
		commands.put(Constant.Command.GO_TO_BOOKINGS_PAGE, new GoToBookingsPageCommand());
		commands.put(Constant.Command.GO_TO_STAYS_PAGE, new GoToStaysPageCommand());
		commands.put(Constant.Command.GO_TO_CONTACTS_PAGE, new GoToContactsPageCommand());
		commands.put(Constant.Command.GO_TO_CLIENTS_PAGE, new GoToClientsPageCommand());
	}
	
	public final Command getCommand(String commandName) {
		Command command = commands.get(commandName);
		return command;
	}
}
