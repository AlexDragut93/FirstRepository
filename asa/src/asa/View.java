package asa;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class View extends ViewPart {
	public static final String ID = "asa.view";
	public String text = "C:/workspace/asa/file.text";
	public Tree tree;

	public Composite parent;
	public NodeList nodeListEmployee;
	public Label labelText;
	public String tempText = "Text initial";
	public TreeItem itemSelected;
	public TreeItem itemEmployees;
	public Node node;
	public Element element;
	public ArrayList<TreeItem> itemSelectedChildren = new ArrayList<TreeItem>();

	public Composite getParent() {
		return parent;
	}

	public void setParent(Composite parent) {
		this.parent = parent;
	}

	class ViewLabelProvider extends LabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */

	public void createPartControl(Composite parent) {

		parent.setLayoutData(new FillLayout());

		ClassicSingleton singleton = ClassicSingleton.getInstance();

		tree = new Tree(parent, SWT.SINGLE | SWT.BORDER);

		singleton.setTree(tree);

		File f = new File("C:/workspace/com.dragut.e4.rcp.xmltree/Employees.xml");

		// Xml Parser
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Document doc = null;

		try {
			doc = builder.parse(f);

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		singleton.setDoc(doc);
		System.out.println("root element " + doc.getDocumentElement().getNodeName());

		nodeListEmployee = doc.getElementsByTagName("Employee");

		itemEmployees = new TreeItem(tree, SWT.NONE);
		itemEmployees.setText("Employees");
		singleton.setItemEmployees(itemEmployees);

		for (int temp = 0; temp < nodeListEmployee.getLength(); temp++) {

			node = nodeListEmployee.item(temp);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				element = (Element) node;

				TreeItem itemEmployee = new TreeItem(itemEmployees, SWT.NONE);
				itemEmployee.setText("Employee: " + element.getAttribute("ID"));

				TreeItem itemFirstname = new TreeItem(itemEmployee, SWT.NONE);
				itemFirstname
						.setText("Firstname: " + element.getElementsByTagName("Firstname").item(0).getTextContent());

				TreeItem itemLastname = new TreeItem(itemEmployee, SWT.NONE);
				itemLastname.setText("Lastname: " + element.getElementsByTagName("Lastname").item(0).getTextContent());

				TreeItem itemAge = new TreeItem(itemEmployee, SWT.NONE);
				itemAge.setText(
						"Age: " + Integer.parseInt(element.getElementsByTagName("Age").item(0).getTextContent()));

				TreeItem itemSalary = new TreeItem(itemEmployee, SWT.NONE);
				itemSalary.setText("Salary: " + element.getElementsByTagName("Salary").item(0).getTextContent());

			}

		}

		// tree.addSelectionListener(new SelectionAdapter() {
		//
		// public void widgetSelection(SelectionEvent e) {
		// TreeItem item = (TreeItem) e.item;
		// System.out.println("selectat");
		// if(item.getItemCount()>0){
		// TreeItem itemAdd = new TreeItem(item,SWT.None);
		// itemAdd.setText("newAdd");
		// tree.setRedraw(true);
		// }
		// }
		// });

		tree.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ClassicSingleton singleton = ClassicSingleton.getInstance();
				
				TreeItem[] selection = tree.getSelection();
				TreeItem[] revisedSelection = new TreeItem[0];
				String test = "Employee";

				for (int i = 0; i < selection.length; i++) {
					String text = selection[i].getText();
					String[] result = text.split(":");
					String part1 = result[0];
					if (part1.equals(test)) {
						TreeItem[] newSelection = new TreeItem[revisedSelection.length + 1];
						System.arraycopy(revisedSelection, 0, newSelection, 0, revisedSelection.length);
						newSelection[revisedSelection.length] = selection[i];
						revisedSelection = newSelection;

						itemSelected = revisedSelection[0];
						singleton.setItemSelected(itemSelected);

					}

					TreeItem[] children = itemSelected.getItems();
					itemSelectedChildren = new ArrayList<TreeItem>();
					System.out.println(children);
					for (int j = 0; j < children.length; j++) {
						itemSelectedChildren.add(children[j]);

					}
					
					singleton.setItemSelectedChildren(itemSelectedChildren);
					System.out.println(itemSelectedChildren);

					System.out.println(itemSelectedChildren.get(0).getText());
					System.out.println(itemSelectedChildren.get(1).getText());
					System.out.println(itemSelectedChildren.get(2).getText());
					System.out.println(itemSelectedChildren.get(3).getText());
					
					itemSelectedChildren =singleton.getItemSelectedChildren();
					String text1 =itemSelectedChildren.get(2).getText();
					String[] result1 = text1.split(" ");
					String part12 = result1[1];
					System.out.println(part12);

				}
				tree.setSelection(revisedSelection);

			}
		});

		// tree.addListener(SWT.Selection, new Listener() {
		// public void handleEvent(Event e) {
		//
		// /*
		// * ************* Read a file**
		// ***************/
		//
		// // String textFile = "";
		// //
		// // try {
		// // Scanner s = new Scanner(new
		// // File("C:/workspace/asa/file.text"));
		// // while (s.hasNextLine()) {
		// // textFile = s.nextLine();
		// // s.close();
		// // System.out.println(textFile);
		// //
		// // }
		// //
		// // } catch (Exception exp) {
		// //
		// // }
		//
		// /* ************** */
		//
		//
		// TreeItem[] revisedSelection = new TreeItem[0];
		// String test= "Employee";
		//
		////
		//
		//
		//
		//
		// ClassicSingleton singleton = ClassicSingleton.getInstance();
		//
		// itemSelected = (TreeItem) e.item;
		// System.out.println(itemSelected.getText());
		// String testSplit = itemSelected.getText() ;
		// String [] result = testSplit.split(":");
		// String part1 = result[0];
		// System.out.println(part1);
		// String employee="Employee";
		//
		// if (part1.equals(employee)) {
		// TreeItem[] newSelection = new TreeItem[revisedSelection.length + 1];
		// System.arraycopy(revisedSelection, 0, newSelection, 0,
		// revisedSelection.length);
		// newSelection[revisedSelection.length] = itemSelected;
		// revisedSelection = newSelection;
		//
		//
		// }
		//
		// tree.setSelection(revisedSelection);
		// TreeItem[] children = itemSelected.getItems();
		// System.out.println(children[0]);
		//
		// for(int i = 0; i < children.length; i++)
		// {
		// itemSelectedChildren.add(children[i]);
		//
		//
		// }
		// System.out.println(itemSelectedChildren);
		// System.out.println(children);
		//
		//
		//
		//// if(part1.equals(employee)){
		//// singleton.setItemSelected(itemSelected);
		//// }
		//// else{System.out.println("you not selected a employee");}
		// // if(item.getItemCount()>0){
		// // TreeItem itemAdd = new TreeItem(item,SWT.None);
		// // itemAdd.setText("aaaa");
		// // System.out.println(item.getItemCount());
		// //
		// // }
		// //
		// // tree.setRedraw(true);
		//
		// }
		// });
		
		TreeItem[] children12 = itemEmployees.getItems();
		ArrayList<TreeItem> treeItemsEmployee = new ArrayList<TreeItem>();
		ArrayList<TreeItem> treeItemsEmployeeChildren = new ArrayList<TreeItem>();
		
		
		for(int k=0 ; k< children12.length; k++){
			
			treeItemsEmployee.add(children12[k]);
			
			for(int l=0 ; l<children12[k].getItems().length; l++){
		treeItemsEmployeeChildren.add(children12[k].getItem(l));
		
		}
		}
		
		
		
		System.out.println(treeItemsEmployee);
		System.out.println(treeItemsEmployeeChildren);
		tree.getItems()[0].setExpanded(true);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {

		// }
	}
}