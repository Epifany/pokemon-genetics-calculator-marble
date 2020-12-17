/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */
package org.epifany.pokemonbreedprobabilitycalculator.model.basic;

/**
 * This class represents a special type of fraction:
 * the numerator will denote the number of combinations that satisfy the user condition
 * the denominator will denote the total number of combinations
 * 
 * @author Stephen Gung
 */
public class Fraction {
	protected long numerator;
	protected long denominator;
	
	public Fraction( long num){
		numerator = num;
		denominator = num;
	}
	
	public Fraction( long numtr, long dentr){
		numerator = numtr;
		denominator = dentr;
	}
	
	// Copy Constructor method
	public Fraction( Fraction p){
		numerator = p.numerator;
		denominator = p.denominator;
	}
	
	public void addNumerator( long num){	numerator += num;	}
	public void addDenominator( long num){	denominator += num;	}
	public void subtractNumerator( long num){	numerator -= num;	}
	public void subtractDenominator( long num){	denominator -= num;	}
	public void multiplyNumerator( long num){	numerator *= num;	}
	public void multiplyDenominator( long num){	denominator *= num;	}
	
	public Fraction append( Fraction fraction){
		return new Fraction( numerator + fraction.numerator,
							denominator + fraction.denominator);
	}
	
	// Return a new object whose value is this x multiplicand 
	public Fraction multiply( Fraction multiplicand){
		return new Fraction( numerator * multiplicand.numerator,
							denominator * multiplicand.denominator);
	}
	
	public long getNumerator(){	return numerator;	}
	public long getDenominator(){	return denominator;	}
}