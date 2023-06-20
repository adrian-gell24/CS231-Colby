    /**
 * Author: Adrian Gellert
 * Date: October 11, 2022
 * File: Agent.java
 * Section: Lecture A, Lab D
 * Project: Agent-Based Simulations
 * Course: CS231 
 */

import java.awt.Graphics;

public abstract class Agent {

    /**
     * The x and y position of Agent
     */
    private double x_pos;
    private double y_pos;

    /**
     * Contstructs the initial Agent position.
     * 
     * @param x0 a double to specify the initial x-axis location of the Agent
     * @param y0 a double to specify the initial y-axis location of the Agent
     */
    public Agent(double x0, double y0){
        this.x_pos = x0;
        this.y_pos = y0;
    }

    /**
     * Returns the current x position of the Agent.
     * 
     * @return the Agent's x-position
     */
    public double getX(){
        return this.x_pos;
    }

    /**
     * Returns the current y position of the Agent.
     * 
     * @return the Agent's y-position
     */
    public double getY(){
        return this.y_pos;
    }

    /**
     * Sets a new x position for the Agent.
     * 
     * @param newX a double to specify the new x-position value
     */
    public void setX( double newX ){
        this.x_pos = newX;
    }

    /**
     * Sets a new y position for the Agent.
     * 
     * @param newY a double to specify the new y-position value
     */
    public void setY( double newY ){
        this.y_pos = newY;
    }

    /**
     * Returns a string representation of the Agent's location.
     * 
     * @return (x-position, y-position)
     */
    public String toString(){
        return "(" + this.x_pos + ", " + this.y_pos + ")";
    }

    // cannot specify in parent class, so empty
    public abstract void updateState( Landscape scape );
    public abstract void draw(Graphics g);
}
