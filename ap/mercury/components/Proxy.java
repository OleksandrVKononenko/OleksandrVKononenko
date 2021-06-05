package ap.mercury.components;

import java.awt.LayoutManager;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Proxy extends JPanel {

	public Proxy() {
		
	}

	public Proxy(LayoutManager layout) {
		super(layout);
		
	}

	public Proxy(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		
	}

	public Proxy(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		
	}


}
