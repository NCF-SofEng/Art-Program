package com.ncfsofteng.artprogram;

/**
 * MenuWindow.java
 * @author AdamAnderson
 * @edited by Damien Razdan
 * A MenuWindow is created alongside a DrawingWindow to allow for parameter
 * tweaking while a Processing Sketch is being run. A MenuWindow displays
 * buttons with the names of all of the parameters specified
 * the values.
 */

import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;
import javax.swing.JOptionPane;

public class MenuWindow extends ProcessingWindow {
    private Map<String, Double> parameters;
    private Map<String, Button> updateButtons;
    private float buttonHeight;
    private static final int TEXT_SIZE = 20;

    /**
     * Base constructor for a MenuWindow. Takes in a width and height for the window dimenstions,
     * a String title, and a parameter map relating Strings with the names of the parameters to
     * doubles representing their values.
     * @param width
     * @param height
     * @param title
     * @param parameters
     */
    public MenuWindow(int width, int height, String title, Map<String, Double> parameters) {
        super(width, height, title);

        this.parameters = parameters;
        updateButtons = new LinkedHashMap<String, Button>();
        buttonHeight = (float) ((height / (parameters.keySet().size())));
    }

    /**
     * If the mouse is pressed over one of the update buttons, a dialog prompting
     * the user for a new value will be launched. If the user does not enter a
     * valid number, the parameter value will not be changed.
     */
    public void mousePressed() {
        for (String parameter : updateButtons.keySet()) {
            // Check if mouse was over an updateButton when pressed
            if (updateButtons.get(parameter).mouseOver(pmouseX, pmouseY)) {
                System.out.println("Update requested for: " + parameter);
                parameters.put(parameter,  1.0);
            }
        }
    }

    /**
     * Draw the menu to the MenuWindow by spacing all parameters equally given
     * the window dimensions.
     *
     * Edits by Damien: Due to the way buttons themselves are sized as well as how they are vertically spaced,
     * I had to manually edit many of the settings and how height and width were calculated for buttons and vertical
     * spacing in order to make a better fitting menu. I also needed to add a new way of spacing the buttons vertically,
     * as previously they were all just in a line down the middle of the menu.
     */
    public void draw() {
        Set<String> parameterNames = parameters.keySet();
        int numParameters = parameterNames.size();
        //determines how far apart the buttons are.
        float verticalSpacing = ((float) height / (float) (numParameters/2.5));
        //When drawing the buttons, they are spaced out in lines of 3 on the x axis.
        float buttonX[] = new float[3];
        buttonX[0] = (float) (width / 4.5);
        buttonX[1] = (float) (width / 2);
        buttonX[2] = (float) (width / 1.3);
        float centerY;
        int Xlevel = 0;
        int Ylevel = 1;
        // Iterate through each parameter, create an updateButton for the parameter,
        // and draw it to the output window
        for (String parameter : parameterNames) {
            centerY = (Ylevel * verticalSpacing);
            updateButtons.put(parameter,  new Button(parameter, buttonX[Xlevel], centerY, buttonHeight));
            updateButtons.get(parameter).draw();
            if (Xlevel == 2){
                Xlevel = 0;
                centerY += ++Ylevel * verticalSpacing;
            }
            else{
                Xlevel++;
            }

        }
        noLoop();
    }

    /*
     * Inner class representing the updateButtons for the MenuWindow. All
     * a button needs to know is its location and dimensions, and it can
     * calculate whether or not the mouse is currently over it.
     */
    private class Button {
        private String parameter;
        private float centerX;
        private float centerY;
        private float bWidth;
        private float bHeight;

        private Button(String parameter, float centerX, float centerY, float buttonHeight) {
            this.parameter = parameter;
            this.centerX = centerX;
            this.centerY = centerY;
            //button calculations were edited to better fit the menu screen
            this.bWidth = buttonHeight * 6f;
            this.bHeight = buttonHeight * 1.6f;
        }

        /*
         * Draws the button to the output window
         */
        private void draw() {
            //special placement for the magic wand to make it look nice :).
            if(parameter.equals("Magic Wand")){
                this.centerX = width / 2;
            }
            rectMode(CENTER);
            fill(50);
            rect(centerX, centerY, bWidth, bHeight);
            System.out.println(centerX + " " + centerY + " " + bWidth + " " + bHeight);
            //Color codes all the color functions with the exception of black since the buttons themselves are black.
            if (parameter.equals("Red")){
                fill(255,0,0);
            }
            else if (parameter.equals("Green")){
                fill(0,255,0);
            }
            else if (parameter.equals("Blue")){
                fill(0,0,255);
            }
            else if (parameter.equals("Grey")){
                fill(128, 128, 128);
            }
            else if (parameter.equals("Yellow")){
                fill(255, 255, 0);
            }
            else if (parameter.equals("Cyan")){
                fill(0, 255, 255);
            }
            else if (parameter.equals("Orange")){
                fill(255, 165, 0);
            }
            else if (parameter.equals("Magenta")){
                fill(255, 0, 255);
            }
            else if (parameter.equals("Brown")){
                fill(165, 42, 42);
            }
            else{
            fill(255);}
            textAlign(CENTER);
            //centers text.
            text(parameter, centerX, centerY - (bHeight / 20) + 10);
            fill(0);
        }

        /*
         * Returns true if the mouse is over the button on the MenuWindow
         */
        private boolean mouseOver(float mouseX, float mouseY) {
            boolean isPressed = false;
            //fixed a tiny bug where the button didn't always respond when clicked at it's outer fringes.
            float bWidth = this.bWidth / 2;
            float bHeight = this.bHeight / 2;
            if ((mouseX > centerX - bWidth) && (mouseX < centerX + bWidth) &&
                    (mouseY > centerY - bHeight) && (mouseY < centerY + bHeight)) {
                isPressed = true;
            }
            return isPressed;
        }
    }

    //////////////////////////////////////////////////////////////////
    /* Below are methods that customize the MenuWindow's appearance */
    //////////////////////////////////////////////////////////////////


    public void setup() {
        //Dynamic resizable window. It stretches the image and doesn't look amazing, but it adds to user functionality.
        surface.setResizable(true);
        background(255);
        fill(0);
        textSize(TEXT_SIZE);
    }




}
