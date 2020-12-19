/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.run;

import org.epifany.pokemonbreedprobabilitycalculator.PokemonApplication;
import org.epifany.pokemonbreedprobabilitycalculator.PokemonManager;
import org.epifany.pokemonbreedprobabilitycalculator.PokemonPresenter;
import org.epifany.pokemonbreedprobabilitycalculator.gui.PokemonGUIContainer;
import org.epifany.pokemonbreedprobabilitycalculator.listener.ButtonListener;
import org.epifany.pokemonbreedprobabilitycalculator.listener.CheckBoxListener;
import org.epifany.pokemonbreedprobabilitycalculator.listener.RadioButtonListener;

/**
 *
 * @author StephenGung
 */
public class PokemonIV_5 {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		PokemonApplication application = new PokemonApplication();
		application.createUI();
		
		// Add presenters and listeners for all containers
		for( PokemonGUIContainer container : application.getContainers()) {
			PokemonPresenter presenter = new PokemonPresenter( new PokemonManager(), container);
			
			ButtonListener bListener = new ButtonListener( presenter);
			container.getCalculateButton().addActionListener( bListener);
			container.getResetButton().addActionListener( bListener);
			
			CheckBoxListener cbListener = new CheckBoxListener( presenter);
			container.getCheckBoxContainer().getHPCheckBox().addItemListener( cbListener);
			container.getCheckBoxContainer().getAtkCheckBox().addItemListener( cbListener);
			container.getCheckBoxContainer().getDefCheckBox().addItemListener( cbListener);
			container.getCheckBoxContainer().getSpACheckBox().addItemListener( cbListener);
			container.getCheckBoxContainer().getSpDCheckBox().addItemListener( cbListener);
			container.getCheckBoxContainer().getSpeCheckBox().addItemListener( cbListener);
			container.getCheckBoxContainer().getEverstoneCheckBox().addItemListener( cbListener);
			container.getCheckBoxContainer().getPercentageCheckBox().addItemListener( cbListener);
			
			RadioButtonListener rbListener = new RadioButtonListener( presenter);
			container.getRBContainer().getHPIncludeRB().addItemListener( rbListener);
			container.getRBContainer().getHPOptionalRB().addItemListener( rbListener);
			container.getRBContainer().getHPExcludeRB().addItemListener( rbListener);
			container.getRBContainer().getAtkIncludeRB().addItemListener( rbListener);
			container.getRBContainer().getAtkOptionalRB().addItemListener( rbListener);
			container.getRBContainer().getAtkExcludeRB().addItemListener( rbListener);
			container.getRBContainer().getDefIncludeRB().addItemListener( rbListener);
			container.getRBContainer().getDefOptionalRB().addItemListener( rbListener);
			container.getRBContainer().getDefExcludeRB().addItemListener( rbListener);
			container.getRBContainer().getSpAIncludeRB().addItemListener( rbListener);
			container.getRBContainer().getSpAOptionalRB().addItemListener( rbListener);
			container.getRBContainer().getSpAExcludeRB().addItemListener( rbListener);
			container.getRBContainer().getSpDIncludeRB().addItemListener( rbListener);
			container.getRBContainer().getSpDOptionalRB().addItemListener( rbListener);
			container.getRBContainer().getSpDExcludeRB().addItemListener( rbListener);
			container.getRBContainer().getSpeIncludeRB().addItemListener( rbListener);
			container.getRBContainer().getSpeOptionalRB().addItemListener( rbListener);
			container.getRBContainer().getSpeExcludeRB().addItemListener( rbListener);
			container.getRBContainer().getEverstoneCheckBox().addItemListener( rbListener);
			container.getRBContainer().getPercentageCheckBox().addItemListener( rbListener);
		}
	}
}
