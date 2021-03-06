 
 
 
 
 
 
 
 
 
 
 
 
package ap.sc; 


import java.awt.*; 
import java.awt.event.*; 

import javax.swing.*; 
import javax.swing.border.*; 

import ap.swing.PanelXml; 

//public class ScrollablePicture extends JLabel implements Scrollable { 

//public class ScrollablePicture extends JLabel implements Scrollable { 

public class ScrollablePicture extends PanelXml implements Scrollable { 

private int maxUnitIncrement = 1; 

public ScrollablePicture(ImageIcon i, int m) { 
//super(i); 
	super(); 
	 
maxUnitIncrement = m; 
} 

public Dimension getPreferredScrollableViewportSize() { 
return getPreferredSize(); 
} 

public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) { 

int currentPosition = 0; 
if (orientation == SwingConstants.HORIZONTAL) 
currentPosition = visibleRect.x; 
else 
currentPosition = visibleRect.y; 

if (direction < 0) { 
int newPosition = currentPosition - (currentPosition / maxUnitIncrement) * maxUnitIncrement; 
return (newPosition == 0) ? maxUnitIncrement : newPosition; 
} else { 
return ((currentPosition / maxUnitIncrement) + 1) * maxUnitIncrement - currentPosition; 
} 
} 

public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) { 
if (orientation == SwingConstants.HORIZONTAL) 
return visibleRect.width - maxUnitIncrement; 
else 
return visibleRect.height - maxUnitIncrement; 
} 

public boolean getScrollableTracksViewportWidth() { 
return false; 
} 

public boolean getScrollableTracksViewportHeight() { 
return false; 
} 

public void setMaxUnitIncrement(int pixels) { 
maxUnitIncrement = pixels; 
} 
} 
