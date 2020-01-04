package parkinglot;

public class FactoryForStrategy {
    public static ParkingLotStrategy getStrategyObject(DriverType driverType, Vehicle.VehicleType vehicleType) throws ParkingLotException {
        if(DriverType.NORMAL == driverType && vehicleType == Vehicle.VehicleType.Normal)
            return new StrategyForNormal();
        else if(DriverType.HANDICAP == driverType && vehicleType == Vehicle.VehicleType.Normal)
            return new StrategyForHandicap();
        else if(DriverType.NORMAL == driverType && vehicleType == Vehicle.VehicleType.Large)
            return new StrategyForLargeVehicle();
        throw new ParkingLotException("Driver type is not present", ParkingLotException.ExceptionType.NOT_PRESENT);
    }
}
