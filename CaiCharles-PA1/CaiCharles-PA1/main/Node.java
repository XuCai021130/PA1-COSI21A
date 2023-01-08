package main;

/**
* This node class is used to help the construction of doubly linekd list with field prev, next, and data
* Known Bugs: None
*
* @author Xu (Charles) Cai 
* xucai@brandeis.edu
* 10, 9, 2022
* COSI 21A PA1
*/
public class Node<T> {
	
	Node<T> prev;
	Node<T> next;
	T data;
	
	/**
	 * construct a node
	 * running time: O(1)
	 * @param data
	 */
	public Node(T data) {
		this.data = data;
	}
	
	/**
	 * set data
	 * running time: O(1)
	 * @param data
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * set next node
	 * running time: O(1)
	 * @param next
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	/**
	 * set previous node
	 * running time: O(1)
	 * @param prev
	 */
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}
	
	/**
	 * get next node
	 * running time: O(1)
	 * @return
	 */
	public Node<T> getNext() {
		if (this.next != null) {
			return next;
		}
		return null;
	}
	
	/**
	 * get previous node
	 * running time: O(1)
	 * @return
	 */
	public Node<T> getPrev() {
		if (this.prev != null) {
			return prev;
		}
		return null;
	}
	
	/**
	 * get data of the node
	 * running time: O(1)
	 * @return
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * return the data of node
	 * running time: O(1)
	 */
	public String toString() {
		String output = "";
		output += data;
		return output;
	}
}
