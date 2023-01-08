package main;

/**
* This station method will construct a station with name and queues storing trains and riders
* Known Bugs: None
*
* @author Xu (Charles) Cai 
* xucai@brandeis.edu
* 10, 9, 2022
* COSI 21A PA1
*/
public class Station {

	public Queue<Rider> northBoundRiders;
	public Queue<Rider> southBoundRiders;
	public Queue<Train> northBoundTrains;
	public Queue<Train> southBoundTrains;
	public String stationName;
	
	/**
	 * construct a station
	 * running : O(1) 
	 * @param name
	 */
	public Station(String name) {
		stationName = name;
		northBoundRiders = new Queue<Rider>(20);
		southBoundRiders = new Queue<Rider>(20);
		northBoundTrains = new Queue<Train>(20);
		southBoundTrains = new Queue<Train>(20);
	}
	
	/**
	 * add rider accordingly
	 * running : O(1) 
	 * @param r
	 * @return
	 */
	public boolean addRider(Rider r) {
		if(this.stationName.equals(r.getStarting())) {
			if (r.goingNorth()) {
				northBoundRiders.enqueue(r); // add rider accordingly
			}
			else {
				southBoundRiders.enqueue(r);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * add train to its next station and disembark passengers. Also, print disembarked 
	 * passengers information
	 * running : O(n^2)
	 * @param t
	 * @return
	 */
	public String addTrain(Train t) {
		t.updateStation(stationName); //first update train's location
		String disembarkedPassengers = t.disembarkPassengers();
		String output = t.getStation() + " Disembarking Passengers:" + "\n";
		if (t.goingNorth()) { // move accordingly
			northBoundTrains.enqueue(t);
		}
		else {
			southBoundTrains.enqueue(t);
		}
		return output + disembarkedPassengers;
	}
	
	/**
	 * add passengers from the station and dequeue first train in southbond and return. 
	 * running : O(n)
	 * @return
	 */
	public Train southBoardTrain() {
		if (southBoundTrains.size() == 0) {
			return null;
		}else {
			Train currentTrain = southBoundTrains.front(); // return the front train
			while (southBoundRiders.size() != 0 && currentTrain.hasSpaceForPassengers()) { // if there is rider in the station and 
				Rider currentRider = southBoundRiders.front(); // current train is not full, add rider
				currentTrain.addPassenger(currentRider);
				southBoundRiders.dequeue();
			}
			southBoundTrains.dequeue();
			return currentTrain;
		}
	}
	
	/**
	 * add passengers from the station and dequeue first train in northbond and return. 
	 * running : O(n)
	 * @return
	 */
	public Train northBoardTrain() {
		if (northBoundTrains.size() == 0) {
			return null;
		}else {
			Train currentTrain = northBoundTrains.front();
			while (northBoundRiders.size() != 0 && currentTrain.hasSpaceForPassengers()) {
				Rider currentRider = northBoundRiders.front();
				currentTrain.addPassenger(currentRider);
				northBoundRiders.dequeue();
			}
			northBoundTrains.dequeue();
			return currentTrain;
		}
	}
	
	/**
	 * if the train reaches the first station, change its direction
	 * and move it to  corresponding queue
	 * running : O(1) 
	 */
	public void moveTrainNorthToSouth() {
		if (northBoundTrains.size() != 0) {
			Train currentTrain = northBoundTrains.front();
			northBoundTrains.dequeue();
			currentTrain.swapDirection();
			southBoundTrains.enqueue(currentTrain);
		}
	}
	
	/**
	 * if the train reaches the last station, change its direction
	 * running : O(1) 
	 */
	public void moveTrainSouthToNorth() {
		if (southBoundTrains.size() != 0) {
			Train currentTrain = southBoundTrains.front();
			southBoundTrains.dequeue();
			currentTrain.swapDirection();
			northBoundTrains.enqueue(currentTrain);
		}
	}
	
	/**
	 * return the status of this station by presenting  the four queues
	 * running : O(1) 
	 */
	public String toString() {
		String output = "";
		output += "Station: " + this.stationName + "\n";
		output += northBoundTrains.size() + " north-bound trains waiting" + "\n";
		output += southBoundTrains.size() + " south-bound trains waiting" + "\n";
		output += northBoundRiders.size() + " north-bound passengers waiting" + "\n";
		output += southBoundRiders.size() + " south-bound passengers waiting" + "\n" + "\n";
		return output;
	}
	
	/**
	 * get the name of station
	 * running : O(1) 
	 * @return
	 */
	public String stationName() {
		return stationName;
	}
	
	/**
	 * compare station with a object o, return true if they have same station name
	 * running : O(1) 
	 */
	public boolean equals(Object o) {
		if (o instanceof Station) {  // if object is a station type
			Station other = (Station) o;
			return this.stationName == other.stationName;
		}
		return false;
	}
}
