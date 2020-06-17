package com.jetbrains;
/**
 * Charles Kolozsvary
 * Period 2 Algorithms
 * Java Version 4.2.1
 * Java SDK: 12.01.0
 * 6-13-20
 * Description:
 * This class will act as a parent function for the selection of Elementary Functions,
 * it will have general methods yet the actual implementation will be particular to individual functions.
 * Any methods with implementations will be inherited for ease of use and to reduce repitition among child classes.
 */
import java.awt.Color;
public class ElementaryFunction
{
    private static final int a = 5;
    private static final int b = 8;
    protected static final Color funcColor = new Color(19,119,187); //the color of the elementary function curve
    protected static final double curveRadius = .0065; //the pen size for the curves, both polynomial and elementary function
    private String function;

    ElementaryFunction()
    {
        this.function = null; //starts as null
    }

    public void setFunction(String n)
    {
        this.function = n;
    }

    public String getFunction() {
    return this.function;
    }

    /**
     * helper math functions
     */
    static double doubleFactorial(double n)
    {
        if (n == 0 || n==1)
        {
            return 1;
        }
        return n * doubleFactorial(n - 2);
    }

    static long factorial(long n)
    {
        if (n == 0)
        {
            return 1;
        }
        return n * factorial(n-1);
    }

    public void generateNthDerivatives(Polynomial polynomial) //create the nth derivatives of the function for given a polynomial centered at c and of degree n
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
                polynomial.addNth(Math.sin(a*c));
            }
            else
            {
                polynomial.addNth(Math.sin(a*c));
                for(int n = 1; n<= order; n++)
                {
                    polynomial.addNth(Math.pow(a, n)*Math.sin((a*c)+ ((n*Math.PI)/2)));
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
                polynomial.addNth(Math.cos(b*c));
            }
            else
            {
                polynomial.addNth(Math.cos(b*c));
                for(int n = 1; n<= order; n++)
                {
                    polynomial.addNth(Math.pow(b, n)*Math.cos((b*c)+ ((n*Math.PI)/2)));
                }
            }
        }
    }

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
                StdDraw.setPenColor(funcColor); //set color of the function curve
                StdDraw.setPenRadius(curveRadius); //set the pensize for the curve
                graph.drawPoint(i, i*Math.pow(Math.E, i));
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
                graph.drawPoint(i, Math.sin(a*i));
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
                graph.drawPoint(i, Math.cos(b*i));
            }
        }
    }
}