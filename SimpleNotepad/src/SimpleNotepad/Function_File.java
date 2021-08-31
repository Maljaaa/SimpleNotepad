package SimpleNotepad;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

import javax.print.PrintService;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Function_File {
	
	GUI gui;
	String fileName;
	String fileAddress;
	
	public Function_File(GUI gui) {
		
		this.gui = gui;
	}
	
	// 새 문서
	public void newfile() {
		
		gui.textArea.setText(""); // 빈 텍스트 영역
		gui.window.setTitle("무제"); // 제목 설정
		fileName = null;
		fileAddress = null;
	}
	
	// 새 창
	public void newwindow() {
		
		// 새로운 GUI 생성
		GUI gui2 = new GUI();
		
		// 새 창 위치가 랜덤으로 나오게(단, 모니터를 벗어나지 않도록)
		Random random = new Random();
	    Dimension frameSize = gui2.window.getSize();
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		gui2.window.setLocation(random.nextInt((screenSize.width - frameSize.width)) + 1, random.nextInt((screenSize.height - frameSize.height)) + 1);
		
		gui2.textArea.setText(""); // 빈 텍스트 영역
		gui2.window.setTitle("무제"); // 제목 설정
		gui2.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	// 새 창만 닫기
		fileName = null;
		fileAddress = null;
	}
	
	// 열기
	public void open() {
		
		// FileDialog를 이용하여 불러오기 창 표현
		FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
		fd.setVisible(true);
		
		// 여기서부터 불러오기 알고리즘
		if(fd.getFile() != null) {
			fileName = fd.getFile(); // 파일 이름 얻기
			fileAddress = fd.getDirectory(); // 파일 주소 얻기
			gui.window.setTitle(fileName); // 파일 이름으로 제목 설정
		}
		
		try {
			// 버퍼 사용하여 파일 읽기
			BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName)); 
			
			gui.textArea.setText(""); // 텍스트 영역 초기화
			
			String line = null; // 줄 초기화
			
			// 계속해서 줄 읽기
			while((line = br.readLine()) != null) {
				gui.textArea.append(line + "\n"); // 줄 넘김 포함해서 데이터 읽기
			}
			br.close();
			
		} catch(Exception e) {
			
			System.out.println("FILE NOE OPENED!");
		}
	}
	
	// 저장 : 덮어쓰기
	public void save() {
		// fileName이 없을 떄 = 처음 작성 중일 때
		if(fileName == null) {
			saveAs();
		}
		
		// fileName이 있을 때 = 이미 저장을 한번한 문서일때
		else {
			try {
				FileWriter fw = new FileWriter(fileAddress + fileName); // 파일을 생성
				fw.write(gui.textArea.getText()); // 텍스트를 파일에 작성
				gui.window.setTitle(fileName); // 제목은 원래 있었던 제목으로
				fw.close();
			} catch(Exception e){
				System.out.println("SOMETHING WRONG!");
			}
		}
	}
	
	// 다른 이름으로 저장 : 새 파일 생성 - save보다 먼저 작성됨
	public void saveAs() {
		
		FileDialog fd = new FileDialog(gui.window, "Save", FileDialog.SAVE); // FileDialog로 save창 생성
		fd.setVisible(true);
		
		// 파일이 작성이 되어 있을 때
		if(fd.getFile() != null) {
			fileName = fd.getFile(); // 파일 이름 얻기
			fileAddress = fd.getDirectory(); // 파일 경로 얻기
			gui.window.setTitle(fileName); // 작성한 이름으로 파일 이름 설정
		}
		
		try {
			FileWriter fw = new FileWriter(fileAddress + fileName); 
			fw.write(gui.textArea.getText());
			fw.close();
		} catch(Exception e){
			System.out.println("SOMETHING WRONG!");
		}
	}
	
	// 페이지 설정
	public void page() {
		
	}
	
	// 프린트
	public void print() {
		PrinterJob pj = PrinterJob.getPrinterJob();
		if(pj.printDialog()) {
			try {
				pj.print();
			}catch(PrinterException exc) {
				System.out.println(exc);
			}
		}
	}
	
	// 종료
	public void exit() {
		
		System.exit(0);
	}
}
