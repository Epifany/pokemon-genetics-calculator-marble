/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */
package org.epifany.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is an extension of PermutationCalculator.
 * In addition to the functions of PermutationCalculator, this class
 * is capable of acknowledging certain form of splits (vague)
 * 
 * @author Stephen Gung
 */
public class NodePermutationCalculator extends PermutationCalculator {
	// Our set of nodes. The ith node corresponds with the ith permutation
	protected final List<Node> nodes;
	
	/**
	 * Constructor method with elements from 0 through (elmts - 1)
	 * @param elmts - Signifies the elements to be used
	 * @param lim - Signifies the amount of elements contained per permutation
	 */
	public NodePermutationCalculator( int elmts, int lim){
		super( elmts, lim);
		nodes = new ArrayList( permutations.size());
		for( List<Integer> permutation : permutations){
			int[] temp = new int[ permutation.size()];
			for( int j = 0; j < temp.length; j++){
				temp[j] = permutation.get(j);
			}
			nodes.add( new Node( null, temp));
		}
	}
	
	/**
	 * Constructor method with a specified input of elements
	 * @param elmts - The array of elements
	 * @param lim - Signifies the amount of elements contained per permutation
	 */
	public NodePermutationCalculator( int[] elmts, int lim){
		super( elmts, lim);
		nodes = new ArrayList( permutations.size());
		for( List<Integer> permutation : permutations){
			int[] temp = new int[ permutation.size()];
			for( int j = 0; j < temp.length; j++){
				temp[j] = permutation.get(j);
			}
			nodes.add( new Node( null, temp));
		}
	}

	/**
	 * Copy constructor method
	 * @param ncc - The NodePermutationCalculator object to be copied
	 */
	public NodePermutationCalculator( NodePermutationCalculator ncc){
		super( ncc);
		nodes = new ArrayList( ncc.nodes);
	}
	
	public boolean expandReplacePermutationsValues( List<List<Integer>> indices_a, List<List<Integer>> indices_b){
		if( indices_a == null || indices_b == null)
			return false;
		// Check to see if all sizes are the same
		if( indices_a.size() != indices_b.size() && indices_a.size() != nodes.size())
			return false;
		
		int height = 0;
		for( int n = 0; n < nodes.size(); n++){
			expandReplacePermutationValues( nodes.get(n), height, indices_a.get(n), indices_b.get(n));
		}
		
		return true;
	}
	
	private void expandReplacePermutationValues( Node node, int index, List<Integer> indices_a, List<Integer> indices_b){
		if( index >= indices_a.size())
			return;
		// Get elements of soon to be parent
		int[] elements_p = node.getElements();
		// Copy elememts for left and right child
		int[] elements_l = new int[elements_p.length];
		System.arraycopy(elements_p, 0, elements_l, 0, elements_p.length);
		int[] elements_r = new int[elements_p.length];
		System.arraycopy(elements_p, 0, elements_r, 0, elements_p.length);
		// Replace respective values
		elements_l[index] = indices_a.get(index);
		elements_r[index] = indices_b.get(index);
		// Create left and right child
		Node leftChild = new Node( node, elements_l);
		Node rightChild = new Node( node, elements_r);
		
		node.setLeftChild( leftChild);
		node.setRightChild( rightChild);
		// Do the same for left and right child
		expandReplacePermutationValues( leftChild, index+1, indices_a, indices_b);
		expandReplacePermutationValues( rightChild, index+1, indices_a, indices_b);
	}
	
