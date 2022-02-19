package com.ncfsofteng.artprogram;

/**
 * DrawingWindow.java
 * @author AdamAnderson
 *
 * A DrawingWindow is a window used by Processing that is paired with a MenuWindow
 * to allow for easy adjustment of parameters during the execution of a Processing sketch.
 * Parameters are stored in a Map that assigns parameter names to doubles representing their
 * value. The Parameters map is shared by the DrawingWindow and MenuWindow, so the
 * DrawingWindow will update one frame after values are changed in the MenuWindow
 *
 * To use DrawingWindows in your own creation, simply change the draw() method so the
 * DrawingWindow will perform whatever actions you desire. The methods
 * setup(), and draw() are located at the bottom of the class source file.
 * By default, they contain code for relevant to the ExampleDriver, but you can change it
 * to make your own Processing sketches with menus!
 */



import javax.swing.*;
import java.util.Map;

public class DrawingWindow extends ProcessingWindow {
    private Map<String, Double> parameters;
    private MenuWindow menu;
    private static final String DEFAULT_TITLE = "Drawing Window";

    /**
     * Base constructor for a DrawingWindow. Takes in the window width and height as parameters
     * and sets the size of the DrawingWindow accordingly, as well as a String title for the
     * DrawingWindow, a Map of parameters, and the dimensions of the MenuWindow
     * DrawingWindow.
     * @param width
     * @param height
     * @param title
     * @param parameters
     */
    public DrawingWindow(int width, int height, String title, Map<String, Double> parameters, int menuWidth, int menuHeight) {
        super(width, height, title);
        this.parameters = parameters;
        createMenu(menuWidth, menuHeight);
    }

    /**
     * Constructor for DrawingWindow where only width, height, title, and menu
     * dimensions are given
     * @param width
     * @param height
     */
    public DrawingWindow(int width, int height, Map<String, Double> parameters, int menuWidth, int menuHeight) {
        this(width, height, DEFAULT_TITLE, parameters, menuWidth, menuHeight);
    }

    /**
     * Constructor for DrawingWindow for window dimensions and a map
     * containing parameters
     * @param width
     * @param height
     * @param parameters
     */
    public DrawingWindow(int width, int height, Map<String, Double> parameters) {
        this(width, height, parameters, width, height);
    }

    /**
     * Creates the DrawingWindow as well as its accompanying MenuWindow.
     */
    @Override
    public void createWindow() {
        super.createWindow();
        menu.createWindow();
    }

    /*
     * Helper method creates a MenuWindow object for the parameters stored by
     * the DrawingWindow. The input parameters represent the dimensions
     * of the menu to be drawn
     */
    private void createMenu(int menuWidth, int menuHeight) {
        menu = new MenuWindow(menuWidth, menuHeight, "Menu for " + title, parameters);
    }


    ///////////////////////////////////////////////////////////////////////////////////////
    /* Below are methods that customize the DrawingWindow's appearance and functionality.*/
    ///////////////////////////////////////////////////////////////////////////////////////

