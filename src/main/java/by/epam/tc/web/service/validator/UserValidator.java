package by.epam.tc.web.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.tc.web.entity.user.Admin;
import by.epam.tc.web.entity.user.Client;

/**
 * Validates data
 * 
 * @author Lizzi9223
 *
 */
public class UserValidator {

	private UserValidator() {
	}

	/**
	 * Checks if user client is valid
	 * 
	 * @param client client object with data to check
	 * @return true if user client is valid, otherwise false
	 */
	public static boolean isValidUserClient(Client client) {
		if (!isValidLogin(client.getLogin()) || !isValidName(client.getFirstName())
				|| !isValidSurname(client.getLastName()) || !isValidPassportId(client.getPassportId())
				|| !isValidCountry(client.getCountry()) || !isValidPhone(client.getPhoneNumber())
				|| !isValidEmail(client.getEmail())) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if client is valid
	 * 
	 * @param client client object with data to check
	 * @return true if client is valid, otherwise false
	 */
	public static boolean isValidClient(Client client) {
		if (!isValidName(client.getFirstName())
				|| !isValidSurname(client.getLastName()) || !isValidPassportId(client.getPassportId())
				|| !isValidCountry(client.getCountry()) || !isValidPhone(client.getPhoneNumber())
				|| !isValidEmail(client.getEmail())) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if administrator is valid
	 * 
	 * @param admin administrator object with data to check
	 * @return true if administrator is valid, otherwise false
	 */
	public static boolean isValidAdmin(Admin admin) {
		if (!isValidLogin(admin.getLogin()) || !isValidName(admin.getName())) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if login is valid
	 * 
	 * @param login login to check
	 * @return true if login is valid, otherwise false
	 */
	public static boolean isValidLogin(String login) {
		if (login.equals("") || login == null) {
			return false;
		}
		String pattern = "^[a-zA-ZЁёА-я0-9_.]{5,15}$";
		return isValid(login, pattern);
	}

	/**
	 * Checks if password is valid
	 * 
	 * @param password password to check
	 * @return true if password is valid, otherwise false
	 */
	public static boolean isValidPassword(String password) {
		if (password.equals("") || password == null) {
			return false;
		}
		String pattern = "^[a-zA-Z0-9_]{5,15}$";
		return isValid(password, pattern);
	}

	/**
	 * Checks if name is valid
	 * 
	 * @param name name to check
	 * @return true if name is valid, otherwise false
	 */
	public static boolean isValidName(String name) {
		if (name.equals("") || name == null) {
			return false;
		}
		String pattern = "^[a-zA-ZЁёА-я]{2,15}$";
		return isValid(name, pattern);
	}

	/**
	 * Checks if surname is valid
	 * 
	 * @param surname surname to check
	 * @return true if surname is valid, otherwise false
	 */
	public static boolean isValidSurname(String surname) {
		if (surname.equals("") || surname == null) {
			return false;
		}
		String pattern = "^[a-zA-ZЁёА-я]{2,15}$";
		return isValid(surname, pattern);
	}

	/**
	 * Checks if passportId is valid
	 * 
	 * @param passportId passportId to check
	 * @return true if passportId is valid, otherwise false
	 */
	public static boolean isValidPassportId(String passportId) {
		if (passportId.equals("") || passportId == null) {
			return false;
		}
		String pattern = "^[a-zA-Z0-9]{5,20}$";
		return isValid(passportId, pattern);
	}

	/**
	 * Checks if country name is valid
	 * 
	 * @param country country name to check
	 * @return true if country name is valid, otherwise false
	 */
	public static boolean isValidCountry(String country) {
		if (country.equals("") || country == null) {
			return true;
		}
		String pattern = "^[a-zA-ZЁёА-я]{3,20}$";
		return isValid(country, pattern);
	}

	/**
	 * Checks if phone number is valid
	 * 
	 * @param phone phone number to check
	 * @return true if phone number is valid, otherwise false
	 */
	public static boolean isValidPhone(String phone) {
		if (phone.equals("") || phone == null) {
			return false;
		}
		String pattern = "^\\+?[1-9][0-9]{4,15}$";
		return isValid(phone, pattern);
	}

	/**
	 * Checks if email is valid
	 * 
	 * @param email email to check
	 * @return true if email is valid, otherwise false
	 */
	public static boolean isValidEmail(String email) {
		if (email.equals("") || email == null) {
			return false;
		}
		String pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w]+$";
		return isValid(email, pattern);
	}

	/**
	 * Checks if the string with data valid based on received pattern
	 * 
	 * @param data data to check
	 * @param patternString pattern on which check is based
	 * @return true if data is valid, otherwise false
	 */
	private static boolean isValid(String data, String patternString) {
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(data);
		return matcher.matches();
	}
}
