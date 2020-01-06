package parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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

    public Boolean parkVehicle(Vehicle vehicle, DriverType typeOfDriver, String attendant) throws ParkingLotException {
        strategy = FactoryForStrategy.getStrategyObject(typeOfDriver, vehicle.vehicleType);
        return strategy.parkVehicle(vehicle, typeOfDriver, this.listOfParkingLots, attendant);
    }

    public Boolean unParkVehicle(Vehicle vehicle) throws ParkingLotException {
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
        return listOfCount_Cars.stream().filter(integer -> integer == value).findAny().isPresent();
    }

    public Boolean parkVehicle(Vehicle vehicle) throws ParkingLotException {
        return parkVehicle(vehicle, DriverType.NORMAL, "PQR");
    }

    public Boolean parkVehicle(Vehicle vehicle, DriverType type) throws ParkingLotException {
        return parkVehicle(vehicle, type, "PQR");
    }

    public Boolean parkVehicle(Vehicle vehicle, String parkingAttendant) throws ParkingLotException {
        return parkVehicle(vehicle,DriverType.NORMAL, parkingAttendant);
    }

    public List<List<ParkingSlot>> getListOfParticularColoredAndTypeOfVehicle(Optional<Vehicle.ColorType> color, Optional<String> vehicleName) {
        List<List<ParkingSlot>> listPosition = new ArrayList<>();
        for(int index = 0; index < listOfParkingLots.size(); index++){
            listPosition.add(listOfParkingLots.get(index).getListOfColoredAndTypeVehicles(color, vehicleName));
        }
        return listPosition;
    }

    public List<List<ParkingSlot>> getListOfVehicleParkInLast30Min(long time) {
        List<List<ParkingSlot>> listPosition = new ArrayList<>();
        listOfParkingLots.stream()
                        .forEach(parkingLot -> IntStream.range(0, listOfParkingLots.size())
                        .forEach(i -> listPosition.add(listOfParkingLots.get(i).getListOfVehicleParkInLast30Min(time))));
        return listPosition;
    }

}
