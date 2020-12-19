/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator;

import java.util.HashMap;
import org.epifany.permutation.Node;
import org.epifany.pokemonbreedprobabilitycalculator.model.PokemonBreedManager;
import org.epifany.pokemonbreedprobabilitycalculator.model.basic.Fraction;
import org.epifany.pokemon.PokemonHelper;

/**
 * @author Stephen Gung
 */
public class PokemonProbRBManager {
	private boolean flag_hp;
	private boolean flag_atk;
	private boolean flag_def;
	private boolean flag_spa;
	private boolean flag_spd;
	private boolean flag_spe;
	private boolean need_hp;
	private boolean need_atk;
	private boolean need_def;
	private boolean need_spa;
	private boolean need_spd;
	private boolean need_spe;
	// Some other booleans for different states
	private boolean everstone;
	private boolean percentage;
	// The breed manager associated with the probabilities we'll be computing
	private final PokemonBreedManager breed;
	// Optimization
	private final HashMap< String, Fraction> probabilities;
	private String currentKey;
	
	public PokemonProbRBManager( PokemonBreedManager b){
		breed = b;
		probabilities = new HashMap();
		flag_hp = true;
		flag_atk = true;
		flag_def = true;
		flag_spa = true;
		flag_spd = true;
		flag_spe = true;
		need_hp = true;
		need_atk = true;
		need_def = true;
		need_spa = true;
		need_spd = true;
		need_spe = true;
		everstone = true;
	}
	
	// Copy Constructor method
	public PokemonProbRBManager( PokemonProbRBManager p){
		flag_hp = p.flag_hp;
		flag_atk = p.flag_atk;
		flag_def = p.flag_def;
		flag_spa = p.flag_spa;
		flag_spd = p.flag_spd;
		flag_spe = p.flag_spe;
		need_hp = p.need_hp;
		need_atk = p.need_atk;
		need_def = p.need_def;
		need_spa = p.need_spa;
		need_spd = p.need_spd;
		need_spe = p.need_spe;
		everstone = p.everstone;
		percentage = p.percentage;
		breed = p.breed;
		probabilities = new HashMap( p.probabilities);
	}
	
	public void updateProbabilities(){
		// To save computing time, update only if it hasn't yet been evaluated
		if( probabilities.get(currentKey) == null){
			System.out.println( "New key: " + currentKey);
			Fraction fraction = new Fraction( 0, 0);
			// Iterate through every permutation for probability
			for( int i = 0; i < breed.getCalculator().size(); i++){
				Node target = breed.getCalculator().getNode(i);
				Fraction temp = calculateFraction( target);
				fraction.addNumerator( temp.getNumerator());
				fraction.addDenominator( temp.getDenominator());
			}
			probabilities.put( currentKey, fraction);
		}/*
		else{
			System.out.println( "Key already exists: " + currentKey);
		}*/
	}
	
	// Do the actual calculating of the fraction when we hit a leaf node
	private Fraction calculateFraction( Node target){
		Fraction result;
		if( target.isLeaf()){
			result = calcFraction( breed.getIVs(target));
		}
		else{
			result = calculateFraction( target.getLeftChild());
			Fraction temp = calculateFraction( target.getRightChild());
			result.addNumerator( temp.getNumerator());
			result.addDenominator( temp.getDenominator());
		}
		return result;
	}
	
	// Calculates the fractional probability for this particlar set of IVs (taken into consideration of the user's stat selection)
	private Fraction calcFraction( int[] target){
		Fraction prob_hp = calcFraction( target[0], 0);
		Fraction prob_atk = calcFraction( target[1], 1);
		Fraction prob_def = calcFraction( target[2], 2);
		Fraction prob_spa = calcFraction( target[3], 3);
		Fraction prob_spd = calcFraction( target[4], 4);
		Fraction prob_spe = calcFraction( target[5], 5);
		return prob_hp.multiply(prob_atk).multiply(prob_def).multiply(prob_spa).multiply(prob_spd).multiply(prob_spe);
	}
	
