package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {

    Integer capacity;
    List<ParkingLot> listOfParkingLots = new ArrayList<>();
    ParkingLotStrategy strategy;

    public ParkingLotSystem(int capacity, List<Integer> listOfParkingLotCapacity){
        this.capacity = capacity;
        for(int i=0; i<this.capacity; i++)
            listOfParkingLots.add(new ParkingLot(listOfParkingLotCapacity.get(i)));
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

}
