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
    List<ParkingLot> listOfParkingLots;
    Vehicle vehicle;
    Vehicle vehicle1;
    Vehicle vehicle2;
    Vehicle vehicle3;
    Vehicle vehicle4;
    Vehicle vehicle5;
    Vehicle vehicle6;
    Vehicle vehicle7;
    Vehicle vehicle8;
    Vehicle vehicle9;

    @Before
    public void setUp() {
        vehicle = new Vehicle();
        vehicle1 = new Vehicle();
        vehicle2 = new Vehicle();
        vehicle3 = new Vehicle();
        vehicle4 = new Vehicle();
        vehicle5 = new Vehicle();
        vehicle6 = new Vehicle(Vehicle.VehicleType.Large);
        vehicle7 = new Vehicle(Vehicle.VehicleType.Large);
        vehicle8 = new Vehicle(Vehicle.ColorType.WHITE, Vehicle.VehicleType.Normal);
        vehicle9 = new Vehicle(Vehicle.ColorType.WHITE, Vehicle.VehicleType.Normal);
        listOfParkingLots = new ArrayList<>();
        listOfParkingLots.add(new ParkingLot(2));
        listOfParkingLots.add(new ParkingLot(3));
        listOfParkingLots.add(new ParkingLot(2));
        parkingLotSystem = new ParkingLotSystem(this.capacity, listOfParkingLots);
    }

    @Test
    public void whenGivenQuantity_CreatesThatMuchParkingLots_ReturnSameNumberOfLots() {
        int capacityOfFirstParkingLot = parkingLotSystem.getNumberOfParkingLots();
        Assert.assertEquals(listOfParkingLots.size(), capacityOfFirstParkingLot);
    }

    @Test
    public void whenParkVehicle_Evenly_ShouldReturnTrue() {
        try{
            Boolean isPark = parkingLotSystem.parkVehicle(vehicle);
            Boolean isPark1 = parkingLotSystem.parkVehicle(vehicle1);
            Boolean isPark2 = parkingLotSystem.parkVehicle(vehicle2);
            Boolean isPark3 = parkingLotSystem.parkVehicle(vehicle3);
            Boolean isPark4 = parkingLotSystem.parkVehicle(vehicle4);
            Boolean isPark5 = parkingLotSystem.parkVehicle(vehicle5);
            Assert.assertTrue(isPark && isPark1 && isPark2 && isPark3 && isPark4 && isPark5);
        } catch(ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void whenParkVehicle_MoreThanItsCapacity_ShouldReturnException() {
        try{
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle1, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle5, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle6, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle7, DriverType.NORMAL);
        } catch(ParkingLotException e){
            Assert.assertEquals(ParkingLotException.ExceptionType.FULL, e.type);
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

    @Test
    public void whenUnParkVehicle_ShouldReturnTrue() {
        try{
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle1, DriverType.NORMAL);
            Boolean isUnpark = parkingLotSystem.unParkVehicle(vehicle);
            Boolean isUnpark1 = parkingLotSystem.unParkVehicle(vehicle1);
            Assert.assertTrue(isUnpark && isUnpark1);
        } catch(ParkingLotException e){}
    }

    @Test
    public void unParkVehicle_IfNoVehiclePresent_ShouldThrowException() {
        try{
            parkingLotSystem.unParkVehicle(vehicle);
        } catch (ParkingLotException e){ Assert.assertEquals(ParkingLotException.ExceptionType.NOT_PRESENT, e.type); }
    }

    @Test
    public void unparkVehicle_IfParkingLotSystemIsEmpty_ShouldReturnException() {
        try{
            parkingLotSystem.unParkVehicle(vehicle);
        }catch (ParkingLotException e){
            Assert.assertEquals(ParkingLotException.ExceptionType.NOT_PRESENT, e.type);
        }
    }

    @Test
    public void unParkVehicle_VehicleIsNotPresent_ShouldThrowException() {
        try {
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
            parkingLotSystem.unParkVehicle(vehicle1);
        } catch (ParkingLotException e) {
           Assert.assertEquals(ParkingLotException.ExceptionType.NOT_PRESENT, e.type);
        }
    }

    @Test
    public void parkVehicle_EvenlyDistributed_ShouldReturnTrue() {
        try{
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle1, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle5, DriverType.NORMAL);
            Boolean checkEvenlyDistributed = parkingLotSystem.isEvenlyDistributed();
            Assert.assertTrue(checkEvenlyDistributed);
        } catch(ParkingLotException e) { }
    }

    @Test
    public void parkVehicleAfterRemovalAVehicle_EvenlyDistributed_ShouldReturnTrue() {
        try{
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle1, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL);
            parkingLotSystem.unParkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL);
            Boolean checkEvenlyDistributed = parkingLotSystem.isEvenlyDistributed();
            Assert.assertTrue(checkEvenlyDistributed);
        } catch(ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parkLargeVehicle_IsParkedAt_LotWithMaximumSlot() {
        try{
            parkingLotSystem.parkVehicle(vehicle6, DriverType.NORMAL);
            Assert.assertEquals(parkingLotSystem.listOfParkingLots.get(1).vehicleMap.get(1).getVehicle(), vehicle6);
        }catch(ParkingLotException e){
        }
    }

    @Test
    public void park3Vehicles_GetLocationOf_WhiteVehicles() {
        try{
            parkingLotSystem.parkVehicle(vehicle8);
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle9);
            List<List<Integer>> expected_list_position =  new ArrayList<>();
            List<Integer> listOfPositions = new ArrayList<>();
            listOfPositions.add(1);
            expected_list_position.add(listOfPositions);
            List<Integer> listOfPositions1 = new ArrayList<>();
            expected_list_position.add(listOfPositions1);
            expected_list_position.add(listOfPositions);
            List<List<Integer>> listofColoredVechicles = parkingLotSystem.getListofColoredVechicles(Vehicle.ColorType.WHITE);
            Assert.assertEquals(expected_list_position,listofColoredVechicles);
        }catch(ParkingLotException e){}
    }
}
