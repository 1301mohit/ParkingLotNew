package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class MockitoInParkingLotSystemTest {

//    ParkingLotSystem parkingLotSystem;
//    int capacity = 3;
//    List<ParkingLot> listOfParkingLots;
//    //List<Integer> listOfCapacityOfParkingLots;
//    Object vehicle;
//    Object vehicle1;
//    Object vehicle2;
//    Object vehicle3;
//    Object vehicle4;
//    Object vehicle5;
//    Object vehicle6;
//    Object vehicle7;
//
//    @Before
//    public void setUp() {
//        vehicle = new Object();
//        vehicle1 = new Object();
//        vehicle2 = new Object();
//        vehicle3 = new Object();
//        vehicle4 = new Object();
//        vehicle5 = new Object();
//        vehicle6 = new Object();
//        vehicle7 = new Object();
//        listOfParkingLots = new ArrayList<>();
//        listOfParkingLots.add(mock(ParkingLot.class));
//        listOfParkingLots.add(mock(ParkingLot.class));
//        listOfParkingLots.add(mock(ParkingLot.class));
//        parkingLotSystem = new ParkingLotSystem(this.capacity, listOfParkingLots);
//    }
//
//    @Test
//    public void whenGivenQuantity_CreatesThatMuchParkingLots_ReturnSameNumberOfLots() {
//        int capacityOfFirstParkingLot = parkingLotSystem.getNumberOfParkingLots();
//        Assert.assertEquals(listOfParkingLots.size(), capacityOfFirstParkingLot);
//    }
//
//    @Test
//    public void whenParkVehicle_Evenly_ShouldReturnTrue() {
//        try{
//            Boolean isPark = parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
//            Boolean isPark1 = parkingLotSystem.parkVehicle(vehicle1, DriverType.NORMAL);
//            Boolean isPark2 = parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL);
//            Boolean isPark3 = parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL);
//            Boolean isPark4 = parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL);
//            Boolean isPark5 = parkingLotSystem.parkVehicle(vehicle5, DriverType.NORMAL);
//            Assert.assertTrue(isPark && isPark1 && isPark2 && isPark3 && isPark4 && isPark5);
//        } catch(ParkingLotException e){ }
//    }
//
//    @Test
//    public void whenParkVehicle_MoreThanItsCapacity_ShouldReturnException() {
//        try{
//            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
//            parkingLotSystem.parkVehicle(vehicle1, DriverType.NORMAL);
//            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL);
//            parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL);
//            parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL);
//            parkingLotSystem.parkVehicle(vehicle5, DriverType.NORMAL);
//            parkingLotSystem.parkVehicle(vehicle6, DriverType.NORMAL);
//            parkingLotSystem.parkVehicle(vehicle7, DriverType.NORMAL);
//        } catch(ParkingLotException e){
//            Assert.assertEquals(ParkingLotException.ExceptionType.FULL, e.type);
//        }
//    }
//
//    @Test
//    public void whenParkVehicleOfHadicapType_ShouldReturnTrue() {
//        try{
//            Boolean isPark = parkingLotSystem.parkVehicle(vehicle, DriverType.HANDICAP);
//            Boolean isPark1 = parkingLotSystem.parkVehicle(vehicle1, DriverType.HANDICAP);
//            Boolean isPark2 = parkingLotSystem.parkVehicle(vehicle2, DriverType.HANDICAP);
//            Boolean isPark3 = parkingLotSystem.parkVehicle(vehicle3, DriverType.HANDICAP);
//            Boolean isPark4 = parkingLotSystem.parkVehicle(vehicle4, DriverType.HANDICAP);
//            Boolean isPark5 = parkingLotSystem.parkVehicle(vehicle5, DriverType.HANDICAP);
//            Assert.assertTrue(isPark && isPark1 && isPark2 && isPark3 && isPark4 && isPark5);
//        } catch(ParkingLotException e){}
//    }
//
//    @Test
//    public void whenUnParkVehicle_ShouldReturnTrue() {
//        try{
//            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
//            parkingLotSystem.parkVehicle(vehicle1, DriverType.NORMAL);
//            Boolean isUnpark = parkingLotSystem.unParkVehicle(vehicle);
//            Boolean isUnpark1 = parkingLotSystem.unParkVehicle(vehicle1);
//            Assert.assertTrue(isUnpark && isUnpark1);
//        } catch(ParkingLotException e){}
//    }
//
//    @Test
//    public void unParkVehicle_IfNoVehiclePresent_ShouldThrowException() {
//        try{
//            parkingLotSystem.unParkVehicle(vehicle);
//        } catch (ParkingLotException e){ Assert.assertEquals(ParkingLotException.ExceptionType.Empty, e.type); }
//    }
//
//    @Test
//    public void unParkVehicle_VehicleIsNotPresent_ShouldThrowException() {
//        try {
//            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
//            Boolean isUnPark = parkingLotSystem.unParkVehicle(vehicle1);
//        } catch (ParkingLotException e) {
//            Assert.assertEquals(ParkingLotException.ExceptionType.NOT_PRESENT, e.type);
//        }
//    }
}
