package SimpleNotepad;

import java.awt.Font;

public class Function_Format {

	GUI gui;
	Font arial, comicSansMS, timesNewRoman;
	String selectedFont;
	
	public Function_Format(GUI gui) {
		
		this.gui = gui;
	}
	
	// 창의 크기에 맞춰서 줄 넘기기
	public void wordWrap() {
		
		// On하려면
		if(gui.wordWrapOn == false) { 
			gui.wordWrapOn = true; // On으로 바꿈
			gui.textArea.setLineWrap(true);
			gui.textArea.setWrapStyleWord(true);
			gui.iWrap.setText("Word Wrap : On");
		}
		
		// Off하려면
		else if(gui.wordWrapOn == true){
			gui.wordWrapOn = false;
			gui.textArea.setLineWrap(false);
			gui.textArea.setWrapStyleWord(false);
			gui.iWrap.setText("Word Wrap : Off");
		}
	}
	
	// 사이즈에 맞게 폰트 설정
	public void createFont(int fontSize) {
		
		// 폰트 객체 생성
		arial = new Font("Arial", Font.PLAIN, fontSize);
		comicSansMS = new Font("ComicSansMS", Font.PLAIN, fontSize);
		timesNewRoman = new Font("timesNewRoman", Font.PLAIN, fontSize);
		
		setFont(selectedFont);
	}
	
	// 폰트에 맞게 사이즈 설정
	public void setFont(String font) {
		
		selectedFont = font; // 폰트 선택
		
		// 선택한 폰트가 xx이면
		switch(selectedFont) {
		case "Arial" : 
			gui.textArea.setFont(arial);
			break;
		case "Comic Sans MS" : 
			gui.textArea.setFont(comicSansMS);
			break;
		case "Times New Roman" : 
			gui.textArea.setFont(timesNewRoman);
			break;
		}
	}
}
