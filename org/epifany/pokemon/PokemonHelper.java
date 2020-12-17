/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */
package org.epifany.pokemon;

import java.math.BigDecimal;

/**
 * This class holds fixed variables that may be used
 * 
 * @author Stephen Gung
 */
public final class PokemonHelper {
	// Some text
	public static final String POKEMON_TEXT = "Pokemon";
	public static final String HP_TEXT = "HP";
	public static final String ATK_TEXT = "Atk";
	public static final String DEF_TEXT = "Def";
	public static final String SPA_TEXT = "SpA";
	public static final String SPD_TEXT = "SpD";
	public static final String SPE_TEXT = "Spe";
	public static final String HP_FULLTEXT = "HP";
	public static final String ATK_FULLTEXT = "Attack";
	public static final String DEF_FULLTEXT = "Defense";
	public static final String SPA_FULLTEXT = "Sp. Attack";
	public static final String SPD_FULLTEXT = "Sp. Defense";
	public static final String SPE_FULLTEXT = "Speed";
	public static final String EVERSTONE_FULLTEXT = "Everstone";
	public static final String IV_TEXT = "IV";
	public static final String EV_TEXT = "EV";
	// Number of natures there are in Pokemon games
	public static final int NUM_NATURES = 25;
	// The number of IVs a Pokemon has
	public static final int NUM_IV = 6;
	// The number of EVs a Pokemon has
	public static final int NUM_EV = 6;
	// The minimum value for an IV
	public static final int MIN_IV_VALUE = 0;
	// The maximum value for an IV
	public static final int MAX_IV_VALUE = 31;
	// The minimum value for an EV
	public static final int MIN_EV_VALUE = 0;
	// The maximum value for an EV
	public static final int MAX_EV_VALUE = 252;
	// The sum total value a Pokemon may carry for EVs
	public static final int SUM_EV_LIMIT = 510;
	// The "null" IV value
	public static final int NULL_IV_VALUE = MIN_IV_VALUE - 1;
	// The "null" EV value
	public static final int NULL_EV_VALUE = MIN_EV_VALUE - 1;
	// The number of unique values a stat can have
	public static final int NUM_IV_VALUES = (MAX_IV_VALUE + 1) - MIN_IV_VALUE;
	
	// Return the number of occurrences of being inclusively stronger
	public static int findNumStrongInclusive( int source){
		boolean valid = (MIN_IV_VALUE <= source && source <= MAX_IV_VALUE);
		return (valid) ? ((MAX_IV_VALUE + 1) - source) : NULL_IV_VALUE;
	}
	
	// Return the number of occurrences of being exclusively stronger
	public static int findNumStrongExclusive( int source){
		boolean valid = (MIN_IV_VALUE <= source && source <= MAX_IV_VALUE);
		return (valid) ? (MAX_IV_VALUE - source) : NULL_IV_VALUE;
	}
	
	// Return the number of occurrences of being inclusively weaker
	public static int findNumWeakInclusive( int source){
		boolean valid = (MIN_IV_VALUE <= source && source <= MAX_IV_VALUE);
		return (valid) ? ((source + 1) - MIN_IV_VALUE) : NULL_IV_VALUE;
	}
	
	// Return the number of occurrences of being exclusively weaker
	public static int findNumWeakExclusive( int source){
		boolean valid = (MIN_IV_VALUE <= source && source <= MAX_IV_VALUE);
		return (valid) ? (source - MIN_IV_VALUE) : NULL_IV_VALUE;
	}
	
	public static boolean isPerfectIV( int target){
		return (target == MAX_IV_VALUE);
	}
	
	public static boolean isValidIV( int target){
		return (MIN_IV_VALUE <= target && target <= MAX_IV_VALUE);
	}
	
	// Determines the probability of this value being a perfect stat
	public static BigDecimal perfect( int target){
		if( MIN_IV_VALUE <= target && target <= MAX_IV_VALUE){
			return (target == MAX_IV_VALUE) ? new BigDecimal("1") : new BigDecimal("0");
		}
		else{
			BigDecimal result = new BigDecimal("1");
			// numerator divided by the denominator
			result = result.divide( new BigDecimal( ( MAX_IV_VALUE + 1) - MIN_IV_VALUE));
			return result;
		}
	}
	
	public static BigDecimal stronger_inclusive( int target, int source){
		// If source value is invalid
		if( source < MIN_IV_VALUE || MAX_IV_VALUE < source){
			return null;
		}
		// If value is within the accept parameters then we can compare directly
		if( MIN_IV_VALUE <= target && target <= MAX_IV_VALUE){
			return (target >= source) ? new BigDecimal("1") : new BigDecimal("0");
		}
		// Determine the probability of being inclusively stronger
		else{
			BigDecimal result = new BigDecimal( (MAX_IV_VALUE + 1) - source);
			// numerator divided by the denominator
			result = result.divide( new BigDecimal( ( MAX_IV_VALUE + 1) - MIN_IV_VALUE));
			return result;
		}
	}

