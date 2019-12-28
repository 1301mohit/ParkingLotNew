package parkinglot;

public abstract class ParkingLotInformation {

    public Boolean isFull = false;

    public void inform(Boolean information) {
        isFull = information;
    }

}
