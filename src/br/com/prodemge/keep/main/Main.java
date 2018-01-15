package br.com.prodemge.keep.main;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import br.com.prodemge.keep.composite.TagCreateComposite;
import br.com.prodemge.keep.db.JpaUtil;
import br.com.prodemge.keep.ui.TagWindow;

public class Main {

	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new TagWindow(display).createShell();

		shell.open();

		// Event Loop
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();

		display.dispose();
		JpaUtil.close();
	}
}
