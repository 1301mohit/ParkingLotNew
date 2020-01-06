package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingLotSystemTest {

    ParkingLotSystem parkingLotSystem;
    int capacity = 3;
    List<ParkingLot> listOfParkingLots;
    Vehicle vehicle1;
    Vehicle vehicle2;
    Vehicle vehicle3;
    Vehicle vehicle4;
    Vehicle vehicle5;
    Vehicle vehicle6;
    Vehicle vehicle7;
    Vehicle vehicle8;

    @Before
    public void setUp() {
        vehicle1 = new Vehicle("12342", Vehicle.ColorType.BLUE, Vehicle.VehicleType.NORMAL, "BMW");
        vehicle2 = new Vehicle("12343", Vehicle.ColorType.BLUE, Vehicle.VehicleType.NORMAL, "BMW");
        vehicle3 = new Vehicle("12301", Vehicle.ColorType.BLACK, Vehicle.VehicleType.LARGE, "ABC");
        vehicle4 = new Vehicle("12302", Vehicle.ColorType.BLACK, Vehicle.VehicleType.NORMAL, "ABC");
        vehicle5 = new Vehicle("12303", Vehicle.ColorType.WHITE, Vehicle.VehicleType.NORMAL, "ABC");
        vehicle6 = new Vehicle("12304", Vehicle.ColorType.WHITE, Vehicle.VehicleType.NORMAL, "ABC");
        vehicle7 = new Vehicle("12345", Vehicle.ColorType.BLUE, Vehicle.VehicleType.NORMAL, "Toyota");
        vehicle8 = new Vehicle("12346", Vehicle.ColorType.BLUE, Vehicle.VehicleType.NORMAL, "Toyota");
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
            Boolean isPark = parkingLotSystem.parkVehicle(vehicle1);
            Boolean isPark1 = parkingLotSystem.parkVehicle(vehicle2);
            Boolean isPark2 = parkingLotSystem.parkVehicle(vehicle3);
            Boolean isPark3 = parkingLotSystem.parkVehicle(vehicle4);
            Boolean isPark4 = parkingLotSystem.parkVehicle(vehicle5);
            Boolean isPark5 = parkingLotSystem.parkVehicle(vehicle6);
            Assert.assertTrue(isPark && isPark1 && isPark2 && isPark3 && isPark4 && isPark5);
        } catch(ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void whenParkVehicle_MoreThanItsCapacity_ShouldReturnException() {
        try{
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
            parkingLotSystem.parkVehicle(vehicle3);
            parkingLotSystem.parkVehicle(vehicle4);
            parkingLotSystem.parkVehicle(vehicle5);
            parkingLotSystem.parkVehicle(vehicle6);
            parkingLotSystem.parkVehicle(vehicle7);
            parkingLotSystem.parkVehicle(vehicle8);
        } catch(ParkingLotException e){
            Assert.assertEquals(ParkingLotException.ExceptionType.FULL, e.type);
        }
    }

    @Test
    public void whenParkVehicleOfHadicapType_ShouldReturnTrue() {
        try{
            Boolean isPark = parkingLotSystem.parkVehicle(vehicle1, DriverType.HANDICAP);
            Boolean isPark1 = parkingLotSystem.parkVehicle(vehicle2, DriverType.HANDICAP);
            Boolean isPark2 = parkingLotSystem.parkVehicle(vehicle4, DriverType.HANDICAP);
            Boolean isPark3 = parkingLotSystem.parkVehicle(vehicle5, DriverType.HANDICAP);
            Boolean isPark4 = parkingLotSystem.parkVehicle(vehicle6, DriverType.HANDICAP);
            Boolean isPark5 = parkingLotSystem.parkVehicle(vehicle7, DriverType.HANDICAP);
            Assert.assertTrue(isPark && isPark1 && isPark2 && isPark3 && isPark4 && isPark5);
        } catch(ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void whenUnParkVehicle_ShouldReturnTrue() {
        try{
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
            Boolean isUnpark = parkingLotSystem.unParkVehicle(vehicle1);
            Boolean isUnpark1 = parkingLotSystem.unParkVehicle(vehicle2);
            Assert.assertTrue(isUnpark && isUnpark1);
        } catch(ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void unParkVehicle_IfNoVehiclePresent_ShouldThrowException() {
        try{
            parkingLotSystem.unParkVehicle(vehicle1);
        } catch (ParkingLotException e){ Assert.assertEquals(ParkingLotException.ExceptionType.NOT_PRESENT, e.type); }
    }

    @Test
    public void unParkVehicle_VehicleIsNotPresent_ShouldThrowException() {
        try {
            parkingLotSystem.parkVehicle(vehicle1, DriverType.NORMAL);
            parkingLotSystem.unParkVehicle(vehicle2);
        } catch (ParkingLotException e) {
           Assert.assertEquals(ParkingLotException.ExceptionType.NOT_PRESENT, e.type);
        }
    }

    @Test
    public void parkVehicle_EvenlyDistributed_ShouldReturnTrue() {
        try{
            parkingLotSystem.parkVehicle(vehicle2);
            parkingLotSystem.parkVehicle(vehicle3);
            parkingLotSystem.parkVehicle(vehicle2);
            parkingLotSystem.parkVehicle(vehicle3);
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
            Boolean checkEvenlyDistributed = parkingLotSystem.isEvenlyDistributed();
            Assert.assertTrue(checkEvenlyDistributed);
        } catch(ParkingLotException e) { e.printStackTrace(); }
    }

    @Test
    public void parkVehicleAfterRemovalAVehicle_EvenlyDistributed_ShouldReturnTrue() {
        try{
            parkingLotSystem.parkVehicle(vehicle5);
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
            parkingLotSystem.unParkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle3);
            Boolean checkEvenlyDistributed = parkingLotSystem.isEvenlyDistributed();
            Assert.assertTrue(checkEvenlyDistributed);
        } catch(ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parkLargeVehicle_IsParkedAt_LotWithMaximumSlot() {
        try{
            parkingLotSystem.parkVehicle(vehicle3);
            Assert.assertEquals(parkingLotSystem.listOfParkingLots.get(1).vehicleMap.get(1).getVehicle(), vehicle3);
        }catch(ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void park3Vehicles_GetLocationListOf_WhiteVehicles() {
        try{
            parkingLotSystem.parkVehicle(vehicle5);
            parkingLotSystem.parkVehicle(vehicle7);
            parkingLotSystem.parkVehicle(vehicle6);
            List<List<Integer>> listofColoredVechicles = parkingLotSystem.getListofColoredVechicles(Vehicle.ColorType.WHITE);
            Assert.assertEquals((Integer) 1,listofColoredVechicles.get(0).get(0));
            Assert.assertEquals((Integer) 1,listofColoredVechicles.get(2).get(0));
        }catch(ParkingLotException e){ e.printStackTrace(); }
    }

    @Test
    public void park3Vehicles_GetPlateNumberAttendantNameAndPositionOfTheVehicle() {
        try{
            parkingLotSystem.parkVehicle(vehicle7, "ABC");
            parkingLotSystem.parkVehicle(vehicle6);
            parkingLotSystem.parkVehicle(vehicle8, "ABD");
            List<List<ParkingSlot>> listOfSpecifiedColoredAndType = parkingLotSystem.getListofSlots("Toyota",Vehicle.ColorType.BLUE);
            Assert.assertEquals("12345", listOfSpecifiedColoredAndType.get(0).get(0).getVehicle().getPlateNumber());
            Assert.assertEquals("ABC", listOfSpecifiedColoredAndType.get(0).get(0).getParkingAttendantName());
            Assert.assertEquals(1, (int)listOfSpecifiedColoredAndType.get(0).get(0).getPosition());
            Assert.assertEquals("12346", listOfSpecifiedColoredAndType.get(2).get(0).getVehicle().getPlateNumber());
            Assert.assertEquals("ABD", listOfSpecifiedColoredAndType.get(2).get(0).getParkingAttendantName());
            Assert.assertEquals(1, (int)listOfSpecifiedColoredAndType.get(2).get(0).getPosition());
        }catch(ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void park3Vehicles_GetLocationListOf_BMWVehicles() {
        try{
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle5);
            parkingLotSystem.parkVehicle(vehicle2);
            List<List<Integer>> listofVechiclesAccordingToHerName = parkingLotSystem.getListofCarWithName("BMW");
            Assert.assertEquals((Integer)1,listofVechiclesAccordingToHerName.get(0).get(0));
            Assert.assertEquals((Integer)1,listofVechiclesAccordingToHerName.get(2).get(0));
        }catch(ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void park3Vehicles_GetLocationListOf_VehiclesParkedLessThan30Min() {
        try{
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle5);
            parkingLotSystem.parkVehicle(vehicle2);
            List<List<Integer>> listofVechiclesParkedLessThan30Min = parkingLotSystem.getListofCarWithTime(30);
            Assert.assertEquals((Integer)1, listofVechiclesParkedLessThan30Min.get(0).get(0));
            Assert.assertEquals((Integer)1, listofVechiclesParkedLessThan30Min.get(1).get(0));
            Assert.assertEquals((Integer)1, listofVechiclesParkedLessThan30Min.get(2).get(0));
        }catch(ParkingLotException e){
            e.printStackTrace();
        }
    }

}
