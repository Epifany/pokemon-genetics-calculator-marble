/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import org.epifany.pokemonbreedprobabilitycalculator.PokemonPresenter;

/**
 * @author Stephen Gung
 */
public class RadioButtonListener implements ItemListener {
	private final PokemonPresenter presenter;
	
	public RadioButtonListener( PokemonPresenter p){
		presenter = p;
	}
	
	@Override
	public void itemStateChanged( ItemEvent ie){
		AbstractButton button = (AbstractButton)ie.getSource();
		String command = button.getActionCommand();
		boolean flag = ( ie.getStateChange() == ItemEvent.SELECTED);
		System.out.println( command + " " + flag);
		if( flag){
			presenter.updateRBState( command, flag);
			presenter.updateRBStateKey();
			presenter.updateRBStateProbability();
		}
	}
}
