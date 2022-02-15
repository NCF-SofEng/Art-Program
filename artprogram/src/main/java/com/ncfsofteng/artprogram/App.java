/**
 * App.java
 * @author Devon Gardner
 *
 * A simple paint app. 
 */

package com.ncfsofteng.artprogram;

import processing.core.PApplet;

public class App extends PApplet
{
    // Canvas settings PIXELS
    private int width = 0;
    private int height = 0;

    // Brush settings
    public int brush_size = 5; // Size in pixels
    public int brush_color = 0; // 0-10: RED/GREEN/BLUE/WHITE/GREY/BLACK/YELLOW/CYAN/MAGENTA/ORANGE/BROWN
    public int brush_style = 0; // 0-2: PIXEL/ELLIPSE/RECTANGLE
    public int brush_mode = 0; // 0/1: DRAW/SHAPE

    public App(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    @Override
    public void setup()
    {
        frameRate(1000);
        background(128);
        ellipseMode(CENTER);
        rectMode(CORNER);
        textAlign(LEFT, TOP);
    }

    @Override
    public void settings()
    {
        size(width, height);
    }

    @Override
    public void draw()
    {
        // TODO: Draw info as text (mouse coords, current brush size, etc) 
        noStroke();
        fill(color(128, 128, 128));
        rect(10, 10, 160, 15);
        fill(color(0, 0, 0));
        text("Mouse Position: (" + mouseX + ", " + mouseY + ")", 10, 10);
    }

    @Override
    public void keyPressed()
    {
        if (key == 'c')
        {
            background(color(128, 128, 128));
        }
    }

    @Override
    public void mouseClicked()
    {
        if (brush_mode == 1)
        {
            setColor();
            square(mouseX, mouseY, 15);
        }
    }

    @Override
    public void mouseDragged()
    {
        if (brush_mode == 0)
        {
            setColor();
            circle(mouseX, mouseY, brush_size);
        }
    }

    private void setColor()
    {
        switch (brush_color) {
            case 0: // RED
                fill(color(255, 0, 0));
                stroke(color(255, 0, 0));
                break;
            case 1: // GREEN
                fill(color(0, 255, 0));
                stroke(color(0, 255, 0));
                break;
            case 2: // BLUE
                fill(color(0, 0, 255));
                stroke(color(0, 0, 255));
                break;
            case 3: // WHITE
                fill(color(255, 255, 255));
                stroke(color(255, 255, 255));
            case 4: // GREY
                fill(color(128, 128, 128));
                stroke(color(128, 128, 128));
            case 5: // BLACK
                fill(color(0, 0, 0));
                stroke(color(0, 0, 0));
            case 6: // YELLOW
                fill(color(255, 255, 0));
                stroke(color(255, 255, 0));
            case 7: // CYAN
                fill(color(0, 255, 255));
                stroke(color(0, 255, 255));
            case 8: // MAGENTA
                fill(color(255, 0, 255));
                stroke(color(255, 0, 255));
            case 9: // ORANGE
                fill(color(255, 165, 0));
                stroke(color(255, 165, 0));
            case 10: // BROWN
                fill(color(165, 42, 42));
                stroke(color(165, 42, 42));
            default:
                break;
        }
    }

    public static void main( String[] args )
    {
        String[] appletArgs = new String[] { "App" };
        App myApp = new App(800, 800);
        PApplet.runSketch(appletArgs, myApp);
    }
}
