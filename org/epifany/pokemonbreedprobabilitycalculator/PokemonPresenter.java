/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator;

import java.text.NumberFormat;
import org.epifany.pokemonbreedprobabilitycalculator.model.HLIEManager;
import org.epifany.pokemonbreedprobabilitycalculator.gui.*;
import org.epifany.permutation.NodePermutationCalculator;
import org.epifany.pokemon.*;
import org.epifany.pokemonbreedprobabilitycalculator.PokemonCalcManager.CalcType;
import org.epifany.pokemonbreedprobabilitycalculator.model.PokemonABManager;
import org.epifany.pokemonbreedprobabilitycalculator.model.PokemonHLABManager;

/**
 * @author Stephen Gung
 */
public class PokemonPresenter {
	private boolean started;
	// Our model
	private final PokemonManager manager;
	// Our view
	private final PokemonGUIContainer view;
	
	public PokemonPresenter( PokemonManager m, PokemonGUIContainer v){
		manager = m;
		view = v;
	}
	
	public void calculate(){
		PokemonCalcManager calcManager = manager.getCalcManager();
		// Obtain the values for A
		InputContainer input = view.getInputContainer_A();
		int hp_a = Integer.parseInt( input.getHPSpinner().getValue().toString());
		int atk_a = Integer.parseInt( input.getAtkSpinner().getValue().toString());
		int def_a = Integer.parseInt( input.getDefSpinner().getValue().toString());
		int spa_a = Integer.parseInt( input.getSpASpinner().getValue().toString());
		int spd_a = Integer.parseInt( input.getSpDSpinner().getValue().toString());
		int spe_a = Integer.parseInt( input.getSpeSpinner().getValue().toString());
		String list_a = input.getItemCB().getSelectedItem().toString();
		// Obtain the values for B
		input = view.getInputContainer_B();
		int hp_b = Integer.parseInt( input.getHPSpinner().getValue().toString());
		int atk_b = Integer.parseInt( input.getAtkSpinner().getValue().toString());
		int def_b = Integer.parseInt( input.getDefSpinner().getValue().toString());
		int spa_b = Integer.parseInt( input.getSpASpinner().getValue().toString());
		int spd_b = Integer.parseInt( input.getSpDSpinner().getValue().toString());
		int spe_b = Integer.parseInt( input.getSpeSpinner().getValue().toString());
		String list_b = input.getItemCB().getSelectedItem().toString();
		// Find out which item was held
		CalcType type = list_a.equals( PokemonCommand.ITEM_DESTINYKNOT_TEXT)	? CalcType.DESTINYKNOT :
						list_a.equals( PokemonCommand.ITEM_POWERWEIGHT_TEXT)	? CalcType.POWERHP :
						list_a.equals( PokemonCommand.ITEM_POWERBRACER_TEXT)	? CalcType.POWERATK :
						list_a.equals( PokemonCommand.ITEM_POWERBELT_TEXT)		? CalcType.POWERDEF :
						list_a.equals( PokemonCommand.ITEM_POWERLENS_TEXT)		? CalcType.POWERSPA :
						list_a.equals( PokemonCommand.ITEM_POWERBAND_TEXT)		? CalcType.POWERSPD :
						list_a.equals( PokemonCommand.ITEM_POWERANKLET_TEXT)	? CalcType.POWERSPE : 
						list_a.equals( PokemonCommand.ITEM_EVERSTONE_TEXT)		? CalcType.EVERSTONE : CalcType.DEFAULT;
		calcManager.setCaclTypeA( type);
		// Find out which item was held
		type = list_b.equals( PokemonCommand.ITEM_DESTINYKNOT_TEXT)	? CalcType.DESTINYKNOT :
				list_b.equals( PokemonCommand.ITEM_POWERWEIGHT_TEXT)	? CalcType.POWERHP :
				list_b.equals( PokemonCommand.ITEM_POWERBRACER_TEXT)	? CalcType.POWERATK :
				list_b.equals( PokemonCommand.ITEM_POWERBELT_TEXT)		? CalcType.POWERDEF :
				list_b.equals( PokemonCommand.ITEM_POWERLENS_TEXT)		? CalcType.POWERSPA :
				list_b.equals( PokemonCommand.ITEM_POWERBAND_TEXT)		? CalcType.POWERSPD :
				list_b.equals( PokemonCommand.ITEM_POWERANKLET_TEXT)	? CalcType.POWERSPE : CalcType.DEFAULT;
		calcManager.setCaclTypeB( type);
		
		// By default for this program, everstone was set to false
		if( list_a.equals(PokemonCommand.ITEM_EVERSTONE_TEXT) || list_b.equals(PokemonCommand.ITEM_EVERSTONE_TEXT))
			calcManager.setEverstone(true);
		// By default for this program, noitem was set to true
		if( !list_a.equals(PokemonCommand.ITEM_NONE_TEXT) && !list_b.equals(PokemonCommand.ITEM_NONE_TEXT))
			calcManager.setNoItem(false);
		
		calcManager.updateCurrentKey();
		calcManager.updateCalculators();
		NodePermutationCalculator currentCalculator = calcManager.getCalculatorAt( calcManager.getCurrentKey());
		manager.getBreedManager().setCalculator( currentCalculator);
		
		Pokemon pokemon_A =	new Pokemon( 99, "A", Gender.MALE, Nature.MODEST,
							hp_a, atk_a, def_a, spa_a, spd_a, spe_a);
		Pokemon pokemon_B = new Pokemon( 99, "B", Gender.FEMALE, Nature.MODEST,
							hp_b, atk_b, def_b, spa_b, spd_b, spe_b);
		
		manager.getBreedManager().setManager( new PokemonABManager( pokemon_A, pokemon_B));
		manager.getBreedManager().evaluateIVMapping();
		started = true;
	}
	
