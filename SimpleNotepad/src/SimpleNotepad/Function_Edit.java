package SimpleNotepad;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.Frame;
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
	
	public void find() {		
		
	}
	
	public void nextfind() {

		Search_Frame sf = new Search_Frame("다음 찾기", gui);
		sf.actionPerformed(null);
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

class Search_Frame extends Frame implements ActionListener{
	
	GUI gui;
	Panel Search_panel;
	Panel cancel_panel;
	Panel check_panel;
	Checkbox checkbox;
	Label content_label;
	Label check_label;
	TextField content_txt;
	Button next_search_btn;
	Button cancel;
	int num = 0;
	String search_input;
	int search_index;
	String str;
	
	Search_Frame(String Title, GUI gui){
		super(Title);
		this.gui = gui;
		Search_panel = new Panel();
		cancel_panel = new Panel();
		check_panel = new Panel();
		checkbox = new Checkbox(null, true, null);
		content_label = new Label("찾을 내용 : ");
		check_label = new Label("대 / 소문자 구분");
		content_txt = new TextField(30);
		next_search_btn = new Button("다음 찾기");
		cancel = new Button("취    소");
		
		Search_panel.add(content_label);
		Search_panel.add(content_txt);
		Search_panel.add(next_search_btn);
		cancel_panel.add(cancel);
		check_panel.add(checkbox);
		check_panel.add(check_label);
		add(Search_panel, "North");
		add(cancel_panel, "East");
		add(check_panel, FlowLayout.LEFT);
		
		next_search_btn.addActionListener(this);
		cancel.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == "다음 찾기") {
			search_input = content_txt.getText();
			search_index = gui.textArea.getText().indexOf(search_input, num);
			if(checkbox.getState() == false) {
				System.out.println("대 / 소 구분 안함");
				search_input = content_txt.getText().toLowerCase();
				search_index= gui.textArea.getText().toLowerCase().indexOf(search_input, num);
			}
			str = gui.textArea.getText().replaceAll("\\r", "");
			gui.textArea.setText(str);
			gui.textArea.requestFocus();
			for(int aa = 0; aa < str.length(); aa++) {
				if(search_index == aa) {
					gui.textArea.select(search_index,  search_index + search_input.length());
					num = (search_index + search_input.length());
				}
			}
			
			if(num != search_index + search_input.length()) {
				End end = new End("경고창", search_input);
				end.setVisible(true);
				end.setBounds(200, 200, 200, 100);
				System.out.println("모두 찾음");
			}
			else {
				this.setVisible(false); // 찾기 창 닫기
			}
		}
	}
	class End extends Frame{
		End(String Title, String input){
			super(Title);
			Label msg = new Label();
			msg.setText(input + "를 찾을 수 없습니다.");
			add(msg);		
		}
	}
	
	
	public void actionPerformed2(ActionEvent e) {
		if (e.getActionCommand() == "찾기") {
			   Search_Frame search_frame = new Search_Frame("찾기", gui);
			   search_frame.setBounds(300, 200, 410, 110);
			   search_frame.setVisible(true);
			   System.out.println("찾기버튼클릭");
			  }
	}
}
