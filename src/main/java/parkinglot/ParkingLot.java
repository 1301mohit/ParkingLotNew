package parkinglot;

import java.util.*;
import java.util.List;
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

    public Boolean parkVehicle(Vehicle vehicle, Integer position, String attendant) throws ParkingLotException {
        if (isParkFull()) {
            listOfObserver.stream().forEach(observer -> observer.inform(true));
            return false;
        }
        this.date = new Date();
        vehicleMap.get(position).setVehicle(vehicle);
        vehicleMap.get(position).setDateAndTime(this.date);
        vehicleMap.get(position).setPosition(position);
        vehicleMap.get(position).setParkingAttendantName(attendant);
        return true;
    }

    public Boolean parkVehicle(Vehicle vehicle, Integer position) throws ParkingLotException {
        return parkVehicle(vehicle, position, "PQR");
    }

    public List<Integer> getListOfUnoccupiedPosition() {
        List<Integer> listOfUnoccupiedPosition = new ArrayList();
        vehicleMap.keySet().stream().forEach(key -> {
            if(vehicleMap.get(key).getVehicle() == null)
                listOfUnoccupiedPosition.add(key);
        });
        return listOfUnoccupiedPosition;
    }

    public Boolean unParkVehicle(Vehicle vehicle) throws ParkingLotException {
        Integer vehiclePosition = findPosition(vehicle);
        if(vehiclePosition == 0)
            return false;
        if (isParkFull())
            listOfObserver.stream().forEach(observer -> observer.inform(false));
        vehicleMap.get(vehiclePosition).setVehicle(null);
        vehicleMap.get(vehiclePosition).setDateAndTime(null);
        vehicleMap.get(vehiclePosition).setPosition(null);
        vehicleMap.get(vehiclePosition).setParkingAttendantName(null);
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

    public List<ParkingSlot> getListOfColoredAndTypeVehicles(Optional<Vehicle.ColorType> color, Optional<String> vehicleName) {
        List<ParkingSlot> listOfSpecifiedColoredAndTypeVehicles = new ArrayList<>();
        Vehicle vehicle;
        for (Integer key : vehicleMap.keySet()
             ) {
            vehicle = vehicleMap.get(key).getVehicle();
            if(vehicle != null && color.isPresent() && vehicleName.isPresent() && vehicle.getVehicleName().equals(vehicleName.get()) && vehicle.getColor().equals(color.get()))
                listOfSpecifiedColoredAndTypeVehicles.add(vehicleMap.get(key));
            else if(vehicle != null && color.isPresent() && !vehicleName.isPresent() && vehicle.getColor().equals(color.get()))
                listOfSpecifiedColoredAndTypeVehicles.add(vehicleMap.get(key));
            else if(vehicle != null && !color.isPresent() && vehicleName.isPresent() && vehicle.getVehicleName().equals(vehicleName.get()))
                listOfSpecifiedColoredAndTypeVehicles.add(vehicleMap.get(key));
        }
        return listOfSpecifiedColoredAndTypeVehicles;
    }

    public List<ParkingSlot> getListOfVehicleParkInLast30Min(long time) {
        Date date = new Date();
        long resultantTime = 0;
        time = time * 6000;
        List<ParkingSlot> listOfVehicleWhichParkWithIn30Min = new ArrayList<>();
        ParkingSlot slot;
        for (Integer key : vehicleMap.keySet()
        ) {
            slot = vehicleMap.get(key);
            if(slot.getDateAndTime() != null ){
                resultantTime = date.getTime() - slot.getDateAndTime().getTime();
                if( resultantTime <= time)
                    listOfVehicleWhichParkWithIn30Min.add(vehicleMap.get(key));
            }
        }
        return listOfVehicleWhichParkWithIn30Min;
    }

}