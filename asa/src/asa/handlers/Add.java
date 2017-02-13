package asa.handlers;

import java.io.File;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import asa.ClassicSingleton;
import asa.AddEmployeeDialog;
import asa.View;

public class Add extends AbstractHandler {
	public String text = "C:/workspace/asa/file.text";
	public String inputText;
	public String node;
	public Tree tree;
	public TreeItem itemSelected;
	public TreeItem itemEmployees;
	public int countEmployees;
	
	private static final Shell Shell = null;
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		ClassicSingleton singleton = ClassicSingleton.getInstance();
		
		
		AddEmployeeDialog dialog = new AddEmployeeDialog(Shell);
		dialog.open();
		System.out.println(dialog.getFirstName());
		System.out.println(dialog.getLastName());
		System.out.println(dialog.getAge());
		System.out.println(dialog.getSalary());
		
		tree = singleton.getTree();
		itemEmployees = singleton.getItemEmployees();
		TreeItem[] children = itemEmployees.getItems();
		
		String text = children[children.length-1].getText();
	    String[] result = text.split(" ");
		String part1 = result[1];
	        

	        
	    
		countEmployees = Integer.parseInt(part1);
		
	
		
		System.out.println(countEmployees);
		System.out.println(countEmployees+1+"+1");
		
	
		
	
		
		TreeItem itemEmployee = new TreeItem(itemEmployees, SWT.NONE);
		itemEmployee.setText("Employee: " + (countEmployees+1) );

		TreeItem itemFirstname = new TreeItem(itemEmployee, SWT.NONE);
		itemFirstname
				.setText("Firstname: " + dialog.getFirstName());

		TreeItem itemLastname = new TreeItem(itemEmployee, SWT.NONE);
		itemLastname.setText("Lastname: " + dialog.getLastName());

		TreeItem itemAge = new TreeItem(itemEmployee, SWT.NONE);
		itemAge.setText(
				"Age: " +dialog.getAge());

		TreeItem itemSalary = new TreeItem(itemEmployee, SWT.NONE);
		itemSalary.setText("Salary: " + dialog.getSalary());
		
		tree.setRedraw(true);
		
		
		
		
		
		
		
		
		
		
		
		
	/*	**********************ADD TreeItem after selection *********************
	 * 
	 * IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

		ClassicSingleton singleton = ClassicSingleton.getInstance();

		InputDialog id = new InputDialog(window.getShell(), "Add", "Please add a word or number: ", null, null);

		id.open();

		inputText = id.getValue();
		Tree tree = singleton.getTree();
		
		TreeItem itemSelected = singleton.getItemSelected();

		if(itemSelected != null){

			TreeItem itemNew = new TreeItem(itemSelected, SWT.None);
			itemNew.setText(inputText);
		

			tree.setRedraw(true);
		}
		else {
	
			JOptionPane.showMessageDialog(null,"Tree item is not selected");
		}
		
		*
		*********************************************************************/
		
		


/* ***************
 *  Write a file *   
 * ***************/
//		try {
//
//			PrintWriter file = new PrintWriter(text);
//			file.println(inputText);
//			file.close();
//		} catch (IOException exp) {
//			// TODO Auto-generated catch block
//			exp.printStackTrace();
//		}
//

	
/* ************** */

		
		return null;
	}

	public String getText() {

		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @wbp.parser.entryPoint
	 */

}
