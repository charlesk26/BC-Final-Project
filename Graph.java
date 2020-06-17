package com.jetbrains;

/*
 * Charles Kolozsvary
 * Period 2 Algorithms
 * Java Version 4.2.1
 * Java SDK: 12.01.0
 * 6-13-20
 * Description:
 * This class is in charge of graphing the various functions and displaying information
 * like the increment of traversal and the bounds of the graph
 */
import java.awt.Color;
import java.awt.Font;
public class Graph
{
    private static final String[] fileNames= {"root.png", "cubeRoot.png", "rational.png","exponential.png","logarithmic.png","sin.png","sinA.png","cos.png","cosA.png"}; //the file names
    //Everything with static final will not be changed and is acting as a global variable of sorts
    private static final Font bigFont = new Font("Dialog", Font.ITALIC, 45); //font for the display of what order it is
    private static final Font orderFont = new Font("Dialog", Font.ITALIC, 30); //font for the display of what order it is
    private static final Font graphFont = new Font("Dialog", Font.ITALIC, 10); //font for the display of the axis values
    private static final Font topRightFont = new Font("Dialog", Font.ITALIC, 20); //font to display the values of the current center, increment and graph size
    private static final Font turboFont = new Font("Dialog", Font.ITALIC, 12); //font for the bottom left text
    private static final Font controlsFont = new Font("Dialog", Font.ITALIC, 13); //font for the other controls around the screen e.g (press I, O)

    private static final int generalInputBuffer = 15; //the number of milliseconds paused after every cycle before the next input can be entered
    private static final int highBuffer = 90; //amount of time in milliseconds before next input can be read
    private static final int lowBuffer = 30;

    private static final double divyUpSpacing = 1300; //the number of times the bound is divied up to draw points
    private static final double extraSpaceOnSidesX = 5; //lower, more space on sides, for moving the order display in the x to the right, lower = more right
    private static final double extraSpaceOnSidesY = 10; //lower, more space on sides, for moving the orderdisplay up and down, lower is more down
    private static final double axisNumberPositioning = 25; //higher = right, lower = left
    private static final double graphTickSeparation = 10; //the number of ticks on the graph for the bound in one direction
    private static final double graphTick = 60; //effects the size of the ticks on the graph, higher = smaller
    private static final double circleRadius = 75; //in relation to bound i.e bound/circleRadius, higher = smaller circle
    private static final double boundChangeDegree = 2;
    private static final double incrementChangeDegree = 2;
    private static final double maxBound = 512.0;
    private static final double turboWordsLR = 1.35; //the higher the more right, the lower the more left for the infor displayed in bottom left
    private static final double[] wordsSpacing = {2.5,2,1.5,1.0};
    private static final double[] topRightSpacing = {.5, 2.5, 4.5};
    private static final double defaultIncrement = 0.015625;
    private static final double defaultBound = 4;
    private static final double directionsUnderOrder = 1.5;
    private static final double topPictureHeight = 600;




    private static final double curveRadius = .0065; //the pen size for the curves, both polynomial and elementary function
    private static final double axisRadius = .002; //the pensize for the graph and ticks
    private static final double circleOutLineRadius = .003; //the pen size for the outline arounf the circle which indicated the center

    private static final Color orderColor = new Color(243, 236, 24); //the color of the display for the current order
    private static final Color polynomialColor = new Color(159,55,150); //the color of the polynomial curve
    private static final Color axisColor = new Color(180,180,180); //the color of the graph and ticks
    private static final Color clearColor = new Color(40,40,40); //the color of the backround, what clears the screen after animation loop
    private static final Color funcColor = new Color(19,119,187); //the color of the elementary function curve
    private static final Color circleColor = new Color(100,240,150); //the color of the circle which indicates the center
    private static final Color circleOutLineColor = new Color(80,80,80);  //the color of the outline of the circle which indicates the center
    private static final Color turboColor = new Color(205,31,68); //color which shows true or false in left bottom corner
    private static final Color turboWordsColor = new Color(240,240,240); //color for the words around the true or false and also for the other directions

    private static final Color centerColor = new Color(33,175,75); //color for the display of curent center
    private static final Color incColor = new Color(163,65,152); //color for display of current increment
    private static final Color boundColor = new Color(21,118,187); //color for display of the bound

