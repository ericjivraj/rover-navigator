package factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;

import model.Plateau;

public class IPlateauFactoryTest
{
  private IPlateauFactory mockIPlateau;
  private Plateau mockPlateau;
  private Exception exception;
  private static final int X_LOCATION = 8;
  private static final int Y_LOCATION = 2;
  private static final int X_LOCATION_INVALID = -8;
  private static final int Y_LOCATION_INVALID = -2;
  private static final int X_MAXIMUM = 9;
  private static final int Y_MAXIMUM = 9;

  @Test
  public void testUnBoundedPlateauExists()
  {
    givenUnBoundedPlateauIsSetUp();
    verifyPlateauExists();
  }

  @Test
  public void testBoundedPlateauExists()
  {
    givenBoundedPlateauIsSetUp(X_MAXIMUM, Y_MAXIMUM);
    verifyPlateauExists();
  }

  @Test
  public void testCreateValidUnboundedPlateau() throws Exception
  {
    givenUnBoundedPlateauIsSetUp();
    whenCreateIsCalled(X_LOCATION, Y_LOCATION);
    verifyPlateauExists();
    verifyPlateauHasCorrectSetUp(mockPlateau.getLocationX(), X_LOCATION);
    verifyPlateauHasCorrectSetUp(mockPlateau.getLocationY(), Y_LOCATION);
  }

  @Test
  public void testCreateValidBoundedPlateau() throws Exception
  {
    givenBoundedPlateauIsSetUp(X_MAXIMUM, Y_MAXIMUM);
    whenCreateIsCalled(X_LOCATION, Y_LOCATION);
    verifyPlateauExists();
    verifyPlateauHasCorrectSetUp(mockPlateau.getLocationX(), X_LOCATION);
    verifyPlateauHasCorrectSetUp(mockPlateau.getLocationY(), Y_LOCATION);
  }

  @Test
  public void testCreateInvalidUnboundedPlateau()
  {
    givenException();
    try
    {
      givenUnBoundedPlateauIsSetUp();
      whenCreateIsCalled(X_LOCATION_INVALID, Y_LOCATION_INVALID);
      fail();
    }
    catch(Exception e)
    {
      exception = e;
    }
    thenExceptionWasThrown(exception);
  }

  @Test
  public void testCreateInvalidBoundedPlateau()
  {
    givenException();
    try
    {
      givenBoundedPlateauIsSetUp(11, 12);
      whenCreateIsCalled(X_LOCATION_INVALID, Y_LOCATION_INVALID);
      fail();
    }
    catch(Exception e)
    {
      exception = e;
    }
    thenExceptionWasThrown(exception);
  }

  private void givenUnBoundedPlateauIsSetUp()
  {
    this.mockIPlateau = new UnboundedPlateauFactory();
  }

  private void givenBoundedPlateauIsSetUp(int expectedMaximumX, int expectedMaximumY)
  {
    this.mockIPlateau = new BoundedPlateauFactory(expectedMaximumX, expectedMaximumY);
  }

  private void givenException()
  {
    exception = null;
  }

  private void whenCreateIsCalled(int expectedX, int expectedY) throws Exception
  {
    this.mockPlateau = mockIPlateau.create(expectedX, expectedY);
  }

  private void verifyPlateauExists()
  {
    assertNotNull(mockIPlateau);
  }

  private void verifyPlateauHasCorrectSetUp(int expectedValue, int actualValue)
  {
    assertEquals(expectedValue, actualValue);
  }

  private void thenExceptionWasThrown(Exception expectedException)
  {
    assertNotNull(expectedException);
  }
}