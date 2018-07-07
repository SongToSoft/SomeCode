
public class Interp extends Polynom
{
    protected double Mat[][];
    //protected double[] A;
    public Interp(double []X, double []F)
    {
        //super(X.length);
        Mat = new double[X.length][X.length];
        for(int i = 0; i < Mat.length; ++i)
        {
            Mat[i][0] = 1;
            Mat[i][1] = X[i];
        }
        for(int i = 0; i < Mat.length; ++i)
        {
            for (int j = 2 ;j < Mat.length; ++j)
            {
                Mat[i][j] = Mat[i][j-1] * Mat[i][1];
            }
        }
        Solver LU = new Solver(Mat);
        A = LU.getSolve(F);
    }

    public void print()
    {
        for(int i = 0; i < Mat.length; ++i)
        {
            for(int j = 0; j < Mat[0].length; ++j)
            {
                System.out.print(Mat[i][j]);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
    }

    public void printAns()
    {
        for(int i = 0; i < Mat.length; ++i )
        {
            System.out.print(A[i]);
            System.out.print(' ');
        }
        System.out.print('\n');

    }
}