package br.com.prodemge.keep.composite;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.prodemge.keep.db.DAO;
import br.com.prodemge.keep.model.Tag;

public class TagCreateComposite extends Composite {
	
	private Text txtName;
	
	public TagCreateComposite(Composite parent) {
		super(parent, SWT.NONE);
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
		
		Button btnSalvar = new Button(this, SWT.PUSH);
		btnSalvar.setText("Salvar");
		GridDataFactory.swtDefaults().span(1, 1).hint(100, SWT.DEFAULT).align(SWT.END, SWT.CENTER).applyTo(btnSalvar);
		
		btnSalvar.addSelectionListener(eventSave());
		
		Button btnFechar = new Button(this, SWT.PUSH);
		btnFechar.setText("Fechar");
		GridDataFactory.swtDefaults().span(1, 1).hint(100, SWT.DEFAULT).applyTo(btnFechar);
		
		btnFechar.addSelectionListener(eventClose());
		
	}
	
	private SelectionAdapter eventClose() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.display.getActiveShell().dispose();
			}
		};
	}
	
	private SelectionAdapter eventSave() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String name = txtName.getText();
				
				if (name.length() < 3) {
					MessageDialog.openError(e.display.getActiveShell(), "Erro de validação", "O nome deve conter no mínimo 3 caracteres!");
					return;
				}
				
				try {
					DAO.save(new Tag(name));
					MessageDialog.openConfirm(e.display.getActiveShell(), "Sucesso", "A tag foi cadastrada com sucesso!");
					
				} catch (Exception ex) {
					MessageDialog.openError(getShell(), "Erro", "A tag já existe!");
				} finally {
					txtName.setText("");
					txtName.setFocus();
				}
				
			}
		};
	}

}
