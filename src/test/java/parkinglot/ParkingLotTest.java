package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotTest {

    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    Object vehicle1 = null;
    Object vehicle2 = null;
    List<ParkingLotInformation> listOfObserver = null;

    @Before
    public void setUp() {
        listOfObserver = new ArrayList<>();
        listOfObserver.add(new AirportSecurity());
        listOfObserver.add(new ParkingLotOwner());
        parkingLotSystem = new ParkingLotSystem(2);
        vehicle = new Object();
        vehicle1 = new Object();
        vehicle2 = new Object();
    }

    @Test
    public void parkVehicle_ShouldReturnTrue() {
        try {
            Boolean isPark = parkingLotSystem.parkVehicle(vehicle);
            Assert.assertTrue(isPark);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parkMoreThanCapacity_ShouldReturnException() {
        try {
            parkingLotSystem.generateOwner(listOfObserver);
            Boolean isPark1 = parkingLotSystem.parkVehicle(vehicle);
            Boolean isPark2 = parkingLotSystem.parkVehicle(vehicle1);
            Boolean isPark3 = parkingLotSystem.parkVehicle(vehicle2);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking is full", e.getMessage());
        }
    }

    @Test
    public void unParkVehicle_ShouldReturnTrue() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle1);
            Boolean isUnPark = parkingLotSystem.unParkVehicle(vehicle1);
            Assert.assertEquals(true, isUnPark);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void unParkVehicle_WithoutParkAnyVehicle_ShouldReturnFalse() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle1);
            Boolean isUnPark = parkingLotSystem.unParkVehicle(vehicle);
            Assert.assertEquals(true, isUnPark);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void informObserver_IsFull_ReturnException() {
        try{
            parkingLotSystem.generateOwner(listOfObserver);
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
        } catch(ParkingLotException e){
            Assert.assertEquals("Parking is full", e.getMessage());
        }
    }

    @Test
    public void WhenFull_InformObservers_IsFull() {
        try{
            parkingLotSystem.generateOwner(listOfObserver);
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
            Assert.assertTrue(listOfObserver.get(0).isFull && listOfObserver.get(1).isFull);
        } catch(ParkingLotException e){
            Assert.assertEquals("Parking is full", e.getMessage());
        }
    }

    @Test
    public void WhenFull_UnparkVehicle_InformObserver_IsNotFull() {
        try{
            parkingLotSystem.generateOwner(listOfObserver);
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.unParkVehicle(vehicle);
            Assert.assertFalse(listOfObserver.get(0).isFull && listOfObserver.get(1).isFull);
        } catch(ParkingLotException e){}
    }

}

