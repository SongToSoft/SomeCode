using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;


using System.Data;
using System.Data.SqlClient;
namespace LibraryApplication
{
    public partial class Form1 : Form
    {
        SqlConnection sqlConnection;
        public Form1()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private async void Form1_Load(object sender, EventArgs e)
        {
            //Путь к базе данных
            //string connectionString = @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=C:\Users\User\Desktop\LibraryApplication-master\LibraryApplication\LibraryApplication\bin\Debug\LibraryDatabase.mdf;Integrated Security=True;Connect Timeout=30";
            string connectionString = @"Data Source = (LocalDB)\MSSQLLocalDB; AttachDbFilename = C:\Users\dima\Documents\3 курс\Разработка программного обеспечения\Практика\LibraryApplication\LibraryApplication\LibraryDatabase.mdf; Integrated Security = True";
            //string connectionString = @"Data Source = (LocalDB)\MSSQLLocalDB; AttachDbFilename = C:\Пользователи\Zakhar\Рабочий стол\Проект\LibraryApplication-master\LibraryApplication\LibraryApplication\LibraryDatabase.mdf; Integrated Security = True";
            sqlConnection = new SqlConnection(connectionString);
            await sqlConnection.OpenAsync();
            SqlDataAdapter da = new SqlDataAdapter("SELECT * from [Books]", sqlConnection);
            SqlCommandBuilder cb = new SqlCommandBuilder(da);

            DataSet ds = new DataSet();
            da.Fill(ds, "[Books]");

            dataGridView2.DataSource = ds.Tables[0];

            SqlDataAdapter da1 = new SqlDataAdapter("SELECT * from [Readers]", sqlConnection);
            SqlCommandBuilder cb1 = new SqlCommandBuilder(da1);

            DataSet ds1 = new DataSet();
            da1.Fill(ds1, "[Readers]");

            dataGridView1.DataSource = ds1.Tables[0];
            if (Form2.MOD == 1)
            {
                //tabControl2.TabPages.Remove(tabPage3);
                tabControl2.TabPages.Remove(tabPage4);
                tabControl2.TabPages.Remove(tabPage5);
                tabControl2.TabPages.Remove(tabPage6);
                tabControl3.TabPages.Remove(tabPage10);
                tabControl3.TabPages.Remove(tabPage11);
            }
        }
        //Кнопка добавление книг
        private async void button1_Click(object sender, EventArgs e)
        {
            if (label4.Visible)
            {
                label4.Visible = false;
            }
            if ((!string.IsNullOrEmpty(textBox1.Text) && !string.IsNullOrWhiteSpace(textBox1.Text)) &&
                (!string.IsNullOrEmpty(textBox2.Text) && !string.IsNullOrWhiteSpace(textBox2.Text)) &&
                (!string.IsNullOrEmpty(textBox3.Text) && !string.IsNullOrWhiteSpace(textBox3.Text)) &&
                (!string.IsNullOrEmpty(textBox4.Text) && !string.IsNullOrWhiteSpace(textBox4.Text)))
            {
                SqlCommand command = new SqlCommand("INSERT INTO [Books] (Title, Author, Year, Status)VALUES(@Title, @Author, @Year, @Status)", sqlConnection);
                //Приложение не поймёт что за Name и Price
                command.Parameters.AddWithValue("Title", textBox1.Text);
                command.Parameters.AddWithValue("Author", textBox3.Text);
                command.Parameters.AddWithValue("Year", textBox2.Text);
                command.Parameters.AddWithValue("Status", textBox4.Text);

                await command.ExecuteNonQueryAsync();
            }
            else
            {
                label4.Visible = true;
                label4.Text = "Все поля должны быть заполнены";
            }
        }
        //Кнопка обновления списков
        private async void обновитьToolStripMenuItem_Click(object sender, EventArgs e)
        {
            dataGridView1.ClearSelection();
            dataGridView2.ClearSelection();

            SqlDataAdapter da = new SqlDataAdapter("SELECT * from [Books]", sqlConnection);
            SqlCommandBuilder cb = new SqlCommandBuilder(da);

            DataSet ds = new DataSet();
            da.Fill(ds, "[Books]");

            dataGridView2.DataSource = ds.Tables[0];

            SqlDataAdapter da1 = new SqlDataAdapter("SELECT * from [Readers]", sqlConnection);
            SqlCommandBuilder cb1 = new SqlCommandBuilder(da1);

            DataSet ds1 = new DataSet();
            da1.Fill(ds1, "[Books]");

            dataGridView1.DataSource = ds1.Tables[0];
        }
        //Кнопка изменить данные о книге 
        private async void button2_Click(object sender, EventArgs e)
        {
            if (label7.Visible)
            {
                label7.Visible = false;
            }
            if ((!String.IsNullOrEmpty(textBox5.Text) && !String.IsNullOrWhiteSpace(textBox5.Text)) &&
                (!String.IsNullOrEmpty(textBox6.Text) && !String.IsNullOrWhiteSpace(textBox6.Text)))
            {
                SqlCommand command = new SqlCommand("UPDATE [Books] SET [Status]=@Status WHERE [Id]=@Id", sqlConnection);
                command.Parameters.AddWithValue("Id", textBox5.Text);
                command.Parameters.AddWithValue("Status", textBox6.Text);

                await command.ExecuteNonQueryAsync();
            }
            else
            {
                label7.Visible = true;
                label7.Text = "Все поля должны быть заполнены";
            }
        }
        //Кнопка удаления книги
        private async void button3_Click(object sender, EventArgs e)
        {
            if (label10.Visible)
            {
                label10.Visible = false;
            }
            if (!String.IsNullOrEmpty(textBox7.Text) && !String.IsNullOrWhiteSpace(textBox7.Text))
            {
                SqlCommand command = new SqlCommand("DELETE FROM [Books] WHERE [ID]=@Id", sqlConnection);

                command.Parameters.AddWithValue("Id", textBox7.Text);
                await command.ExecuteNonQueryAsync();
            }
            else
            {
                label10.Visible = true;
                label10.Text = "'Id' должен быть заполнен";
            }
        }

