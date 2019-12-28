package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {

    private final int capacity;
    List<Object> listOfVehicle = new ArrayList<>();
    List<ParkingLotInformation> listOfObserver = new ArrayList<>();

    public ParkingLotSystem(int capacity) {
        this.capacity = capacity;
    }

    public Boolean parkVehicle(Object vehicle) throws ParkingLotException {
        if(isParkFull()) {
            for (ParkingLotInformation observer: listOfObserver
                 ) {
                observer.inform(true);
            }
            throw new ParkingLotException("Parking is full");
        }
        listOfVehicle.add(vehicle);
        return true;
    }

    public Boolean unParkVehicle(Object vehicle) throws ParkingLotException {
        if(listOfVehicle.size() == 0)
            throw new ParkingLotException("Parking is Empty");
        if(listOfVehicle.contains(vehicle)){
            if(isParkFull()){
                for (ParkingLotInformation observer: listOfObserver
                ) {
                    observer.inform(false);
                }
            }
            listOfVehicle.remove(vehicle);
            return true;
        }
        return false;
    }

    public Boolean isParkFull(){
        if(this.listOfVehicle.size() == this.capacity)
            return true;
        return false;
    }

    public void generateOwner(List<ParkingLotInformation> listOfObserver) {
        for (ParkingLotInformation observer:listOfObserver
             ) {
            this.listOfObserver.add(observer);
        }
    }

}
