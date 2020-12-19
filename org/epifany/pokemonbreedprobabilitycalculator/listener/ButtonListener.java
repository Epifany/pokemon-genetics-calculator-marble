/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.epifany.pokemonbreedprobabilitycalculator.PokemonPresenter;

/**
 * @author Stephen Gung
 */
public class ButtonListener implements ActionListener {
	private final PokemonPresenter presenter;
	
	public ButtonListener( PokemonPresenter p){
		presenter = p;
	}
	
	@Override
	public void actionPerformed( ActionEvent ae){
		String s = ae.getActionCommand();
		switch (s) {
			case "Calculate":
				presenter.reset();
				
				presenter.calculate();
				//presenter.reaffirmCBEverstone();
				presenter.updateFlagStateKey();
				presenter.updateFlagStateProbability();
				//presenter.readdirmRBEverstone();
				presenter.updateRBStateKey();
				presenter.updateRBStateProbability();
				break;
			case "Reset":
				presenter.reset();
				presenter.resetGUI();
				break;
		}
	}
	
}
