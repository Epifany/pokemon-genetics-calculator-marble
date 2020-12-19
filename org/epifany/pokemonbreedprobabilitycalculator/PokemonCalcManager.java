/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */

package org.epifany.pokemonbreedprobabilitycalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.epifany.permutation.NodePermutationCalculator;
import org.epifany.pokemon.PokemonHelper;

/**
 * This class handles, and saves,
 * all the different types of calculations that occurs while the program is in use
 * 
 * @author Stephen Gung
 */
public class PokemonCalcManager {
	private CalcType type_a;
	private CalcType type_b;
	private boolean noitem;
	private boolean everstone;
	
	private boolean slotApproach;
	
	private final HashMap<String, NodePermutationCalculator> calculators;
	private String currentKey;
	
	public PokemonCalcManager(){
		calculators = new HashMap();
		slotApproach = true;
	}
	
	public void updateCalculators(){
		// To save computing time, update only if it hasn't yet been evaluated
		if( calculators.get( currentKey) == null){
			System.out.println( "New key: " + currentKey);
			// Generate a new calculator here
			NodePermutationCalculator calc_new = (slotApproach) ? slotNewCalculator() : marbleNewCalculator();
			calculators.put( currentKey, calc_new);
		}/*
		else{
			System.out.println( "Key already exists: " + currentKey);
		}*/
	}
	
	// Slot approach
	private NodePermutationCalculator slotNewCalculator(){
		NodePermutationCalculator calculator;
		// Two power items
		if( currentKey.matches("[0-5][0-5]")){
			int priority_a = Character.getNumericValue( currentKey.charAt(0));
			int numElements = 2;
			// We'll need to create a dynamic array for the power item case
			// Create an array of integers that excludes the priority index
			List<Integer> list = new ArrayList();
			for( int i = 0; i < PokemonHelper.NUM_IV; i++){
				if( priority_a != i){
					list.add(i);
				}
			}
			int[] indices = convertToArray(list);
			calculator = new NodePermutationCalculator( indices, numElements);
			// need to replace and expand
			// Create copies of the permutations
			List<List<Integer>> permutationsCopy_l = new ArrayList();
			List<List<Integer>> permutationsCopy_r = new ArrayList();
			for( int i = 0; i < calculator.size(); i++){
				permutationsCopy_l.add( convertToList( calculator.getNode(i).getElements()));
				permutationsCopy_r.add( convertToList( calculator.getNode(i).getElements()));
			}
			// In this particular program, we only need to replace values for the "right" side copy
			replace( permutationsCopy_r);
			calculator.expandReplacePermutationsValues(permutationsCopy_l, permutationsCopy_r);
			// Now append the priority index to every node
			for( int i = 0; i < calculator.size(); i++){
				calculator.appendElement( calculator.getNode(i), priority_a);
			}
			
			// Do the same for B
			int offset = PokemonCommand.HP_B_INDEX;
			int priority_b = Character.getNumericValue( currentKey.charAt(1)) + offset;
			list = new ArrayList();
			for( int i = 0; i < PokemonHelper.NUM_IV; i++){
				if( priority_b != (i+offset)){
					list.add(i);
				}
			}
			indices = convertToArray(list);
			NodePermutationCalculator calculator2 = new NodePermutationCalculator( indices, numElements);
			// need to replace and expand
			// Create copies of the permutations
			permutationsCopy_l = new ArrayList();
			permutationsCopy_r = new ArrayList();
			for( int i = 0; i < calculator2.size(); i++){
				permutationsCopy_l.add( convertToList( calculator2.getNode(i).getElements()));
				permutationsCopy_r.add( convertToList( calculator2.getNode(i).getElements()));
			}
			// In this particular program, we only need to replace values for the "right" side copy
			replace( permutationsCopy_r);
			calculator2.expandReplacePermutationsValues(permutationsCopy_l, permutationsCopy_r);
			// Now append the priority index to every node
			for( int i = 0; i < calculator2.size(); i++){
				calculator2.appendElement( calculator2.getNode(i), priority_b);
			}
			
			// Now combine all the nodes together
			calculator.addNodes(calculator2);
			
		}
		// Power item held by first Pokemon or held by second Pokemon
		else if(currentKey.matches("[0-5]d")
		|| currentKey.matches("[0-5]n")
		|| currentKey.matches("d[0-5]")
		|| currentKey.matches("n[0-5]")){
			int priority;
			int numElements;
			int offset;
			// Was Power item held by first Pokemon?
			if( currentKey.matches("[0-5]d") || currentKey.matches("[0-5]n")){
				offset = 0;
				priority = Character.getNumericValue( currentKey.charAt(0)) + offset;
				// Find out how many remaining number of elements will be passed to the child
				// Is second Pokemon holding Destiny Knot or No Item?
				numElements = (currentKey.charAt(1) == 'd') ? 4 : 2;	
			}
			else{
				offset = PokemonCommand.HP_B_INDEX;
				priority = Character.getNumericValue( currentKey.charAt(1)) + offset;
				// Find out how many remaining number of elements will be passed to the child
				// Is first Pokemon holding Destiny Knot or No Item?
				numElements = (currentKey.charAt(0) == 'd') ? 4 : 2;
			}
			// We'll need to create a dynamic array for the power item case
			// Create an array of integers that excludes the priority index
			List<Integer> list = new ArrayList();
			for( int i = 0; i < PokemonHelper.NUM_IV; i++){
				if( priority != (i+offset)){
					list.add(i);
				}
			}
			int[] indices = convertToArray(list);
			calculator = new NodePermutationCalculator( indices, numElements);
			// need to replace and expand
			// Create copies of the permutations
			List<List<Integer>> permutationsCopy_l = new ArrayList();
			List<List<Integer>> permutationsCopy_r = new ArrayList();
			for( int i = 0; i < calculator.size(); i++){
				permutationsCopy_l.add( convertToList( calculator.getNode(i).getElements()));
				permutationsCopy_r.add( convertToList( calculator.getNode(i).getElements()));
			}
			// In this particular program, we only need to replace values for the "right" side copy
			replace( permutationsCopy_r);
			calculator.expandReplacePermutationsValues(permutationsCopy_l, permutationsCopy_r);
			// Now append the priority index to every node
			for( int i = 0; i < calculator.size(); i++){
				calculator.appendElement( calculator.getNode(i), priority);
			}
		}
		else{
			// Is any of them holding Destiny Knot?
			int numElements =	currentKey.equals("dd") ? 5 :
								currentKey.equals("dn") ? 5 :
								currentKey.equals("nd") ? 5 : 3;
			int[] indices = { PokemonCommand.HP_A_INDEX, PokemonCommand.ATK_A_INDEX, PokemonCommand.DEF_A_INDEX,
							PokemonCommand.SPA_A_INDEX, PokemonCommand.SPD_A_INDEX, PokemonCommand.SPE_A_INDEX};
			calculator = new NodePermutationCalculator( indices, numElements);
			// Create copies of the permutations
			List<List<Integer>> permutationsCopy_l = new ArrayList();
			List<List<Integer>> permutationsCopy_r = new ArrayList();
			for( int i = 0; i < calculator.size(); i++){
				permutationsCopy_l.add( convertToList( calculator.getNode(i).getElements()));
				permutationsCopy_r.add( convertToList( calculator.getNode(i).getElements()));
			}
			// In this particular program, we only need to replace values for the "right" side copy
			replace( permutationsCopy_r);
			calculator.expandReplacePermutationsValues(permutationsCopy_l, permutationsCopy_r);
		}
		
		return calculator;
	}
	
