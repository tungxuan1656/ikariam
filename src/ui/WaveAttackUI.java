/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import model.Army;
import model.SendingArmy;
import model.SentArmy;

/**
 *
 * @author Dat Ngo
 */
@SuppressWarnings("serial")
public class WaveAttackUI extends JDialog {

    int size = 0;
    int wei = 0;
    JLabel[] lblTitle;
    public PnWaveAttack[] pnWave;
    HashMap<Integer, ArrayList<SentArmy>> sentRealArmy;
    HashMap<Integer, PriorityQueue<SendingArmy>> sendingArmy;
    int count = 0;
    public static long timeRound = (long) (15*60000*1.0/(Integer.parseInt((String) IsLandUI.cbbTimeRound.getSelectedItem())));
    JPanel pnMain;

    public WaveAttackUI(String title, HashMap<Integer, ArrayList<SentArmy>> sentRealArmy, HashMap<Integer, PriorityQueue<SendingArmy>> sendingArmy) {
        setTitle(title);
        setSize(820, 500);
        setLocationRelativeTo(null);

        lblTitle = new JLabel[7];
        sentRealArmy.forEach((key, arrList) -> {
            size += arrList.size();
        }
        );
        sendingArmy.forEach((key, queue) -> {
            wei += queue.size();
        });
        pnWave = new PnWaveAttack[size + wei];
        this.sentRealArmy = sentRealArmy;
        this.sendingArmy = sendingArmy;
        addControls();
        
        // đếm thời gian sau khi điều quân tới nơi thì mở giao diện đánh nhau
//        java.util.Timer timmm = new java.util.Timer();
//        timmm.schedule(new TimerTask() {
//            @Override
//            public void run() {
//            	dispose();
//                IsLandUI.bffUI = new BattleFieldFightingUI(IsLandUI.currentHouse.getBattleFieldFighting());
//                IsLandUI.bffUI.showWindow();
//            }
//        }, timeRound);
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void addControls() {
        Container con = getContentPane();
        pnMain = new JPanel();
        JScrollPane sc = new JScrollPane(pnMain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sc.getVerticalScrollBar().setUnitIncrement(20);
        con.add(sc);
        pnMain.setLayout(null);
        pnMain.setBackground(new Color(221, 176, 109));
        for (int i = 0; i < 7; i++) {
            lblTitle[i] = new JLabel();
            lblTitle[i].setBounds(30 + 110 * i, 20, 100, 50);
            pnMain.add(lblTitle[i]);
        }
        lblTitle[0].setText("Mission");
        lblTitle[0].setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTitle[1].setText("Arrival time");
        lblTitle[1].setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTitle[2].setText("Munition");
        lblTitle[2].setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTitle[3].setText("Unit");
        lblTitle[3].setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTitle[4].setText("Origin");
        lblTitle[4].setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTitle[5].setText("Target");
        lblTitle[5].setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTitle[6].setText("Action");
        lblTitle[6].setFont(new Font("Tahoma", Font.PLAIN, 14));

        sentRealArmy.forEach((key, arrList) -> {
            for (int j = 0; j < arrList.size(); j++) {
                int numberOfUnit = 0;
                SentArmy sentArmy = arrList.get(arrList.size() - j - 1);
                for (int k = 0; k < sentArmy.getRealArmy().getArcher().size(); k++) {
                    if (sentArmy.getRealArmy().getArcher().get(k).getHitPoint() != 0) {
                        numberOfUnit++;
                    }
                }
                for (int k = 0; k < sentArmy.getRealArmy().getBB().size(); k++) {
                    if (sentArmy.getRealArmy().getBB().get(k).getHitPoint() != 0) {
                        numberOfUnit++;
                    }
                }
                for (int k = 0; k < sentArmy.getRealArmy().getCatapult().size(); k++) {
                    if (sentArmy.getRealArmy().getCatapult().get(k).getHitPoint() != 0) {
                        numberOfUnit++;
                    }
                }
                for (int k = 0; k < sentArmy.getRealArmy().getHop().size(); k++) {
                    if (sentArmy.getRealArmy().getHop().get(k).getHitPoint() != 0) {
                        numberOfUnit++;
                    }
                }
                for (int k = 0; k < sentArmy.getRealArmy().getMortar().size(); k++) {
                    if (sentArmy.getRealArmy().getMortar().get(k).getHitPoint() != 0) {
                        numberOfUnit++;
                    }
                }
                for (int k = 0; k < sentArmy.getRealArmy().getRam().size(); k++) {
                    if (sentArmy.getRealArmy().getRam().get(k).getHitPoint() != 0) {
                        numberOfUnit++;
                    }
                }
                for (int k = 0; k < sentArmy.getRealArmy().getSC().size(); k++) {
                    if (sentArmy.getRealArmy().getSC().get(k).getHitPoint() != 0) {
                        numberOfUnit++;
                    }
                }
                for (int k = 0; k < sentArmy.getRealArmy().getSlinger().size(); k++) {
                    if (sentArmy.getRealArmy().getSlinger().get(k).getHitPoint() != 0) {
                        numberOfUnit++;
                    }
                }
                for (int k = 0; k < sentArmy.getRealArmy().getSpear().size(); k++) {
                    if (sentArmy.getRealArmy().getSpear().get(k).getHitPoint() != 0) {
                        numberOfUnit++;
                    }
                }
                for (int k = 0; k < sentArmy.getRealArmy().getSteam().size(); k++) {
                    if (sentArmy.getRealArmy().getSteam().get(k).getHitPoint() != 0) {
                        numberOfUnit++;
                    }
                }
                for (int k = 0; k < sentArmy.getRealArmy().getSword().size(); k++) {
                    if (sentArmy.getRealArmy().getSword().get(k).getHitPoint() != 0) {
                        numberOfUnit++;
                    }
                }
                for (int k = 0; k < sentArmy.getRealArmy().getGyrocopter().size(); k++) {
                    if (sentArmy.getRealArmy().getGyrocopter().get(k).getHitPoint() != 0) {
                        numberOfUnit++;
                    }
                }
                int temp = count;
                if (numberOfUnit != 0) {
                    pnWave[count] = new PnWaveAttack(sentArmy, key);
                    pnWave[count].getLblOrigin().setText(IsLandUI.myHouse.getName());
                    pnWave[count].getLblTarget().setText(IsLandUI.house[key].getName());
                    pnWave[count].getLblUnit().setText(numberOfUnit + "");
                    pnWave[count].getLblMission().setIcon(new ImageIcon(getClass().getResource("/Image/Pillage_Enabled.PNG")));

                    SimpleDateFormat ft = new SimpleDateFormat("mm:ss");

                    
                    // thời gian mỗi hiệp (hoặc thời gian rút quân)
                    // cài đặt bởi arrivaltime khi đánh bt hoặc rút quân
                    Timer roundclock = new Timer(1000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	pnWave[temp].getLblArrivalTime().setText(ft.format(new Date( - System.currentTimeMillis() + sentArmy.getArrivalTime())));
//                        	System.out.println(ft.format(new Date( - System.currentTimeMillis() + sentArmy.getArrivalTime())));
                        }
                    });
                    roundclock.start();
                    
                    if (sentArmy.isdel) {
                    	
                    	IsLandUI.myHouse.getSentRealArmy().get(pnWave[count].id).remove(pnWave[count].sentArmy);
                    	sentArmy.isdel = false;
                    }
                    
                    pnWave[count].setBounds(20, 60 * count + 75, 820, 50);
                    pnMain.add(pnWave[count]);
                    count++;
                }

            }

        }
        );

        sendingArmy.forEach((key, queue) -> {
            Iterator<SendingArmy> iterator = queue.iterator();
            while (iterator.hasNext()) {
                int numberOfUnit = 0;
                SendingArmy sendingArmy = iterator.next();

                Army army = sendingArmy.getArmy();

                for (Army.Unit unit : Army.Unit.values()) {
                    numberOfUnit += army.getNumberOf(unit);
                }
                int temp = count;
                if (numberOfUnit != 0) {
                    pnWave[count] = new PnWaveAttack(sendingArmy, key, this.sendingArmy.get(key));
                    pnWave[count].getLblOrigin().setText(IsLandUI.myHouse.getName());
                    pnWave[count].getLblTarget().setText(IsLandUI.house[key].getName());
                    pnWave[count].getLblUnit().setText(numberOfUnit + "");
//                    pnWave[count].getLblMission().setIcon(new ImageIcon(getClass().getResource("/Image/arival.PNG")));
                    pnWave[count].getLblMission().setIcon(new ImageIcon(getClass().getResource("/Image/Pillage_Enabled.PNG")));
                    SimpleDateFormat ft = new SimpleDateFormat("mm:ss");
                    
                    long temptime = System.currentTimeMillis();
                    Timer dongho = new Timer(1000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
//                            pnWave[temp].getLblArrivalTime().setText(ft.format(new Date(System.currentTimeMillis() - sendingArmy.getStartTime())));
                        	pnWave[temp].getLblArrivalTime().setText(ft.format(new Date(- System.currentTimeMillis() + sendingArmy.getFinishTime())));
                        }
                    });
                    dongho.start();
                    
                    // đếm thời gian sau khi điều quân tới nơi thì mở giao diện đánh nhau
                    java.util.Timer timmm = new java.util.Timer();
                    
                	timmm.schedule(new TimerTask() {
                		@Override
                		public void run() {
                			dispose();
                			IsLandUI.bffUI = new BattleFieldFightingUI(IsLandUI.currentHouse.getBattleFieldFighting());
                			IsLandUI.bffUI.showWindow();
                		}
                	},sendingArmy.getFinishTime() - temptime);
                    

                    pnWave[count].setBounds(20, 60 * (count) + 75, 820, 50);
                    pnMain.add(pnWave[count]);
                    count++;
                }
            }
        });

    }
}
