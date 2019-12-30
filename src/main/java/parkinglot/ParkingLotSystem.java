package parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotSystem {

    private Integer capacity;
    Map<Integer, Object> vehicleMap = new HashMap<>();
    List<ParkingLotInformation> listOfObserver = new ArrayList<>();
    List listOfPosition = new ArrayList();
    Integer position;
    private ParkingLotOwner owner;

    public ParkingLotSystem(int capacity) {
        this.capacity = capacity;
        for(int i=1; i<this.capacity; i++) {
            vehicleMap.put(i,null);
        }
    }

    public ParkingLotSystem() {}

    public Boolean parkVehicle(Object vehicle) throws ParkingLotException {
        position = getPositionFromOwner();
        if(isParkFull()) {
            for (ParkingLotInformation observer: listOfObserver
                 ) {
                observer.inform(true);
            }
            throw new ParkingLotException("Parking is full");
        }
        vehicleMap.replace(position, null, vehicle);
        return true;
    }

    private Integer getPositionFromOwner() {
        for (Integer key : vehicleMap.keySet()
        ) {
            if (vehicleMap.get(null) == null)
                listOfPosition.add(key);
        }
        position = this.owner.nullPositionList(listOfPosition);
        return position;
    }

    public Boolean unParkVehicle(Object vehicle) throws ParkingLotException {
        Integer vehicle_Key = null;
        if(vehicleMap.size() == 0)
            throw new ParkingLotException("Parking is Empty");
        for (Integer key: vehicleMap.keySet()) {
            if ( vehicleMap.get(key).equals(vehicle)){
                vehicle_Key = key;
            }
        }
        if( vehicle_Key == null) throw new ParkingLotException("Vehicle is not present");
        if(isParkFull()){
            for (ParkingLotInformation observer: listOfObserver) {
                observer.inform(false);
            }
        }
        vehicleMap.replace(vehicle_Key, vehicle, null);
        return true;
    }

    public Boolean isParkFull(){
        if(!this.vehicleMap.values().contains(null))
            return true;
        return false;
    }

    public void generateOwner(List<ParkingLotInformation> listOfObserver) {
        for (ParkingLotInformation observer:listOfObserver
             ) {
            this.listOfObserver.add(observer);
        }
        this.owner = (ParkingLotOwner) listOfObserver.get(0);

    }

}
