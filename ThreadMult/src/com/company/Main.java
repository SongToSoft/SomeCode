package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class Main
{
    //Вывод матрицы
    public static void print(int[][] A)
    {
        for (int i = 0; i < A.length; ++i)
        {
            for(int j = 0; j < A[0].length; ++j)
            {
                System.out.print(A[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args)
    {
        int[][] firstMatrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] secondMatrix = {{2, 1, 2, 1, 2, 1, 2}, {1, 2, 1, 2, 1, 2, 1}, {2, 1, 2, 1, 2, 1, 2}, {1, 2, 1, 2, 1, 2, 1}};
        int n = 1000;
        int[][] firstMatrix = new int[n][n];
        int[][] secondMatrix = new int[n][n];
        for (int i = 0; i < n; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                firstMatrix[i][j] = (int) (Math.random() * n);
                secondMatrix[i][j] = (int) (Math.random() * n);
            }
        }
        MultiThread MT = new MultiThread();

        for (int i = 1; i <= 2048; i *= 2)
        {
            long startTime = System.currentTimeMillis();
            int[][] resultMatrix = MT.multiplyMatrixMT(firstMatrix, secondMatrix, i);
            long timeSpent = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("программа выполнялась в " + i + " поток " + timeSpent + " секунд");
            //print(resultMatrix);
        }
    }
}