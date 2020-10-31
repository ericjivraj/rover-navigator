package service;

import java.util.Map;

import model.Rover;

/** Interface class to represent the Rover Navigator object
 * This class effectively represents the object that handles the NASA commands
 */
public interface IRoverNavigator
{
  /** Method to simulate NASA's input commands
   * @param plateauX The Plateau's x location
   * @param plateauY The Plateau's y location
   * @return Rover object state (x, y, cardinalPoint) after the commands
   * throws Exception if an error occurs
   */
  void process(Map<Rover, String> rovers, int plateauX, int plateauY) throws Exception;
}
