package model;

/** Model class for the Plateau object
 * This entity class effectively represents the grid
 */
public class Plateau
{
  private int locationX;
  private int locationY;

  /** Constructor that creates an instance object of the Plateau with the
   * specified x location and y location
   * @param locationX The x location of the Plateau
   * @param locationY The y location of the Plateau
   */
  public Plateau(int locationX, int locationY)
  {
    this.locationX = locationX;
    this.locationY = locationY;
  }

  /** Method that gets the x location of the plateau
   * @return An int that represents the x location of the plateau
   */
  public int getLocationX()
  {
    return locationX;
  }

  /** Method that sets the x location of the plateau
   * @param locationX The Plateau's x location
   */
  public void setLocationX(int locationX)
  {
    this.locationX = locationX;
  }

  /** Method that gets the y location of the plateau
   * @return An int that represents the y location of the plateau
   */
  public int getLocationY()
  {
    return locationY;
  }

  /** Method that sets the y location of the plateau
   * @param locationY The Plateau's y location
   */
  public void setLocationY(int locationY)
  {
    this.locationY = locationY;
  }
}