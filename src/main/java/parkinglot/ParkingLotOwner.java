package parkinglot;

import java.util.List;
import java.util.Random;

public class ParkingLotOwner extends ParkingLotInformation{

    ParkingAttendent parkingAttendent = new ParkingAttendent();

    public void park(Integer position, Object vehicle) throws ParkingLotException {
        parkingAttendent.parkVehicle(position, vehicle);
    }

//    public Integer getPositionToPark(List<Integer> listOfPosition) {
//        Random random = new Random();
//        int index = random.nextInt(listOfPosition.size());
//        return listOfPosition.get(index);
//    }

}
