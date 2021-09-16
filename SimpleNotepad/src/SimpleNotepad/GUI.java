package SimpleNotepad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

// 기본 창 생성
public class GUI implements ActionListener {
	
	// TEXT AREA
	// 프레임 생성
	JFrame window;
	
	// 텍스트 영역 생성
	JTextArea textArea;
	
	// 스크롤바 사용
	JScrollPane scrollPane;
	
	boolean wordWrapOn = false;
	
	boolean statusbarOn = false;
	
	// TOP MENU BAR
	// 메뉴바 생성
	JMenuBar menuBar;
	
	// 상위 메뉴 생성
	JMenu menuFile, menuEdit, menuFormat, menuLook, menuHelp;
	
	// FILE MENU
	// 하위 메뉴 생성
	JMenuItem iNew, iNWindow, iOpen, iSave, iSaveAs, iPage, iPrint, iExit;
	
	// EDIT MENU
	JMenuItem iUndo, iRedo, iCut, iCopy, iPaste, iDelete, iFind, iNFind, iChange, iMove, iASelect, iCalendar;
	
	// FORMAT MENU
	JMenuItem iWrap, iFontArial, iFontCSMS, iFontTNR, iFontSize8, iFontSize12, iFontSize16, iFontSize20, iFontSize24, iFontSize26;
	JMenu menuFont, menuFontSize;
	
	// LOOK MENU
	JMenuItem iState;
	
	// HELP MENU
	JMenuItem iHelp;
	
	// CREATE FIND WINDOW
	JFrame search ;
	JPanel searchPanel, dPanel;
	JLabel searchString;
	JTextField string ;
	JButton searchNext, cancel;
	JCheckBox division, arround;
	JRadioButton up, down;
		
	// Function_File 객체 생성 -> 불러서 쓰기 위해 -> this로 연결
	Function_File file = new Function_File(this);
	Function_Format format = new Function_Format(this);
	Function_Edit edit = new Function_Edit(this);
	Function_Look look = new Function_Look(this);
	Function_Help help = new Function_Help(this);
	
	UndoManager um = new UndoManager();

	int count = 1;
	
	public static void main(String[] args) {
		new GUI();

	}
	
	// 생성자 생성
	public GUI() {
		
		createWindow();
		createTextArea();
		createMenuBar();
		createFileMenu();
		createEditMenu();
		createFormatMenu();
		createLookMenu();
		createHelpMenu();
		
		format.selectedFont = "Arial";
		format.createFont(16);
		format.wordWrap();
		
		window.setVisible(true);
	}
	
	// 창 생성 메소드
	public void createWindow() {
		
		this.count = count;
	    
		// 프레임 이름 설정
		window = new JFrame("Notepad");
		// 프레임 크기 설정
		window.setSize(800, 600);
		// x누르면 종료
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 화면 중앙에 위치
		// 프레임(자바 화면) 크기
	    Dimension frameSize = window.getSize();
	    // 모니터 크기
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    // (모니터화면 가로 - 프레임화면 가로) / 2, (모니터화면 세로 - 프레임화면 세로) / 2
	    window.setLocation(((screenSize.width - frameSize.width) / 2) + count, ((screenSize.height - frameSize.height) / 2) + count);
	    
	    count += 2;
	}
	
	// 텍스트 영역 메소드
	public void createTextArea() {
		
		// 텍스트 영역 생성
		textArea = new JTextArea();
		
		textArea.getDocument().addUndoableEditListener(
				new UndoableEditListener() {
					public void undoableEditHappened(UndoableEditEvent e) {
						um.addEdit(e.getEdit());
					}
				});
		
		// 수평, 수직 상태에서 필요하면 스크롤 바 생성
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// 테두리 안보이게
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		// 스크롤바 적용
		window.add(scrollPane);
		//window.add(textArea);
	}
	
	// 메뉴바 생성
	public void createMenuBar() {
		
		menuBar = new JMenuBar();
		window.setJMenuBar(menuBar); // 메뉴바 적용
		
		menuFile = new JMenu("File"); // 상위 메뉴 객체 생성
		menuBar.add(menuFile); // 메뉴바에 객체 적용
		
		menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);
		
		menuFormat = new JMenu("Format");
		menuBar.add(menuFormat);
		
