/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator.model;

import java.util.HashMap;
import org.epifany.permutation.Node;
import org.epifany.permutation.NodePermutationCalculator;

/**
 * This class contains a breeding calculator, has knowledge of Pokemon A & Pokemon B,
 * as well as a IV value mapping w.r.t. the expanded breeding permutations
 * 
 * @author Stephen Gung
 */
public class PokemonBreedManager {
	// The calculator associated with this class
	private NodePermutationCalculator calculator;
	private boolean changed_calculator;
	// The Pokemon manager associated with this class
	private PokemonABManager abManager;
	private boolean changed_manager;
	/* This maps the (splitted) permutations to the respective IV values.
	The IV values are obtained from either Pokemon A or Pokemon B */
	private final HashMap< Node, int[]> IV_map;
	// Although these variables arne't necessarily needed, I did this for "good practice"
	protected final int HP_A_INDEX = 0;
	protected final int ATK_A_INDEX = 1;
	protected final int DEF_A_INDEX = 2;
	protected final int SPA_A_INDEX = 3;
	protected final int SPD_A_INDEX = 4;
	protected final int SPE_A_INDEX = 5;
	protected final int HP_B_INDEX = 6;
	protected final int ATK_B_INDEX = 7;
	protected final int DEF_B_INDEX = 8;
	protected final int SPA_B_INDEX = 9;
	protected final int SPD_B_INDEX = 10;
	protected final int SPE_B_INDEX = 11;
	
	public PokemonBreedManager(){
		IV_map = new HashMap();
	}
	
	public PokemonBreedManager( PokemonABManager m){
		abManager = new PokemonABManager( m);
		changed_manager = true;
		IV_map = new HashMap();
	}
	
	public PokemonBreedManager( NodePermutationCalculator c){
		calculator = new NodePermutationCalculator( c);
		changed_calculator = true;
		IV_map = new HashMap();
	}
	
	public PokemonBreedManager( PokemonABManager m, NodePermutationCalculator c){
		abManager = new PokemonABManager( m);
		changed_manager = true;
		calculator = new NodePermutationCalculator( c);
		changed_calculator = true;
		IV_map = new HashMap();
	}
	
	// Copy Constructor method
	public PokemonBreedManager( PokemonBreedManager pbm){
		calculator = new NodePermutationCalculator( pbm.calculator);
		abManager = new PokemonABManager( pbm.abManager);
		IV_map = new HashMap( pbm.IV_map);
	}
	
	// Evaluate the IV mappings for this particular data
	// Returns true if successfully evaluated, false otherwise
	public boolean evaluateIVMapping(){
		// Nothing new to update, or something was set to null
		if( (changed_calculator == false && changed_manager == false)
		|| abManager == null
		|| calculator == null){
			return false;
		}
		// If a new calculator was given
		if( changed_calculator){
			IV_map.clear();
		}
		for( int i = 0; i < calculator.size(); i++){
			evaluateIVMapping( calculator.getNode(i));
			//System.out.println("");
		}
		//System.out.println( "Size of IV_final: " + IV_map.size());
		changed_calculator = false;
		changed_manager = false;
		return true;
	}
	
	public void setCalculator( NodePermutationCalculator c){
		calculator = c;
		changed_calculator = true;
	}
	
	public void setManager( PokemonABManager m){
		abManager = m;
		changed_manager = true;
	}
	
	// Evaluates the IV mapping(s) for this particular permutation
	private void evaluateIVMapping( Node node){
		if( node.isLeaf()){
			int[] ivs = expandIV( node.getElements());
			IV_map.put( node, ivs);
			//System.out.print( Arrays.toString( ivs));
		}
		else{
			//System.out.print( "( ");
			evaluateIVMapping( node.getLeftChild());
			//System.out.print( ", ");
			evaluateIVMapping( node.getRightChild());
			//System.out.print( " )");
		}
	}
	
	// Replace all elemental indices with the appropriate Pokemon stat value
	private int[] expandIV( int[] elements){
		int[] values = { -1, -1, -1, -1, -1, -1};
		for( int i = 0; i < elements.length; i++){
			switch (elements[i]) {
				case HP_A_INDEX:
					values[0] = abManager.getPokemonA().getHP_IV();
					break;
				case ATK_A_INDEX:
					values[1] = abManager.getPokemonA().getAtk_IV();
					break;
				case DEF_A_INDEX:
					values[2] = abManager.getPokemonA().getDef_IV();
					break;
				case SPA_A_INDEX:
					values[3] = abManager.getPokemonA().getSpA_IV();
					break;
				case SPD_A_INDEX:
					values[4] = abManager.getPokemonA().getSpD_IV();
					break;
				case SPE_A_INDEX:
					values[5] = abManager.getPokemonA().getSpe_IV();
					break;
				case HP_B_INDEX:
					values[0] = abManager.getPokemonB().getHP_IV();
					break;
				case ATK_B_INDEX:
					values[1] = abManager.getPokemonB().getAtk_IV();
					break;
				case DEF_B_INDEX:
					values[2] = abManager.getPokemonB().getDef_IV();
					break;
				case SPA_B_INDEX:
					values[3] = abManager.getPokemonB().getSpA_IV();
					break;
				case SPD_B_INDEX:
					values[4] = abManager.getPokemonB().getSpD_IV();
					break;
				case SPE_B_INDEX:
					values[5] = abManager.getPokemonB().getSpe_IV();
					break;
				default:
					break;
			}
		}
		return values;
	}
	
	public NodePermutationCalculator getCalculator(){	return calculator;	}
	public PokemonABManager getABManager(){	return abManager;	}
	public int[] getIVs( Node node){	return IV_map.get(node);	}
}