	private Fraction calcFraction( int target, int stat){
		// Get the appropriate stat string-key
		char keyAt = currentKey.charAt(stat);
		int numerator = 0;
		int denominator = (target == PokemonHelper.NULL_IV_VALUE) ? PokemonHelper.NUM_IV_VALUES : 1;
		// Include
		switch( keyAt){
			case 'i':
				numerator = (target == PokemonHelper.NULL_IV_VALUE) ? 1 :
							(PokemonHelper.isPerfectIV(target)) ? 1 : 0;
				break;
			case 'e':
				numerator = (target == PokemonHelper.NULL_IV_VALUE) ? (PokemonHelper.NUM_IV_VALUES - 1) :
							(PokemonHelper.isPerfectIV(target)) ? 0 : 1;
				break;
			case 'o':
				numerator = denominator;
				break;
			default:
				break;
		}
		return new Fraction( numerator, denominator);
	}
	
	public void clearProbabilities(){	probabilities.clear();	}
	
	public void updateCurrentKey(){
		String hp = (need_hp == true && flag_hp == true) ? "i" :
					(need_hp == true && flag_hp == false) ? "e" :
					(need_hp == false && flag_hp == true) ? "o" : "n";
		String atk = (need_atk == true && flag_atk == true) ? "i" :
					(need_atk == true && flag_atk == false) ? "e" :
					(need_atk == false && flag_atk == true) ? "o" : "n";
		String def = (need_def == true && flag_def == true) ? "i" :
					(need_def == true && flag_def == false) ? "e" :
					(need_def == false && flag_def == true) ? "o" : "n";
		String spa = (need_spa == true && flag_spa == true) ? "i" :
					(need_spa == true && flag_spa == false) ? "e" :
					(need_spa == false && flag_spa == true) ? "o" : "n";
		String spd = (need_spd == true && flag_spd == true) ? "i" :
					(need_spd == true && flag_spd == false) ? "e" :
					(need_spd == false && flag_spd == true) ? "o" : "n";
		String spe = (need_spe == true && flag_spe == true) ? "i" :
					(need_spe == true && flag_spe == false) ? "e" :
					(need_spe == false && flag_spe == true) ? "o" : "n";
		currentKey = hp + atk + def + spa + spd + spe;
	}
	
	public void setFlagHp( boolean flag){	flag_hp = flag;	}
	public void setFlagAtk( boolean flag){	flag_atk = flag;	}
	public void setFlagDef( boolean flag){	flag_def = flag;	}
	public void setFlagSpA( boolean flag){	flag_spa = flag;	}
	public void setFlagSpD( boolean flag){	flag_spd = flag;	}
	public void setFlagSpe( boolean flag){	flag_spe = flag;	}
	
	public void setNeedHp( boolean flag){	need_hp = flag;	}
	public void setNeedAtk( boolean flag){	need_atk = flag;	}
	public void setNeedDef( boolean flag){	need_def = flag;	}
	public void setNeedSpA( boolean flag){	need_spa = flag;	}
	public void setNeedSpD( boolean flag){	need_spd = flag;	}
	public void setNeedSpe( boolean flag){	need_spe = flag;	}
	
	public void setEverstone( boolean e){	everstone = e;	}
	public void setPercentage(boolean p){	percentage = p;	}
	
	public boolean flagHp(){	return flag_hp;	}
	public boolean flagAtk(){	return flag_atk;	}
	public boolean flagDef(){	return flag_def;	}
	public boolean flagSpA(){	return flag_spa;	}
	public boolean flagSpD(){	return flag_spd;	}
	public boolean flagSpe(){	return flag_spe;	}
	
	public boolean needHp(){	return need_hp;	}
	public boolean needAtk(){	return need_atk;	}
	public boolean needDef(){	return need_def;	}
	public boolean needSpA(){	return need_spa;	}
	public boolean needSpD(){	return need_spd;	}
	public boolean needSpe(){	return need_spe;	}
	
	public boolean hasEverstone(){	return everstone;	}
	public boolean showPercentage(){	return percentage;	}
	
	public PokemonBreedManager getBreedManager(){	return breed;	}
	
	public Fraction getFractionAt( String key){	return probabilities.get(key);	}
	
	public String getCurrentKey(){	return currentKey;	}
}