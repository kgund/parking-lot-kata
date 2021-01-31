package com.digite.kata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLot {

    private final int parkingLotCapacity;
    Map<Integer, Car> slots;
    private int currentCapacity;

    public ParkingLot(int intialCapacity) {
        parkingLotCapacity = intialCapacity;
        currentCapacity = parkingLotCapacity;
        slots = new HashMap<>();
    }

    public int getStatus() {
        return this.currentCapacity;
    }

    public void park(String registrationNumber, String colour) {
        int key = 1;
        while(this.slots.containsKey(key)) {
            key += 1;
        }

        if(key <= parkingLotCapacity){
            this.slots.put(key, new Car(registrationNumber, colour));
            this.currentCapacity -= 1;
        }
    }

    public String getColourByRegistrationNumber(String registrationNumber) {
        return slots.entrySet().stream().filter(entry -> registrationNumber.equals(entry.getValue().getRegistrationNumber()))
                .map(val -> val.getValue().getColour())
                .collect(Collectors.joining());
    }

    public void leave(int i) {
        slots.remove(i);
        this.currentCapacity += 1;
    }

    public int getSlotNumberByRegistrationNumber(String registrationNumber) {
        return slots.entrySet().stream().filter(entry -> registrationNumber.equals(entry.getValue().getRegistrationNumber()))
                .findFirst().get().getKey();
    }

    public List<Integer> getSlotNumbersByColor(String color) {
        return slots.entrySet().stream().filter(entry -> color.equals(entry.getValue().getColour()))
                .map(val -> val.getKey())
                .collect(Collectors.toList());
    }

    public List<String> getRegistrationNumbersByColor(String color) {
        return slots.entrySet().stream().filter(entry -> color.equals(entry.getValue().getColour()))
                .map(val -> val.getValue().getRegistrationNumber())
                .collect(Collectors.toList());
    }
}
