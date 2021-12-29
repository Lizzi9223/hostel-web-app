package by.epam.tc.web.entity.room;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Room implements Serializable {
    private int roomNumber;
    private int cost;
    private int capacity;
    private String gender;
    private boolean isBathroomInRoom;
    private String notes;
    private List<Image> images;

    public Room(){}

    public Room(int roomNumber, int cost, int capacity, String gender, boolean isBathroomInRoom, String notes) {
        this.roomNumber = roomNumber;
        this.cost = cost;
        this.capacity = capacity;
        this.gender = gender;
        this.isBathroomInRoom = isBathroomInRoom;
        this.notes = notes;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isBathroomInRoom() {
        return isBathroomInRoom;
    }

    public void setBathroomInRoom(boolean bathroomInRoom) {
        isBathroomInRoom = bathroomInRoom;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }  

    public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return capacity == other.capacity && cost == other.cost && Objects.equals(gender, other.gender)
				&& Objects.equals(images, other.images) && isBathroomInRoom == other.isBathroomInRoom
				&& Objects.equals(notes, other.notes) && roomNumber == other.roomNumber;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(capacity, cost, gender, images, isBathroomInRoom, notes, roomNumber);
	}

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", cost=" + cost + ", capacity=" + capacity + ", gender=" + gender
				+ ", isBathroomInRoom=" + isBathroomInRoom + ", notes=" + notes + ", images=" + images + "]";
	}
}
