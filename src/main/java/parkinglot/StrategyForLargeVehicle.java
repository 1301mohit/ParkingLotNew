package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class StrategyForLargeVehicle extends ParkingLotStrategy{

    public Boolean parkVehicle(Vehicle vehicle, DriverType typeOfDriver, List<ParkingLot> listOfParkingLots, String attendant) throws ParkingLotException {
        try{
            List<Integer> listOfUnoccupiedVehiclePosition = new ArrayList<>();
            int index = 0;
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
            return listOfParkingLots.get(index).parkVehicle(vehicle,listOfUnoccupiedVehiclePosition.get(0), attendant);
        }catch(IndexOutOfBoundsException e){
            throw new ParkingLotException("ParkingLotSystem is full", ParkingLotException.ExceptionType.FULL);
        }
    }

}
