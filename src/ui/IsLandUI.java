/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.House;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Dat Ngo
 */
@SuppressWarnings("serial")
public class IsLandUI extends JFrame{

	JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17, btnWaveAttack;
    JTextField txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10, txt11, txt12, txt13, txt14, txt15, txt16, txt17;
    JLabel lblIsLand;
    public static JButton currentButton;
    public static House currentHouse;
    public static JTextField currentTextField, currenNameHouse;
    public HouseInfoUI houseInfoUI;
    public static House[] house;
    public static House myHouse;
    public static BattleFieldFightingUI bffUI;
    @SuppressWarnings("rawtypes")
	public static JComboBox cbbTimeGo, cbbTimeWave, cbbTimeRound;
    private JPanel pnMain_1;
    
    public IsLandUI(){
        super("Ikariam");
        
//        try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//				| UnsupportedLookAndFeelException e1) {
//			e1.printStackTrace();
//		}
        
        setSize(1050, 665);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true); 
        
        house = new House[18];
        currentHouse = new House();
        currentButton = new JButton();
        for(int i=1; i<18; i++){
            house[i] = new House();
            house[i].setName("");
            house[i].setId(i);
        }
            
        houseInfoUI = new HouseInfoUI();
        
        addControls();
        setVisible(true);
        
