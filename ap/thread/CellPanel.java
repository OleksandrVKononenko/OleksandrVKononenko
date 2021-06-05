 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.thread; 

import static java.util.stream.Collectors.toMap; 

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Insets; 
import java.awt.Rectangle; 
import java.awt.event.MouseEvent; 
import java.util.Collections; 
import java.util.HashMap; 
import java.util.LinkedHashMap; 
import java.util.Map; 

import javax.swing.JFrame; 

import ap.area.AreaManager; 
import ap.btn.Event; 
import ap.btn.Fire; 
import ap.btn.TGridRect; 
import ap.global.gl; 
import ap.shape.Ru; 
import ap.utils.Biu; 
import ap.utils.GU; 

@SuppressWarnings("serial") 
public class CellPanel extends Panel { 

	public CellPanel() { 
		 
	} 

	public CellPanel(String text) { 
		super(text); 
		 
	} 

	public CellPanel(String text, Dimension size) { 
		super(text, size); 
		 
	} 

	public CellPanel(String text, Rectangle bounds) { 
		super(text, bounds); 
		 
	} 

	public CellPanel(Dimension size) { 
		super(size); 
		 
	} 
	 
	public static void createAndShowGUI() { 

		JFrame frame = new JFrame("Test application"); 

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		 
		// Desktop. 

		Panel desktop = new Panel(new Dimension(Panel.PREF_W,Panel.PREF_H)); 
		 
		desktop.setBackground(Color.darkGray); 

		// Insert child items. 
		 
		AreaManager am = new AreaManager(desktop.getPreferedSize(),new Dimension(10,10)); 

		desktop.setLayout(null); 
		 
		int [] index = {0}; 
		 
		// Add to items collection. 
		am.getRects().forEach(a-> 
		{ 
			CellPanel cell = new CellPanel("",a); 

			cell.setBackground(gl.getRandomColor()); 
			 
			cell.setIndex(index[0]); 
			 
			cell.setText(String.format("%s [%d][%d] ","C",cell.getId(),cell.getIndex())); 
	 
			items.put(cell.getId(),cell); 
			 
			cell.setDesktop(desktop); 
		 
			index[0]++; 
			 
		}); 
		 
		desktop.removeAll(); 
		 
		items.forEach((k,v)->{ 
		 
			desktop.add(v,v.getIndex()); 
		}); 
		 
		Map<Integer,Panel> sorted = new HashMap<Integer,Panel>(); 
		 
		items.forEach((k,v)->{ 
			 
			sorted.put(v.getIndex(),v); 
			 
		}); 
		 
		frame.getContentPane().add(desktop); 

		frame.pack(); 

		frame.setLocationRelativeTo(null); 

		frame.setVisible(true); 
		 
	} 
	 
	@Override 
	public void paintComponent(Graphics g) { 
		 
		super.paintComponent(g); 
		 
		GU.drawAlphaRectB( 
				(Graphics2D)g, 
				Ru.get_spanned_rect(Ru.norm4g(this.getBounds()),new Insets(2,2,4,4)), 
				gl.ma_on_select_style, 
				Biu.TESTB(this.getState(),Panel.ENTERED)); 
		 
		GU.drawAlphaRectB( 
				(Graphics2D) g, 
				Ru.get_spanned_rect(Ru.norm4g(this.getBounds()),new Insets(5,5,10,10)), 
				gl.ma_on_selected_style, 
				Biu.TESTB(this.getState(),Panel.SELECTED)); 
		 
		 
	} 
	 
	 
	@Override 
	public void mouseEntered(MouseEvent e) { 
		 
		super.mouseEntered(e); 
	 
		this.getDesktop().repaint(); 
	} 

	@Override 
	public void mouseExited(MouseEvent e) { 
		 
		super.mouseExited(e); 
		 
		this.getDesktop().repaint(); 
		 
	} 
	 
	 
	public static void main(String[] args) { 

		CellPanel.createAndShowGUI(); 

	} 

} 
