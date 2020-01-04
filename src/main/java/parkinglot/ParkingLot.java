package parkinglot;

import java.util.*;
import java.util.stream.IntStream;

public class ParkingLot {

    Integer capacity;
    Map<Integer, ParkingSlot> vehicleMap = new HashMap<>();
    List<ParkingLotInformation> listOfObserver = new ArrayList<>();
    Date date;

    public ParkingLot() {}

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        IntStream.range(1, capacity+1).forEach(position -> vehicleMap.put(position, new ParkingSlot()));
    }

    public Boolean parkVehicle(Object vehicle, Integer position) throws ParkingLotException {
        if (isParkFull()) {
            listOfObserver.stream().forEach(observer -> observer.inform(true));
            return false;
        }
        this.date = new Date();
        vehicleMap.get(position).setVehicle(vehicle);
        vehicleMap.get(position).setDateAndTime(this.date);
        return true;
    }

    public List<Integer> getListOfUnoccupiedPosition() {
        List<Integer> listOfUnoccupiedPosition = new ArrayList();
        vehicleMap.keySet().stream().forEach(key -> {
            if(vehicleMap.get(key).getVehicle() == null)
                listOfUnoccupiedPosition.add(key);
        });
        return listOfUnoccupiedPosition;
    }

    public Boolean unParkVehicle(Object vehicle) throws ParkingLotException {
        Integer vehiclePosition = null;
        vehiclePosition = findPosition(vehicle);
        if(vehiclePosition == 0)
            return false;
        if (isParkFull()) {
            listOfObserver.stream().forEach(observer -> observer.inform(false));
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
        this.listOfObserver.addAll(listOfObserver);
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
            return 0;
        }
    }

}