    /*
     * Initialize the DrawingWindow the same way we would a normal Processing sketch
     */
    public void setup() {
        background(255);
        fill(0);
        noStroke();
        rectMode(CENTER);
    }
    /*
     * The draw function should be updated so that the DrawingWindow creates
     * whatever the user desires.
     */
    public void draw() {

        //Below is an example to demo the MenuWindow based on the
        // parameters set in the ExampleDriver.
        float red = parameters.get("Red").floatValue();
        float green = parameters.get("Green").floatValue();
        float blue = parameters.get("Blue").floatValue();
        float white = parameters.get("White").floatValue();
        float grey = parameters.get("Grey").floatValue();
        float black = parameters.get("Black").floatValue();
        float yellow = parameters.get("Yellow").floatValue();
        float cyan = parameters.get("Cyan").floatValue();
        float orange = parameters.get("Orange").floatValue();
        float magenta = parameters.get("Magenta").floatValue();
        float brown = parameters.get("Brown").floatValue();
        float circle = parameters.get("Circle").floatValue();
        float square = parameters.get("Square").floatValue();
        float Rectangle = parameters.get("Rectangle").floatValue();
        float triangle = parameters.get("Triangle").floatValue();
        float brushResize = parameters.get("Brush Size").floatValue();
        float canvasResize = parameters.get("Canvas Size").floatValue();
        float save = parameters.get("Save").floatValue();
        background(255);
        ellipse(250, 250, 100*2, 100*2);
//<<<<<<< HEAD
        if(red == 1.0)
        {
            fill(255, 0, 0);
            parameters.put("Green",  0.0);
            parameters.put("Blue",  0.0);
            parameters.put("Brown", 0.0);
        }
        if(green == 1.0)
        {
            fill(0, 255, 0);
            parameters.put("Blue",  0.0);
            parameters.put("Red",  0.0);
            parameters.put("Brown", 0.0);
        }
        if(blue == 1.0)
        {
            fill(0, 0, 255);
            parameters.put("Green",  0.0);
            parameters.put("Red",  0.0);
            parameters.put("Brown", 0.0);
        }
//=======
        if(canvasResize == 1.0){
            canvasResize = (float) 0.0;
            int canvasWidth;
            int canvasLength;
            String input = JOptionPane.showInputDialog("Please enter the pixel width you'd like your canvas to be!");
            String input2 = JOptionPane.showInputDialog("Please enter the pixel height you'd like your canvas to be!");
            // Try to set the newValue to the user's input
            try {
                canvasWidth = Integer.parseInt(input);
                canvasLength = Integer.parseInt(input2);
            }
            catch (NumberFormatException e) {
                // Canvas launches with default sizes if given bad input
                canvasWidth = 500;
                canvasLength = 500;
            }

        }
        if(red == 1.0){
            //setColor(0);
            parameters.put("Green", 0.0);
            parameters.put("Blue", 0.0);
            parameters.put("White", 0.0);
            parameters.put("Grey", 0.0);
            parameters.put("Black", 0.0);
            parameters.put("Yellow", 0.0);
            parameters.put("Cyan", 0.0);
            parameters.put("Orange", 0.0);
            parameters.put("Magenta", 0.0);
            parameters.put("Brown", 0.0);
        }
        if(green == 1.0){
            //setColor(1);
            parameters.put("Red", 0.0);
            parameters.put("Blue", 0.0);
            parameters.put("White", 0.0);
            parameters.put("Grey", 0.0);
            parameters.put("Black", 0.0);
            parameters.put("Yellow", 0.0);
            parameters.put("Cyan", 0.0);
            parameters.put("Orange", 0.0);
            parameters.put("Magenta", 0.0);
            parameters.put("Brown", 0.0);
        }
        if(blue == 1.0){
            //setColor(2);
            parameters.put("Red", 0.0);
            parameters.put("Green", 0.0);
            parameters.put("White", 0.0);
            parameters.put("Grey", 0.0);
            parameters.put("Black", 0.0);
            parameters.put("Yellow", 0.0);
            parameters.put("Cyan", 0.0);
            parameters.put("Orange", 0.0);
            parameters.put("Magenta", 0.0);
            parameters.put("Brown", 0.0);
        }
        if(white == 1.0){
            //setColor(3);
            parameters.put("Red", 0.0);
            parameters.put("Green", 0.0);
            parameters.put("Blue", 0.0);
            parameters.put("Grey", 0.0);
            parameters.put("Black", 0.0);
            parameters.put("Yellow", 0.0);
            parameters.put("Cyan", 0.0);
            parameters.put("Orange", 0.0);
            parameters.put("Magenta", 0.0);
            parameters.put("Brown", 0.0);
        }
        if(grey == 1.0){
            //setColor(4);
            parameters.put("Red", 0.0);
            parameters.put("Green", 0.0);
            parameters.put("Blue", 0.0);
            parameters.put("White", 0.0);
            parameters.put("Black", 0.0);
            parameters.put("Yellow", 0.0);
            parameters.put("Cyan", 0.0);
            parameters.put("Orange", 0.0);
            parameters.put("Magenta", 0.0);
            parameters.put("Brown", 0.0);
        }
        if(black == 1.0){
            //setColor(5);
            parameters.put("Red", 0.0);
            parameters.put("Green", 0.0);
            parameters.put("Blue", 0.0);
            parameters.put("White", 0.0);
            parameters.put("Grey", 0.0);
            parameters.put("Black", 0.0);
            parameters.put("Yellow", 0.0);
            parameters.put("Cyan", 0.0);
            parameters.put("Orange", 0.0);
            parameters.put("Magenta", 0.0);
            parameters.put("Brown", 0.0);
        }
        if(yellow == 1.0){
            //setColor(6);
            parameters.put("Red", 0.0);
            parameters.put("Green", 0.0);
            parameters.put("Blue", 0.0);
            parameters.put("White", 0.0);
            parameters.put("Grey", 0.0);
            parameters.put("Black", 0.0);
            parameters.put("Cyan", 0.0);
            parameters.put("Orange", 0.0);
            parameters.put("Magenta", 0.0);
            parameters.put("Brown", 0.0);
        }
        if(cyan == 1.0){
            //setColor(7);
            parameters.put("Red", 0.0);
            parameters.put("Green", 0.0);
            parameters.put("Blue", 0.0);
            parameters.put("White", 0.0);
            parameters.put("Grey", 0.0);
            parameters.put("Black", 0.0);
            parameters.put("Yellow", 0.0);
            parameters.put("Orange", 0.0);
            parameters.put("Magenta", 0.0);
            parameters.put("Brown", 0.0);
        }
        if(orange == 1.0){
            //setColor(8);
            parameters.put("Red", 0.0);
            parameters.put("Green", 0.0);
            parameters.put("Blue", 0.0);
            parameters.put("White", 0.0);
            parameters.put("Grey", 0.0);
            parameters.put("Black", 0.0);
            parameters.put("Yellow", 0.0);
            parameters.put("Cyan", 0.0);
            parameters.put("Magenta", 0.0);
            parameters.put("Brown", 0.0);
        }
        if(magenta == 1.0){
            //setColor(9);
            parameters.put("Red", 0.0);
            parameters.put("Green", 0.0);
            parameters.put("Blue", 0.0);
            parameters.put("White", 0.0);
            parameters.put("Grey", 0.0);
            parameters.put("Black", 0.0);
            parameters.put("Yellow", 0.0);
            parameters.put("Cyan", 0.0);
            parameters.put("Orange", 0.0);
            parameters.put("Brown", 0.0);
        }
        if(brown == 1.0){
            //setColor(10);
            parameters.put("Red", 0.0);
            parameters.put("Green", 0.0);
            parameters.put("Blue", 0.0);
            parameters.put("White", 0.0);
            parameters.put("Grey", 0.0);
            parameters.put("Black", 0.0);
            parameters.put("Yellow", 0.0);
            parameters.put("Cyan", 0.0);
            parameters.put("Orange", 0.0);
            parameters.put("Magenta", 0.0);
        }



//>>>>>>> 153e617b9ebd6e9c31b49daae3ae7f145fda60a9
    }
}