	// Marble approach
	private NodePermutationCalculator marbleNewCalculator(){
		NodePermutationCalculator calculator;
		int[] indices = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		int[] split_a = { 0, 1, 2, 3, 4, 5};
		int[] split_b = { 6, 7, 8, 9, 10, 11};
		int numElements;
		// Two power items
		if( currentKey.matches("[0-5][0-5]")){
			numElements = 2;
			int priority_a = Character.getNumericValue( currentKey.charAt(0));
			int priority_b = Character.getNumericValue( currentKey.charAt(1)) + 6;
			calculator = new NodePermutationCalculator( indices, numElements);
			calculator.splitNodes( split_a, split_b);
			for( int i = 0; i < calculator.size(); i++){
				List<Integer> permutation = calculator.get(i);
				// "Invalid" set. Remove it, then decrement counter to correctly iterate through list again
				if( permutation.contains( priority_a) && permutation.contains( priority_b)){
					calculator.remove(i--);
				}
				else if( permutation.contains( priority_a)){
					calculator.appendElement( calculator.getNode(i), priority_b);
				}
				else if( permutation.contains( priority_b)){
					calculator.appendElement( calculator.getNode(i), priority_a);
				}
				else{
					calculator.appendElement( calculator.getNode(i), priority_a, priority_b);
				}
			}
		}	
		// Power item held by first Pokemon or held by second Pokemon
		else if( currentKey.matches("[0-5]d")
		|| currentKey.matches("[0-5]n")
		|| currentKey.matches("d[0-5]")
		|| currentKey.matches("n[0-5]")){
			int priority;
			// Was Power item held by first Pokemon?
			if( currentKey.matches("[0-5]d") || currentKey.matches("[0-5]n")){
				// This be denoted as the "master" element
				priority = Character.getNumericValue( currentKey.charAt(0));
				// Find out how many remaining number of elements will be passed to the child
				// Is second Pokemon holding Destiny Knot or No Item?
				numElements = (currentKey.charAt(1) == 'd') ? 4 : 2;
			}
			// Or was Power item held by second Pokemon?
			else{
				// This be denoted as the "master" element
				priority = Character.getNumericValue( currentKey.charAt(1)) + 6;
				// Find out how many remaining number of elements will be passed to the child
				// Is first Pokemon holding Destiny Knot or No Item?
				numElements = (currentKey.charAt(0) == 'd') ? 4 : 2;
			}
			int[] indices_priority = new int[indices.length-1];
			int count = 0;
			// Copy all elements except for the master element
			for( int i = 0; i < indices.length; i++){
				if( i != priority){
					indices_priority[count++] = i;
				}
			}
			// New calculator
			calculator = new NodePermutationCalculator( indices_priority, numElements);
			calculator.splitNodes( split_a, split_b);
			// Go through every node and add the master element
			for( int i = 0; i < calculator.size(); i++){
				calculator.appendElement( calculator.getNode(i), priority);
			}
		}
		else{
			// Is any of them holding Destiny Knot?
			numElements =	currentKey.equals("dd") ? 5 :
							currentKey.equals("dn") ? 5 :
							currentKey.equals("nd") ? 5 : 3;
			calculator = new NodePermutationCalculator( indices, numElements);
			calculator.splitNodes( split_a, split_b);
		}
		
		return calculator;
	}
	
