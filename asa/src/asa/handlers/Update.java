package asa.handlers;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import asa.UpdateEmployeeDialog;
import asa.ClassicSingleton;

public class Update extends AbstractHandler {

	public String text = "C:/workspace/asa/file.text";
	public String inputText;
	public String node;
	public Tree tree;
	public TreeItem itemEmployees;
	public TreeItem itemSelected;
	public int countEmployees;

	private static final Shell Shell = null;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ClassicSingleton singleton = ClassicSingleton.getInstance();

		UpdateEmployeeDialog dialog = new UpdateEmployeeDialog(Shell);
		dialog.open();

		System.out.println(dialog.getFirstName());
		System.out.println(dialog.getLastName());
		System.out.println(dialog.getAge());
		System.out.println(dialog.getSalary());

		tree = singleton.getTree();
		itemEmployees = singleton.getItemEmployees();
		itemSelected = singleton.getItemSelected();

		itemSelected.getItem(0).setText("Firstname: "+dialog.getFirstName());
		itemSelected.getItem(1).setText("Lastname: "+dialog.getLastName());
		itemSelected.getItem(2).setText("Age: "+String.valueOf(dialog.getAge()));
		itemSelected.getItem(3).setText("Salary: "+String.valueOf(dialog.getSalary()));
		tree.setRedraw(true);

		return null;
	}

}
