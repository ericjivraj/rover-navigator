import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import factory.IPlateauFactory;
import controller.RoverController;
import factory.UnboundedPlateauFactory;
import model.Rover;
import service.IRoverNavigator;
import service.RoverNavigatorImpl;

/**
 * This class is used to run the application program for the Mars Rover Problem
 */
public class RoverNavigatorApplication {

  /**
   * Main method to run the application
   * @param args arguments
   */
  public static void main(String[] args) throws Exception
  {
    Scanner inputScanner = new Scanner(System.in);
    System.out.print("Welcome to the Mars Rover Navigator System \n");
    System.out.print("Please enter coordinate x for the Plateau: ");
    int plateauX = inputScanner.nextInt();
    System.out.print("Please enter coordinate y for the Plateau: ");
    int plateauY = inputScanner.nextInt();

    List<Rover> roverList = new ArrayList<>();
    RoverController roverController = new RoverController();
    IPlateauFactory plateauFactory = new UnboundedPlateauFactory();
    IRoverNavigator roverNavigator = new RoverNavigatorImpl(roverController, plateauFactory);

    int i = 0;
    while (true)
    {

      System.out.print("Please enter coordinate x for the Rover: ");
      int roverX = inputScanner.nextInt();
      System.out.print("Please enter coordinate y for the Rover: ");
      int roverY = inputScanner.nextInt();
      System.out.print("Please enter the cardinal point for the Rover: ");
      String cardinalPoint = inputScanner.next();
      System.out.print("Please enter the command for the Rover: ");
      String command = inputScanner.next();

      final Rover rover = roverController.createRover(roverX, roverY, cardinalPoint);
      roverList.add(rover);
      Map<Rover, String> commandByRover = new HashMap<>();
      commandByRover.put(roverList.get(i), command);

      roverNavigator.process(commandByRover, plateauX, plateauY);

      System.out.print(roverList.get(i).getLocationX());
      System.out.print(" ");
      System.out.print(roverList.get(i).getLocationY());
      System.out.print(" ");
      System.out.println(roverList.get(i).getCardinalPoint());

      i++;
    }
  }
}