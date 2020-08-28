
/**
 * Charles Kolozsvary
 * Period 2 Algorithms
 * Java Version 4.2.1
 * Java SDK: 12.01.0
 * 6-13-20
 * Description:
 */
/**
 * No objects of this class will be created. The sole purpose of this class is to run the "main" method which is why it is denoted as a "driver class".
 * The "main" method, "public static void main(String [] args)", might look like a lot of jargon
 * but its much more managable (and understandable) when broken down. Public denotes acess to this class among classes in this directory.
 * By being public, this class can be easily accessed, as opposed to if private was used. 
 * "static" means that the method can be called withought instantiating an object of this class. "void" is what will be returned by the method, so in this case, nothing.
 * "main" denotes the method name. The name "main" is very particular, by convention, it will directly outline
 * what will be executed when running "the program" as a whole.
 */
public class TaylorDemonstrationDriver
{
    /**
     * Runs the "program".
     * @param args the standard parameter of all "main" methods; this is convention. No arguments required for execution; just click "OK".
     */
    public static void main(String[] args)
    {
        //initialize graph, polynomial, and elementary function objects here so additional initialization will not be necessary and prevent potential redundancies
        Graph graph = new Graph();
        Polynomial polynomial = new Polynomial();
        ElementaryFunction function = new ElementaryFunction();
        //(below) draw the selection screen (only once, no need to update since the screen will not change based on user input, that is, untill they have clicked their mouse)
        graph.drawSelectionScreen();

        boolean showingSelection = true; //this boolean will denote wether the selection screen is being shown, it is a little redundant since we exit the program by pressing enter on the selection screen or clcicking the "x" in the top right, but it doesn't waste much memory being a single variable and it is not repeaditley accessed
        boolean graphing = false; //this boolean denotes wether a particular visualization in happening, it is true when the graphs are being displayed (with user interaction) it is false during the selection screen
        
        while(showingSelection)
        {
            /**
             * Display Selection menue and wait for mouse to be clicked
             */
            if(StdDraw.isKeyPressed(10)) //if the enter key is pressed, program is closed
            {
                showingSelection = false;
                System.exit(0); //the integer given is indicitive of the sucsess of terminating the process
                //0 indicates sucsesfull termination, while 1 and -1 and any other non zero integer demonstrate termination due to an issue
                //or perhaps an unsucsesfull termination
            }
            if(StdDraw.isMousePressed()) //if the mouse if clicked
            {
                function.setFunction(graph.determineSelection(StdDraw.mouseX(), StdDraw.mouseY())); //determine which function was chosen, and set the value appropriatley
                graphing = true; //run visualization sequence, allow for below while loop to be executed
                function.generateNthDerivatives(polynomial);
                //below is first animation/ update sequence for the visualization
                update(graph, polynomial, function);
                //(below) wait 15 miliseconds before the vizualization for consistancy
                graph.waitSmallBuffer(); 
            }
            /**
             * VISUALIZATION HAS STARTED
             */
            while (graphing) //while in a particular visualization
            {
                /**
                 * The following inputs will change graph size
                 */
                if (StdDraw.isKeyPressed('O')) //zoom out
                {
                    graph.increaseBound(); //change information
                    //above is unique to each case
                    update(graph, polynomial, function); //take care of graphing
                    graph.stopTime(graph.getBoundTurbo());
                    graph.waitSmallBuffer();
                }
                else if (StdDraw.isKeyPressed('I')) //zoom in
                {
                    graph.decreaseBound(); //change information
                    //above is unique to each case
                    update(graph, polynomial, function); //take care of graphing
                    graph.stopTime(graph.getBoundTurbo());
                    graph.waitSmallBuffer();
                }
                /**
                 * The following inputs will change incement
                 */
                else if (StdDraw.isKeyPressed('L')) //move faster (left right)
                {
                    graph.increaseInc(); //change information
                    //above is unique to each case
                    update(graph, polynomial, function); //take care of graphing
                    graph.stopTime(graph.getIncTurbo());
                } 
                else if (StdDraw.isKeyPressed('K')) //move slower (left right)
                {
                    graph.decreaseInc(); //change information
                    //above is unique to each case
                    update(graph, polynomial, function); //take care of graphing
                    graph.stopTime(graph.getIncTurbo());
                }

                /**
                 * Checking if the time interval after ANY change was changed; Checks if the "TURBO" selection has been changed, if so change it (Still in check turbo method) then return to driver and redraw graph aspects
                 * change the buffer time after changing center, order, increment, and bound
                 * check wether to wait 90 miliseconds or 30 miliseconds after zooming in or out, after increasing or decreasing the order of the graph
                 * after increasing or decreasing the increment, or after moving the center of the graph
                 */
                else if (graph.checkTurbo()) 
                {   
                    //only thing needed to be done here is update the graph
                    update(graph, polynomial, function);
                    graph.waitSmallBuffer(); //wait a smaller amount of time before checking if there is another keyboard input
                }
                /**
                 * The following inputs will change the ORDER of the taylor polynomial
                 */
                if (StdDraw.isKeyPressed(38))  //if up arrow is pressed //order is increased
                {
                    polynomial.increaseDegree(); //change information
                    function.generateNthDerivatives(polynomial); //implement new information
                    //above is unique to each case
                    update(graph, polynomial, function); //take care of graphing
                    graph.stopTime(graph.getOrderTurbo()); //wait the proper amount of time depending on wether or not turbo is acitvated
                    graph.waitSmallBuffer();
                } 
                else if (StdDraw.isKeyPressed(40)) //if down arrow is pressed //order is decreased
                {
                    polynomial.decreaseDegree(); //change information
                    function.generateNthDerivatives(polynomial); //implement new information
                    //above is unique to each case
                    update(graph, polynomial, function); //take care of graphing
                    graph.stopTime(graph.getOrderTurbo()); //unique to this
                    graph.waitSmallBuffer();
                }
                /**
                 * The following inputs will change the CENTER of the taylor polynomial
                 */
                else if (StdDraw.isKeyPressed(39)) //if right arrow is pressed //change center to move right for given increment
                {
                    polynomial.moveCenter(graph.getInc()); //change information
                    function.generateNthDerivatives(polynomial); //implement new information
                    //above is unique to each case
                    update(graph, polynomial, function); //take care of graphing
                    graph.stopTime(graph.getCenterTurbo()); //unique to this
                } 
                else if (StdDraw.isKeyPressed(37)) //if left arrow is pressed same as above just left
                {
                    polynomial.moveCenter(-graph.getInc()); //change information
                    function.generateNthDerivatives(polynomial); //implement new information
                    //above is unique to each case
                    update(graph, polynomial, function); //take care of graphing
                    graph.stopTime(graph.getCenterTurbo()); //unique to this
                }
                /**
                 * if P is pressed, a screen capture will be made, and saved in the project directory
                 */
                else if(StdDraw.isKeyPressed('P')) 
                {
                    String fileName = new String(""+function.getFunction()+" center "+polynomial.getCenter()+" order "+polynomial.getDegree());
                    StdDraw.save(fileName+".png");
                }
                /**
                 * return to the selection of visualizations for taylore polynomials
                 */
                else if (StdDraw.isKeyPressed('Q')) 
                {
                    graphing = false;
                    graph.reset();
                    polynomial.completeReset();
                    graph.drawSelectionScreen();
                }
            }
        }
    }
    /**
     * Update the visualization.
     * This static method will execute a general animation cycle, this is meant to reduce redundancy in the code;
     * the "static" means that no object of the class must be instantiated to use the method.
     * Hence, the instance fields pertaining to an object of a class are not required
     * 
     * @param graph the graph object which will be utilized to display the information for the visualziation
     * @param polynomial the object which holds information essentail towards drawing the taylor polynomial
     * @param function the elementary function which is being approximated by the taylor polynomial
     */
    public static void update(Graph graph, Polynomial polynomial, ElementaryFunction function)
    {
        //wipe canvas
        graph.clear();
        //Draw
        //graph.drawAxisAndInformation(polynomial);
        function.drawElementFunction(graph);
        graph.graphPolynomial(polynomial);
        graph.drawAxisAndInformation(polynomial);
        //show
        graph.show();
    }
}
/**
 * Nothing to see here...
 * Run the program if you want to see something! (void main(String[] args))
 */