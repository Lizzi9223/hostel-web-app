package by.epam.tc.web.entity.stay;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Booking implements Serializable {
    private int id;
    private int userId;
    private int roomNumber;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int guestsCount;
    private Boolean isApproved;
    private LocalDate approveDate;
    private boolean isPaid;

    public Booking(){}

    public Booking(int userId, int roomNumber, LocalDate fromDate, LocalDate toDate,
                   int guestsCount, Boolean isApproved, LocalDate approveDate, boolean isPaid) {
        this.userId = userId;
        this.roomNumber = roomNumber;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.guestsCount = guestsCount;
        this.isApproved = isApproved;
        this.approveDate = approveDate;
        this.isPaid = isPaid;
    }

    public Booking(int id, int userId, int roomNumber, LocalDate fromDate, LocalDate toDate,
                   int guestsCount, Boolean isApproved, LocalDate approveDate, boolean isPaid) {
        this.id = id;
        this.userId = userId;
        this.roomNumber = roomNumber;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.guestsCount = guestsCount;
        this.isApproved = isApproved;
        this.approveDate = approveDate;
        this.isPaid = isPaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getGuestsCount() {
        return guestsCount;
    }

    public void setGuestsCount(int guestsCount) {
        this.guestsCount = guestsCount;
    }

    public Boolean isApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public LocalDate getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(LocalDate approveDate) {
        this.approveDate = approveDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
	public int hashCode() {
		return Objects.hash(approveDate, fromDate, guestsCount, id, isApproved, isPaid, roomNumber, toDate, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		return Objects.equals(approveDate, other.approveDate) && Objects.equals(fromDate, other.fromDate)
				&& guestsCount == other.guestsCount && id == other.id && Objects.equals(isApproved, other.isApproved)
				&& isPaid == other.isPaid && roomNumber == other.roomNumber && Objects.equals(toDate, other.toDate)
				&& userId == other.userId;
	}

	@Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", userId=" + userId +
                ", roomNumber=" + roomNumber +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", guestsCount=" + guestsCount +
                ", isApproved=" + isApproved +
                ", approveDate=" + approveDate +
                ", isPaid=" + isPaid +
                '}';
    }
}
