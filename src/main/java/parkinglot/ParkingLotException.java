package parkinglot;

public class ParkingLotException extends Exception {

    public enum ExceptionType {
        FULL, NOT_PRESENT
    }

    ExceptionType type;

    public ParkingLotException(String s, ExceptionType type) {
        super(s);
        this.type = type;
    }

}
