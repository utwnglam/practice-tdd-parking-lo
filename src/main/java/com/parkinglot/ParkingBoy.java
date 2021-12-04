package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.List;
import java.util.Observable;

public class ParkingBoy extends Observable {
  private final List<ParkingLot> parkingLots;

  public ParkingBoy(List<ParkingLot> parkingLot) {
    this.parkingLots = parkingLot;
  }

  public Ticket park(Car car) {
    try {
      return parkingLots.stream()
        .filter(parkingLot -> parkingLot.getAvailablePosition() > 0)
        .findFirst()
        .get()
        .park(car);
    } catch (Exception exception) {
      if (countObservers() == 0) {
        throw new NoAvailablePositionException("No available position.");
      } else {
        setChanged();
        notifyObservers(new NoAvailablePositionException("Manager: No available position."));
        return null;
      }
    }
  }

  public Car fetch(Ticket ticket) {
    for (ParkingLot parkingLot : parkingLots) {
      if (parkingLot.contains(ticket)) {
        return parkingLot.fetch(ticket);
      }
    }
    if (countObservers() == 0) {
      throw new UnrecognizedTicketException("Unrecognized parking ticket.");
    } else {
      setChanged();
      notifyObservers(new UnrecognizedTicketException("Manager: Unrecognized parking ticket."));
      return null;
    }
  }

  public List<ParkingLot> getParkingLots() {
    return parkingLots;
  }
}
