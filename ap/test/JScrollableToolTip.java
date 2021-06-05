package ap.test;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class JScrollableToolTip extends JToolTip {

    private static final long serialVersionUID = 1L;
    private final MouseWheelListener mouseWheelListener;
    private final JTextArea tipArea;

    public JScrollableToolTip(final int rows, final int columns) {
        setLayout(new BorderLayout());
        mouseWheelListener = createMouseWheelListener();
        tipArea = new JTextArea(rows, columns);
        tipArea.setLineWrap(true);
        tipArea.setWrapStyleWord(true);
        tipArea.setEditable(false);
        LookAndFeel.installColorsAndFont(tipArea, "ToolTip.background",
                "ToolTip.foreground", "ToolTip.font");
        JScrollPane scrollpane = new JScrollPane(tipArea);
        scrollpane.setBorder(null);
        scrollpane.getViewport().setOpaque(false);
        add(scrollpane);
    }

    private MouseWheelListener createMouseWheelListener() {
        return new MouseWheelListener() {

            @Override
            public void mouseWheelMoved(final MouseWheelEvent e) {
                JComponent component = getComponent();
                if (component != null) {
                    tipArea.dispatchEvent(new MouseWheelEvent(tipArea, e.getID(),
                            e.getWhen(), e.getModifiers(), 0, 0, e.getClickCount(),
                            e.isPopupTrigger(), e.getScrollType(), e.getScrollAmount(),
                            e.getWheelRotation()));
                }
            }
        };
    }

    @Override
    public void addNotify() {
        super.addNotify();
        JComponent component = getComponent();
        if (component != null) {
            component.addMouseWheelListener(mouseWheelListener);
        }
    }

    @Override
    public void removeNotify() {
        JComponent component = getComponent();
        if (component != null) {
            component.removeMouseWheelListener(mouseWheelListener);
        }
        super.removeNotify();
    }

    @Override
    public void setComponent(JComponent c) {
        JComponent component = getComponent();
        if (component != null) {
            component.removeMouseWheelListener(mouseWheelListener);
        }
        super.setComponent(c);
    }

    @Override
    public void setTipText(final String tipText) {
        String oldValue = this.tipArea.getText();
        tipArea.setText(tipText);
        tipArea.setCaretPosition(0);
        firePropertyChange("tiptext", oldValue, tipText);
    }

    @Override
    public Dimension getPreferredSize() {
        return getComponent(0).getPreferredSize();
    }

    @Override
    public String getTipText() {
        return tipArea == null ? "" : tipArea.getText();
    }

    @Override
    protected String paramString() {
        String tipTextString = (tipArea.getText() != null ? tipArea.getText() : "");
        return super.paramString() + ",tipText=" + tipTextString;
    }

    //for testing only:
    public static void main(final String args[]) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JFrame f = new JFrame("JScrollableToolTip");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(300, 200);
                f.setLocationRelativeTo(null);
                ToolTipManager.sharedInstance().setInitialDelay(500);
                ToolTipManager.sharedInstance().setDismissDelay(10000);
                ToolTipManager.sharedInstance().mousePressed(null);
                JTable table = new JTable(50, 4) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public JToolTip createToolTip() {
                        JScrollableToolTip tip = new JScrollableToolTip(3, 20);
                        tip.setComponent(this);
                        return tip;
                    }
                };
                table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value,
                            boolean isSelected, boolean hasFocus, int row, int column) {
                        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        setToolTipText("Row " + row + " Column " + column
                                + "\nUsed to display a 'Tip' for a Component. "
                                + "Typically components provide api to automate the process of "
                                + "using ToolTips. For example, any Swing component can use the "
                                + "JComponent  setToolTipText method to specify the text for a standard tooltip.");

                        return this;
                    }
                });
                f.add(new JScrollPane(table));
                f.setVisible(true);
            }
        });
    }
}
