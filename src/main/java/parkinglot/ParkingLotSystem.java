package parkinglot;

import com.predicates.SlotPredicates;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<List<Integer>> getListofColoredVechicles(Vehicle.ColorType color) {
        List<List<Integer>> list_position = new ArrayList<>();
        for (int index = 0; index < listOfParkingLots.size(); index++) {
            list_position.add(SlotPredicates.findColorCar(color,
                    listOfParkingLots.get(index).vehicleMap.values().stream().collect(Collectors.toList())));
        }
        return list_position;
    }

    public List<List<ParkingSlot>> getListofSlots(String carname, Vehicle.ColorType color) {
        List<List<ParkingSlot>> parkingSlots = new ArrayList<>();
        for (int index = 0; index < listOfParkingLots.size(); index++) {
             parkingSlots.add(SlotPredicates.giveListofSlotWhenGivenColorAndName(carname, color,
                    listOfParkingLots.get(index).vehicleMap.values().stream().collect(Collectors.toList())));
        }
        return parkingSlots;
    }
    

    public List<List<Integer>> getListofCarWithName(String carname) {
        List<List<Integer>> list_position = new ArrayList<>();
        for (int index = 0; index < listOfParkingLots.size(); index++) {
            list_position.add(SlotPredicates.findCarByName(carname,
                    listOfParkingLots.get(index).vehicleMap.values().stream().collect(Collectors.toList())));
        }
        return list_position;
    }

    public List<List<Integer>> getListofCarWithTime(long time) {
        List<List<Integer>> list_position = new ArrayList<>();
        for (int index = 0; index < listOfParkingLots.size(); index++) {
            list_position.add(SlotPredicates.findListOfPositionByTime(time,
                    listOfParkingLots.get(index).vehicleMap.values().stream().collect(Collectors.toList())));
        }
        return list_position;
    }

}
