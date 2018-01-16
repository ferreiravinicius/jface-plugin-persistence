package br.com.prodemge.keep.composite;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import br.com.prodemge.keep.db.DAO;
import br.com.prodemge.keep.model.Note;
import br.com.prodemge.keep.ui.NoteWindow;
import br.com.prodemge.keep.ui.TagWindow;

public class NoteViewComposite extends Composite {

	private TableViewer viewer;

	public NoteViewComposite(Composite parent) {
		super(parent, SWT.NONE);
		this.getShell().setText("Notas");
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(this);
		createMenu();
		createTableViewer();
		createComponents();
	}

	private void createComponents() {

	}

	private void createTableViewer() {

		viewer = new TableViewer(this, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns();

		Table tbl = viewer.getTable();
		tbl.setHeaderVisible(true);
		tbl.setLinesVisible(true);

		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setInput(DAO.getAll(Note.class));

		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).span(2, 1).hint(400, 100).grab(true, true)
				.applyTo(viewer.getControl());

		viewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {

				IStructuredSelection selected = (IStructuredSelection) event.getSelection();

				if (selected == null || selected.isEmpty())
					return;

				new NoteWindow(getDisplay(), (Note) selected.getFirstElement()).createShell().open();
			}
		});

	}

	// tbl
	private TableViewerColumn createTableColumn(String title, int bound) {

		TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);

		return viewerColumn;
	}

	// tbl
	private void createColumns() {

		String[] titles = new String[] { "Título", "Descrição" };
		int[] bounds = { 110, 300 };

		TableViewerColumn col = createTableColumn(titles[0], bounds[0]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Note note = (Note) element;
				return note.getTitle();
			}
		});

		col = createTableColumn(titles[1], bounds[1]);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Note note = (Note) element;
				return note.getDescription().substring(0, 30).concat("...");
			}
		});
	}

	private void createMenu() {

		Menu menu = new Menu(this.getShell(), SWT.BAR);
		getShell().setMenuBar(menu);

		// ITEM NOTAS
		MenuItem itemNote = new MenuItem(menu, SWT.CASCADE);
		itemNote.setText("&Notas");

		Menu submenuNote = new Menu(getShell(), SWT.DROP_DOWN);
		itemNote.setMenu(submenuNote);

		MenuItem itemNewNote = new MenuItem(submenuNote, SWT.PUSH);
		itemNewNote.setText("Cadastro");

		itemNewNote.addListener(SWT.Selection, e -> new NoteWindow(getDisplay()).createShell().open());

		// ITEM TAGS
		MenuItem itemTag = new MenuItem(menu, SWT.CASCADE);
		itemTag.setText("&Tag");

		Menu submenuTag = new Menu(getShell(), SWT.DROP_DOWN);
		itemTag.setMenu(submenuTag);

		MenuItem itemNewTag = new MenuItem(submenuTag, SWT.PUSH);
		itemNewTag.setText("Cadastro");

		itemNewTag.addListener(SWT.Selection, e -> new TagWindow(getDisplay()).createShell().open());

	}
}
