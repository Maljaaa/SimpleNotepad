package SimpleNotepad;

import java.awt.Desktop;

import java.net.URI;

public class Function_Help {
	
	GUI gui;
	
	public Function_Help(GUI gui) {
		
		this.gui = gui;
	}
	
	public void help() {
		String link = "https://support.microsoft.com/";
		
		try { 
			Desktop.getDesktop().browse(new URI(link)); 
		} catch (Exception e) { 
		
		}
	}
}
