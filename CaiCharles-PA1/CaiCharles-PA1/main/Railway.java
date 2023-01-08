package main;

/**
* This railway class construct a whole MBTA red line railway and will run simulations 
* Known Bugs: None
*
* @author Xu (Charles) Cai 
* xucai@brandeis.edu
* 10, 9, 2022
* COSI 21A PA1
*/
public class Railway {

	public DoubleLinkedList<Station> railway;
	public String[] stationNames;
	
	/**
	 * construct a railway object
	 * running time: O(1)
	 */
	public Railway()  {
		stationNames = new String[18];
		railway= new DoubleLinkedList<Station>();
	}
	/**
	 * Add element to the station's linked list 
	 * Running time: O(1)
	 * @param s
	 */
	public void addStation(Station s) {
		railway.insert(s);
	}
	
	/**
	 * Set rider's direction first and add it to station's queue if the starting station of 
	 * the rider is same as the current  station
	 * running time: O(n)
	 * @param r
	 */
	public void addRider(Rider r) {
		setRiderDirection(r);
		Node<Station> curr = railway.getFirst();
		boolean whetherAddRider = false;
		while (curr != null && !whetherAddRider) { // exit the while loop if after we add the rider 
			Station currStation = curr.getData();  
			whetherAddRider = currStation.addRider(r);
			curr = curr.next;
		}
	}
	/**
	 * add train to the station's queue if the direction and station name matches
	 * running time: O(n)
	 * @param t
	 */
	public void addTrain(Train t) {
		Node<Station> curr = railway.getFirst();
		boolean matchStation = false;
		while (curr != null && !matchStation) { // exit the while loop if after we add the train and 
			Station currStation = curr.getData();// find their  stations are matched
			matchStation = currStation.stationName().equals(t.getStation());
			if (matchStation) {
				currStation.addTrain(t);
			}
			curr = curr.next;
		}
	}
	/**
	 * set rider's direction by comparing its start place and destination
	 * running time: O(n)
	 * @param r
	 */
	public void setRiderDirection(Rider r) {
		String startStation = r.getStarting();
		int startIndex = 0;
		String endStation = r.getDestination();
		int endIndex = 0;
		for (int i = 0; i < stationNames.length; i++) {
			if (stationNames[i].equals(startStation)) {
				startIndex = i;
			}
			if (stationNames[i].equals(endStation)) {// find index of the start place and destination
				endIndex = i;
			}
		}
		if (startIndex > endIndex) { // compare and set rider's direction
			r.direction = 0;
		}
		else {
			r.direction = 1;
		}
	}
	
	/**
	 * this method will do one simulation of the railway. It will traverse from the northest station
	 * to the southest station. If encountering train, it will add appropriate riders and move the 
	 * train to its next place and disembark passengers. Also, switch direction if necessary
	 * running time: O(n^4)
	 * @return
	 */
	public String simulate() {
		String output = "";
		String south = "Southbond";
		String north = "Northbond";
		Node<Station> curr = railway.getFirst();
		Train southTrain = null;
		Train northTrain = null;
		int northBoundTrainSize = -1;
		int southBoundTrainSize = -1; // these variables will be used in the following while loop
		Station nextStation = null;
		Station prevStation = null;
		
		while (curr != null) {
			Station currStation = curr.getData();
			northBoundTrainSize = currStation.northBoundTrains.size();
			southBoundTrainSize = currStation.southBoundTrains.size();
			output += currStation.toString();
			
			if (northBoundTrainSize > 0) { // if there is a train  in north bond
				if (curr.prev != null) { // if it is not in the first station
					prevStation = curr.prev.getData();
					for (int i = 0; i < northBoundTrainSize; i++) {
						northTrain = currStation.northBoardTrain(); //add passengers
						output += prevStation.addTrain(northTrain); // move it to next station
						output += "Direction: " + north + "\n";  // output
						output += "Passengers:" + "\n";
						if (!northTrain.currentPassengers().equals("")) {
							output += northTrain.currentPassengers();
						}
						output += "Current station: " + prevStation.stationName() + "\n" +"\n";
					}
				}
				else {
					for (int i = 0; i < northBoundTrainSize; i++) {
						currStation.moveTrainNorthToSouth();
					}
				}
			}
			if (southBoundTrainSize > 0) { // if there is train  in south bond
				if (curr.next != null) {  // if it is not in the final station
					nextStation = curr.next.getData();
					for (int i = 0; i < southBoundTrainSize; i++) {
						southTrain = currStation.southBoardTrain(); // add passengers
						output += nextStation.addTrain(southTrain); // move train to next station
						output += "Direction: " + south + "\n";  // output
						output += "Passengers:" + "\n";
						if (!southTrain.currentPassengers().equals("")) {
							output += southTrain.currentPassengers();
						}
						output += "Current station: " + nextStation.stationName() + "\n" +"\n";
					}
				}
				else {
					for (int i = 0; i < southBoundTrainSize; i++) {
						currStation.moveTrainSouthToNorth();  // change direction
					}
				}
			}
			curr = curr.next;
		}
		
		return output;
	}
	
	/**
	 * return a string representation of each station's name
	 * running time: O(n) 
	 */
	public String toString() {
		String output = "[";
		if (stationNames[0] != null) {
			output += stationNames[0];
		}
		for (int i = 1 ; i < stationNames.length; i++) {
			output += ", ";
			output += stationNames[i];
		}
		output += "]";
		return output;
	}
}
