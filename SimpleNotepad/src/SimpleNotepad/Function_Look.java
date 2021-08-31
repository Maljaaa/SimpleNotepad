package SimpleNotepad;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
	
	public Function_Look(GUI gui) {
		
		this.gui = gui;
	}
	
	public void createStatusBar() {
		// On하려면
		if(gui.statusbarOn == false) { 
			gui.statusbarOn = true; // On으로 바꿈
			statusbar();
			gui.iState.setText("Status Bar : On");
		}
				
		// Off하려면
		else if(gui.wordWrapOn == true){
			gui.statusbarOn = false;
			
			gui.iState.setText("Status bar : Off");
		}
	}
	public void statusbar() {
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		gui.window.add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setPreferredSize(new Dimension(gui.window.getWidth(), 16));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		JLabel count = new JLabel();
		count.setText("Ln " + countLn() + ", " + "Col " + countCol());
		count.setHorizontalAlignment(JLabel.RIGHT);
		statusPanel.add(count);
	}
	
	public int countLn() {
		int count = 0;
		
		return count;
	}
	
	public int countCol() {
		int count = 0;
		
		return count;
	}
}
