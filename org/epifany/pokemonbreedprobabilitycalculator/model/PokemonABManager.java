/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator.model;

import org.epifany.pokemon.*;

/**
 * This class is responsible for managing Pokemon A and Pokemon B
 * 
 * @author Stephen Gung
 */
public class PokemonABManager {
	// Represents Pokemon A
	private final Pokemon pokemon_a;
	// Represents Pokemon B
	private final Pokemon pokemon_b;
	// Represents how many IVs are maxed in Pokemon A
	private final int numPerfectIV_a;
	// represents how many IVs are maxed in Pokemon B
	private final int numPerfectIV_b;
	
	public PokemonABManager( int id_a, String name_a,
						Gender gender_a, Nature nature_a,
						int hp_a, int atk_a, int def_a, int spa_a, int spd_a, int spe_a,
						int id_b, String name_b,
						Gender gender_b, Nature nature_b,
						int hp_b, int atk_b, int def_b, int spa_b, int spd_b, int spe_b){
		// Initializing Pokemon A and Pokemon B
		System.out.println( "Initializing Pokemon A & Pokemon B..");
		pokemon_a = new Pokemon( id_a, name_a,
						gender_a, nature_a,
						hp_a, atk_a, def_a, spa_a, spd_a, spe_a);
		pokemon_b = new Pokemon( id_b, name_b,
						gender_b, nature_b,
						hp_b, atk_b, def_b, spa_b, spd_b, spe_b);
		// Keeping record of num perfect IVs
		int num = 0;
		if( pokemon_a.getHP_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_a.getAtk_IV()== PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_a.getDef_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_a.getSpA_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_a.getSpD_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_a.getSpe_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		numPerfectIV_a = num;
		// Reset and check same thing for Pokemon B
		num = 0;
		if( pokemon_b.getHP_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_b.getAtk_IV()== PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_b.getDef_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_b.getSpA_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_b.getSpD_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_b.getSpe_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		numPerfectIV_b = num;
	}
	
	public PokemonABManager( Pokemon pA, Pokemon pB){
		// Initializing Pokemon A and Pokemon B
		System.out.println( "Initializing Pokemon A & Pokemon B..");
		pokemon_a = new Pokemon( pA);
		pokemon_b = new Pokemon( pB);
		// Keeping record of num perfect IVs
		int num = 0;
		if( pokemon_a.getHP_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_a.getAtk_IV()== PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_a.getDef_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_a.getSpA_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_a.getSpD_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_a.getSpe_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		numPerfectIV_a = num;
		// Reset and check same thing for Pokemon B
		num = 0;
		if( pokemon_b.getHP_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_b.getAtk_IV()== PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_b.getDef_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_b.getSpA_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_b.getSpD_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		if( pokemon_b.getSpe_IV() == PokemonHelper.MAX_IV_VALUE){	num++;	}
		numPerfectIV_b = num;
	}
	
	/**
	 * Copy constructor method
	 * @param pm - The object to be copied
	 */
	public PokemonABManager( PokemonABManager pm){
		pokemon_a = new Pokemon( pm.pokemon_a);
		pokemon_b = new Pokemon( pm.pokemon_b);
		numPerfectIV_a = pm.numPerfectIV_a;
		numPerfectIV_b = pm.numPerfectIV_b;
	}
	
	public Pokemon getPokemonA(){	return pokemon_a;	}
	public Pokemon getPokemonB(){	return pokemon_b;	}
	
	public int numPerfIVA(){	return numPerfectIV_a;	}
	public int numPerfIVB(){	return numPerfectIV_b;	}
}