package main;

/**
* This train class construct a train object with corresponding fields and will move inside the railway 
* Known Bugs: None
*
* @author Xu (Charles) Cai 
* xucai@brandeis.edu
* 10, 9, 2022
* COSI 21A PA1
*/
public class Train {

	public static final int TOTAL_PASSENGERS = 10;
	public Rider[] passengers;
	public int passengerIndex;
	public String currentStation;
	public int direction;
	
	/**
	 * construct a train object
	 * running time: O(1)
	 * @param currentStation
	 * @param direction
	 */
	public Train(String currentStation, int direction) {
		this.direction = direction;
		this.currentStation = currentStation;
		passengers = new Rider[TOTAL_PASSENGERS];
		passengerIndex = 0;
	}
	/**
	 * return if train goes to north
	 * running time: O(1)
	 * @return
	 */
	public boolean goingNorth() {
		return direction == 0;
	}
	
	/**
	 * swap direction
	 * running time: O(1)
	 */
	public void swapDirection() {
		direction = (direction + 1) % 2;
	}
	
	/**
	 * return current passengers
	 * running time: O(n)
	 * @return
	 */
	public String currentPassengers() {
		String output = "";
		for (int i = 0; i < passengerIndex; i++) { // traverse the array
			output += passengers[i].getRiderID() + ", " + passengers[i].getDestination() + "\n";
		}
		return output;
	}
	
	/**
	 * add passengers if conditions are met
	 * running time: O(1)
	 * @param r
	 * @return
	 */
	public boolean addPassenger(Rider r) {
		boolean condition1 = r.startingStation.equals(this.currentStation);
		boolean condition2 = r.direction == this.direction;
		boolean condition3 = this.hasSpaceForPassengers();
		boolean finalOutcom = condition1 && condition2 && condition3;
		if (finalOutcom) { // add rider only if three conditions are met
			passengers[passengerIndex] = r;
			passengerIndex ++;
		}
		return finalOutcom;
	}
	
	/**
	 * test if there is empty space
	 * running time: O(1)
	 * @return
	 */
	public boolean hasSpaceForPassengers() {
		return passengerIndex < passengers.length;
	}
	/**
	 * disembark passengers if this is rider's destination and return their information
	 * running time: O(n^2)
	 * @return
	 */
	public String disembarkPassengers() {
		String getOffPassengers = "";
		for (int i = 0; i < passengerIndex; i++) { /// loop the riders array
			Rider currRider = passengers[i];
			if (currRider.getDestination().equals(currentStation)) {
				getOffPassengers += currRider.getRiderID() +"\n";
				Rider[] newPassengers = new Rider [passengers.length];
				int count = 0;
				for (int j = 0; j <= i - 1; j++) { // remove the  passengers  and create a new 
					newPassengers[count] = passengers[j]; // array so that the empty space of that passengers is eliminated 
					count++;
				}
				for (int k = i + 1; k < passengerIndex; k++) {
					newPassengers[count] = passengers[k]; // add remaining passengers that is behind the disembarked rider
					count++;
				}
				passengers = newPassengers;  // give the midified array back to passengers array
				i--;
				passengerIndex--;
			}
		}
		return getOffPassengers;
	}
	
	/**
	 * update station
	 * running time: O(1)
	 * @param s
	 */
	public void updateStation(String s) {
		currentStation = s;
	}
	
	/**
	 * return train's current station
	 * running time: O(1)
	 * @return
	 */
	public String getStation() {
		return currentStation;
	}
	
	/**
	 * return a string representation of train
	 * running time: O(n)
	 */
	@Override
	public String toString() {
		String output = "Current Passengers:" + "\n"; 
		for (int i = 0; i < passengerIndex - 1; i++) {
			output += passengers[i] + ", ";
		}
		output += passengers[passengerIndex - 1] + "\n";
		output += "Current Station: " + currentStation + "\n";
		output += "Direction" + direction;
		return output;
	}
}
