package by.epam.tc.web.entity.room;

import java.io.Serializable;
import java.util.Objects;

public class Image implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private int imgId;
    private String imgPath;
    private int roomNumber;

    public Image(){}

    public Image(String imgPath, int roomNumber) {
        this.imgPath = imgPath;
        this.roomNumber = roomNumber;
    }

    public Image(int imgId, String imgPath, int roomNumber) {
        this.imgId = imgId;
        this.imgPath = imgPath;
        this.roomNumber = roomNumber;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return imgId == image.imgId && roomNumber == image.roomNumber && Objects.equals(imgPath, image.imgPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imgId, imgPath, roomNumber);
    }

    @Override
    public String toString() {
        return "Image{" +
                "imgId=" + imgId +
                ", imgPath='" + imgPath + '\'' +
                ", roomNumber=" + roomNumber +
                '}';
    }
}
