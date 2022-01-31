package by.epam.tc.web.entity.user;

import java.time.LocalDate;
import java.util.Objects;

public class RegularClient extends Client {
	private static final long serialVersionUID = 1L;

	private LocalDate sinceDate;
	private int discount;
	private String note;

	public RegularClient() {
	}

	public RegularClient(LocalDate sinceDate, int discount, String note) {
		this.sinceDate = sinceDate;
		this.discount = discount;
		this.note = note;
	}

	public RegularClient(int id, LocalDate sinceDate, int discount, String note) {
		this.setClientId(id);
		this.sinceDate = sinceDate;
		this.discount = discount;
		this.note = note;
	}

	public RegularClient(int clientId, LocalDate sinceDate, int discount, String note, String firstName,
			String lastName, String passportId, LocalDate birthDate, String country, String phoneNumber, String email) {
		super(clientId, firstName, lastName, passportId, birthDate, country, phoneNumber, email);
		this.sinceDate = sinceDate;
		this.discount = discount;
		this.note = note;
	}

	public LocalDate getSinceDate() {
		return sinceDate;
	}

	public void setSinceDate(LocalDate sinceDate) {
		this.sinceDate = sinceDate;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(discount, note, sinceDate);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegularClient other = (RegularClient) obj;
		return discount == other.discount && Objects.equals(note, other.note)
				&& Objects.equals(sinceDate, other.sinceDate);
	}

	@Override
	public String toString() {
		return "RegularClient{" + "id=" + getClientId() + ", sinceDate=" + sinceDate + ", discount=" + discount
				+ ", note='" + note + '\'' + '}';
	}
}
