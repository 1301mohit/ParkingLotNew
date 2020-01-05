package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {

    Integer capacity;
    List<ParkingLot> listOfParkingLots = new ArrayList<>();
    ParkingLotStrategy strategy;

    public ParkingLotSystem(int capacity, List<ParkingLot> listOfParkingLots){
        this.capacity = capacity;
        this.listOfParkingLots.addAll(listOfParkingLots);
    }

    public Integer getNumberOfParkingLots() {
        return this.listOfParkingLots.size();
    }

    public Boolean parkVehicle(Vehicle vehicle, DriverType typeOfDriver) throws ParkingLotException {
        strategy = FactoryForStrategy.getStrategyObject(typeOfDriver, vehicle.vehicleType);
        return strategy.parkVehicle(vehicle, typeOfDriver, this.listOfParkingLots);
    }

    public Boolean unParkVehicle(Object vehicle) throws ParkingLotException {
        for(int i=0; i<this.capacity; i++){
            Boolean check = listOfParkingLots.get(i).unParkVehicle(vehicle);
            if(check)
                return true;
        }
        throw new ParkingLotException("Vehicle is not Present", ParkingLotException.ExceptionType.NOT_PRESENT);
    }

    public Boolean isEvenlyDistributed(){
        List<Integer> listOfCount_Cars = new ArrayList<>();
        listOfCount_Cars.addAll(strategy.getListOfSizeOfOccupiedVehicleParkingLot(listOfParkingLots));
        Integer value = listOfCount_Cars.get(0);
        for(int i=1; i<listOfCount_Cars.size(); i++){
            if(value != listOfCount_Cars.get(i))
                return false;
        }
        return true;
    }

    public Boolean parkVehicle(Vehicle vehicle) throws ParkingLotException {
        return parkVehicle(vehicle,DriverType.NORMAL);
    }

    public List<List<Integer>> getListofColoredVechicles(Vehicle.ColorType color) {
        List<List<Integer>> list_position = new ArrayList<>();
        for(int index=0; index < listOfParkingLots.size();index++){
            list_position.add(listOfParkingLots.get(index).getListofColoredVechicles(color));
        }
        return list_position;
    }

}
