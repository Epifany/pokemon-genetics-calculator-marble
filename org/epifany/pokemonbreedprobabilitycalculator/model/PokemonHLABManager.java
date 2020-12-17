/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator.model;

import org.epifany.pokemonbreedprobabilitycalculator.model.basic.*;

/**
 * @author Stephen Gung
 */
public class PokemonHLABManager {
	private final HLIEManager manager_a;
	private final HLIEManager manager_b;
	private final HLIEManager manager_ab;
	private final Fraction manager_perfect;

	public PokemonHLABManager(){
		manager_a = new HLIEManager();
		manager_b = new HLIEManager();
		manager_ab = new HLIEManager();
		manager_perfect = new Fraction( 0, 0);
	}

	// Copy Constructor method
	public PokemonHLABManager( HLIEManager a,
							HLIEManager b,
							HLIEManager max,
							Fraction perfect){
		manager_a = new HLIEManager( a);
		manager_b = new HLIEManager( b);
		manager_ab = new HLIEManager( max);
		manager_perfect = new Fraction( perfect);
	}

	public HLIEManager getManagerA(){	return manager_a;	}
	public HLIEManager getManagerB(){	return manager_b;	}
	public HLIEManager getManagerAB(){	return manager_ab;	}
	public Fraction getManagerPerfect(){	return manager_perfect;	}
}
