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
 * A class that creates the actions for the File and Tools menu on the JFrame  
 */
public class FileListener implements ActionListener
{  
   //JTextArea anotherArea is a private attribute that creates a area that text can be placed on
   private JTextArea anotherArea;
   //JTextField fNT is a private attribute that creates a textfield where the file name will be placed
   private JTextField fNT;
   //FileWriter fW is a private attribute that will write the file when you want to save the text
   private FileWriter fW = null;
   //JFileChooser whichFile is a private attribute that will choose the file that you would like to open
   private JFileChooser whichFile;
   //JFileChooser saveFile is a private attribute that will choose the file that you would like to save
   private JFileChooser saveFile;
   //File theFile is a private attribute that will grab the name of the file you would like to open/save
   private File theFile;
   //JTextArea anotherArea is a private attribute that
   private String directory;
   //JComboBox fonts is a private attribute that stores an array of a list of different font styles
   private JComboBox fonts;
   //JComboBox fontSize is a private attribute that stores an array of a list of different font sizes
   private JComboBox fontSize;
   //JButton setFont is a private attribute that will allow the user to actually change the font style/size by clicking on the button
   private JButton setFont;
   
   /*
    * Constructor that takes in a TextArea, JTextField, JButton, and JComboBoxes.
    * @param area takes in a text area that will be allowed to contain text and assigns it to anotherArea
    * @param fileNameTwo takes in the name of the file and assigns it fNT to be put in the textfield
    * @param _setFont takes in the name of the JButton so that the button can communicate with the JComboBoxes to receieve the data
    * @param _fonts takes in the name of the JComboBox and assigns it to fonts so that it can communicate to the button to know what option style was picked
    * @param _fontSize takes in the name of the JComboBox and assigns it to the fontSize so that it can communicate to the button to know what option size was picked
    */   
   public FileListener(JTextArea area, JTextField fileNameTwo, JButton _setFont ,JComboBox _fonts, JComboBox _fontSize)
   {
      //Assigns area to anotherArea
      anotherArea = area;
      //Assigns fileNameTwo to fNT 
      fNT = fileNameTwo;
      //Assigns _fonts to fonts 
      fonts = _fonts;
      //Assigns _fontSize to fontSize 
      fontSize = _fontSize;
      //Assigns _setFont to setFont 
      setFont = _setFont;
   }
   
   /*
    * Void method which will clear both the textArea and textfield
    * @Exception NullPointerException
    */    
   public void newF()
   {
      //Sets anotherArea to a blank String
      anotherArea.setText("");
      
      //Trys to clear the TextField
      try
      {
         //If the TextField is not null then it will do the follwing action
         if(fNT != null)
         {
            //Sets fNT to a blank String
            fNT.setText("");
         }
      }
      //Catches NullPointerException if there is nothing in the TextField
      catch(NullPointerException npe)
      {
      
      }
      
      //Then sets the file back to null
      whichFile = null;
   }
   
   /*
    * Void method which will open both files from the user's directory
    * @Exception FileNotFound, IOException
    */     
   public void open()
   {
      //Assigns the directory variable to the spot in the directory that you are currently working in
      directory = System.getProperty("user.dir");      
      //Creates a new JFileChooser with the current directory that you are working in
      whichFile = new JFileChooser(directory);
		//Opens the dialog box for you to choose a file
      int finishOpen = whichFile.showOpenDialog(anotherArea);
      //Assigns the selected file to theFile object
      theFile = whichFile.getSelectedFile();
      //Creates a StringBuffer object to hold the data from the file
      StringBuffer sb = new StringBuffer();
      //Sets FileReader to null
      FileReader fr = null;
      //Sets BufferedReader to null
      BufferedReader br = null;
         
      //Trys to open the file for reading
      try
      {
         //Sets the text for the TextField to whatever file you have chosen
         fNT.setText(whichFile.getSelectedFile().getName());         
         //Creates a new FileReader named fr which takes in the file Chosen
         fr = new FileReader(theFile);
         //Creates a new BufferedReader object which takes in the FileReader object
         br = new BufferedReader(fr);
      }
      //Catches a FileNotFoundException if it cannot find or open the file
      catch(FileNotFoundException fnfe)
      {
         
      }
      catch(NullPointerException npe)
      {
      
      }
                
      //Trys to read the file
      try
      {  
         //Reads the first line in the record
         String line = br.readLine();
            
         //While the line is not null it will append each item into the Stringbuffer
         while(line != null)
         {
            //Appends the lines in the record
            sb.append(line + "\n");
            //Reads the next line until there are no more lines to read
            line = br.readLine();
         }
      }
      //Catches an IOException if it cannot read the file
      catch(IOException ioe)
      {
        
      }
      catch(NullPointerException npe)
      {
      
      }
         
      //Sets the TextArea to the data that was held in the StringBuffer
      anotherArea.setText(sb.toString());
   
      //Trys to close the file
      try
      {
         fr.close();
         br.close();
      }
      //Catches an IOException if the readers cannot be closed
      catch(IOException ioe)
      {
            
      }
      catch(NullPointerException npe)
      {
      
      }
   }   
   
   /*
    * Void method which will save any changes that were made to the file
    * @exception Exception
    */   
   public void save()
   {
      //Trys to open the file for writing
      try
      {         
         //Assigns the absolute path of the file to the String saveAnywhere
         String saveAnywhere = saveFile.getSelectedFile().getAbsolutePath();   
         //Creates a FileWriter object named fw which takes in the String
         FileWriter fW = new FileWriter(saveAnywhere);
         //Writes the text in the TextArea
         fW.write(anotherArea.getText());
         //Flushes and then closes the file
         fW.flush();
         fW.close();
      }
      //Catches an exception if the file cannot be closed
      catch(Exception e)
      {

      }      
   }
   
