package main;

/**
* This queue class construct a queue with head tail and numEntries
* Known Bugs: None
*
* @author Xu (Charles) Cai 
* xucai@brandeis.edu
* 10, 9, 2022
* COSI 21A PA1
*/
import java.util.*;

public class Queue<T> {

	public T[] q;
	public int head;
	public int tail;
	public int numEntries;
	
	/**
	 * construct a queue
	 * running time: O(1)
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public Queue(int capacity) {
		this.q = (T[]) new Object[capacity];
		head = 0;
		tail = 0;
		numEntries = 0;
	}
	
	/**
	 * add element in the queue
	 * running time: O(n)
	 * @param element
	 */
	
	@SuppressWarnings("unchecked")
	public void enqueue(T element) {
		if (!(numEntries + 1 == q.length)) { // if there is enought space in the array to add element
			q[tail] = element;
			if (tail == q.length - 1) {
				tail = 0;
			}
			else {
				tail++;
			}
		}
		else {  // if there is not enough space in array
			T[] newQ= (T[]) new Object[2 * q.length]; // create a larger array and add them inside
			if (head > tail) {
				head += q.length; // change the head accordingly  when head is behind tail
				for (int i = 0; i < tail; i++) {
					newQ[i] = q[i];
				}
				newQ[tail] = element;
				for (int j = newQ.length - 1; j >= head; j--) {
					newQ[j] = q[j - q.length];
			}
			}
			else { //case when tail is behind head
				for (int i = 0; i < tail; i++) {
					newQ[i] = q[i];
				}
			}
			
			q = newQ; // make the larger array to be the q
			tail++;
		}
		numEntries++;
	}
	
	/**
	 * remove the first element in queue 
	 * running time: O(1)
	 */
	public void dequeue() { 
		if (numEntries != 0) {
			if (head == q.length - 1) {// if head reaches the  end of the array
				q[head] = null;
				head = 0;
			}
			else {
				q[head] = null;
				head++;
			}
			numEntries--;
		}
		else {
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * return the first element of queue
	 * running time: O(1)
	 * @return
	 */
	public T front() {
		if (numEntries != 0) { // if queue is not empty
			return q[head];
		}
		else {
			return null;
		}
	}
	
	/**
	 * return size of queue
	 * running time: O(1)
	 * @return
	 */
	public int size() {
		return numEntries;
	}
	
	
	/**
	 * return a string representation of queue
	 * running time: O(n)
	 */
	public String toString() {
		String output = "";
		output += "[";
		if (head < tail) { // if tail is behind head
			output += q[head];
			for(int i = head + 1; i < tail; i++) {
				output += ", ";
				output += q[i];
			}
		}
		else {  //  if head is behind tail
			output += q[head];
			for(int j = head + 1; j < q.length; j++) {
				output += ", ";
				output += q[j];
			}
			for (int k = 0; k < tail; k++) {
				output += ", ";
				output += q[k];
			}
		}
		output += "]";
		return output;
	}
}
