public abstract class Polynom
{
    protected double[] A;
    /*public Polynom(int n)
    {
        A = new double[n];
    }*/
    public double getValue(double x)
    {
        double ptr = x;
        double otv = A[0];
        for(int i = 1; i < A.length; ++i)
        {
            otv += A[i] * ptr;
            ptr = ptr * x;
        }
        return otv;
    }
    public double[] getValue(double []X)
    {
        double Interpol[];
        Interpol = new double[X.length];
        for(int i = 0; i < Interpol.length; ++i)
        {
            Interpol[i] = getValue(X[i]);
        }
        return Interpol;
    }
}
