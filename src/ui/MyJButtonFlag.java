/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;


@SuppressWarnings("serial")
public class MyJButtonFlag extends JButton {

    public MyJButtonFlag() {
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/flag.PNG")));
        setContentAreaFilled(false);
        setBorder(null);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            }

            public void mouseExited(MouseEvent e) {
                setBorder(null);
            }
        });
    }

}
