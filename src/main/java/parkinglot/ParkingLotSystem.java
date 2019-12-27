package parkinglot;

public class ParkingLotSystem {

    private Object vehicle = null;

    public Boolean parkVehicle(Object vehicle) {
        this.vehicle = vehicle;
        return true;
    }

    public Boolean unParkVehicle() {
        this.vehicle = null;
        return true;
    }

}
