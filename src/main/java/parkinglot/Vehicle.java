package parkinglot;

public class Vehicle {

    enum VehicleType{
        Large, Normal;
    }

    public VehicleType vehicleType;

    public Vehicle(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Vehicle(){
        this.vehicleType=VehicleType.Normal;
    }

}
