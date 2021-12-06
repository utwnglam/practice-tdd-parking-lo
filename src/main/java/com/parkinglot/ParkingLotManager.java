package com.parkinglot;

import com.parkinglot.exception.BoyNotInManagementListException;
import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ParkingLotManager extends ParkingBoy implements Observer {
  private final List<ParkingBoy> managementList;
  private final String notManagedExceptionMessage = "Parking boy is not managed by this parking manager.";
  public static String NoAvailablePositionManagerMessage = "Manager: No available position.";
  public static String UnrecognizedTicketManagerMessage = "Manager: Unrecognized parking ticket.";

  public ParkingLotManager(List<ParkingBoy> managementList, List<ParkingLot> parkingLot) {
    super(parkingLot);
    this.managementList = managementList;
    for (ParkingBoy parkingBoy : managementList) {
      parkingBoy.addObserver(this);
    }
  }

  public Ticket tellBoyToPark(Car car, ParkingBoy parkingBoy) {
    if (contains(parkingBoy)) {
      return parkingBoy.park(car);
    }
    throw new BoyNotInManagementListException(notManagedExceptionMessage);
  }

  public Car tellBoyToFetch(Ticket ticket, ParkingBoy parkingBoy) {
    if (contains(parkingBoy)) {
      return parkingBoy.fetch(ticket);
    }
    throw new BoyNotInManagementListException(notManagedExceptionMessage);
  }

  public boolean contains(ParkingBoy parkingBoy) {
    return managementList.contains(parkingBoy);
  }

  @Override
  public void update(Observable observable, Object message) {
    String messageFromBoy = (String) message;
    if (messageFromBoy.contains(NoAvailablePositionManagerMessage)) {
      throw new NoAvailablePositionException(NoAvailablePositionManagerMessage);
    } else if (messageFromBoy.contains(UnrecognizedTicketManagerMessage)) {
      throw new UnrecognizedTicketException(UnrecognizedTicketManagerMessage);
    }
  }
}
