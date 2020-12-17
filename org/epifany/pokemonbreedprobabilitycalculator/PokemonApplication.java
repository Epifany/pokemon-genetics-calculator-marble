/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator;

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
	
	private final List<PokemonGUIContainer> containers;
	
	public PokemonApplication(){
		containers = new ArrayList();
		// 3 should be enough calculators...
		for( int i = 0; i < 3; i++){
			PokemonGUIContainer gui = new PokemonGUIContainer();
			containers.add(gui);
		}
	}
	
	public void createUI(){
			// Creating frame
			frame = new JFrame( "Pokemon Genetics Probability Calculator (Gen 6) Marble 2.0 - https://epifany.github.io");
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
			frame.setResizable( false);

			// Menu Bar for our frame
			JMenuBar menuBar = new JMenuBar();
			// Create a File menu
			JMenu fileMenu = createJMenu( "File", KeyEvent.VK_F);
			fileMenu.add( createJMenuItem( "Exit", 0, 0));
			menuBar.add( fileMenu);
			// Create a Options menu
			//JMenu optionsMenu = createJMenu( "Options", KeyEvent.VK_O);
			//menuBar.add( optionsMenu);
			// Create a Help menu
			//JMenu helpMenu = createJMenu( "Help", KeyEvent.VK_H);
			//menuBar.add( helpMenu);
			// Set this bar for our frame
			frame.setJMenuBar( menuBar);
			
			//Dimension d = new Dimension( 600, 500);
			
			JTabbedPane tabbedPane = new JTabbedPane();
			for( int i = 0; i < containers.size(); i++){
				JPanel panel = containers.get(i).createDefaultPanel();
				//panel.setPreferredSize(d);
				tabbedPane.addTab("Calculator " + (i+1), panel);
			}

			frame.add( tabbedPane);
			frame.pack();
			frame.setVisible( true);
	}
	
	public List<PokemonGUIContainer> getContainers(){
		return containers;
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