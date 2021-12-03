package com.parkinglot;

public class ParkingLot {
  private int capacity;

  public ParkingLot(int capacity) {
    this.capacity = capacity;
  }

  public Ticket park(Car car) {
    return new Ticket();
  }

  public Car fetch(Ticket ticket) {
    return new Car();
  }
}
