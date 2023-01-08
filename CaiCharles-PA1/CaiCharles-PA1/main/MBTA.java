package main;

/**
* In the main method, we will read three text files and add stations, trains, riders to corresponding place
* And we will run the simulation of MBTA railway
* Known Bugs: None
*
* @author Xu (Charles) Cai 
* xucai@brandeis.edu
* 10, 9, 2022
* COSI 21A PA1
*/
import java.io.*;
import java.util.*;

public class MBTA {

	public static final int SOUTHBOUND = 1;
	public static final int NORTHBOUND = 0;
	
	static final int TIMES = 6;
	static Railway r;
	
	/**
	 * read three text files and add stations, trains, riders to corresponding place
     * And we will run the simulation of MBTA railway
     * running time: O(n^5)
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		r = new Railway();
		String stationsFile = "redLine.txt";
		String trainsFile = "trains.txt";
		String ridersFile = "riders.txt";
		initStations(stationsFile);
		initTrains(trainsFile);
		initRiders(ridersFile);
		Node<Station> first = r.railway.getFirst();
		Node<Station> curr = first;
		while (!curr.data.stationName.equals("Quincy Adams")) {
		    curr = curr.next;
		}
		System.out.println(curr.data.northBoundRiders.front().direction);
		System.out.println(r.railway.getFirst().data.southBoundRiders.front().direction);
		
	}
	
	/**
	 * run the simulations
	 * running time: O(n^5)
	 */
	public static void runSimulation() { //run simulations according to the global constant TIMES
		System.out.println("INITIATED RED LINE");
		System.out.println();
		Node<Station> curr = r.railway.getFirst();
		while(curr != null) {
			System.out.print(curr.getData());
			curr = curr.next;
		}
		System.out.println();
		System.out.println("BEGINNING RED LINE SIMULATION" + "\n" + "\n");
		for (int i = 1; i <= TIMES; i++) {
			System.out.println("------ " + i + " ------ ");
			System.out.println();
			System.out.println(r.simulate());
			System.out.println();
		}
	}
	
	/**
	 * read file and add trains to railway
	 * running time: O(n^2)
	 * @param trainsFile
	 * @throws FileNotFoundException
	 */
	public static void initTrains(String trainsFile) throws FileNotFoundException {
		Scanner file = new Scanner(new File(trainsFile));
		int lineNumber = 0;
		String startStation = "";
		int direction = -1;
		while (file.hasNextLine()) {
			lineNumber++;
			if (lineNumber % 2 == 1) { // if its a odd linenumber, it will be a station name
				startStation = file.nextLine();
			}
			else if (lineNumber % 2 == 0){ // if its a even line number, it will be direction
				direction = Integer.valueOf(file.nextLine());
				r.addTrain(new Train(startStation, direction));
			}
		}
	}
	
	/**
	 * red file and add riders to railway
	 * running time: O(n^2)
	 * @param ridersFile
	 * @throws FileNotFoundException
	 */
	public static void initRiders(String ridersFile) throws FileNotFoundException {
		Scanner file = new Scanner(new File(ridersFile));
		int lineNumber = 0;
		String Id = "";
		String startStation = "";
		String endStation = "";
		while (file.hasNextLine()) {
			lineNumber++;
			if (lineNumber % 3 == 1) { // rider's id
				Id = file.nextLine();
			}
			else if (lineNumber % 3 == 2) { // rider's starting station
				startStation = file.nextLine();
			}
			else if (lineNumber % 3 == 0){ // rider's ending station
				endStation = file.nextLine();
				Rider rider = new Rider(Id, startStation, endStation);
				r.addRider(rider);
			}
		}
		
	}
	
	/**
	 * read filed and add stations to railway
	 * running time: O(n)
	 * @param stationsFile
	 * @throws FileNotFoundException
	 */
	public static void initStations(String stationsFile) throws FileNotFoundException {
		Scanner file = new Scanner(new File(stationsFile));
		int i = 0;
		while (file.hasNextLine()) {
			String stationName = file.nextLine();
			r.stationNames[i] = stationName;
			r.addStation(new Station (stationName));
			i++;
		}
	}
}
