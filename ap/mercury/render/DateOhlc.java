package ap.mercury.render;

import javax.swing.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Component;
import javax.swing.table.TableCellRenderer;

import ap.utils.DateUtil;
import ap.utils.Nu;

import javax.swing.table.DefaultTableCellRenderer;

public class DateOhlc extends JLabel implements TableCellRenderer {

	//private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu HH:mm:ss");
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	
	private static final long serialVersionUID = 1L;

	
	public Component getTableCellRendererComponent
	(JTable table,Object value, boolean isSelected, boolean hasFocus, int row,int col) 
	{
    
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		
		Component c = renderer.getTableCellRendererComponent(table,value, isSelected, hasFocus, row, col);
      
      	String s = "";
      
      	if (Nu.in(col,new Integer[] {1,2,3,4})) {
         
      		DecimalFormat dFormat = new DecimalFormat("#0.0");
         
      		Double d = (Double) value;
         
      		s = dFormat.format(d);
         
      		c = renderer.getTableCellRendererComponent(table, s,isSelected, hasFocus, row, col);
         
      		((JLabel) c).setHorizontalAlignment(SwingConstants.RIGHT);
      }
      	else if(col == 0)
      	{
      		
      		String formattedDateTime = DateUtil.to_string(DateUtil.to_date((String)value));//((LocalDateTime) value).format(formatter);
             
            c = renderer.getTableCellRendererComponent(table,
                    formattedDateTime, isSelected, hasFocus, row, col);
            
            ((JLabel) c).setHorizontalAlignment(SwingConstants.LEFT);
        
            return c;
      		
      	}
      		return null;
   }
}

/*
public class MyDateTimeRenderer implements TableCellRenderer {

private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu HH;mm;ss");

@Override
public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    String formattedDateTime = ((LocalDateTime) value).format(formatter);
    Component c = renderer.getTableCellRendererComponent(table,
            formattedDateTime, isSelected, hasFocus, row, column);
    return c;
}

}
*/