	private void replace( List<List<Integer>> list){
		for (List<Integer> list1 : list) {
			// For each numerical value, replace it with Pokemon B's corresponding index
			// the advantage we have is that there are no duplicate values
			if( list1.contains(PokemonCommand.HP_A_INDEX)){
				int index = list1.indexOf(PokemonCommand.HP_A_INDEX);
				list1.remove(index);
				list1.add(index, PokemonCommand.HP_B_INDEX);
			}
			if( list1.contains(PokemonCommand.ATK_A_INDEX)){
				int index = list1.indexOf(PokemonCommand.ATK_A_INDEX);
				list1.remove(index);
				list1.add(index, PokemonCommand.ATK_B_INDEX);
			}
			if( list1.contains(PokemonCommand.DEF_A_INDEX)){
				int index = list1.indexOf(PokemonCommand.DEF_A_INDEX);
				list1.remove(index);
				list1.add(index, PokemonCommand.DEF_B_INDEX);
			}
			if( list1.contains(PokemonCommand.SPA_A_INDEX)){
				int index = list1.indexOf(PokemonCommand.SPA_A_INDEX);
				list1.remove(index);
				list1.add(index, PokemonCommand.SPA_B_INDEX);
			}
			if( list1.contains(PokemonCommand.SPD_A_INDEX)){
				int index = list1.indexOf(PokemonCommand.SPD_A_INDEX);
				list1.remove(index);
				list1.add(index, PokemonCommand.SPD_B_INDEX);
			}
			if( list1.contains(PokemonCommand.SPE_A_INDEX)){
				int index = list1.indexOf(PokemonCommand.SPE_A_INDEX);
				list1.remove(index);
				list1.add(index, PokemonCommand.SPE_B_INDEX);
			}	
		}
	}
	
	// Method for convenience
	private List<Integer> convertToList( int[] elements){
		if( elements == null || elements.length == 0)
			return null;
		List<Integer> list = new ArrayList();
		for( int i = 0; i < elements.length; i++){
			list.add(elements[i]);
		}	
		return list;
	}
	
	// Method for convenience
	private int[] convertToArray( List<Integer> elements){
		if( elements == null || elements.isEmpty())
			return null;
		int[] elements_new = new int[elements.size()];
		for( int i = 0; i < elements_new.length; i++){
			elements_new[i] = elements.get(i);
		}
		return elements_new;
	}
	
	public void updateCurrentKey(){
		String a = (type_a == CalcType.DESTINYKNOT) ?	"d" :
					(type_a == CalcType.POWERHP)	?	"0" :
					(type_a == CalcType.POWERATK)	?	"1" :
					(type_a == CalcType.POWERDEF)	?	"2" :
					(type_a == CalcType.POWERSPA)	?	"3" :
					(type_a == CalcType.POWERSPD)	?	"4" :
					(type_a == CalcType.POWERSPE)	?	"5" : "n";
		String b = (type_b == CalcType.DESTINYKNOT) ?	"d" :
					(type_b == CalcType.POWERHP)	?	"0" :
					(type_b == CalcType.POWERATK)	?	"1" :
					(type_b == CalcType.POWERDEF)	?	"2" :
					(type_b == CalcType.POWERSPA)	?	"3" :
					(type_b == CalcType.POWERSPD)	?	"4" :
					(type_b == CalcType.POWERSPE)	?	"5" : "n";
		System.out.println( currentKey);
		currentKey = a + b;
	}
	
	public void setCaclTypeA( CalcType type){	type_a = type;	}
	public void setCaclTypeB( CalcType type){	type_b = type;	}
	public void setEverstone( boolean e){	everstone = e;	}
	public void setNoItem( boolean i){	noitem = i;	}
	public void setSlotApproach( boolean sa){	slotApproach = sa;	}
	
	public NodePermutationCalculator getCalculatorAt( String key){
		return calculators.get(key);
	}
	
	public boolean hasEverstone(){	return everstone;	}
	public boolean hasNoItem(){	return noitem;	}
	public boolean usingSlotApproach(){	return slotApproach;	}
	public boolean usingMarbleApproach(){	return !slotApproach;	}
	public String getCurrentKey(){	return currentKey;	}
	
	public enum CalcType{
		DEFAULT,
		DESTINYKNOT,
		POWERHP,
		POWERATK,
		POWERDEF,
		POWERSPA,
		POWERSPD,
		POWERSPE,
		EVERSTONE
	}
}