	// Reset everything; clean slate
	public void reset(){
		manager.getProbCBManager().clearProbabilities();
		manager.getProbRBManager().clearProbabilities();
		manager.getCalcManager().setEverstone(false);
		manager.getCalcManager().setNoItem(true);
		started = false;
	}
	
	// This method will reset all the GUI values. Calling this method will not affect the internal data.
	public void resetGUI(){
		InputContainer input_a = view.getInputContainer_A();
		InputContainer input_b = view.getInputContainer_B();
		CheckBoxContainer cbContainer = view.getCheckBoxContainer();
		RadioButtonContainer rbContainer = view.getRBContainer();
		// Reset spinner values
		input_a.getHPSpinner().setValue("31");
		input_a.getAtkSpinner().setValue("31");
		input_a.getDefSpinner().setValue("31");
		input_a.getSpASpinner().setValue("31");
		input_a.getSpDSpinner().setValue("31");
		input_a.getSpeSpinner().setValue("31");
		// Reset list
		input_a.getItemCB().setSelectedIndex(1);
		// Reset spinner values
		input_b.getHPSpinner().setValue("31");
		input_b.getAtkSpinner().setValue("31");
		input_b.getDefSpinner().setValue("31");
		input_b.getSpASpinner().setValue("31");
		input_b.getSpDSpinner().setValue("31");
		input_b.getSpeSpinner().setValue("31");
		// Reset list
		input_b.getItemCB().setSelectedIndex(2);
		// Reset checkboxes
		cbContainer.getHPCheckBox().setSelected(false);
		cbContainer.getAtkCheckBox().setSelected( false);
		cbContainer.getDefCheckBox().setSelected( false);
		cbContainer.getSpACheckBox().setSelected( false);
		cbContainer.getSpDCheckBox().setSelected( false);
		cbContainer.getSpeCheckBox().setSelected( false);
		// Reset labels
		view.getHighLowA().setHighInclusiveText( "0 / 0");
		view.getHighLowA().setHighExclusiveText( "0 / 0");
		view.getHighLowA().setLowInclusiveText( "0 / 0");
		view.getHighLowA().setLowExclusiveText( "0 / 0");
		view.getHighLowB().setHighInclusiveText( "0 / 0");
		view.getHighLowB().setHighExclusiveText( "0 / 0");
		view.getHighLowB().setLowInclusiveText( "0 / 0");
		view.getHighLowB().setLowExclusiveText( "0 / 0");
		view.getHighLowAB().setHighInclusiveText( "0 / 0");
		view.getHighLowAB().setHighExclusiveText( "0 / 0");
		view.getHighLowAB().setLowInclusiveText( "0 / 0");
		view.getHighLowAB().setLowExclusiveText( "0 / 0");
		view.getHighLowPerfect().setText( "0 / 0");
		// Reset RadioButtons
		rbContainer.getHPIncludeRB().setSelected( true);
		rbContainer.getAtkIncludeRB().setSelected( true);
		rbContainer.getDefIncludeRB().setSelected( true);
		rbContainer.getSpAIncludeRB().setSelected( true);
		rbContainer.getSpDIncludeRB().setSelected( true);
		rbContainer.getSpeIncludeRB().setSelected( true);
		// Reset label
		view.getRBLabel().setText( "0 / 0");
	}
	
