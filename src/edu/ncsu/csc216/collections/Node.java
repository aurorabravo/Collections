package edu.ncsu.csc216.collections;

/**
 * 
 * @author Aurora Bravo
 * 
 * This class creates a node of a generic object type
 * for a linked list.
 * 
 * @param <E>
 * 		Generic type object
 */
public class Node<E> {
	
	/**
	 * Data of a generic type element
	 */
	public E data;
	
	/**
	 * Node of a generic type object
	 */
	public Node<E> next;
	
	/**
	 * Constructor for a node in a generic linked list.
	 * This constructor has only one parameter, and implies
	 * that the next node in the generic linked list has not
	 * been created and is therefore null. The one parameter
	 * passed is the generic type element to contain as data
	 * within the node.
	 * 
	 * @param element
	 * 		Generic type element for the node data
	 */
	public Node(E element){
		this(element, null);
	}
	
	/**
	 * Constructor for a node in a generic linked list.
	 * This constructor has two parameters: the first parameter is the generic
	 * type element for the node data, and the second parameter is the next node
	 * for the generic linked list.
	 * 
	 * @param element
	 * 		Generic type element for the node data
	 * @param node
	 * 		Next node in a generic linked list
	 */
	public Node(E element, Node<E> node){
		this.data = element;
		this.next = node;
	}
}
