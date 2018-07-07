
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.MouseInfo;
import java.awt.Point;

public class Widow extends JFrame
{
    private int step = 10;
    private double x = 0;
    private double y = 0;
    int flag = 0;
    public Widow()
    {
        super("Widow");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 542);
        setLocation(600,0);
    }
    public void Set_graph(Polynom M,int N)
    {
        Paint Painter = new Paint(M, N);
        getContentPane().add(Painter);

    }
    public class Paint extends JPanel
    {
        public double Xap[];
        public double Yap[];
        public int N;
        public Paint(Polynom M,int n)
        {
            N = n;
            Xap = new double[N];
            Yap = new double[N];
            for (int i = 0; i < N; ++i)
            {
                Xap[i] = i - N/2;
            }
            Yap = M.getValue(Xap);
        }
        protected void paintComponent(Graphics g)
        {
            super.paintComponents(g);
            //Динамическое изменение осей и сетки
            //Создаём сетку
            g.setColor(Color.gray);
            for(int i = 0; i <= this.getSize().height; i+=step)
            {
                g.drawLine(0, i, this.getSize().height , i);
            }

            for(int i = 0; i <= this.getSize().width; i+=step)
            {
                g.drawLine(i, 0, i, this.getSize().width);
            }
            //Создаём оси
            g.setColor(Color.black);
            g.drawLine(0, this.getSize().height/2, this.getSize().width, this.getSize().height/2);
            g.drawLine(this.getSize().width/2, 0, this.getSize().width/2, this.getSize().height);
            g.setColor(Color.black);


            g.drawString("X", this.getSize().width - 10, this.getSize().height/2 - 3);
            g.drawString("Y", this.getSize().width/2 + 3, 0 + 15);

            ///Приводим кардинаты к целочисленному типу

            int Xone[] = new int[N];
            int Yone[] = new int[N];
            for (int i = 0; i < N; ++i)
            {
                Xone[i] = this.getSize().width/2 + (int)(Xap[i] * step);
                Yone[i] = this.getSize().height/2 + (int)(Yap[i] * step);
            }
            g.drawPolyline(Xone, Yone, N);
            Point location = MouseInfo.getPointerInfo().getLocation();
            if(flag == 0)
            {
                x = location.getX();
                y = location.getY();
                g.drawString("X = " + x, 10, this.getSize().height - 10);
                g.drawString("Y = " + y, 70, this.getSize().height - 10);
            }
            if ((x) != location.getX() && (y) != location.getY())
            {
                x = location.getX();
                y = location.getY();
                g.drawString("X =         ", 10, this.getSize().height - 10);
                g.drawString("Y =         ", 70, this.getSize().height - 10);
            }
            else
            {
                x = location.getX();
                y = location.getY();
                g.drawString("X = " + x, 10, this.getSize().height - 10);
                g.drawString("Y = " + y, 70, this.getSize().height - 10);
            }
            flag = 1;
            g.setColor(Color.red);
            repaint();

        }
    }
}