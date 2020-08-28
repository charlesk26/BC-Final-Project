
/**
 * Charles Kolozsvary
 * Period 2 Algorithms
 * Java Version 4.2.1
 * Java SDK: 12.01.0
 * 6-13-20
 * Description:
 * This class will be responsible for managing all of the elementary functions which the user can select from.
 * This includes drawing each elementary function on the graph and generating particluar nth derivatives for each function.
 * The only instance field of this class is the function name. Depending on the value of this string, the proper elementary function will be drawn and the the proper Nth derivatives for that function will be generated.
 */
import java.awt.Color;
public class ElementaryFunction
{
    //Useful static final varibales for easy and quick adjustments
    private static final int a1 = 5; //the coefficient of the sin function (SinA)
    private static final int a2 = 8; //the coefficient for the cos function (CosA)
    private static final Color funcColor = new Color(19,119,187); //the color of the elementary function curve
    private static final double curveRadius = .0065; //the pen size for the curves, both polynomial and elementary function

    //Instance field! The only one for this class!
    private String function;

    /**
     * Constructor; the function name is initialized to null.
     * This is done because the elementary function object is initialized before user prefrenece and the value of this string will change often.
     */
    ElementaryFunction()
    {
        this.function = null; //starts as null
    }

    /**
     * Assign the value of the function instance field.
     * @param n the string which will corelate to elementary function selected
     */
    public void setFunction(String n)
    {
        this.function = n;
    }
    
    /**
     * Return the string which demonstrates the current elementary function selected
     */
    public String getFunction() 
    {
        return this.function;
    }

    /**
     * Return the double factorial for a given number.
     * @param n the number which's double factorial will be returned
     */
    static double doubleFactorial(double n)
    {
        if (n == 0 || n==1)
        {
            return 1;
        }
        return n * doubleFactorial(n - 2);
    }
    
    /**
     * Return the factorial of a given number.
     * @param n the number which's factorial will be returned
     */
    static long factorial(long n)
    {
        if (n == 0)
        {
            return 1;
        }
        return n * factorial(n-1);
    }

    /**
     * Generate the Nth derivatives of the function depending on the attribues of the current taylor polynomial and add it to the working description.
     * @param polynomial The taylor polynomial centered at a given c and is of a particular order; this information will determine what and how many derivatives will be evaluated
     */
    public void generateNthDerivatives(Polynomial polynomial) 
    {
        double c = polynomial.getCenter(); //where the curve is centered
        int order = polynomial.getDegree(); //the order of the polynomial
        if(function.equals("root")) 
        {
            if(order == 0)
            {
                polynomial.addNth(Math.sqrt(c)); //the zeroth derivative of a function at c is just the function value
            }
            else if(order == 1)
            {
                polynomial.addNth(Math.sqrt(c)); //the zeroth derivative of a function at c is just the function value
                polynomial.addNth(1/(2*Math.sqrt(c))); //the first derivative of root x is just 1/2 * 1/root x
            }
            else
            {
                polynomial.addNth(Math.sqrt(c)); //the zeroth derivative of a function at c is just the function value
                polynomial.addNth(1/(2*Math.sqrt(c))); //the first derivative of root x is just 1/2 * 1/root x
                for(int n = 2; n<=order; n++)
                {
                    double numerator = Math.pow(-1, n-1) * doubleFactorial((2*n)-3) * Math.sqrt(c);
                    double denominator = Math.pow(2*c, n);
                    polynomial.addNth(numerator/denominator);
                }
            }
        }
        else if(function.equals("cubeRoot"))
        {
            if(order == 0)
            {
                polynomial.addNth(Math.cbrt(c)); //the zeroth derivative of a function at c is just the function value
            }
            else if(order == 1)
            {
                polynomial.addNth(Math.cbrt(c)); //the zeroth derivative of a function at c is just the function value
                polynomial.addNth(((double)1)/(3*Math.pow(Math.cbrt(c),2))); //the first derivative of root x is just 1/3 * 1/(cuberoot x )^2
            }
            else
            {
                polynomial.addNth(Math.cbrt(c)); //the zeroth derivative of a function at c is just the function value
                polynomial.addNth(((double)1)/(3*Math.pow(Math.cbrt(c),2))); //the first derivative of root x is just 1/3 * 1/(cuberoot x )^2
                for(int n = 2; n<=order; n++)
                {
                    double numerator = Math.pow(-1, n-1) * ((3*n)-4) * Math.cbrt(c);
                    double denominator = Math.pow(3*c, n);
                    polynomial.addNth(numerator/denominator);
                }
            }
        }
        else if(function.equals("rational"))
        {
            if(order == 0)
            {
                polynomial.addNth(1/(c)); //the zeroth derivative of a function at c is just the function value
            }
            else
            {
                polynomial.addNth(1/c); //the zeroth derivative of a function at c is just the function value
                for(int n = 1; n<=order; n++)
                {
                    double numerator = Math.pow(-1, n) * factorial(n);
                    double denominator = Math.pow(c, n+1);
                    polynomial.addNth(numerator/denominator);
                }
            }
        }
        else if(function.equals("exponential"))
        {
            if(order == 0)
            {
                polynomial.addNth(c*Math.pow(Math.E, c));
            }
            else
            {
                polynomial.addNth(c*Math.pow(Math.E, c));
                for(int n = 1; n<= order; n++)
                {
                    polynomial.addNth((c+n)*Math.pow(Math.E, c));
                }
            }
        }
        else if(function.equals("logarithmic"))
        {
            if(order == 0)
            {
                polynomial.addNth(Math.log(c));
            }
            else
            {
                polynomial.addNth(Math.log(c));
                for(int n = 1; n<= order; n++)
                {
                    double numerator = Math.pow(-1, n-1)*factorial(n-1);
                    double denominator = Math.pow(c, n);
                    polynomial.addNth(numerator/denominator);
                }
            }
        }
        else if(function.equals("sin"))
        {
            if(order == 0)
            {
                polynomial.addNth(Math.sin(c));
            }
            else
            {
                polynomial.addNth(Math.sin(c));
                for(int n = 1; n<= order; n++)
                {
                    polynomial.addNth(Math.sin(c+ ((n*Math.PI)/2)));
                }
            }
        }
        else if(function.equals("sinA"))
        {
            if(order == 0)
            {
                polynomial.addNth(Math.sin(a1*c));
            }
            else
            {
                polynomial.addNth(Math.sin(a1*c));
                for(int n = 1; n<= order; n++)
                {
                    polynomial.addNth(Math.pow(a1, n)*Math.sin((a1*c)+ ((n*Math.PI)/2)));
                }
            }
        }
        else if(function.equals("cos"))
        {
            if(order == 0)
            {
                polynomial.addNth(Math.cos(c));
            }
            else
            {
                polynomial.addNth(Math.cos(c));
                for(int n = 1; n<= order; n++)
                {
                    polynomial.addNth(Math.cos(c+ ((n*Math.PI)/2)));
                }
            }
        }
        else if(function.equals("cosA"))
        {
            if(order == 0)
            {
                polynomial.addNth(Math.cos(a2*c));
            }
            else
            {
                polynomial.addNth(Math.cos(a2*c));
                for(int n = 1; n<= order; n++)
                {
                    polynomial.addNth(Math.pow(a2, n)*Math.cos((a2*c)+ ((n*Math.PI)/2)));
                }
            }
        }
    }

