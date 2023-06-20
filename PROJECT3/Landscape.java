    /**
 * Author: Adrian Gellert
 * Date: October 11, 2022
 * File: Landscape.java
 * Section: Lecture A, Lab D
 * Project: Agent-Based Simulations
 * Course: CS231 
 */

import java.lang.Math;
import java.awt.Graphics;

public class Landscape {

    /**
     * The width and height of the drawing window.
     */
    private int width;
    private int height;

    /**
     * A linked list containing agents within the Landscape.
     */
    private LinkedList<Agent> agents;

    /**
     * A constructor that sets the width and height fields, and initializes the agent list.
     * @param w an integer to specify the width of the Landscape drawing window.
     * @param h an integer to specify the height of the Landscape drawing window.
     */
    public Landscape(int w, int h){
        this.width = w;
        this.height = h;
        agents = new LinkedList<>();
    }

    /**
     * Returns the current Landscape width.
     * @return width
     */
    public int getWidth(){
        return this.width;
    }

    /**
     * Returns the current Landscape height.
     * @return height
     */
    public int getHeight(){
        return this.height;
    }

    /**
     * Sets a new width for the Landscape drawing window.
     * @param newWidth an integer to specify the new Landscape width.
     */
    public void setWidth(int newWidth){
        this.width = newWidth;
    }

    /**
     * Sets a new height for the Landscape drawing window.
     * @param newHeight an integer to specify the new Landscape height.
     */
    public void setHeight(int newHeight){
        this.height = newHeight;
    }

    /**
     * Inserts an agent at the beginning of its list of agents.
     * @param a an Agent object created for the Landscape.
     */
    public void addAgent( Agent a ){
        agents.add(a);
    }

    /**
     * returns a String representing the Landscape.
     */
    public String toString(){
        return "" + agents.toString();
    }

    /**
     * Returns a list of the Agents within radius distance of the location x0, y0.
     * @param x0 a double specifying the initial x-position of the Agent before it moved.
     * @param y0 a double specifying the inition y-position of the Agent before it moved.
     * @param radius a double specifying the space within which other agents are neighbors.
     * @return
     */
    public LinkedList<Agent> getNeighbors(double x0, double y0, double radius){
        LinkedList<Agent> neighbors = new LinkedList<>();

        for (Agent agent : agents){
            double xdist_squared = Math.pow(agent.getX()-x0, 2);
            double ydist_squared = Math.pow(agent.getY()-y0, 2);

            if (Math.sqrt(xdist_squared+ydist_squared) <= radius && agent.getX() != x0 && agent.getY() != y0){
                neighbors.add(agent);
            }
        }

        return neighbors;
    }

    /**
     * Calls the draw method of all the agents on the Landscape.
     * @param g a Graphics object that can change colors and locations and draw on the Landscape.
     */
    public void draw(Graphics g){
        for (Agent agent : agents){
            agent.draw(g);
        }
    }

    /**
     * Updates the state of each agent in the agents LinkedList.
     */
    public void updateAgents(){
        for (Agent agent : agents){
            agent.updateState(this);
        }
    }
}
