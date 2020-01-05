package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParkingLotTest {

    ParkingLot parkingLot;
    Vehicle vehicle;
    Vehicle vehicle1;
    Vehicle vehicle2;
    List<ParkingLotInformation> listOfObserver;

    @Before
    public void setUp() {
        listOfObserver = new ArrayList<>();
        listOfObserver.add(new ParkingLotOwner());
        listOfObserver.add(new AirportSecurity());
        parkingLot = new ParkingLot(2);
        vehicle = new Vehicle();
        vehicle1 = new Vehicle();
        vehicle2 = new Vehicle();
        parkingLot.generateOwner(listOfObserver);
        parkingLot.getListOfUnoccupiedPosition();
    }

    @Test
    public void parkVehicle_ShouldReturnTrue() {
        try {
            Boolean isPark = parkingLot.parkVehicle(vehicle, 1);
            Boolean isPark1 = parkingLot.parkVehicle(vehicle1, 2);
            Assert.assertTrue(isPark && isPark1);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parkMoreThanCapacity_ShouldReturnFalse() {
        try {
            parkingLot.parkVehicle(vehicle, 1);
            parkingLot.parkVehicle(vehicle1, 2);
            Boolean isPark3 = parkingLot.parkVehicle(vehicle2, 3);
            Assert.assertFalse(isPark3);
        } catch (ParkingLotException e) { }
    }

    @Test
    public void unParkVehicle_ShouldReturnTrue() {
        try {
            parkingLot.parkVehicle(vehicle1, 1);
            Boolean isUnPark = parkingLot.unParkVehicle(vehicle1);
            parkingLot.parkVehicle(vehicle2,1);
            Assert.assertEquals(true, isUnPark);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void unParkVehicle_VehicleIsNotPresent_ShouldReturnFalse() {
        try {
            parkingLot.parkVehicle(vehicle1, 1);
            Boolean isUnPark = parkingLot.unParkVehicle(vehicle);
            Assert.assertFalse(isUnPark);
        } catch (ParkingLotException e) { }
    }

    @Test
    public void informObserver_IsFull_ReturnException() {
        try {
            parkingLot.parkVehicle(vehicle, 1);
            parkingLot.parkVehicle(vehicle1, 2);
            parkingLot.parkVehicle(vehicle2, 3);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.FULL, e.type);
        }
    }

    @Test
    public void WhenFull_UnparkVehicle_InformObserver_IsNotFull() {
        try {
            parkingLot.generateOwner(listOfObserver);
            parkingLot.parkVehicle(vehicle, 1);
            parkingLot.parkVehicle(vehicle1, 2);
            parkingLot.unParkVehicle(vehicle);
            Assert.assertFalse(listOfObserver.get(0).isFull && listOfObserver.get(1).isFull);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void parkVehicleAtPosition_ReturnPositionSame() {
        try {
            parkingLot.parkVehicle(vehicle, 1);
            int position = parkingLot.findPosition(vehicle);
            Assert.assertEquals(1, position);
        } catch (ParkingLotException e) { }
    }

    @Test
    public void parkedVehicleDate_ReturnDateSame() {
        try {
            parkingLot.parkVehicle(vehicle, 1);
            Date date = parkingLot.getDate(vehicle);
            Assert.assertEquals(parkingLot.date, date);
        } catch (ParkingLotException e) { }
    }

}

