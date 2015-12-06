import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class SocialNetwork {
	
	public static LinkedList<Person> allPeople = new LinkedList<Person>();
	public static HashMap<String, Person> total = new HashMap<String,Person>();
	//public static int peopleCount = 0;
	
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
					System.out.println(relation(n1, n2));
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
					//peopleCount++;
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
	
	public static void isFriend(String n1, String n2) {
		Person p1 = total.get(n1);
		Person p2 = total.get(n2);
		if (p1.isAdj(p2)){
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}
	
	public static void mutual(String n1, String n2) {
		Person p1 = total.get(n1);
		Person p2 = total.get(n2);
		LinkedList<Person> l1 = p1.getAdj();
		
		for(int i = 0; i < l1.size(); i++) {
			Person p3 = l1.get(i);
			//System.out.println(p3.getName());
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
		Person start = total.get(n1);
		Person end = total.get(n2);
		
		LinkedList<Person> tovisit = new LinkedList<Person>();
		LinkedList<Person> alreadyvisit = new LinkedList<Person>();
		String thepath = "";
		
		tovisit.add(start);
		alreadyvisit.add(start);
		start.setPred(null);
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
					if (!alreadyvisit.contains(a)) {
						a.setPred(p);
						alreadyvisit.add(a);
						tovisit.add(a);
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
		//set all to not visited
		for (int i = 0; i < allPeople.size(); i++){
			allPeople.get(i).setVisited(false);
		}
		int numNot = 1;
		for (int i = 0; i < allPeople.size(); i++){
			Person pe = allPeople.get(i);
			while(!pe.isVisit()){
				pe.setVisited(true);
				LinkedList<Person> tovisit = new LinkedList<Person>();
				LinkedList<Person> alreadyvisit = new LinkedList<Person>();
				
				tovisit.add(pe);
				alreadyvisit.add(pe);
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
							if (!alreadyvisit.contains(a)) {
								alreadyvisit.add(a);
								tovisit.add(a);
								a.setVisited(true);
							}
						}
					}	
				}
				numNot *= alreadyvisit.size();
			}
		}
		System.out.println(numNot);
	}
	
	public static void popular() {
		for (int i = 0; i < allPeople.size(); i++) {
			Person p = allPeople.get(i);
			//LinkedList<Person> adj = p.getAdj();
			String friendsandnum = bfs(p);
			//System.out.println("NAME- " + p.getName());
			//System.out.println(friendsandnum);
			double numFriends = Double.parseDouble(friendsandnum.split("-")[1]);
			if (numFriends == 0.0){
				p.setPop(0.0);
			} else {
				String friends = friendsandnum.split("-")[0];
				double sumlength = 0.0;
				for(int k = 0; k < friends.split("\n").length; k++) {
					String per = friends.split("\n")[k];
					String path = relation(p.getName(), per);
					//check this and watch for newline
					sumlength += path.split("\n").length - 1;
				}
				//System.out.println(p.getName());
				//System.out.println(numFriends);
				//System.out.println(sumlength);
				p.setPop(numFriends/sumlength);
			}
		}
		
		///now get max and print out the ones that have this max
		double maxPop = 0;
		for (int i = 0; i < allPeople.size(); i++) {
			Double p = allPeople.get(i).getPop();
			//String name = allPeople.get(i).getName();
			//System.out.println(name + ": " + p);
			if (p > maxPop) {
				maxPop = p;
			}
		}
		
		for (int i = 0; i < allPeople.size(); i++) {
			Person p = allPeople.get(i);
			if (p.getPop() == maxPop){
				System.out.println(p.getName());
			}
		}
	}
	
	public static String bfs(Person pe){
		for (int i = 0; i < allPeople.size(); i++){
			allPeople.get(i).setVisited(false);
		}
		String friends = "";
		int num = 0;
		while(!pe.isVisit()){
			pe.setVisited(true);
			LinkedList<Person> tovisit = new LinkedList<Person>();
			LinkedList<Person> alreadyvisit = new LinkedList<Person>();
			
			tovisit.add(pe);
			alreadyvisit.add(pe);
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
						if (!alreadyvisit.contains(a)) {
							alreadyvisit.add(a);
							tovisit.add(a);
							a.setVisited(true);
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
