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
      Ticket ticketToBeReturn = new Ticket();
      parkingLotMap.put(ticketToBeReturn, car);
      return ticketToBeReturn;
    } else return null;
  }

  public Car fetch(Ticket ticket) {
    if (ticket != null) {
      return parkingLotMap.get(ticket);
    } else return null;
  }
}
