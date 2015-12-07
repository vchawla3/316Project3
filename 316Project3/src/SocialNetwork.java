import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

/**
 * Main class where graph is created and algorithms are run
 * @author Vaibhav Chawla (vchawla3), Kirtan Patel, Aaron Chan, Morgan Arrington
 *
 */
public class SocialNetwork {
	
	/**
	 * adjacency list holding all Person objects in the order they were inputed
	 */
	public static LinkedList<Person> allPeople = new LinkedList<Person>();
	/**
	 * map holding string name of Person object, only used to get Person objects
	 */
	public static HashMap<String, Person> total = new HashMap<String,Person>();
	/**
	 * count of number of people in network
	 */
	public static int peopleCount = 0;
	
	/**
	 * main method for SocialNetwork program
	 * @param args
	 */
	public static void main(String args[]){
		try {
			File input = new File(args[0]);
			Scanner scan = new Scanner(input);
			//first make graph
			createGraph(scan);
			//run commands
			parseCommands();
		} catch (FileNotFoundException e) {
			System.out.println("File not Found!");
			e.printStackTrace();
		}
	}
	
	/**
	 * parses commands from the user input and runs corresponding algorithms
	 */
	public static void parseCommands(){
		System.out.println("$");
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()){
			String cmd = scan.next();
			String n1;
			String n2;
			switch(cmd) {
				case "isfriend":
					n1 = scan.next();
					n2 = scan.next();
					isFriend(n1, n2);
					System.out.println("$");
					break;
				case "mutual":
					n1 = scan.next();
					n2 = scan.next();
					mutual(n1,n2);
					System.out.println("$");
					break;
				case "relation":
					n1 = scan.next();
					n2 = scan.next();
					System.out.print(relation(n1, n2));
					System.out.println("$");
					break;
				case "notconnected":
					notconnected();
					System.out.println("$");
					break;
				case "popular":
					popular();
					System.out.println("$");
					break;
				case "quit":
					scan.close();
					System.exit(0);
			}
		}
	}
	
	/**
	 * parses the input file using the initialized scanner and creates the 
	 * graph/adjacency list
	 * @param scan Scanner holding input file
	 */
	public static void createGraph(Scanner scan){
		boolean t = true;
		while(scan.hasNext()){
			if (t){
				String name = scan.nextLine();
				if (name.equals("$")) {
					t = false;
				} else {
					Person p = new Person(name);
					total.put(name, p);
					allPeople.add(p);
					peopleCount++;
				}
			} else {
				String n1 = scan.next();
				String n2 = scan.next();
				Person p1 = total.get(n1);
				Person p2 = total.get(n2);
				p1.addAdj(p2);
				p2.addAdj(p1);
			}
		}
	}
	
	/**
	 * checks and prints yes if the two people passed in
	 * are friends, else print no.
	 * @param n1 name of first person
	 * @param n2 name of second person
	 */
	public static void isFriend(String n1, String n2) {
		//get Person objects - only place to use map
		Person p1 = total.get(n1);
		Person p2 = total.get(n2);
		
		//check if adjacent by traversing list using contains method
		if (p1.isAdj(p2)){
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}
	
	/**
	 * checks and prints the mutual friends of the two people passed in
	 * @param n1 name of first person
	 * @param n2 name of second person
	 */
	public static void mutual(String n1, String n2) {
		//get Person objects - only place to use map
		Person p1 = total.get(n1);
		Person p2 = total.get(n2);
		//compute algorithm
		
		LinkedList<Person> l1 = p1.getAdj();
		
		for(int i = 0; i < l1.size(); i++) {
			Person p3 = l1.get(i);
			if (p2.isAdj(p3)){
				System.out.println(p3.getName());
			}
		}
	}

	/**
	 * used code from http://www.careercup.com/question?id=20786668 but modified partially
	 * @param n1 name of first person
	 * @param n2 name of second person
	 * @return string containing the path, empty string ("") if no path
	 */
	public static String relation(String n1, String n2) {
		for (int i = 0; i < peopleCount; i++){
			allPeople.get(i).setVisited(false);
		}
		
		//get Person objects - only place to use map
		Person start = total.get(n1);
		Person end = total.get(n2);
		
		//compute relation
		LinkedList<Person> tovisit = new LinkedList<Person>();
		String thepath = "";
		tovisit.add(start);
		start.setPred(null);
		start.setVisited(true);
		boolean t = true;
		while(!tovisit.isEmpty() && t){
			Person p = tovisit.removeHead();
			if (p == end){
				//found it
				thepath = p.getName() + "\n" + thepath;
				Person name = p.getPred();
				while(name != null){
					thepath = name.getName() + "\n" + thepath;
					name = name.getPred();
				}
				t = false;
			} else if(p == null) {
				t = false;
			}else {
				//keep searching
				LinkedList<Person> pList = p.getAdj();
				for (int i = 0; i < pList.size(); i++) {
					Person a = pList.get(i);
					if (!a.visited) {
						a.setPred(p);
						tovisit.add(a);
						a.setVisited(true);
					}
				}	
			}
		}
		return thepath;
	}
	
	/**
	 * prints number of pairs of people are not connected in the social network
	 */
	public static void notconnected() {
		for (int i = 0; i < peopleCount; i++){
			allPeople.get(i).setVisited(false);
		}
		
		int compSizes[] = new int[peopleCount];
		int x = 0;
		
		int compCount = 0;
		for (int i = 0; i < peopleCount; i++) {
			Person pe = allPeople.get(i);
			while(!pe.isVisit()){
				pe.setVisited(true);
				LinkedList<Person> tovisit = new LinkedList<Person>();
				
				int sizeOfComp = 0;
				tovisit.add(pe);
				sizeOfComp++;
				
				boolean t = true;
				while(!tovisit.isEmpty() && t){
					Person p = tovisit.removeHead();
					if (p == null){
						//no more to search
						t = false;
					} else {
						//keep searching
						LinkedList<Person> pList = p.getAdj();
						if (pList.size() == 0) {
							//do nothing
						} else {
							for (int k = 0; k < pList.size(); k++) {
								Person a = pList.get(k);
								if (!a.visited) {
									tovisit.add(a);
									a.setVisited(true);
									sizeOfComp++;
								}
							}
						}
					}
				}
				//increment num of Comps
				compCount++;
				//add size of comp to the array
				compSizes[x] = sizeOfComp;
				//increment indexer for array
				x++;
			}
		}
		
		int numNot = 0;
		for(int i = 0; i < compCount; i++) {
			int n = 0;
			for (int j = i + 1; j < compCount;j++){
				n += compSizes[i]*compSizes[j];
			}
			numNot +=n;
		}
		System.out.println(numNot);
	}
	
	/**
	 * Finds and outputs the most popular people in the graph
	 */
	public static void popular() {

		
		for (int i = 0; i < peopleCount; i++) {
			Person p = allPeople.get(i);
			String friendsandnum = bfs(p);
			double numFriends = Double.parseDouble(friendsandnum.split("-")[1]);
			if (numFriends == 0.0){
				p.setPop(0.0);
			} else {
				String friends = friendsandnum.split("-")[0];
				double sumlength = 0.0;
				int size = friends.split("\n").length;
				for(int k = 0; k < size; k++) {
					String per = friends.split("\n")[k];
					String path = relation(p.getName(), per);
					//subtract one because string also has starting person who don't want to count
					sumlength += path.split("\n").length - 1;
				}
				p.setPop(numFriends/sumlength);
			}
		}
		
		///now get max and print out the ones that have this max
		double maxPop = 0;
		for (int i = 0; i < peopleCount; i++){
			Double p = allPeople.get(i).getPop();
			if (p > maxPop) {
				maxPop = p;
			}
		}
		//print the names with max pop
		for (int i = 0; i < peopleCount; i++){
			Person p = allPeople.get(i);
			if (p.getPop() == maxPop){
				System.out.println(p.getName());
			}
		}
	}
	
	/**
	 * method to run a full bfs and return all people hit plus the num people hit
	 * @param pe person to start bfs from
	 * @return string holding people in traversed order plus total num ppl hit
	 */
	public static String bfs(Person pe){
		//set all to not visited before doing bfs
		for (int i = 0; i < peopleCount; i++){
			allPeople.get(i).setVisited(false);
		}
		String friends = "";
		//LinkedList<Person> component = new LinkedList<Person>();
		
		int num = 0;
		while(!pe.isVisit()){
			pe.setVisited(true);
			LinkedList<Person> tovisit = new LinkedList<Person>();
			tovisit.add(pe);
			boolean t = true;
			while(!tovisit.isEmpty() && t){
				Person p = tovisit.removeHead();
				if (p == null){
					//no more to search
					t = false;
				} else {
					//keep searching
					LinkedList<Person> pList = p.getAdj();
					for (int k = 0; k < pList.size(); k++) {
						Person a = pList.get(k);
						if (!a.visited) {
							tovisit.add(a);
							a.setVisited(true);
							//component.add(a);
							friends += a.getName() + "\n";
							num++;
						}
					}
				}	
			}
		}
		friends = friends + "-" + num;
		return friends;
	}
	
}
