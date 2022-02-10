package com.ncfsofteng.artprogram;
import processing.core.PApplet;

/**
 * Hello world!
 *
 */
public class App extends PApplet
{
    public void settings()
    {
        size(500, 500);
    }

    public void draw()
    {
        ellipse(mouseX, mouseY, 20, 20);
    }

    public void mousePressed()
    {
        background(64);
    }

    public static void main( String[] args )
    {
        String[] appletArgs = new String[] { "App" };
        App myApp = new App();
        PApplet.runSketch(appletArgs, myApp);
    }
}
