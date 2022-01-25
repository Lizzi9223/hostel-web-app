package by.epam.tc.web.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.Client;

public class UserValidator {
	
	private UserValidator() {}
	
	public static boolean isValidClient(Client client) {
		if(!isValidLogin(client.getLogin()) 
				|| !isValidName(client.getFirstName())
				|| !isValidSurname(client.getLastName())
				|| !isValidPassportId(client.getPassportId())
				|| !isValidCountry(client.getCountry())
				|| !isValidPhone(client.getPhoneNumber())
				|| !isValidEmail(client.getEmail())) {
			return false;
		}
		return true;
	}
	
	public static boolean isValidAdmin(Admin admin) {
		if(!isValidLogin(admin.getLogin()) 
				|| !isValidName(admin.getName())) {
			return false;
		}
		return true;
	}
	
	public static boolean isValidLogin(String login) {
		if(login.equals("") || login == null) {
			return false;
		}
		String pattern = "^[a-zA-ZЁёА-я0-9_.]{5,15}$";
        return isValid(login, pattern);
	}
	
	public static boolean isValidPassword(String password) {
		if(password.equals("") || password == null) {
			return false;
		}
		String pattern = "^[a-zA-Z0-9_]{5,15}$";
        return isValid(password, pattern);
	}
	
	public static boolean isValidName(String name) {
		if(name.equals("") || name == null) {
			return false;
		}
		String pattern = "^[a-zA-ZЁёА-я]{2,15}$";
        return isValid(name, pattern);
	}
	
	public static boolean isValidSurname(String surname) {
		if(surname.equals("") || surname == null) {
			return false;
		}
		String pattern = "^[a-zA-ZЁёА-я]{2,15}$";
        return isValid(surname, pattern);
	}
	
	public static boolean isValidPassportId(String passportId) {
		if(passportId.equals("") || passportId == null) {
			return false;
		}
		String pattern = "^[a-zA-Z0-9]{5,20}$";
        return isValid(passportId, pattern);
	}
	
	public static boolean isValidCountry(String country) {
		if(country.equals("") || country == null) {
			return true;
		}
		String pattern = "^[a-zA-ZЁёА-я]{3,20}$";
        return isValid(country, pattern);
	}

	public static boolean isValidPhone(String phone) {
		if(phone.equals("") || phone == null) {
			return false;
		}
		String pattern = "^\\+?[1-9]?[0-9]{4,15}$";
        return isValid(phone, pattern);
	}

	public static boolean isValidEmail(String email) {
		if(email.equals("") || email == null) {
			return false;
		}
		String pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w]+$";
        return isValid(email, pattern);
	}
	
	private static boolean isValid(String data, String patternString) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
	}
}
