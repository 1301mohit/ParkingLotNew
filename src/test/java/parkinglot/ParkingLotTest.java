package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingLotSystem parkingLotSystem = null;

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    public void parkVehicle_ShouldReturnTrue() {
        parkingLotSystem = new ParkingLotSystem();
        Boolean isPark = parkingLotSystem.parkVehicle(new Object());
        Assert.assertEquals(true, isPark);
    }

    @Test
    public void unParkVehicle_ShouldReturnTrue() {
        parkingLotSystem = new ParkingLotSystem();
        parkingLotSystem.parkVehicle(new Object());
        Boolean isUnPark = parkingLotSystem.unParkVehicle();
        Assert.assertEquals(true, isUnPark);

    }
}
