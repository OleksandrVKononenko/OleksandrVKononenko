package ap.test;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
public class TableDemo {
   public static void main(String[] args) {
      JFrame frame = new JFrame();
      JPanel panel = new JPanel();
      panel.setBorder(BorderFactory.createTitledBorder(
         BorderFactory.createEtchedBorder(), "My Demo Table", TitledBorder.LEFT, TitledBorder.TOP));
      String[][] rec = {
         { "001", "David", "AUS" },
         { "002", "Steve", "AUS" },
         { "003", "Yuvraj", "IND" },
         { "004", "Kane", "NZ" },
         { "005", "Ben", "ENG" },
         { "006", "Eion", "ENG" },
         { "007", "Miller", "SA" },
         { "008", "Rohit", "IND" }
      };
      String[] header = { "Id", "Player", "Team" };
      JTable table = new JTable(rec, header);
      panel.add(new JScrollPane(table));
      frame.add(panel);
      frame.setSize(550, 400);
      frame.setVisible(true);
   }
}