        addEvents();
    }
    
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	public void addControls(){
        Container con = getContentPane();
        getContentPane().setLayout(null);
        JPanel pnMain = null;
  
        try {
        	final BufferedImage image = ImageIO.read(new File("E:\\OneDrive - Hanoi University of Science and Technology\\IT\\JAVA Neon\\Ikariam_Dat\\src\\image\\island.jpg"));
			
			pnMain_1 = new JPanel() {
	        	protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                g.drawImage(image, 0, 0, con.getSize().width, con.getSize().height, null);
	            }
	        };
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        pnMain_1.setBounds(0, 0, 3000, 2000);
        pnMain_1.setLayout(null);
        con.add(pnMain_1);
        
        
        
        btn1 = new MyJButtonFlag();
        btn1.setBounds(175, 200, 30, 30);
        txt1 = new JTextField();
        txt1.setBounds(165, 241, 60, 15);
        txt1.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt1.setEditable(false);
        txt1.setVisible(false);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 575, 26);
        pnMain_1.add(panel);
        panel.setLayout(null);
        
        JLabel lblTimeGo = new JLabel("  Tốc độ đi:   x");
        lblTimeGo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTimeGo.setBounds(0, 0, 107, 26);
        panel.add(lblTimeGo);
        
        cbbTimeGo = new JComboBox();
        cbbTimeGo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbbTimeGo.setModel(new DefaultComboBoxModel(new String[] {"0.5", "1", "2", "3", "5", "10", "15", "20", "30"}));
        cbbTimeGo.setSelectedIndex(1);
        cbbTimeGo.setBounds(101, 0, 60, 26);
        panel.add(cbbTimeGo);
        
        JLabel lblWaveAttack = new JLabel("Thời gian Wave Attack");
        lblWaveAttack.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblWaveAttack.setBounds(171, 0, 155, 26);
        panel.add(lblWaveAttack);
        
        cbbTimeWave = new JComboBox();
        cbbTimeWave.setModel(new DefaultComboBoxModel(new String[] {"1", "10", "30", "60", "120", "300", "600", "1200", "1800"}));
        cbbTimeWave.setSelectedIndex(1);
        cbbTimeWave.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbbTimeWave.setBounds(320, 0, 60, 26);
        panel.add(cbbTimeWave);
        
        JLabel lblThiGianHip = new JLabel("   Tốc độ t/g hiệp  x");
        lblThiGianHip.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblThiGianHip.setBounds(383, 0, 135, 26);
        panel.add(lblThiGianHip);
        
        cbbTimeRound = new JComboBox();
        cbbTimeRound.setModel(new DefaultComboBoxModel(new String[] {"0.5", "1", "2", "3", "5", "10", "15", "20", "30"}));
        cbbTimeRound.setSelectedIndex(1);
        cbbTimeRound.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbbTimeRound.setBounds(516, 0, 60, 26);
        panel.add(cbbTimeRound);
        pnMain_1.add(btn1);
        pnMain_1.add(txt1);
        
        btn2 = new MyJButtonFlag();
        btn2.setBounds(280, 225, 30 ,30);
        txt2 = new JTextField();
        txt2.setBounds(270, 260, 60, 15);
        txt2.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt2.setEditable(false);
        txt2.setVisible(false);
        pnMain_1.add(txt2);
        pnMain_1.add(btn2);
        
        btn3 = new MyJButtonFlag();
        btn3.setBounds(360, 180, 30 ,30);
        txt3 = new JTextField();
        txt3.setBounds(349, 221, 60, 15);
        txt3.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt3.setEditable(false);
        txt3.setVisible(false);
        pnMain_1.add(txt3);
        pnMain_1.add(btn3);
        
        btn4 = new MyJButtonFlag();
        btn4.setBounds(480, 116, 30 ,30);
        txt4 = new JTextField();
        txt4.setBounds(471, 157, 60, 15);
        txt4.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt4.setEditable(false);
        txt4.setVisible(false);
        pnMain_1.add(txt4);
        pnMain_1.add(btn4);
        
        btn5 = new MyJButtonFlag();
        btn5.setBounds(760, 155, 30 ,30);
        txt5 = new JTextField();
        txt5.setBounds(746, 187, 60, 15);
        txt5.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt5.setEditable(false);
        txt5.setVisible(false);
        pnMain_1.add(txt5);
        pnMain_1.add(btn5);
        
        btn6 = new MyJButtonFlag();
        btn6.setBounds(660, 100, 30 ,30);
        txt6 = new JTextField();
        txt6.setBounds(647, 141, 60, 15);
        txt6.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt6.setEditable(false);
        txt6.setVisible(false);
        pnMain_1.add(txt6);
        pnMain_1.add(btn6);
        
        btn7 = new MyJButtonFlag();
        btn7.setBounds(135, 289, 30 ,30);
        txt7 = new JTextField();
        txt7.setBounds(115, 330, 60, 15);
        txt7.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt7.setEditable(false);
        txt7.setVisible(false);
        pnMain_1.add(txt7);
        pnMain_1.add(btn7);
        
        btn8 = new MyJButtonFlag();
        btn8.setBounds(115, 430, 30 ,30);
        txt8 = new JTextField();
        txt8.setBounds(102, 471, 60, 15);
        txt8.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt8.setEditable(false);
        txt8.setVisible(false);
        pnMain_1.add(txt8);
        pnMain_1.add(btn8);
        
        btn9 = new MyJButtonFlag();
        btn9.setBounds(335, 450, 30 ,30);
        txt9 = new JTextField();
        txt9.setBounds(321, 480, 60,  15);
        txt9.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt9.setEditable(false);
        txt9.setVisible(false);
        pnMain_1.add(txt9);
        pnMain_1.add(btn9);
        
        btn10 = new MyJButtonFlag();
        btn10.setBounds(230, 475, 30 ,30);
        txt10 = new JTextField();
        txt10.setBounds(215, 505, 60, 15);
        txt10.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt10.setEditable(false);
        txt10.setVisible(false);
        pnMain_1.add(txt10);
        pnMain_1.add(btn10);
        
        btn11 = new MyJButtonFlag();
        btn11.setBounds(380, 530, 30 ,30);
        txt11 = new JTextField();
        txt11.setBounds(370, 563, 60,  15);
        txt11.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt11.setEditable(false);
        txt11.setVisible(false);
        pnMain_1.add(txt11);
        pnMain_1.add(btn11);
        
        btn12 = new MyJButtonFlag();
        btn12.setBounds(611, 505, 30 ,30);
        txt12 = new JTextField();
        txt12.setBounds(600, 546, 60, 15);
        txt12.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt12.setEditable(false);
        txt12.setVisible(false);
        pnMain_1.add(txt12);
        pnMain_1.add(btn12);
        
        btn13 = new MyJButtonFlag();
        btn13.setBounds(710, 450, 30 ,30);
        txt13 = new JTextField();
        txt13.setBounds(703, 480, 60, 15);
        txt13.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt13.setEditable(false);
        txt13.setVisible(false);
        pnMain_1.add(txt13);
        pnMain_1.add(btn13);
        
        btn14 = new MyJButtonFlag();
        btn14.setBounds(825, 430, 30 ,30);
        txt14 = new JTextField();
        txt14.setBounds(815, 468, 60,  15);
        txt14.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt14.setEditable(false);
        txt14.setVisible(false);
        pnMain_1.add(txt14);
        pnMain_1.add(btn14);
        
        btn15 = new MyJButtonFlag();
        btn15.setBounds(825, 341, 30 ,30);
        txt15 = new JTextField();
        txt15.setBounds(815, 382, 60, 15);
        txt15.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt15.setEditable(false);
        txt15.setVisible(false);
        pnMain_1.add(txt15);
        pnMain_1.add(btn15);
        
        btn16 = new MyJButtonFlag();
        btn16.setBounds(815, 255, 30 ,30);
        txt16 = new JTextField();
        txt16.setBounds(799, 289, 60,  15);
        txt16.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt16.setEditable(false);
        txt16.setVisible(false);
        pnMain_1.add(txt16);
        pnMain_1.add(btn16);
        
        btn17 = new MyJButtonFlag();
        btn17.setBounds(680, 265, 30 ,30);
        txt17 = new JTextField();
        txt17.setBounds(672, 307, 60, 15);
        txt17.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        txt17.setEditable(false);
        txt17.setVisible(false);
        pnMain_1.add(txt17);
        pnMain_1.add(btn17);
        
        btnWaveAttack = new JButton("");
        btnWaveAttack.setBounds(775, 10, 70, 100);
        btnWaveAttack.setOpaque(false);
        btnWaveAttack.setContentAreaFilled(false);
        btnWaveAttack.setBorder(null);
        pnMain_1.add(btnWaveAttack);
        
