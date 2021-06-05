package ap.orion.components;

import java.awt.Component;
import java.awt.HeadlessException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

public class FileChooser extends JFileChooser{

	
	    protected JDialog createDialog(Component parent) throws HeadlessException {
	        JDialog dialog = super.createDialog(parent);
	        dialog.setAlwaysOnTop(true);
	        return dialog;
	    }

}
