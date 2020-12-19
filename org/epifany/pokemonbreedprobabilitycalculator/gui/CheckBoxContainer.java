/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator.gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 * @author Stephen Gung
 */
public class CheckBoxContainer {
	protected final JCheckBox hp_checkbox;
	protected final JCheckBox atk_checkbox;
	protected final JCheckBox def_checkbox;
	protected final JCheckBox spa_checkbox;
	protected final JCheckBox spd_checkbox;
	protected final JCheckBox spe_checkbox;
	
	protected final JCheckBox everstone_checkbox;
	protected final JCheckBox percentage_checkbox;
	
	public CheckBoxContainer( String hp, String atk, String def,
							String spa, String spd, String spe,
							String everstoneText, String percentageText){
		hp_checkbox = new JCheckBox(hp);
		atk_checkbox = new JCheckBox(atk);
		def_checkbox = new JCheckBox(def);
		spa_checkbox = new JCheckBox(spa);
		spd_checkbox = new JCheckBox(spd);
		spe_checkbox = new JCheckBox(spe);
		everstone_checkbox = new JCheckBox(everstoneText);
		everstone_checkbox.setAlignmentX(Component.LEFT_ALIGNMENT);
		everstone_checkbox.setSelected(true);
		percentage_checkbox = new JCheckBox(percentageText);
		percentage_checkbox.setAlignmentX(Component.LEFT_ALIGNMENT);
	}
	
	public JPanel createMyDefaultPanel(){
		JPanel panel = new JPanel( new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		panel.add( everstone_checkbox, c);
		c.gridx += c.gridwidth;
		panel.add( percentage_checkbox, c);
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 10;
		panel.add( createCheckBoxPanel(), c);
		
		return panel;
	}
	
	// Default panel for CheckBox GUI
	private JPanel createCheckBoxPanel(){
		// A panel for our check boxes
		JPanel panel = new JPanel( new FlowLayout());
		panel.add( hp_checkbox);
		panel.add( atk_checkbox);
		panel.add( def_checkbox);
		panel.add( spa_checkbox);
		panel.add( spd_checkbox);
		panel.add( spe_checkbox);
		return panel;
	}
	
	public JCheckBox getHPCheckBox(){	return hp_checkbox;	}
	public JCheckBox getAtkCheckBox(){	return atk_checkbox;	}
	public JCheckBox getDefCheckBox(){	return def_checkbox;	}
	public JCheckBox getSpACheckBox(){	return spa_checkbox;	}
	public JCheckBox getSpDCheckBox(){	return spd_checkbox;	}
	public JCheckBox getSpeCheckBox(){	return spe_checkbox;	}
	public JCheckBox getEverstoneCheckBox(){	return everstone_checkbox;	}
	public JCheckBox getPercentageCheckBox(){	return percentage_checkbox;	}
}