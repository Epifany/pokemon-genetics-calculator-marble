/**
 * Copyright 2020, Stephen Gung, All rights reserved
 */
package org.epifany.permutation;

/**
 * This class represents a node containing references to a
 * parent, left child, right child,
 * and contains an array of elements.
 * 
 * @author Stephen Gung
 */
public class Node {
	private Node parent;
	private Node child_left;
	private Node child_right;
	private int[] elements;
	
	/**
	 * Constructor method
	 * @param p - The parent node
	 * @param e - The array of elements
	 */
	public Node( Node p, int[] e){
		parent = p;
		elements = new int[ e.length];
		System.arraycopy(e, 0, elements, 0, e.length);
	}
	
	/**
	 * Sets this node's parent reference to the given parameter
	 * @param p - The parent node
	 */
	public void setParent( Node p){
		parent = p;
	}
	
	/**
	 * Sets this node's left child reference to the given parameter
	 * @param l - The left child node
	 */
	public void setLeftChild( Node l){
		child_left = l;
	}
	
	/**
	 * Sets this node's right child reference to the given parameter
	 * @param r - The right child node
	 */
	public void setRightChild( Node r){
		child_right = r;
	}
	
	/**
	 * Sets this node's array of elements to the given parameter
	 * @param e - The array of elements
	 */
	public void setElements( int[] e){
		elements = e;
	}
	
	/**
	 * @return this node's parent
	 */
	public Node getParent(){
		return parent;
	}
	
	/**
	 * @return this node's left child
	 */
	public Node getLeftChild(){
		return child_left;
	}
	
	/**
	 * @return this node's right child
	 */
	public Node getRightChild(){
		return child_right;
	}
	
	/**
	 * Determines if this node is a leaf
	 * @return true if this node contains neither a left nor right child, false otherwise.
	 */
	public boolean isLeaf(){
		return ((child_left == null) && (child_right == null));
	}
	
	/**
	 * Determines if this node has a parent
	 * @return true if contains a parent, false otherwise
	 */
	public boolean hasParent(){
		return (parent != null);
	}
	
	/**
	 * Returns the array of elements this node is using
	 * @return the array of elements
	 */
	public int[] getElements(){
		return elements;
	}
}
