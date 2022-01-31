package by.epam.tc.web.entity.stay;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Stay implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int clientId;
	private int roomNumber;
	private LocalDate fromDate;
	private LocalDate toDate;
	private String notes;

	public Stay() {
	}

	public Stay(int clientId, int roomNumber, LocalDate fromDate, LocalDate toDate, String notes) {
		this.clientId = clientId;
		this.roomNumber = roomNumber;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.notes = notes;
	}

	public Stay(int id, int clientId, int roomNumber, LocalDate fromDate, LocalDate toDate, String notes) {
		this.id = id;
		this.clientId = clientId;
		this.roomNumber = roomNumber;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.notes = notes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientId, fromDate, id, notes, roomNumber, toDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stay other = (Stay) obj;
		return clientId == other.clientId && Objects.equals(fromDate, other.fromDate) && id == other.id
				&& Objects.equals(notes, other.notes) && roomNumber == other.roomNumber
				&& Objects.equals(toDate, other.toDate);
	}

	@Override
	public String toString() {
		return "Stay{" + "id=" + id + ", clientId=" + clientId + ", roomNumber=" + roomNumber + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", notes='" + notes + '\'' + '}';
	}
}
