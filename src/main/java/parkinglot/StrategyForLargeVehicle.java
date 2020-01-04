package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class StrategyForLargeVehicle extends ParkingLotStrategy{

    public Boolean parkVehicle(Vehicle vehicle, DriverType typeOfDriver, List<ParkingLot> listOfParkingLots) throws ParkingLotException {
        try{
  //          List<Integer> listOfSizeOfOccupiedVehicleParkingLot = super.getListOfSizeOfOccupiedVehicleParkingLot(listOfParkingLots);
//            Integer minimumValue = listOfSizeOfOccupiedVehicleParkingLot.get(0);
            List<Integer> listOfUnoccupiedVehiclePosition = new ArrayList<>();
            int index = 0;
//            for(int i=0; i<listOfSizeOfOccupiedVehicleParkingLot.size(); i++){
//                if(minimumValue > listOfSizeOfOccupiedVehicleParkingLot.get(i)){
//                    minimumValue = listOfSizeOfOccupiedVehicleParkingLot.get(i);
//                    index = i;
//                }
//            }
            while(index < listOfParkingLots.size()){
                listOfUnoccupiedVehiclePosition.add(listOfParkingLots.get(index).getListOfUnoccupiedPosition().size());
                index++;
            }
            Integer maximumValue = listOfUnoccupiedVehiclePosition.get(0);
            index=0;
            for(int i=1; i<listOfUnoccupiedVehiclePosition.size(); i++){

                  if(maximumValue < listOfUnoccupiedVehiclePosition.get(i)){
                      maximumValue = listOfUnoccupiedVehiclePosition.get(i);
                      index = i;
                  }
            }
            listOfUnoccupiedVehiclePosition = listOfParkingLots.get(index).getListOfUnoccupiedPosition();

            return listOfParkingLots.get(index).parkVehicle(vehicle,listOfUnoccupiedVehiclePosition.get(0));
        }catch(IndexOutOfBoundsException e){
            throw new ParkingLotException("ParkingLotSystem is full", ParkingLotException.ExceptionType.FULL);
        }
    }

}