	// This method is called whenever a flag state is changed
	public void updateFlagState( String text, boolean flag){
		switch (text) {
			case PokemonHelper.HP_TEXT:
				manager.getProbCBManager().setFlagHp(flag);
				break;
			case PokemonHelper.ATK_TEXT:
				manager.getProbCBManager().setFlagAtk(flag);
				break;
			case PokemonHelper.DEF_TEXT:
				manager.getProbCBManager().setFlagDef(flag);
				break;
			case PokemonHelper.SPA_TEXT:
				manager.getProbCBManager().setFlagSpA(flag);
				break;
			case PokemonHelper.SPD_TEXT:
				manager.getProbCBManager().setFlagSpD(flag);
				break;
			case PokemonHelper.SPE_TEXT:
				manager.getProbCBManager().setFlagSpe(flag);
				break;
			default:
				break;
		}
	}
	
	public void updateFlagStateKey(){	manager.getProbCBManager().updateCurrentKey();	}
	
	// Called whenever a flag state is changed, or when calculate is done
	public void updateFlagStateProbability(){
		if( started){
			PokemonProbCBManager probManager = manager.getProbCBManager();
			probManager.updateProbabilities();
			PokemonHLABManager swab = probManager.getSWABAt( probManager.getCurrentKey());
			// For A, B, then AB
			updateFlagStateProbabilityDetail( view.getHighLowA(), swab.getManagerA());
			updateFlagStateProbabilityDetail( view.getHighLowB(), swab.getManagerB());
			updateFlagStateProbabilityDetail( view.getHighLowAB(), swab.getManagerAB());
			
			long numerator = swab.getManagerPerfect().getNumerator();
			long denominator = swab.getManagerPerfect().getDenominator();
			
			// everstone, everstone -> 16 million
			// noitem, everstone -> 16 million
			// everstone, noitem -> 16 million
			
			// only time it'll actually "expand" is if
			// noitem, noitem -> expand
			// noitem, dk/poweritem -> expand
			// dk/poweritem, noitem -> expand
			
			// Not having Everstone will expand the probability
			if( !manager.getCalcManager().hasEverstone()){
				numerator *= PokemonHelper.NUM_NATURES;
				denominator *= PokemonHelper.NUM_NATURES;
			}
			
			String text =
				NumberFormat.getInstance().format(numerator) + " / " +
				NumberFormat.getInstance().format(denominator);
			view.getHighLowPerfect().setText( text);
		}
	}
	
	// Method for convenience
	private void updateFlagStateProbabilityDetail( HighLowContainer swContainer, HLIEManager swie){
		// For adding commas to large numbers
		NumberFormat nf = NumberFormat.getInstance();
		
		// Inclusively stronger
		long numerator = swie.getHighInclusive().getNumerator();
		long denominator = swie.getHighInclusive().getDenominator();
		// Not having Everstone will expand the probability
		if( !manager.getCalcManager().hasEverstone()){
			numerator *= PokemonHelper.NUM_NATURES;
			denominator *= PokemonHelper.NUM_NATURES;
		}
		swContainer.setHighInclusiveText( nf.format(numerator) + " / " + nf.format(denominator));
		
		// Exclusively stronger
		numerator = swie.getHighExclusive().getNumerator();
		denominator = swie.getHighExclusive().getDenominator();
		// Not having Everstone will expand the probability
		if( !manager.getCalcManager().hasEverstone()){
			numerator *= PokemonHelper.NUM_NATURES;
			denominator *= PokemonHelper.NUM_NATURES;
		}
		swContainer.setHighExclusiveText( nf.format(numerator) + " / " + nf.format(denominator));
		
		// Inclusively weaker
		numerator = swie.getLowInclusive().getNumerator();
		denominator = swie.getLowInclusive().getDenominator();
		// Not having Everstone will expand the probability
		if( !manager.getCalcManager().hasEverstone()){
			numerator *= PokemonHelper.NUM_NATURES;
			denominator *= PokemonHelper.NUM_NATURES;
		}
		swContainer.setLowInclusiveText( nf.format(numerator) + " / " + nf.format(denominator));
		
		// Exclusively weaker
		numerator = swie.getLowExclusive().getNumerator();
		denominator = swie.getLowExclusive().getDenominator();
		// Not having Everstone will expand the probability
		if( !manager.getCalcManager().hasEverstone()){
			numerator *= PokemonHelper.NUM_NATURES;
			denominator *= PokemonHelper.NUM_NATURES;
		}
		swContainer.setLowExclusiveText( nf.format(numerator) + " / " + nf.format(denominator));
	}
	
