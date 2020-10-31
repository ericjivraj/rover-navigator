package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;

import model.Plateau;
import model.Rover;

public class RoverControllerTest
{
  private RoverController mockIRover;
  private Rover mockRover;
  private Plateau mockPlateau;
  private Exception exception;
  private static final int X_LOCATION = 3;
  private static final int Y_LOCATION = 1;
  private static final String CARDINAL_POINT = "E";

  @Test
  public void testRoverExists()
  {
    givenRoverIsSetUp();
    verifyRoverExists(this.mockIRover);
  }

  @Test
  public void testCreateValidRover() throws Exception
  {
    givenRoverIsSetUp();
    whenCreateIsCalled(X_LOCATION, Y_LOCATION, CARDINAL_POINT);
    thenLocationIs(mockRover.getLocationX(), X_LOCATION);
    thenLocationIs(mockRover.getLocationY(), Y_LOCATION);
    thenCardinalPointIs(mockRover.getCardinalPoint(), CARDINAL_POINT);
  }

  @Test
  public void testCreateInvalidRover()
  {
    givenException();
    try
    {
      givenRoverIsSetUp();
      whenCreateIsCalled(-3, -1, "A");
      fail();
    }
    catch (Exception e)
    {
      exception = e;
    }
    thenAssertExceptionWasThrown(exception);
  }

  @Test
  public void testCreateRoverWithInvalidCardinal()
  {
    givenException();
    try
    {
      givenRoverIsSetUp();
      whenCreateIsCalled(X_LOCATION, Y_LOCATION, "A");
      fail();
    }
    catch (Exception e)
    {
      exception = e;
    }
    thenAssertExceptionWasThrown(exception);
  }

  @Test
  public void testRoverCanMove() throws Exception
  {
    givenRoverIsSetUp();
    givenPlateauIsSetUp(5, 6);
    whenCreateIsCalled(X_LOCATION, Y_LOCATION, CARDINAL_POINT);
    verifyRoverExists(mockIRover);
    verifyPlateauExists(mockPlateau);
    thenAssertRoverCanMove(mockRover, mockPlateau);
  }

  @Test
  public void testRoverCannotMove() throws Exception
  {
    givenRoverIsSetUp();
    givenPlateauIsSetUp(3, 6);
    whenCreateIsCalled(X_LOCATION, Y_LOCATION, CARDINAL_POINT);
    verifyRoverExists(mockIRover);
    verifyPlateauExists(mockPlateau);
    thenAssertRoverCannotMove(mockRover, mockPlateau);
  }

  @Test
  public void testRoverMove() throws Exception
  {
    givenRoverIsSetUp();
    whenCreateIsCalled(X_LOCATION, Y_LOCATION, CARDINAL_POINT);
    whenMoveIsCalled(mockRover);
    verifyRoverExists(mockIRover);
    thenAssertRoverMoveIs(X_LOCATION, mockRover.getLocationX());
  }

  @Test
  public void testTurnDirectionValid() throws Exception
  {
    givenRoverIsSetUp();
    whenCreateIsCalled(X_LOCATION, Y_LOCATION, CARDINAL_POINT);
    whenTurnDirectionIsCalled(mockRover, "R");
    verifyRoverExists(mockIRover);
    thenAssertCardinalPointIs("S", mockRover.getCardinalPoint());
  }

  @Test
  public void testTurnDirectionInvalid()
  {
    givenException();
    try
    {
      givenRoverIsSetUp();
      whenCreateIsCalled(X_LOCATION, Y_LOCATION, CARDINAL_POINT);
      whenTurnDirectionIsCalled(mockRover, "Z");
      verifyRoverExists(mockIRover);
      fail();
    }
    catch(Exception e)
    {
      exception = e;
    }
    thenAssertExceptionWasThrown(exception);
  }

  private void givenRoverIsSetUp()
  {
    this.mockIRover = new RoverController();
  }

  private void givenPlateauIsSetUp(int expectedX, int expectedY)
  {
    this.mockPlateau = new Plateau(expectedX, expectedY);
  }

  private void givenException()
  {
    exception = null;
  }

  private void whenCreateIsCalled(int expectedX, int expectedY, String expectedCardinal) throws Exception
  {
    this.mockRover = this.mockIRover.createRover(expectedX, expectedY, expectedCardinal);
  }

  private void whenMoveIsCalled(Rover expectedRover)
  {
    this.mockIRover.moveRover(expectedRover);
  }

  private void whenTurnDirectionIsCalled(Rover expectedRover, String expectedDirection) throws Exception
  {
    this.mockIRover.turnRover(expectedRover, expectedDirection);
  }

  private void verifyRoverExists(RoverController expectedRover)
  {
    assertNotNull(expectedRover);
  }

  private void verifyPlateauExists(Plateau expectedPlateau)
  {
    assertNotNull(expectedPlateau);
  }

  private void thenLocationIs(int expectedValue, int actualValue)
  {
    assertEquals(expectedValue, actualValue);
  }

  private void thenCardinalPointIs(String expectedCardinal, String actualCardinal)
  {
    assertEquals(expectedCardinal, actualCardinal);
  }

  private void thenAssertRoverCanMove(Rover expectedRover, Plateau expectedPlateau)
  {
    assertTrue(this.mockIRover.isAbleToMove(expectedRover, expectedPlateau));
  }

  private void thenAssertRoverCannotMove(Rover expectedRover, Plateau expectedPlateau)
  {
    assertFalse(this.mockIRover.isAbleToMove(expectedRover, expectedPlateau));
  }

  private void thenAssertRoverMoveIs(int expectedX, int actualX)
  {
    assertEquals(expectedX + 1, actualX);
  }

  private void thenAssertCardinalPointIs(String expectedDirection, String actualDirection)
  {
    assertEquals(expectedDirection, actualDirection);
  }

  private void thenAssertExceptionWasThrown(Exception expectedException)
  {
    assertNotNull(expectedException);
  }
}
