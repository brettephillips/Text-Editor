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
 * A class that creates the actions for the help menu on the JFrame  
 */
public class HelpListener implements ActionListener
{  
   /*
    * Void method that is to listen to the menuitem, then in return, perform
    * a certain action.
    * @ ActionEvent ae allows the action command to be performed 
    */    
   public void actionPerformed(ActionEvent ae)
   {      
      //If the about item is clicked, then it will perform the following method
      if(ae.getActionCommand().equals("About FileViewer"))
      {
         //Presents a JDialogBox with certain information about the program
         JOptionPane.showMessageDialog(null, "121: FileViewer\nVersion 3.4\nFebruary 11, 2016\nDeveloped by Brett Phillips");
      }      
   }
}
