/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */
package org.epifany.pokemonbreedprobabilitycalculator.model;

import org.epifany.pokemonbreedprobabilitycalculator.model.basic.Fraction;

/**
 * Class for handling
 * Higher Inclusiveness,
 * Higher Exclusiveness,
 * Lower Inclusiveness,
 * Lower Exclusiveness
 * 
 * @author Stephen Gung
 */
public class HLIEManager {
	private final Fraction highInclusive;
	private final Fraction highExclusive;
	private final Fraction lowInclusive;
	private final Fraction lowExclusive;
	
	public HLIEManager(){
		highInclusive = new Fraction( 0, 0);
		highExclusive = new Fraction( 0, 0);
		lowInclusive = new Fraction( 0, 0);
		lowExclusive = new Fraction( 0, 0);
	}
	
	// Copy Constructor method
	public HLIEManager( HLIEManager p){
		highInclusive = new Fraction( p.highInclusive);
		highExclusive = new Fraction( p.highExclusive);
		lowInclusive = new Fraction( p.lowInclusive);
		lowExclusive = new Fraction( p.lowExclusive);
	}
	
	public void updateHighInclusive( Fraction p){
		highInclusive.addNumerator( p.getNumerator());
		highInclusive.addDenominator( p.getDenominator());
	}
	
	public void updateHighExclusive( Fraction p){
		highExclusive.addNumerator( p.getNumerator());
		highExclusive.addDenominator( p.getDenominator());
	}
	
	public void updateLowInclusive( Fraction p){
		lowInclusive.addNumerator( p.getNumerator());
		lowInclusive.addDenominator( p.getDenominator());
	}
	
	public void updateLowExclusive( Fraction p){
		lowExclusive.addNumerator( p.getNumerator());
		lowExclusive.addDenominator( p.getDenominator());
	}
	
	public Fraction getHighInclusive(){	return highInclusive;	}
	public Fraction getHighExclusive(){	return highExclusive;	}
	public Fraction getLowInclusive(){	return lowInclusive;	}
	public Fraction getLowExclusive(){	return lowExclusive;	}
}