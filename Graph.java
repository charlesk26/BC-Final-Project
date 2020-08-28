
/**
 * Charles Kolozsvary
 * Period 2 Algorithms
 * Java Version 4.2.1
 * Java SDK: 12.01.0
 * 6-13-20
 * 
 */
import java.awt.Color;
import java.awt.Font;
/**
 * Objects of this class hold a variety of instance fields which pertain to displaying the majority of information on screen at any given time. 
 */
public class Graph
{
    //Everything with static final will not be changed

    /**
     * The names of the image files which are used for displaying the selection screen.
     */
    public static final String[] fileNames= {"root.png", "cubeRoot.png", "rational.png","exponential.png","logarithmic.png","sin.png","sinA.png","cos.png","cosA.png"}; 

    /**
     * The font for displaying the order.
     */
    public static final Font orderFont = new Font("Dialog", Font.ITALIC, 30); 

    /**
     * The font for displaying axis values.
     */
    public static final Font graphFont = new Font("Dialog", Font.ITALIC, 10);

    /**
     * The font for displaying words in the top right of the screen including the center, increment, and graph size.
     */
    public static final Font topRightFont = new Font("Dialog", Font.ITALIC, 20); 

    /**
     * The font for the words in the bottom left of the screen.
     */
    public static final Font bottomLeftFont = new Font("Dialog", Font.ITALIC, 12); 

    /**
     * The font for displaying the controls to the user e.g (press I, O).
     */
    public static final Font controlsFont = new Font("Dialog", Font.ITALIC, 13); 

    /**
     * The number of milliseconds paused for a very small buffer.
     */
    public static final int generalSmallBuffer = 10;

    /**
     * The number of milliseconds paused for a long buffer.
     */
    public static final int highBuffer = 90; 

    /**
     * The number of milliseconds paused for a short buffer.
     */
    public static final int lowBuffer = 30;
    
    /**
     * The number of places numbers are rounded to.
     */
    public static final int roundTo = 10;
    
    /**
     * The modifier on the bound size to make the text move further left for the center, increment and graph size display
     */
    public static final double moreLeft = 2.25;
    
    /**
     * The modifier on the top right spacing (vertically) so that the controls fro changing center, increment, and graph size are placed more downwards
     */
    public static final double extraDownControls = 0.5;
    
    /**
     * The number of times the bound is divided to designate points drawn within the region.
     */
    public static final double divyUpSpacing = 1300;

    /**
     * For moving the order display in the x to the right, lower = more right, higher = more left.
     */
    public static final double extraSpaceOnSidesX = 5;