		menuLook = new JMenu("Look");
		menuBar.add(menuLook);
		
		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
	}
	
	// 파일 메뉴. 하위 메뉴 생성
	public void createFileMenu() {
		
		iNew = new JMenuItem("New"); // 하위 메뉴 객체 생성
		iNew.addActionListener(this); // 동작 받기
		iNew.setActionCommand("New");  // 동작 이름 표현 -> case문 사용하기 위해
		menuFile.add(iNew); // 메뉴 적용
		
		iNWindow = new JMenuItem("New Window");
		iNWindow.addActionListener(this);
		iNWindow.setActionCommand("New Window");
		menuFile.add(iNWindow);
		
		iOpen = new JMenuItem("Open");
		iOpen.addActionListener(this);
		iOpen.setActionCommand("Open");
		menuFile.add(iOpen);
		
		iSave = new JMenuItem("Save");
		iSave.addActionListener(this);
		iSave.setActionCommand("Save");
		menuFile.add(iSave);
		
		iSaveAs = new JMenuItem("SaveAs");
		iSaveAs.addActionListener(this);
		iSaveAs.setActionCommand("SaveAs");
		menuFile.add(iSaveAs);
		
		menuFile.addSeparator();
		
		iPage = new JMenuItem("Page");
		iPage.addActionListener(this);
		iPage.setActionCommand("Page");
		menuFile.add(iPage);
		
		iPrint = new JMenuItem("Print");
		iPrint.addActionListener(this);
		iPrint.setActionCommand("Print");
		menuFile.add(iPrint);
		
		menuFile.addSeparator();
		
		iExit = new JMenuItem("Exit");
		iExit.addActionListener(this);
		iExit.setActionCommand("Exit");
		menuFile.add(iExit);
	}
	
	public void createEditMenu() {
		
		iUndo = new JMenuItem("Undo");
		iUndo.addActionListener(this);
		iUndo.setActionCommand("Undo");
		menuEdit.add(iUndo);
		
		iRedo = new JMenuItem("Redo");
		iRedo.addActionListener(this);
		iRedo.setActionCommand("Redo");
		menuEdit.add(iRedo);
		
		menuEdit.addSeparator();
		
		iCut = new JMenuItem("Cut");
		iCut.addActionListener(this);
		iCut.setActionCommand("Cut");
		menuEdit.add(iCut);
		
		iCopy = new JMenuItem("Copy");
		iCopy.addActionListener(this);
		iCopy.setActionCommand("Copy");
		menuEdit.add(iCopy);
		
		iPaste = new JMenuItem("Paste");
		iPaste.addActionListener(this);
		iPaste.setActionCommand("Paste");
		menuEdit.add(iPaste);
		
		iDelete = new JMenuItem("Delete");
		iDelete.addActionListener(this);
		iDelete.setActionCommand("Delete");
		menuEdit.add(iDelete);
		
		menuEdit.addSeparator();
		
		iFind = new JMenuItem("Find");
		iFind.addActionListener(this);
		iFind.setActionCommand("Find");
		menuEdit.add(iFind);
		
		iNFind = new JMenuItem("Next Find");
		iNFind.addActionListener(this);
		iNFind.setActionCommand("Next Find");
		menuEdit.add(iNFind);
		
		iChange = new JMenuItem("Change");
		iChange.addActionListener(this);
		iChange.setActionCommand("Change");
		menuEdit.add(iChange);
		
		iMove = new JMenuItem("Move");
		iMove.addActionListener(this);
		iMove.setActionCommand("Move");
		menuEdit.add(iMove);
		
		menuEdit.addSeparator();
		
		iASelect = new JMenuItem("All Select");
		iASelect.addActionListener(this);
		iASelect.setActionCommand("All Select");
		menuEdit.add(iASelect);
		
		iCalendar = new JMenuItem("Calendar");
		iCalendar.addActionListener(this);
		iCalendar.setActionCommand("Calendar");
		menuEdit.add(iCalendar);
		
	}
	
	public void createFormatMenu() {
		
		iWrap = new JMenuItem("Word Wrap : Off");
		iWrap.addActionListener(this);
		iWrap.setActionCommand("Word Wrap");
		menuFormat.add(iWrap);
		
		menuFont = new JMenu("Font");
		menuFormat.add(menuFont);
		
		iFontArial = new JMenuItem("Arial");
		iFontArial.addActionListener(this);
		iFontArial.setActionCommand("Arial");
		menuFont.add(iFontArial);
		
		iFontCSMS = new JMenuItem("Comic Sans MS");
		iFontCSMS.addActionListener(this);
		iFontCSMS.setActionCommand("Comic Sans MS");
		menuFont.add(iFontCSMS);
		
		iFontTNR = new JMenuItem("Times New Roman");
		iFontTNR.addActionListener(this);
		iFontTNR.setActionCommand("Times New Roman");
		menuFont.add(iFontTNR);
		
		menuFontSize = new JMenu("Font Size");
		menuFormat.add(menuFontSize);
		
		iFontSize8 = new JMenuItem("8");
		iFontSize8.addActionListener(this);
		iFontSize8.setActionCommand("size8");
		menuFontSize.add(iFontSize8);
		
		iFontSize12 = new JMenuItem("12");
		iFontSize12.addActionListener(this);
		iFontSize12.setActionCommand("size12");
		menuFontSize.add(iFontSize12);
		
		iFontSize16 = new JMenuItem("16");
		iFontSize16.addActionListener(this);
		iFontSize16.setActionCommand("size16");
		menuFontSize.add(iFontSize16);
		
		iFontSize20 = new JMenuItem("20");
		iFontSize20.addActionListener(this);
		iFontSize20.setActionCommand("size20");
		menuFontSize.add(iFontSize20);
		
		iFontSize24 = new JMenuItem("24");
		iFontSize24.addActionListener(this);
		iFontSize24.setActionCommand("size24");
		menuFontSize.add(iFontSize24);
		
		iFontSize26 = new JMenuItem("26");
		iFontSize26.addActionListener(this);
		iFontSize26.setActionCommand("size26");
		menuFontSize.add(iFontSize26);
	}
	
	public void createLookMenu() {
		
		iState = new JMenuItem("Status bar : Off");
		iState.addActionListener(this);
		iState.setActionCommand("Status bar");
		menuLook.add(iState);
	}
	
	public void createHelpMenu() {
		
		iHelp = new JMenuItem("Help");
		iHelp.addActionListener(this);
		iHelp.setActionCommand("Help");
		menuHelp.add(iHelp);
	}
	
	public void createFind() {
		search = new JFrame("찾기");
		search.setSize(500, 200);
		search.setVisible(true);
		search.setResizable(false);
	    Dimension frameSize = search.getSize();
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    search.setLocation(((screenSize.width - frameSize.width) / 2), ((screenSize.height - frameSize.height) / 2));
	    search.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
		searchPanel = new JPanel();
		searchPanel.setLayout(null);
		
		searchString = new JLabel("찾을 내용(N):");
		searchString.setBounds(10, 10, 70, 30);
		string = new JTextField();
		string.setBounds(80, 10, 300, 30);
		
		searchNext = new JButton("다음 찾기(F)");
		searchNext.setBounds(380, 10, 100, 30);
		searchNext.addActionListener(this);
		searchNext.setActionCommand("다음 찾기(F)");
		
		cancel = new JButton("취소");
		cancel.setBounds(380, 40, 100, 30);
		cancel.addActionListener(this);
		cancel.setActionCommand("취소");
		
		division = new JCheckBox("대/소문자 구분(C)");
		division.setBounds(10, 100, 130, 30);
		division.setSelected(true);
		
		arround = new JCheckBox("주위에 배치(R)");
		arround.setBounds(10, 130, 110, 30);
		
		dPanel = new JPanel();
		dPanel.setLayout(null);
		dPanel.setBounds(220, 40, 160, 60);
		dPanel.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY), "방향"));
		
		// RadioButton으로 둘중 하나만 선택되게 설정. default값은 아래로 설정
		ButtonGroup group = new ButtonGroup();
		
		up = new JRadioButton("위로(U)");
		up.setBounds(5, 20, 80, 35);
		up.setSelected(false);
		
		down = new JRadioButton("아래로(D)");
		down.setBounds(75, 20, 100, 35);
		down.setSelected(true);
		
		group.add(up);
		group.add(down);
		
		search.add(searchPanel);
		searchPanel.add(searchString);
		searchPanel.add(string);
		searchPanel.add(searchNext);
		searchPanel.add(cancel);
		searchPanel.add(division);
		searchPanel.add(arround);
		
		searchPanel.add(dPanel);
		dPanel.add(up);
		dPanel.add(down);
		
		
		
		search.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand(); // addActionCommand로 쓴 이름 받기
		
		switch(command) {
		case "New" : file.newfile(); break; // command가 New일때 Function_File에 있는 newfile() 메소드 실행
		case "New Window" : file.newwindow(); break;
		case "Open" : file.open(); break;
		case "Save" : file.save(); break;
		case "SaveAs" : file.saveAs(); break;
		case "Page" : file.page(); break;
		case "Print" : file.print(); break;
		case "Exit" : file.exit(); break;
		case "Undo" : edit.undo(); break;
		case "Redo" : edit.redo(); break;
		case "Cut" : edit.cut(); break;
		case "Copy" : edit.copy(); break;
		case "Paste" : edit.paste(); break;
		case "Delete" : edit.delete(); break;
		case "Find" : edit.createFind(); break;
		case "다음 찾기(F)" : edit.nextfind(); break;
		case "취소" : edit.cancel(); break;
		case "Next Find" : edit.createFind(); break;
		case "Move" : edit.move(); break;
		case "All Select" : edit.allselect(); break;
		case "Calendar" : edit.calendar(); break;
		case "Word Wrap" : format.wordWrap(); break;
		case "Arial" : format.setFont(command); break;
		case "Comic Sans MS" : format.setFont(command); break;
		case "Times New Roman" : format.setFont(command); break;
		case "size8" : format.createFont(8); break;
		case "size12" : format.createFont(12); break;
		case "size16" : format.createFont(16); break;
		case "size20" : format.createFont(20); break;
		case "size24" : format.createFont(24); break;
		case "size26" : format.createFont(26); break;
		case "Status bar" : look.createStatusBar(); break;
		case "Help" : help.help(); break;
		}
	}

}
