package edu.ncsu.csc216.collections;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This class extends AbstractSequentialList by implementing a double-linked list
 * that is navigated by a ListIterator.
 * 
 * @author Aurora Bravo
 * 
 * @param <E>
 * 		Generic element
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	
	/**
	 * This class creates a ListNode within our double-linked list.
	 * 
	 * @author Walton Surratt
	 * 
	 */
	private class ListNode {
		
		/**
		 * Element data
		 */
		public E data;
		/**
		 * Previous Node in the list
		 */
		public ListNode prev;
		/**
		 * Next Node in the list
		 */
		public ListNode next;
		
		/**
		 * This constructor creates a Node to be used in a double-linked list.
		 * The single parameter is used to create an element data Node without
		 * a previous or next Node.
		 * 
		 * @param data
		 * 		Element data
		 */
		public ListNode(E data){
			this(data, null, null);
		}
		/**
		 * This constructor creates a Node to be used in a double-linked list.
		 * The first parameter sets the element data, the second parameter sets
		 * the reference to the previous Node in the list, and the third 
		 * parameter sets the reference to the next Node in the list.
		 * 
		 * @param data
		 * 		Element data
		 * @param prev
		 * 		Previous Node in the list
		 * @param next
		 * 		Next Node in the list
		 */
		public ListNode(E data, ListNode prev, ListNode next){
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
		
	}
	
	/**
	 * This class implements the ListIterator class that is used for
	 * navigation through a double-linked list.
	 * 
	 * @author Walton Surratt
	 * 
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/**
		 * Previous Node in the list
		 */
		private ListNode prevNode;
		/**
		 * Next Node in the list
		 */
		private ListNode nextNode;
		/**
		 * Index of the previous Node in the list
		 */
		private int prevIndex;
		/**
		 * Index of the next Node in the list
		 */
		private int nextIndex;
		/**
		 * Stores the name of the last called method.
		 * Used as a flag for Set and Remove methods.
		 */
		private String lastCalled;
		
		/**
		 * Constructs a LinkedListIterator to navigate the list.
		 * No parameter was passed, therefore this ListIterator starts at index 0.
		 */
		public LinkedListIterator(){
			this(0);
		}
		
		/**
		 * Constructs a LinkedListIterator to navigate the list.
		 * The parameter passed determines the starting index of the ListIterator.
		 * 
		 * @param index
		 * 		Index where the ListIterator starts
		 */
		public LinkedListIterator(int index){
			//ListIterator starts with two padding Nodes, therefore there at N+1 locations
			//for the ListIterator where N = size of the list.
			if(index < 0 || index > LinkedList.this.size){
				throw new IndexOutOfBoundsException("Invalid index for the list!");
			}
			
			//Iterate through list until it reaches the specified index
			ListNode current = LinkedList.this.front;
			for(int i = 0; i < index; i++){
				current = current.next;
			}
			//Update ListIterator fields for where the iterator starts
			this.prevNode = current;
			this.nextNode = current.next;
			this.prevIndex = index;
			this.nextIndex = index + 1;
		}
		
		/**
		 * Adds an element to the list.
		 * This adds an element between the previous Node and next Node
		 * in the ListIterator. It works by changing the previous Node to
		 * a new Node with the defined element data, updating all the
		 * necessary references and index, then incrementing the list size.
		 * 
		 * @param element
		 * 		Generic element being added to the list
		 */
		@Override
		public void add(E element) {
			//Throws an Exception for a null element
			if(element == null){
				throw new IllegalArgumentException("Cannot add null element!");
			}
			
			//Creates the new Node, then updates all references including index
			this.prevNode = new ListNode(element, this.prevNode, this.nextNode);
			this.prevNode.prev.next = this.prevNode;
			this.nextNode.prev = this.prevNode;
			this.prevIndex = this.nextIndex;
			this.nextIndex = this.nextIndex + 1;
			
			//Increments list size
			LinkedList.this.size = LinkedList.this.size + 1;
			
			//Flags this method as being the last one called
			this.lastCalled = "add";
		}
		
		/**
		 * Checks if the list has an element at the next Node.
		 * If the element at the next Node is null, then it means
		 * the ListIterator has reached the back of the list.
		 * 
		 * @return
		 * 		True, if there is a non-null element at the next Node
		 * 		False, if there is a null element at the next Node
		 */
		@Override
		public boolean hasNext() {
			return this.nextNode.data != null;
		}
		
		/**
		 * Checks if the list has an element at the previous Node.
		 * If the element at the previous Node is null, then it means
		 * the ListIterator has reached the front of the list.
		 * 
		 * @return
		 * 		True, if there is a non-null element at the previous Node
		 * 		False, if there is a null element at the previous Node
		 */
		@Override
		public boolean hasPrevious() {
			return this.prevNode.data != null;
		}

		/**
		 * Moves to the next position between-nodes by passing and returning
		 * the next Node in the list, and updating all necessary references.
		 * 
		 * @return
		 * 		Element data at the next Node
		 */
		@Override
		public E next() {
			//Throws an Exception if ListIterator reaches the back of the list
			if(this.nextNode.data == null){
				throw new NoSuchElementException("You've reached the back of the list!");
			}
			
			//Move iterator forward by one location between-nodes
			this.prevNode = this.nextNode;
			this.nextNode = this.nextNode.next;
			this.prevIndex = this.nextIndex;
			this.nextIndex = this.nextIndex + 1;
			
			//Flags this method as being the last one called
			this.lastCalled = "next";
			
			return this.prevNode.data;
		}

		/**
		 * Gets the index of the next Node.
		 * 
		 * @return
		 * 		Index of the next Node
		 */
		@Override
		public int nextIndex() {
			return this.nextIndex;
		}

		/**
		 * Moves to the previous position between-nodes by passing and returning
		 * the previous Node in the list, and updating all necessary references.
		 * 
		 * @return
		 * 		Element data at the previous Node
		 */
		@Override
		public E previous() {
			//Throws an Exception if ListIterator reaches the front of the list
			if(this.prevNode.prev.data == null){
				throw new NoSuchElementException("You've reached the front of the list!");
			}
			
			//Move iterator back by one location between nodes
			this.nextNode = this.prevNode;
			this.prevNode = this.prevNode.prev;
			this.nextIndex = this.prevIndex;
			this.prevIndex = this.prevIndex - 1;
			
			//Flags this method as being the last one called
			this.lastCalled = "previous";
			
			return this.nextNode.data;
		}

		/**
		 * Gets the index of the previous Node.
		 * 
		 * @return
		 * 		Index of the previous Node
		 */
		@Override
		public int previousIndex() {
			return this.prevIndex;
		}

		/**
		 * Removes an element from the list based on the last call to 
		 * next() or previous(). This method can only be used if the
		 * add() method was not called after the last call to next()
		 * or previous().
		 */
		@Override
		public void remove() {
			//Remove an element based on which was called last: next() or previous().
			//If next() was called last, remove the previous Node
			//Else, If previous() was called last, remove the next Node
			if(this.lastCalled.equals("next")){
				this.prevNode = this.prevNode.prev;
			}else if(this.lastCalled.equals("previous")) {
				this.nextNode = this.nextNode.next;
			}
			//Update other references
			this.prevNode.next = this.nextNode;
			this.nextNode.prev = this.prevNode;
			
			//Decrement list size
			LinkedList.this.size = LinkedList.this.size - 1;
			
			//Flags this method as being the last one called
			this.lastCalled = "remove";
		}
		
		/**
		 * Sets an element to a new value in the linked list.
		 * This method can only be used as long as neither the
		 * add() nor remove() methods were called after the last
		 * call to next() or previous().
		 * 
		 * @param element
		 * 		Element data being set at either the previous() or next() position
		 */
		@Override
		public void set(E element) {
			//Throws an Exception for a null element
			if(element == null){
				throw new IllegalArgumentException("Cannot set to a null element!");
			}
			
			//Set an element based on which was called last: next() or previous().
			//If next() was called last, change the previous Node
			//Else, If previous() was called last, change the next Node
			if(this.lastCalled.equals("next")){
				this.prevNode.data = element;
			}else if(this.lastCalled.equals("previous")) {
				this.nextNode.data = element;
			}
		}
		
		
		
	}
	
	//LinkedList<E> class fields, constructor, and methods start here
	/**
	 * Padding Node at the front of the double-linked list
	 */
	private ListNode front;
	/**
	 * Padding Node at the back of the double-linked list
	 */
	private ListNode back;
	/**
	 * Size of the list
	 */
	private int size;
	
	/**
	 * Constructs a Linked List of a generic type that is double-linked
	 * and requires a ListIterator to navigate it. All lists start out
	 * empty with a padding Node at the front and back of the list to
	 * mark its beginning and end.
	 */
	public LinkedList(){
		this.front = this.new ListNode(null);
		this.back = this.new ListNode(null);
		this.front.next = this.back;
		this.back.prev = this.front;
		this.size = 0;
	}
	
	/**
	 * Constructs a ListIterator to navigate a double-linked list.
	 * This ListIterator does not have a set index, and starts at
	 * the beginning of the list (index 0).
	 * 
	 * @return
	 * 		ListIterator of generic element type
	 */
	@Override
	public ListIterator<E> listIterator() {
		return this.new LinkedListIterator();
	}
	
	/**
	 * Constructs a ListIterator to navigate a double-linked list.
	 * This ListIterator has one parameter, which sets the starting
	 * index of the iterator.
	 * 
	 * @param index
	 * 		Index of where the ListIterator will start
	 * 
	 * @return
	 * 		ListIterator of generic element type starting at specified index
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return this.new LinkedListIterator(index);
	}

	/**
	 * Gets the size of the double-linked list.
	 * 
	 * @return
	 * 		Size of the list
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	
	
}
