package edu.ncsu.csc216.collections;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Walton Surratt
 *
 */
public class LinkedListALTest {
	
	private LinkedListAL<String> list;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new LinkedListAL<String>();
	}

	/**
	 * Tests that a LinkedList is constructed correctly.  A LinkedList of
	 * any generic type should be not null and empty, which implies a size of 0.
	 * Test method for {@link edu.ncsu.csc216.collections.LinkedListAL#LinkedList()}.
	 */
	@Test
	public void testLinkedList() {
		//Test that the list field is created correctly.
		//Check that the list is not null
		assertNotNull(list);
		//Check the that the list is empty
		assertEquals(0, list.size());
		
		//Test that a LinkedStringList can be created with a
		//constructor parameter for the first node.
		//Create a new LinkedStringList with a first node
		Node<String> firstNode = new Node<String>("Start");
		LinkedListAL<String> list2 = new LinkedListAL<String>(firstNode);
		//Check that the list is not null
		assertNotNull(list2);
		//Check that the size is 1
		assertEquals(1, list2.size());
		
		//Test that a LinkedStringList can determine the proper
		//size of the list if the first node is from a previous
		//list that had more than one element.
		
		//Add element to list2
		list2.add("End");
		//Create new LinkedStringList with list2's first node
		LinkedListAL<String> list3 = new LinkedListAL<String>(firstNode);
		//Check that the list is not null
		assertNotNull(list3);
		//Check that the size is 2
		assertEquals(2, list3.size());
	}

	/**
	 * Tests adding elements to an empty LinkedList.  Then tests adding elements to the 
	 * front, middle, and back of a LinkedList.  The size and contents should be checked
	 * after each insertion.  Additionally, the bounds of the list should be checked and null
	 * elements should not be added to the list.  Finally, test that the ArrayList with an
	 * initial capacity of 10 grows when an 11th element is added.
	 * Test method for {@link edu.ncsu.csc216.collections.LinkedListAL#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddIntE() {
		//Attempt to add an element to index 1 in an empty list.
		//Check that the element was not added.
		try {
			list.add(1, "apple");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		//Add element to empty list
		list.add(0, "apple");
		assertEquals(1, list.size());
		assertEquals("apple", list.get(0));
		
		//Add element to the front of a list
		list.add(0, "banana");
		assertEquals(2, list.size());
		assertEquals("banana", list.get(0));
		
		//Add element to the middle of a list
		list.add(1, "kiwi");
		assertEquals(3, list.size());
		assertEquals("kiwi", list.get(1));
		
		//Add element to the back of a list
		list.add("cherry");
		assertEquals(4, list.size());
		assertEquals("cherry", list.get(3));
		
		//Attempt to add a null element.  Check that the element
		//was not added.
		try {
			list.add(4, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
			assertEquals("banana", list.get(0));
			assertEquals("kiwi", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("cherry", list.get(3));
		}
		
		//Attempt to add at index -1.  Check that the element was not
		//added.
		try {
			list.add(-1, "orange");
			fail();
		} catch (Exception e) {
			assertEquals(4, list.size());
			assertEquals("banana", list.get(0));
			assertEquals("kiwi", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("cherry", list.get(3));
		}
		
		
		//Attempt to add at index 5 (since there are 4 elements in the list).
		//Check that the element was not added.
		try {
			list.add(5, "orange");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("banana", list.get(0));
			assertEquals("kiwi", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("cherry", list.get(3));
		}
		
		//Test adding an 11th element to an ArrayList with an initial 
		//capacity of 10.
		try {
			list.add(11, "orange");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
			assertEquals("banana", list.get(0));
			assertEquals("kiwi", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("cherry", list.get(3));
		}
		
	}

	/**
	 * Tests that elements are correctly removed from the front, middle, and back of an 
	 * ArrayList.  Removing the last element should leave an empty list.  The bounds are
	 * checked for the appropriate exceptions.
	 * Test method for {@link edu.ncsu.csc216.collections.LinkedListAL#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		//Attempt to remove an element from an empty list
		try{
			list.remove(0);
			fail();
		}catch(IndexOutOfBoundsException e){
			assertEquals(0, list.size());
		}
		
		//Add 4 elements to the list and test that the contents are correct.
		//Reuse code from your testAddIntE.
		list.add(0, "orange");
		list.add(1, "banana");
		list.add(2, "apple");
		list.add(3, "kiwi");
		assertEquals(4, list.size());
		assertEquals("orange", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("kiwi", list.get(3));
		
		//Test that IndexOutOfBoundsException is thrown when remove() is passed
		//a negative index.  Make sure the list is unchanged.
		try{
			list.remove(-1);
			fail();
		}catch(IndexOutOfBoundsException e){
			assertEquals(4, list.size());
			assertEquals("orange", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("kiwi", list.get(3));
		}
		
		//Test that IndexOutOfBoundsException is thrown when remove() is passed
		//an index > size() - 1.  Make sure the list is unchanged.
		try{
			list.remove(list.size());
			fail();
		}catch(IndexOutOfBoundsException e){
			assertEquals(4, list.size());
			assertEquals("orange", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("kiwi", list.get(3));
		}
		
		//Remove middle element.  Test that the removed element is correct and
		//that the remaining list is correct after the removal.
		String s1 = list.remove(2);
		assertEquals(s1, "apple");
		assertEquals(3, list.size());
		assertEquals("orange", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("kiwi", list.get(2));
		
		//Remove first element
		s1 = list.remove(0);
		assertEquals(s1, "orange");
		assertEquals(2, list.size());
		assertEquals("banana", list.get(0));
		assertEquals("kiwi", list.get(1));
		
		//Remove last element
		s1 = list.remove(list.size()-1);
		assertEquals(s1, "kiwi");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//Remove only element
		s1 = list.remove(0);
		assertEquals(s1, "banana");
		assertEquals(0, list.size());
	}

	/**
	 * Tests setting an element in an empty list, the bounds of the list when
	 * using the set() method, and setting an element at the front, middle, and back
	 * of the list.  The set() method is also passed null.
	 * Test method for {@link edu.ncsu.csc216.collections.LinkedListAL#set(int, java.lang.Object)}.
	 */
	@Test
	public void testSetIntE() {
		//Attempt to set a value in an empty list
		try{
			list.set(0, "apple");
			fail();
		}catch(IndexOutOfBoundsException e){
			assertEquals(0, list.size());
		}
		
		//Add 4 elements to the list and test that the contents are correct.
		//Reuse code from your testAddIntE.
		list.add(0, "orange");
		list.add(1, "banana");
		list.add(2, "apple");
		list.add(3, "kiwi");
		assertEquals(4, list.size());
		assertEquals("orange", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("kiwi", list.get(3));
		
		//Test that IndexOutOfBoundsException is thrown when set() is passed
		//a negative index.  Make sure the list is unchanged.
		try{
			list.set(-1, "cherry");
			fail();
		}catch(IndexOutOfBoundsException e){
			assertEquals(4, list.size());
			assertEquals("orange", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("kiwi", list.get(3));
		}
		
		
		//Test that IndexOutOfBoundsException is thrown when set() is passed
		//an index > size() - 1.  Make sure the list is unchanged.
		try{
			list.set(list.size(), "cherry");
			fail();
		}catch(IndexOutOfBoundsException e){
			assertEquals(4, list.size());
			assertEquals("orange", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("kiwi", list.get(3));
		}
		
		//Set middle element.  Test that the element is modified correctly, set() returns the
		//right value, and that the rest of the list is correct.
		String s1 = list.set(1, "strawberry");
		assertEquals(s1, "banana");
		assertEquals(4, list.size());
		assertEquals("orange", list.get(0));
		assertEquals("strawberry", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("kiwi", list.get(3));
		
		//Set first element
		s1 = list.set(0, "blueberry");
		assertEquals(s1, "orange");
		assertEquals(4, list.size());
		assertEquals("blueberry", list.get(0));
		assertEquals("strawberry", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("kiwi", list.get(3));
		
		//Set last element
		s1 = list.set(list.size()-1, "peach");
		assertEquals(s1, "kiwi");
		assertEquals(4, list.size());
		assertEquals("blueberry", list.get(0));
		assertEquals("strawberry", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("peach", list.get(3));
		
		//Attempt to set an element to null.  Check that the element
		//was not modified.
		try{
			list.set(2, null);
			fail();
		}catch(NullPointerException e){
			assertEquals("apple", list.get(2));
		}
	}

	/**
	 * Main get() functionality is tested in the other test methods.  This method will
	 * focus on testing the exceptions associated with bounds.
	 * Test method for {@link edu.ncsu.csc216.collections.LinkedListAL#get(int)}.
	 */
	@Test
	public void testGetInt() {
		//Add 4 elements to the list and test that the contents are correct.
		//Reuse code from your testAddIntE.
		list.add(0, "orange");
		list.add(1, "banana");
		list.add(2, "apple");
		list.add(3, "kiwi");
		assertEquals(4, list.size());
		assertEquals("orange", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("kiwi", list.get(3));
		
		//Test that IndexOutOfBoundsException is thrown when get() is passed
		//a negative index.  Make sure the list is unchanged.
		try{
			list.get(-1);
			fail();
		}catch(IndexOutOfBoundsException e){
			assertEquals(4, list.size());
			assertEquals("orange", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("kiwi", list.get(3));
		}
		
		//Test that IndexOutOfBoundsException is thrown when get() is passed
		//an index > size() - 1.  Make sure the list is unchanged.
		try{
			list.get(list.size());
			fail();
		}catch(IndexOutOfBoundsException e){
			assertEquals(4, list.size());
			assertEquals("orange", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("apple", list.get(2));
			assertEquals("kiwi", list.get(3));
		}
		
	}

}
