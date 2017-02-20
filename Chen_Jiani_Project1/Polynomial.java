import java.util.Iterator;

/**
 * This Polynomial class stores the terms of polynomial into linked list , and 
 * @author jenny
 *
 */
public class Polynomial {
    DoublyLinkedList<Term> TermDoublyLinkedList;

    public enum MathOpeartion
    {
        Add,
        Subtract
    }

    public Polynomial()
    {
        TermDoublyLinkedList = new DoublyLinkedList<Term>();
    }


/**
 * stores the terms of a polynomial expression
 * @param newTerm
 */
    public void addTerm(Term newTerm) {

        // insert each term based on Exponent order.
           int i=0;
            for(i=0;i<TermDoublyLinkedList.size();i++)
            {
                Term term = TermDoublyLinkedList.get(i);

                // make sure each term is inserted in an  ascending order.
                if(newTerm.getExponent() > term.getExponent())
                {
                    TermDoublyLinkedList.add(i, newTerm);
                    return;
                }

            }

        TermDoublyLinkedList.add(TermDoublyLinkedList.size(), newTerm);

    }
/**
 * perform addition of two polynomial expressions, and return the result
 * @param other
 * @return
 */
    public Polynomial add(Polynomial other)
    {
        return  perfromOperation(other, MathOpeartion.Add);
    }
/**
 * perform subtraction of two polynomial expressions, and return the result
 * @param other
 * @return
 */
    public Polynomial subtract(Polynomial other)
    {
        return perfromOperation(other, MathOpeartion.Subtract);
    }
/**
 * perform multiplication of two polynomial expressions, and return the result
 * @param other
 * @return
 */
    public Polynomial mulitply(Polynomial other)
    {
        Polynomial sum = new Polynomial();
        Iterator<Term> otherIterator = other.TermDoublyLinkedList.iterator();

        while(otherIterator.hasNext()) {
            Iterator<Term> iterator = this.TermDoublyLinkedList.iterator();
            Polynomial tempPolynomial = new Polynomial();
            Term otherTerm = otherIterator.next();

            while (iterator.hasNext()) {
                Term currentTerm = iterator.next();
                tempPolynomial.addTerm(new Term(currentTerm.getCoefficient() * otherTerm.getCoefficient(), currentTerm.getExponent() + otherTerm.getExponent()));
            }

          //  System.out.println(tempPolynomial);
            sum = sum.add(tempPolynomial);
    }

        return sum;
    }
/**
 * according to the flag, then determine to do addition or subtraction
 * @param other
 * @param op
 * @return 
 */
    private Polynomial perfromOperation(Polynomial other, MathOpeartion op)
    {
        Polynomial result = new Polynomial();
        int highestTerm = 0;

        if(this.TermDoublyLinkedList.size() > 0 &&
           other.TermDoublyLinkedList.size() > 0) {
            Term otherHighestTerm = other.TermDoublyLinkedList.get(0);
            Term myHighestTerm = this.TermDoublyLinkedList.get(0);
            highestTerm = (myHighestTerm.getExponent() > otherHighestTerm.getExponent()) ? myHighestTerm.getExponent() : otherHighestTerm.getExponent();
        }
        else if(this.TermDoublyLinkedList.size() > 0)
        {
            Term myHighestTerm = this.TermDoublyLinkedList.get(0);
            highestTerm = myHighestTerm.getExponent();
        }
        else if(other.TermDoublyLinkedList.size() > 0)
        {
            Term otherHighestTerm = other.TermDoublyLinkedList.get(0);
            highestTerm = otherHighestTerm.getExponent();
        }

            for(int i=0;i<=highestTerm;i++)
            {
                Term otherTerm = other.getTermByExponent(i);
                Term myTerm =this.getTermByExponent(i);

                if(myTerm!=null && otherTerm != null)
                {
                    double coefficient = 0;

                    if( op == MathOpeartion.Add)
                    coefficient =  myTerm.getCoefficient() + otherTerm.getCoefficient();
                    else
                    coefficient =  myTerm.getCoefficient() - otherTerm.getCoefficient();

                    result.addTerm(new Term(coefficient  , i) );
                }
                else if(myTerm != null & otherTerm == null) {
                    result.addTerm(new Term(myTerm.getCoefficient()  , i) );
                }
                else if(myTerm == null & otherTerm != null) {
                    if( op == MathOpeartion.Add)
                    result.addTerm(new Term(otherTerm.getCoefficient()  , i) );
                    else
                    result.addTerm(new Term(-1 * otherTerm.getCoefficient(), i));
                }

            }

        return result;
    }

/**
 * add the term in a order from big to small
 * @param otherTerm
 */
    // assume our doublely lincked list does not have this term with the same exponent.
    private void InsertBeforeLowerTerm(Term otherTerm)
    {
        Iterator<Term> iterator = this.TermDoublyLinkedList.iterator();
        boolean found = false;
        int count = 0;

        while(iterator.hasNext()) {

            Term currentTerm = iterator.next();
            if(currentTerm.getExponent() < otherTerm.getExponent())
            {
                this.TermDoublyLinkedList.add(count, otherTerm);
                found = true;
                break;
            }

            count++;
        }

    }
/**
 * get the same degree of exponents to add 
 * @param exponent
 * @return
 */
    private Term getTermByExponent(int exponent)
    {
        Iterator<Term> iterator = this.TermDoublyLinkedList.iterator();

        while(iterator.hasNext()) {

            Term currentTerm = iterator.next();

            if(currentTerm.getExponent()==exponent)
                return currentTerm; // found matched Term

        }

        return null;
    }

    /**
     * 
     */
    public String toString() {
        Iterator<Term> iterator = this.TermDoublyLinkedList.iterator();

        String expression = "";

        while(iterator.hasNext())
        {
            Term currentTerm = iterator.next();

            if(currentTerm.getCoefficient()!=0) {

                if(expression.length()>0)
                {
                    if(currentTerm.getCoefficient()==0)
                    {
                        // do nothing
                    }
                    else if(currentTerm.getCoefficient() > 0)
                    {
                        expression += " + ";
                    }
                    else // if it is less than 0
                    {
                        expression += " ";
                    }

                    expression += currentTerm.toString();
                }
                else
                {
                    expression = currentTerm.toString();
                }
            }
        }

        return expression;
    }
}
