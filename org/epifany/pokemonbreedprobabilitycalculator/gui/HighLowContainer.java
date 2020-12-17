/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator.gui;

import java.awt.Component;
import javax.swing.JLabel;

/**
 * @author Stephen Gung
 */
public class HighLowContainer {
	private final JLabel highInclusiveMsg;
	private final JLabel highExclusiveMsg;
	private final JLabel lowInclusiveMsg;
	private final JLabel lowExclusiveMsg;
	private final JLabel highInclusive;
	private final JLabel highExclusive;
	private final JLabel lowInclusive;
	private final JLabel lowExclusive;
	
	public HighLowContainer( String subject){
		highInclusive = new JLabel("0 / 0");
		highExclusive = new JLabel("0 / 0");
		lowInclusive = new JLabel("0 / 0");
		lowExclusive = new JLabel("0 / 0");
		highInclusiveMsg = new JLabel("Higher (inclusively) than " + subject + ": ");
		highExclusiveMsg = new JLabel("Higher (exclusively) than " + subject + ": ");
		lowInclusiveMsg = new JLabel("Lower (inclusively) than " + subject + ": ");
		lowExclusiveMsg = new JLabel("Lower (exclusively) than " + subject + ": ");
	}
	
	// Optional default layout for all components
	public void setLeftAlignment(){
		highInclusive.setAlignmentX( Component.LEFT_ALIGNMENT);
		highExclusive.setAlignmentX( Component.LEFT_ALIGNMENT);
		lowInclusive.setAlignmentX( Component.LEFT_ALIGNMENT);
		lowExclusive.setAlignmentX( Component.LEFT_ALIGNMENT);
		highInclusiveMsg.setAlignmentX( Component.LEFT_ALIGNMENT);
		highExclusiveMsg.setAlignmentX( Component.LEFT_ALIGNMENT);
		lowInclusiveMsg.setAlignmentX( Component.LEFT_ALIGNMENT);
		lowExclusiveMsg.setAlignmentX( Component.LEFT_ALIGNMENT);
	}
	
	public void setHighInclusiveText( String text){	highInclusive.setText(text);	}
	public void setHighExclusiveText( String text){	highExclusive.setText(text);	}
	public void setLowInclusiveText( String text){	lowInclusive.setText(text);	}
	public void setLowExclusiveText( String text){	lowExclusive.setText(text);	}
	public void setHighInclusiveMsg( String text){	highInclusiveMsg.setText(text);	}
	public void setHighExclusiveMsg( String text){	highExclusiveMsg.setText(text);	}
	public void setLowInclusiveMsg( String text){	lowInclusiveMsg.setText(text);	}
	public void setLowkExclusiveMsg( String text){	lowExclusiveMsg.setText(text);	}
	
	public JLabel getHighInclusive(){	return highInclusive;	}
	public JLabel getHighExclusive(){	return highExclusive;	}
	public JLabel getLowInclusive(){	return lowInclusive;	}
	public JLabel getLowExclusive(){	return lowExclusive;	}
	public JLabel getHighInclusiveMsg(){	return highInclusiveMsg;	}
	public JLabel getHighExclusiveMsg(){	return highExclusiveMsg;	}
	public JLabel getLowInclusiveMsg(){	return lowInclusiveMsg;	}
	public JLabel getLowExclusiveMsg(){	return lowExclusiveMsg;	}
}