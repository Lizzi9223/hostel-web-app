package by.epam.tc.web.entity.user;

import java.time.LocalDate;
import java.util.Objects;

public class Client extends User{
    private int clientId;
    private String firstName;
    private String lastName;
    private String passportId;
    private LocalDate birthDate;
    private String country;
    private String phoneNumber;
    private String email;

    public Client(){}

    public Client(String firstName, String lastName, String passportId, LocalDate birthDate,
                  String country, String phoneNumber, String email) {
        this.setRole(Role.CLIENT);
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportId = passportId;
        this.birthDate = birthDate;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Client(int clientId, String firstName, String lastName, String passportId, LocalDate birthDate,
                  String country, String phoneNumber, String email) {
        this.setRole(Role.CLIENT);
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportId = passportId;
        this.birthDate = birthDate;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Client(int userId, String login, int clientId, String firstName, String lastName,
                  String passportId, LocalDate birthDate, String country, String phoneNumber, String email) {
        super(userId,login,Role.CLIENT);
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportId = passportId;
        this.birthDate = birthDate;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    
    public Client(String login, String password, String firstName, String lastName,
            String passportId, LocalDate birthDate, String country, String phoneNumber, String email) {
	  super(login, password, Role.CLIENT);
	  this.firstName = firstName;
	  this.lastName = lastName;
	  this.passportId = passportId;
	  this.birthDate = birthDate;
	  this.country = country;
	  this.phoneNumber = phoneNumber;
	  this.email = email;
	}
    
    public Client(String login, String firstName, String lastName,
            String passportId, LocalDate birthDate, String country, String phoneNumber, String email) {
	  super(login, Role.CLIENT);
	  this.firstName = firstName;
	  this.lastName = lastName;
	  this.passportId = passportId;
	  this.birthDate = birthDate;
	  this.country = country;
	  this.phoneNumber = phoneNumber;
	  this.email = email;
	}
    
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return clientId == client.clientId && Objects.equals(firstName, client.firstName)
                && Objects.equals(lastName, client.lastName) && Objects.equals(passportId, client.passportId)
                && Objects.equals(birthDate, client.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clientId, firstName, lastName, passportId, birthDate);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + clientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportId='" + passportId + '\'' +
                ", birthDate=" + birthDate +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
