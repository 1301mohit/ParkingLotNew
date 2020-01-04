package parkinglot;

import java.util.List;

public class StrategyForNormal extends ParkingLotStrategy{

    public Boolean parkVehicle(Object vehicle, DriverType typeOfDriver, List<ParkingLot> listOfParkingLots) throws ParkingLotException {
        try{
            List<Integer> listOfSizeOfOccupiedVehicleParkingLot = super.getListOfSizeOfOccupiedVehicleParkingLot(listOfParkingLots);
            Integer minimumValue = listOfSizeOfOccupiedVehicleParkingLot.get(0);
            int index = 0;
            for(int i=0; i<listOfSizeOfOccupiedVehicleParkingLot.size(); i++){
                if(minimumValue > listOfSizeOfOccupiedVehicleParkingLot.get(i)){
                    minimumValue = listOfSizeOfOccupiedVehicleParkingLot.get(i);
                    index = i;
                }
            }
            List<Integer> listOfUnoccupiedVehiclePosition = listOfParkingLots.get(index).getListOfUnoccupiedPosition();
            while(listOfUnoccupiedVehiclePosition.size() == 0 && index < listOfParkingLots.size()){
                index++;
                listOfUnoccupiedVehiclePosition = listOfParkingLots.get(index).getListOfUnoccupiedPosition();
            }
            return listOfParkingLots.get(index).parkVehicle(vehicle,listOfUnoccupiedVehiclePosition.get(0));
        }catch(IndexOutOfBoundsException e){
            throw new ParkingLotException("ParkingLotSystem is full", ParkingLotException.ExceptionType.FULL);
        }
    }

}
