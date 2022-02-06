package by.epam.tc.web.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.tc.web.controller.constant.CommandName;
import by.epam.tc.web.controller.impl.AddClientCommand;
import by.epam.tc.web.controller.impl.ApproveBookingCommand;
import by.epam.tc.web.controller.impl.BookRoomCommand;
import by.epam.tc.web.controller.impl.ChangeLanguageCommand;
import by.epam.tc.web.controller.impl.ChangePasswordCommand;
import by.epam.tc.web.controller.impl.CheckRoomAvailabilityCommand;
import by.epam.tc.web.controller.impl.ChooseAdminCommand;
import by.epam.tc.web.controller.impl.ChooseBookingCommand;
import by.epam.tc.web.controller.impl.ChooseClientCommand;
import by.epam.tc.web.controller.impl.ChooseStayCommand;
import by.epam.tc.web.controller.impl.ChooseUserCommand;
import by.epam.tc.web.controller.impl.DeleteAccountCommand;
import by.epam.tc.web.controller.impl.DeleteBookingCommand;
import by.epam.tc.web.controller.impl.DeleteStayCommand;
import by.epam.tc.web.controller.impl.EditBookingCommand;
import by.epam.tc.web.controller.impl.EditClientCommand;
import by.epam.tc.web.controller.impl.EditCommand;
import by.epam.tc.web.controller.impl.EditStayCommand;
import by.epam.tc.web.controller.impl.LoginationCommand;
import by.epam.tc.web.controller.impl.RegistrationCommand;
import by.epam.tc.web.controller.impl.SubmitBookingCommand;
import by.epam.tc.web.controller.impl.SubmitStayCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToAdminsPageCommand;
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
		commands.put(CommandName.LOGINATION, new LoginationCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.EDIT_ACCOUNT, new EditCommand());
		commands.put(CommandName.CHANGE_PASSWORD, new ChangePasswordCommand());
		commands.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguageCommand());
		commands.put(CommandName.BOOK_ROOM, new BookRoomCommand());
		commands.put(CommandName.DELETE_ACCOUNT, new DeleteAccountCommand());
		commands.put(CommandName.DELETE_ADMIN, new DeleteAccountCommand());
		commands.put(CommandName.SEARCH_ROOMS, new GoToRoomsPageCommand());
		commands.put(CommandName.CHECK_ROOM_AVAILABILITY, new CheckRoomAvailabilityCommand());
		commands.put(CommandName.SUBMIT_BOOKING, new SubmitBookingCommand());
		commands.put(CommandName.CHOOSE_BOOKING, new ChooseBookingCommand());
		commands.put(CommandName.APPROVE_BOOKING, new ApproveBookingCommand());
		commands.put(CommandName.EDIT_BOOKING, new EditBookingCommand());
		commands.put(CommandName.DELETE_BOOKING, new DeleteBookingCommand());
		commands.put(CommandName.ADD_STAY, new CheckRoomAvailabilityCommand());
		commands.put(CommandName.ADD_BOOKING, new CheckRoomAvailabilityCommand());
		commands.put(CommandName.EDIT_BOOKING_CHECK, new CheckRoomAvailabilityCommand());
		commands.put(CommandName.EDIT_STAY, new EditStayCommand());
		commands.put(CommandName.DELETE_STAY, new DeleteStayCommand());
		commands.put(CommandName.CHOOSE_STAY, new ChooseStayCommand());
		commands.put(CommandName.EDIT_STAY_CHECK, new EditStayCommand());
		commands.put(CommandName.SUBMIT_STAY, new SubmitStayCommand());
		commands.put(CommandName.CHOOSE_CLIENT, new ChooseClientCommand());
		commands.put(CommandName.FINISH_CHOOSING_CLIENT, new ChooseClientCommand());
		commands.put(CommandName.ADD_CLIENT, new AddClientCommand());
		commands.put(CommandName.SEARCH_CLIENT, new GoToClientsPageCommand());
		commands.put(CommandName.GUESTS_ARRIVED, new SubmitStayCommand());
		commands.put(CommandName.CHOOSE_USER, new ChooseUserCommand());
		commands.put(CommandName.CHOOSE_ADMIN, new ChooseAdminCommand());
		commands.put(CommandName.EDIT_CLIENT, new EditClientCommand());

		commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPageCommand());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
		commands.put(CommandName.GO_TO_LOGINATION_PAGE, new GoToLoginationPageCommand());
		commands.put(CommandName.GO_TO_WELCOME_PAGE, new GoToWelcomePageCommand());
		commands.put(CommandName.GO_TO_ROOMS_PAGE, new GoToRoomsPageCommand());
		commands.put(CommandName.GO_TO_MY_ACCOUNT_PAGE, new GoToMyAccountPageCommand());
		commands.put(CommandName.GO_TO_BOOKINGS_PAGE, new GoToBookingsPageCommand());
		commands.put(CommandName.GO_TO_STAYS_PAGE, new GoToStaysPageCommand());
		commands.put(CommandName.GO_TO_CONTACTS_PAGE, new GoToContactsPageCommand());
		commands.put(CommandName.GO_TO_CLIENTS_PAGE, new GoToClientsPageCommand());
		commands.put(CommandName.GO_TO_ADMINS_PAGE, new GoToAdminsPageCommand());
	}

	public final Command getCommand(String commandName) {
		Command command = commands.get(commandName);
		return command;
	}
}
