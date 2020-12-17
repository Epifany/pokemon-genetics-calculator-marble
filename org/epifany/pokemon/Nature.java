/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */
package org.epifany.pokemon;

/**
 * This class represents the different natures a Pokemon will have
 * 
 * @author Stephen Gung
 */
public enum Nature {
	// Natures with Attack stat increased
	HARDY,		// Attack stat decreased
	LONELY,		// Defense stat decreased
	BRAVE,		// Speed stat decreased
	ADAMANT,	// Sp. Attack stat decreased
	NAUGHTY,	// Sp. Defense decreased
	// Natures with Defense stat increased
	DOCILE,		// Defense stat decreased
	BOLD,		// Attack stat decreased
	RELAXED,	// Speed stat decreased
	IMPISH,		// Sp. Attack decreased
	LAX,		// Sp. Defense decreased
	// Natures with Speed stat increased
	SERIOUS,	// Speed stat decreased
	TIMID,		// Attack stat decreased
	HASTY,		// Defense stat decreased
	JOLLY,		// Sp. Attack decreased
	NAIVE,		// Sp. Defense decreased
	// Natures with Sp. Attack stat increased
	BASHFUL,	// Sp. Attack decreased
	MODEST,		// Attack stat decreased
	MILD,		// Defense stat decreased
	QUIET,		// Speed stat decreased
	RASH,		// Sp. Defense stat decreased
	// Natures with Sp. Defense stat increased
	QUIRKY,		// Sp. Defense decreased
	CALM,		// Attack stat decreased
	GENTLE,		// Defense stat decreased
	SASSY,		// Speed stat decreased
	CAREFUL		// Sp. Attack decreased
}