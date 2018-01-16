package br.com.prodemge.keep.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.AbstractSelectionDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class SelectDialog<T> extends AbstractSelectionDialog<T> {
	
	private List<T> items;
	private ListViewer viewer;

	public SelectDialog(Shell parent) {
		super(parent);
	}
	
	public SelectDialog(Shell parent, List<T> items) {
		super(parent);
		this.items = items;
	}
	
	public void setItems(List<T> items) {
		this.items = items;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		Label lblTitle = new Label(container, SWT.NONE);
		lblTitle.setText("Lista de Tags");
		
		viewer = new ListViewer(container, SWT.MULTI | SWT.BORDER);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setInput(items);
		
		GridDataFactory.fillDefaults().hint(400, SWT.DEFAULT).applyTo(viewer.getList());
		
		return container;
	}
	
	@Override
	protected void okPressed() {
		
		List<T> ret = new ArrayList<>();
		
		int[] indexes = viewer.getList().getSelectionIndices();
		
		for (int i =0; i < indexes.length; i++)
			ret.add(items.get(indexes[i]));
		
		this.setResult(ret);
		
		super.okPressed();
	}

}
