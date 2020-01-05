package parkinglot;

public class Vehicle {

    enum VehicleType{
        Large, Normal;
    }

    enum ColorType{
        WHITE, BLACK;
    }

    public ColorType colorType;

    public VehicleType vehicleType;

    public Vehicle(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        this.colorType = ColorType.BLACK;
    }

    public Vehicle(){
        this.vehicleType = VehicleType.Normal;
        this.colorType = ColorType.BLACK;
    }

    public Vehicle(ColorType colorType, VehicleType vehicleType) {
        this.colorType = colorType;
        this.vehicleType = vehicleType;
    }

    public ColorType getColor() {
        return this.colorType;
    }

}
