package src.project2.utils;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Menu {

    final private String border0 = "============================================================";
    final private String border1 = "----------------------------------------";
    final private String vborder0 = "||";
//    final private String vborder1 = "|";
    final private short MAX_MENU_LEVEL = 3;
    final private int SCREEN_WIDTH = border0.length();
    
    private Scanner keyboard;

    private class MenuItem {
	public String title;
	public String key;
	public int level;
	public MenuItem() {}
	public MenuItem(String t, int l, String k) {
	    title = t;
	    key = k.toLowerCase();
	    if (l <= 0)
		level = 1;
	    else if (l > MAX_MENU_LEVEL)
		level = MAX_MENU_LEVEL;
	    else
		level = l;
	}
	public String toString() {
		int leftBorderFmt, rightBorderFmt;
		String outFmt;
		
		leftBorderFmt = (6 * level) - 1 - key.length();
		rightBorderFmt = SCREEN_WIDTH - leftBorderFmt - title.length() - 6;
		outFmt = "%-" + leftBorderFmt + "s (%s) %s %" + rightBorderFmt + "s";
		return String.format(outFmt, vborder0, key, title, vborder0);
	}
    }
    
    private class BlankMenuItem extends MenuItem {
	public String toString() {
	    int offset = Math.floorDiv(SCREEN_WIDTH, 2);
	    String fmt = "%-" + offset +"s%" + offset + "s";
	    return String.format(fmt, vborder0, vborder0);
	}
    }
    
    private HashMap<String, ArrayList<MenuItem>> menus;
    
    public Menu(Scanner keyb) {
	keyboard = keyb;
	menus = new HashMap<String, ArrayList<MenuItem>>();
    }
    public Menu() {
	keyboard = new Scanner(System.in);
    }

    public void clearScreen() {
	System.out.print("\033[H\033[2J");
	System.out.flush();  
    }

    public void notImplemented() {
	notImplemented("");
    }
    public void notImplemented(String title) {
	clearScreen();
	System.out.println(border0);
	textRowCenter("KMN Event Manager - " + title);
	textRowCenter(border1);
	blankRows(2);
	textRowCenter("This method has not been implemented.");
	textRowCenter("It is left to the student to make this method work.");
	textRowCenter("When you do, remove the call to menu.notImplemented()");
	textRowCenter("That will make this message go away.");
	blankRows(2);
	textRowCenter("Enter \"a\" and press Enter to return: ");
	blankRows(2);
	System.out.printf("%s%n%n", border0);
	getUserInput();
    }

    public ArrayList<MenuItem> createMenu(String menuKey){
	ArrayList<MenuItem> newMenu = new ArrayList<MenuItem>();
	this.menus.put(menuKey, newMenu);
	return newMenu;
    }
    public void addMenuItem(String menuKey, String title, int level, String key) {
	ArrayList<MenuItem> currMenu = this.menus.get(menuKey);
	currMenu.add(new MenuItem(title, level, key));
    }
    public void addBlankMenuItem(String menuKey) {
	this.menus.get(menuKey).add(new BlankMenuItem());
    }
    
    public void showMenu(String menuKey, String title) {
	ArrayList<MenuItem> currMenu = this.menus.get(menuKey);
	clearScreen();
	System.out.println(border0);
	textRowCenter(title);
	textRowCenter(border1);
	blankRow();
	for(int x = 0; x < currMenu.size(); x++) {
	    System.out.println(currMenu.get(x));
	}
	blankRows(3);
	System.out.printf("%s%n%n", border0);
    }
    
    public void blankRow() {
	System.out.printf("%-30s %29s%n", vborder0, vborder0);
    }
    public void blankRows(int numRows) {
	for (int k = 0; k < numRows; k++)
	    blankRow();
    }
    public void textRowCenter(String text) {
	String formatstr;
	double realHalfSpace = (SCREEN_WIDTH - text.length())/2.0;
	int halfSpace = (int) Math.floor(realHalfSpace);
	int leftSpace = halfSpace - 1, rightSpace = halfSpace - 1;
	if (halfSpace < realHalfSpace)
	    rightSpace += 1;
	formatstr = "%-" + leftSpace + "s %s %" + rightSpace + "s%n";
	System.out.printf(formatstr, vborder0, text, vborder0);
    }

    public String getUserInput(String prompt, int cursorRowsUp) {
	String inval;
	String realPrompt = "\u001b[" + cursorRowsUp + "A%-6s%s";
	System.out.printf(realPrompt, vborder0, prompt);
	inval = keyboard.next();
	inval = inval.toLowerCase();
	return inval;
    }
    public String getUserInput() {
	return getUserInput("Your choice: ", 4);
    }

}
