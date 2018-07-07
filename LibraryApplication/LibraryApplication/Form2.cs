using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace LibraryApplication
{
    public partial class Form2 : Form
    {
        //Состояние входа
        public static int MOD;
        public Form2()
        {
            InitializeComponent();
        }

        private void Form2_Load(object sender, EventArgs e)
        {
            this.Text = "Авторизация";
        }
        //Кнопка входа как администратор
        private void button1_Click(object sender, EventArgs e)
        {
            if (((textBox1.Text == "Olga") && (textBox2.Text == "123456")) ||
               ((textBox1.Text == "Zakhar") && (textBox2.Text == "123456")) ||
               ((textBox1.Text == "Dima") && (textBox2.Text == "123456")))
            {
                MOD = 0;
                Form1 curform = new Form1();
                curform.Show();
                this.Hide();
            }
        }
        //Поведение при закрытии формы
        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
           
        }
        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }
        //Кнопка входа как читатель
        private void button2_Click(object sender, EventArgs e)
        {
            MOD = 1;
            Form1 curform = new Form1();
            curform.Show();
            this.Hide();
        }
    }
}
