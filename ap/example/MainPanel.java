package ap.example;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public final class MainPanel extends JPanel {
    private final String[] columnNames = {"family", "name", "postscript name"};
    private final DefaultTableModel model = new DefaultTableModel(null, columnNames) {
        @Override public boolean isCellEditable(int row, int column) {
            return true;
        }
        @Override public Class<?> getColumnClass(int column) {
            return String.class;
        }
    };
    private final JTable table = new JTable(model);
    public MainPanel() {
        super(new BorderLayout());
        table.setAutoCreateRowSorter(true);
        Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        for (Font f: fonts) {
            model.addRow(new Object[] {f.getFamily(), f.getName(), f.getPSName()});
        }
        add(new JScrollPane(table));
        //add(new JScrollPane(new JList(fonts)));
        setPreferredSize(new Dimension(320, 240));
    }
    public static void main(String... args) {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                createAndShowGUI();
            }
        });
    }
    public static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
               | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        JFrame frame = new JFrame("@title@");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MainPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
