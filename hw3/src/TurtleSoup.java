package turtle;

/* Copyright (c) 2007-2014 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */

/*
 * Abby Danger, Scott Cogan, Andrew Grimes, Jack Paul
 * Homework 3
 * CSE 211
 * Professor Alomari
 */

import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * TASK #1: drawSquare
     * 
     * For loop iterates 4 times to draw a side of the square
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        for(int i = 0; i < 4; i++){
            turtle.forward(sideLength);
            turtle.turn(90);
        }
    }

    /**
     * TASK #2: calculateRegularPolygonAngle()
     * 
     * Given number of sides is converted to a double in order to determine
     * inside angles of a regular polygon
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        double numSides = sides;
        return (numSides - 2) * 180 / numSides;
    }

    /**
     * TASK #2: calculatePolygonSidesFromAngle()
     * 
     * Variable sides is create and casted as an integer that determines
     * the number of sides given the size of interior angles of a regular polygon.
     * @param angle size of interior angles in degrees
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
    		int sides = 0;   
        sides = (int) (360.0 / (180.0 - angle) + .001); //rounding error
        return sides;
    }

    /**
     * TASK #2: drawRegularPolygon()
     * 
     * For loop iterates based on the number of sides the polygon has
     * to draw a side the polygon
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
    		for(int i = 0; i < sides; i++) {
    			turtle.forward(sideLength);
	        	turtle.turn(180 - calculateRegularPolygonAngle(sides));
	    }
    }

    /**
     * TASK #3: calculateHeadingToPoint()
     * 
     * Calculates the heading towards the target point using atan2
     * when given the current direction, current location,
     * and a target location
     * @param currentHeading current direction as clockwise from north
     * @param currentX currentY current location
     * @param targetX targetY target point
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360.
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
        int resultY = targetY - currentY;
        int resultX = targetX - currentX;
        double northAngle = (Math.atan2(resultX, resultY) * 180 / Math.PI);
        double angle = northAngle - currentHeading;
        if(angle < 0) {
            angle += 360;
        }
        return angle;
    }

    /**
     * TASK #3: calculateHeadings()
     * 
     * For loop iterates through each list to assign a value to a parameter of the
     * calculateHeadingToPoint() method and updates the array
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size (# of points) - 1.
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
        List<Double> coordChanges = new ArrayList<Double>();
        int numCoords = xCoords.size();
        double currentHeading = 0;
        for(int i = 1; i < numCoords; i++){
            double adjust = calculateHeadingToPoint(currentHeading, xCoords.get(i-1), yCoords.get(i-1), xCoords.get(i), xCoords.get(i));
            currentHeading += adjust;
            coordChanges.add(adjust);
        }
        return coordChanges;
    }

    /**
     * TASK #4: drawPersonalArt()
     * 
     * Four different for loops are used to draw 4 star figured-shapes
     * that were created using a series of forward() and turn() statements
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    		// Draws blue star
        turtle.color(PenColor.BLUE);
        for(int i = 0; i < 20; i++) {
            turtle.forward(20);
            turtle.turn(30);
            turtle.forward(40);
            turtle.turn(-160);
            turtle.forward(100);
            turtle.turn(30);
        }
        
        // Draws orange star
        turtle.color(PenColor.ORANGE);
        turtle.forward(-200);
        for(int i = 0; i < 20; i++) {
            turtle.forward(10);
            turtle.turn(30);
            turtle.forward(20);
            turtle.turn(-160);
            turtle.forward(50);
            turtle.turn(30);
        }
        
        // Draws red star
        turtle.color(PenColor.RED);
        turtle.turn(135);
        turtle.forward(100);
        for(int i = 0; i < 20; i++) {
            turtle.forward(5);
            turtle.turn(30);
            turtle.forward(10);
            turtle.turn(-160);
            turtle.forward(25);
            turtle.turn(30);
        }
        
        // Draws pink star
        turtle.color(PenColor.PINK);
        turtle.turn(-150);
        turtle.forward(150);
        for(int i = 0; i < 20; i++) {
            turtle.forward(13);
            turtle.turn(30);
            turtle.forward(27);
            turtle.turn(-160);
            turtle.forward(67);
            turtle.turn(30);
        }
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        // TASK #1
        drawSquare(turtle, 80);
        
        // TASK #2
        drawRegularPolygon(turtle, 5, 80);

        // TASK #4
        drawPersonalArt(turtle);

        
        
        // draw the window
        turtle.draw();
    }

}
