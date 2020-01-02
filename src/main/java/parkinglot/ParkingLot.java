package parkinglot;

import java.util.*;

public class ParkingLot {

    private Integer capacity;
    Map<Integer, ParkingSlot> vehicleMap = new HashMap<>();
    List<ParkingLotInformation> listOfObserver = new ArrayList<>();
    List<Integer> listOfUnoccupiedPosition = new ArrayList();
    Date date;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        for (int i = 1; i <= this.capacity; i++) {
            vehicleMap.put(i, new ParkingSlot());
        }
    }

    public Boolean parkVehicle(Object vehicle, Integer position) throws ParkingLotException {
        if (isParkFull()) {
            for (ParkingLotInformation observer : listOfObserver
            ) {
                observer.inform(true);
            }
            throw new ParkingLotException("Parking is full");
        }
        this.date = new Date();
        vehicleMap.get(position).setVehicle(vehicle);
        vehicleMap.get(position).setDateAndTime(this.date);
        return true;
    }

    public List<Integer> getListOfUnoccupiedPosition() {
        for (int key : vehicleMap.keySet()) {
            if (vehicleMap.get(key).getVehicle() == null)
                listOfUnoccupiedPosition.add(key);
        }
        return listOfUnoccupiedPosition;
    }

    public Boolean unParkVehicle(Object vehicle) throws ParkingLotException {
        Integer vehiclePosition = null;
        if (vehicleMap.values().stream().filter(v -> v.getVehicle() == null).count() == vehicleMap.size())
            throw new ParkingLotException("Parking is Empty");
        vehiclePosition = findPosition(vehicle);
        if (isParkFull()) {
            for (ParkingLotInformation observer : listOfObserver) {
                observer.inform(false);
            }
        }
        vehicleMap.get(vehiclePosition).setVehicle(null);
        vehicleMap.get(vehiclePosition).setDateAndTime(null);
        return vehicleMap.replace(vehiclePosition, vehicleMap.get(vehiclePosition), vehicleMap.get(vehiclePosition));
    }

    public Boolean isParkFull() {
        if (this.vehicleMap.values().stream().filter(p -> p.getVehicle() != null).count() == this.vehicleMap.size())
            return true;
        return false;
    }

    public void generateOwner(List<ParkingLotInformation> listOfObserver) {
        for (ParkingLotInformation observer : listOfObserver
        ) {
            this.listOfObserver.add(observer);
        }
    }

    public Date getDate(Object vehicle) throws ParkingLotException {
        Integer vehiclePosition = findPosition(vehicle);
        this.date = this.vehicleMap.get(vehiclePosition).getDateAndTime();
        return this.date;
    }

    public Integer findPosition(Object vehicle) throws ParkingLotException {
        try{
            return this.vehicleMap
                    .entrySet()
                    .stream()
                    .filter(v -> v.getValue().getVehicle() == vehicle)
                    .map(Map.Entry::getKey).findFirst().get();
        }
        catch(NoSuchElementException e){
            throw new ParkingLotException("Vehicle is not present");
        }
    }

}
