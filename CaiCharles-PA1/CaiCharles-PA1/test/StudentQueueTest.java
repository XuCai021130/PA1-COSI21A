package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Queue;

class StudentQueueTest {

	@Test
	void test() {
		Queue<Integer> r = new Queue<Integer> (2);
		
		assertTrue(r.head == 0 && r.tail == 0);
		
		assertTrue(r.size() == 0);
		
		r.enqueue(1);
		
		assertTrue(r.front() == 1);
		
		r.enqueue(2);
		
		assertTrue(r.front() == 1);
		
		r.enqueue(3);
		
		assertEquals(r.tail, 3);
		
		assertTrue(r.size() == 3);
		
		r.dequeue();
		r.enqueue(4);
		
		assertTrue(r.size() == 3);
		
		r.enqueue(5);
		r.enqueue(6);
		assertTrue(r.size() == 5);
		r.dequeue();
		r.dequeue();
		
		assertTrue(r.front() == 4);
		assertTrue(r.size() == 3);
		
	}
	

    @Test
    void test2() {
        Queue<Integer> rr = new Queue<Integer> (1);
        rr.enqueue(1);
        assertEquals(rr.front(), 1);
        
    }
	
	

}
