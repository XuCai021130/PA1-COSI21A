package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.DoubleLinkedList;

class StudentDLLTest {

	@Test
	void test() {
		DoubleLinkedList<Integer> dll = new DoubleLinkedList<Integer>();
		
		assertTrue(dll.delete(1) == null);
		dll.insert(1);
		
		assertEquals(dll.getFirst().getData(), 1);
		
		dll.insert(2);
		dll.insert(3);
		dll.insert(4);
		dll.delete(1);
		
		assertEquals(dll.getFirst().getData(), 2);
		
		dll.delete(2);
		
		assertEquals(dll.getFirst().getData(), 3);
		
		dll.delete(3);
		
		assertEquals(dll.getFirst().getData(), 4);
		
		dll.insert(5);
		dll.insert(6);
		dll.insert(7);
		
		assertTrue(dll.size() == 4);
		
		dll.delete(6);
		
		assertTrue(dll.size() == 3);
		
		assertTrue(dll.get(5) == 5);
		
		assertTrue(dll.get(100) == null);

		
	
	}
	
	@Test
	void test2() {
		DoubleLinkedList<String> dll = new DoubleLinkedList<String>();
		dll.insert("a");
		dll.insert("b");
		dll.insert("c");
		
		assertTrue(dll.size() == 3);
		
		assertEquals(dll.getFirst().getData(), "a");
		
		assertEquals(dll.delete("a"), "a");
		
		assertEquals(dll.getFirst().getData(), "b");
		
		
	}

}
