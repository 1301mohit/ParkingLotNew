package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {

    Integer capacity;
    List<ParkingLot> listOfParkingLots = new ArrayList<>();

    public ParkingLotSystem(int capacity, List<Integer> listOfParkingLotCapacity){
        this.capacity = capacity;
        for(int i=0; i<this.capacity; i++)
            listOfParkingLots.add(new ParkingLot(listOfParkingLotCapacity.get(i)));
    }

    public Integer getNumberOfParkingLots() {
        return this.listOfParkingLots.size();
    }

    public Boolean parkVehicle(Object vehicle, DriverType typeOfDriver) throws ParkingLotException {
        try{
            List<Integer> listOfSizeOfOccupiedVehicleParkingLot = getListOfSizeOfUnoccupiedVehicleParkingLot();
            Integer minimumSize = listOfSizeOfOccupiedVehicleParkingLot.get(0);
            int index = 0;
            if(typeOfDriver == DriverType.NORMAL){
                for(int i=0; i<listOfSizeOfOccupiedVehicleParkingLot.size(); i++){
                    if(minimumSize > listOfSizeOfOccupiedVehicleParkingLot.get(i)){
                        minimumSize = listOfSizeOfOccupiedVehicleParkingLot.get(i);
                        index = i;
                    }
                }
            }
            List<Integer> listOfUnoccupiedVehiclePosition = listOfParkingLots.get(index).getListOfUnoccupiedPosition();
            while(listOfUnoccupiedVehiclePosition.size() == 0 && index < this.capacity){
                index++;
                listOfUnoccupiedVehiclePosition = listOfParkingLots.get(index).getListOfUnoccupiedPosition();
            }
            return listOfParkingLots.get(index).parkVehicle(vehicle,listOfUnoccupiedVehiclePosition.get(0));
        }catch(IndexOutOfBoundsException e){
            throw new ParkingLotException("ParkingLotSystem is full");
        }

    }

    public List<Integer> getListOfSizeOfUnoccupiedVehicleParkingLot() {
        List<Integer> listOfSizeOfOccupiedVehicleParkingLot = new ArrayList<>();
        for(int i=0; i<this.capacity; i++){
            Integer sizeOfOccupiedVehicle = (int)listOfParkingLots.get(i).vehicleMap.values().stream().filter(v -> v.getVehicle() != null).count();
            listOfSizeOfOccupiedVehicleParkingLot.add(i, sizeOfOccupiedVehicle);
        }
        return listOfSizeOfOccupiedVehicleParkingLot;
    }

}
