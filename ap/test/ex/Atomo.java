 
 
 
 
 
package ap.test.ex; 

import javax.swing.*; 


import java.awt.*; 
import java.awt.event.*; 
import java.util.*; 


@SuppressWarnings("serial") 
public class Atomo extends JPanel implements MouseListener { 

	private Dimension area; // indicates area taken up by graphics 
	 
	private Vector<JPanel> panels; // coordinates used to draw graphics 
	 
	private Atomo desktop; 

	private final Color colors[] = { Color.red, Color.blue, Color.green, 
			Color.orange, Color.cyan, Color.magenta, Color.darkGray, 
			Color.yellow }; 

	private final int color_n = colors.length; 

	public Atomo() { 

		super(new BorderLayout()); 

		area = new Dimension(0, 0); 
		 
		panels = new Vector<JPanel>(); 

		 
		desktop = Atomo.get_instance(); 
		 
		desktop.setBackground(Color.white); 
		 
		desktop.setName("Desktop"); 
		 
		desktop.addMouseListener(this); 
		 

		JScrollPane scroller = new JScrollPane(desktop); 

		scroller.setPreferredSize(new Dimension(200, 200)); 

		add(scroller); 
		 
	} 
	 
	public Atomo(Rectangle rect) 
	{ 
		this(); 
		 
		this.setBounds(rect); 
	} 

	 
	// Handle mouse events. 
	public void mouseReleased(MouseEvent e) { 

		final int W = 50; 

		final int H = 50; 

		boolean changed = false; 

		if (SwingUtilities.isRightMouseButton(e)) { 

			// This will clear the graphic objects. 

			panels.removeAllElements(); 

			area.width = 0; 

			area.height = 0; 

			changed = true; 

		} else { 

			int x = e.getX() - W / 2; 

			int y = e.getY() - H / 2; 

			if (x < 0) 
				x = 0; 
			if (y < 0) 
				y = 0; 
			 
			Atomo atom = Atomo.get_instance(new Rectangle(x, y, W, H)); 
			 
			//atom.getStio().set_deny_drag(false); 
			 
			 

			//JPanel pan = new JPanel(); 
			 
			//pan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); 

			//pan.setBounds(new Rectangle(x, y, W, H)); 
			 
			//pan.setBackground(UIManager.getColor("Panel.background")); 

			//drawingPane.add(atom); 
			 
			//desktop.insert(atom); 
			 
			desktop.add(atom); 
			 
			desktop.scrollRectToVisible(atom.getBounds()); 

			int this_width = (x + W + 2); 

			if (this_width > area.width) { 
				area.width = this_width; 
				changed = true; 
			} 

			int this_height = (y + H + 2); 

			if (this_height > area.height) { 
				area.height = this_height; 
				changed = true; 
			} 
		} 
		if (changed) { 
			// Update client's preferred size because 
			// the area taken up by the graphics has 
			// gotten larger or smaller (if cleared). 
			desktop.setPreferredSize(area); 

			// Let the scroll pane know to update itself 
			// and its scrollbars. 
			desktop.revalidate(); 
		} 
		 
			desktop.repaint(); 
	} 
	 
	 
	 
	public static Atomo get_instance(Rectangle rect) 
	{ 
		return new Atomo(rect); 
	} 
	 
	public static Atomo get_instance() 
	{ 
		return new Atomo(); 
	} 


	public void mouseClicked(MouseEvent e) { 
	} 

	public void mouseEntered(MouseEvent e) { 
	} 

	public void mouseExited(MouseEvent e) { 
	} 

	public void mousePressed(MouseEvent e) { 
	} 

	private static void createAndShowGUI() { 
		 
		JFrame 		frame = new JFrame("Atomo"); 
		 
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		JComponent 	newContentPane = new ScrollPaneSuper(); 
		 
					newContentPane.setOpaque(true); // content panes must be opaque 
		 
					frame.setContentPane(newContentPane); 

					frame.pack(); 
		 
					frame.setVisible(true); 
	} 

	public static void main(String[] args) { 

		javax.swing.SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				createAndShowGUI(); 
			} 
		}); 
		 
	} 
} 

