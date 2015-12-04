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
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()){
			System.out.println("$");
			String cmd = scan.next();
			String n1;
			String n2;
			switch(cmd) {
				case "isfriend":
					n1 = scan.next();
					n2 = scan.next();
					
					System.out.println("$");
				case "mutual":
					n1 = scan.next();
					n2 = scan.next();
					
					System.out.println("$");
				case "relation":
					n1 = scan.next();
					n2 = scan.next();
					
					System.out.println("$");
				case "nonconnected":
					
					System.out.println("$");
				case "popular":
					
					System.out.println("$");
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
}
