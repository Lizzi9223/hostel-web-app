package by.epam.tc.web.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.tc.web.entity.user.Client;

public class UserValidator {
	
	private UserValidator() {}
	
	public static boolean isValidClient(Client client) {
		if(!isValidLogin(client.getLogin()) 
				|| !isValidPassword(client.getPassword())
				|| !isValidName(client.getFirstName())
				|| !isValidSurname(client.getLastName())
				|| !isValidPhone(client.getPhoneNumber())
				|| !isValidEmail(client.getEmail())) {
			return false;
		}
		return true;
	}
	
	public static boolean isValidLogin(String login) {
		if(login.equals("") || login == null) {
			return false;
		}
		String pattern = "^[a-zA-Z0-9_]{4,}$";
        return isValid(login, pattern);
	}
	
	public static boolean isValidPassword(String password) {
		if(password.equals("") || password == null) {
			return false;
		}
		String pattern = "^[a-zA-Z0-9_]{4,}$";
        return isValid(password, pattern);
	}
	
	public static boolean isValidName(String name) {
		if(name.equals("") || name == null) {
			return false;
		}
		String pattern = "^[a-zA-Z]{2,}$";
        return isValid(name, pattern);
	}
	
	public static boolean isValidSurname(String surname) {
		if(surname.equals("") || surname == null) {
			return false;
		}
		String pattern = "^[a-zA-Z]{2,}$";
        return isValid(surname, pattern);
	}
	
	public boolean isValidCountry(String country) {
		String pattern = "^[a-zA-Z]{3,}$";
        return isValid(country, pattern);
	}

	public static boolean isValidPhone(String phone) {
		if(phone.equals("") || phone == null) {
			return false;
		}
		String pattern = "^[1-9]+[0-9]{4,}$";
        return isValid(phone, pattern);
	}

	public static boolean isValidEmail(String email) {
		if(email.equals("") || email == null) {
			return false;
		}
		String pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return isValid(email, pattern);
	}
	
	private static boolean isValid(String data, String patternString) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
	}
}