    //real instance fields here
    private double spacing; //distance between points on a curve
    private double increment; //the increment of traversal, this is different from spacing in that this controls what next series will be generated not how many points themselves are generated
    private double bound; //the bound of display in both x and y direction

    private boolean incTurbo; //if it is turbo, it will update mmore quickly, and thus animate faster but offer less precision when experiencing the grapph
    private boolean boundTurbo; //
    private boolean orderTurbo; //
    private boolean centerTurbo;  //

    private double middle;


    /**
     * Constructs the instances of a graph object which controls the properties of the graph being displayed
     * takes @params the increment of traversal, and x and y bounds
     */
    public Graph() //CONSTRUCTOR
    {
        // initialise instance variables
        this.orderTurbo = false;
        this.centerTurbo = true; //default with easy movement of center
        this.incTurbo = false;
        this.boundTurbo = false;

        this.bound = defaultBound; //the bounds of the graph
        this.middle = this.bound/3;
        this.spacing = this.bound/divyUpSpacing; //draw as many points in a region proportional to the space that the region takes up
        this.increment = defaultIncrement; //how far the user moves left and right when pressed a single time

        StdDraw.setCanvasSize(975,975); //the number of pixels by pixels that is displayed in the pop-up window, seperate from mathmatical scaling; just sheer size
        StdDraw.setScale(-this.bound,this.bound); //setting the scale of the space

        StdDraw.enableDoubleBuffering(); //more efficient to choose when to show rather than always showing after every change
    }

    public void reset() //reset
    {
        // initialise instance variables
        this.orderTurbo = false;
        this.centerTurbo = true; //default with easy movement of center
        this.incTurbo = false;
        this.boundTurbo = false;
        this.bound = defaultBound; //the bounds of the graph
        this.spacing = this.bound/divyUpSpacing; //draw as many points in a region proportional to the space that the region takes up
        this.increment = defaultIncrement; //how far the user moves left and right when pressed a single time
        StdDraw.setScale(-this.bound,this.bound); //setting the scale of the space
    }

    public void drawSelectionScreen()
    {
        StdDraw.clear(new Color(50,50,50));
        StdDraw.setPenColor(0,0,0);
        StdDraw.line(-middle, -bound, -middle, bound); //left line
        StdDraw.line(-bound, middle, bound, middle); // top line
        StdDraw.line(-bound, -middle, bound, -middle); //bottom line
        StdDraw.line(middle, -bound, middle, bound); //right line
        StdDraw.picture(-bound+bound/3, bound-bound/3, fileNames[0], 2*bound/3.25,2*bound/3.25);
        StdDraw.picture(-bound+3*bound/3, bound-bound/3, fileNames[1], 2*bound/3.25,2*bound/3.25);
        StdDraw.picture(-bound+5*bound/3, bound-bound/3, fileNames[2], 2*bound/3.25,2*bound/3.25);



        StdDraw.picture(-bound+bound/3, 0, fileNames[3], 2*bound/3.25,2*bound/3.25);
        StdDraw.picture(-bound+3*bound/3, 0, fileNames[4], 2*bound/3.25,2*bound/3.25);
        StdDraw.picture(-bound+5*bound/3, 0, fileNames[5], 2*bound/3.25,2*bound/3.25);

        StdDraw.picture(-bound+bound/3, -bound+bound/3, fileNames[6], 2*bound/3.25,2*bound/3.25);
        StdDraw.picture(-bound+3*bound/3, -bound+bound/3, fileNames[7], 2*bound/3.25,2*bound/3.25);
        StdDraw.picture(-bound+5*bound/3, -bound+bound/3, fileNames[8], 2*bound/3.25,2*bound/3.25);

        StdDraw.show();
        StdDraw.pause(20);
    }

    public String determineSelection(double x, double y)
    {
        if(x >= -bound && x<= -middle && y>middle)
        {
            return "root";
        }
        else if(x >= -middle && x<= middle && y>middle)
        {
            return "cubeRoot";
        }
        else if(x >= middle && x<= bound && y>middle)
        {
            return "rational";
        }
        else if(x >= -bound && x<= -middle && y<middle && y>-middle)
        {
            return "exponential";
        }
        else if(x >= -middle && x<= middle && y<middle && y>-middle)
        {
            return "logarithmic";
        }
        else if(x >= middle && x<= bound && y<middle && y>-middle)
        {
            return "sin";
        }
        else if(x >= -bound && x<= -middle && y<-middle)
        {
            return "sinA";
        }
        else if(x >= -middle && x<= middle && y<-middle)
        {
            return "cos";
        }
        else if(x >= middle && x<= bound && y<-middle)
        {
            return "cosA";
        }
        else
        {
            return "";
        }
    }

