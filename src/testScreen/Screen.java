package testScreen;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class Screen extends Info {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Screen window = new Screen();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(20, 10, 374, 231);
		
		Button button = new Button(composite, SWT.NONE);
		button.setLocation(214, 207);
		button.setSize(75, 25);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Info info = new Info();
				try {
					button.setText("Creating");
					info.setName(text.getText());
					info.create();
					button.setText("Create");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button.setText("Create");
		
		text = new Text(composite, SWT.BORDER);
		text.setLocation(0, 22);
		text.setSize(322, 25);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(0, 74, 322, 25);
		
		Label lblPorFavorEscreva = new Label(composite, SWT.NONE);
		lblPorFavorEscreva.setBounds(0, 0, 322, 15);
		lblPorFavorEscreva.setText("Por favor escreva o SN do Kidau / Microdau");
		
		Label lblEscrevaOTipo = new Label(composite, SWT.NONE);
		lblEscrevaOTipo.setBounds(0, 53, 322, 15);
		lblEscrevaOTipo.setText("Escreva o n\u00FAmero do Kidau/Microdau");
		
		Label lblEscrevaONmero = new Label(composite, SWT.NONE);
		lblEscrevaONmero.setBounds(0, 105, 322, 15);
		lblEscrevaONmero.setText("Escreva o n\u00FAmero EPUS do Kidau/Microdau");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setBounds(0, 126, 322, 25);
		
		Button btnUpdate = new Button(composite, SWT.NONE);
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Info info = new Info();
				try {
					btnUpdate.setText("Updating");
					info.setSN(text.getText());
					info.setEPUS(text_2.getText());
					info.setName(text_1.getText());
					try {
						info.modify();
					} catch (EncryptedDocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					btnUpdate.setText("Update");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdate.setBounds(295, 207, 75, 25);
		btnUpdate.setText("Update");
		
		Label lblDate = new Label(shell, SWT.NONE);
		lblDate.setBounds(330, 247, 94, 15);
		lblDate.setText(timeStamp);

	}
}
