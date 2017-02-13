package asa.handlers;



import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import asa.ClassicSingleton;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SaveXML extends AbstractHandler {
	
	private static void getAllItems(Tree tree, ArrayList<TreeItem> allItems)
	{
	    for(TreeItem item : tree.getItems())
	    {
	        getAllItems(item, allItems);
	    }
	}

	private static void getAllItems(TreeItem currentItem, ArrayList<TreeItem> allItems)
	{
	    TreeItem[] children = currentItem.getItems();

	    for(int i = 0; i < children.length; i++)
	    {
	        allItems.add(children[i]);

	        getAllItems(children[i], allItems);
	    }
	}
	
	
	
	
	/**
	 * The constructor.
	 */
	public SaveXML() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		
		ClassicSingleton singleton = ClassicSingleton.getInstance();
//		
//		
//		Document doc = singleton.getDoc();
		
		Tree tree = singleton.getTree();
		
		
		ArrayList<TreeItem> allItems = new ArrayList<TreeItem>();

		getAllItems(tree, allItems);

	    System.out.println(allItems);
	    
	    
	    
		
		DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
        DocumentBuilder build = null;
		try {
			build = dFact.newDocumentBuilder();
		} catch (ParserConfigurationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        Document doc = build.newDocument();
        
        
        Element root = doc.createElement("Employees");
        doc.appendChild(root);
        System.out.println(allItems.size());
        
        int number = allItems.size()/5;
        
        for (int j=0 ; j<number;j++) {
        	String id = String.valueOf(j+1);
        	
        
            Element employee = doc.createElement("Employee");
            employee.setAttribute("ID", id);
            employee.appendChild(doc.createTextNode(allItems.get(5*j+0).getText()));
            System.out.println(5*j+0);
            System.out.println(5*j+1);
            System.out.println(5*j+2);
            System.out.println(5*j+3);
            System.out.println(5*j+4);
            root.appendChild(employee);

            Element firstName = doc.createElement("Firstname");
            firstName.appendChild(doc.createTextNode(allItems.get(5*j+1).getText()));
            employee.appendChild(firstName);

            Element lastName = doc.createElement("Lastname");
            lastName.appendChild(doc.createTextNode(allItems.get(5*j+2).getText()));
            employee.appendChild(lastName);
            
            Element age = doc.createElement("Age");
            age.appendChild(doc.createTextNode(allItems.get(5*j+3).getText()));
            employee.appendChild(age);
            
            Element salary = doc.createElement("Salary");
            salary.appendChild(doc.createTextNode(allItems.get(5*j+4).getText()));
            employee.appendChild(salary);
            
            
            
        }
        
        
        
        
		
		Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException | TransformerFactoryConfigurationError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		//initialize StreamResult with File object to save to file
		StreamResult result = new StreamResult("C:/workspace/asa/test.xml");
		DOMSource source = new DOMSource(doc);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		String xmlString = result.getWriter().toString();
//		System.out.println(xmlString);

		
		return null;
	}
}
