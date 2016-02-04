package edu.ncsu.csc216.collections;

import java.util.AbstractList;

/**
 * 
 * @author Aurora Bravo
 * 
 * A class used in creating linked lists of String elements,
 * and extends the AbstractList class.
 */
public class LinkedStringList extends AbstractList<String> {
	
	/**
	 * First node in the list
	 */
	private ListNode front;
	
	/**
	 * Size of the list
	 */
	private int size;
	
	/**
	 * Constructor for a linked string list.
	 * This constructor has no parameters, and constructs 
	 * an empty linked list.
	 */
	public LinkedStringList(){
		this.front = null;
		this.size = 0;
	}
	
	/**
	 * Constructor for a linked string list.
	 * This constructor has only one parameter, as the first node of
	 * the linked list, which acts as the front node. It then
	 * calculates the size of the list based on the first node.
	 * 
	 * @param first
	 * 		First node in the linked list
	 */
	public LinkedStringList(ListNode first){
		this.front = first;
		ListNode current = this.front;
		int count = 1;
		while(current.next != null){
			current = current.next;
			count++;
		}
		this.size = count;
	}
	
	/**
	 * Adds a string element to the list at a specific index.
	 * The list size is incremented and the list references are
	 * updated as the string element is added to the linked list.
	 * 
	 * @param index
	 * 		Index of where the user wants to add an element
	 * @param element
	 * 		String element that the user wants to add to the list
	 */
	@Override
	public void add(int index, String element) {
		if(index < 0 || index > this.size ){
			throw new IndexOutOfBoundsException("Index outside of list!");
		}
		
		if(element == null){
			throw new NullPointerException("Element cannot be null!");
		}
		
		if(index == 0){
			this.front = new ListNode(element, this.front);
		}else{
			ListNode current = this.front;
			for(int i = 0; i < index - 1; i++){
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
		}
		
		this.size = this.size + 1;
	}
	
	/**
	 * Retrieves the string element at a specific index.
	 * 
	 * @param index
	 * 		Index where the user wants to get the string element
	 * 
	 * @return
	 * 		The string element at the specified index
	 */
	@Override
	public String get(int index) {
		if(index < 0 || index > this.size - 1){
			throw new IndexOutOfBoundsException("Index outside of list!");
		}
		
		ListNode current = this.front;
		for(int i = 0; i < index; i++){
			current = current.next;
		}
		return current.data;
	}
	
	/**
	 * Removes a string element in the list at a specific index.
	 * The list size is decremented, and the list references
	 * are updated when the string element is removed from
	 * the linked list.
	 * 
	 * @param index
	 * 		Index of the element that the user wants to remove
	 * 
	 * @return
	 * 		String element being removed
	 */
	@Override
	public String remove(int index) {
		if(index < 0 || index > this.size - 1){
			throw new IndexOutOfBoundsException("Index outside of list!");
		}
		
		String oldString = null;
		
		if(index == 0){
			oldString = this.front.data;
			this.front = this.front.next;
		}else{
			ListNode current = this.front;
			for(int i = 0; i < index - 1; i++){
				current = current.next;
			}
			oldString = current.next.data;
			current.next = current.next.next;
		}
		
		this.size = this.size - 1;
		return oldString;
	}

	/**
	 * Sets the value of the string element at a specific index.
	 * 
	 * @param index
	 * 		Index of the element that the user wants to change
	 * @param element
	 * 		New string element that will be set at the specified index
	 * 
	 * @return
	 * 		String value of the element before it changed
	 */
	@Override
	public String set(int index, String element) {
		if(index < 0 || index > this.size - 1){
			throw new IndexOutOfBoundsException("Index outside of list!");
		}
		
		if(element == null){
			throw new NullPointerException("Element cannot be null!");
		}
		
		ListNode current = this.front;
		for(int i = 0; i < index; i++){
			current = current.next;
		}
		String oldString = current.data;
		current.data = element;
		return oldString;
	}
	
	/**
	 * Returns the size of the linked list.
	 * The size is maintained by the constant variable <code>size</code>,
	 * and changes when elements are added and removed.
	 * 
	 * @return
	 * 		Size of the linked list
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	
	
}
