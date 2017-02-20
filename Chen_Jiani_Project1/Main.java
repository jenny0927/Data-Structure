
import java.io.BufferedReader;
import java.io.FileReader;
/**
 * This Main class read the polynomials from text file, stores the number to polynomial expression, 
 * and do the operation of two polynomials
 * @author jenny
 *
 */
public class Main {

    public static void main(String[] args) {
        String filepath = "project1.txt";

        Main project = new Main();
        project.processPolynomialExpressionFile(filepath);



    }
/**
 * read from the text file 
 * @param filepath
 */
    public void processPolynomialExpressionFile(String filepath)
    {
        int lineNumber = 0;
        String line = null;

        try {

            BufferedReader br = new BufferedReader(new FileReader(filepath));
            Polynomial[] polynomials = new Polynomial[2];
            int count = 0;

            while (true) {
                lineNumber++;

                line = br.readLine();
                if (line == null) break;

                if (line.equalsIgnoreCase("add") ||
                        line.equalsIgnoreCase("subtract") ||
                        line.equalsIgnoreCase("multiply")
                        ) {
                    printResult(line, polynomials);
                    count = 0;
                } else {
                    if(count >= 2 )
                    {
                        throw new Exception("Unexpected command or line.");
                    }

                    polynomials[count++] = createPolynomalFromLine(line);
                }

            }

            br.close();
        } catch (Exception err) {
            System.out.println("Error opening/processing the file[" + filepath + "]: " + err.getMessage());
            System.out.println("Line number:" + lineNumber);
            System.out.println("Line Content:" + line);
        }
    }
    /**
     * transfer the string to operation expression
     * @param line
     * @param polynomials
     */
    public void printResult(String line, Polynomial[] polynomials)
    {
        System.out.println(polynomials[0]);

        if(line.equalsIgnoreCase("add"))
        {
            System.out.println("+");
        }
        else if(line.equalsIgnoreCase("subtract"))
        {
            System.out.println("-");
        }
        else if(line.equalsIgnoreCase("multiply"))
        {
            System.out.println("*");
        }

        System.out.println(polynomials[1]);

        System.out.println("=");


        if(line.equalsIgnoreCase("add"))
        {
            System.out.println(polynomials[0].add(polynomials[1]));
        }
        else if(line.equalsIgnoreCase("subtract"))
        {
            System.out.println(polynomials[0].subtract(polynomials[1]));
        }
        else if(line.equalsIgnoreCase("multiply"))
        {
            System.out.println(polynomials[0].mulitply(polynomials[1]));
        }

        System.out.println("---------------------------------------------------------");
    }

/**
 * read the number from text file, and transfer them into polynomial expression
 * @param line
 * @return
 */
    public Polynomial createPolynomalFromLine(String line)
    {
        String[] args = line.split(" ");

        Polynomial polynomial = new Polynomial();

        for(int i=0;i<args.length/2 ; i++)
        {
         //   System.out.println(args[i * 2] + "," + args[i * 2 + 1]);
            float coefficient = Float.parseFloat(args[i * 2]);
            int exponent = Integer.parseInt(args[i*2+1]);

            polynomial.addTerm(new Term(coefficient, exponent));
        }

        return polynomial;
    }
}
