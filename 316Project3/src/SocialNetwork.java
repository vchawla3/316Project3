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
		
	}
	
	public static void createGraph(Scanner scan){
		while(scan.hasNext()){
			String name = scan.nextLine();
			Person p = new Person(name);
			total.put(name, p);
			peopleCount++;
		}
	}
}
