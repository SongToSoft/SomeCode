class Approx extends Polynom
{
    protected double Mat[][];
    //protected double[] A;
    protected double[] Y;
    protected int BASIS;
    public Approx(double[] X, double[] F, int basis)
    {
        //super(basis);
        //Нахождение матрицы
        //basis = basis + 1;
        //Итоговая матрица
        double matrix[][] = new double[basis][basis + 1];
        for (int i = 0; i < basis; i++)
        {
            for (int j = 0; j < basis; j++)
            {
                matrix[i][j] = 0;
            }
        }
        //Поиск коэффициентов для полинома
        for (int i = 0; i < basis; ++i)
        {
            for (int j = 0; j < basis; ++j)
            {
                double sumA = 0, sumB = 0;
                for (int k = 0; k < (X.length); ++k)
                {
                    sumA += Math.pow(X[k],i) * Math.pow(X[k], j);
                    sumB += F[k] * Math.pow(X[k], i);
                }
                matrix[i][j] = sumA;
                matrix[i][basis] = sumB;
            }
        }
        //Вывод матрицы
		/*System.out.print("\nMatrix Matrix\n");
		for(int i = 0; i < basis; ++i )
		{
			for(int j = 0; j < basis + 1; ++j)
			{
				System.out.print(matrix[i][j]);
				System.out.print(' ');
			}
			System.out.print('\n');
		}
		*/
        double Mat[][] = new double[basis][basis];
        double Y[] = new double[basis + 1];
        for(int i = 0; i < basis; ++i )
        {
            for(int j = 0; j < basis; ++j)
            {
                Mat[i][j] = matrix[i][j];
            }
            System.out.print('\n');
        }
        for(int i = 0; i < basis; ++i)
        {
            Y[i] = matrix[i][basis];
        }
        System.out.print("\nMatrix Mat\n");
        for(int i = 0; i < basis; ++i )
        {
            for(int j = 0; j < basis; ++j)
            {
                System.out.print(Mat[i][j]);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
        System.out.print("\nMatrix Y\n");
        A = new double[basis];
        for(int j = 0; j < basis; ++j)
        {
            System.out.print(Y[j]);
            System.out.print(' ');
        }
        Solver LU = new Solver(Mat);
        A = LU.getSolve(Y);
    }
    //вывод решения
    public void printAns()
    {
        System.out.print("\nMatrix Ans\n");
        for(int i = 0; i < A.length; ++i )
        {
            System.out.print(A[i]);
            System.out.print(" ");
        }
        System.out.print('\n');
    }
}