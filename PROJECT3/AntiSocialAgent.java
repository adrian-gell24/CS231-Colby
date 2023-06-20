    /**
 * Author: Adrian Gellert
 * Date: October 11, 2022
 * File: AntiSocialAgent.java
 * Section: Lecture A, Lab D
 * Project: Agent-Based Simulations
 * Course: CS231 
 */

import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class AntiSocialAgent extends Agent{

    /**
     * Whether the Agent moved during its last updateState.
     */
    boolean moved;

    /**
     * The space within which the Agent considers other agents a neighbor.
     */
    int radius;

    /**
     * Constructs an anti-social agent at a location specified by the super class Agent 
     * and with the specified radius.
     * 
     * @param x0 a double to specify the AntiSocialAgent's x-position
     * @param y0 a double to specify the AntiSocialAgent's y-position
     * @param radius an integer to specify the neighborly space of AntiSocialAgent.
     */
    public AntiSocialAgent(double x0, double y0, int radius) {
        super( x0, y0 );
        // remainder of constructor code
        this.radius = radius;
    }

    /**
     * Sets the current radius of AntiSocialAgent to a new radius.
     * 
     * @param radius an integer to specify the neighborly space of AntiSocialAgent.
     */
    public void setRadius(int radius){
        this.radius = radius;
    }

    /**
     * Returns the current radius of AntiSocialAgent
     * 
     * @return AntiSocialAgent's neighbor boundary.
     */
    public int getRadius(){
        return this.radius;
    }

    /**
     * Draws a circle of radius 5 (i.e. it fits in a 10x10 box) at the Agent's location.
     * Draw the agent light red if it moved during the last updateState
     * Otherwise, draw it dark red.
     * 
     * @param g a Graphics object that can change colors and locations and draw on the Landscape.
     */
    public void draw(Graphics g){
        if(!moved) g.setColor(new Color(255, 0, 0));
        else g.setColor(new Color(187, 0, 0));
    
        g.fillOval((int) getX(), (int) getY(), 5, 5);
    }

    /**
     * Calculates the number of neighbors that an AntiSocialAgent has.
     * Move the Agent if there is more than 1 neighbor within its radius.
     * Keep track of whether agent has moved.
     * 
     * @param scape Landscape object that holds height, width, and added agents information.
     */
    public void updateState(Landscape scape){
        if (scape.getNeighbors(radius, radius, radius).size() > 1){
            Random ran = new Random();
            int x_change = ran.nextInt(-10, 10);
            int y_change = ran.nextInt(-10, 10);
            if (this.getX() + x_change < scape.getWidth() && this.getY() + y_change < scape.getHeight()
                && (this.getX() + x_change) > 0 && (this.getY() + y_change) > 0){
                this.setX(this.getX() + x_change);
                this.setY(this.getY() + y_change);
                moved = true;
            }else{
                moved = false;
            }
        }
    }
}
