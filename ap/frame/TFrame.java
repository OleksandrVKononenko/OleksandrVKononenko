 
package ap.frame; 

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Component; 
import java.awt.Dimension; 
import java.awt.Font; 
import java.awt.Frame; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.Stroke; 
import java.awt.event.ComponentEvent; 
import java.awt.event.ComponentListener; 
import java.awt.event.KeyAdapter; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.awt.event.MouseWheelEvent; 
import java.awt.event.MouseWheelListener; 
import java.awt.image.BufferedImage; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 
import java.util.Vector; 

import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.SwingUtilities; 
import javax.swing.UIManager; 
import javax.swing.UnsupportedLookAndFeelException; 
import javax.swing.UIManager.LookAndFeelInfo; 

import ap.collectors.SPanelCollector; 
import ap.force.Odin; 
import ap.global.gl; 
import ap.menu.TManagerPopupFactory; 
import ap.mng.SPanelManager; 
import ap.btn.TPanel; 
import ap.btn.TTick; 
import ap.prop.SBounds; 
import ap.shape.Ru; 
import ap.utils.CU; 
import ap.utils.Fu; 
import ap.utils.GU; 
import ap.utils.ImageUtil; 

public class  TFrame extends JPanel implements ComponentListener, 
		MouseListener, MouseMotionListener, MouseWheelListener, KeyListener { 

	private static final long serialVersionUID = 1L; 

	public static Rectangle location = null; 

	public Point startPos = null; 

	public Rectangle selector = new Rectangle(0, 0, 0, 0); 
	 
	public Rectangle previous_selector = new Rectangle(0, 0, 0, 0); 
	 
	private SPanelManager manager = null; 

	private BufferedImage desktopImage = null; 

	private boolean bl_desk_top_image = false; 

	public SPanelCollector majors = new SPanelCollector(); 

	private boolean blControl; 

	private boolean blAlt; 

	private boolean blGrid; 

	private boolean blCut; 
	 
	private boolean blEstablishFormat; 
	 

	private String objectFactory = "TPanel"; 

	public static List<String> msg_pool = new ArrayList<String>(); 

	private boolean blPickMode; 

	private Color PickColor = Color.black; 

	private List<Point> track_path = new ArrayList<Point>(); 

	public static Odin odin = new Odin(25); 

	private boolean blRoundableFormMode; 

	private TPanel entered; 
	 
	private boolean blVkLeft; 
	 
	private boolean blVkRight; 
	 
	private boolean blVkUp; 
	 
	private boolean blVkDown; 
	 
	private String modal_result=""; 
	 
	 
	public static boolean skip_wheel = false; 
	 
	 
	 
	 
	public String getModal_result() { 
		return modal_result; 
	} 

	public void setModal_result(String modal_result) { 
		this.modal_result = modal_result; 
	} 

	public Rectangle getPrevious_selector() { 
		return previous_selector; 
	} 

	public void setPrevious_selector(Rectangle previous_selector) { 
		this.previous_selector = previous_selector; 
	} 

	public boolean isBlEstablishFormat() { 
		return blEstablishFormat; 
	} 

	public void setBlEstablishFormat(boolean blEstablishFormat) { 
		this.blEstablishFormat = blEstablishFormat; 
	} 

	public boolean isBlVkUp() { 
		return blVkUp; 
	} 

	public void setBlVkUp(boolean blVkUp) { 
		this.blVkUp = blVkUp; 
	} 

	public boolean isBlVkDown() { 
		return blVkDown; 
	} 

	public void setBlVkDown(boolean blVkDown) { 
		this.blVkDown = blVkDown; 
	} 

	public boolean isBlVkLeft() { 
		return blVkLeft; 
	} 

	public void setBlVkLeft(boolean blVkLeft) { 
		this.blVkLeft = blVkLeft; 
	} 

	public boolean isBlVkRight() { 
		return blVkRight; 
	} 

	public void setBlVkRight(boolean blVkRight) { 
		this.blVkRight = blVkRight; 
	} 


	public static String DESKTOP_TITLE = "Composite %s Items %04d"; 
			 
	 
	public void updateTitle(String composite_source) 
	{ 
		TFrame.HDC.setTitle(String.format(TFrame.DESKTOP_TITLE,composite_source,getManager().getSize())); 
	} 

	public TPanel getEntered() { 
		return entered; 
	} 

	public void setEntered(TPanel entered) { 
		this.entered = entered; 
	} 

	public boolean isBlRoundableFormMode() { 
		return blRoundableFormMode; 
	} 

	public void setBlRoundableFormMode(boolean blRoundableFormMode) { 
		this.blRoundableFormMode = blRoundableFormMode; 
	} 

	public boolean isBlCut() { 
		return blCut; 
	} 

	public void setBlCut(boolean blCut) { 
		this.blCut = blCut; 
	} 

	public List<Point> getTrack_path() { 
		return track_path; 
	} 

	public void setTrack_path(List<Point> track_path) { 
		this.track_path = track_path; 
	} 

	public Color getPickColor() { 
		return PickColor; 
	} 

	public void setPickColor(Color pickColor) { 
		PickColor = pickColor; 
	} 

	public boolean isBlPickMode() { 
		return blPickMode; 
	} 

	public void setBlPickMode(boolean blPickMode) { 
		this.blPickMode = blPickMode; 
	} 

	public String getObjectFactory() { 
		return objectFactory; 
	} 

	public void setObjectFactory(String objectFactory) { 
		this.objectFactory = objectFactory; 
	} 

	public boolean isBlGrid() { 
		return blGrid; 
	} 

	public void setBlGrid(boolean blGrid) { 
		this.blGrid = blGrid; 
		 
		 
	} 

	public boolean isBlAlt() { 
		return blAlt; 
	} 

	public void setBlAlt(boolean blAlt) { 
		this.blAlt = blAlt; 
	} 

	public boolean isBlControl() { 
		return blControl; 
	} 

	public void setBlControl(boolean blControl) { 
		this.blControl = blControl; 
	} 

	public boolean isBl_desk_top_image() { 
		return bl_desk_top_image; 
	} 

	public void setBl_desk_top_image(boolean bl_desk_top_image) { 
		this.bl_desk_top_image = bl_desk_top_image; 
	} 

	public BufferedImage getDesktopImage() { 
		return desktopImage; 
	} 

	public void setDesktopImage(BufferedImage desktopImage) { 
		this.desktopImage = desktopImage; 
	} 

	public TFrame(Rectangle rect) { 
		this(); 

		setBounds(rect); 
	} 

	public SPanelManager getManager() { 
		return manager; 
	} 

	public void setManager(SPanelManager manager) { 
		this.manager = manager; 
	} 

	public Rectangle getSelector() { 
		return selector; 
	} 

	public void setSelector(Rectangle selector) { 
		this.selector = selector; 
	} 

	public Point getStartPos() { 
		return startPos; 
	} 

	public void setStartPos(Point startPos) { 
		this.startPos = startPos; 
	} 

 
	public static boolean setLookAndFill(String look) 
	{ 
	try { 

			 
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) { 

				 
				if (look.equals(info.getName())) { 

					UIManager.setLookAndFeel(info.getClassName()); 

					break; 
				} 
			} 

		} catch (ClassNotFoundException e) { 

			return false; 

		} catch (InstantiationException e) { 

			return false; 

		} catch (IllegalAccessException e) { 

			return false; 

		} catch (UnsupportedLookAndFeelException e) { 

			return false; 
		 
		} 
	 
		//gl.smn("---> Current desktop setting : " + UIManager.getLookAndFeel().getName()); 
		 
		return UIManager.getLookAndFeel().getName().equalsIgnoreCase(look); 
		 
	} 
	 
	public static TFrame createUI() { 

		FRAME = createDeskTop(); 

		FRAME.setLayout(null); 

		return FRAME; 

	} 

	public List<TPanel> getComps() { 
		List<Component> list = Arrays.asList(this.getComponents()); 

		List<TPanel> result_list = new ArrayList<TPanel>(); 

		for (int i = 0; i < list.size(); i++) { 
			result_list.add(((TPanel) list.get(i))); 
		} 

		return result_list; 
	} 

	public static void mainUP(String[] args) { 

		SwingUtilities.invokeLater(new Runnable() { 

			@Override 
			public void run() { 

			} 

		}); 

	} 

	@Override 
	public void keyTyped(KeyEvent e) { 

	} 

	@Override 
	public void keyPressed(KeyEvent e) { 

		Object v = new Object() { 
		}; 

		int key = e.getKeyCode(); 

		if (e.isControlDown()) 
			this.setBlControl(true); 

		if (e.isAltDown()) 
			this.setBlAlt(true); 
		 
		if (key == KeyEvent.VK_LEFT) 
			this.setBlVkLeft(true); 
		 
		if (key == KeyEvent.VK_RIGHT) 
			this.setBlVkRight(true); 
		 
		if (key == KeyEvent.VK_UP) 
			this.setBlVkUp(true); 
		 
		if (key == KeyEvent.VK_DOWN) 
			this.setBlVkDown(true); 
		 
				 

		if (e.isControlDown() && !e.isAltDown() && key == KeyEvent.VK_S) { 
			gl.smn("Save suite that was saved altough one time. Home : " 
					+ this.getManager().getHome()); 

			if (this.getManager().isSuiteWasSavedBefore()) { 
				this.getManager().fileSaveAs(this.getManager().getHome()); 
			} else { 
				Fu.to_file_dlg_save_as(this.getManager()); 
			} 

		} else 

		// Clear selectors. 

		if (e.isControlDown() && e.isShiftDown()) { 
			this.getManager().getSelectors().clear(); 

			gl.tracex(new Object() { 
				 
			}, String.format("Ctrl+Shift activation...%s", "Clear selectors")); 
 
			this.repaint(); 
		} 

		else if (e.isControlDown() && e.isShiftDown() && key == KeyEvent.VK_Q) { 

			gl.tracex(new Object() { 
			}, String.format("Ctrl+Shift+Q activation...%s", "Iconified")); 
 
			TFrame.HDC.setState(Frame.ICONIFIED); 

		} 

		else if (e.isControlDown() && e.isAltDown() && key == KeyEvent.VK_O) { 
			gl.tracex(new Object() { 
			}, String.format("Open suite activation...%s", "ShortKeys")); 

			this.getManager().getFilesSuite(); 
		} 
		 
		else if (e.isControlDown() && e.isAltDown() && key == KeyEvent.VK_L )  { 
			gl.tracex(new Object() { 
			}, String.format("Ctrl+Alt+L...%s...%s", "Align operation","Left")); 
			 
		} 

		else if (e.isControlDown() && !e.isAltDown() && key == KeyEvent.VK_O) { 

			gl.tracex(new Object() { 
			}, String.format("Open suite activation...%s...%s", "ShortKeys", 
					"WithoutDialog")); 

			if (this.getManager().isSuiteWasSavedBefore()) { 

				gl.tracex(new Object() { 
				}, String.format("ShortKeys mode open suite...%s...%s", 
						"Ready", "Ok")); 

				this.getManager().openFileBySK( 
						this.getManager().getHome()); 

			} else { 

				gl.tracex(new Object() { 
				}, String.format("ShortKeys mode open suite...%s...%s", 
						"Ready", "Error")); 

			} 
		} 

		else if (this.isBlControl() && this.isBlAlt() 
				&& key == KeyEvent.VK_S) { 
			gl.tracex(new Object() { 
			}, String.format("Save suite activation...%s...%s", "ShortKeys", 
					"within dialog")); 
	 
			Fu.to_file_dlg_save_as(this.getManager()); 

		} else if (this.isBlControl() && key == KeyEvent.VK_F) { 

			 
			 
			this.setBlEstablishFormat(!this.isBlEstablishFormat()); 
			 
			gl.tracex(new Object() {}, String.format("Change method of establish order format to...%s", this.isBlEstablishFormat())); 

			 
		} else if (this.isBlControl() && key == KeyEvent.VK_R) { 

			gl.tracex(new Object() { 
			}, String.format("Establish order...%s...%s", "ShortKeys", 
					"Five Column")); 

			this.getManager().establishOrder( 
					this.getManager().getEstablish_order(), false); 

		} else if (this.isBlControl() && key == KeyEvent.VK_E) { 

			gl.tracex(new Object() { 
			}, String.format("Establish order...%s...%s", "ShortKeys", 
					"Five Column with Grow")); 

			this.getManager().establishOrder( 
					this.getManager().getEstablish_order(), true); 

		} else if (this.isBlControl() && key == KeyEvent.VK_G) { 

			this.setBlGrid(!this.isBlGrid()); 

			gl.tracex(new Object() { 
			}, String.format("Setup grid to...%s", this.isBlGrid())); 

			this.getManager().getSelected().getData().forEach( 

			a -> { 
				a.setBlGrid(this.isBlGrid()); 
			}); 

		} else if (this.isBlControl() && key == KeyEvent.VK_C) { 

			this.setBlCut(!this.isBlCut()); 

			gl.tracex(new Object() { 
			}, String.format("Setup CUT mode to...%s", this.isBlCut())); 

			this.getManager().getSelected().getData().forEach( 

			a -> { 
				a.setBlCut(this.isBlCut()); 
			}); 

		} 

		else if (e.isControlDown() && e.isAltDown() && key == KeyEvent.VK_T) { 

			gl.tracex(new Object() { 
			}, String.format("Enable toolTip for all selected objects...%s", 
					"ShortKeys")); 

			this.getManager().getSelected().getData().forEach(a -> { 
				a.setBl_tool_tip(!a.isBl_tool_tip()); 
				 
				gl.tracex(new Object() { 
				}, String.format("Enable toolTip for object id...%04d...to...%s", 
						a.getId(),a.isBl_tool_tip())); 
			}); 
		} 

		else if (e.isControlDown() && e.isAltDown() && key == KeyEvent.VK_I) { 

			if (this.getManager().openGraphicsFilesSuite()) 
				gl.tracex(new Object() { 
				}, String.format("Open images...%s...%s", "ShortKeys", "Ok")); 
			else 
				gl.tracex(new Object() { 
				}, String.format("Open images...%s...%s", "ShortKeys", "Error")); 

		} 

		else if (e.isControlDown() && key == KeyEvent.VK_A) { 

			if (this.getManager().selectAll()) 
				gl.tracex(new Object() { 
				}, String.format("Select all objects...%s...%s", "ShortKeys", 
						"Ok")); 
			else 
				gl.tracex(new Object() { 
				}, String.format("Select all objects...%s...%s", "ShortKeys", 
						"Error")); 

		} 

		else if (e.isControlDown() && key == KeyEvent.VK_D) { 

			if (this.getManager().deleteSelected()) 
				gl.tracex(new Object() { 
				}, String.format("Delete selected objects...%s...%s", 
						"ShortKeys", "Ok")); 
			else 
				gl.tracex(new Object() { 
				}, String.format("Delete selected objects...%s...%s", 
						"ShortKeys", "Error")); 

		} 

		// Exit from an application. 

		else if (e.isControlDown() && e.isAltDown() && key == KeyEvent.VK_X) { 

			gl.tracex(new Object() { 
			}, String.format("Application close...%s...%s", "ShortKeys", "Ok")); 

			System.exit(gl.E_EMPTY); 

		} 

		// Speed moving activation. 

		else if (e.isAltDown() && key == KeyEvent.VK_PERIOD) { 
			gl.tracex(v, String.format("Speed activation...%s...%03d", "[+]", 
					TPanel.speed++)); 
		} else if (e.isAltDown() && key == KeyEvent.VK_COMMA) { 
			gl.tracex(v, String.format("Speed activation...%s...%03d", "[-]", 
					TPanel.speed--)); 
		} else if (e.isControlDown() && !e.isAltDown() && key == KeyEvent.VK_I) { 
			this.setBl_desk_top_image(!this.isBl_desk_top_image()); 

			gl.tracex( 
					v, 
					String.format("Desktop image activation is ...%s", 
							this.isBl_desk_top_image())); 

		} else if (e.isControlDown() && e.isAltDown() && key == KeyEvent.VK_P) { 

			this.setBlPickMode(!this.isBlPickMode()); 

			gl.tracex(new Object() {},String.format("%s...Disable|Enable pick mode...%s",gl.S_OK,this.isBlPickMode())); 

		} else if (e.isControlDown() && key == KeyEvent.VK_F1) { 

			this.setBlRoundableFormMode(!this.isBlRoundableFormMode()); 

			gl.tracex( 
					new Object() { 
					}, 
					String.format("Disable|Enable roundable mode...%s", 
							this.isBlRoundableFormMode())); 
			 
		 

		} else if(!this.isBlAlt() && isBlVkLeft()) 
		{ 
			 
			int notches = 10; 

			if (this.isBlControl()) 
				notches *= 10; 

			this.getManager().moveObjectsByWheel(notches*gl.E_ERROR,gl.E_EMPTY); 
			 
		}	else if(!this.isBlAlt() && isBlVkRight()) 
		{ 
			 
			int notches = 10; 

			if (this.isBlControl()) 
				notches *= 10; 

			this.getManager().moveObjectsByWheel(notches,gl.E_EMPTY); 
			 
		} 
		else if(!this.isBlAlt() && isBlVkUp()) 
		{ 
			 
			int notches = 10; 

			if (this.isBlControl()) 
				notches *= 10; 

			this.getManager().moveObjectsByWheel(gl.E_EMPTY,notches*gl.E_ERROR); 
			 
		} 
		else if(!this.isBlAlt() && isBlVkDown()) 
		{ 
			 
			int notches = 10; 

			if (this.isBlControl()) 
				notches *= 10; 

			this.getManager().moveObjectsByWheel(gl.E_EMPTY,notches); 
			 
		} 
		 
		 
	} // keyPressed() 

	@Override 
	public void keyReleased(KeyEvent e) { 

		if (this.isBlControl()) 
			this.setBlControl(false); 

		if (this.isBlAlt()) 
			this.setBlAlt(false); 
		 
		if (this.isBlVkLeft()) 
			this.setBlVkLeft(false); 
		 
		if (this.isBlVkRight()) 
			this.setBlVkRight(false); 
		 
		if (this.isBlVkUp()) 
			this.setBlVkUp(false); 
		 
		if (this.isBlVkDown()) 
			this.setBlVkDown(false); 
		 
		 
	} 

	 
	public static Rectangle SCREEN_BOUNDS = new Rectangle(0,0,100,100); 
	 

	public static JFrame HDC = null; 

	public TFrame() { 

		addMouseListener(this); 

		addMouseMotionListener(this); 

		addMouseWheelListener(this); 

		this.addComponentListener(this); 

		this.addKeyListener(this); 

		this.setLayout(null); 

		this.setBackground(Color.gray); 

		 
	} 

	public static TFrame createDeskTop() { 

		TFrame bf = new TFrame(); 

		JFrame frame = new JFrame("Test frame."); 

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		frame.add(bf); 

		frame.setBounds(TFrame.SCREEN_BOUNDS); 

		frame.setLocationRelativeTo(null); 
		 
		frame.setUndecorated(false); 

		frame.setIgnoreRepaint(false); 
		 
		 

		frame.addKeyListener(new KeyAdapter() { 
			public void keyPressed(KeyEvent e) { 
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { 
					System.exit(0); 
				} 

			} 
		}); 

		frame.pack(); 

		frame.setVisible(false); 

		TFrame.location = frame.getBounds(); 

		TFrame.HDC = frame; 

		return bf; 
	} 

	public static TFrame FRAME = null; 

	/** 
	 * @return the fRAME 
	 */ 
	public static TFrame getFrame() { 
		return FRAME; 
	} 

	public static void setFrame(TFrame Frame) { 
		FRAME = Frame; 
	} 

	 
	@Override 
	public void mouseDragged(MouseEvent e) { 

		if (startPos != null) { 

			int dx = e.getX() - startPos.x; 

			int dy = e.getY() - startPos.y; 

			Rectangle rect = new Rectangle(startPos.x, startPos.y, dx, dy); 

			selector.setBounds(rect); 

			this.repaint(); 

		} 

	} 

	 
	@Override 
	public void mouseMoved(MouseEvent e) { 
		// Auto-generated method stub 

	} 

	@Override 
	public void mouseClicked(MouseEvent e) { 

		if (SwingUtilities.isRightMouseButton(e)) { 
			this.requestFocus(); 

			TManagerPopupFactory.createPopup(this.getManager(),e); 

		} else if (SwingUtilities.isLeftMouseButton(e)) { 

			if (this.getManager().getSelected().size() != gl.E_EMPTY) { 
				this.getManager().resetSelected(); 

				this.getManager().getSelected().clear(); 

			} 

			this.repaint(); 
		} 

	} 

	 
	@Override 
	public void mousePressed(MouseEvent e) { 

		startPos = e.getPoint(); 

		this.setSelector(new Rectangle(0, 0, 0, 0)); 
	} 

	@Override 
	public void mouseReleased(MouseEvent e) { 

		if (SwingUtilities.isRightMouseButton(e)) 
			return; 

		Object v = new Object() { 
		}; 

		if (this.getManager() == null) { 
			gl.tracex(v, String.format("%s...%s", "Check manager", "Error")); 

			return; 
		} 

		// Selector activity occur. 

		if (!ap.shape.Ru.is_valid_size(this.getSelector().getBounds())) { 
			gl.tracex(v, String.format("NOP ")); 

			this.getManager().resetSelected(); 

			this.getManager().getSelected().clear(); 

			this.repaint(); 

			return; 
		} 

		Vector<TPanel> selected = this.getManager().getCollector() 
				.getBySelector(this.getSelector().getBounds()); 

		// Try to void duplicate items in the set of selected ones. 

		for (TPanel s : selected) { 
			if (this.getManager().getSelected().getData().contains(s)) { 
				gl.tracex( 
						v, 
						String.format("%s...%d", "Already selected id:", 
								s.getId())); 

				this.getManager().getSelected().getData().remove(s); 

			} 
		} 

		if (selected.size() != gl.E_EMPTY) { 
			this.getManager().getSelected().addAll(selected); 

			selected.forEach(a -> { 

				a.setSelected(true); 

				a.repaint(); 

			}); 

		} // selected items exists 
		else { 

			if (!this.isBlControl()) { 
				// Add new object to the frame 

				if (!this.getManager().createObject(this.getObjectFactory(), 
						this.getSelector())) { 
					gl.tracex(v, String.format("%s...%s", 
							"Inject object to the frame", "Error")); 
				} else { 

					// Drag selected items. 

					gl.tracex(v, String.format("%s...%s", 
							"Inject object to the frame", "Ok")); 

				} 

			} else { 
				TPanel se = new TPanel(this.getSelector()); 

				this.getManager().getSelectors().add(se); 

				gl.tracex(v, String.format("CTRL activated...%s...%d", 
						"add selectors", this.getManager().getSelectors() 
								.size())); 

				this.getManager() 
						.getSelectors() 
						.getData() 
						.forEach( 

								a -> { 
									gl.tracex(v, String.format("%s", 
											SBounds.toString(a.getBounds()))); 
								} 

						); 

			} 

		} // new Entity 

		this.setPrevious_selector(this.getSelector().getBounds()); 
		 
		this.setSelector(null); 

		this.repaint(); 

	} 

	@Override 
	public void mouseEntered(MouseEvent e) { 

		requestFocus(); 

	} 

	@Override 
	public void paintComponent(Graphics g) { 

		super.paintComponent(g); 

		Graphics2D g2 = (Graphics2D) g; 

		g2.setPaint(Color.white); 

		 
		if (selector != null) { 

			Stroke prev_stroke = g2.getStroke(); 
			 
			g2.setStroke(gl.dashed_stroke_thin); 

			Ru.drawRect2D(g2,selector); 
			 
			g2.setStroke(prev_stroke); 
			 
		} 

		if (this.getManager().getSelectors() != null 
				&& this.getManager().getSelectors().size() != gl.E_EMPTY 
				&& this.isBlControl()) { 

			Stroke prev_stroke = g2.getStroke(); 

			g2.setStroke(gl.dashed_stroke_thin); 
			 

			this.getManager().getSelectors().getData().forEach(a -> { 
				Ru.drawRect2D(g2, a.getBounds()); 
				 
				g2.setStroke(prev_stroke); 
			}); 
		} 

		// If service is on. 
		if (this.isBl_desk_top_image()) { 
			if (this.getDesktopImage() != null) { 

				if (getManager().getSelectors().size() != gl.E_EMPTY) { 
					getManager() 
							.getSelectors() 
							.getData() 
							.forEach( 

									a -> { 
										GU.drawImage(g2, 
												this.getDesktopImage(), 
												a.getBounds()); 

										BufferedImage inverted = ImageUtil 
												.flip_bi_v(this 
														.getDesktopImage()); 

										BufferedImage shadow = ImageUtil 
												.get_cust_gradient_bi( 
														inverted, 
														CU 
																.getAlphaColor( 
																		Color.gray, 
																		128), 
														CU 
																.getAlphaColor( 
																		Color.gray, 
																		255)); 

										Rectangle targetRect = Ru.Shift( 
												a.getBounds(), 0, 
												a.getBounds().height); 

										ImageUtil.paint_bi(g2, targetRect, 
												shadow); 

										Rectangle textRect = new Rectangle(a 
												.getBounds().x, 
												a.getBounds().y - 16, a 
														.getBounds().width, 16); 

										GU.drawGradientEx(g2, 
												textRect, textRect, Color.blue, 
												Color.white); 

										GU.drawAlphaString(g2, 
												Fu.get_pure_file_name(this 
														.getEntered() 
														.getImg_desc()), a 
														.getBounds().x, a 
														.getBounds().y - 4, 
												Color.white, 
												new Font("Tahoma", Font.ITALIC 
														| Font.BOLD, 12), 1.0f); 

									}); 
				} 
			} 
		} 

	} 

	public SPanelCollector getMajors() { 
		return majors; 
	} 

	public void setMajors(SPanelCollector majors) { 
		this.majors = majors; 
	} 

	@Override 
	public void mouseExited(MouseEvent e) { 
		// Auto-generated method stub 

	} 

	/* 
	 * (non-Javadoc) 
	 * 
	 * @see java.awt.event.ComponentListener#componentResized(java.awt.event. 
	 * ComponentEvent) 
	 */ 
	@Override 
	public void componentResized(ComponentEvent e) { 

		 
		this.setBlControl(false); 
	} 

	/* 
	 * (non-Javadoc) 
	 * 
	 * @see 
	 * java.awt.event.ComponentListener#componentMoved(java.awt.event.ComponentEvent 
	 * ) 
	 */ 
	@Override 
	public void componentMoved(ComponentEvent e) { 
		// Auto-generated method stub 

	} 

	/* 
	 * (non-Javadoc) 
	 * 
	 * @see 
	 * java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent 
	 * ) 
	 */ 
	@Override 
	public void componentShown(ComponentEvent e) { 
		// Auto-generated method stub 

	} 

	/* 
	 * (non-Javadoc) 
	 * 
	 * @see java.awt.event.ComponentListener#componentHidden(java.awt.event. 
	 * ComponentEvent) 
	 */ 
	@Override 
	public void componentHidden(ComponentEvent e) { 
		// Auto-generated method stub 

	} 

	/** 
	 * @param selected 
	 */ 
	public void removeAll(Vector<TPanel> selected) { 

		selected.forEach(a -> { 
			this.remove(a); 
		}); 

	} 
	 
	public void removeAll(List<TPanel> selected) { 

		selected.forEach(a -> { 
			this.remove(a); 
		}); 

	} 
	 
	public void removeAllTicks(List<TTick> selected) { 

		if(selected == null) 
			return; 
		 
		selected.forEach(a -> { 
			this.remove(a); 
		}); 

	} 

	public boolean isa(TPanel panel) { 
		Component comps[] = this.getComponents(); 

		List<Component> list = Arrays.asList(comps); 

		return list.contains(panel); 

	} 

	/* 
	 * (non-Javadoc) 
	 * 
	 * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event. 
	 * MouseWheelEvent) 
	 */ 

	@Override 
	public void mouseWheelMoved(MouseWheelEvent e) { 

		int notches = e.getWheelRotation() * 10; 

		if (this.isBlControl()) 
			notches *= 10; 

		 
		if(isBlVkLeft()) 
			this.getManager().moveObjectsByWheel(notches*gl.E_ERROR,gl.E_EMPTY); 
		else if(isBlVkRight()) 
			this.getManager().moveObjectsByWheel(notches,gl.E_EMPTY); 
		else 
			this.getManager().moveObjectsByWheel(gl.E_EMPTY, notches); 

	} 
} 