        private void listBox2_SelectedIndexChanged(object sender, EventArgs e)
        {

        }
        //Кнопка добавление читателей
        private async void button4_Click(object sender, EventArgs e)
        {
            if (label12.Visible)
            {
                label12.Visible = false;
            }
            if ((!string.IsNullOrEmpty(textBox11.Text) && !string.IsNullOrWhiteSpace(textBox11.Text)) &&
                (!string.IsNullOrEmpty(textBox9.Text) && !string.IsNullOrWhiteSpace(textBox9.Text)) &&
                (!string.IsNullOrEmpty(textBox10.Text) && !string.IsNullOrWhiteSpace(textBox10.Text)))
            {
                SqlCommand command = new SqlCommand("INSERT INTO [Readers] (Name, Title, Date)VALUES(@Name, @Title, @Date)", sqlConnection);
                //Приложение не поймёт что за Name и Price
                command.Parameters.AddWithValue("Name", textBox11.Text);
                command.Parameters.AddWithValue("Title", textBox9.Text);
                command.Parameters.AddWithValue("Date", textBox10.Text);

                await command.ExecuteNonQueryAsync();
            }
            else
            {
                label12.Visible = true;
                label12.Text = "Все поля должны быть заполнены";
            }
        }
        //Кнопка Изменить данные о читателе
        private async void button5_Click(object sender, EventArgs e)
        {
            if (label17.Visible)
            {
                label17.Visible = false;
            }
            if ((!String.IsNullOrEmpty(textBox13.Text) && !String.IsNullOrWhiteSpace(textBox13.Text)) &&
                (!String.IsNullOrEmpty(textBox12.Text) && !String.IsNullOrWhiteSpace(textBox12.Text)))
            {
                SqlCommand command = new SqlCommand("UPDATE [Readers] SET [Title]=@Title WHERE [Id]=@Id", sqlConnection);
                command.Parameters.AddWithValue("Id", textBox13.Text);
                command.Parameters.AddWithValue("Title", textBox12.Text);

                await command.ExecuteNonQueryAsync();
            }
            else
            {
                label17.Visible = true;
                label17.Text = "Все поля должны быть заполнены";
            }
        }
        //Кнопка удаления читателя
        private async void button6_Click(object sender, EventArgs e)
        {
            if (label19.Visible)
            {
                label19.Visible = false;
            }
            if (!String.IsNullOrEmpty(textBox14.Text) && !String.IsNullOrWhiteSpace(textBox14.Text))
            {
                SqlCommand command = new SqlCommand("DELETE FROM [Readers] WHERE [ID]=@Id", sqlConnection);

                command.Parameters.AddWithValue("Id", textBox14.Text);
                await command.ExecuteNonQueryAsync();
            }
            else
            {
                label19.Visible = true;
                label19.Text = "'Id' должен быть заполнен";
            }
        }
       

        //Поведение при закрытии формы
        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (sqlConnection != null && sqlConnection.State != ConnectionState.Closed)
            {
                sqlConnection.Close();
            }
        }

        private void dataGridView2_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
    }
}
