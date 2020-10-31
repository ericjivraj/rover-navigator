package factory;

import model.Plateau;

/** Class to represent a given type of Plateau, in this case, an Unbounded Plateau
 * An Unbounded Plateau is a type of Plateau that has no specific boundaries
 */
public class UnboundedPlateauFactory implements IPlateauFactory
{
  public Plateau create(int locationX, int locationY) throws Exception
  {
    if(locationX < 0 || locationY < 0)
    {
      throw new Exception("Invalid Coordinates for Plateau! Coordinates need to be positive!");
    }

    return new Plateau(locationX, locationY);
  }
}
