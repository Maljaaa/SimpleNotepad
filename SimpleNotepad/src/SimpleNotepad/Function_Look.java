package SimpleNotepad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;


public class Function_Look {
	GUI gui;
	String fileName;
	String fileAddress;
	Point x, y;
	
	JPanel statusPanel = new JPanel();
	JLabel statusLabel = new JLabel("Ln " + y + ", " + "Col " + x);
	public Function_Look(GUI gui) {
		
		this.gui = gui;
	}
	
	public void StatusBarMenu() {
		// On하려면
		if(gui.statusbarOn == false) { 
			gui.statusbarOn = true; // On으로 바꿈
			CreateStatusbar();
			gui.iState.setText("Status Bar : On");
		}
				
		// Off하려면
		else if(gui.wordWrapOn == true){
			gui.statusbarOn = false;
			statusPanel.setVisible(false);
			statusLabel.setVisible(false);
			gui.iState.setText("Status bar : Off");
		}
	}
	// 상태 표시줄 생성
	public void CreateStatusbar() {
		
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));	// 패널을 아래쪽에 생성
		gui.window.add(statusPanel, BorderLayout.SOUTH);	// 프레임에 패널 추가
		statusPanel.setPreferredSize(new Dimension(gui.window.getWidth(), 16));	// 패널 크기 설정
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));	// 패널 가로로 설정
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);	// 라벨 왼쪽 정렬
		statusPanel.add(statusLabel);		// 패널에 라벨 추가		
		// 반복되는 클릭에도 작동되도록 조치
		statusPanel.setVisible(true);
		statusLabel.setVisible(true);
		// 작은 창에서 바로 보일 수 있도록 조치 
		gui.window.setVisible(true);
	}
	
	// 몇번째 줄인지 계산
	public void countLn() {
		 PointerInfo pt = MouseInfo.getPointerInfo();
         while(true) {
                pt = MouseInfo.getPointerInfo();
                x = pt.getLocation();
                y = pt.getLocation();
         }
	}
}
	


