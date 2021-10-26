package SimpleNotepad;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Desktop;
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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	
	public void googleFind() {
		
		String str = gui.textArea.getSelectedText();
		
		String link = "https://www.google.com/search?q=" + str;
		
		if(str != null) {
			try {
				Desktop.getDesktop().browse(new URI(link));
			}catch(IOException e) {
				e.printStackTrace();
			}catch(URISyntaxException e) {
				e.printStackTrace();
			}
		}
		else
			JOptionPane.showMessageDialog(null,  "찾을 내용이 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
	}
	
	public void createFind() {		
		
		gui.createFind();		
	}
	
	public void nextfind() {
		
		int num = 0;
		
		String search_input = gui.string.getText();
		int search_index = gui.textArea.getText().indexOf(search_input, num);
		if(gui.division.isSelected() == false) {
			System.out.println("대소구분안함");
			search_input = gui.string.getText().toLowerCase();
			search_index = gui.textArea.getText().toLowerCase().indexOf(search_input, num);
		}
		String str = gui.textArea.getText().replaceAll("\\r", "");
		gui.textArea.setText(str);
		gui.textArea.requestFocus();
		for(int i = 0; i < str.length(); i++) {
			if(search_index == i) {
				gui.textArea.select(search_index, search_index + search_input.length());
				continue;
			}
		}
		if(num != search_index + search_input.length()) {
			JOptionPane.showMessageDialog(null,  "모두 다 찾았습니다.", "종료", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void cancel() {
		
		gui.search.setVisible(false);

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


