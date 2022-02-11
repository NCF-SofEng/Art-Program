package com.ncfsofteng.artprogram;
import processing.core.PApplet;

/**
 * Hello world!
 *
 */
public class App extends PApplet
{
    @Override
    public void setup()
    {
        frameRate(1000);
        background(255);
    }

    @Override
    public void settings()
    {
        size(500, 500);
    }

    @Override
    public void draw()
    {
        // background(255);
        // ellipse(mouseX, mouseY, 20, 20);
    }

    @Override
    public void mousePressed()
    {
    }

    @Override
    public void mouseDragged()
    {
        // point(mouseX, mouseY);
        fill(0);
        stroke(0);
        point(mouseX, mouseY);
    }

    public static void main( String[] args )
    {
        String[] appletArgs = new String[] { "App" };
        App myApp = new App();
        PApplet.runSketch(appletArgs, myApp);
    }
}