    /**
     * For moving the orderdisplay up and down, lower is more down.
     */
    public static final double extraSpaceOnSidesY = 10; 
    /**
     * 
     */
    public static final double axisNumberPositioning = 25; //higher = right, lower = left
    /**
     * 
     */
    public static final double graphTickSeparation = 10; //the number of ticks on the graph for the bound in one direction
    /**
     * 
     */
    public static final double graphTick = 60; //effects the size of the ticks on the graph, higher = smaller
    /**
     * 
     */
    public static final double circleRadius = 75; //in relation to bound i.e bound/circleRadius, higher = smaller circle
    /**
     * 
     */
    public static final double boundChangeDegree = 2; //the factor by how much the graph size will change upon a button press (I or O)
    /**
     * 
     */
    public static final double incrementChangeDegree = 2; //the factor by which the increment will change
    /**
     * 
     */
    public static final double maxBound = 8192.0; //the largest size for the graph in one direction (units)
    /**
     * 
     */
    public static final double turboWordsLR = 1.35; //the higher the more right, the lower the more left for the infor displayed in bottom left
    /**
     * 
     */
    public static final double[] bottomLeftWordsSpacing = {2.5,2,1.5,1.0}; //spacing for words in the bottom left of the screen during a visualization
    /**
     * 
     */
    public static final double[] topRightSpacing = {12.5, 14.5, 16.5}; //spacing for words in the top right of the screen during a visualization
    /**
     * 
     */
    public static final double defaultIncrement = 0.015625; //the orriginal amount of distance that the center of the polynomial aproximation will move at the start of the vizualization upon presing left or right
    /**
     * 
     */
    public static final double defaultBound = 4; //the orriginal size of the graph in one direction when starting any visualization
    /**
     * 
     */
    public static final double directionsUnderOrder = 1.5; //meant to keep track of spacing of the directions under the order display, the higher this number the further down the directions will be for up and down arrow key
    /**
     * 
     */
    public static final double curveRadius = .0065; //the pen size for the curves, both polynomial and elementary function
    /**
     * 
     */
    public static final double axisRadius = .002; //the pensize for the graph and ticks
    /**
     * 
     */
    public static final double circleOutLineRadius = .003; //the pen size for the outline arounf the circle which indicated the center
    /**
     * 
     */
    public static final Color orderColor = new Color(243, 236, 24); //the color of the display for the current order
    /**
     * 
     */
    public static final Color polynomialColor = new Color(159,55,150); //the color of the polynomial curve
    /**
     * 
     */
    public static final Color axisColor = new Color(180,180,180); //the color of the graph and ticks
    /**
     * 
     */
    public static final Color clearColor = new Color(40,40,40); //the color of the backround, what clears the screen after animation loop
    /**
     * 
     */
    public static final Color circleColor = new Color(100,240,150); //the color of the circle which indicates the center
    /**
     * 
     */
    public static final Color circleOutLineColor = new Color(80,80,80);  //the color of the outline of the circle which indicates the center
    /**
     * 
     */
    public static final Color turboColor = new Color(205,31,68); //color which shows true or false in left bottom corner
    /**
     * 
     */
    public static final Color turboWordsColor = new Color(240,240,240); //color for the words around the true or false and also for the other directions
    /**
     * 
     */
    public static final Color centerColor = new Color(33,175,75); //color for the display of curent center
    /**
     * 
     */
    public static final Color incColor = new Color(163,65,152); //color for display of current increment
    /**
     * the color of the words for "Graph Size" and relating numbers
     */
    public static final Color boundColor = new Color(21,118,187); //color for display of the bound

    //Instance fields (mutable) below
    private double spacing; //distance between points on a curve
    private double increment; //the increment of traversal, this is different from spacing in that this controls what next series will be generated not how many points themselves are generated
    private double bound; //the bound of display in both x and y direction

    //if any turbo is true, the particular variable (bound, increment, order, center) will change more rapidly (ofter providing a smoother vizualisation at the cost of precision)
    private boolean incTurbo;  // whether or not you will be able to change the increment quickly (but with less control) or not
    private boolean boundTurbo; // whether or not you will be able to change the graph size smoothyly (but with less control) or not
    private boolean orderTurbo; //  whether or not you will be able to change the order of the polynomial function smoothyly (but with less control) or not
    private boolean centerTurbo; //  whether or not you will be able to change the graph size smoothyly (but with less control) or not

    private double middle; //keeps track of the "middle of the graph", this is easier than typing out bound/3 every time