    /**
     * Draw the elementary function as it exists soley.
     * @param graph The graph object which will be used to draw points of the elementary function onto the graph
     */
    public void drawElementFunction(Graph graph)
    {
        if(function.equals("root"))
        {
            for(double i = 0; i<graph.getBound(); i+=graph.getSpacing())
            {
                StdDraw.setPenColor(funcColor); //set color of the function curve
                StdDraw.setPenRadius(curveRadius); //set the pensize for the curve
                graph.drawPoint(i, Math.sqrt(i));
            }
        }
        else if(function.equals("cubeRoot"))
        {
            for(double i = -graph.getBound(); i<graph.getBound(); i+=graph.getSpacing())
            {
                StdDraw.setPenColor(funcColor); //set color of the function curve
                StdDraw.setPenRadius(curveRadius); //set the pensize for the curve
                graph.drawPoint(i, Math.cbrt(i));
            }
        }
        else if(function.equals("rational"))
        {
            for(double i = -graph.getBound(); i<graph.getBound(); i+=graph.getSpacing())
            {
                StdDraw.setPenColor(funcColor); //set color of the function curve
                StdDraw.setPenRadius(curveRadius); //set the pensize for the curve
                graph.drawPoint(i, 1/i);
            }
        }
        else if(function.equals("exponential"))
        {
            for(double i = -graph.getBound(); i<graph.getBound(); i+=graph.getSpacing())
            {
                if(Double.isNaN(i*Math.pow(Math.E, i)) || Double.isInfinite(i*Math.pow(Math.E, i)))
                {
                    continue;
                }
                else
                {
                    StdDraw.setPenColor(funcColor); //set color of the function curve
                    StdDraw.setPenRadius(curveRadius); //set the pensize for the curve
                    graph.drawPoint(i, i*Math.pow(Math.E, i));
                }
            }
        }
        else if(function.equals("logarithmic"))
        {
            for(double i = 0; i<graph.getBound(); i+=graph.getSpacing())
            {
                if(Double.isNaN(Math.log(i)) || Double.isInfinite(Math.log(i)))
                {
                    continue;
                }
                else
                {
                    StdDraw.setPenColor(funcColor); //set color of the function curve
                    StdDraw.setPenRadius(curveRadius); //set the pensize for the curve
                    graph.drawPoint(i, Math.log(i));
                }
            }
        }
        else if(function.equals("sin"))
        {
            for(double i = -graph.getBound(); i<graph.getBound(); i+=graph.getSpacing())
            {
                StdDraw.setPenColor(funcColor); //set color of the function curve
                StdDraw.setPenRadius(curveRadius); //set the pensize for the curve
                graph.drawPoint(i, Math.sin(i));
            }
        }
        else if(function.equals("sinA"))
        {
            for(double i = -graph.getBound(); i<graph.getBound(); i+=graph.getSpacing())
            {
                StdDraw.setPenColor(funcColor); //set color of the function curve
                StdDraw.setPenRadius(curveRadius); //set the pensize for the curve
                graph.drawPoint(i, Math.sin(a1*i));
            }
        }
        else if(function.equals("cos"))
        {
            for(double i = -graph.getBound(); i<graph.getBound(); i+=graph.getSpacing())
            {
                StdDraw.setPenColor(funcColor); //set color of the function curve
                StdDraw.setPenRadius(curveRadius); //set the pensize for the curve
                graph.drawPoint(i, Math.cos(i));
            }
        }
        else if(function.equals("cosA"))
        {
            for(double i = -graph.getBound(); i<graph.getBound(); i+=graph.getSpacing())
            {
                StdDraw.setPenColor(funcColor); //set color of the function curve
                StdDraw.setPenRadius(curveRadius); //set the pensize for the curve
                graph.drawPoint(i, Math.cos(a2*i));
            }
        }
    }
}
/*
 * NO OUTPUT (Text based that is)
 */

