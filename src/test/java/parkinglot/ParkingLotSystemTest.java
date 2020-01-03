package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystemTest {

    ParkingLotSystem parkingLotSystem;
    int capacity = 3;
    List<Integer> listOfCapacityOfParkingLots;
    Object vehicle;
    Object vehicle1;
    Object vehicle2;
    Object vehicle3;
    Object vehicle4;
    Object vehicle5;
    Object vehicle6;
    Object vehicle7;

    @Before
    public void setUp() {
        vehicle = new Object();
        vehicle1 = new Object();
        vehicle2 = new Object();
        vehicle3 = new Object();
        vehicle4 = new Object();
        vehicle5 = new Object();
        vehicle6 = new Object();
        vehicle7 = new Object();
        listOfCapacityOfParkingLots = new ArrayList<>();
        listOfCapacityOfParkingLots.add(2);
        listOfCapacityOfParkingLots.add(3);
        listOfCapacityOfParkingLots.add(2);
        parkingLotSystem = new ParkingLotSystem(this.capacity, listOfCapacityOfParkingLots);
    }

    @Test
    public void whenGivenQuantity_CreatesThatMuchParkingLots_ReturnSameNumberOfLots() {
        int capacityOfFirstParkingLot = parkingLotSystem.getNumberOfParkingLots();
        Assert.assertEquals(listOfCapacityOfParkingLots.size(), capacityOfFirstParkingLot);
    }

    @Test
    public void whenParkVehicle_Evenly_ShouldReturnTrue() {
        try{
            Boolean isPark = parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
            Boolean isPark1 = parkingLotSystem.parkVehicle(vehicle1, DriverType.NORMAL);
            Boolean isPark2 = parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL);
            Boolean isPark3 = parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL);
            Boolean isPark4 = parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL);
            Boolean isPark5 = parkingLotSystem.parkVehicle(vehicle5, DriverType.NORMAL);
            Assert.assertTrue(isPark && isPark1 && isPark2 && isPark3 && isPark4 && isPark5);
        } catch(ParkingLotException e){ }
    }

    @Test
    public void whenParkVehicle_MoreThanItsCapacity_ShouldReturnException() {
        try{
            Boolean isPark = parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
            Boolean isPark1 = parkingLotSystem.parkVehicle(vehicle1, DriverType.NORMAL);
            Boolean isPark2 = parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL);
            Boolean isPark3 = parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL);
            Boolean isPark4 = parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL);
            Boolean isPark5 = parkingLotSystem.parkVehicle(vehicle5, DriverType.NORMAL);
            Boolean isPark6 = parkingLotSystem.parkVehicle(vehicle6, DriverType.NORMAL);
            Boolean isPark7 = parkingLotSystem.parkVehicle(vehicle7, DriverType.NORMAL);
        } catch(ParkingLotException e){
            Assert.assertEquals("ParkingLotSystem is full", e.getMessage());
        }
    }

    @Test
    public void whenParkVehicleOfHadicapType_ShouldReturnTrue() {
        try{
            Boolean isPark = parkingLotSystem.parkVehicle(vehicle, DriverType.HANDICAP);
            Boolean isPark1 = parkingLotSystem.parkVehicle(vehicle1, DriverType.HANDICAP);
            Boolean isPark2 = parkingLotSystem.parkVehicle(vehicle2, DriverType.HANDICAP);
            Boolean isPark3 = parkingLotSystem.parkVehicle(vehicle3, DriverType.HANDICAP);
            Boolean isPark4 = parkingLotSystem.parkVehicle(vehicle4, DriverType.HANDICAP);
            Boolean isPark5 = parkingLotSystem.parkVehicle(vehicle5, DriverType.HANDICAP);
            Assert.assertTrue(isPark && isPark1 && isPark2 && isPark3 && isPark4 && isPark5);
        } catch(ParkingLotException e){}
    }

}
