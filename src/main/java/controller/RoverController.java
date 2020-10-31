package controller;

import model.Plateau;
import model.Rover;

/** This Class represents the Rover Controller Object
 * It acts as the controller for the Rover object
 */
public class RoverController
{
  private static final String NORTH = "N";
  private static final String EAST = "E";
  private static final String SOUTH = "S";
  private static final String WEST = "W";
  private static final String LEFT = "L";
  private static final String RIGHT = "R";

  /** Method that creates a Rover object
   * @param locationX The x position of the Rover
   * @param locationY The y position of the Rover
   * @param cardinalPoint The cardinal point of the Rover
   * @return Rover object with the given locations and cardinal point
   * @throws Exception if the Rover object attempted to create is invalid (invalid coordinates or cardinal point)
   */
  public Rover createRover(int locationX, int locationY, String cardinalPoint) throws Exception
  {
    if(locationX <  0 || locationY < 0)
    {
      throw new Exception("Invalid Coordinates for Rover! Coordinates need to be positive!");
    }

    else if(!NORTH.equals(cardinalPoint) && !EAST.equals(cardinalPoint) && !SOUTH.equals(cardinalPoint) && !WEST.equals(cardinalPoint))
    {
      throw new Exception("Invalid Robot's Cardinal Point! Cardinal Point must either be 'N', 'E', 'S', or 'W'");
    }

    return new Rover(locationX, locationY, cardinalPoint);
  }

  /** Method that checks if the Rover object is able to move
   * @param rover Rover object
   * @param plateau Plateau object
   * @return true if the Rover is able to move within the Plateau, false otherwise
   */
  public boolean isAbleToMove(Rover rover, Plateau plateau)
  {
    if(NORTH.equals(rover.getCardinalPoint()) && rover.getLocationY() + 1 > plateau.getLocationY())
    {
      return false;
    }

    else if(EAST.equals(rover.getCardinalPoint()) && rover.getLocationX() + 1 > plateau.getLocationX())
    {
      return false;
    }

    else if(SOUTH.equals(rover.getCardinalPoint()) && rover.getLocationY() - 1 < 0)
    {
      return false;
    }

    else if(WEST.equals(rover.getCardinalPoint()) && rover.getLocationX() + 1 < 0)
    {
      return false;
    }

    return true;
  }

  /** Method that moves the Rover object
   * @param rover Rover object before moving
   * @return Rover object after moving
   */
  public Rover moveRover(Rover rover)
  {
    switch(rover.getCardinalPoint())
    {
      case NORTH:
        rover.setLocationY(rover.getLocationY() + 1);
        break;
      case EAST:
        rover.setLocationX(rover.getLocationX() + 1);
        break;
      case SOUTH:
        rover.setLocationY(rover.getLocationY() - 1);
        break;
      case WEST:
        rover.setLocationX(rover.getLocationX() - 1);
        break;
    }

    return rover;
  }

  /** Method that turns the Rover object in to a specific direction
   * @param rover
   * @param turnDirection
   * @return Rover object after turning direction
   * @throws Exception if the direction is invalid
   */
  public Rover turnRover(Rover rover, String turnDirection) throws Exception
  {
    if(LEFT.equals(turnDirection))
    {
      rover.setCardinalPoint(turnLeft(rover.getCardinalPoint()));
    }

    else if(RIGHT.equals(turnDirection))
    {
      rover.setCardinalPoint(turnRight(rover.getCardinalPoint()));
    }

    else
    {
      throw new Exception("Invalid Turn Direction! It must either be 'L' or 'R'");
    }

      return rover;
  }

  /** Method for turning to the left direction
   * @param turnDirection the turning left direction
   * @return the facing cardinal point after turning turning left
   */
  private String turnLeft(String turnDirection)
  {
    if(NORTH.equals(turnDirection))
    {
      return WEST;
    }

    else if(WEST.equals(turnDirection))
    {
      return SOUTH;
    }

    else if(SOUTH.equals(turnDirection))
    {
      return EAST;
    }

    return NORTH;
  }

  /** Method for turning to the right direction
   * @param turnDirection the turning right direction
   * @return the facing cardinal point after turning turning right
   */
  private String turnRight(String turnDirection)
  {
    if(NORTH.equals(turnDirection))
    {
      return EAST;
    }

    else if(EAST.equals(turnDirection))
    {
      return SOUTH;
    }

    else if(SOUTH.equals(turnDirection))
    {
      return WEST;
    }

    return NORTH;
  }
}