public class Solver {
    private double[][] L, U;
    private double[] X, Y;
    private int m;
    private double factor;
    public double[][] RetL()
    {
        return L;
    }
    public double[][] RetU()
    {
        return U;
    }
    public double[][] Mult(double[][] A, double[][] B)
    {
        int m = A.length;
        int n = B[0].length;
        int o = B.length;
        double[][] res = new double[m][n];
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {

                for (int k = 0; k < o; k++)
                {
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return res;
    }
    public void print()
    {
        for (int i = 0; i < L.length; ++i)
        {
            for(int j = 0; j < L.length; ++j)
            {
                System.out.print(L[i][j]);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
        System.out.print("Получили матрицу L\n");

        for (int i = 0; i < U.length; ++i)
        {
            for(int j = 0; j < U.length; ++j)
            {
                System.out.print(U[i][j]);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
        System.out.print("Получили матрицу U\n");
    }
    public Solver(double[][] A){
        double buff;
        double[][] T;
        int n = A.length;
        T = new double[n][n];
        L = new double[n][n];
        U = new double[n][n];
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j){
                if(j == i){
                    T[i][j] = 1;
                    L[i][j] = 1;
                    continue;
                }
                T[i][j] = 0;
                L[i][j] = 0;
            }
        }
        for(int i = 0; i < n; ++i){
            if(A[i][i] == 0){
                for(int d = i; d < n; ++d){
                    if(A[d][i] != 0){
                        for(int c = i; c < n; ++c){
                            buff = A[d][c];
                            A[d][c] = A[i][c];
                            A[i][c] = buff;
                        }
                    }
                }
            }
            for(int j = i + 1; j < n; ++j){
                factor = -(A[j][i]/A[i][i]);
                for(int k = i; k < n; ++k){
                    A[j][k] = (factor * A[i][k]) + A[j][k];
                    T[j][k] = (factor * T[i][k]) + T[j][k];
                }
            }
        }

        for(int i = 0; i < n; ++i){
            if(T[i][i] == 0){
                for(int d = i; d < n; ++d){
                    if(T[d][i] != 0){
                        for(int c = i; c < n; ++c){
                            buff = T[d][c];
                            T[d][c] = T[i][c];
                            T[i][c] = buff;
                        }
                    }
                }
            }
            for(int j = i + 1; j < n; ++j){
                factor = -(T[j][i]/T[i][i]);
                for(int k = i; k < n; ++k){
                    T[j][k] = (factor * T[i][k]) + T[j][k];
                    L[j][k] = (factor * L[i][k]) + L[j][k];
                }
            }
            buff = T[i][i];
            for(int z = 0; z < n; ++z)
                T[i][z] = T[i][z] / buff;
            for(int z = 0; z < n; ++z)
                L[i][z] = L[i][z] / buff;
        }
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j){
                U[i][j] = A[i][j];
            }
        }
        m = n;
    }
    public double[] getSolve(double[] F){
        Y = new double[m];
        X = new double[m];
        double summ;
        for(int i = 0; i < m; ++i){
            summ = 0;
            for(int j = i; j > 0; --j){
                summ = summ + Y[j - 1]*L[i][j - 1];
            }
            Y[i] = (F[i] - summ)/L[i][i];
        }
        for(int i = m; i > 0; --i){
            summ = 0;
            for(int j = i; j < m; ++j){
                summ = summ + X[j]*U[i - 1][j];
            }
            X[i - 1] = (Y[i - 1] - summ)/(U[i - 1][i - 1]);
        }
        return X;
    }
}