/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package normalisationautomatique;

/**
 *
 * @author lenovo
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JProgressBar;

public class NewClass extends JApplet {

    JProgressBar progress = new JProgressBar();
    JButton BtnIncrmnt = new JButton("Increase The Progress Bar");

    public void init() {
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        container.add(BtnIncrmnt);
        progress.setForeground(Color.blue);
        container.add(progress);
        BtnIncrmnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e1) {
                progress.setValue(progress.getValue() + 15);
            }
        });
        /*
         * PrgsBar.addChangeListener(new ChangeListener() {
         * public void stateChanged(ChangeEvent e2) {
         * showStatus("Progress Bar Mininum:" + PrgsBar.getMinimum() + "Maximum
         * :" + PrgsBar.getMaximum() + " value: " + PrgsBar.getValue());
         * }
         * });
         */
    }
}
