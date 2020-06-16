 

/**
 * Charles Kolozsvary
 * Period 2 Algorithms
 * Java Version 4.2.1
 * Java SDK: 12.01.0
 * 6-13-20
 * Description:
 * This class will create objects of particular polynomials which are modeling a particular function.
 * Each term within a polynomial is composed of an nth derivative, the (x-c) to the nth power all divided by n factorial.
 * For the purpose of efficiency, I will be storing all values of N factorial (for the highest number of terms) so that they do not need to be generated
 * over and over again. The Nth degree derivative per term will constantly be changing depending on where the polynomial is centered.
 * Thus I think it is most beneficial to make the Nth derivative an arraylist, where depending on the degree of the power series,
 * I will add subsequent nth derivatives.
 */
import java.util.ArrayList;
public class Polynomial
{
    //initialize instance fields
    protected double center; //the center of the taylor polynomial
    protected int degree; //the number of terms the current taylor polynomial has
    private ArrayList<Double> nthDerivatives;
    private double[] factorials;
    private final int max;

    public Polynomial()
    {
        //initialize instance fields
        this.center = 0;
        this.degree = 0;
        this.nthDerivatives = new ArrayList<>();
        this.max = 21; //after 20 factorial things start getting crazy, so I capped it at 20 degrees
        this.factorials = new double[max];
        buildFactorials(); //build all of the factorials used
    }

    /**
     * getter methods
     */
    public double getCenter()
    {
        return this.center;
    }

    public int getDegree()
    {
        return this.degree;
    }

    public int getMax()
    {
        return max;
    }

    public double getFactorial(int index)
    {
        if(index >= max)
        {
            return -1;
        }
        else
        {
            return factorials[index];
        }
    }

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
     * Void methods
     */

    public void buildFactorials()
    {
        for(int i = 0; i< max; i++)
        {
            this.factorials[i] = factorial(i);
        }
    }
    public void completeReset()
    {
        this.center = 0;
        this.degree = 0;
        reset();
    }
    public void reset()
    {
        this.nthDerivatives.clear();
    }

    public void moveCenter(double changeX)
    {
        reset();
        this.center += changeX;
    }

    public void increaseDegree()
    {
        if(this.degree < max-1)
        {
            reset();
            this.degree++;
        }
    }

    public void decreaseDegree()
    {
        if(this.degree > 0)
        {
            reset();
            this.degree--;
        }
    }

    public void addNth(double evaluatedDerivative)
    {
        this.nthDerivatives.add(evaluatedDerivative);
    }


    /**
     * Helper methods
     */
    public void printFactorials()
    {
        for(int i = 0; i< max; i++)
        {
            System.out.println(i+": "+factorials[i]);
        }
        System.out.print("\n");
    }

    static long factorial(long n)
    {
        if (n == 0)
        {
            return 1;
        }
        return n * factorial(n-1);
    }
}