//        lblIsLand = new JLabel();
//        lblIsLand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/island.jpg")));
//        pnMain.add(lblIsLand);
//        lblIsLand.setBounds(0, 0, 1000, 665);
        
    }
    
    public void addEvents(){
        btnWaveAttack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(myHouse!=null)
                    new WaveAttackUI("WaveAttack", myHouse.getSentRealArmy(), myHouse.getSendingArmy()).setVisible(true);
            }
        });
        
        
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[1];
                currentButton = btn1;
                currentTextField = txt1;
                houseInfoUI.showWindow();
            }
        });
        
        
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[2];
                currentButton = btn2;
                currentTextField = txt2;
                houseInfoUI.showWindow();
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[3];
                currentButton = btn3;
                currentTextField = txt3;
                houseInfoUI.showWindow();
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[4];
                currentButton = btn4;
                currentTextField = txt4;
                houseInfoUI.showWindow();
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[5];
                currentButton = btn5;
                currentTextField = txt5;
                houseInfoUI.showWindow();
            }
        });
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[6];
                currentButton = btn6;
                currentTextField = txt6;
                houseInfoUI.showWindow();
            }
        });
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[7];
                currentButton = btn7;
                currentTextField = txt7;
                houseInfoUI.showWindow();
            }
        });
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[8];
                currentButton = btn8;
                currentTextField = txt8;
                houseInfoUI.showWindow();
            }
        });
        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[9];
                currentButton = btn9;
                currentTextField = txt9;
                houseInfoUI.showWindow();
            }
        });
        btn10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[10];
                currentButton = btn10;
                currentTextField = txt10;
                houseInfoUI.showWindow();
            }
        });
        btn11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[11];
                currentButton = btn11;
                currentTextField = txt11;
                houseInfoUI.showWindow();
            }
        });
        btn12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[12];
                currentButton = btn12;
                currentTextField = txt12;
                houseInfoUI.showWindow();
            }
        });
        btn13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[13];
                currentButton = btn13;
                currentTextField = txt13;
                houseInfoUI.showWindow();
            }
        });
        btn14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[14];
                currentButton = btn14;
                currentTextField = txt14;
                houseInfoUI.showWindow();
            }
        });
        btn15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[15];
                currentButton = btn15;
                currentTextField = txt15;
                houseInfoUI.showWindow();
            }
        });
        btn16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[16];
                currentButton = btn16;
                currentTextField = txt16;
                houseInfoUI.showWindow();
            }
        });
        btn17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentHouse = house[17];
                currentButton = btn17;
                currentTextField = txt17;
                houseInfoUI.showWindow();
            }
        });
        
        // resize this frame
        addComponentListener(new ComponentAdapter() {
        	@Override
        	public void componentResized(ComponentEvent arg0) {
        		int w = arg0.getComponent().getWidth();
        		int h = arg0.getComponent().getHeight();
        		btn1.setLocation((int)(0.175*w), (int)(0.28*h));
        		txt1.setLocation((int)(0.165*w), (int)(0.39*h));
        		
        		btn2.setLocation((int)(0.28*w), (int)(0.338*h));
        		txt2.setLocation((int)(0.27*w), (int)(0.39*h));
        		
        		btn3.setLocation((int)(0.36*w), (int)(0.27*h));
        		txt3.setLocation((int)(0.349*w), (int)(0.332*h));
        		
        		btn4.setLocation((int)(0.48*w), (int)(0.174*h));
        		txt4.setLocation((int)(0.471*w), (int)(0.236*h));
        		
        		btn5.setLocation((int)(0.76*w), (int)(0.233*h));
        		txt5.setLocation((int)(0.746*w), (int)(0.281*h));
        		
        		btn6.setLocation((int)(0.66*w), (int)(0.15*h));
        		txt6.setLocation((int)(0.647*w), (int)(0.212*h));
        		
        		btn7.setLocation((int)(0.135*w), (int)(0.435*h));
        		txt7.setLocation((int)(0.115*w), (int)(0.5*h));
        		
        		btn8.setLocation((int)(0.115*w), (int)(0.6466*h));
        		txt8.setLocation((int)(0.102*w), (int)(0.708*h));
        		
        		btn9.setLocation((int)(0.335*w), (int)(0.677*h));
        		txt9.setLocation((int)(0.321*w), (int)(0.722*h));
        		
        		btn10.setLocation((int)(0.230*w), (int)(0.714*h));
        		txt10.setLocation((int)(0.215*w), (int)(0.76*h));
        		
        		btn11.setLocation((int)(0.38*w), (int)(0.8*h));
        		txt11.setLocation((int)(0.37*w), (int)(0.85*h));
        		
        		btn12.setLocation((int)(0.611*w), (int)(0.76*h));
        		txt12.setLocation((int)(0.6*w), (int)(0.82*h));
        		
        		btn13.setLocation((int)(0.71*w), (int)(0.677*h));
        		txt13.setLocation((int)(0.703*w), (int)(0.721*h));
        		
        		btn14.setLocation((int)(0.825*w), (int)(0.65*h));
        		txt14.setLocation((int)(0.815*w), (int)(0.704*h));
        		
        		btn15.setLocation((int)(0.825*w), (int)(0.513*h));
        		txt15.setLocation((int)(0.815*w), (int)(0.5744*h));
        		
        		btn16.setLocation((int)(0.815*w), (int)(0.383*h));
        		txt16.setLocation((int)(0.8*w), (int)(0.435*h));
        		
        		btn17.setLocation((int)(0.680*w), (int)(0.4*h));
        		txt17.setLocation((int)(0.672*w), (int)(0.46*h));
        		
        		btnWaveAttack.setLocation((int)(0.775*w), (int)(0.015*h));
        	}
        });
    }        
}

