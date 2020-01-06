package parkinglot;

import java.util.ArrayList;
import java.util.List;

public abstract class ParkingLotStrategy {

    public abstract Boolean parkVehicle(Vehicle vehicle, DriverType typeOfDriver, List<ParkingLot> listOfParkingLots, String attendant) throws ParkingLotException;

    public List<Integer> getListOfSizeOfOccupiedVehicleParkingLot(List<ParkingLot> listOfParkingLots) {
        List<Integer> listOfSizeOfOccupiedVehicleParkingLot = new ArrayList<>();
        for(int i=0; i<listOfParkingLots.size(); i++){
            Integer sizeOfOccupiedVehicle = (int)listOfParkingLots.get(i).vehicleMap.values().stream().filter(v -> v.getVehicle() != null).count();
            listOfSizeOfOccupiedVehicleParkingLot.add(i, sizeOfOccupiedVehicle);
        }
        return listOfSizeOfOccupiedVehicleParkingLot;
    }

}
