package br.com.prodemge.keep.composite;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TagCreateComposite extends Composite {
	
	private Text txtName;
	private Button btnSalvar;
	
	public TagCreateComposite(Composite parent) {
		super(parent, SWT.NONE);
		this.shell = (Shell)parent;
		this.getShell().setText("Tag - Cadastro");
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(this);
		createComponents();
	}
	
	public void createComponents() {
		
		Label lblNome = new Label(this, SWT.NONE);
		lblNome.setText("Nome");
		GridDataFactory.fillDefaults().span(2, 1).applyTo(lblNome);
		
		txtName = new Text(this, SWT.BORDER);
		GridDataFactory.fillDefaults().span(2, 1).hint(250, SWT.DEFAULT).applyTo(txtName);
		
		Label separator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridDataFactory.fillDefaults().span(2,1).align(SWT.FILL, SWT.CENTER).applyTo(separator);
		
		btnSalvar = new Button(this, SWT.PUSH);
		btnSalvar.setText("Salvar");
		GridDataFactory.swtDefaults().span(2, 1).hint(100, SWT.DEFAULT).align(SWT.END, SWT.CENTER).applyTo(btnSalvar);
		
		btnSalvar.addSelectionListener(eventSave());
		
	}
	
	public SelectionAdapter eventSave() {
		
		return new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				System.out.println("AAAA");
			}
		};
		
	}

}
