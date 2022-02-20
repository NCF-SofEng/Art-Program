/**
 * App.java
 * @author Devon Gardner
 *
 * A simple paint app. 
 */

package com.ncfsofteng.artprogram;

import processing.core.*;
import processing.pdf.*;
import java.io.File;
import java.util.Random;

import java.util.ArrayList;

public class App extends PApplet
{
    private String save_file = "out";
    private Random random = new Random(0);

    // Canvas settings
    private int width = 0;
    private int height = 0;
    private final int BG_COLOR = color(255, 255, 255);

    // Objects on canvas
    private ArrayList<Shape> shapes = new ArrayList<>();
    private ArrayList<Image> images = new ArrayList<>();

    // Brush settings
    private int brush_size = 15; // Size in pixels
    private int color = 0; // 0-10: RED/GREEN/BLUE/WHITE/GREY/BLACK/YELLOW/CYAN/MAGENTA/ORANGE/BROWN
    private int brush_shape = 0; // 0-4: PIXEL/ELLIPSE/CIRCLE/RECTANGLE/SQUARE
    private int brush_type = 0; // 0-3: SprayPaint/Thin/Thick/Custom
    private int mode = 0; // 0-2: BRUSH/SHAPE/MANIPULATE
    private boolean save = false;

    public App(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    @Override
    public void setup()
    {
        frameRate(300);
        background(BG_COLOR);
        ellipseMode(CENTER);
        rectMode(CORNER);
        textAlign(LEFT, TOP);
        noStroke();

        // Demo shapes
        shapes.add(new Ellipse(width / 2 + 75, height / 2, 50, 30, -45.0f, setColor(color)));
        shapes.add(new Rectangle(width / 2 - 75, height / 2, 50, 30, 15.0f, setColor(color)));
        images.add(new Image("https://i.imgur.com/CXMDB5o.png", "png", 100, 100));
    }

    @Override
    public void settings()
    {
        size(width, height);
    }

    @Override
    public void draw()
    {
        // Begin recording if save flag triggered
        if (save)
        {
            beginRecord(PDF, save_file + ".pdf");
        }

        // Redraw background so objects can move
        background(BG_COLOR);

        // Draw some info
        fill(color(0, 0, 0));
        text("Mouse Position: (" + mouseX + ", " + mouseY + ")", 10, 10);

        // Draw tracked images
        for (Image image : images)
        {
            image.draw();
        }

        // Draw tracked shapes
        for (Shape shape : shapes)
        {
            shape.draw();
        }

        // Save pdf, png, and jpg
        if (save)
        {
            endRecord();
            save(save_file + ".png");
            save(save_file + ".jpg");
            save = false;
        }
    }

    @Override
    public void keyPressed() {
        if (key == '0')
            mode = 0;
        if (key == '1')
            mode = 1;
        if (key == '2')
            mode = 2;
        if (key == 's')
            save = true;
        if (key == 'l')
            selectInput("Select an image:", "localImage");
        System.out.println(mode);
    }

    @Override
    public void mouseClicked()
    {
        if (mode == 1)
        {
            switch (brush_shape)
            {
                case 0:
                    set(mouseX, mouseY, setColor(color));
                    break;
                case 1:
                    shapes.add(new Ellipse(mouseX, mouseY, 30, 50, 0.0f, setColor(color)));
                    break;
                case 2:
                    shapes.add(new Ellipse(mouseX, mouseY, 30, 30, 0.0f, setColor(color)));
                    break;
                case 3:
                    shapes.add(new Rectangle(mouseX, mouseY, 40, 60, 0.0f, setColor(color)));
                    break;
                case 4:
                    shapes.add(new Rectangle(mouseX, mouseY, 40, 40, 0.0f, setColor(color)));
                    break;
                default:
                    break;
            }
        }
        else if (mode == 2)
        {
            for (Shape shape : shapes)
            {
                if (shape.mouseOver())
                    shape.c = setColor(color);
            }
        }
    }

    @Override
    public void mouseDragged()
    {
        if (mode == 0)
        {
            switch (brush_type)
            {
                case 0: // SPRAYPAINT
                    // https://mathworld.wolfram.com/DiskPointPicking.html
                    for (int i = 0; i < sq(brush_size) / 2; i++)
                    {
                        float a = random.nextFloat() * PI * 2; 
                        float r = sqrt(random.nextFloat()) * brush_size;
                        float x = mouseX + r * cos(a);
                        float y = mouseY + r * sin(a);
                        // point((int)x, (int)y);
                        set((int)x, (int)y, setColor(color));
                        shapes.add(new Ellipse((int)x, (int)y, 1, 1, 0.0f, setColor(color)));
                    }
                    break;
                case 1: // THICK BRUSH
                    shapes.add(new Ellipse(mouseX, mouseY, 30, 30, 0f, setColor(color)));
                    break;
                case 2: // THIN BRUSH
                    shapes.add(new Ellipse(mouseX, mouseY, 5, 5, 0f, setColor(color)));
                    break;
                case 3: // CUSTOM BRUSH
                    shapes.add(new Ellipse(mouseX, mouseY, brush_size, brush_size, 0f, setColor(color)));
                    break;
                default:
                    break;
            }
        }
        else if (mode == 2)
        {
            // Move shapes if mouse is over them and dragging
            for (Shape shape : shapes)
            {
                if (shape.mouseOver())
                {
                    shape.x = mouseX;
                    shape.y = mouseY;
                }
            }

            // Move images if mouse is over them and dragging
            for (Image image : images)
            {
                if (image.mouseOver())
                {
                    image.x = mouseX - image.image.width / 2;
                    image.y = mouseY - image.image.height / 2;
                }
            }
        }
    }

    public void localImage(File selection)
    {
        if (selection != null)
        {
            images.add(new Image(selection.getAbsolutePath(), width / 2, height / 2));
        }
    }

    private int setColor(int color)
    {
        int c;

        switch (color) {
            case 0: // RED
                c = color(255, 0, 0);
                break;
            case 1: // GREEN
                c = color(0, 255, 0);
                break;
            case 2: // BLUE
                c = color(0, 0, 255);
                break;
            case 3: // WHITE
                c = color(255, 255, 255);
                break;
            case 4: // GREY
                c = color(128, 128, 128);
                break;
            case 5: // BLACK
                c = color(0, 0, 0);
                break;
            case 6: // YELLOW
                c = color(255, 255, 0);
                break;
            case 7: // CYAN
                c = color(0, 255, 255);
                break;
            case 8: // MAGENTA
                c = color(255, 0, 255);
                break;
            case 9: // ORANGE
                c = color(255, 165, 0);
                break;
            case 10: // BROWN
                c = color(165, 42, 42);
                break;
            default: // BLACK
                c = color(0, 0, 0);
                break;
        }

        return c;
    }

    /**
     * Inner class representing a shape on the canvas
     */
    abstract private class Shape
    {
        protected int x;
        protected int y;
        protected int w;
        protected int h;
        protected float a;
        protected int c;

        /**
         * Geometric description of the shape.
         * @param x Center x value
         * @param y Center y value
         * @param w Width of shape
         * @param h Height of shape
         * @param a Angle of the shape in degrees
         * @param c Color of the shape
         */
        private Shape(int x, int y, int w, int h, float a, int c)
        {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.a = radians(a);
            this.c = c;
        }

        /*
         * Draws the button to the output window
         */
        abstract public void draw();

        /*
         * Returns true if the mouse is over the button on the MenuWindow
         */
        abstract public boolean mouseOver();
    }

    /**
     * Inner class representing an ellipse on the canvas
     */
    private class Ellipse extends Shape
    {
        public Ellipse(int x, int y, int w, int h, float a, int c)
        {
            super(x, y, w, h, a, c);
        }

        @Override
        public void draw()
        {
            ellipseMode(CENTER);
            fill(c);
            translate(x, y);
            rotate(a);
            ellipse(0, 0, this.w, this.h);
            rotate(-a);
            translate(-x, -y);
        }

        @Override
        public boolean mouseOver()
        {
            float f = cos(a) * (pmouseX - this.x) + sin(a) * (pmouseY - this.y);
            float g = sin(a) * (pmouseX - this.x) - cos(a) * (pmouseY - this.y);
            float rx = (float)w / 2;
            float ry = (float)h / 2;

            //return pow((float) (pmouseX - this.x) / ((float)this.w / 2), 2) + pow((float) (pmouseY - this.y) / ((float)this.h / 2), 2) <= 1;
            return pow(f / rx, 2) + pow(g / ry, 2) <= 1.0f;
        }
    }

    private class Rectangle extends Shape
    {
        public Rectangle(int x, int y, int w, int h, float a, int c)
        {
            super(x, y, w, h, a, c);
        }

        @Override
        public void draw()
        {
            rectMode(CENTER);
            fill(c);
            translate(x, y);
            rotate(a);
            rect(0, 0, this.w, this.h);
            rotate(-a);
            translate(-x, -y);
        }

        @Override
        public boolean mouseOver()
        {
            float rx = (float)w / 2;
            float ry = (float)h / 2;
            return (pmouseX > x - rx) && (pmouseX < x + rx) && (pmouseY > y - ry) && (pmouseY < y + ry);
        }
    }

    private class Image 
    {
        PImage image;
        int x;
        int y;

        public Image(String path, int x, int y)
        {
            image = loadImage(path);
            this.x = x;
            this.y = y;
        }

        public Image(String path, String type, int x, int y)
        {
            image = loadImage(path, type);    
            this.x = x;
            this.y = y;
        }

        public void draw()
        {
            image(this.image, this.x, this.y);
        }

        public boolean mouseOver()
        {
            return (pmouseX > this.x) && (pmouseX < this.x + this.image.width) && (pmouseY > this.y) && (pmouseY < this.y + this.image.height);
        }

        public void resize(int w, int h)
        {
            this.image.resize(w, h);
        }
    }

    public static void main( String[] args )
    {
        String[] appletArgs = new String[] { "App" };
        App myApp = new App(800, 800);
        PApplet.runSketch(appletArgs, myApp);
    }
}
