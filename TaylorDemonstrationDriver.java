package com.jetbrains;
/**
 * Charles Kolozsvary
 * Period 2 Algorithms
 * Java Version 4.2.1
 * Java SDK: 12.01.0
 * 6-13-20
 * Description:
 */
public class TaylorDemonstrationDriver
{
    public static void main(String args[])
    {
        //initialize graph, polynomial, and elementary function objects
        Graph graph = new Graph();
        Polynomial polynomial = new Polynomial();
        ElementaryFunction function = new ElementaryFunction();
        graph.drawSelectionScreen();
        boolean graphing = false; //the main program is nor yet running
        boolean showingSelection = true;
        while(showingSelection)
        {
            if(StdDraw.isKeyPressed(10)) //if the enter key is pressed, totall stop
            {
                showingSelection = false;
                System.exit(0); //the integer given is indicitive of the sucsess of terminating the process
                //0 indicates sucsesfull termination, while 1 and -1 and any other non zero integer demonstrate termination due to an issue
                //or perhaps an unsucsesfull termination
            }
            if(StdDraw.isMousePressed())
            {
                function.setFunction(graph.determineSelection(StdDraw.mouseX(), StdDraw.mouseY()));
                graphing = true;
                graph.clear();
                graph.drawAxisAndInformation(polynomial);
                function.drawElementFunction(graph);
                function.generateNthDerivatives(polynomial);
                graph.graphPolynomial(polynomial);
                graph.show();
                graph.waitInputBuffer();
                graph.waitInputBuffer();
                graph.waitInputBuffer();
            }
            while (graphing)
            {
                //resize graph if needed
                if (StdDraw.isKeyPressed('O')) //zoom out
                {
                    //wipe canvas
                    graph.clear();
                    //change information
                    graph.increaseBound();
                    //Draw
                    graph.drawAxisAndInformation(polynomial);
                    function.drawElementFunction(graph);
                    graph.graphPolynomial(polynomial);
                    //show and wait
                    graph.show();
                    graph.stopTime(graph.getBoundTurbo());
                }
                else if (StdDraw.isKeyPressed('I')) //zoom in
                {
                    //wipe canvas
                    graph.clear();
                    //change information
                    graph.decreaseBound();
                    //Draw
                    graph.drawAxisAndInformation(polynomial);
                    function.drawElementFunction(graph);
                    graph.graphPolynomial(polynomial);
                    //show and wait
                    graph.show();
                    graph.stopTime(graph.getBoundTurbo());
                }
                //change incement if needed
                else if (StdDraw.isKeyPressed('L')) //move faster (left right)
                {
                    //wipe canvas
                    graph.clear();
                    //change information
                    graph.increaseInc();
                    //Draw
                    graph.drawAxisAndInformation(polynomial);
                    function.drawElementFunction(graph);
                    graph.graphPolynomial(polynomial);
                    //show and wait
                    graph.show();
                    graph.stopTime(graph.getIncTurbo());
                } else if (StdDraw.isKeyPressed('K')) //move slower
                {
                    //wipe canvas
                    graph.clear();
                    //change information
                    graph.decreaseInc();
                    //Draw
                    graph.drawAxisAndInformation(polynomial);
                    function.drawElementFunction(graph);
                    graph.graphPolynomial(polynomial);
                    //show and wait
                    graph.show();
                    graph.stopTime(graph.getIncTurbo());
                }
                //change the buffer time after changing center, order, increment, and bound
                //check wether to wait 90 miliseconds or 30 miliseconds after zooming in or out, after increasing or decreasing the order of the graph
                // after increasing or decreasing the increment, or after moving the center of the graph
                else if (graph.checkTurbo()) {
                    //wipe canvas
                    graph.clear();
                    //Draw
                    graph.drawAxisAndInformation(polynomial);
                    function.drawElementFunction(graph);
                    graph.graphPolynomial(polynomial);
                    //show
                    graph.show();
                    //no need to wait more than the general input buffer time here
                }

                if (StdDraw.isKeyPressed(38))  //if up arrow is pressed
                {
                    //wipe canvas
                    graph.clear();
                    //change information
                    polynomial.increaseDegree();
                    //implement new information
                    function.generateNthDerivatives(polynomial);
                    //Draw
                    graph.drawAxisAndInformation(polynomial);
                    function.drawElementFunction(graph);
                    graph.graphPolynomial(polynomial);
                    //show and wait
                    graph.show();
                    graph.stopTime(graph.getOrderTurbo());
                } else if (StdDraw.isKeyPressed(40)) //if down arrow is pressed
                {
                    //wipe canvas
                    graph.clear();
                    //change information
                    polynomial.decreaseDegree();
                    //implement new information
                    function.generateNthDerivatives(polynomial);
                    //Draw
                    graph.drawAxisAndInformation(polynomial);
                    function.drawElementFunction(graph);
                    graph.graphPolynomial(polynomial);
                    //show and wait
                    graph.show();
                    graph.stopTime(graph.getOrderTurbo());
                } else if (StdDraw.isKeyPressed(39)) //if right arrow is pressed
                {
                    //wipe canvas
                    graph.clear();
                    //change information
                    polynomial.moveCenter(graph.getInc());
                    //implement new information
                    function.generateNthDerivatives(polynomial);
                    //Draw
                    graph.drawAxisAndInformation(polynomial);
                    function.drawElementFunction(graph);
                    graph.graphPolynomial(polynomial);
                    //show and wait
                    graph.show();
                    graph.stopTime(graph.getCenterTurbo());
                } else if (StdDraw.isKeyPressed(37)) //if left arrow is pressed
                {
                    //wipe canvas
                    graph.clear();
                    //change information
                    polynomial.moveCenter(-graph.getInc());
                    //implement new information
                    function.generateNthDerivatives(polynomial);
                    //Draw
                    graph.drawAxisAndInformation(polynomial);
                    function.drawElementFunction(graph);
                    graph.graphPolynomial(polynomial);
                    //show and wait
                    graph.show();
                    graph.stopTime(graph.getCenterTurbo());
                }
                else if(StdDraw.isKeyPressed('P'))
                {
                    StdDraw.save(function.getFunction()+" Degree:"+polynomial.getDegree()+" Center: "+polynomial.getCenter()+".png");
                }
                else if (StdDraw.isKeyPressed('Q'))
                {
                    graphing = false;
                    graph.reset();
                    polynomial.completeReset();
                    graph.drawSelectionScreen();
                }
                graph.waitInputBuffer(); //wait a smaller amount of time before checking if there is another keyboard input
            }
        }
    }

}