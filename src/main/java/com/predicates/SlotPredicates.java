package com.predicates;

import parkinglot.ParkingSlot;
import parkinglot.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SlotPredicates {

    public static Predicate<ParkingSlot> isColor(Vehicle.ColorType color) {
        return slot -> slot.getVehicle().colorType == color;
    }

    public static Predicate<ParkingSlot> isCarName(String carname) {
        return slot -> slot.getVehicle().vehicleName == carname;
    }

    public static void findColorCar(Vehicle.ColorType color, List<ParkingSlot> slots) {
        find(isColor(color),slots);
    }

    public static List<Integer> findCarByName(String carname, List<ParkingSlot> slots) {
        List<ParkingSlot> parkingSlots = find(isCarName(carname), slots);
        return collectAll(parkingSlots);
    }

    private static List<Integer> collectAll(List<ParkingSlot> parkingSlots){
        List<Integer> positions = new ArrayList<>();
        parkingSlots.stream().forEach(slot -> positions.add(slot.getPosition()));
        return positions;
    }

    private static List<ParkingSlot> find(Predicate<ParkingSlot> predicate, List<ParkingSlot> slot) {
        return slot.stream().filter(predicate).collect(Collectors.toList());
    }

}
