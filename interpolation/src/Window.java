import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Window extends JFrame
{
    private int step = 5;
    private int N = 0;
    private JLabel countLabel;
    private JButton Add_point;
    private JButton Del_point;
    private JButton Next;
    //private JButton ZOOM;
    //private JButton Degvion;
    private int Deg = 2;
    private int Flag = 0;
    private double now_zoom = 1;
    private double last_zoom = 1;
    JSpinner spinner = new JSpinner();
    public Window()
    {
        super("Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        countLabel = new JLabel("Количество точек: " + N);
        Add_point = new JButton("Добавить точку");
        Del_point = new JButton("Удалить точку");
        //ZOOM = new JButton("+");
        //Degvion = new JButton("Степень");
        Next = new JButton("Построить график");
        //addMouseListener(new CustomListener());
        /* Подготавливаем временные компоненты  */
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        /* Расставляем компоненты по местам  */
        add(countLabel, BorderLayout.NORTH);
        buttonsPanel.add(Add_point);
        buttonsPanel.add(Del_point);
        buttonsPanel.add(Next);
        //buttonsPanel.add(ZOOM);
        //Работа со спинером
        spinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
        //JCheckBox checkBox = new JCheckBox("Change");
        //JButton buttonDraw = new JButton("ReColor");//"Build");
        add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.add(spinner);
        initListeners();

        setSize(520, 600);
        setLocation(0,0);
        revalidate();
        repaint();
    }
    public void Set_graph()
    {
        Paint Painter = new Paint();
        getContentPane().add(Painter);
    }

    private void initListeners()
    {
        /*ZOOM.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                now_zoom+=2;
                last_zoom+=1;
                Paint Painter = new Paint();
                getContentPane().add(Painter);
            }
        });*/
        Add_point.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ++N;
                updateNumberCounter();
            }
        });
        Del_point.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (N > 0)
                {
                    --N;
                    updateNumberCounter();
                }
            }
        });
        Next.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Flag = 1;
                updateNumberCounter();
                --N;
                updateNumberCounter();
                ++N;
                updateNumberCounter();
            }
        });
    }
    private void updateNumberCounter()
    {
        if(Flag == 1)
        {
            Paint Painter = new Paint();
            getContentPane().add(Painter);
        }
        countLabel.setText("Количество точек: " + N);
    }
    public class Paint extends JPanel
    {
        protected void paintComponent(Graphics gIn)
        {
            //super.paintComponents(g);
            //Создаём сетку

            Graphics2D g = (Graphics2D)gIn.create();
            g.scale(now_zoom, last_zoom );
            g.setColor(Color.WHITE);
            g.fillRect(0, 0,this.getSize().width, this.getSize().height);
            g.setColor(Color.gray);
            for(int i = 0; i <= this.getSize().height; i+=step)
            {
                g.drawLine(0, i, this.getSize().width , i);
            }

            for(int i = 0; i <= this.getSize().width; i+=step)
            {
                g.drawLine(i, 0, i, this.getSize().height);
            }
            //Создаём оси
            g.setColor(Color.black);
            g.drawLine(0,this.getSize().height/2,this.getSize().width,this.getSize().height/2);
            g.drawLine(this.getSize().width/2,0,this.getSize().width/2,this.getSize().height);
            g.setColor(Color.black);


            g.drawString("X",this.getSize().width - 10,this.getSize().height/2 - 3);
            g.drawString("Y",this.getSize().width/2 + 3, 0 + 15);
            //countLabel.setText("Синий - Аппроксимация");
            //countLabel.setText("Красный - Интерполяция");
            double X[] = new double[N];
            double mark = 2;
            for(int i = 0; i < N;++i)
            {
                X[i] = mark;
                mark += 2;
                //System.out.print(X[i]);
                //System.out.print(' ');
            }
            System.out.print('\n');
            double F[] = new double[N];
            /*F[0] = 2.5;
            F[1] = 1.2;
            F[2] = 1.12;
            F[3] = 2.25;
            F[4] = 4.28;*/
            for(int i = 0; i < N;++i)
            {
                F[i] = 1/2 * X[i]*X[i] + Math.cos(2*X[i]) ;
                //System.out.print(X[i]);
                //System.out.print(' ');
            }
            Deg = (int)spinner.getValue();
            Approx Dog = new Approx(X,F,Deg);
            Interp Cat = new Interp(X,F);
            //Dog.printAns();
            int def = 200;
            double Xap[] = new double[def];
            double Yap[] = new double[def];
            double Xint[] = new double[def];
            double Yint[] = new double[def];
            int Xone[] = new int[def];
            int Yone[] = new int[def];
            for (int i = 0; i < def; ++i)
            {
                Xap[i] = i - def/2;
                Xint[i] = i - def/2;
            }
            Yap = Dog.getValue(Xap);
            Yint = Cat.getValue(Xint);
            //Приводим кардинаты к целочисленному типу
            System.out.print("\nApproximator\n");
            int n = def;
            for (int i = 0; i < n; ++i)
            {
                Xone[i] = this.getSize().width/2 + (int)(Xap[i] * step);
                Yone[i] = this.getSize().height/2 + (int)(Yap[i] * step);
            }
            g.setColor(Color.blue);
            //Аппроксимация
            g.drawPolyline(Xone, Yone, n);
			/*
			//Проверка приведения типов
			System.out.print("\nYan and Yone\n"); 
			for (int i = 0; i < 50; ++i) 
			{ 
				System.out.print(Yan[i]); 
				System.out.print(' ');
				System.out.print(Yone[i]); 
				System.out.print('\n'); 
			}
			*/
            for (int i = 0; i < n; ++i)
            {
                Xone[i] = this.getSize().width/2 + (int)(Xint[i] * step);
                Yone[i] = this.getSize().height/2 + (int)(Yint[i] * step);
            }
            g.setColor(Color.red);
            int Xoval[] = new int[N];
            int Yoval[] = new int[N];
            for(int i = 0; i < N; ++i)
            {
                Xoval[i] = this.getSize().width/2 + (int)(X[i] * step);
                Yoval[i] = this.getSize().height/2 + (int)(F[i] * step);
                //g.drawOval(Xoval[i], Yoval[i], step*2, step*2);
            }
            //Интерполяция
            g.drawPolyline(Xone, Yone, n);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0,150, 40);
            Color newColor = new Color(89, 74, 167);
            g.setColor(newColor);
            g.drawString("Cиний - Аппроксимация",0,10);
            Color newCol = new Color(167, 21, 5);
            g.setColor(newCol);
            g.drawString("Красный - Интерполяция",0,30);
        }
    }
}