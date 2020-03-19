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
import javax.swing.*;

public class ProgressBar extends JPanel {

    JProgressBar pbar;
    static int MY_MINIMUM = 0;
    static int MY_MAXIMUM = 100;

    public ProgressBar() {
        pbar = new JProgressBar();
        pbar.setMinimum(MY_MINIMUM);
        pbar.setMaximum(MY_MAXIMUM);
        add(pbar);
    }

    public void updateBar(int newValue) {
        pbar.setValue(newValue);
    }

    @SuppressWarnings("empty-statement")
    public static void main(String args[]) {

        final ProgressBar it = new ProgressBar();

        JFrame frame = new JFrame("Progress Bar Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(it);
        frame.pack();
        frame.setVisible(true);

        for (int i = MY_MINIMUM; i <= MY_MAXIMUM; i++) {
            final int percent = i;
            try {
                SwingUtilities.invokeLater(() -> {
                    it.updateBar(percent);
                });
                java.lang.Thread.sleep(100);
            } catch (InterruptedException e) {;
            }
        }
    }
}
