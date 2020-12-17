/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */
package org.epifany.permutation;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for computing all possible permutations of
 * a given set of elements. Duplicate elements are not excluded;
 * computation will treat each element as a unique entity.
 * 
 * @author Stephen Gung
 */
public class PermutationCalculator {
	// This is where we'll hold all our set of permutations
	protected final List<List<Integer>> permutations;
	// The elements used for computing permutations
	protected final int[] elements;
	// The amount of elements each set (of permutations) may contain
	protected final int limit;
	
	/**
	 * Constructor method with elements from 0 through (elmts - 1)
	 * @param elmts - Signifies the elements to be used
	 * @param lim - Signifies the amount of elements contained per permutation
	 */
	public PermutationCalculator( int elmts, int lim){
		permutations = new ArrayList();
		elements = new int[elmts];
		limit = lim;
		// Setup for cNr formula
		List<Integer> bucket = new ArrayList( elmts);
		for( int i = 0; i < elmts; i++){
			elements[i] = i;
			bucket.add(i);
		}
		// Recursively compute
		permutation( new ArrayList(), bucket);
	}
	
	/**
	 * Constructor method with a specified input of elements
	 * @param elmts - The array of elements
	 * @param lim - Signifies the amount of elements contained per permutation
	 */
	public PermutationCalculator( int[] elmts, int lim){
		permutations = new ArrayList();
		elements = new int[elmts.length];
		limit = lim;
		// Setup for cNr formula
		ArrayList<Integer> bucket = new ArrayList( elmts.length);
		for( int i = 0; i < elmts.length; i++){
			elements[i] = elmts[i];
			bucket.add( elmts[i]);
		}
		// Recursively compute
		permutation( new ArrayList(), bucket);
	}
	
	/**
	 * Copy constructor method
	 * @param cc - The PermutationCalculator object to be copied
	 */
	public PermutationCalculator( PermutationCalculator cc){
		permutations = new ArrayList( cc.permutations);
		elements = new int[ cc.elements.length];
		System.arraycopy(cc.elements, 0, elements, 0, elements.length);
		limit = cc.limit;
	}
	
	/**
	 * Recursively computes for all possible permutations
	 * @param potential - The current set of permutation
	 * @param bucket - The current elements left for extraction
	 */
	private void permutation( List<Integer> potential, List<Integer> bucket){
		// If we've reached the maximum amount of elements permitted for a permutation
		if( potential.size() >= limit){
			// Add to our collection of permutationss
			permutations.add(new ArrayList(potential));
			return;
		}
		// Return if empty bucket or if further computations won't complete a set
		if( bucket.isEmpty() || ((potential.size() + bucket.size()) < limit)){
			return;
		}
		// Create a local copy
		List<Integer> bucket_local = new ArrayList( bucket);
		while( !bucket_local.isEmpty()){
			// Extract first element
			potential.add(bucket_local.remove(0));
			// Recursively compute
			permutation( potential, bucket_local);
			// Remove the extracted element for next iteration
			potential.remove(potential.size()-1);
		}
	}
	
	/**
	 * Determines whether list exists within the set of permutations
	 * @param list - The list to be searched
	 * @return true if list is found, false otherwise
	 */
	public boolean contains( List<Integer> list){
		for( List<Integer> permutation : permutations){
			if( permutation.equals(list)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes the permutation at the specified index
	 * @param index - The specified index
	 * @return the permutation that was removed
	 */
	public List<Integer> remove( int index){
		return permutations.remove(index);
	}
	
	/**
	 * Returns the permutation at the specified index
	 * @param index - The specified index
	 * @return the permutation at the specified index
	 */
	public List<Integer> get( int index){
		return permutations.get(index);
	}
	
	/**
	 * Returns the elements that were used for computing permutations
	 * @return The elements
	 */
	public int[] getElements(){
		return elements;
	}
	
	/**
	 * Returns the number representing the amount of elements a single permutation can hold
	 * @return The amount of elements per permutation
	 */
	public int getLimit(){
		return limit;
	}
	
	/**
	 * Returns the amount of permutations there are
	 * @return The number of permutations
	 */
	public int size(){
		return permutations.size();
	}
}