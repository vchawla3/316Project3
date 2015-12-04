import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class SocialNetwork {
	
	public static LinkedList<Person> allPeople = new LinkedList<Person>();
	public static HashMap<String, Person> total = new HashMap<String,Person>();
	public static int peopleCount = 0;
	
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
					relation(n1, n2);
					System.out.println("$");
					break;
				case "nonconnected":
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
					//allPeople.add(p);
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

	public static void relation(String n1, String n2) {
		Person start = total.get(n1);
		Person end = total.get(n2);
		
		LinkedList<Person> tovisit = new LinkedList<Person>();
		LinkedList<Person> alreadyvisit = new LinkedList<Person>();
		HashMap<String, Person> path = new HashMap<String, Person>();
		String thepath = "";
		
		tovisit.add(start);
		alreadyvisit.add(start);
		path.put(start.getName(), null);

		//p1.setVisited();
		boolean t = true;
		while(!tovisit.isEmpty() && t){
			Person p = tovisit.removeHead();
			if (p == end){
				//found it
				String name = p.getName();
				while(name != null){
					thepath = name + "\n" + thepath;
					Person tmp = path.get(name);
					if (tmp == null){
						name = null;
					} else {
						name = path.get(name).getName();
					}
				}
				System.out.print(thepath);
				t = false;
			} else {
				//keep searching
				LinkedList<Person> pList = p.getAdj();
				for (int i = 0; i < pList.size(); i++) {
					Person a = pList.get(i);
					if (!alreadyvisit.contains(a)) {
						path.put(a.getName(), p);
						alreadyvisit.add(a);
						tovisit.add(a);
					}
				}	
			}
		}
		
	}
	
	public static void notconnected() {
		
	}
	
	public static void popular() {
		
	}
}
