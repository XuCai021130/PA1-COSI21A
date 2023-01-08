package main;

/**
* construct a rider with id, starting station, destination, and direction
* Known Bugs: None
*
* @author Xu (Charles) Cai 
* xucai@brandeis.edu
* 10, 9, 2022
* COSI 21A PA1
*/
public class Rider {

	public String riderID;
	public String startingStation;
	public String destinationStation;
	public int direction;
	
	/**
	 * construct a rider
	 * running time: O(1) 
	 * @param riderID
	 * @param startingStation
	 * @param destinationStation
	 */
	public Rider(String riderID, String startingStation, String destinationStation) {
		this.riderID = riderID;
		this.startingStation = startingStation;
		this.destinationStation = destinationStation;
		this.direction = 1;
	}
	
	/**
	 * get starting station
	 * running time: O(1) 
	 * @return
	 */
	public String getStarting() {
		return startingStation;
	}
	
	/**
	 * get ending station
	 * running time: O(1) 
	 * @return
	 */
	public String getDestination() {
		return destinationStation;
	}
	
	/**
	 * get rider id
	 * running time: O(1) 
	 * @return
	 */
	public String getRiderID() {
		return riderID;
	}
	
	/**
	 * test if rider is going to north
	 * running time: O(1) 
	 * @return
	 */
	public boolean goingNorth() {	
		return direction == 0;
	}
	
	/**
	 * swap rider's direction
	 * running time: O(1) 
	 */
	public void swapDirection() {
		direction = (direction + 1) % 2; // swap direction from 1 to 0 or 0 to 1
	}
	
	/**
	 * return a string representation of rider
	 * running time: O(1) 
	 */
	public String toString() {
		return riderID + ", " + destinationStation;
	}
	
	/**
	 * test if object s has same id as current rider
	 * running time: O(1) 
	 */
	public boolean equals(Object s) {
		if (s instanceof Rider) {
			Rider other = (Rider) s;
			return this.riderID == other.riderID;
		}
		return false;
	}
}
