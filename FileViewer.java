/*
 * @author <Brett Phillips>
 * @version 2/11/2016
 * Course: ISTE - 121.02
 * HW: #2 GUI and I/O Application
 */ 
 
//Importing packages needed to create the program 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/*
 * A class that creates a text editor by creating a JFrame containing 
 * labels, textfields, comboboxes, menuitems, and buttons with certain actions.
 */
public class FileViewer extends JFrame
{      
   //JTextArea area is a private attribute that creates an area that text can be placed on and puts it on the frame
   private JTextArea area = null;
   //JMenuBar menuBar is a private attribute that creates a menu bar, which will contain menus, that are placed on the frame
   private JMenuBar menuBar = null;
   //JMenu file is a private attribute that creates a menu that is located on the menu bar in the frame
   private JMenu file = null;
   //JMenu tools is a private attribute that creates a menu that is located on the menu bar in the frame
   private JMenu tools = null;
   //JMenu help is a private attribute that creates a menu that is located on the menu bar in the frame
   private JMenu help = null;
   //JMenuItem newF is a private attribute that creates a menu item that is located in the file menu in the frame, which will clear the text
   private JMenuItem newF = null;
   //JMenuItem open is a private attribute that creates a menu item that is located in the file menu in the frame, which will open a file
   private JMenuItem open = null;
   //JMenuItem save is a private attribute that creates a menu item that is located in the file menu in the frame, which will save the file with the current name
   private JMenuItem save = null;
   //JMenuItem saveAs is a private attribute that creates a menu item that is located in the file menu in the frame, which will save the file with a new name
   private JMenuItem saveAs = null;
   //JMenuItem exit is a private attribute that creates a menu item that is located in the file menu in the frame, which will terminate the program
   private JMenuItem exit = null;
   //JMenuItem wordCount is a private attribute that creates a menu item that is located in the tools menu in the frame, which will count the amount of words located in the document
   private JMenuItem wordCount = null;
   //JMenuItem about is a private attribute that creates a menu item that is located in the file menu in the frame, which will tell you about the program
   private JMenuItem about = null;
   //JComboBox fonts is a private attribute that creates a dropdown menu from which font styles can be selected
   private JComboBox fonts = null;
   //JComboBox fontSize is a private attribute that creates a dropdown menu from which font sizes can be selected
   private JComboBox fontSize = null;
   //JTextField fileNameTwo is a private attribute that creates a textfield where the file name will be placed
   private JTextField fileNameTwo = null;
   //JPanel panel is a private attribute that creates an area for the objects to be placed in
   private JPanel panel = null;
   //JPanel panelTwo is a private attribute that creates an area for the objects to be placed in
   private JPanel panelTwo = null;
   //JTextArea anotherArea is a private attribute that creates a area that text can be placed on
   private JButton setFont = null;
   //Creates an array named items which contains font styles
   private String [] items = {"Regular", "Italic", "Bold", "Bold Italic"};
   //Creates an array named size which contains font sizes
   private String [] size = {"10", "12", "15"};
   
   //Main method
   public static void main(String [] args)
   {
      //Creates a new object called file
      FileViewer file = new FileViewer();
   }   
   
