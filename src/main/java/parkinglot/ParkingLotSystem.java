package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {

    private final int capacity;
    private ParkingLotOwner owner;
    List<Object> listOfVehicle = new ArrayList<>();

    public ParkingLotSystem(int capacity) {
        this.capacity = capacity;
    }

    public Boolean parkVehicle(Object vehicle) throws ParkingLotException {
        if(isParkFull())
            throw new ParkingLotException("Parking is full");
        listOfVehicle.add(vehicle);
        return true;
    }

    public Boolean unParkVehicle(Object vehicle) throws ParkingLotException {
        if(listOfVehicle.size() == 0)
            throw new ParkingLotException("Parking is Empty");
        if(listOfVehicle.contains(vehicle)){
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

    public Boolean informOwner(ParkingLotOwner owner) {
        this.owner = owner;
        return owner.inform(isParkFull());
    }

}
