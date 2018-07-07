package com.company;


public class MultiThread extends Thread
{
    private int[][] firstMatrix;
    private int[][] secondMatrix;
    private int[][] resultMatrix;
    private int firstIndex;
    private int lastIndex;
    private int sumLength;
    //Конструктор для создания класса
    public MultiThread() {}
    //Конструктор используемый в вычислениях значений результирующей матрицы
    public MultiThread(int[][] firstMatrix, int[][] secondMatrix, int[][] resultMatrix, int firstIndex, int lastIndex)
    {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.resultMatrix = resultMatrix;
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
        sumLength = secondMatrix.length;
    }

    //Вычисление значения в одной ячейки
    private void calcValue(final int row, final int col)
    {
        int sum = 0;
        for (int i = 0; i < sumLength; ++i)
        {
            sum += firstMatrix[row][i] * secondMatrix[i][col];
        }
        resultMatrix[row][col] = sum;
    }

    @Override
    public void run()
    {
        //System.out.println("Thread " + getName() + " started. Calculating cells from " + firstIndex + " to " + lastIndex + "...");
        int colCount = secondMatrix[0].length;  // Число столбцов результирующей матрицы.
        for (int index = firstIndex; index < lastIndex; ++index)
        {
            calcValue(index / colCount, index % colCount);
        }
        //System.out.println("Thread " + getName() + " finished.");
    }
    public static int[][] multiplyMatrixMT(int[][] firstMatrix, int[][] secondMatrix, int threadCount)
    {
        assert threadCount > 0;

        int rowCount = firstMatrix.length;
        int colCount = secondMatrix[0].length;
        int[][] result = new int[rowCount][colCount];

        int cellsForThread = (rowCount * colCount) / threadCount;  // Число вычисляемых ячеек на поток.
        int firstIndex = 0;  // Индекс первой вычисляемой ячейки.
        MultiThread[] MT = new MultiThread[threadCount];  // Массив потоков.

        // Создание и запуск потоков.
        for (int threadIndex = threadCount - 1; threadIndex >= 0; --threadIndex)
        {
            int lastIndex = firstIndex + cellsForThread;  // Индекс последней вычисляемой ячейки.
            if (threadIndex == 0)
            {
                lastIndex = rowCount * colCount;
            }
            MT[threadIndex] = new MultiThread(firstMatrix, secondMatrix, result, firstIndex, lastIndex);
            MT[threadIndex].start();
            firstIndex = lastIndex;
        }
        // Ожидание завершения потоков.
        try
        {
            for (MultiThread multiplierThread : MT)
            {
                multiplierThread.join();
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