	public static BigDecimal stronger_exclusive( int target, int source){
		// If source value is invalid
		if( source < MIN_IV_VALUE || MAX_IV_VALUE < source){
			return null;
		}
		// If value is within the accept parameters then we can compare directly
		if( MIN_IV_VALUE <= target && target <= MAX_IV_VALUE){
			return (target > source) ? new BigDecimal("1") : new BigDecimal("0");
		}
		// Determine the probability of being exclusively stronger
		else{
			BigDecimal result = new BigDecimal( MAX_IV_VALUE - source);
			// numerator divided by the denominator
			result = result.divide( new BigDecimal( ( MAX_IV_VALUE + 1) - MIN_IV_VALUE));
			return result;
		}
	}

	public static BigDecimal weaker_inclusive( int target, int source){
		// If source value is invalid
		if( source < MIN_IV_VALUE || MAX_IV_VALUE < source){
			return null;
		}
		// If value is within the accept parameters then we can compare directly
		if( MIN_IV_VALUE <= target && target <= MAX_IV_VALUE){
			return (target <= source) ? new BigDecimal("1") : new BigDecimal("0");
		}
		// Determine the probability of being inclusively weaker
		else{
			BigDecimal result = new BigDecimal( (source + 1) - MIN_IV_VALUE);
			// numerator divided by the denominator
			result = result.divide( new BigDecimal( ( MAX_IV_VALUE + 1) - MIN_IV_VALUE));
			return result;
		}
	}
	
	public static BigDecimal weaker_exclusive( int target, int source){
		// If source value is invalid
		if( source < MIN_IV_VALUE || MAX_IV_VALUE < source){
			return null;
		}
		// If value is within the accept parameters then we can compare directly
		if( MIN_IV_VALUE <= target && target <= MAX_IV_VALUE){
			return (target < source) ? new BigDecimal("1") : new BigDecimal("0");
		}
		// Determine the probability of being inclusively weaker
		else{
			BigDecimal result = new BigDecimal( source - MIN_IV_VALUE);
			// numerator divided by the denominator
			result = result.divide( new BigDecimal( ( MAX_IV_VALUE + 1) - MIN_IV_VALUE));
			return result;
		}
	}
	
	public static BigDecimal stronger_inclusive( int[] target, int[] source){
		if( target.length != source.length){
			return new BigDecimal("0");
		}
		BigDecimal prob = new BigDecimal("1");
		for( int i = 0; i < target.length; i++){
			BigDecimal multiplicand = stronger_inclusive( target[i], source[i]);
			if( multiplicand == null){
				return null;
			}
			// Optimizing, if this probability yields 0, then we can stop further computations
			if( multiplicand.compareTo( BigDecimal.ZERO) == 0){
				return new BigDecimal("0");
			}
			prob = prob.multiply( multiplicand);
		}
		return prob;
	}
	
	public static BigDecimal stronger_exclusive( int[] target, int[] source){
		if( target.length != source.length){
			return new BigDecimal("0");
		}
		BigDecimal prob = new BigDecimal("1");
		for( int i = 0; i < target.length; i++){
			BigDecimal multiplicand = stronger_exclusive( target[i], source[i]);
			if( multiplicand == null){
				return null;
			}
			// Optimizing, if this probability yields 0, then we can stop further computations
			if( multiplicand.compareTo( BigDecimal.ZERO) == 0){
				return new BigDecimal("0");
			}
			prob = prob.multiply( multiplicand);
		}
		return prob;
	}
	
	public static BigDecimal weaker_inclusive( int[] target, int[] source){
		if( target.length != source.length){
			return new BigDecimal("0");
		}
		BigDecimal prob = new BigDecimal("1");
		for( int i = 0; i < target.length; i++){
			BigDecimal multiplicand = weaker_inclusive( target[i], source[i]);
			if( multiplicand == null){
				return null;
			}
			// Optimizing, if this probability yields 0, then we can stop further computations
			if( multiplicand.compareTo( BigDecimal.ZERO) == 0){
				return new BigDecimal("0");
			}
			prob = prob.multiply( multiplicand);
		}
		return prob;
	}
	
	public static BigDecimal weaker_exclusive( int[] target, int[] source){
		if( target.length != source.length){
			return new BigDecimal("0");
		}
		BigDecimal prob = new BigDecimal("1");
		for( int i = 0; i < target.length; i++){
			BigDecimal multiplicand = weaker_exclusive( target[i], source[i]);
			if( multiplicand == null){
				return null;
			}
			// Optimizing, if this probability yields 0, then we can stop further computations
			if( multiplicand.compareTo( BigDecimal.ZERO) == 0){
				return new BigDecimal("0");
			}
			prob = prob.multiply( multiplicand);
		}
		return prob;
	}
	
	
}