	public boolean splitNodes( int[] split1, int[] split2){
		if( split1.length != split2.length)
			return false;
		boolean splitNodes = false;
		
		LinkedList<Node> queue = new LinkedList();
		for( Node node : nodes){
			queue.add( node);
		}
		// At this point we know all the nodes are leaf nodes
		while( !queue.isEmpty()){
			Node target = queue.pop();
			for( int i = 0; i < split1.length; i++){
				int s1 = split1[i];
				int s2 = split2[i];
				int[] elements_target = target.getElements();
				int index1 = -1;
				int index2 = -1;
				boolean proceedToSplit = false;
				// The purpose of the following for loop is to find the indices of two specific values
				// If we do find these two specific values, we'll then create two nodes
				// Each of which contains one of these specific values
				for( int t = 0; t < elements_target.length; t++){
					// If we found the first occurrence of the target value
					if( elements_target[t] == s1 && index1 == -1){
						index1 = t;
					}
					// If we found the first occurrence of the target value
					// And this target value is of a different target
					if( elements_target[t] == s2 && index2 == -1 && elements_target[t] != s1){
						index2 = t;
					}
					// if( elements_target[i] == e2 && index2 == -1 && index1 != -1){
					//	index2 = i
					// }
					// If we found the indices with which to split
					if( index1 != -1 && index2 != -1){
						proceedToSplit = true;
						splitNodes = true;
						break;
					}
				}
				if( proceedToSplit){
					// We want to split them into left and right nodes
					int[] elements_child = new int[ elements_target.length - 1];
					int count = 1;
					// Copy each stat, except for the indices that are used for the split
					for( int n = 0; n < elements_target.length; n++){
						if( n != index1 && n != index2){
							elements_child[count++] = elements_target[n];
						}
					}
					// Create child nodes. Head of the list contain the split value
					elements_child[0] = elements_target[index1];
					target.setLeftChild( new Node( target, elements_child));
					queue.add(target.getLeftChild());
					
					elements_child[0] = elements_target[index2];
					target.setRightChild( new Node( target, elements_child));
					queue.add(target.getRightChild());
					break;
				}
			}
		}
		return splitNodes;
	}
	
	public void appendElement( Node target, int e1, int e2){
		if( target.isLeaf()){
			int[] elements_target = target.getElements();
			// Make new array to accomodate new element
			int[] elements_child = new int[ elements_target.length + 1];
			System.arraycopy( elements_target, 0, elements_child, 0, elements_target.length);
			// Append element for left child
			elements_child[ elements_child.length - 1] = e1;
			target.setLeftChild( new Node( target, elements_child));
			// Append element for right child
			elements_child[ elements_child.length - 1] = e2;
			target.setRightChild( new Node( target, elements_child));
		}
		else{
			appendElement( target.getLeftChild(), e1, e2);
			appendElement( target.getRightChild(), e1, e2);
		}
	}
	
	public void appendElement( Node target, int e){
		if( target.isLeaf()){
			int[] elements_target = target.getElements();
			// Make new array to accomodate new element
			int[] elements_new = new int[ elements_target.length + 1];
			System.arraycopy( elements_target, 0, elements_new, 0, elements_target.length);
			// Append element
			elements_new[ elements_new.length - 1] = e;
			target.setElements( elements_new);
		}
		else{
			appendElement( target.getLeftChild(), e);
			appendElement( target.getRightChild(), e);
		}
	}
	
	// Method for adding nodes
	// Note: use this method with caution, it directly adds additional nodes
	// without regard for the elements & limit of the calculator it is getting the nodes from
	public boolean addNodes( NodePermutationCalculator npc){
		if( npc == null)
			return false;
		for( int i = 0; i < npc.size(); i++){
			permutations.add( npc.get(i));
			nodes.add( npc.getNode(i));
		}
		
		return true;
	}
	
	// Returns the node associated with the ith permutation
	public Node getNode( int index){
		return nodes.get(index);
	}
	
	@Override
	public List<Integer> remove( int index){
		nodes.remove(index);
		return super.remove(index);
	}
	
	public void debug(){
		for (Node node : nodes) {
			printNode(node);
			System.out.println("");
		}
	}
	
	private void printNode( Node target){
		if( target.isLeaf()){
			System.out.print( Arrays.toString( target.getElements()));
		}
		else{
			System.out.print( "( ");
			printNode( target.getLeftChild());
			System.out.print( ", ");
			printNode( target.getRightChild());
			System.out.print( " )");
		}
	}
}