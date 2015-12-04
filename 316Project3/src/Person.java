
public class Person {

	String name;
	LinkedList<Person> adjList = new LinkedList<Person>();
	
	public Person(String name){
		this.name = name;
	}
	
	public void addAdj(Person p){
		adjList.add(p);
	}
}
