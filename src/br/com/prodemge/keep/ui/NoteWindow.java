package br.com.prodemge.keep.ui;

import org.eclipse.jface.layout.RowLayoutFactory;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import br.com.prodemge.keep.composite.NoteCreateComposite;
import br.com.prodemge.keep.model.Note;

public class NoteWindow {
	
	private Note note = null;
	
	private Display display;
	private Shell shell;
	
	public NoteWindow(Display ds) {
		this.display = ds;
	}
	
	public NoteWindow(Display ds, Note note) {
		this.display = ds;
		this.note = note;
	}
	
	public Shell createShell() {

		shell = new Shell(display);
		shell.setSize(500, 330);
		RowLayoutFactory.swtDefaults().justify(true).applyTo(shell);
		
		if (note == null)
			new NoteCreateComposite(shell);
		else
			new NoteCreateComposite(shell, note);
		
		return shell;
	}

}
