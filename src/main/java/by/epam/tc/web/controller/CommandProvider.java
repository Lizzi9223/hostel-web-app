package by.epam.tc.web.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.tc.web.controller.impl.LoginationCommand;
import by.epam.tc.web.controller.impl.RegistrationCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToHomePageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToLoginationPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToRegistrationPageCommand;
import by.epam.tc.web.controller.impl.gotopage.GoToWelcomePageCommand;

public final class CommandProvider {

	private final Map<String, Command> commands = new HashMap<String, Command>();
	
	public CommandProvider() {
		commands.put("Logination", new LoginationCommand());
		commands.put("Registration", new RegistrationCommand());
		commands.put("GO_TO_REGISTRATION_PAGE", new GoToRegistrationPageCommand());
		commands.put("GO_TO_LOGINATION_PAGE", new GoToLoginationPageCommand());
		commands.put("GO_TO_WELCOME_PAGE", new GoToWelcomePageCommand());
		commands.put("GO_TO_HOME_PAGE", new GoToHomePageCommand());
	}
	
	public final Command getCommand(String commandName) {
		Command command = commands.get(commandName);
		return command;
	}
}
