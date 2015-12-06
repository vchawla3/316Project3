
public class Person {

	public String name;
	public LinkedList<Person> adjList = new LinkedList<Person>();
	public boolean visited;
	public Person pred;
	
	public Person(String name) {
		this.name = name;
		this.visited = false;
	}
	
	public void addAdj(Person p) {
		adjList.add(p);
	}
	
	public boolean isAdj(Person p) {
		return adjList.contains(p);
	}
	
	public LinkedList<Person> getAdj(){
		return this.adjList;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setVisited(boolean s){
		this.visited = s;
	}
	
	public boolean isVisit(){
		return this.visited;
	}
	
	public void setPred(Person p){
		this.pred = p;
	}
	
	public Person getPred(){
		return this.pred;
	}
}
