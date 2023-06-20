    /**
 * Author: Adrian Gellert
 * Date: October 11, 2022
 * File: AgentSimulaiton.java
 * Section: Lecture A, Lab D
 * Project: Agent-Based Simulations
 * Course: CS231 
 */

/**
 * Agent Simulation class based on Landscape Display that 
 * calls the advance() method of the Landscape, 
 * calls the repaint() method of LandscapeDisplay, 
 * and calls Thread.sleep( 250 ) to pause for 250 milliseconds before starting the next iteration
 */

import java.util.Random;
public class AgentSimulation {
    public static void main(String[] args) throws InterruptedException {

        // making a new landscape with an initial width and height of 0.
        Landscape scape = new Landscape(0, 0);

        // checking for command line arguments
        if (args.length < 4){
            System.out.println("Please provide integer values for: width, height, numAgents, and iterations");
        }
        else{

            // setting landscape width and height to new values specified by command line arguments
            scape.setWidth(Integer.parseInt(args[0]));
            scape.setHeight(Integer.parseInt(args[1]));

            // Creating a random number generator
            Random gen = new Random();

            // Creates args[2] SocialAgents and args[2] AntiSocialAgents for (int i = 0; i < args[2]; i++)
            for (int i = 0; i < Integer.parseInt(args[2]); i++) {
                scape.addAgent(new SocialAgent(gen.nextDouble() * scape.getWidth(),
                        gen.nextDouble() * scape.getHeight(),
                        15));
                scape.addAgent(new AntiSocialAgent(gen.nextDouble() * scape.getWidth(),
                        gen.nextDouble() * scape.getHeight(),
                        25));
            }

            // Initializing AgentSimulation initial setup using scape field
            LandscapeDisplay display = new LandscapeDisplay(scape);

            // Continuing simulation with args[3] number of time steps
            // Saving image of each frame
            for (int i = 0; i < Integer.parseInt(args[3]); i++){
                Thread.sleep(100);
                scape.updateAgents();
                display.repaint();
                display.saveImage( "data/life_frame_" + String.format( "%03d", i ) + ".png" );
            }
        }
    }
}