    /**
     * Initializes and assigns the instance fields of a Graph object when created. No paramaters are required; the value of
     * instance fields are set to a particular default when beggining any visualization, yet can be changed once the visualization has started.
     */
    public Graph()
    {
        // initialise instance variables
        this.orderTurbo = false;
        this.centerTurbo = true; //default with smooth movement of center
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

    /**
     * This method resets the instance fields (and the scaling of StdDraw) back to their default values;
     * this is very similar in functionality to a constructor, yet does not require that we instantiate another
     * object of the graph class to provide default values for the instance fields; this reduces redundancy,
     */
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

    /**
     * Draw the selection screen
     */
    public void drawSelectionScreen()
    {   
        StdDraw.clear(new Color(50,50,50));
        StdDraw.setPenColor(0,0,0);
        StdDraw.line(-middle, -bound, -middle, bound); //left line
        StdDraw.line(-bound, middle, bound, middle); // top line
        StdDraw.line(-bound, -middle, bound, -middle); //bottom line
        StdDraw.line(middle, -bound, middle, bound); //right line
        //The following lines draw the pictures of the graphs on the selection screen going from top left to bottom right
        StdDraw.picture(-bound+bound/3, bound-bound/3, fileNames[0], 2*bound/3.25,2*bound/3.25); //draw top left graph (square root)
        StdDraw.picture(-bound+3*bound/3, bound-bound/3, fileNames[1], 2*bound/3.25,2*bound/3.25); //draw top middle graph (cube root)
        StdDraw.picture(-bound+5*bound/3, bound-bound/3, fileNames[2], 2*bound/3.25,2*bound/3.25); //draw top right graph (rational)
        StdDraw.picture(-bound+bound/3, 0, fileNames[3], 2*bound/3.25,2*bound/3.25); //draw middle left graph (exponential)
        StdDraw.picture(-bound+3*bound/3, 0, fileNames[4], 2*bound/3.25,2*bound/3.25); //draw middle middle graph (logarithmic)
        StdDraw.picture(-bound+5*bound/3, 0, fileNames[5], 2*bound/3.25,2*bound/3.25); //draw middle right graph (sin x)
        StdDraw.picture(-bound+bound/3, -bound+bound/3, fileNames[6], 2*bound/3.25,2*bound/3.25); //draw bottom left graph (sin(ax))
        StdDraw.picture(-bound+3*bound/3, -bound+bound/3, fileNames[7], 2*bound/3.25,2*bound/3.25); //draw bottom middle graph (cos x)
        StdDraw.picture(-bound+5*bound/3, -bound+bound/3, fileNames[8], 2*bound/3.25,2*bound/3.25); //draw bottom right graph (cos (ax))

        StdDraw.show(); //show everything that was drawn
    }

    /**
     * Based on where the user clicked, determine the proper visualization
     */
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
        else //this should never happen, but its just there because you need to make sur4e that regardless, something must be returned
        {
            return "";
        }
    }

    /**
     * Check if A Z S X D or C is pressed to change the value of the booleans for turbo
     * returns whether or not a button was pressed to change a particular "turbo" instance field.
     */
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
        else if(StdDraw.isKeyPressed('S') && !centerTurbo) //same thing as above, different key
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
     * Getter method, retrieve the instance field "spacing" which is the
     * number of unique x coordinates which will be accounted for when drawing the graph.
     */
    public double getSpacing()
    {
        return spacing; //returns the spacing, the x distances between points drawn on a curve
    }

    /**
     * Getter method, retrieve the instance field "increment"
     * "increment" is the distance the user will change the center
     * of the taylor polynomial after pressing left or right arrow key.
     */
    public double getInc()
    {
        return increment; //returns the increment, the change in center for the polynomial induced by the press of left or right
    }

    /**
     * Getter method, returns instance field "bound"
     * "bound" is the size of the graph in a particular direction
     * a "bound" of 10 will mean the largest positive or negative number
     * displayed on the x axis is 10, same for the y axis.
     */
    public double getBound()
    {
        return bound; //the region of the graph being displayed
    }

    /**
     * Getter method, returns instance field "orderTurbo".
     */
    public boolean getOrderTurbo()
    {
        return orderTurbo;
    }

    /**
     * Getter method, returns instance field "centerTurbo".
     */
    public boolean getCenterTurbo()
    {
        return centerTurbo;
    }

    /**
     * Getter method, returns instance field "incTurbo".
     */
    public boolean getIncTurbo()
    {
        return incTurbo;
    }

    /**
     * Getter method, returns instance field "boundTurbo"
     * When any "turbo" instance field equals "true" the amount of buffer time after an input to
     * alter a mutable aspect of the visualization (order, center, increment, bound) will be greater than if
     * it equals "false".
     */
    public boolean getBoundTurbo()
    {
        return boundTurbo;
    }

