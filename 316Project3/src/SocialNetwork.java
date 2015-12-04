import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class SocialNetwork {
	
	//LinkedList<Person> allPeople = new LinkedList<Person>();
	public static HashMap<String, Person> total = new HashMap<String,Person>();
	public static int peopleCount = 0;
	
	public static void main(String args[]){
		try {
			File input = new File(args[0]);
			Scanner scan = new Scanner(input);
			
			createGraph(scan);
			parseCommands();
		} catch (FileNotFoundException e) {
			System.out.println("File not Found!");
			e.printStackTrace();
		}
	}
	
	public static void parseCommands(){
		Scanner scan = new Scanner(System.in);
		
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
