/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */
package org.epifany.pokemon;

/**
 * This class represents a Pokemon
 * 
 * @author Stephen Gung
 */
public class Pokemon {
	// The unique identifier for the Pokemon
	protected final int id;
	// The name of the Pokemon
	protected String name;
	// The gender of the Pokemon
	protected final Gender gender;
	// The nature of the Pokemon
	protected final Nature nature;
	// The Individual Values of the Pokemon, these values cannot change
	protected final int hp_iv;
	protected final int atk_iv;
	protected final int def_iv;
	protected final int spa_iv;
	protected final int spd_iv;
	protected final int spe_iv;
	// The Effort Values of the Pokemon
	protected int hp_ev;
	protected int atk_ev;
	protected int def_ev;
	protected int spa_ev;
	protected int spd_ev;
	protected int spe_ev;
	// The sum total of Effort Values of the Pokemon
	protected int sum_ev;
	
	public Pokemon( int ID,
					String name,
					Gender gender,
					Nature nature,
					int hp, int atk, int def,
					int spa, int spd, int spe){
		id = ID;
		this.name = name;
		this.gender = gender;
		this.nature = nature;
		hp_iv = hp;
		atk_iv = atk;
		def_iv = def;
		spa_iv = spa;
		spd_iv = spd;
		spe_iv = spe;
		hp_ev = PokemonHelper.MIN_EV_VALUE;
		atk_ev = PokemonHelper.MIN_EV_VALUE;
		def_ev = PokemonHelper.MIN_EV_VALUE;
		spa_ev = PokemonHelper.MIN_EV_VALUE;
		spd_ev = PokemonHelper.MIN_EV_VALUE;
		spe_ev = PokemonHelper.MIN_EV_VALUE;
		sum_ev = PokemonHelper.MIN_EV_VALUE;
	}
	
	// Copy constructor method
	public Pokemon( Pokemon p){
		id = p.id;
		name = p.name;
		gender = p.gender;
		nature = p.nature;
		// Copy IV values
		hp_iv = p.hp_iv;
		atk_iv = p.atk_iv;
		def_iv = p.def_iv;
		spa_iv = p.spa_iv;
		spd_iv = p.spd_iv;
		spe_iv = p.spe_iv;
		// Copy EV values
		hp_ev = p.hp_ev;
		atk_ev = p.atk_ev;
		def_ev = p.def_ev;
		spa_ev = p.spa_ev;
		spd_ev = p.spd_ev;
		spe_ev = p.spe_ev;
		sum_ev = p.sum_ev;
	}
	
	/**
	 * Resets the EVs of this Pokemon to zero
	 */
	public void resetEVs(){
		hp_ev = 0;
		atk_ev = 0;
		def_ev = 0;
		spa_ev = 0;
		spd_ev = 0;
		spe_ev = 0;
		sum_ev = 0;
	}
	
	public void setName( String str){	name = str;	}
	
	public boolean addHP_EV( int ev){
		int num = addEV( hp_ev, ev);
		if( num <= 0){
			return false;
		}
		hp_ev += num;
		sum_ev += num;
		return true;
	}
	
	public boolean addAtk_EV( int ev){
		int num = addEV( atk_ev, ev);
		if( num <= 0){
			return false;
		}
		atk_ev += num;
		sum_ev += num;
		return true;
	}
	
	public boolean addDef_EV( int ev){
		int num = addEV( def_ev, ev);
		if( num <= 0){
			return false;
		}
		def_ev += num;
		sum_ev += num;
		return true;
	}
	
	public boolean addSpA_EV( int ev){
		int num = addEV( spa_ev, ev);
		if( num <= 0){
			return false;
		}
		spa_ev += num;
		sum_ev += num;
		return true;
	}
	
	public boolean addSpD_EV( int ev){
		int num = addEV( spd_ev, ev);
		if( num <= 0){
			return false;
		}
		spd_ev += num;
		sum_ev += num;
		return true;
	}
	
	public boolean addSpe_EV( int ev){
		int num = addEV( spe_ev, ev);
		if( num <= 0){
			return false;
		}
		spe_ev += num;
		sum_ev += num;
		return true;
	}
	
	protected int addEV( int source_ev, int target_ev){
		// Check if additional ev can be added
		if( (source_ev >= PokemonHelper.MAX_EV_VALUE)
		|| (sum_ev >= PokemonHelper.SUM_EV_LIMIT)
		|| target_ev == 0){
			return 0;
		}
		// If we're here then that means some ev can be added
		// Find the amount of extra ev
		int s1 = source_ev + target_ev;
		int s2 = sum_ev + target_ev;
		if( s1 <= PokemonHelper.MAX_EV_VALUE
		&& s2 <= PokemonHelper.SUM_EV_LIMIT){
			return target_ev;
		}
		// If adding ev will exceed only the sum_ev
		else if( s1 <= PokemonHelper.MAX_EV_VALUE
		&& s2 > PokemonHelper.SUM_EV_LIMIT){
			return (PokemonHelper.SUM_EV_LIMIT - sum_ev);
		}
		// If adding ev will exceed only the source_ev
		else if( s1 > PokemonHelper.MAX_EV_VALUE
		&& s2 <= PokemonHelper.SUM_EV_LIMIT){
			return (PokemonHelper.MAX_EV_VALUE - source_ev);
		}
		// both exceeds their corresponding evs
		else{
			// Who will add less?
			s1 = PokemonHelper.MAX_EV_VALUE - source_ev;
			s2 = PokemonHelper.SUM_EV_LIMIT - sum_ev;
			return (s1 < s2) ? s1 : s2;
		}
	}
	
	public void setHP_EV( int ev){	hp_ev = ev;	}
	public void setAtk_EV( int ev){	atk_ev = ev;	}
	public void setDef_EV( int ev){	def_ev = ev;	}
	public void setSpA_EV( int ev){	spa_ev = ev;	}
	public void setSpD_EV( int ev){	spd_ev = ev;	}
	public void setSpe_EV( int ev){	spe_ev = ev;	}
	
	public int getID(){	return id;	}
	public String getName(){	return name;	}
	public Gender getGender(){	return gender;	}
	public Nature getNature(){	return nature;	}
	
	public int getHP_IV(){	return hp_iv;	}
	public int getAtk_IV(){	return atk_iv;	}
	public int getDef_IV(){	return def_iv;	}
	public int getSpA_IV(){	return spa_iv;	}
	public int getSpD_IV(){	return spd_iv;	}
	public int getSpe_IV(){	return spe_iv;	}
	public int getHP_EV(){	return hp_ev;	}
	public int getAtk_EV(){	return atk_ev;	}
	public int getDef_EV(){	return def_ev;	}
	public int getSpA_EV(){	return spa_ev;	}
	public int getSpD_EV(){	return spd_ev;	}
	public int getSpe_EV(){	return spe_ev;	}

	public int getSumEVs(){	return sum_ev;	}
	
	public boolean hasMaxedEVs(){
		return (sum_ev >= PokemonHelper.SUM_EV_LIMIT);
	}
}