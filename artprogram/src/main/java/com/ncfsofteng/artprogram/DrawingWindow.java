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



import processing.core.PImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class DrawingWindow extends ProcessingWindow {
    private Map<String, Double> parameters;
    private MenuWindow menu;
    private static final String DEFAULT_TITLE = "Drawing Window";

    // BEGIN DEVON'S STUFF
    // Miscellaneous things
    private Random random = new Random(0);
    private String save_file = "out";

    // Canvas settings
    private final int BG_COLOR = color(255, 255, 255);

    // Objects on canvas
    private ArrayList<Shape> shapes = new ArrayList<>();
    private ArrayList<Image> images = new ArrayList<>();
    private ArrayList<Line> lines = new ArrayList<>();

    private int line_x0 = 0;
    private int line_y0 = 0;
    private int line_x1 = 0;
    private int line_y1 = 0;

    // Brush settings
    private int brush_size = 15; // Size in pixels
    private int color = 0; // 0-10: RED/GREEN/BLUE/WHITE/GREY/BLACK/YELLOW/CYAN/MAGENTA/ORANGE/BROWN
    private int brush_shape = 2; // 0-5: PIXEL/ELLIPSE/CIRCLE/RECTANGLE/SQUARE/LINE
    private int brush_type = 3; // 0-3: SprayPaint/Thin/Thick/Custom
    private int mode = 0; // 0-2: BRUSH/SHAPE/MANIPULATE
    private boolean save = false;
    private boolean clipboard = false;
    // END DEVON'S STUFF

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
        surface.setResizable(true);

        // BEGIN DEVON'S STUFF
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
        // END DEVON'S STUFF
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
        float rectangle = parameters.get("Rectangle").floatValue();
        float ellipse = parameters.get("Ellipse").floatValue();
        float pixel = parameters.get("Pixel").floatValue();
        float line = parameters.get("Line").floatValue();
        float brushResize = parameters.get("Brush Size").floatValue();
        float save = parameters.get("Save").floatValue();
        float brushType = parameters.get("Brush Type").floatValue();
        float brushMode = parameters.get("Brush Mode").floatValue();
        float load = parameters.get("Load").floatValue();
        float clipboard = parameters.get("Clipboard").floatValue();
        if(circle == 1.0){
            parameters.put("Circle", 0.0);
            this.brush_shape = 2;
        }
        if(square == 1.0){
            parameters.put("Square", 0.0);
            this.brush_shape = 4;
        }
        if(rectangle == 1.0){
            parameters.put("Rectangle", 0.0);
            this.brush_shape = 3;
        }
        if(ellipse == 1.0){
            parameters.put("Ellipse", 0.0);
            this.brush_shape = 1;
        }
        if(pixel == 1.0){
            parameters.put("Pixel", 0.0);
            this.brush_shape = 0;
            // TODO: PIXEL SHAPE NOT WORKING
        }
        if(line == 1.0){
            parameters.put("Line", 0.0);
            this.brush_shape = 5;
        }
        if(save == 1.0){
            parameters.put("Save", 0.0);
            this.save = true;
        }
        if(load == 1.0){
            parameters.put("Load", 0.0);
            selectInput("Select an image:", "localImage");
        }
        if(brushResize == 1.0){
            parameters.put("Brush Size", 0.0);
            String input = JOptionPane.showInputDialog("Please enter the amount of pixels you want your brush size to be!");
            try {
                brush_size = Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                // Canvas launches with default sizes if given bad input
                brush_size = 5;
            }
        }
        if(brushType == 1.0){
            parameters.put("Brush Type", 0.0);
            String input = JOptionPane.showInputDialog("Please select a brush type by entering the number corresponding to the desired brush type.!\n 0: Spray Paint\n 1: Thin Brush\n 2: Thick Brush\n 3: Custom Brush\n");
            try {
                brush_type = Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                // Canvas launches with default sizes if given bad input
                brush_type = 0;
            }
        }
        if(brushMode == 1.0){
            parameters.put("Brush Mode", 0.0);
            String input = JOptionPane.showInputDialog("Please select a brush mode by entering the number corresponding to the desired brush type.!\n 0: Brush\n 1: Shape\n 2: Manipulate");
            try {
                mode = Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                mode = 0;
            }
        }
        if(red == 1.0){
            this.color = 0;
            parameters.put("Red", 0.0);
        }
        if(green == 1.0){
            this.color = 1;
            parameters.put("Green", 0.0);
        }
        if(blue == 1.0){
            this.color = 2;
            parameters.put("Blue", 0.0);
        }
        if(white == 1.0){
            this.color = 3;
            parameters.put("White", 0.0);
        }
        if(grey == 1.0){
            this.color = 4;
            parameters.put("Grey", 0.0);
        }
        if(black == 1.0){
            this.color = 5;
            parameters.put("Black", 0.0);
        }
        if(yellow == 1.0){
            this.color = 6;
            parameters.put("Yellow", 0.0);
        }
        if(cyan == 1.0){
            this.color = 7;
            parameters.put("Cyan", 0.0);
        }
        if(orange == 1.0){
            this.color = 9;
            parameters.put("Orange", 0.0);
        }
        if(magenta == 1.0){
            this.color = 8;
            parameters.put("Magenta", 0.0);
        }
        if(brown == 1.0){
            this.color = 10;
            parameters.put("Brown", 0.0);
        }
        if(clipboard == 1.0){
            parameters.put("Clipboard", 0.0);
            this.clipboard = true;
        }


        // BEGIN DEVON'S STUFF
        // Begin recording if save flag triggered
        if (this.save)
        {
            beginRecord(PDF, save_file + ".pdf");
        }

        // Redraw background so objects can move
        background(BG_COLOR);

        // Draw some info
        if (!this.save && !this.clipboard){
            fill(color(0, 0, 0));
            text("Mouse Position: (" + mouseX + ", " + mouseY + ")", 10, 10);
        }

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

        // Draw tracked lines
        for (Line l : lines)
        {
            l.draw();
        }

        // Save pdf, png, and jpg
        if (this.save)
        {
            endRecord();
            save(save_file + ".png");
            save(save_file + ".jpg");
            this.save = false;
        }

        if (this.clipboard)
        {
            // Create temp image from canvas
            save("temp.png");
            BufferedImage image = null;

            // Load temp image
            File file = new File("temp.png");
            try {
                image = ImageIO.read(file);
            }
            catch (IOException e) {}

            // Copy temp image into clipboard
            ImageSelection imageSelection = new ImageSelection(image);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imageSelection, null);

            this.clipboard = false;
            // Delete temp file
            file.delete();
        }
        // END DEVON'S STUFF
    }

    /**
     * DEVON'S STUFF
     * TODO: INSERT DOCSTRING HERE
     */
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

    /**
     * DEVON'S STUFF
     * TODO: ADD DOCSTRING HERE
     */
    public void mousePressed()
    {
        if (mode == 1 && brush_shape == 5)
        {
            line_x0 = mouseX;
            line_y0 = mouseY;
        }
    }

    /**
     * DEVON'S STUFF
     * TODO: ADD DOCSTRING HERE
     */
    public void mouseReleased()
    {
        if (mode == 1 && brush_shape == 5)
        {
            line_x1 = mouseX;
            line_y1 = mouseY;

            lines.add(new Line(line_x0, line_y0, line_x1, line_y1, setColor(color)));

            line_x0 = 0;
            line_y0 = 0;
            line_x1 = 0;
            line_y1 = 0;
        }
    }

    /**
     * DEVON'S STUFF
     * TODO: ADD DOCSTRING HERE
     */
    public void mouseDragged()
    {
        if (mode == 0)
        {
            switch (brush_type)
            {
                case 0: // SPRAYPAINT
                    // https://mathworld.wolfram.com/DiskPointPicking.html
                    //for (int i = 0; i < sq(brush_size) / 2; i++)
                    for (int i = 0; i < brush_size / 2; i++)
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

    /**
     * DEVON'S STUFF
     * TODO: INSERT DOCSTRING HERE
     * @param selection
     */
    public void localImage(File selection)
    {
        if (selection != null)
        {
            images.add(new Image(selection.getAbsolutePath(), width / 2, height / 2));
        }
    }

    /**
     * DEVON'S STUFF
     * TODO: INSERT DOCSTRING HERE
     * @param color
     * @return
     */
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

    private class Line
    {
        int x0;
        int y0;
        int x1;
        int y1;
        int c;

        public Line(int x0, int y0, int x1, int y1, int c)
        {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
            this.c = c;
        }

        public void draw()
        {
            stroke(c);
            line(x0, y0, x1, y1);
            noStroke();
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

    // This class is used to hold an image while on the clipboard.
    static class ImageSelection implements Transferable
    {
        private java.awt.Image image;

        public ImageSelection(java.awt.Image image)
        {
            this.image = image;
        }

        // Returns supported flavors
        public DataFlavor[] getTransferDataFlavors()
        {
            return new DataFlavor[] { DataFlavor.imageFlavor };
        }

        // Returns true if flavor is supported
        public boolean isDataFlavorSupported(DataFlavor flavor)
        {
            return DataFlavor.imageFlavor.equals(flavor);
        }

        // Returns image
        public Object getTransferData(DataFlavor flavor)
                throws UnsupportedFlavorException, IOException
        {
            if (!DataFlavor.imageFlavor.equals(flavor))
            {
                throw new UnsupportedFlavorException(flavor);
            }
            return image;
        }
    }
}
