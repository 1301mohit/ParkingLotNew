package parkinglot;

import java.util.Date;

public class ParkingSlot {

    private Vehicle vehicle;
    private Date dateAndTime;
    private Integer position;
    private String parkingAttendantName;

    public ParkingSlot() {
        this.vehicle = null;
        this.dateAndTime = null;
        this.position = null;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getParkingAttendantName() {
        return parkingAttendantName;
    }

    public void setParkingAttendantName(String parkingAttendantName) {
        this.parkingAttendantName = parkingAttendantName;
    }

}
