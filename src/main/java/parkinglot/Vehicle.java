package parkinglot;

public class Vehicle {

    public enum VehicleType{
        LARGE, NORMAL;
    }

    public enum ColorType{
        WHITE, BLACK, BLUE;
    }

    public String plateNumber;
    public ColorType colorType;
    public VehicleType vehicleType;
    public String vehicleName;

    public Vehicle(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        this.colorType = ColorType.BLACK;
    }

    public Vehicle(){
        this.vehicleType = VehicleType.NORMAL;
        this.colorType = ColorType.BLACK;
    }

    public Vehicle(ColorType color, VehicleType vehicleType) {
        this.colorType = color;
        this.vehicleType = vehicleType;
    }

    public Vehicle(String plateNumber, ColorType color, VehicleType vehicleType, String vehicleName) {
        this.plateNumber = plateNumber;
        this.colorType = color;
        this.vehicleType = vehicleType;
        this.vehicleName = vehicleName;
    }

    public ColorType getColor() {
        return this.colorType;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

}
