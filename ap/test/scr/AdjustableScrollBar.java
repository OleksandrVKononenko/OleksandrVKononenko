package ap.test.scr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdjustableScrollBar implements AdjustmentListener {

    static JScrollBar sbarR, sbarG, sbarB;
    static JLabel lbl;
    static JPanel panel2;
    static int r, g, b;
    static AdjustableScrollBar nb;

    public static void main(String[] args) {

        nb = new AdjustableScrollBar();

        JFrame frame = new JFrame("Test");
        frame.setLayout(new GridLayout(1,2));
        frame.setBounds(100, 100, 200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 3));
        panel2 = new JPanel();

        lbl = new JLabel("Test value");
        lbl.setOpaque(true);
        
        lbl.setForeground(Color.WHITE);

        sbarR = new JScrollBar(Adjustable.VERTICAL, 0, 1, 0, 255);
        sbarG = new JScrollBar(Adjustable.VERTICAL, 0, 1, 0, 255);
        sbarB = new JScrollBar(Adjustable.VERTICAL, 0, 1, 0, 255);

        sbarR.addAdjustmentListener(nb);
        sbarG.addAdjustmentListener(nb);
        sbarB.addAdjustmentListener(nb);

        panel2.add(lbl);        
        frame.add(panel1);
        frame.add(panel2);

        panel1.add(sbarR);
        panel1.add(sbarG);
        panel1.add(sbarB);

        frame.setVisible(true);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {

        r = sbarR.getValue();
        g = sbarG.getValue();
        b = sbarB.getValue();

        lbl.setBackground(new Color(r, g ,b));
    }
}
