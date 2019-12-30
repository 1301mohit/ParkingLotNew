package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingLotTest {

    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    Object vehicle1 = null;
    Object vehicle2 = null;
    List<ParkingLotInformation> listOfObserver = null;

    @Before
    public void setUp() {
        listOfObserver = new ArrayList<>();
        listOfObserver.add(new ParkingLotOwner());
        listOfObserver.add(new AirportSecurity());
        parkingLotSystem = new ParkingLotSystem(10);
        vehicle = new Object();
        vehicle1 = new Object();
        vehicle2 = new Object();
        parkingLotSystem.generateOwner(listOfObserver);
    }

    @Test
    public void parkVehicle_ShouldReturnTrue() {
        try {
            Boolean isPark = parkingLotSystem.parkVehicle(vehicle);
            Boolean isPark1 = parkingLotSystem.parkVehicle(vehicle1);
            Assert.assertTrue(isPark && isPark1);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parkMoreThanCapacity_ShouldReturnException() {
        try {
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
            parkingLotSystem.parkVehicle(vehicle1);
            Boolean isUnPark = parkingLotSystem.unParkVehicle(vehicle1);
            Assert.assertEquals(true, isUnPark);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void unParkVehicle_VehicleIsNotPresent_ShouldThrowException() {
        try {
            parkingLotSystem.parkVehicle(vehicle1);
            Boolean isUnPark = parkingLotSystem.unParkVehicle(vehicle);
        } catch (ParkingLotException e) {
           Assert.assertEquals("Vehicle is not present", e.getMessage());
        }
    }

    @Test
    public void informObserver_IsFull_ReturnException() {
        try{
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
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
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
        } catch(ParkingLotException e) {}
    }

    @Test
    public void ownerSendsPosition_ToParkCar_ParksCarAtPosition() {
        try{
            ParkingLotOwner owner = mock(ParkingLotOwner.class);
            parkingLotSystem.setOwner(owner);
            when(owner.getPositionToPark(parkingLotSystem.vehicleMap.keySet().stream().collect(Collectors.toList())))
                    .thenReturn(4);
            parkingLotSystem.parkVehicle(vehicle);
            Assert.assertEquals(vehicle,parkingLotSystem.vehicleMap.get(4));
        } catch(ParkingLotException e) {}
    }
}

