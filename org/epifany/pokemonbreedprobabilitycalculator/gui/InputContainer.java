/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

/**
 * @author Stephen Gung
 */
public class InputContainer {
	private final JSpinner hp_spinner;
	private final JSpinner atk_spinner;
	private final JSpinner def_spinner;
	private final JSpinner spa_spinner;
	private final JSpinner spd_spinner;
	private final JSpinner spe_spinner;
	private final JComboBox item_cb;
	
	public InputContainer( String[] spnrValues, String[] cbValues,
							String hp, String atk, String def, String spa, String spd, String spe){
		// Find the maximum value, find the "length" of that text
		// For this case, it's the integer with the largest value (i.e. has the most digits)
		int length = spnrValues[ spnrValues.length - 1].length() + 1;
		// Set range of values for hp
		hp_spinner = new JSpinner( new SpinnerListModel( spnrValues));
		((JSpinner.DefaultEditor)hp_spinner.getEditor()).getTextField().setColumns(length);
		hp_spinner.setValue( spnrValues[ spnrValues.length - 1]);
		hp_spinner.setName(hp);
		// Same for atk
		atk_spinner = new JSpinner( new SpinnerListModel( spnrValues));
		((JSpinner.DefaultEditor)atk_spinner.getEditor()).getTextField().setColumns(length);
		atk_spinner.setValue( spnrValues[ spnrValues.length - 1]);
		atk_spinner.setName(atk);
		// Same for def
		def_spinner = new JSpinner( new SpinnerListModel( spnrValues));
		((JSpinner.DefaultEditor)def_spinner.getEditor()).getTextField().setColumns(length);
		def_spinner.setValue( spnrValues[ spnrValues.length - 1]);
		def_spinner.setName(def);
		// Same for spa
		spa_spinner = new JSpinner( new SpinnerListModel( spnrValues));
		((JSpinner.DefaultEditor)spa_spinner.getEditor()).getTextField().setColumns(length);
		spa_spinner.setValue( spnrValues[ spnrValues.length - 1]);
		spa_spinner.setName(spa);
		// Same for spd
		spd_spinner = new JSpinner( new SpinnerListModel( spnrValues));
		((JSpinner.DefaultEditor)spd_spinner.getEditor()).getTextField().setColumns(length);
		spd_spinner.setValue( spnrValues[ spnrValues.length - 1]);
		spd_spinner.setName(spd);
		// Same for spe
		spe_spinner = new JSpinner( new SpinnerListModel( spnrValues));
		((JSpinner.DefaultEditor)spe_spinner.getEditor()).getTextField().setColumns(length);
		spe_spinner.setValue( spnrValues[ spnrValues.length - 1]);
		spe_spinner.setName(spe);
		// Create combo box with a pre-given list of choices
		item_cb = new JComboBox( cbValues);
	}
	
	public JPanel createInputPanel( String title){
		JPanel panel = new JPanel( new GridBagLayout());
		if( title != null){
			panel.setBorder( BorderFactory.createTitledBorder( title));
		}
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets( 1, 1, 1, 1);	// Gaps for (top, left, bottom, right)
		c.fill = GridBagConstraints.HORIZONTAL;
		int width_label = 1;
		int width_spinner = 2;
		
		//c.weighty = 1.0;
		c.gridwidth = width_label;
		c.gridx = 0;
		c.gridy = 0;
		panel.add( new JLabel(hp_spinner.getName()), c);
		c.gridy = 1;
		panel.add( new JLabel(atk_spinner.getName()), c);
		c.gridy = 2;
		panel.add( new JLabel(def_spinner.getName()), c);
		c.gridy = 3;
		panel.add( new JLabel(spa_spinner.getName()), c);
		c.gridy = 4;
		panel.add( new JLabel(spd_spinner.getName()), c);
		c.gridy = 5;
		panel.add( new JLabel(spe_spinner.getName()), c);
		c.gridy = 6;
		c.gridwidth = width_label + width_spinner;
		panel.add( item_cb, c);
		
		//c.weighty = 0;
		c.gridwidth = width_spinner;
		c.gridx = width_label;
		c.gridy = 0;
		panel.add( hp_spinner, c);
		c.gridy = 1;
		panel.add( atk_spinner, c);
		c.gridy = 2;
		panel.add( def_spinner, c);
		c.gridy = 3;
		panel.add( spa_spinner, c);
		c.gridy = 4;
		panel.add( spd_spinner, c);
		c.gridy = 5;
		panel.add( spe_spinner, c);
		
		return panel;
	}
	
	public JSpinner getHPSpinner(){	return hp_spinner;	}
	public JSpinner getAtkSpinner(){	return atk_spinner;	}
	public JSpinner getDefSpinner(){	return def_spinner;	}
	public JSpinner getSpASpinner(){	return spa_spinner;	}
	public JSpinner getSpDSpinner(){	return spd_spinner;	}
	public JSpinner getSpeSpinner(){	return spe_spinner;	}
	public JComboBox getItemCB(){	return item_cb;	}
}