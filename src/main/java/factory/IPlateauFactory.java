package factory;

import model.Plateau;

/** Interface class to represent the Plateau Factory object
 * This class effectively acts as the factory for different types of plateaus
 */
public interface IPlateauFactory
{
  /** Method that creates a Plateau object with the given locations x and y
   * @param locationX The x location of the plateau
   * @param locationY The y location of the plateau
   * @return Plateau object with given parameters as location x and y
   * @throws Exception error when x or y are invalid
   */
  Plateau create(int locationX, int locationY) throws Exception;
}