	/*
	private Fraction adjustForEverstone( Fraction f){
		if( f == null)
			return null;
		Fraction result = f;
		if( !manager.getCalcManager().hasEverstone()){
			long numerator = f.getNumerator() * PokemonHelper.NUM_NATURES;
			long denominator = f.getDenominator() * PokemonHelper.NUM_NATURES;
			result = new Fraction( numerator, denominator);
		}
		return result;
	}
	*/
	
	// This method is called whenever a radiobutton state is changed
	public void updateRBState( String text, boolean flag){
		if( flag){
			switch (text) {
				case PokemonCommand.HP_INC:
					manager.getProbRBManager().setNeedHp( true);
					manager.getProbRBManager().setFlagHp( true);
					break;
				case PokemonCommand.HP_OPT:
					manager.getProbRBManager().setNeedHp( false);
					manager.getProbRBManager().setFlagHp( true);
					break;
				case PokemonCommand.HP_EXC:
					manager.getProbRBManager().setNeedHp( true);
					manager.getProbRBManager().setFlagHp( false);
					break;
				case PokemonCommand.ATK_INC:
					manager.getProbRBManager().setNeedAtk( true);
					manager.getProbRBManager().setFlagAtk( true);
					break;
				case PokemonCommand.ATK_OPT:
					manager.getProbRBManager().setNeedAtk( false);
					manager.getProbRBManager().setFlagAtk( true);
					break;
				case PokemonCommand.ATK_EXC:
					manager.getProbRBManager().setNeedAtk( true);
					manager.getProbRBManager().setFlagAtk( false);
					break;
				case PokemonCommand.DEF_INC:
					manager.getProbRBManager().setNeedDef( true);
					manager.getProbRBManager().setFlagDef( true);
					break;
				case PokemonCommand.DEF_OPT:
					manager.getProbRBManager().setNeedDef( false);
					manager.getProbRBManager().setFlagDef( true);
					break;
				case PokemonCommand.DEF_EXC:
					manager.getProbRBManager().setNeedDef( true);
					manager.getProbRBManager().setFlagDef( false);
					break;
				case PokemonCommand.SPA_INC:
					manager.getProbRBManager().setNeedSpA( true);
					manager.getProbRBManager().setFlagSpA( true);
					break;
				case PokemonCommand.SPA_OPT:
					manager.getProbRBManager().setNeedSpA( false);
					manager.getProbRBManager().setFlagSpA( true);
					break;
				case PokemonCommand.SPA_EXC:
					manager.getProbRBManager().setNeedSpA( true);
					manager.getProbRBManager().setFlagSpA( false);
					break;
				case PokemonCommand.SPD_INC:
					manager.getProbRBManager().setNeedSpD( true);
					manager.getProbRBManager().setFlagSpD( true);
					break;
				case PokemonCommand.SPD_OPT:
					manager.getProbRBManager().setNeedSpD( false);
					manager.getProbRBManager().setFlagSpD( true);
					break;
				case PokemonCommand.SPD_EXC:
					manager.getProbRBManager().setNeedSpD( true);
					manager.getProbRBManager().setFlagSpD( false);
					break;
				case PokemonCommand.SPE_INC:
					manager.getProbRBManager().setNeedSpe( true);
					manager.getProbRBManager().setFlagSpe( true);
					break;
				case PokemonCommand.SPE_OPT:
					manager.getProbRBManager().setNeedSpe( false);
					manager.getProbRBManager().setFlagSpe( true);
					break;
				case PokemonCommand.SPE_EXC:
					manager.getProbRBManager().setNeedSpe( true);
					manager.getProbRBManager().setFlagSpe( false);
					break;
				default:
					break;
			}
		}
	}

	public void updateRBStateKey(){
		manager.getProbRBManager().updateCurrentKey();
	}
	
	// Called whenever a radio button state is changed, or when calculate is done
	public void updateRBStateProbability(){
		if( started){
			PokemonProbRBManager prbm = manager.getProbRBManager();
			prbm.updateProbabilities();
			long numerator = prbm.getFractionAt( prbm.getCurrentKey()).getNumerator();
			long denominator = prbm.getFractionAt( prbm.getCurrentKey()).getDenominator();
			// Not having Everstone will expand the probability
			if( !manager.getCalcManager().hasEverstone()){
				numerator *= PokemonHelper.NUM_NATURES;
				denominator *= PokemonHelper.NUM_NATURES;
			}
			view.getRBLabel().setText(
				NumberFormat.getInstance().format(numerator) + " / " +
				NumberFormat.getInstance().format(denominator));
		}
	}
}