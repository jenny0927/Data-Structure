/**
 *  This Term class which stores the coefficient and exponent of a term, 
 *  and get and set value for the coefficient and the exponent.
 * @author jenny
 *
 */
public class Term {
    private double coefficient;
    private int exponent;
/**
 * set the constructor for Term class
 * @param coefficient
 * @param exponent
 */
    public Term(double coefficient, int exponent)
    {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

/**
 * get the value for coefficient
 * @return
 */
    public double getCoefficient() {
        return coefficient;
    }
/**
 * set the value for coefficient
 * @param coefficient
 */
    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
/**
 * get the value for exponent
 * @return
 */
    public int getExponent() {
        return exponent;
    }
/**
 * set the value for exponent
 * @param exponent
 */
    public void setExponent(int exponent) {
        this.exponent = exponent;
    }


/**
 * display the  term into correct format
 */
   
    public String toString() {
        int intCoefficient = (int)this.coefficient;
        String strCoefficient;

        if(intCoefficient == this.coefficient)
            strCoefficient = String.valueOf(intCoefficient); // get rid of the dot.
        else
            strCoefficient = String.valueOf(this.coefficient);

        if(this.exponent==0)
            return strCoefficient;

        if(this.coefficient==1) //  if it is 1, no need to display the coefficient as 1.
            strCoefficient = "";
        else if(this.coefficient==-1)
            strCoefficient = "-";

        if(this.exponent==1) // no need to display ^ when exponent = 1
            return strCoefficient + "x";

            return strCoefficient + "x^" + String.valueOf(this.exponent);
    }


}
