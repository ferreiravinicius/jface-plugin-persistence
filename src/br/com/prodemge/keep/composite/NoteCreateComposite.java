package br.com.prodemge.keep.composite;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.prodemge.keep.db.DAO;
import br.com.prodemge.keep.model.Note;
import br.com.prodemge.keep.model.Tag;
import br.com.prodemge.keep.ui.SelectDialog;

public class NoteCreateComposite extends Composite {

	private Note note;

	private Text txtTitle;
	private Text txtDesc;
	private ListViewer lstTags;

	public NoteCreateComposite(Composite parent) {
		super(parent, SWT.NONE);
		init();
		this.note = new Note();
		createComponents();
	}

	public NoteCreateComposite(Composite parent, Note note) {
		super(parent, SWT.NONE);
		init();
		this.note = note;
		createComponents();
		fillComponents();
	}

	private void init() {
		this.getShell().setText("Notas - Cadastro");
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(this);
	}

	private void fillComponents() {

		if (!note.getTitle().isEmpty() || note.getTitle() != null)
			txtTitle.setText(note.getTitle());

		if (!note.getDescription().isEmpty() || note.getDescription() != null)
			txtDesc.setText(note.getDescription());

	}

	private void createComponents() {

		Label lblTitle = new Label(this, SWT.NONE);
		lblTitle.setText("Título");
		GridDataFactory.fillDefaults().span(2, 1).applyTo(lblTitle);

		txtTitle = new Text(this, SWT.BORDER);
		GridDataFactory.fillDefaults().hint(400, SWT.DEFAULT).span(2, 1).applyTo(txtTitle);

		Label lblDesc = new Label(this, SWT.NONE);
		lblDesc.setText("Descrição");
		GridDataFactory.fillDefaults().span(2, 1).applyTo(lblDesc);

		txtDesc = new Text(this, SWT.BORDER);
		GridDataFactory.fillDefaults().span(2, 1).applyTo(txtDesc);

		Label lblTags = new Label(this, SWT.NONE);
		lblTags.setText("Tags");
		GridDataFactory.fillDefaults().span(2, 1).applyTo(lblTags);

		lstTags = new ListViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		lstTags.setContentProvider(ArrayContentProvider.getInstance());
		lstTags.setInput(note.getTags());
		lstTags.setSelection(new StructuredSelection(note.getTags()));
		GridDataFactory.fillDefaults().span(1, 2).hint(300, SWT.DEFAULT).applyTo(lstTags.getList());

		Button btnAdd = new Button(this, SWT.PUSH);
		btnAdd.setText("Adicionar");
		GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING).hint(80, SWT.DEFAULT).span(1, 1)
				.applyTo(btnAdd);

		btnAdd.addSelectionListener(eventAddTag());

		Button btnRemover = new Button(this, SWT.PUSH);
		btnRemover.setText("Remover");
		GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING).hint(80, SWT.DEFAULT).span(1, 1)
				.applyTo(btnRemover);

		btnRemover.addSelectionListener(eventRemoveTag());

		Label separator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridDataFactory.fillDefaults().span(2, 5).align(SWT.FILL, SWT.CENTER).applyTo(separator);

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
				getShell().dispose();
			}
		};
	}

	private SelectionAdapter eventSave() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				String title = txtTitle.getText();
				String desc = txtDesc.getText();

				if (title.length() < 3 || desc.length() < 3) {
					MessageDialog.openError(getShell(), "Erro de validação",
							"Os campos devem conter no mínimo 3 caracteres!");
					return;
				}

				try {

					note.setTitle(title);
					note.setDescription(desc);

					if (note.getId() == null)
						DAO.save(note);
					else
						DAO.update(note);

					MessageDialog.openConfirm(getShell(), "Sucesso", "A nota foi cadastrada com sucesso!");

				} catch (Exception ex) {
					MessageDialog.openError(getShell(), "Erro", "Erro ao cadastrar no banco de dados!");
				} finally {
					getShell().dispose();
				}

			}
		};
	}

	private SelectionAdapter eventRemoveTag() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				IStructuredSelection selection = lstTags.getStructuredSelection();

				if (selection.getFirstElement() == null) {
					MessageDialog.openError(getShell(), "Erro", "É necessário selecionar um item para remoção!");
					return;
				}

				note.getTags().remove((Tag) selection.getFirstElement());
				lstTags.refresh();

			}
		};
	}

	private SelectionAdapter eventAddTag() {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SelectDialog<Tag> dlgSelection = new SelectDialog<Tag>(getShell(), DAO.getAll(Tag.class));

				if (dlgSelection.open() == Window.OK) {

					note.getTags().addAll(dlgSelection.getResult());
					lstTags.refresh();

				}
			}
		};
	}

}
