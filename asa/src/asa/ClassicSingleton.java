package asa;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.w3c.dom.Document;

public class ClassicSingleton {
	private static ClassicSingleton instance = null;

	private Tree tree;
	private TreeItem itemSelected;
	private TreeItem itemEmployees;
	private ArrayList<TreeItem> itemSelectedChildren;
	public ArrayList<TreeItem> getItemSelectedChildren() {
		return itemSelectedChildren;
	}

	public void setItemSelectedChildren(ArrayList<TreeItem> itemSelectedChildren) {
		this.itemSelectedChildren = itemSelectedChildren;
	}

	public TreeItem getItemEmployees() {
		return itemEmployees;
	}

	public void setItemEmployees(TreeItem itemEmployees) {
		this.itemEmployees = itemEmployees;
	}

	private Document doc;

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public TreeItem getItemSelected() {
		return itemSelected;
	}

	public void setItemSelected(TreeItem itemSelected) {
		this.itemSelected = itemSelected;
	}

	protected ClassicSingleton() {
		// Exists only to defeat instantiation.
	}

	public static ClassicSingleton getInstance() {
		if (instance == null) {
			instance = new ClassicSingleton();
		}
		return instance;
	}

	public Tree getTree() {
		return tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}
}