    /**
     * Stops the program for either a short or long period.
     * 
     * @param turbo the boolean which controls whether the buffer duration will be longer or shorter; if true, longer, if false, shorter
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
     * Sets the value (true or false) for instance field "orderTurbo".
     */
    public void setOrderTurbo(boolean onOff) //given a boolean, set as that boolean
    {
        this.orderTurbo = onOff;
    }

    /**
     * Sets the value (true or false) for instance field "centerTurbo".
     */
    public void setCenterTurbo(boolean onOff)
    {
        this.centerTurbo = onOff;
    }

    /**
     * Sets the value (true or false) for instance field "incTurbo".
     */
    public void setIncTurbo(boolean onOff)
    {
        this.incTurbo = onOff;
    }

    /**
     * Sets the value (true or false) for instance field "boundTurbo".
     */
    public void setBoundTurbo(boolean onOff)
    {
        this.boundTurbo = onOff;
    }

    /**
     * Increases the value of bound in proportion to its former value;  bound = bound multiplied by the static final varibale "boundChangeDegree".
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

    /**
     * Decreases the value of bound in proportion to its former value; bound = bound divided by the static final varibale "boundChangeDegree".
     */
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
     * Increases the value of increment in proportion to its former value; increment = increment multiplied by the static final varibale "incrementChangeDegree".
     */
    public void increaseInc()
    {
        this.increment = increment*incrementChangeDegree;
    }

    /**
     * Decreases the value of increment in proportion to its former value; increment = increment divided by the static final varibale "incrementChangeDegree".
     */
    public void decreaseInc()
    {
        if(increment/incrementChangeDegree > 0.0)
        {
            this.increment = increment/incrementChangeDegree;
        }
    }

    /**
     * Shows what has been drawn to the canvas.
     */
    public void show()
    {
        StdDraw.show();
    }

    /**
     * Wipes the canvas; colors in the entire drawing region to the specified color.
     */
    public void clear()
    {
        StdDraw.clear(clearColor);
    }

    /**
     * Draws a point on the canvas; the size is dependant on penSize.
     * 
     * @param x the coordinate of the point
     * @param y the y coordinate of the point
     */
    public void drawPoint(double x, double y)
    {
        StdDraw.point(x, y);
    }

    /**
     * Pauses the program for the number of milliseconds specified by final static variable "generalSmallBuffer".
     */
    public void waitSmallBuffer() 
    {
        StdDraw.pause(generalSmallBuffer);
    }

