package SimpleNotepad;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class Function_Edit {

	GUI gui;
	
	public Function_Edit(GUI gui) {
		
		this.gui = gui;
	}
	
	public void undo() {
		
		gui.um.undo();
	}
	
	public void redo() {
		
		gui.um.redo();
	}
	
	public void cut() {
		
		String copyText;
		copyText = gui.textArea.getSelectedText();
		
		int start = gui.textArea.getSelectionStart();
		int end = gui.textArea.getSelectionEnd();
		gui.textArea.replaceRange("", start, end);
	}
	
	public void copy() {
		
		StringSelection data = new StringSelection(gui.textArea.getSelectedText());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(data, data);
	}
	
	public void paste() {
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable data = clipboard.getContents(this);
		try {
			String copytext = (String)data.getTransferData(DataFlavor.stringFlavor);
			gui.textArea.append(copytext);
		} catch(Exception e) {
			
		}
	}
	
	public void delete() {
		
		int start = gui.textArea.getSelectionStart();
		int end = gui.textArea.getSelectionEnd();
		
		gui.textArea.replaceRange("", start, end);
	}
	
	public void createFind() {		
		gui.createFind();
	}
	
	public void nextfind() {
		String word = gui.string.getSelectedText();
		int position = gui.textArea.getCaretPosition();
		while(word != null) {
			
		}
		int start = gui.textArea.getSelectionStart();
		int end = gui.textArea.getSelectionEnd();
		gui.textArea.requestFocus();
		gui.textArea.select(start, end);
	}
	
	public void cancel() {
		gui.search.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
	
	
	public void change() {
		
	}
	
	public void move() {
		
	}
	
	public void allselect() {
		gui.textArea.selectAll();
	}
	
	public void calendar() {
		SimpleDateFormat format = new SimpleDateFormat("hh:mm yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		
		String format_calendar = format.format(cal.getTime());
		
		int ampm = cal.get(Calendar.AM_PM);
		String strAmpm = (ampm == Calendar.AM)?"오전":"오후";
		
		gui.textArea.append(strAmpm + " " + format_calendar);
	}
}


