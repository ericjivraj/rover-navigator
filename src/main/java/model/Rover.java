package model;

/** Model class for the Rover object
 * This entity class effectively represents mars rover robot
 */
public class Rover
{
  private int locationX;
  private int locationY;
  private String cardinalPoint;

  /** Constructor which creates an instance object of the Rover with the
   * specified x location, y location, and cardinal point
   * @param locationX The x location of the Rover
   * @param locationY The y location of the Rover
   * @param cardinalPoint The cardinal point of the Rover
   */
  public Rover(int locationX, int locationY, String cardinalPoint)
  {
    this.locationX = locationX;
    this.locationY = locationY;
    this.cardinalPoint = cardinalPoint;
  }

  /** Method that gets the x location of the rover
   * @return An int that represents the x location of the rover
   */
  public int getLocationX()
  {
    return locationX;
  }

  /** Method that sets the x location of the rover
   * @param locationX The Rover's x location
   */
  public void setLocationX(int locationX)
  {
    this.locationX = locationX;
  }

  /** Method that gets the y location of the rover
   * @return An int that represents the y location of the rover
   */
  public int getLocationY()
  {
    return locationY;
  }

  /** Method that sets the y location of the rover
   * @param locationY The Rover's y location
   */
  public void setLocationY(int locationY)
  {
    this.locationY = locationY;
  }

  /** Method that gets the cardinal point of the rover
   * @return A char that represents the cardinal point of the rover
   */
  public String getCardinalPoint()
  {
    return cardinalPoint;
  }

  /** Method that sets the cardinal point of the rover
   * @param cardinalPoint The Rover's cardinal point
   */
  public void setCardinalPoint(String cardinalPoint)
  {
    this.cardinalPoint = cardinalPoint;
  }
}