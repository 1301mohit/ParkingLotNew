package parkinglot;

public class ParkingAttendent{

    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    public void parkVehicle(int position, Object vehicle) throws ParkingLotException {
        parkingLotSystem.parkVehicle( vehicle);
    }

}
