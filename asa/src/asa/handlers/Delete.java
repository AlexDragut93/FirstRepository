package asa.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import asa.ClassicSingleton;

public class Delete extends AbstractHandler {

	public Tree tree;
	public TreeItem itemSelected;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		ClassicSingleton singleton = ClassicSingleton.getInstance();
		
		itemSelected =singleton.getItemSelected();
		
		itemSelected.dispose();
		
		tree = singleton.getTree();
		
		tree.setRedraw(true);
		
		return null;
	}

}
