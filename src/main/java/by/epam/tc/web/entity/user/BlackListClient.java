package by.epam.tc.web.entity.user;

import java.time.LocalDate;
import java.util.Objects;

public class BlackListClient extends Client {
	private static final long serialVersionUID = 1L;
	
    private String reason;
    private LocalDate sinceDate;

    public BlackListClient() {}

    public BlackListClient(String reason, LocalDate sinceDate) {
        this.reason = reason;
        this.sinceDate = sinceDate;
    }

    public BlackListClient(int id, String reason, LocalDate sinceDate) {
        this.setClientId(id);
        this.reason = reason;
        this.sinceDate = sinceDate;
    }

    public BlackListClient(int clientId, String reason, LocalDate sinceDate, String firstName, String lastName,
                           String passportId, LocalDate birthDate, String country, String phoneNumber, String email) {
        super(clientId, firstName, lastName, passportId, birthDate, country, phoneNumber, email);
        this.reason = reason;
        this.sinceDate = sinceDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getSinceDate() {
        return sinceDate;
    }

    public void setSinceDate(LocalDate sinceDate) {
        this.sinceDate = sinceDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackListClient that = (BlackListClient) o;
        return this.getClientId() == that.getClientId() && Objects.equals(reason, that.reason) && Objects.equals(sinceDate, that.sinceDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClientId(), reason, sinceDate);
    }

    @Override
    public String toString() {
        return "BlackListClient{" +
                "id=" + getClientId() +
                ", reason='" + reason + '\'' +
                ", sinceDate=" + sinceDate +
                '}';
    }
}
