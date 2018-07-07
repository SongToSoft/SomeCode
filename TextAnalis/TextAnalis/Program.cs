using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TextAnalis
{
    class Program
    {
        static public char[] Alfav = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',' ', '.', ',', '/', '"', '-', '?', '!', '[', ']'};
        //static public char[] Original;
        //static public char[] Twink;
        static public int[] Num = new int[46];
        static public double[] Shift = new double[46];
        static char[] CharFile;
        static public void CheckReader(char C)
        {
            for (int i = 0; i < Alfav.Length; ++i)
            {
                if (Alfav[i] == C)
                {
                    ++Num[i];
                }
            }
        }
        static public void Swap(char[] file, char A, char B)
        {
            for (int i = 0; i < CharFile.Length; ++i)
            {
                if (CharFile[i] == A)
                {
                    file[i] = B;
                }
            }
        }
        static public void PrintFile(char[] CharFile)
        {
            string path = @"C: \Users\Dima\source\repos\TextAnalis\TextAnalis\Result.txt";
            string newString = new string(CharFile);
            File.WriteAllText(path, newString, Encoding.UTF8);
        }
        static void Reader(string filename)
        {
            string[] readText = File.ReadAllLines(filename);
            string file = readText[0];
            CharFile = new char[file.Length];
            for (int i = 0; i < file.Length; ++i)
            {
                CharFile[i] = file[i];
            }
            //System.Console.WriteLine(file);
            //System.Console.WriteLine(Alfav.Length);
            //System.Console.WriteLine(file.Length);
            for (int i = 0; i < Num.Length; ++i)
            {
                Num[i] = 0;
            }
            for (int i = 0; i < file.Length; ++i)
            {
                CheckReader(file[i]);
            }
            Array.Sort(Alfav, Num);
            Array.Sort(Num, Alfav);
            //for (int i = 0; i < Shift.Length; ++i)
            //{
            //    Shift[i] = (Num[i] / file.Length) * 100;
            //}
            for (int i = 0; i < Num.Length; ++i)
            {
                System.Console.Write(Alfav[i] + " ");
                System.Console.Write(Num[i]);
                System.Console.WriteLine();
            }
            char[] TMP_CharFile = new char[CharFile.Length];
            for (int i = 0; i < CharFile.Length; ++i)
            {
                TMP_CharFile[i] = CharFile[i];
            }
            //char[] TMP_CharFile = CharFile; 

            //Swap(TMP_CharFile, 'p', 's');
            //(TMP_CharFile, 'l', 'h');
            Swap(TMP_CharFile, 'l', 's');
            //Swap(TMP_CharFile, 'g', 'r');
            Swap(TMP_CharFile, 'w', 'd');
            //Swap(TMP_CharFile, 'i', 'l');
            //Swap(TMP_CharFile, ' ', 'c');
            Swap(TMP_CharFile, 'j', 'u');
            //Swap(TMP_CharFile, 'e', 'w');
            Swap(TMP_CharFile, 'e', '!');
            //Swap(TMP_CharFile, '6', 'f');
            //Swap(TMP_CharFile, '6', 'x');
            Swap(TMP_CharFile, '6', 'c');
            Swap(TMP_CharFile, 't', 'g');
            //Swap(TMP_CharFile, ']', 'y');
            //Swap(TMP_CharFile, ']', 'c');
            //Swap(TMP_CharFile, ']', 'x');
            Swap(TMP_CharFile, 'c', 'b');
            //Swap(TMP_CharFile, 'x', 'b');
            //Swap(TMP_CharFile, 'x', 'p');
            //Swap(TMP_CharFile, 'x', 'v');
            //Swap(TMP_CharFile, '3', 'k');
            //Swap(TMP_CharFile, '3', 'x');
            //Swap(TMP_CharFile, 'z', 'k');
            //Swap(TMP_CharFile, 'g', 'j');
            //Swap(TMP_CharFile, '1', 'q');
            Swap(TMP_CharFile, '1', 'v');
            Swap(TMP_CharFile, '"', ' ');

            Swap(TMP_CharFile, '7', '?');
            Swap(TMP_CharFile, 'f', '-');
            Swap(TMP_CharFile, '?', 'q');
            Swap(TMP_CharFile, ']', '.');
            Swap(TMP_CharFile, 'z', 'k');
            Swap(TMP_CharFile, 'g', '"');
            Swap(TMP_CharFile, 'o', 'm');
            Swap(TMP_CharFile, 'p', 'h');
            Swap(TMP_CharFile, 's', ' ');
            Swap(TMP_CharFile, '8', 'e');
            Swap(TMP_CharFile, '0', 't');
            Swap(TMP_CharFile, '2', 'a');
            Swap(TMP_CharFile, 'm', 'o');
            Swap(TMP_CharFile, ',', 'i');
            Swap(TMP_CharFile, 'n', 'n');
            Swap(TMP_CharFile, 'i', 'w');
            Swap(TMP_CharFile, ' ', 'y');
            Swap(TMP_CharFile, 'x', 'f');
            Swap(TMP_CharFile, '5', 'p');
            Swap(TMP_CharFile, '3', ',');
            Swap(TMP_CharFile, '9', 'r');
            Swap(TMP_CharFile, '!', 'l');
            Swap(TMP_CharFile, '.', '.');

            PrintFile(TMP_CharFile);
        }
        static void Main(string[] args)
        {
            Reader(@"C: \Users\Dima\source\repos\TextAnalis\TextAnalis\Text\TheHacker0.txt");
            System.Console.WriteLine("Press key");
            Console.ReadKey();
        }
    }
}
