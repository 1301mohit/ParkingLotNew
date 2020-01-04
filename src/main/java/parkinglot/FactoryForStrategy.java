package parkinglot;

public class FactoryForStrategy {
    public static ParkingLotStrategy getStrategyObject(DriverType type) throws ParkingLotException {
        if(DriverType.NORMAL == type)
            return new StrategyForNormal();
        else if(DriverType.HANDICAP == type)
            return new StrategyForHandicap();
        throw new ParkingLotException("Driver type is not present", ParkingLotException.ExceptionType.NOT_PRESENT);
    }
}
