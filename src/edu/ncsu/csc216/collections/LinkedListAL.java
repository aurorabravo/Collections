package edu.ncsu.csc216.collections;

import java.util.AbstractList;

/**
 * 
 * @author Aurora Bravo
 * 
 * This class creates a generic linked list that uses nodes that
 * contain generic object data, and also extends the AbstractList class.
 *
 * @param <E>
 * 		Generic Linked List
 */
public class LinkedListAL<E> extends AbstractList<E> {
	
	/**
	 * First Node in the Linked List
	 */
	public Node<E> front;
	
	/**
	 * Size of the list
	 */
	public int size;
	
	/**
	 * Constructor for a generic linked list.
	 * This constructor has no parameters, and is used to
	 * create an empty linked list.
	 */
	public LinkedListAL(){
		this.front = null;
		this.size = 0;
	}
	
	/**
	 * Constructor for a generic linked list.
	 * This constructor has only one parameter, which as the first node
	 * of the linked list, which acts as the front node. It then
	 * calculates the size of the list based on the first node.
	 * 
	 * @param firstNode
	 * 		Node at the front/first in the LinkedList
	 */
	public LinkedListAL(Node<E> first){
		this.front = first;
		Node<E> current = this.front;
		int count = 1;
		while(current.next != null){
			current = current.next;
			count++;
		}
		this.size = count;
	}
	
	/**
	 * Adds a generic type element to the linked list at a specific index.
	 * The list size is incremented, and the list references are updated
	 * as the generic type element is added to the linked list.
	 * 
	 * @param index
	 * 		Index where the user wants to add a generic type element
	 * @param element
	 * 		Generic type element the user wants to add to the linked list
	 */
	@Override
	public void add(int index, E element) {
		if(index < 0 || index > this.size){
			throw new IndexOutOfBoundsException("Index outside of list!");
		}
		
		if(element == null){
			throw new NullPointerException("Element cannot be null!");
		}
		
		if(index == 0){
			this.front = new Node<E>(element, this.front);
		}else{
			Node<E> current = this.front;
			for(int i = 0; i < index - 1; i++){
				current = current.next;
			}
			current.next = new Node<E>(element, current.next);
		}
		
		this.size = this.size + 1;
	}
	
	/**
	 * Retrieves the generic type element at a specific index.
	 * 
	 * @param index
	 * 		Index where the user wants to get the generic type element
	 * 
	 * @return
	 * 		Generic type element at the specified index
	 */
	@Override
	public E get(int index) {
		if(index < 0 || index > this.size - 1){
			throw new IndexOutOfBoundsException("Index outside of list!");
		}
		
		Node<E> current = this.front;
		for(int i = 0; i < index; i++){
			current = current.next;
		}
		return current.data;
	}
	
	/**
	 * Removes a generic type element from the linked list at a specific index.
	 * The list size is decremented, and the list references are updated when
	 * the generic type element is removed from the linked list.
	 * 
	 * @param index
	 * 		Index of the generic type element the user wants to remove
	 * 
	 * @return
	 * 		Generic type element being removed
	 */
	@Override
	public E remove(int index) {
		if(index < 0 || index > this.size - 1){
			throw new IndexOutOfBoundsException("Index outside of list!");
		}
		
		E oldElement = null;
		
		if(index == 0){
			oldElement = this.front.data;
			this.front = this.front.next;
		}else{
			Node<E> current = this.front;
			for(int i = 0; i < index - 1; i++){
				current = current.next;
			}
			oldElement = current.next.data;
			current.next = current.next.next;
		}
		
		this.size = this.size - 1;
		return oldElement;
	}
	
	/**
	 * Sets the data of a generic type element at a specific index.
	 * 
	 * @param index
	 * 		Index of the generic type element that the user wants to change
	 * @param element
	 * 		New generic type element that will be set at the specified index
	 * 
	 * @return
	 * 		Data of the generic type element before it changed
	 */
	@Override
	public E set(int index, E element) {
		if(index < 0 || index > this.size - 1){
			throw new IndexOutOfBoundsException("Index outside of list!");
		}
		
		if(element == null){
			throw new NullPointerException("Element cannot be null!");
		}
		
		Node<E> current = this.front;
		for(int i = 0; i < index; i++){
			current = current.next;
		}
		E oldElement = current.data;
		current.data = element;
		return oldElement;
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
