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
	
	public void createFind() {		
		
		gui.createFind();
	}
	
	public void nextfind() {
		
		// index부여하기 위한 num
		int num = 0;
		// 위로 일때
		if(gui.up.isSelected()) {
			
		}
		// 아래로 일때
		else if(gui.down.isSelected()) {
			String word = gui.textArea.getSelectedText();
			int word_index = gui.textArea.getText().indexOf(word, num);
			if(gui.division.isSelected() == false) {
				word = gui.textArea.getSelectedText().toLowerCase();
				word_index = gui.textArea.getText().toLowerCase().indexOf(word, num);
			}
			String str = gui.textArea.getText().replaceAll("\\r", "");
			gui.textArea.setText(str);
			gui.textArea.requestFocus();
			for(int i = 0; i < str.length(); i++) {
				if(word_index == i) {
					gui.textArea.select(word_index, word_index + word.length());
					num = word_index + word.length();
				}
			}
			JOptionPane op = new JOptionPane();
			
			if(num != word_index + word.length()) {
				JOptionPane.showMessageDialog(null, "\"" + word + "\"" + " 를 찾을 수 없습니다.");
			}
			
			
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


