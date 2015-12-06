/**
 * class representing a Person or Node in our graph
 * each person holds a list of people who they are adjacent too
 * @author Vaibhav Chawla (vchawla3), Kirtan Patel, Aaron Chan, Morgan Arrington
 */
public class Person {

	/**
	 * String holding the name of this person
	 */
	public String name;
	/**
	 * LinkedList of all adjacent People in the graph
	 */
	public LinkedList<Person> adjList = new LinkedList<Person>();
	/**
	 * boolean of whether this Person has been visited
	 */
	public boolean visited;
	/**
	 * pointer to a Person object who is this Person's predecessor 
	 */
	public Person pred;
	/**
	 * double value of the popularity of this Person
	 */
	public double popularity;
	
	/**
	 * constructor for person, takes a string for the name
	 * @param name name of person
	 */
	public Person(String name) {
		this.name = name;
		this.visited = false;
		this.popularity = 0;
	}
	
	/**
	 * adds a Person to the list of adjacent Nodes within this Person
	 * @param p Person to add
	 */
	public void addAdj(Person p) {
		adjList.add(p);
	}
	
	/**
	 * checks if a person is in the adjacency list
	 * @param p Person to check
	 * @return true if in the list, false if not
	 */
	public boolean isAdj(Person p) {
		return adjList.contains(p);
	}
	
	/**
	 * Method to get the adjacency list
	 * @return the adjacency list
	 */
	public LinkedList<Person> getAdj(){
		return this.adjList;
	}
	
	/**
	 * gets the name of this Person
	 * @return name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * sets whether the person has been visited or not
	 * @param s boolean to set the visited flag too
	 */
	public void setVisited(boolean s){
		this.visited = s;
	}
	
	/**
	 * checks if this Person has been visited
	 * @return true or false depending on their visited status
	 */
	public boolean isVisit(){
		return this.visited;
	}
	
	/**
	 * sets the pointer to the Predecessor of
	 * this Person in the graph
	 * @param p Person to set pred too
	 */
	public void setPred(Person p){
		this.pred = p;
	}
	
	/**
	 * returns the Predecessor of this person
	 * @return the Person object who is the predecessor
	 */
	public Person getPred(){
		return this.pred;
	}
	
	/**
	 * sets the popularity of this Person
	 * @param p popularity value to set
	 */
	public void setPop(double p){
		this.popularity = p;
	}
	
	/**
	 * gets the popularity of this person
	 * @return a double var of the Popularity
	 */
	public double getPop(){
		return this.popularity;
	}
}
