/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import model.Army;
import java.awt.Font;

/**
 *
 * @author admin
 */
@SuppressWarnings("serial")
public class SetUnitJDialog extends JDialog {

    JPanel pnMain, pnTitle, pnLvHou, pnLvWall, pnTypeOfHou, pnButton, pnContent;
    JLabel lblLvHou, lblLvWall, lblTypeOfHou;
    JLabel lblImg, lblTitle, lblImgTitle;
    JSlider sldLevelHouse, sldLevelWall;
    JTextField txtLevelHouse, txtLevelWall;
    JComboBox<String> cbo;
    JButton btnDispose, btnPillage, btnBarrack, btnOK;
    PnSetUnit[] pnSetUnit;

    public SetUnitJDialog() throws HeadlessException {
        pnSetUnit = new PnSetUnit[12];
        addControls();
        addEvents();
    }

    public void addControls() {
        Container con = getContentPane();
        con.setLayout(null);
        lblImgTitle = new JLabel();
        lblImgTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lblHouTitle.png")));
        lblImgTitle.setBounds(20, 0, 60, 60);
        con.add(lblImgTitle);

        pnTitle = new JPanel(null);
        pnTitle.setBounds(0, 30, 700, 20);
        pnTitle.setBackground(new Color(215, 172, 116));
        con.add(pnTitle);
        lblTitle = new JLabel("House's Information");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTitle.setBounds(239, 0, 136, 20);
        pnTitle.add(lblTitle);
        btnDispose = new JButton(new ImageIcon(getClass().getResource("/Image/xButton.PNG")));
        btnDispose.setBounds(680, 0, 20, 20);
        pnTitle.add(btnDispose);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setPreferredSize(new Dimension(700, 1050));
        pnMain.setBackground(new Color(253, 247, 221));

        JScrollPane scDialog = new JScrollPane(pnMain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scDialog.getVerticalScrollBar().setUnitIncrement(20);
        scDialog.setBounds(0, 50, 700, 480);
        con.add(scDialog);

        int d = 0; //d is the distance between 2 panel
        for (Army.Unit unit : Army.Unit.values()) {
            if (unit.ordinal() == 12) {
                break;
            }
            pnSetUnit[unit.ordinal()] = new PnSetUnit(unit);
            pnSetUnit[unit.ordinal()].setBounds(0, 30 + d, 700, 50);
            pnMain.add(pnSetUnit[unit.ordinal()]);
            d += 80;
        }

        btnOK = new JButton("OK");
        btnOK.setBounds(240, 1010, 110, 30);
        btnOK.setBackground(new Color(248, 222, 164));
        pnMain.add(btnOK);

    }

    public void addEvents() {

        this.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent evt) {
                Army army = IsLandUI.currentHouse.getArmy();
                int i = 0;
                for (Army.Unit unit : Army.Unit.values()) {
                    if (unit.ordinal() == 12) {
                        break;
                    }
                    pnSetUnit[i].getSld().setValue(army.getNumberOf(unit));
                    i++;
                }

            }
        });

        btnDispose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Army army = new Army();
                for (Army.Unit unit : Army.Unit.values()) {
                    if (unit.ordinal() == 12) {
                        break;
                    }
                    army.setNumberOf(unit, pnSetUnit[unit.ordinal()].getSld().getValue());
                }
                army.setArmourUpgrade(IsLandUI.currentHouse.getArmy().getArmourUpgrade());
                army.setDamageUpgrade(IsLandUI.currentHouse.getArmy().getDamageUpgrade());
                IsLandUI.currentHouse.setArmy(army);
                getInstance().dispose();
            }
        });
    }

    private SetUnitJDialog getInstance() {
        return this;
    }

    //Show the HouseInfoUI dialog
    public void showWindows() {
        this.setSize(700, 530);
        setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);
    }

}
