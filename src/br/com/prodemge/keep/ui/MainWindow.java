package br.com.prodemge.keep.ui;

import org.eclipse.jface.layout.RowLayoutFactory;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import br.com.prodemge.keep.composite.NoteCreateComposite;
import br.com.prodemge.keep.composite.NoteViewComposite;

public class MainWindow {
	
	private Display display;
	private Shell shell;
	
	public MainWindow(Display display) {
		this.display = display;
	}
	
	public Shell createShell() {

		shell = new Shell(display);
		shell.setSize(500, 330);
		RowLayoutFactory.swtDefaults().justify(true).applyTo(shell);
		
		new NoteViewComposite(shell);
		
		return shell;
	}


}
