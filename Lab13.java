import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Lab13 extends JFrame 
{
	//constants for set up of note taking area
   public static final int WIDTH = 600;
   public static final int HEIGHT = 300;
   public static final int LINES = 13;
   public static final int CHAR_PER_LINE = 45;

	//objects in GUI
	private JTextArea theText;		//area to take notes
  	private JMenuBar mBar;	//horizontal menu bar
   private JPanel textPanel;	//panel to hold scrolling text area 
	private JMenu notesMenu;	//vertical menu with choices for notes
	
	//****THESE ITEMS ARE NOT YET USED.  YOU WILL BE CREATING THEM IN THIS LAB
	private JMenu viewMenu;	//vertical menu with choices for views	
	private JMenu lafMenu;  //vertical menu with look and feel
	private JMenu sbMenu;	//vertical menu with scroll bar option
	private JScrollPane scrolledText;//scroll bars   

	//default notes
	private String note1 = "No Note 1.";
   private String note2 = "No Note 2.";

 	/**constructor*/ 
	public Lab13()
   {
		//create a closeable JFrame with a specific size
	 	super("Lab13");		
		setSize(WIDTH, HEIGHT);
      setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      		
		//get contentPane and set layout of the window
      Container contentPane = getContentPane();
      contentPane.setLayout(new BorderLayout());

		//creates the vertical menus 
		createNotes();
		createViews();
		
		//creates horizontal menu bar and 
		//adds vertical menus to it
      mBar = new JMenuBar();
      mBar.add(notesMenu); // Add the NotesMenu to the manu bar
	  // Task 1 step 4
      mBar.add(viewMenu);
	  // Add the viewMenu to the menu bar here, similar to add notesMenu.
      setJMenuBar(mBar);

		//creates a panel to take notes on
      textPanel = new JPanel();
      textPanel.setBackground(Color.blue);
      theText = new JTextArea(LINES, CHAR_PER_LINE);
      theText.setBackground(Color.white);
	  // Task 2 step 1
 	  //****CREATE A JScrollPane object scrolledText like
	  scrolledText = new JScrollPane(theText);
	  //****CHANGE THE LINE BELOW BY PASSING IN scrolledText, instead of theText
	  textPanel.add(scrolledText);
      contentPane.add(textPanel, BorderLayout.CENTER);
	 }

	 /**creates vertical menu associated with Notes 
	 menu item on menu bar*/
    public void createNotes()
	 {
	 	notesMenu = new JMenu("Notes");
		JMenuItem item;
		
		item = new JMenuItem("Save Note 1");
		item.addActionListener(new MenuListener());
		notesMenu.add(item);

		item = new JMenuItem("Save Note 2");
		item.addActionListener(new MenuListener());
		notesMenu.add(item);

		item = new JMenuItem("Open Note 1");
		item.addActionListener(new MenuListener());
		notesMenu.add(item);

		item = new JMenuItem("Open Note 2");
		item.addActionListener(new MenuListener());
		notesMenu.add(item);

		item = new JMenuItem("Clear");
		item.addActionListener(new MenuListener());
		notesMenu.add(item);

		item = new JMenuItem("Exit");
		item.addActionListener(new MenuListener());
      notesMenu.add(item);
	}
	
	/**creates vertical menu associated with Views 
	menu item on the menu bar*/ 
	public void createViews()
	{
		viewMenu = new JMenu("Views");

		createLookAndFeel();
		lafMenu.addActionListener(new MenuListener());
		viewMenu.add(lafMenu);
		
                createScrollBars();
		lafMenu.addActionListener(new MenuListener());
		viewMenu.add(lafMenu);
		// Task 1 step 3.
		// call createScrollBars() method the similar way
		// of calling createLookAndFeel();
		// using sbMenu instead of lafMenu
	}

	/**creates the look and feel submenu */
	public void createLookAndFeel()
	{
		lafMenu = new JMenu("Look and Feel"); // create Look and feel menu 
		JMenuItem item;
		
		item = new JMenuItem("Metal"); // breate a menu item
		item.addActionListener(new MenuListener()); // add action listener to the item
		lafMenu.add(item); // add the item to the menu

		item = new JMenuItem("Motif");
		item.addActionListener(new MenuListener());
		lafMenu.add(item);

		item = new JMenuItem("Windows");
		item.addActionListener(new MenuListener());
		lafMenu.add(item);
	}

	/**creates the scroll bars submenu*/
	public void createScrollBars()
	{
                lafMenu = new JMenu("Scroll Bars"); // create Look and feel menu 
		JMenuItem item;
		
		item = new JMenuItem("Never"); // breate a menu item
		item.addActionListener(new MenuListener()); // add action listener to the item
		lafMenu.add(item); // add the item to the menu

		item = new JMenuItem("Always");
		item.addActionListener(new MenuListener());
		lafMenu.add(item);

		item = new JMenuItem("As Needed");
		item.addActionListener(new MenuListener());
		lafMenu.add(item);
		// Task 1 step 2.
		// code createScrollBars() method the similar way
		// of createLookAndFeel();
		// using sbMenu instead of lafMenu
	}

	private class MenuListener implements ActionListener
	{
	
 	   public void actionPerformed(ActionEvent e)
  		{
      	String actionCommand = e.getActionCommand();
       	if (actionCommand.equals("Save Note 1"))
            note1 = theText.getText();
        	else if (actionCommand.equals("Save Note 2"))
            note2 = theText.getText();
        	else if (actionCommand.equals("Clear"))
            theText.setText("");
        	else if (actionCommand.equals("Open Note 1"))
            theText.setText(note1);
        	else if (actionCommand.equals("Open Note 2"))
            theText.setText(note2);
        	else if (actionCommand.equals("Exit"))
            System.exit(0);
        //****ADD 6 BRANCHES TO THE ELSE-IF STRUCTURE
		  //****TO ALLOW ACTION TO BE PERFORMED FOR EACH
		  //****MENU ITEM YOU HAVE CREATED
		  else if (actionCommand.equals("Metal")) 
        	{
				try // 
				{
					UIManager.setLookAndFeel(
						"javax.swing.plaf.metal.MetalLookAndFeel");
					// Any components that have already been created need to be updated
					SwingUtilities.updateComponentTreeUI( 
						getContentPane());
				}
				catch (Exception except)
				{
					System.out.println(
						"Could not load the Metal look and feel");
				}
			}
                  else if (actionCommand.equals("Motif"))
                  {
                      try //
                      {
                          UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                          SwingUtilities.updateComponentTreeUI(getContentPane());
                      }
                      catch (Exception except)
                      {
                          System.out.println("Could not load the Motif look and feel");
                      }
                  }
                  
                  else if (actionCommand.equals("Windows"))
                  {
                      try //
                      {
                          UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                          SwingUtilities.updateComponentTreeUI(getContentPane());
                      }
                      catch (Exception except)
                      {
                          System.out.println("Could not load the Windows look and feel");
                      }
                  }
               /* else if (actionCommand.equals("Motif"))
                try // 
				{
					UIManager.setLookAndFeel(
						"com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                                         UIManager.setLookAndFeel(
						"com.sun.java.swing.plaf.Windows.WindowsLookAndFeel");       
					// Any components that have already been created need to be updated
					SwingUtilities.updateComponentTreeUI( 
						getContentPane());
				}
				catch (Exception except)
				{
					System.out.println(
						"Could not load the Motif look and feel");
				}
			}
                */
			// Task 2 step 2 -  add action for Motif style
			// by using com.sun.java.swing.plaf.motif.MotifLookAndFeel
			// add action for Window style 
			// by using com.sun.java.swing.plaf.windows.WindowsLookAndFeel
			
		else if (actionCommand.equals("Never"))
                    
                        {
        		scrolledText.setHorizontalScrollBarPolicy(
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				scrolledText.setVerticalScrollBarPolicy(
					JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				SwingUtilities.updateComponentTreeUI(
					getContentPane());
			}
                else if  (actionCommand.equals("Always"))
                    
                        {
        		scrolledText.setHorizontalScrollBarPolicy(
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scrolledText.setVerticalScrollBarPolicy(
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				SwingUtilities.updateComponentTreeUI(
					getContentPane());
			}
                    else if  (actionCommand.equals("As Needed"))
                    
                        {
        		scrolledText.setHorizontalScrollBarPolicy(
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scrolledText.setVerticalScrollBarPolicy(
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				SwingUtilities.updateComponentTreeUI(
					getContentPane());
			}
			// Task 2 step 3 -  add action for Always, and As Needed
			// by using constant JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS and 
			// JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
			// add action for "As Needed" style 
			// by using JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
			// and JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			
        	else
            theText.setText("Error in memo interface");
    	}
	}

  	public static void main(String[] args)
  	{
   	Lab13 gui = new Lab13();
      gui.setVisible(true);
   }
}
