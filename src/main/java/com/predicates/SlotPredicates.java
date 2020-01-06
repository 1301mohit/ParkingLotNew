package com.predicates;

import parkinglot.ParkingSlot;
import parkinglot.Vehicle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SlotPredicates {

    public static Predicate<ParkingSlot> isColor(Vehicle.ColorType color) {
        return slot -> slot.getVehicle()!=null && slot.getVehicle().colorType == color;
    }

    public static Predicate<ParkingSlot> isTime(long time) {
        Date date = new Date();
        return slot -> slot.getVehicle()!=null && date.getTime() - slot.getDateAndTime().getTime() < time * 60000;
    }

    public static Predicate<ParkingSlot> isCarName(String carname) {
        return slot -> slot.getVehicle()!=null && slot.getVehicle().vehicleName == carname;
    }

    public static List<ParkingSlot> giveListofSlotWhenGivenColorAndName(String carname, Vehicle.ColorType color, List<ParkingSlot> slots) {
        Predicate<ParkingSlot> andPredicate = isCarName(carname).and(isColor(color));
        List<ParkingSlot> parkingSlots = find(andPredicate,slots);
        return parkingSlots;
    }

    public static List<Integer> findListOfPositionByTime(long time, List<ParkingSlot> slots) {
        List<ParkingSlot> parkingSlots = find(isTime(time),slots);
        return collectAll(parkingSlots);
    }

    public static List<Integer> findColorCar(Vehicle.ColorType color, List<ParkingSlot> slots) {
        List<ParkingSlot> parkingSlots = find(isColor(color),slots);
        return collectAll(parkingSlots);
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
