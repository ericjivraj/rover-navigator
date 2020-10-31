package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import factory.IPlateauFactory;
import controller.RoverController;
import factory.UnboundedPlateauFactory;
import model.Rover;

public class IRoverNavigatorTest
{
  private IRoverNavigator roverNavigator;
  private List<Rover> roverList;
  private Map<Rover, String> commandByRoverMap;
  private Exception exception;
  private static final String NORTH = "N";
  private static final String EAST = "E";
  private static final String SOUTH = "S";
  private static final String WEST = "W";
  private static final int X_PLATEAU = 5;
  private static final int Y_PLATEAU = 5;

  @Test
  public void testProcessWithOneRoverAndEmptyCommands() throws Exception
  {
    givenRoverList();
    givenCommandByRoverMap();
    givenRoverNavigatorIsSetUpWithUnboundedPlateau();
    givenRoverExistsInList(0, 0, NORTH, "");
    whenProcessIsCalled(X_PLATEAU, Y_PLATEAU);
    thenAssertRoverLocation(roverList.get(0).getLocationX(), 0);
    thenAssertRoverLocation(roverList.get(0).getLocationY(), 0);
    thenAssertRoverCardinal(roverList.get(0).getCardinalPoint(), NORTH);
  }

  @Test
  public void testProcessWithOneRoverAndInvalidRoverLocation()
  {
    givenException();
    try
    {
      givenRoverList();
      givenCommandByRoverMap();
      givenRoverNavigatorIsSetUpWithUnboundedPlateau();
      givenRoverExistsInList(-2, -3, NORTH, "MLMLM");
      whenProcessIsCalled(X_PLATEAU, Y_PLATEAU);
      fail();
    }
    catch(Exception e)
    {
      exception = e;
    }
    thenAssertExceptionWasThrown(exception);
  }

  @Test
  public void testProcessWithOneRoverAndInvalidRoverCardinal()
  {
    givenException();
    try
    {
      givenRoverList();
      givenCommandByRoverMap();
      givenRoverNavigatorIsSetUpWithUnboundedPlateau();
      givenRoverExistsInList(2, 3, "T", "MLMLM");
      whenProcessIsCalled(X_PLATEAU, Y_PLATEAU);
      fail();
    }
    catch(Exception e)
    {
      exception = e;
    }
    thenAssertExceptionWasThrown(exception);
  }

  @Test
  public void testProcessWithOneRoverAndInvalidCommands()
  {
    givenException();
    try
    {
      givenRoverList();
      givenCommandByRoverMap();
      givenRoverNavigatorIsSetUpWithUnboundedPlateau();
      givenRoverExistsInList(2, 3, NORTH, "OKO");
      whenProcessIsCalled(X_PLATEAU, Y_PLATEAU);
      fail();
    }
    catch (Exception e)
    {
      exception = e;
    }
    thenAssertExceptionWasThrown(exception);
  }

  @Test
  public void testProcessWithTwoRovers() throws Exception
  {
    givenRoverList();
    givenCommandByRoverMap();
    givenRoverNavigatorIsSetUpWithUnboundedPlateau();
    givenRoverExistsInList(1, 2, NORTH, "LMLMLMLMM");
    givenRoverExistsInList(3, 3, EAST, "MMRMMRMRRM");
    whenProcessIsCalled(X_PLATEAU, Y_PLATEAU);
    thenAssertRoverLocation(roverList.get(0).getLocationX(), 1);
    thenAssertRoverLocation(roverList.get(0).getLocationY(), 3);
    thenAssertRoverCardinal(roverList.get(0).getCardinalPoint(), NORTH);
    thenAssertRoverLocation(roverList.get(1).getLocationX(), 5);
    thenAssertRoverLocation(roverList.get(1).getLocationY(), 1);
    thenAssertRoverCardinal(roverList.get(1).getCardinalPoint(), EAST);
  }

  @Test
  public void testProcessWithOneRoverAndEastCardinal() throws Exception
  {
    givenRoverList();
    givenCommandByRoverMap();
    givenRoverNavigatorIsSetUpWithUnboundedPlateau();
    givenRoverExistsInList(3, 3, EAST, "MMRMMRMRRM");
    whenProcessIsCalled(X_PLATEAU, Y_PLATEAU);
    thenAssertRoverLocation(roverList.get(0).getLocationX(), 5);
    thenAssertRoverLocation(roverList.get(0).getLocationY(), 1);
    thenAssertRoverCardinal(roverList.get(0).getCardinalPoint(), EAST);
  }

  @Test
  public void testProcessWithOneRoverAndSouthCardinal() throws Exception
  {
    givenRoverList();
    givenCommandByRoverMap();
    givenRoverNavigatorIsSetUpWithUnboundedPlateau();
    givenRoverExistsInList(2, 2, SOUTH, "LMRRLM");
    whenProcessIsCalled(X_PLATEAU, Y_PLATEAU);
    thenAssertRoverLocation(roverList.get(0).getLocationX(), 3);
    thenAssertRoverLocation(roverList.get(0).getLocationY(), 1);
    thenAssertRoverCardinal(roverList.get(0).getCardinalPoint(), SOUTH);
  }

  @Test
  public void testProcessWithOneRoverAndWestCardinal() throws Exception
  {
    givenRoverList();
    givenCommandByRoverMap();
    givenRoverNavigatorIsSetUpWithUnboundedPlateau();
    givenRoverExistsInList(3, 4, WEST, "LLLMMRMLM");
    whenProcessIsCalled(X_PLATEAU, Y_PLATEAU);
    thenAssertRoverLocation(roverList.get(0).getLocationX(), 4);
    thenAssertRoverLocation(roverList.get(0).getLocationY(), 5);
    thenAssertRoverCardinal(roverList.get(0).getCardinalPoint(), NORTH);
  }

  private void givenRoverList()
  {
    roverList = new ArrayList<>();
  }

  private void givenCommandByRoverMap()
  {
    commandByRoverMap = new HashMap<>();
  }

  private void givenRoverNavigatorIsSetUpWithUnboundedPlateau()
  {
    RoverController roverController = new RoverController();
    IPlateauFactory plateauFactory = new UnboundedPlateauFactory();
    roverNavigator = new RoverNavigatorImpl(roverController, plateauFactory);
  }

  private void givenRoverExistsInList(int expectedRoverX, int expectedRoverY, String expectedCardinal, String expectedCommand) throws Exception
  {
    RoverController roverController = new RoverController();
    final Rover rover = roverController.createRover(expectedRoverX, expectedRoverY, expectedCardinal);
    commandByRoverMap.put(rover, expectedCommand);
    roverList.add(rover);
  }

  private void givenException()
  {
    exception = null;
  }

  private void whenProcessIsCalled(int expectedPlateauX, int expectedPlateauY) throws Exception
  {
    this.roverNavigator.process(commandByRoverMap, expectedPlateauX, expectedPlateauY);
  }

  private void thenAssertRoverLocation(int expectedLocation, int actualLocation)
  {
    assertEquals(expectedLocation, actualLocation);
  }

  private void thenAssertRoverCardinal(String expectedCardinal, String actualCardinal)
  {
    assertEquals(expectedCardinal, actualCardinal);
  }

  private void thenAssertExceptionWasThrown(Exception exception)
  {
    assertNotNull(exception);
  }
}
