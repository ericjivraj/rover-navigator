package factory;

import model.Plateau;

/** Class to represent a given type of Plateau, in this case, a Bounded Plateau
 * A Bounded Plateau is a type of Plateau that has specific boundaries
 */
public class BoundedPlateauFactory implements IPlateauFactory
{
  private int maximumX;
  private int maximumY;

  public BoundedPlateauFactory(int maximumX, int maximumY)
  {
    this.maximumX = maximumX;
    this.maximumY = maximumY;
  }

  public Plateau create(int locationX, int locationY) throws Exception
  {
    if(locationX < 0 || locationY < 0)
    {
      throw new Exception("Invalid Coordinates for Plateau! Coordinates need to be positive!");
    }

    if(locationX > maximumX || locationY > maximumY)
    {
      throw new Exception("Invalid Coordinates for Plateau! Coordinates exceed boundaries!");
    }

    return new Plateau(locationX, locationY);
  }
}
