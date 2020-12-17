/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator;

import org.epifany.pokemonbreedprobabilitycalculator.model.PokemonBreedManager;

/**
 * This is a "super" manager. It consolidates all the other managers into one place
 * 
 * @author Stephen Gung
 */
public class PokemonManager {
	private final PokemonBreedManager breedManager;
	private final PokemonProbCBManager probCBManager;
	private final PokemonProbRBManager probRBManager;
	private final PokemonCalcManager calcManager;
	
	public PokemonManager(){
		breedManager = new PokemonBreedManager();
		probCBManager = new PokemonProbCBManager( breedManager);
		probRBManager = new PokemonProbRBManager( breedManager);
		calcManager = new PokemonCalcManager();
	}
	
	public PokemonBreedManager getBreedManager(){	return breedManager;	}
	public PokemonProbCBManager getProbCBManager(){	return probCBManager;	}
	public PokemonProbRBManager getProbRBManager(){	return probRBManager;	}
	public PokemonCalcManager getCalcManager(){	return calcManager;	}
}