
public class Main
{
    public static void main (String[] args)
    {
        //Проверка решения матрицы
		/*double[][] M = new double[n][n];
		double[] B = new double[n];
		for (int i = 0 ; i < n; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				M[i][j] = sc.nextDouble();
			}
			B[i] = sc.nextDouble();
		}
		for (int i = 0 ; i < n; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				System.out.print(M[i][j]);
				System.out.print(' ');
			}
			System.out.println('\n');
		}
		Solver LU = new Solver(M);
		LU.getSolv(B);
		LU.print();
        */
		//Проверка линейной интерполяции
        int n = 2;
		double[] X = new double[n];
		double[] F = new double[n];
        X[0] = 2;
        X[1] = 4;
        F[0] = 8;
        F[1] = 64;
		/*for(int i = 0; i < n; ++i)
		{
			X[i] = sc.nextDouble();
			F[i] = sc.nextDouble();
		}*/
		Interp Buffolo = new Interp(X,F);
        System.out.print('\n');
		Buffolo.printAns();
        System.out.print('\n');
		System.out.print(Buffolo.getValue(3));
		System.out.print('\n');


		//Approx Test = new Approx(X,F);
		/*System.out.print(Test.power(2,3));//проверка степени
		System.out.print('\n');*/

        //Проверка аппроксимации
        /*double X[] = new double[5];
        double mark = 2;
        for(int i=0; i < 5;++i)
        {
            X[i] = mark;
            mark += 2;
        }

        double F[] = new double[5];
        F[0] = 2.5;
        F[1] = 1.2;
        F[2] = 1.12;
        F[3] = 2.25;
        F[4] = 4.28;

        Approx Dog = new Approx(X, F, 2);
        Interp Cat = new Interp(X,F);
        //Widow Wal = new Widow();
        //Wal.Set_graph(Cat, 100);
        //Wal.setVisible(true);
           */
        //int n = 3;
        //double[] G = new double[n];
        //double[] H = new double[n];
        //G[0] = 2;
        //G[1] = 4;
        //H[0] = 8;
        //H[1] = 64;
        //Approx Apr = new Approx(G,H,2);
        //System.out.print("\n");
        //System.out.print(Apr.getValue(3));
		/*for(int i = 0; i < n; ++i)
		{
			X[i] = sc.nextDouble();
			F[i] = sc.nextDouble();
		}*/
		/*Interp Buffolo = new Interp(X,F);
        System.out.print('\n');
		Buffolo.printAns();
        System.out.print('\n');
		System.out.print(Buffolo.getValue(4));
		System.out.print('\n');*/

        //Window Wind = new Window();
        //Wind.setVisible(true);

        /*double A[][] = {{5}};
        double B[] ={4};
        Solver LU = new Solver(A);*/
        //Вывод решения методом LU
        /*double X[] = LU.getSolve(B);
        for(int i = 0; i < 3; ++i)
        {
            System.out.print(X[i]);
            System.out.print(' ');
        }
        System.out.print("\nОтве
        т\n");*/
        //LU.print();
        //Проверка правильности разложения LU
        /*double[][] U = LU.RetU();
        double[][] L = LU.RetL();
        double[][] Res = LU.Mult(L,U);
        System.out.print("Результат перемножения  L на U\n");
        for (int i = 0; i < Res.length; ++i)
        {
            for(int j = 0; j < Res.length; ++j)
            {
                System.out.print(Res[i][j]);
                System.out.print(' ');
            }
            System.out.print('\n');
        }*/
        Window W = new Window();
        W.setVisible(true);
    }
}