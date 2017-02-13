package asa;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;

public class UpdateEmployeeDialog extends TitleAreaDialog {
	private ArrayList<TreeItem> itemSelectedChildren;
	private TreeItem itemSelected;
	private Text txtFirstName;
	private Text txtLastName;
	private Text txtAge;
	private Text txtSalary;

	private String firstName;
	private String lastName;
	private int age;
	private long salary;
	
	ClassicSingleton singleton = ClassicSingleton.getInstance();
	
	
	
	
	public UpdateEmployeeDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		itemSelected = singleton.getItemSelected();
		super.create();
		setTitle("Update");
		setMessage(itemSelected.getText(), IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		createFirstName(container);
		createLastName(container);
		createAge(container);
		createSalary(container);

		return area;
	}

	private void createFirstName(Composite container) {
		itemSelectedChildren =singleton.getItemSelectedChildren();
		String text =itemSelectedChildren.get(0).getText();
	    String[] result = text.split(" ");
		String part1 = result[1];
	   
		
		Label lbtFirstName = new Label(container, SWT.NONE);
		lbtFirstName.setText("First Name");

		GridData dataFirstName = new GridData();
		dataFirstName.grabExcessHorizontalSpace = true;
		dataFirstName.horizontalAlignment = GridData.FILL;

		txtFirstName = new Text(container, SWT.BORDER);
		txtFirstName.setLayoutData(dataFirstName);
		txtFirstName.setText(part1);
	}

	private void createLastName(Composite container) {
		itemSelectedChildren =singleton.getItemSelectedChildren();
		String text =itemSelectedChildren.get(1).getText();
	    String[] result = text.split(" ");
		String part1 = result[1];
		
		Label lbtLastName = new Label(container, SWT.NONE);
		lbtLastName.setText("Last Name");

		GridData dataLastName = new GridData();
		dataLastName.grabExcessHorizontalSpace = true;
		dataLastName.horizontalAlignment = GridData.FILL;
		txtLastName = new Text(container, SWT.BORDER);
		txtLastName.setLayoutData(dataLastName);
		txtLastName.setText(part1);
	}

	private void createAge(Composite container) {
		itemSelectedChildren =singleton.getItemSelectedChildren();
		String text =itemSelectedChildren.get(2).getText();
		String[] result = text.split(" ");
		String part1 = result[1];
		System.out.println(part1);
		
		Label lbtAge = new Label(container, SWT.NONE);
		lbtAge.setText("Age");

		GridData dataAge = new GridData();
		dataAge.grabExcessHorizontalSpace = true;
		dataAge.horizontalAlignment = GridData.FILL;
		txtAge = new Text(container, SWT.BORDER);
		txtAge.setLayoutData(dataAge);
		txtAge.setTextLimit(2);
		txtAge.setText(part1);
		
		txtAge.addListener(SWT.Verify, new Listener() {

			@Override
			public void handleEvent(Event event) {

				String string = event.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9')) {
						event.doit = false;
						return;
					}

				}
				

			}
		});
	}

	private void createSalary(Composite container) {
		itemSelectedChildren =singleton.getItemSelectedChildren();
		String text =itemSelectedChildren.get(3).getText();
	    String[] result = text.split(" ");
		String part1 = result[1];
		
		Label lbtSalary = new Label(container, SWT.NONE);
		lbtSalary.setText("Salary");

		GridData dataSalary = new GridData();
		dataSalary.grabExcessHorizontalSpace = true;
		dataSalary.horizontalAlignment = GridData.FILL;
		txtSalary = new Text(container, SWT.BORDER);
		txtSalary.setLayoutData(dataSalary);
		txtSalary.setText(part1);
		
		txtSalary.addListener(SWT.Verify, new Listener() {

			@Override
			public void handleEvent(Event event) {

				String string = event.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9')) {
						event.doit = false;
						return;
					}
				}

			}
		});

	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	// save content of the Text fields because they get disposed
	// as soon as the Dialog closes
	private void saveInput() {

		firstName = txtFirstName.getText();
		lastName = txtLastName.getText();
		age = Integer.parseInt(txtAge.getText());
		salary = Long.parseLong(txtSalary.getText());

	}

	@Override
	protected void okPressed() {
		saveInput();
		if (firstName != null && lastName != null && age != 0 && salary != 0) {
			super.okPressed();
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public long getSalary() {
		return salary;
	}

}