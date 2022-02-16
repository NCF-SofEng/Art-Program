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

public class ExampleDriver {

    // Create a map mapping strings with parameter names to double representing their values. Key/Value pairs
    // are added to this map so they can be tweaked from the MenuWindow
    static java.util.LinkedHashMap<String, Double> parameters = new java.util.LinkedHashMap<String, Double>();

    // Program execution begins inside the main() method. To draw the output window, just
    // create a DrawingWindow and call its createWindow() method.
    public static void main(String[] args) {
        // Initialize Parameters with names and starting values
        parameters.put("Red", 0.0);
        parameters.put("Green", 0.0);
        parameters.put("Blue", 0.0);
        parameters.put("Brown", 0.0);
        parameters.put("Purple", 0.0);
        DrawingWindow window = new DrawingWindow(500, 500, parameters, 500, 500);
        window.createWindow();

    }

}
