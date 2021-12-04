package com.parkinglot;

import com.parkinglot.exception.BoyNotInManagementListException;
import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotManagerTest {
  private ParkingBoy parkingBoy;
  private SmartParkingBoy smartParkingBoy;
  private SuperSmartParkingBoy superSmartParkingBoy;
  private ParkingLot firstParkingLot, thirdParkingLot, fifthParkingLot;

  @BeforeEach
  public void setup() {
    firstParkingLot = new ParkingLot();
    ParkingLot secondParkingLot = new ParkingLot();
    List<ParkingLot> parkingBoyLotList = Arrays.asList(firstParkingLot, secondParkingLot);
    parkingBoy = new ParkingBoy(parkingBoyLotList);

    thirdParkingLot = new ParkingLot();
    ParkingLot fourthParkingLot = new ParkingLot();
    List<ParkingLot> smartParkingBoyLotList = Arrays.asList(thirdParkingLot, fourthParkingLot);
    smartParkingBoy = new SmartParkingBoy(smartParkingBoyLotList);

    fifthParkingLot = new ParkingLot();
    ParkingLot sixthParkingLot = new ParkingLot();
    List<ParkingLot> superSmartParkingBoyLotList = Arrays.asList(fifthParkingLot, sixthParkingLot);
    superSmartParkingBoy = new SuperSmartParkingBoy(superSmartParkingBoyLotList);
  }

  @Test
  public void should_return_true_when_add_parkingBoy_to_list_given_parkingLotManager_have_managementList() {
    List<ParkingBoy> managementList = Arrays.asList(parkingBoy, smartParkingBoy, superSmartParkingBoy);
    List<ParkingLot> parkingLotList = Collections.emptyList();

    ParkingLotManager parkingLotManager = new ParkingLotManager(managementList, parkingLotList);
    assertTrue(parkingLotManager.contains(parkingBoy));
  }

  @Test
  public void should_park_to_first_lot_when_parking_given_parking_manager_manage_two_parking_lots_and_both_available() {
    List<ParkingBoy> managementList = Arrays.asList(parkingBoy, smartParkingBoy, superSmartParkingBoy);

    ParkingLot seventhParkingLot = new ParkingLot();
    ParkingLot eighthParkingLot = new ParkingLot();
    List<ParkingLot> parkingLotList = Arrays.asList(seventhParkingLot, eighthParkingLot);

    ParkingLotManager parkingLotManager = new ParkingLotManager(managementList, parkingLotList);
    Ticket ticket = parkingLotManager.park(new Car());

    assertNotNull(ticket);
    assertEquals(9, seventhParkingLot.getAvailablePosition());
    assertEquals(10, eighthParkingLot.getAvailablePosition());
  }

  @Test
  public void should_return_right_when_parking_given_parking_manager_manage_two_parking_lots_and_both_have_1car() {
    List<ParkingBoy> managementList = Arrays.asList(parkingBoy, smartParkingBoy, superSmartParkingBoy);
    ParkingLot seventhParkingLot = new ParkingLot(1);
    ParkingLot eighthParkingLot = new ParkingLot(1);
    List<ParkingLot> parkingLotList = Arrays.asList(seventhParkingLot, eighthParkingLot);

    ParkingLotManager parkingLotManager = new ParkingLotManager(managementList, parkingLotList);
    Car firstParkedCar = new Car();
    Ticket firstTicket = parkingLotManager.park(firstParkedCar);
    Car secondParkedCar = new Car();
    Ticket secondTicket = parkingLotManager.park(secondParkedCar);

    Car carFetchFromFirstTicket = parkingLotManager.fetch(firstTicket);
    Car carFetchFromSecondTicket = parkingLotManager.fetch(secondTicket);

    assertThat(carFetchFromFirstTicket).isEqualTo(firstParkedCar);
    assertThat(carFetchFromSecondTicket).isEqualTo(secondParkedCar);
  }

  @Test
  public void should_return_nothing_when_fetching_given_parking_manager_manage_two_parking_lots_and_unrecognized_ticket() {
    List<ParkingBoy> managementList = Arrays.asList(parkingBoy, smartParkingBoy, superSmartParkingBoy);
    ParkingLot seventhParkingLot = new ParkingLot();
    ParkingLot eighthParkingLot = new ParkingLot();
    List<ParkingLot> parkingLotList = Arrays.asList(seventhParkingLot, eighthParkingLot);

    ParkingLotManager parkingLotManager = new ParkingLotManager(managementList, parkingLotList);

    UnrecognizedTicketException unrecognizedWrongTicket = assertThrows(UnrecognizedTicketException.class, () -> {
      parkingLotManager.fetch(new Ticket());
    });
    assertEquals("Unrecognized parking ticket.", unrecognizedWrongTicket.getMessage());
  }

  @Test
  public void should_return_nothing_when_fetching_given_parking_manager_manage_two_parking_lots_and_used_ticket() {
    List<ParkingBoy> managementList = Arrays.asList(parkingBoy, smartParkingBoy, superSmartParkingBoy);
    ParkingLot seventhParkingLot = new ParkingLot();
    ParkingLot eighthParkingLot = new ParkingLot();
    List<ParkingLot> parkingLotList = Arrays.asList(seventhParkingLot, eighthParkingLot);

    ParkingLotManager parkingLotManager = new ParkingLotManager(managementList, parkingLotList);

    Ticket ticket = parkingLotManager.park(new Car());
    parkingLotManager.fetch(ticket);
    UnrecognizedTicketException unrecognizedUsedTicket = assertThrows(UnrecognizedTicketException.class, () -> {
      parkingLotManager.fetch(ticket);
    });
    assertEquals("Unrecognized parking ticket.", unrecognizedUsedTicket.getMessage());
  }

  @Test
  public void should_return_nothing_when_parking_given_parking_manager_manage_two_parking_lots_and_both_unavailable() {
    List<ParkingBoy> managementList = Arrays.asList(parkingBoy, smartParkingBoy, superSmartParkingBoy);
    ParkingLot seventhParkingLot = new ParkingLot(1);
    ParkingLot eighthParkingLot = new ParkingLot(1);
    List<ParkingLot> parkingLotList = Arrays.asList(seventhParkingLot, eighthParkingLot);

    ParkingLotManager parkingLotManager = new ParkingLotManager(managementList, parkingLotList);

    seventhParkingLot.park(new Car());
    eighthParkingLot.park(new Car());

    NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> {
      parkingLotManager.park(new Car());
    });
    assertEquals("No available position.", noAvailablePositionException.getMessage());
  }

  @Test
  public void should_return_correct_car_when_parking_given_parking_manager_manage_three_parking_boys() {
    List<ParkingBoy> managementList = Arrays.asList(parkingBoy, smartParkingBoy, superSmartParkingBoy);
    List<ParkingLot> parkingLotList = Collections.emptyList();
    ParkingLotManager parkingLotManager = new ParkingLotManager(managementList, parkingLotList);

    Car firstParkedCar = new Car();
    Ticket firstTicket = parkingLotManager.tellBoyToPark(firstParkedCar, parkingBoy);
    Car secondParkedCar = new Car();
    Ticket secondTicket = parkingLotManager.tellBoyToPark(secondParkedCar, smartParkingBoy);
    Car thirdParkedCar = new Car();
    Ticket thirdTicket = parkingLotManager.tellBoyToPark(thirdParkedCar, superSmartParkingBoy);

    Car carFetchFromFirstTicket = parkingLotManager.tellBoyToFetch(firstTicket, parkingBoy);
    Car carFetchFromSecondTicket = parkingLotManager.tellBoyToFetch(secondTicket, smartParkingBoy);
    Car carFetchFromThirdTicket = parkingLotManager.tellBoyToFetch(thirdTicket, superSmartParkingBoy);

    assertThat(carFetchFromFirstTicket).isEqualTo(firstParkedCar);
    assertThat(carFetchFromSecondTicket).isEqualTo(secondParkedCar);
    assertThat(carFetchFromThirdTicket).isEqualTo(thirdParkedCar);
  }

  @Test
  public void should_return_exception_when_park_or_fetch_given_parkingBoys_not_managed_by_parkingManager() {
    List<ParkingBoy> managementList = Collections.emptyList();
    List<ParkingLot> parkingLotList = Collections.emptyList();
    ParkingLotManager parkingLotManager = new ParkingLotManager(managementList, parkingLotList);

    BoyNotInManagementListException boyNotInManagementListException1 = assertThrows(BoyNotInManagementListException.class, () -> {
      parkingLotManager.tellBoyToPark(new Car(), parkingBoy);
    });
    assertEquals("Unrecognized parking ticket.", boyNotInManagementListException1.getMessage());

    BoyNotInManagementListException boyNotInManagementListException2 = assertThrows(BoyNotInManagementListException.class, () -> {
      parkingLotManager.tellBoyToFetch(new Ticket(), parkingBoy);
    });
    assertEquals("Unrecognized parking ticket.", boyNotInManagementListException2.getMessage());
  }
}
