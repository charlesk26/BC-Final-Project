
/**
 * Charles Kolozsvary
 * Period 2 Algorithms
 * Java Version 4.2.1
 * Java SDK: 12.01.0
 * 6-13-20
 * Description:
 * This class will create objects of particular polynomials which are modeling a particular function.
 * Each term within a polynomial is composed of the product between the nth derivative, the (x-c) to the nth power which is then all divided by n factorial.
 * For the purpose of efficiency, I will be storing all values of N factorial (for the highest number of terms) so that they do not need to be generated
 * over and over again. The Nth degree derivative per term will constantly be changing depending on where the polynomial is centered.
 * Thus I think it is most beneficial to make the Nth derivative an arraylist, where depending on the degree of the power series,
 * I will add subsequent nth derivatives.
 */
import java.util.ArrayList;
public class Polynomial
{
    /**
     * The maximum degree for a given taylor polynomial for the purposes of this demonstration
     */
    public static final int maxDeg = 21;

    //initialize instance fields
    private double center; //the center of the taylor polynomial
    private int degree; //the number of terms the current taylor polynomial has
    private ArrayList<Double> nthDerivatives; //the nth Dericative of a function at a point f(c)
    private double[] factorials; //an array of all the factorials which will be used for generating the taylor polynomial
    /**
     * Construct a new taylor polynomial object.
     * A taylor polynomial object is determined by its center, degree, and Nth derivatives.
     * The factorials are simply part of the coefficents for each term in every taylor polynomial.
     */
    public Polynomial()
    {
        //initialize instance fields
        this.center = 0;
        this.degree = 0;
        this.nthDerivatives = new ArrayList<>();
        this.factorials = new double[maxDeg];
        buildFactorials(); //build all of the factorials used right now in contructor
    }

    /**
     * Return the center of the taylor polynomial.
     */
    public double getCenter()
    {
        return this.center;
    }

    /**
     * Return the degree of the taylor polynomial.
     */
    public int getDegree()
    {
        return this.degree;
    }

    /**
     * Return the maximum degree of any taylor polynomial.
     */
    public int getMax()
    {
        return maxDeg;
    }
    
    /**
     * Return the factorial for a given number; e.g 3! will return 6.
     */
    public double getFactorial(int index)
    {
        if(index >= maxDeg)
        {
            return -1;
        }
        else
        {
            return factorials[index];
        }
    }
    
    /**
     * Return the Nth Derivative of the function at a point from an array list of Nth derivatives which have allready been generated for the function at that point.
     */
    public double getNthDerivative(int n)
    {
        if(n < 0 || n >= nthDerivatives.size())
        {
            return 0;
        }
        else
        {
            return nthDerivatives.get(n);
        }
    }

    /**
     * Generate all the facotrials which will be used in the coefficients which will built the terms of the taylor polynomial
     */
    public void buildFactorials()
    {
        for(int i = 0; i< maxDeg; i++)
        {
            this.factorials[i] = factorial(i);
        }
    }
    
    /**
     * "Reset" the taylor polyomial back to initial conditions; center = 0, order = 0, etc;
     */
    public void completeReset()
    {
        this.center = 0;
        this.degree = 0;
        reset();
    }
    
    /**
     * Clear the array list of Nth derivatives at a particular point.
     */
    public void reset()
    {
        this.nthDerivatives.clear();
    }
    
    /**
     * Move the center of the taylor polynomial.
     * @param changeX the amount by which the center is moved; can be both positive and negative.
     */
    public void moveCenter(double changeX)
    {
        reset();
        this.center += changeX;
    }
    
    /**
     * Increases the degree of the taylor polynomial.
     */
    public void increaseDegree()
    {
        if(this.degree < maxDeg-1)
        {
            reset();
            this.degree++;
        }
    }
    
    /**
     * Decreases the degree of the taylor polynomial.
     */
    public void decreaseDegree() // lowers degree
    {
        if(this.degree > 0)
        {
            reset();
            this.degree--;
        }
    }
    
    /**
     * Adds an Nth derivative of the function at a point to the array list of other Nth derivatives.
     * @param evaluatedDerivative the Nth derivative evaluated at a particular point.
     */
    public void addNth(double evaluatedDerivative)
    {
        this.nthDerivatives.add(evaluatedDerivative);
    }

    /**
     * Print all the factorials.
     * Helper method during debugging stage
     */
    public void printFactorials()
    {
        for(int i = 0; i< maxDeg; i++)
        {
            System.out.println(i+": "+factorials[i]);
        }
        System.out.print("\n");
    }
    
    /**
     * Returns the factorial of a given number.
     * @param n the number which's factorial will be evaluated.
     */
    static long factorial(long n)
    {
        if (n == 0)
        {
            return 1;
        }
        return n * factorial(n-1);
    }
}