    /**
     * Draws the the axis and all information on screen.
     * 
     * @param polynomial the polynomial object which provides information regarding the center and order of the polynomial; for display
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

        //all of this displays whether or not the higher buffer is on for changing order or changing center, so
        //you can easily change from being able to move precisely and seeing a faster animation
        StdDraw.setFont(bottomLeftFont);

        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+bottomLeftWordsSpacing[0]*bound/extraSpaceOnSidesY, "  Order Turbo:"+"                              ");
        StdDraw.setPenColor(turboColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+bottomLeftWordsSpacing[0]*bound/extraSpaceOnSidesY, "     "+orderTurbo+" ");
        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+bottomLeftWordsSpacing[0]*bound/extraSpaceOnSidesY, "                "+"               press A, Z");

        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+bottomLeftWordsSpacing[1]*bound/extraSpaceOnSidesY, "  Center Turbo:"+"                              ");
        StdDraw.setPenColor(turboColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+bottomLeftWordsSpacing[1]*bound/extraSpaceOnSidesY, "     "+centerTurbo+" ");
        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+bottomLeftWordsSpacing[1]*bound/extraSpaceOnSidesY, "                "+"               press S, X");

        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+bottomLeftWordsSpacing[2]*bound/extraSpaceOnSidesY, "Increment Turbo:"+"                               ");
        StdDraw.setPenColor(turboColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+bottomLeftWordsSpacing[2]*bound/extraSpaceOnSidesY, "     "+incTurbo+" ");
        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+bottomLeftWordsSpacing[2]*bound/extraSpaceOnSidesY, "                "+"               press D, C");

        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+bottomLeftWordsSpacing[3]*bound/extraSpaceOnSidesY, " Graph Turbo:"+"                              ");
        StdDraw.setPenColor(turboColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+bottomLeftWordsSpacing[3]*bound/extraSpaceOnSidesY, "      "+boundTurbo+" ");
        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(-bound+turboWordsLR*bound/extraSpaceOnSidesX, -bound+bottomLeftWordsSpacing[3]*bound/extraSpaceOnSidesY, "                "+"               press F, V");

        //Display the current center of the polynomial, increment and size of the graph
        StdDraw.setFont(topRightFont);
        StdDraw.setPenColor(centerColor);
        StdDraw.text(bound-bound*moreLeft/extraSpaceOnSidesX, bound-topRightSpacing[0]*bound/extraSpaceOnSidesY, "Center = "+round(polynomial.getCenter(),roundTo));
        StdDraw.setFont(controlsFont);
        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(bound-bound*moreLeft/extraSpaceOnSidesX, bound-(topRightSpacing[0]+extraDownControls)*bound/extraSpaceOnSidesY, "      press ←, →     ");

        StdDraw.setFont(topRightFont);
        StdDraw.setPenColor(incColor);
        StdDraw.text(bound-bound*moreLeft/extraSpaceOnSidesX, bound-topRightSpacing[1]*bound/extraSpaceOnSidesY, "Incremnt = "+round(increment,roundTo));

        StdDraw.setFont(controlsFont);
        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(bound-bound*moreLeft/extraSpaceOnSidesX, bound-(topRightSpacing[1]+extraDownControls)*bound/extraSpaceOnSidesY, "      press K, L     ");

        StdDraw.setFont(topRightFont);
        StdDraw.setPenColor(boundColor);
        StdDraw.text(bound-bound*moreLeft/extraSpaceOnSidesX, bound-topRightSpacing[2]*bound/extraSpaceOnSidesY, "Graph Size = "+round(bound,roundTo));

        StdDraw.setFont(controlsFont);
        StdDraw.setPenColor(turboWordsColor);
        StdDraw.text(bound-bound*moreLeft/extraSpaceOnSidesX, bound-(topRightSpacing[2]+extraDownControls)*bound/extraSpaceOnSidesY, "      press I, O     ");

        StdDraw.setFont(orderFont); //set font for order display
        StdDraw.setPenColor(orderColor); //set color for order display
        StdDraw.text(-bound+bound/extraSpaceOnSidesX, bound-bound/extraSpaceOnSidesY, "Order = "+polynomial.getDegree()); //write the order to canvas
        StdDraw.setFont(controlsFont); //set the font to the font for the controls
        StdDraw.setPenColor(turboWordsColor); //set the color back to the grey color for controls/ turbo display
        StdDraw.text(-bound+bound/extraSpaceOnSidesX, bound-directionsUnderOrder*bound/extraSpaceOnSidesY, "      press ↑, ↓     "); //print the text for the controls
    }

    /**
     * Draws the taylor polynomial on to the canvas.
     * 
     * @param polynomial the polynomial object which will be utilized to display the taylor polynomial (given the center and order)
     */
    public void graphPolynomial(Polynomial polynomial)
    {
        double y = 0; //initialize and assign the function value as zero, this will be concatinated later on; it will be built upon
        double c = polynomial.getCenter(); //initialize and assign variable for center for more oraginaztion and reduced redundancy
        int order = polynomial.getDegree(); //same as above initialization

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
     * Rounds a double to a particular number of places.
     * This method helps manage the information on screen 
     * @param value the decimal value
     * @param places the number of places the decimal value will be rounded to
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
