/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator;

import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import org.epifany.pokemonbreedprobabilitycalculator.gui.PokemonGUIContainer;

/**
 * @author Stephen Gung
 */
public class PokemonApplication {
	private JFrame frame;
	private JMenuItem exit;
	private JMenuItem slotApproach;
	private JMenuItem marbleApproach;
	
	private final List<PokemonGUIContainer> slotContainers;
	private final List<PokemonGUIContainer> marbleContainers;
	
	private JPanel panel;
	
	public PokemonApplication(){
		slotContainers = new ArrayList();
		marbleContainers = new ArrayList();
		// 3 should be enough calculators...
		for( int i = 0; i < 3; i++){
			slotContainers.add( new PokemonGUIContainer());
			marbleContainers.add( new PokemonGUIContainer());
		}
	}
	
	public void createUI(){
			// Creating frame
			frame = new JFrame( "Pokemon Genetics Probability Calculator (Gen 6) 2.0 - https://epifany.github.io");
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
			frame.setResizable( false);

			// Menu Bar for our frame
			JMenuBar menuBar = new JMenuBar();
			// Create a File menu
			JMenu fileMenu = createJMenu( "File", KeyEvent.VK_F);
			exit = createJMenuItem( "Exit", 0, 0);
			fileMenu.add( exit);
			menuBar.add( fileMenu);
			
			// Create a Options menu
			JMenu optionsMenu = createJMenu( "Options", KeyEvent.VK_O);
			slotApproach = createJMenuItem("Slot Approach", 0, 0);
			marbleApproach = createJMenuItem("Marble Approach", 0, 0);
			optionsMenu.add(slotApproach);
			optionsMenu.add(marbleApproach);
			menuBar.add( optionsMenu);
			
			// Create a Help menu
			//JMenu helpMenu = createJMenu( "Help", KeyEvent.VK_H);
			//menuBar.add( helpMenu);
			// Set this bar for our frame
			frame.setJMenuBar( menuBar);
			
			JTabbedPane slotPane = createTabbedPane(slotContainers, "Slot");
			JTabbedPane marblePane = createTabbedPane(marbleContainers, "Marble");
			
			panel = new JPanel( new CardLayout());
			panel.add(slotPane, "Slot Approach");
			panel.add(marblePane, "Marble Approach");

			frame.add( panel);
			frame.pack();
			frame.setVisible( true);
	}
	
	public JMenuItem getExitMI(){	return exit;	}
	public JMenuItem getSlotApproachMI(){	return slotApproach;	}
	public JMenuItem getMarbleApproachMI(){	return marbleApproach;	}
	
	public List<PokemonGUIContainer> getSlotContainers(){
		return slotContainers;
	}
	public List<PokemonGUIContainer> getMarbleContainers(){
		return marbleContainers;
	}
	public JPanel getPanel(){	return panel;	}
	
	private JTabbedPane createTabbedPane( List<PokemonGUIContainer> containers, String name){
		JTabbedPane tabbedPane = new JTabbedPane();
		for( int i = 0; i < containers.size(); i++){
			JPanel temp = containers.get(i).createDefaultPanel();
			tabbedPane.addTab(name + " " + (i+1), temp);
		}
		return tabbedPane;
	}
	
	
	// Helper method
	private JMenu createJMenu( String text, int key){
		JMenu menu = new JMenu( text);
		menu.setMnemonic( key);
		return menu;
	}
	
	// Helper method
	private JMenuItem createJMenuItem( String text, int key, int action){
		JMenuItem menuItem = new JMenuItem( text);
		if( key != 0 && action != 0)
			menuItem.setAccelerator( (KeyStroke.getKeyStroke( key, action)));
		return menuItem;
	}
}