package parkinglot;

import java.util.List;

public class StrategyForHandicap extends ParkingLotStrategy{

    public Boolean parkVehicle(Vehicle vehicle, DriverType typeOfDriver, List<ParkingLot> listOfParkingLots) throws ParkingLotException {
        try{
            int index = 0;
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