    public boolean checkTurbo() //check if the buffer for either changing the order or the center is being changed
    {
        if(StdDraw.isKeyPressed('A') && !orderTurbo) //if the user presses A and turbo was off before
        {
            setOrderTurbo(true); //turbo on
            return true;
        }
        else if(StdDraw.isKeyPressed('Z') && orderTurbo)
        {
            setOrderTurbo(false); //turn off turbo
            return true;
        }
        else if(StdDraw.isKeyPressed('S') && !centerTurbo)
        {
            setCenterTurbo(true);
            return true;
        }
        else if(StdDraw.isKeyPressed('X') && centerTurbo)
        {
            setCenterTurbo(false);
            return true;
        }
        else if(StdDraw.isKeyPressed('D') && !incTurbo)
        {
            setIncTurbo(true);
            return true;
        }
        else if(StdDraw.isKeyPressed('C') && incTurbo)
        {
            setIncTurbo(false);
            return true;
        }
        else if(StdDraw.isKeyPressed('F') && !boundTurbo)
        {
            setBoundTurbo(true);
            return true;
        }
        else if(StdDraw.isKeyPressed('V') && boundTurbo)
        {
            setBoundTurbo(false);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Getter methods, retrieve the instance fields of Graph
     */
    public double getSpacing()
    {
        return spacing; //returns the spacing, the x distances between points drawn on a curve
    }

    public double getInc()
    {
        return increment; //returns the increment, the change in center for the polynomial induced by the press of left or right
    }

    public double getBound()
    {
        return bound; //the region of the graph being displayed
    }

    public boolean getOrderTurbo()
    {
        return orderTurbo;
    }

    public boolean getCenterTurbo()
    {
        return centerTurbo;
    }

    public boolean getIncTurbo()
    {
        return incTurbo;
    }

    public boolean getBoundTurbo()
    {
        return boundTurbo;
    }

    /**
     * Given a boolean which describes wether an aspect has turbo, wait for the appropriate amount of time
     * no turbo = short buffer time
     * yes turbo = low buffer time
     */
    public void stopTime(boolean turbo)
    {
        if(turbo)
        {
            StdDraw.pause(lowBuffer);
        }
        else
        {
            StdDraw.pause(highBuffer);
        }
    }

    /**
     * Setter methods for turbo
     */
    public void setOrderTurbo(boolean onOff) //given a boolean, set as that boolean
    {
        this.orderTurbo = onOff;
    }

    public void setCenterTurbo(boolean onOff)
    {
        this.centerTurbo = onOff;
    }

    public void setIncTurbo(boolean onOff)
    {
        this.incTurbo = onOff;
    }

    public void setBoundTurbo(boolean onOff)
    {
        this.boundTurbo = onOff;
    }

    /**
     * change bound, the size of the graph/ whats being displayed
     */
    public void increaseBound()
    {
        if(bound*boundChangeDegree <= maxBound)
        {
            this.bound = bound*boundChangeDegree;
            this.spacing = this.bound/divyUpSpacing; //draw as many points in a region proportional to the space that the region takes up
            StdDraw.setScale(-this.bound,this.bound); //setting the scale of the space
        }
        else
        {
            this.bound = maxBound;
            this.spacing = this.bound/divyUpSpacing; //draw as many points in a region proportional to the space that the region takes up
            StdDraw.setScale(-this.bound,this.bound); //setting the scale of the space
        }

    }

    public void decreaseBound()
    {
        if(bound/boundChangeDegree > 0)
        {
            this.bound = bound/boundChangeDegree;
            this.spacing = this.bound/divyUpSpacing; //draw as many points in a region proportional to the space that the region takes up
            StdDraw.setScale(-this.bound,this.bound); //setting the scale of the space
        }
    }

    /**
     * Change Increment of traversal
     */
    public void increaseInc()
    {
        if(increment*incrementChangeDegree <= bound) //cant go larger than the bound, would not be very meaningful
        {
            this.increment = increment*incrementChangeDegree;
        }

    }

    public void decreaseInc()
    {
        if(increment/incrementChangeDegree > 0.0)
        {
            this.increment = increment/incrementChangeDegree;
        }
    }

    /**
     * Void Methods
     */
    public void show()
    {
        StdDraw.show();
    }

    public void clear()
    {
        StdDraw.clear(clearColor);
    }

    public void drawPoint(double x, double y)
    {
        StdDraw.point(x, y);
    }

    public void waitInputBuffer() //time spent waiting before checking for next input
    {
        StdDraw.pause(generalInputBuffer);
    }

    /**
     * Main graphing methods
     */
    public void drawAxisAndInformation(Polynomial polynomial)
    {
        StdDraw.setPenRadius(axisRadius); //set color and pensize for axis and numbers
        StdDraw.setPenColor(axisColor);
        StdDraw.setFont(graphFont); //set font for axis/graph

        StdDraw.line(-bound, 0, bound, 0); //draw the two main lines of the axis, x and y
        StdDraw.line(0, -bound, 0, bound);
        for(double i = 0; i<=bound; i+= bound/graphTickSeparation) //draw ticks on graph
        {
            StdDraw.line(i, -bound/graphTick, i, bound/graphTick);
            StdDraw.line(-i, -bound/graphTick, -i, bound/graphTick);
            StdDraw.line(-bound/graphTick, i, bound/graphTick, i);
            StdDraw.line(-bound/graphTick, -i, bound/graphTick, -i);
        }

        for(double i = -bound+bound/graphTickSeparation; i<=bound-bound/graphTickSeparation; i+= bound/graphTickSeparation) //write numbers on graph
        {
            StdDraw.text(i, (-bound/graphTick)-(bound/axisNumberPositioning), ""+round(i,2));
            StdDraw.text((-bound/graphTick)-(bound/axisNumberPositioning), i, ""+round(i,2));
        }

        //all of this displays wether or not the higher buffer is on for changing order or changing center, so
        //you can easily change from being able to move precisely and seeing a faster animation
        StdDraw.setFont(turboFont);

        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+wordsSpacing[0]*bound/extraSpaceOnSidesY, "  Order Turbo:"+"                              ");
        StdDraw.setPenColor(turboColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+wordsSpacing[0]*bound/extraSpaceOnSidesY, "     "+orderTurbo+" ".toUpperCase());
        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+wordsSpacing[0]*bound/extraSpaceOnSidesY, "                "+"               press A, Z");

        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+wordsSpacing[1]*bound/extraSpaceOnSidesY, "  Center Turbo:"+"                              ");
        StdDraw.setPenColor(turboColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+wordsSpacing[1]*bound/extraSpaceOnSidesY, "     "+centerTurbo+" ".toUpperCase());
        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+wordsSpacing[1]*bound/extraSpaceOnSidesY, "                "+"               press S, X");

        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+wordsSpacing[2]*bound/extraSpaceOnSidesY, "Increment Turbo:"+"                               ");
        StdDraw.setPenColor(turboColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+wordsSpacing[2]*bound/extraSpaceOnSidesY, "     "+incTurbo+" ".toUpperCase());
        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+wordsSpacing[2]*bound/extraSpaceOnSidesY, "                "+"               press D, C");

        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+wordsSpacing[3]*bound/extraSpaceOnSidesY, " Graph Turbo:"+"                              ");
        StdDraw.setPenColor(turboColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+wordsSpacing[3]*bound/extraSpaceOnSidesY, "      "+boundTurbo+" ".toUpperCase());
        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+wordsSpacing[3]*bound/extraSpaceOnSidesY, "                "+"               press F, V");

        //Display the current center of the polynomial, increment and size of the graph
        StdDraw.setFont(topRightFont);
        StdDraw.setPenColor(centerColor);
        StdDraw.text(bound-bound/extraSpaceOnSidesX, bound-topRightSpacing[0]*bound/extraSpaceOnSidesY, "Center = "+round(polynomial.getCenter(),5));
        StdDraw.setFont(controlsFont);
        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(bound-bound/extraSpaceOnSidesX, bound-(topRightSpacing[0]+0.5)*bound/extraSpaceOnSidesY, "      press ←, →    ");

        StdDraw.setFont(topRightFont);
        StdDraw.setPenColor(incColor);
        StdDraw.text(bound-bound*1.125/extraSpaceOnSidesX, bound-topRightSpacing[1]*bound/extraSpaceOnSidesY, "Increment = "+round(increment,6));

        StdDraw.setFont(controlsFont);
        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(bound-bound/extraSpaceOnSidesX, bound-(topRightSpacing[1]+0.5)*bound/extraSpaceOnSidesY, "       press K, L     ");

        StdDraw.setFont(topRightFont);
        StdDraw.setPenColor(boundColor);
        StdDraw.text(bound-bound/extraSpaceOnSidesX, bound-topRightSpacing[2]*bound/extraSpaceOnSidesY, "Graph Size = "+round(bound,5));

        StdDraw.setFont(controlsFont);
        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(bound-bound/extraSpaceOnSidesX, bound-(topRightSpacing[2]+0.5)*bound/extraSpaceOnSidesY, "      press I, O     ");

        StdDraw.setFont(orderFont); //set font for order display
        StdDraw.setPenColor(orderColor); //set color for order display
        StdDraw.text(-bound+bound/extraSpaceOnSidesX, bound-bound/extraSpaceOnSidesY, "Order = "+polynomial.getDegree()); //write the order to canvas
        StdDraw.setFont(controlsFont);
        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+bound/extraSpaceOnSidesX, bound-directionsUnderOrder*bound/extraSpaceOnSidesY, "      press ↑, ↓     ");
    }

    public void graphPolynomial(Polynomial polynomial)
    {
        double y = 0;
        double c = polynomial.getCenter();
        int order = polynomial.getDegree();

        for(double x = -bound; x<=bound; x+= spacing)//iterate through the region with the current spacing
        {
            for(int n = 0; n<=order; n++) // determine the y value of the polynomial for a given x point in the region
            {
                //add together terms ( += is adding each term together) which themselves are composed of the product of the nthderivative
                //and the term (i-c)^n which is all divided by n factorial.
                y += (polynomial.getNthDerivative(n)*Math.pow(x-c, n))/polynomial.getFactorial(n);
            }
            //now we have an x coordinate and a function value for the x coordinate for the polynomial
            //yet the function value may be undefined or infinite so we check if it is
            if(!Double.isNaN(y) && !Double.isInfinite(y)) //! denotes not, so if the function favlue is non undefined or infite we graph the point
            {
                StdDraw.setPenColor(polynomialColor); //set pencolor before drawing the point
                StdDraw.setPenRadius(curveRadius); //set the pensize of the curve
                StdDraw.point(x, y);
            }
            //if the function value IS undefined or infinite, then we stop iterating through all x values since we are no longer interested in graphing this polynomial
            else
            {
                break; //by breaking here we will not set y = 0, it will stay undefined, this is why we catch is below
            }
            y = 0; //reset function value move to next x;
        }
        //once we are done drawing the function, we draw the circle which indicates the position of the center of the polynomial
        if(!Double.isNaN(y) && !Double.isInfinite(y))// if the y value was not undefined and thefore we did not break from the loop, then we draw the circle
        {
            StdDraw.setPenColor(circleColor); //draw circle with appropriate color
            StdDraw.filledCircle(c, polynomial.getNthDerivative(0), bound/circleRadius);

            StdDraw.setPenColor(circleOutLineColor); //set color and pen size of circle outline
            StdDraw.setPenRadius(circleOutLineRadius);
            StdDraw.circle(c, polynomial.getNthDerivative(0), bound/circleRadius); //draw cricle at coordinate center, function value
        }
        //if it WAS undefined or infite, meaning we broke from the loop, then we also draw a vertical line from the current center and the circle is placed at y = 0
        else
        {
            StdDraw.setPenColor(polynomialColor); //set pencolor before drawing the point
            StdDraw.setPenRadius(curveRadius); //set the pensize of the curve
            StdDraw.line(c, -bound, c, bound); //vertical line of the color and thickness the polynomial would normally be

            StdDraw.setPenColor(circleColor); //set color for the circle
            StdDraw.filledCircle(polynomial.getCenter(), 0, bound/circleRadius); //draw circle

            StdDraw.setPenColor(circleOutLineColor); //set color and pen size of circle outline
            StdDraw.setPenRadius(circleOutLineRadius);
            StdDraw.circle(polynomial.getCenter(), 0, bound/circleRadius); //draw circle outline
        }
    }

    /**
     * Helper Methods
     *
     */

    public static double round(double value, int places)
    {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}