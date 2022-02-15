package com.ncfsofteng.artprogram;

import processing.core.PApplet;

/**
 * App.java
 * @author Devon Gardner
 *
 * A simple paint app. 
 */
public class App extends PApplet
{
    // Canvas settings PIXELS
    private int width = 0;
    private int height = 0;

    // Colors RGB
    public final int red = color(255, 0, 0);
    public final int green = color(0, 255, 0);
    public final int blue = color(0, 0, 255);
    public final int white = color(255, 255, 255);
    public final int grey = color(128, 128, 128);
    public final int black = color(0, 0, 0);
    public final int yellow = color(255, 255, 0);
    public final int cyan = color(0, 255, 255);
    public final int magenta = color(255, 0, 255);
    public final int orange = color(255, 165, 0);
    public final int brown = color(165, 42, 42);

    // Brush settings
    private int brush_size = 5;
    private int brush_color = blue;

    public App(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    @Override
    public void setup()
    {
        // High framerate for smooth drawing
        frameRate(1000);
        background(128);
    }

    @Override
    public void settings()
    {
        // Use constructor width and height values to create canvas
        size(width, height);
    }

    @Override
    public void draw()
    {
    
    }

    @Override
    public void keyPressed()
    {
        if (key == 'c')
        {
            background(grey);
        }
    }

    @Override
    public void mouseDragged()
    {
        drawCircle(mouseX, mouseY, brush_size, brush_color);
        // drawPixel(mouseX, mouseY, orange);
    }

    /**
     * Draws a pixel onto the canvas.
     * 
     * @param x     x coordinate of the target pixel
     * @param y     y coodinate of the target pixel
     * @param color color to set the target pixel to
     */
    public void drawPixel(int x, int y, int color)
    {
        // Set pixel color to color and draw at x, y
        stroke(color);
        point(x, y);
    }

    /**
     * Draws a circle onto the canvas
     * 
     * @param x     center x coordinate
     * @param y     center y coordinate
     * @param r     circle radius
     * @param color circle color
     */
    public void drawCircle(int x, int y, int r, int color)
    {
        // noStroke results in the shape having no border
        noStroke();
        // fill sets the body color of a shape
        fill(color);
        circle(x, y, r);
    }

    public static void main( String[] args )
    {
        String[] appletArgs = new String[] { "App" };
        App myApp = new App(800, 800);
        PApplet.runSketch(appletArgs, myApp);
    }
}
