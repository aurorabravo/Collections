package edu.ncsu.csc216.collections;

/**
 * 
 * @author Aurora Bravo
 * 
 * This class creates List Nodes for linked String lists, and
 * each node contains a String element and the next node.
 */
public class ListNode {
	
	/**
	 * String element data
	 */
	public String data;
	
	/**
	 * The next node in the list
	 */
	public ListNode next;
	
	/**
	 * Constructor for a list node in a linked string list.
	 * This constructor has only one parameter, and implies
	 * the next node in the linked list has not been created and
	 * is therefore null. The one parameter passed is the string
	 * element to contain as data within the list node.
	 * 
	 * @param element
	 * 		String element for the list node data
	 */
	public ListNode(String element){
		this(element, null);
	}
	
	/**
	 * Constructor for a list node in a linked string list.
	 * This constructor has two parameters: the first parameter is the string
	 * element to contain as data within the list node, and the second parameter
	 * is the next node for the linked list.
	 * 
	 * @param element
	 * 		String element for the list node data
	 * @param node
	 * 		The next node in a linked list
	 */
	public ListNode(String element, ListNode node){
		this.data = element;
		this.next = node;
	}
}
