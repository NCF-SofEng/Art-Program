package com.ncfsofteng.artprogram;

/**
 * ExampleDriver.java
 * @author AdamAnderson
 *
 * An example to show the functionality of DrawingWindow-MenuWindow pairs.
 * We create a map of 3 parameters an ordered pair (x, y) and a radius.
 * These values are used to plot a circle centered at (x, y) on the
 * DrawingWindow, and they can be tweaked while the sketch is running
 * by entering new values using the MenuWindow.
 */
import javax.swing.JOptionPane;
public class ExampleDriver {

    // Create a map mapping strings with parameter names to double representing their values. Key/Value pairs
    // are added to this map so they can be tweaked from the MenuWindow
    static java.util.LinkedHashMap<String, Double> parameters = new java.util.LinkedHashMap<String, Double>();

    // Program execution begins inside the main() method. To draw the output window, just
    // create a DrawingWindow and call its createWindow() method.
    public static void main(String[] args) {
        // Initialize Parameters with names and starting values

        // Prompt user for value to update parameter with
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
        parameters.put("Circle", 0.0);
        parameters.put("Square", 0.0);
        parameters.put("Rectangle", 0.0);
        parameters.put("Ellipse", 0.0);
        parameters.put("Brush Size", 0.0);
        parameters.put("Brush Type", 0.0);
        parameters.put("Save", 0.0);
        parameters.put("Load", 0.0);
        DrawingWindow window = new DrawingWindow(canvasWidth, canvasLength, parameters, 500, 500);
        window.createWindow();
    }
}
