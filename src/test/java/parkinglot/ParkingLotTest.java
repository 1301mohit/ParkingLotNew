package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    Object vehicle1 = null;
    Object vehicle2 = null;

    @Before
    public void setUp() {
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
            Boolean isPark1 = parkingLotSystem.parkVehicle(vehicle);
            Boolean isPark2 = parkingLotSystem.parkVehicle(vehicle1);
            Boolean isPark3 = parkingLotSystem.parkVehicle(vehicle2);
            Assert.assertFalse(isPark1 && isPark2 && isPark3);
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
            Boolean isUnPark = parkingLotSystem.unParkVehicle(vehicle1);
            Assert.assertEquals(true, isUnPark);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void informOwner_IsNotFull_ReturnTrue() {
        try{
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
            Boolean check = parkingLotSystem.informOwner(new ParkingLotOwner());
            Assert.assertTrue(check);
        } catch(ParkingLotException e){
            Assert.assertEquals("Parking is full", e.getMessage());
        }
    }

}

