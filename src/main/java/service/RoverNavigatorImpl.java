package service;

import java.util.Map;

import factory.IPlateauFactory;
import controller.RoverController;
import model.Plateau;
import model.Rover;

/**
 * Class that represents the Rover Navigator implementation
 */
public class RoverNavigatorImpl implements IRoverNavigator
{
  private RoverController rover;
  private IPlateauFactory plateauFactory;
  private static final char MOVE = 'M';

  public RoverNavigatorImpl(RoverController rover, IPlateauFactory plateauFactory)
  {
    this.rover = rover;
    this.plateauFactory = plateauFactory;
  }

  public void process(Map<Rover, String> rovers, int plateauX, int plateauY) throws Exception
  {
    Plateau plateau = this.plateauFactory.create(plateauX, plateauY);
    for(Map.Entry<Rover, String> rover : rovers.entrySet())
    {
      handleCommands(plateau, rover.getKey(), rover.getValue());
    }
  }

  /** Method that handles the commands
   * @param plateau The Plateau object
   * @param rover The Rover object
   * @param command The given command
   * @return Rover object after commands
   * @throws Exception if an error gets thrown
   */
  private Rover handleCommands(Plateau plateau, Rover rover, String command) throws Exception
  {
    for (int i = 0; i < command.length(); i++)
    {
      if (command.charAt(i) == MOVE)
      {

        if (this.rover.isAbleToMove(rover, plateau))
        {
          rover = this.rover.moveRover(rover);
        }

        }

        else
        {
          rover = this.rover.turnRover(rover, String.valueOf(command.charAt(i)));
        }
    }

    return rover;
  }
}
