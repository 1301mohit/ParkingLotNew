package parkinglot;

import java.util.Date;

public class ParkingSlot {

    private Object vehicle;
    private Date dateAndTime;

    public ParkingSlot() {
        this.vehicle = null;
        this.dateAndTime = null;
    }

    public Vehicle getVehicle() {
        return (Vehicle)vehicle;
    }

    public void setVehicle(Object vehicle) {
        this.vehicle = vehicle;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

}
