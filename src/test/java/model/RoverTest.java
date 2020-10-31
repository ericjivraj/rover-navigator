package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class RoverTest
{
  private Rover mockRover;
  private static final int X_LOCATION = 1;
  private static final int Y_LOCATION = 2;
  private static final String CARDINAL_POINT = "N";

  @Test
  public void createRover()
  {
    givenRoverIsSetUp(X_LOCATION, Y_LOCATION, CARDINAL_POINT);
    verifyRoverHasCorrectLocation(mockRover.getLocationX(), X_LOCATION);
    verifyRoverHasCorrectLocation(mockRover.getLocationY(), Y_LOCATION);
    verifyRoverHasCorrectCardinalPoint(mockRover.getCardinalPoint(), CARDINAL_POINT);
  }

  private void givenRoverIsSetUp(int expectedX, int expectedY, String expectedCardinal)
  {
    mockRover = new Rover(expectedX, expectedY, expectedCardinal);
  }

  private void verifyRoverHasCorrectLocation(int expectedValue, int actualValue)
  {
    assertEquals(expectedValue, actualValue);
  }

  private void verifyRoverHasCorrectCardinalPoint(String expectedCardinal, String actualCardinal)
  {
    assertEquals(expectedCardinal, actualCardinal);
  }
}