   /*
    * Constructor that creates a JFrame and adds menuitems, TextFields, lables, comboboxes, and
    * buttons. 
    */    
   public FileViewer()
   {
      //Creates a JFrame object named frame
      JFrame frame = new JFrame();
      //Sets the title of the frame
      frame.setTitle("File Viewer");
      //Sets the size of the frame
      frame.setSize(1000, 700);
      //Sets the frame to close when the exit button is hit on the frame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Makes a new JTextArea called area with 15 coloumns and 20 rows
      area = new JTextArea(15, 20);
      //Makes a new JTextField called fileNameTwo
      fileNameTwo = new JTextField(30);
      //Adds the JTextArea to the frame plus a scrollable item
      frame.add(new JScrollPane(area));
      
      //Creates a new JComboBox named size
      fontSize = new JComboBox(size);
      //Sets the selected index to the first position in the array
      fontSize.setSelectedIndex(0);      
      //Creates a new JComboBox named fonts
      fonts = new JComboBox(items);
      //Sets the selected index to the first position in the array
      fonts.setSelectedIndex(0);      
      
      //Creates a new fileListener object named fL
      FileListener fL = new FileListener(area, fileNameTwo, setFont, fonts, fontSize);
      //Creates a new JMenuBar named menuBar
      menuBar = new JMenuBar(); 
      //Creates a new JMenu named file
      file = new JMenu("File");
      //Sets the mnemonic for file to be the 'f' character
      file.setMnemonic('F');
      //Creates a new JMenuItem named newF
      newF = new JMenuItem("New");
      //Creates a new JMenuItem named open
      open = new JMenuItem("Open...");
      //Creates a new JMenuItem named open
      save = new JMenuItem("Save");
      //Creates a new JMenuItem named save
      saveAs = new JMenuItem("Save as...");
      //Creates a new JMenuItem named saveAs
      saveAs.setMnemonic('a');
      //Sets the mnemonic for saveAs to be the 'a' character
      exit = new JMenuItem("Exit"); 
      //Adds the JMenuItem to the file menu
      file.add(newF);
      //Adds an action to the newF menuItem
      newF.addActionListener(fL);
      //Adds the JMenuItem to the file menu
      file.add(open);
      //Adds an action to the open menuItem
      open.addActionListener(fL);
      //Adds a menu separator
      file.addSeparator();
      //Adds the JMenuItem to the file menu
      file.add(save);
      //Adds an action to the save menuItem
      save.addActionListener(fL);
      //Adds the JMenuItem to the file menu
      file.add(saveAs);
      //Adds an action to the saveAs menuItem
      saveAs.addActionListener(fL);
      //Adds a menu separator
      file.addSeparator();
      //Adds the JMenuItem to the file menu
      file.add(exit);
      //Adds an action to the exit menuItem
      exit.addActionListener(fL);
      //Adds the file menu to the menu bar
      menuBar.add(file);     
      
      //Creates a new HelpListener object named hL
      HelpListener hL = new HelpListener();
      //Creates a new JMenu named tools
      tools = new JMenu("Tools");
      //Sets the mnemonic for tools to be the 't' character
      tools.setMnemonic('T');
      //Creates a new JMenuItem named wordCount
      wordCount = new JMenuItem("Word Count"); 
      //Adds the JMenuItem to the file menu
      tools.add(wordCount);
      //Adds an action to the wordCount menuItem
      wordCount.addActionListener(fL);      
      //Adds the tools menu to the menu bar
      menuBar.add(tools);     
       
      //Creates a new JMenu named help
      help = new JMenu("Help");
      //Sets the mnemonic for help to be the 'h' character
      help.setMnemonic('H');
      //Creates a new JMenuItem named about
      about = new JMenuItem("About FileViewer"); 
      //Adds the JMenuItem to the file menu
      help.add(about);
      //Adds an action to the about menuItem
      about.addActionListener(hL);      
      //Adds the help menu to the menu bar
      menuBar.add(help);
      
      //Sets the menuBar onto the frame
      frame.setJMenuBar(menuBar);
      
      //Creates a new panel named panel
      panel = new JPanel();
      //Sets the layout of the panel to FlowLayout
      panel.setLayout(new FlowLayout());
      
      //Creates a new JLabel named fileName which is right aligned
      JLabel fileName = new JLabel("File Name:", SwingConstants.RIGHT);
      //Adds the label to the panel
      panel.add(fileName);
      //Adds the textField created earlier to the panel
      panel.add(fileNameTwo);
      
      //Adds the panel to the south of the frame
      frame.add(panel, BorderLayout.SOUTH);
      
      //Creates a new panel named panelTwo
      panelTwo = new JPanel();
      //Sets the layout of the panel to GridLayout ( zero rows, one column)
      panelTwo.setLayout(new GridLayout(0, 1));
      
      //Adds the combobox to panelTwo
      panelTwo.add(fonts);
      //Adds the combobox to panelTwo
      panelTwo.add(fontSize);
      //Creates a new JButton named setFont
      JButton setFont = new JButton("Set Font");
      //Sets the mnemonic for tools to be the 't' character
      setFont.setMnemonic('t');
      //Adds an action to the newF menuItem
      setFont.addActionListener(fL);

      //Adds the button, setFont, to panelTwo
      panelTwo.add(setFont);
      
      //Adds the panelTwo to the east of the frame
      frame.add(panelTwo, BorderLayout.EAST);
            
      //Sets the frame visible
      frame.setVisible(true);      
   }
}