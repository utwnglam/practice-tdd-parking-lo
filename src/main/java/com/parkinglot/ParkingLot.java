package com.parkinglot;

import java.util.HashMap;

public class ParkingLot {
  private int capacity;
  HashMap<Ticket, Car> parkingLotMap = new HashMap<>();

  public ParkingLot(int capacity) {
    this.capacity = capacity;
  }

  public Ticket park(Car car) {
    if (parkingLotMap.size() < capacity) {
      Ticket newTicket = new Ticket();
      parkingLotMap.put(newTicket, car);
      return newTicket;
    } else return null;
  }

  public Car fetch(Ticket ticket) {
    return new Car();
  }
}
