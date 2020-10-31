package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

public class PlateauTest
{
  private Plateau mockPlateau;
  private static final int X_LOCATION = 5;
  private static final int Y_LOCATION = 5;

  @Test
  public void testCreatePlateau()
  {
    givenPlateauIsSetUp(X_LOCATION, Y_LOCATION);
    verifyPlateauExists(mockPlateau);
    verifyPlateauHasCorrectLocation(mockPlateau.getLocationX(), X_LOCATION);
    verifyPlateauHasCorrectLocation(mockPlateau.getLocationY(), Y_LOCATION);
  }

  private void givenPlateauIsSetUp(int expectedX, int expectedY)
  {
    mockPlateau = new Plateau(expectedX, expectedY);
  }

  private void verifyPlateauExists(Plateau expectedPlateau)
  {
    assertNotNull(expectedPlateau);
  }

  private void verifyPlateauHasCorrectLocation(int expectedLocation, int actualLocation)
  {
    assertEquals(expectedLocation, actualLocation);
  }
}