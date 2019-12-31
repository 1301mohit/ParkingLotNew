package parkinglot;

import java.util.*;

public class ParkingLotSystem {

    private Integer capacity;
    Map<Integer, Object> vehicleMap = new HashMap<>();
    Map<Integer, Date> timeOfVehicleMap = new HashMap<>();
    List<ParkingLotInformation> listOfObserver = new ArrayList<>();
    List<Integer> listOfPosition = new ArrayList();
    Date date;
    Integer position;
    public ParkingLotOwner owner;

    public ParkingLotSystem(int capacity) {
        this.capacity = capacity;
        for (int i = 1; i <= this.capacity; i++) {
            vehicleMap.put(i, null);
            timeOfVehicleMap.put(i, new Date());
        }
    }

    public ParkingLotSystem() {
    }

    public Boolean parkVehicle(Object vehicle) throws ParkingLotException {
        position = getPosition();
        if (isParkFull()) {
            for (ParkingLotInformation observer : listOfObserver
            ) {
                observer.inform(true);
            }
            throw new ParkingLotException("Parking is full");
        }
        vehicleMap.replace(position, null, vehicle);
        this.date = new Date();
        timeOfVehicleMap.replace(position, null, date);
        return true;
    }

    public Integer getPosition() {
        for (int key : vehicleMap.keySet()
        ) {
            if (vehicleMap.get(null) == null)
                listOfPosition.add(key);
        }
        Random random = new Random();
        int index = random.nextInt(listOfPosition.size());
        position = listOfPosition.get(index);
        return position;
    }

    public Boolean unParkVehicle(Object vehicle) throws ParkingLotException {
        Integer vehicle_Key = null;
        if (vehicleMap.values().stream().filter(v -> v == null).count() == vehicleMap.size())
            throw new ParkingLotException("Parking is Empty");
        for (Integer key : vehicleMap.keySet()) {
            if (vehicleMap.get(key) == vehicle) {
                vehicle_Key = key;
            }
        }
        if (vehicle_Key == null) throw new ParkingLotException("Vehicle is not present");
        if (isParkFull()) {
            for (ParkingLotInformation observer : listOfObserver) {
                observer.inform(false);
            }
        }
        vehicleMap.replace(vehicle_Key, vehicle, null);
        timeOfVehicleMap.replace(vehicle_Key, null);
        return true;
    }

    public Boolean isParkFull() {
        if (!this.vehicleMap.values().contains(null))
            return true;
        return false;
    }

    public void generateOwner(List<ParkingLotInformation> listOfObserver) {
        for (ParkingLotInformation observer : listOfObserver
        ) {
            this.listOfObserver.add(observer);
        }
    }

    public Date getDate(Object vehicle) {
        Integer key = findPosition(vehicle);
        this.date = this.timeOfVehicleMap.get(key);
        return this.date;
    }

    public Integer findPosition(Object vehicle) {
        return this.vehicleMap.entrySet().stream().filter(v -> v.getValue() == vehicle).map(Map.Entry::getKey).findFirst().get();
    }

}
