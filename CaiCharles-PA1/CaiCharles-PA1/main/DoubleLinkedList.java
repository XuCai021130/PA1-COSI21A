package main;

/**
* This queue class construct a double linked list with tail, head, and size
* Known Bugs: None
*
* @author Xu (Charles) Cai 
* xucai@brandeis.edu
* 10, 9, 2022
* COSI 21A PA1
*/
public class DoubleLinkedList<T> {
	
	public Node<T> head;
	public Node<T> tail;
	public int size;
	
	/**
	 * construct a double linked list object
	 * running time: O(1)
	 */
	public DoubleLinkedList() {
		size = 0;
		head = null;
		tail = null;
	}
	
	/**
	 * return the first node
	 * running time: O(1)
	 * @return
	 */
	public Node<T> getFirst() {
		return head;
	}
	
	/**
	 * insert a element into the dll, change head and tail accordingly
	 * running time: O(1)
	 * @param element
	 */
	public void insert(T element) {
		Node<T> newNode = new Node<T>(element);
		if (size == 0) { // if the dll is null at first, the first added element is head
			head = newNode;
			head.prev = null;
			head.next = null;
		}
		else if (size == 1) { // if the dll only has head, the added element is tail
			tail = newNode;
			tail.next = null;
			tail.prev = head;
			head.next = tail;
		}
		else if (size >= 2){
			tail.next = newNode;
			newNode.prev = tail;
			newNode.next = null;
			tail = newNode;
		}
		size++;
	}
	
	/**
	 * delete the key from dll and return it. If key is not found, return null
	 * running time: O(n)
	 * @param key
	 * @return
	 */
	public T delete(T key) {
		Node<T> curr = head;
		while (curr != null) {
			if (curr.data.equals(key)) {
				size--;
				if (size >= 2) { // if there are >=2 elements before delete
					if (curr == head) { // if the deleted element is head
						Node<T> next = head.next;
						next.prev = null;
						head = next;
					}
					else if (curr == tail) { // if the deleted element is tail
						Node<T> prev = curr.prev;
						prev.next = null;
						tail = prev;
					}
					else {
						Node<T> prev = curr.prev; // if the deleted element is not tail or head
						Node<T> next = curr.next;
						prev.next = next;
						next.prev = prev;
					}
				}	
				else if (size == 1) { // if there are two elements before delete, it can be either head or tail
					if (curr.next != null) { // test if it is tail or head
						head = tail;
						head.prev = null;
					}
					tail = null;
					head.next = null;
				}
				else if (size == 0){ // if there is only one element before delete, the element must be head
					head = null;
				}
				return key;
			}
			curr = curr.next;
		}
		return null;
	}
	
	/**
	 * return the key if it is included in dll and return null if not found
	 * running time: O(n)
	 * @param key
	 * @return
	 */
	public T get(T key) {
		Node<T> curr = head;
		while (curr != null) {
			if (curr.data.equals(key)) {
				return key;
			}
			curr = curr.next;
		}
		return null;
	}
	
	/**
	 * return size of dll
	 * running time: O(1)
	 * @return
	 */
	public int size() {
		return size;
	}
	
	
	/**
	 * return a string representation of the dll
	 * running time: O(n)
	 */
	public String toString() {
		String output = "[";
		Node<T> curr = head;
		while (curr != null) {
			output += curr + ", ";
			curr = curr.next;
		}
		output += "]";
		return output;
	}
}