   /*
    * Void method which will save a new document with a new file name set by the user
    * @exception exception
    */    
   public void saveAs()
   {
      //Assigns the directory variable to the spot in the directory that you are currently working in
      directory = System.getProperty("user.dir");
      //Creates a new JFileChooser with the current directory that you are working in
      saveFile = new JFileChooser(directory);
		//Opens the dialog box for you to create a name for the file you will save
      int finishSave = saveFile.showSaveDialog(anotherArea);     
      
      //Trys to open the file for writing
      try
      {
         //Assigns the absolute path of the file to the String saveAnywhere
         String saveAnywhere = saveFile.getSelectedFile().getAbsolutePath();   
         //Creates a FileWriter object named fw which takes in the String
         FileWriter fW = new FileWriter(saveAnywhere);
         //Writes the text in the TextArea
         fW.write(anotherArea.getText());
         //Flushes and then closes the file
         fW.flush();
         fW.close();         
      }         
      //Catches an exception if the file cannot be closed
      catch(Exception e)
      {

      } 
   }   
   
   /*
    * Void method which will terminate the program
    */   
   public void exit()
   {
      //Terminates the program
      System.exit(0);
   } 
   
   /*
    * Void method which will set the different font types and styles in the TextArea
    */
   public void setFont()
   {
      //Taking the current font style, casting it as a String, and assigning it to checkFontStyle 
      String checkFontStyle = (String)fonts.getSelectedItem(); 
      //Taking the current font size, casting it as a String and then parsing it as an int, and assigning it to checkFontSize 
      int checkFontSize = Integer.parseInt((String)fontSize.getSelectedItem());
      
      //Checks if the font style is regular
      if(checkFontStyle.equals("Regular"))
      {
         //Sets the font of the area to the following parameters  
         anotherArea.setFont(new Font("Arial", Font.PLAIN, checkFontSize));
      }
      //Checks if the font style is bold
      else if(checkFontStyle.equals("Bold"))
      {
         //Sets the font of the area to the following parameters  
         anotherArea.setFont(new Font("Arial", Font.BOLD, checkFontSize));
      }      
      //Checks if the font style is italic
      else if(checkFontStyle.equals("Italic"))
      {
         //Sets the font of the area to the following parameters  
         anotherArea.setFont(new Font("Arial", Font.ITALIC, checkFontSize));
      }       
      //Checks if the font style is bold italic
      else if(checkFontStyle.equals("Bold Italic"))
      {
         //Sets the font of the area to the following parameters  
         anotherArea.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, checkFontSize));
      }       
   }
   
   /*
    * Void method which will count all of the words in the TextArea
    */    
   public void wordCount()
   {              
      //Creates a local variable that will be used to count the empty strings
      int counter = 0;
      //Creates an array that will split the text everytime it comes upon a new line
      String[] countItems = anotherArea.getText().split("\n");
      //A for loop that instantiates a an int i that will increment until it is equal to the size of the array
      for(int i = 0; i < countItems.length; i++)
      {
         //If the position in the array equals a empty String, then it will perform the follwing action
         if(countItems[i].equals(""))
         {
            //Increments counter
            counter++;
         }
      }
      //Creates an array that will split the text everytime it comes upon a space
      String[] countItemsTwo = anotherArea.getText().split(" ");
      //A for loop that instantiates a an int i that will increment until it is equal to the size of the array
      for(int i = 0; i < countItemsTwo.length; i++)
      {
         //If the position in the array equals a empty String, then it will perform the follwing action
         if(countItemsTwo[i].equals(""))
         {
            //Increments counter
            counter++;
         }
      }      
      //Assigns the length of the sum of both the arrays to result  
      int result = (countItems.length + countItemsTwo.length) - counter;
      //Presents a dialog box with the amount of words that were in the TextArea
      JOptionPane.showMessageDialog(null, "Found " + (result - 1) +" Words");
   }      
   
   /*
    * Void method that is to listen to the buttons, comboboxes, and menuitems, then in return, perform
    * a certain action.
    * @ ActionEvent ae allows the action commands to be performed 
    */      
   public void actionPerformed(ActionEvent ae)
   {      
      //If the newF item is clicked, then it will perform the following method
      if(ae.getActionCommand().equals("New"))
      {
         newF();
      }  
            
      //If the open item is clicked, then it will perform the following method
      if(ae.getActionCommand().equals("Open..."))
      {
         open();
      }      
      
      //If the save item is clicked, then it will perform the following method
      if(ae.getActionCommand().equals("Save"))
      {
         save();
      }      
      
      //If the saveAs item is clicked, then it will perform the following method
      if(ae.getActionCommand().equals("Save as..."))
      {
         saveAs();
      }
      
      //If the exit item is clicked, then it will perform the following method
      if(ae.getActionCommand().equals("Exit"))
      {
         exit();
      } 
      
      //If the setFont button is clicked, then it will perform the following method
      if(ae.getActionCommand().equals("Set Font"))
      {
         setFont();
      }

      //If the wordCount item is clicked, then it will perform the following method
      if(ae.getActionCommand().equals("Word Count"))
      {
         wordCount();
      }                          
   }
}