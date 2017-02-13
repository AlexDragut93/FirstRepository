package asa;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.jface.viewers.TableTreeViewer;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;

public class MyTitleAreaDialog extends Dialog {
	private Text txtFirstName;
	private Text txtLastName;
	private String FirstName = "";
	private String LastName = "";
	private String Age = "";
	private String Salary = "";
	private Text txtAge;
	private Text txtSalary;

	public MyTitleAreaDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setToolTipText("");

		container.setLayout(new FormLayout());

		Group grpDasdas = new Group(container, SWT.NONE);
	
		
		grpDasdas.setText("Employee");
		grpDasdas.setLayout(null);
		grpDasdas.setTabList(new Control[] { txtFirstName, txtLastName, txtAge, txtSalary });
		
				Label lblFirstName = new Label(grpDasdas, SWT.NONE);
				lblFirstName.setBounds(29, 27, 71, 20);
				lblFirstName.setText("First name:");
		
				txtFirstName = new Text(grpDasdas, SWT.BORDER);
				txtFirstName.setBounds(127, 24, 316, 26);
				txtFirstName.setText(FirstName);
				txtFirstName.addModifyListener(new ModifyListener() {

					@Override
					public void modifyText(ModifyEvent e) {
						Text textWidget = (Text) e.getSource();
						String FirstNameText = textWidget.getText();
						FirstName = FirstNameText;
					}
				});
		
				Label lblLastName = new Label(grpDasdas, SWT.NONE);
				lblLastName.setBounds(29, 62, 67, 20);
				lblLastName.setText("Last name");

		txtLastName = new Text(grpDasdas, SWT.BORDER);
		txtLastName.setBounds(127, 59, 316, 26);
		txtLastName.setText(LastName);
		txtLastName.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				Text textWidget = (Text) e.getSource();
				String LastNameText = textWidget.getText();
				LastName = LastNameText;
			}
		});
		
				Label lblAge = new Label(grpDasdas, SWT.NONE);
				lblAge.setBounds(29, 98, 30, 20);
				lblAge.setText("Age:");

		txtAge = new Text(grpDasdas, SWT.BORDER);
		txtAge.setBounds(127, 95, 316, 26);
		txtAge.setText(Age);
		txtAge.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				Text textWidget = (Text) e.getSource();
				String AgeText = textWidget.getText();
				Age = AgeText;
				
			}
		});

		Label lblSalary = new Label(grpDasdas, SWT.NONE);
		lblSalary.setBounds(29, 137, 43, 20);
		lblSalary.setText("Salary:");
		

		txtSalary = new Text(grpDasdas, SWT.BORDER);
		txtSalary.setBounds(127, 134, 316, 26);
		txtSalary.setText(Salary);
		txtSalary.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				Text textWidget = (Text) e.getSource();
				String SalaryText = textWidget.getText();
				Salary = SalaryText;
				
			}
		});
		return container;
	}

	// override method to use "Login" as label for the OK button
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		Button button = createButton(parent, IDialogConstants.OK_ID, "Login", true);
		button.setText("Add");
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(485, 262);
	}

	@Override
	protected void okPressed() {
		FirstName = txtFirstName.getText();
		LastName = txtLastName.getText();
		Age = txtAge.getText();
		Salary = txtSalary.getText();
		super.okPressed();
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getSalary() {
		return Salary;
	}

	public void setSalary(String salary) {
		Salary = salary;
	}


}