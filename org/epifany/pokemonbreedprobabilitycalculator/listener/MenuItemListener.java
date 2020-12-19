/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator.listener;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.epifany.pokemonbreedprobabilitycalculator.PokemonApplication;

/**
 *
 * @author StephenGung
 */
public class MenuItemListener implements ActionListener{
	private final PokemonApplication application;
	
	public MenuItemListener( PokemonApplication a){
		application = a;
	}
	
	@Override
	public void actionPerformed( ActionEvent ae){
		String s = ae.getActionCommand();
		if( s.equals("Exit")){
			System.exit(0);
		}
		else if( s.equals("Slot Approach") || s.equals("Marble Approach")){
			CardLayout cl = (CardLayout)application.getPanel().getLayout();
			cl.show(application.getPanel(), s);
		}
	}
}
