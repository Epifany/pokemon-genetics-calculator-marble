/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.epifany.pokemon.PokemonHelper;
import org.epifany.pokemonbreedprobabilitycalculator.PokemonCommand;

/**
 * This class is the "master" container, consolidating all the other containers together
 * @author Stephen Gung
 */
public class PokemonGUIContainer {
	private final InputContainer input_a;
	private final InputContainer input_b;
	
	private final JButton calculateButton;
	private final JButton resetButton;
	
	private final CheckBoxContainer checkBoxContainer;
	private final HighLowContainer highlow_a;
	private final HighLowContainer highlow_b;
	private final HighLowContainer highlow_ab;
	private final JLabel highlow_perfect;
	private final RadioButtonContainer radioButtonContainer;
	private final JLabel rbLabel;
	
	public PokemonGUIContainer(){
		String[] spnrValues = { "0", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "11", "12", "13", "14", "15",
			"16", "17", "18", "19", "20", "21", "22", "23",
			"24", "25", "26", "27", "28", "29", "30", "31"
		};
		String[] itemValues = { PokemonCommand.ITEM_NONE_TEXT,
			PokemonCommand.ITEM_DESTINYKNOT_TEXT,
			PokemonCommand.ITEM_EVERSTONE_TEXT,
			PokemonCommand.ITEM_POWERWEIGHT_TEXT,
			PokemonCommand.ITEM_POWERBRACER_TEXT,
			PokemonCommand.ITEM_POWERBELT_TEXT,
			PokemonCommand.ITEM_POWERLENS_TEXT,
			PokemonCommand.ITEM_POWERBAND_TEXT,
			PokemonCommand.ITEM_POWERANKLET_TEXT
		};
		// Initialize Pokemon A Input guis
		input_a = new InputContainer( spnrValues, itemValues,
			PokemonHelper.HP_FULLTEXT + ":",
			PokemonHelper.ATK_FULLTEXT + ":",
			PokemonHelper.DEF_FULLTEXT + ":",
			PokemonHelper.SPA_FULLTEXT + ":",
			PokemonHelper.SPD_FULLTEXT + ":",
			PokemonHelper.SPE_FULLTEXT + ":");
		// Just for this program I set the initial index to 1
		input_a.getItemCB().setSelectedIndex(1);
		// Initialize Pokemon B Input guis
		input_b = new InputContainer( spnrValues, itemValues,
			PokemonHelper.HP_FULLTEXT + ":",
			PokemonHelper.ATK_FULLTEXT + ":",
			PokemonHelper.DEF_FULLTEXT + ":",
			PokemonHelper.SPA_FULLTEXT + ":",
			PokemonHelper.SPD_FULLTEXT + ":",
			PokemonHelper.SPE_FULLTEXT + ":");
		// Just for this program I set the initial index to 2
		input_b.getItemCB().setSelectedIndex(2);
		calculateButton = new JButton("Calculate");
		resetButton = new JButton("Reset");
		checkBoxContainer = new CheckBoxContainer(
			PokemonHelper.HP_TEXT,
			PokemonHelper.ATK_TEXT,
			PokemonHelper.DEF_TEXT,
			PokemonHelper.SPA_TEXT,
			PokemonHelper.SPD_TEXT,
			PokemonHelper.SPE_TEXT,
			"Correct Nature?");
		highlow_a = new HighLowContainer("A");
		highlow_b = new HighLowContainer("B");
		highlow_ab = new HighLowContainer("A & B");
		highlow_perfect = new JLabel("0 / 0");
		radioButtonContainer = new RadioButtonContainer( "Perfect", "Not perfect", "Either", "Correct Nature?");
		rbLabel = new JLabel("0 / 0");
		rbLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	// Default panel
	public JPanel createDefaultPanel(){
		JPanel panel = new JPanel( new BorderLayout());
		panel.setBorder( BorderFactory.createEtchedBorder());
		// Creating the input panel
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout( new BoxLayout( inputPanel, BoxLayout.Y_AXIS));
		inputPanel.setBorder( BorderFactory.createEtchedBorder());
		inputPanel.add( input_a.createInputPanel("Pokemon (A)"));
		inputPanel.add( input_b.createInputPanel("Pokemon (B)"));
		calculateButton.setAlignmentX( Component.CENTER_ALIGNMENT);
		inputPanel.add( calculateButton);
		
		// Creating the output panel
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout( new BoxLayout( outputPanel, BoxLayout.Y_AXIS));
		outputPanel.setBorder( BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Offspring Goal"));
		JPanel temp = createRadioButtonPanel( "Perfect Stat Probabilities");
		temp.setAlignmentX(Component.LEFT_ALIGNMENT);
		// These numbers were determined after finding out the size of this panel through debugging...
		temp.setPreferredSize( new Dimension( 335, 225));
		outputPanel.add( temp);
		temp = createCheckBoxPanel( "Comparisons For The Curiously Inclined");
		temp.setAlignmentX(Component.LEFT_ALIGNMENT);
		// These numbers were determined after finding out the size of this panel through debugging...
		temp.setPreferredSize( new Dimension( 355, 310));
		outputPanel.add( temp);
		
		// Note: RadioButton and CheckBox panels are the largest sized "active" panels
		// So the overall preferred size of the application was built around those (size) numbers
		
		// Consolidate our panels
		panel.add( inputPanel, BorderLayout.LINE_START);
		panel.add( outputPanel, BorderLayout.LINE_END);
		panel.add( resetButton, BorderLayout.PAGE_END);
		return panel;
	}
	
	private JPanel createCheckBoxPanel( String title){
		highlow_a.setLeftAlignment();
		highlow_b.setLeftAlignment();
		highlow_ab.setLeftAlignment();
		// A panel for our messages
		JPanel msgPanel = new JPanel();
		msgPanel.setLayout( new BoxLayout( msgPanel, BoxLayout.Y_AXIS));
		//msgPanel.setBorder( BorderFactory.createEtchedBorder());
		msgPanel.add( highlow_a.getHighInclusiveMsg());
		msgPanel.add( highlow_b.getHighInclusiveMsg());
		msgPanel.add( highlow_ab.getHighInclusiveMsg());
		msgPanel.add( highlow_a.getHighExclusiveMsg());
		msgPanel.add( highlow_b.getHighExclusiveMsg());
		msgPanel.add( highlow_ab.getHighExclusiveMsg());
		msgPanel.add( highlow_a.getLowInclusiveMsg());
		msgPanel.add( highlow_b.getLowInclusiveMsg());
		msgPanel.add( highlow_ab.getLowInclusiveMsg());
		msgPanel.add( highlow_a.getLowExclusiveMsg());
		msgPanel.add( highlow_b.getLowExclusiveMsg());
		msgPanel.add( highlow_ab.getLowExclusiveMsg());
		msgPanel.add( new JLabel("Perfect stats: "));
		// A panel for where we'll display our results
		JPanel reportPanel = new JPanel();
		reportPanel.setLayout( new BoxLayout( reportPanel, BoxLayout.Y_AXIS));
		//reportPanel.setBorder( BorderFactory.createEtchedBorder());
		// Hardcoded preferred size...
		reportPanel.setPreferredSize( new Dimension( 160, 210));
		reportPanel.add( highlow_a.getHighInclusive());
		reportPanel.add( highlow_b.getHighInclusive());
		reportPanel.add( highlow_ab.getHighInclusive());
		reportPanel.add( highlow_a.getHighExclusive());
		reportPanel.add( highlow_b.getHighExclusive());
		reportPanel.add( highlow_ab.getHighExclusive());
		reportPanel.add( highlow_a.getLowInclusive());
		reportPanel.add( highlow_b.getLowInclusive());
		reportPanel.add( highlow_ab.getLowInclusive());
		reportPanel.add( highlow_a.getLowExclusive());
		reportPanel.add( highlow_b.getLowExclusive());
		reportPanel.add( highlow_ab.getLowExclusive());
		reportPanel.add( highlow_perfect);
		// Put all our panels together
		JPanel panel = new JPanel( new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		// Row 0, column 0, width 10
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 10;
		panel.add( new JLabel("Select checkmarks you want to include for comparisons:"), c);
		// Row 1, column 0, width 10
		c.gridy = 1;
		panel.add( checkBoxContainer.createMyDefaultPanel(), c);
		// Row 2, column 0, width 8
		c.gridy = 2;
		c.gridwidth = 8;
		panel.add( msgPanel, c);
		// Row 2, column 8, width 10
		c.gridx = 8;
		c.gridwidth = 10;
		panel.add( reportPanel, c);
		
		if( title != null){
			panel.setBorder( BorderFactory.createTitledBorder(title));
		}
		return panel;
	}
	
	private JPanel createRadioButtonPanel( String title){
		JPanel panel = new JPanel( new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Row 0, column 0, width 10
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 10;
		panel.add( new JLabel( "Select the condition (for each stat) you want to consider:"), c);
		// Row 1, column 0, width 8
		c.gridy = 1;
		c.gridwidth = 8;
		panel.add( radioButtonContainer.createMyDefaultPanel(), c);
		// Row 2, column 0, width 8;
		c.gridy = 2;
		panel.add( rbLabel, c);
		
		if( title != null){
			panel.setBorder( BorderFactory.createTitledBorder(title));
		}
		return panel;
	}
	
	public JButton getCalculateButton(){	return calculateButton;	}
	public JButton getResetButton(){	return resetButton;	}
	public InputContainer getInputContainer_A(){	return input_a;	}
	public InputContainer getInputContainer_B(){	return input_b;	}
	public CheckBoxContainer getCheckBoxContainer(){	return checkBoxContainer;	}
	public HighLowContainer getHighLowA(){	return highlow_a;	}
	public HighLowContainer getHighLowB(){	return highlow_b;	}
	public HighLowContainer getHighLowAB(){	return highlow_ab;	}
	public JLabel getHighLowPerfect(){	return highlow_perfect;	}
	public RadioButtonContainer getRBContainer(){	return radioButtonContainer;	}
	public JLabel getRBLabel(){	return rbLabel;	}
}