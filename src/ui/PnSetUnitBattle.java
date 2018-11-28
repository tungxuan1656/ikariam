/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Font;

import javax.swing.JSlider;
import javax.swing.JTextField;
import model.Army;


@SuppressWarnings("serial")
public class PnSetUnitBattle extends PnSetUnit {
    
    JTextField txtMax;
    Army.Unit unit;
    
    public PnSetUnitBattle(Army.Unit unit) {
        super(unit);
        this.unit = unit;
        
        lblIcon.setBounds(150, 5, 50, 45);
        lblName.setBounds(210, 12, 100, 26);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sld.setBounds(360, 12, 350, 26);
        txt.setBounds(750, 12, 45, 26);
        txt.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSetMax.setBounds(790, 12, 70, 26);
        
        txtMax = new JTextField(3);
        txtMax.setBounds(300, 12, 45, 26);
        txtMax.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.add(txtMax);
        
    }

    public JSlider getSlider() {
        return sld;
    }
    public JTextField getJTextField() {
        return txt;
    }
    public JTextField getJTextFieldMax() {
        return txtMax;
    }
    
    public int getValueOfSlider() {
        return this.sld.getValue();
    }
}