/* 
* 
* 1. Ctrl + S - Save suite that was saved one time. 
* 2. Ctrl + Shift - Clear of selectors. 
* 3. Ctrl + Shift + Q - Iconified. 
* 4. Ctrl + Alt + O - Open of a suite with dialog. 
* 5. Ctrl + O - Re - Open of an suite without dialog. 
* 6. Ctrl + Alt + S - Save of a suite with dialog. 
* 7. Ctrl + F - Save of a suite with dialog. 
* 8. Ctrl + R - Set of an order and minimize of objects. 
* 9. Ctrl + E - Set of an order and maximize of objects. 
* 10.Ctrl + G - Set grid for an objects. 
* 11.Ctrl + Shift + T - Set enable|disable ToolTip for all selected objects. 
* 12.Ctrl + Alt + I - Open graphics images. 
* 13.Ctrl + A - Select of all objects. 
* 14.Ctrl + D - Delete selected objects. 
* 15.Ctrl + Alt + X - Exit from application. 
* 16.Ctrl + Alt + I - Set desktop image. 
* 17.Alt + '+' - Speed Up. 
* 18.Alt + '-' - Speed Down. 
* 19.Ctrl + Alt + P - Enable pick mode and set pick_color 
* 20.Ctrl + Shift + F - Setup of the form of control(rect||elipse) 
* 
*/ 

// Revision : 10.09.2018 12:49:15 
