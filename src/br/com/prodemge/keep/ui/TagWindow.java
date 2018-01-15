package br.com.prodemge.keep.ui;

import org.eclipse.jface.layout.RowLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import br.com.prodemge.keep.composite.TagCreateComposite;

public class TagWindow {

	private Display display;
	private Shell shell;
	
	public TagWindow(Display ds) {
		this.display = ds;
	}
	
	public Shell createShell() {

		shell = new Shell(display);
		shell.setSize(350, 150);
		RowLayoutFactory.swtDefaults().justify(true).applyTo(shell);
		
		new TagCreateComposite(shell);
		
		return shell;
	}
}
