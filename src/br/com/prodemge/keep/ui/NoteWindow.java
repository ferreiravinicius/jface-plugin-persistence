package br.com.prodemge.keep.ui;

import org.eclipse.jface.layout.RowLayoutFactory;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import br.com.prodemge.keep.composite.NoteCreateComposite;

public class NoteWindow {
	
	private Display display;
	private Shell shell;
	
	public NoteWindow(Display ds) {
		this.display = ds;
	}
	
	public Shell createShell() {

		shell = new Shell(display);
		shell.setSize(500, 330);
		RowLayoutFactory.swtDefaults().justify(true).applyTo(shell);
		
		new NoteCreateComposite(shell);
		
		return shell;
	}

}
