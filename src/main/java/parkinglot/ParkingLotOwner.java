package parkinglot;

public class ParkingLotOwner {


    private Boolean isFull = false;

    public Boolean inform(Boolean information) {
        isFull = information;
        return isFull;
    }

}
