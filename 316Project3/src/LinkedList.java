/**
 * 
 */

/**
 * @author Vaibhav Chawla (vchawla3), Kirtan Patel, Aaron Chan, Morgan Arrington
 * the list we implement for this project
 */
public class LinkedList<T> {
	/**
	 * private top Node that corresponds to the very first node in the list
	 */
	private Node top;
	/**
	 * private int to keep track of the size of the total list
	 */
	private int size;
	
	/**
	 * the constructor for a linked list
	 */
	public LinkedList() {
		//this.top = new Node(null);
		top = null;
		this.size = 0;
	}
	
	/**
	 * adds new element to the end of the list
	 * @param data value to put into the Node at the end
	 */
	public void add(T data) {
		if (top == null) {
			top = new Node(data);
			top.next = null;
		} else {
			Node n = new Node(data);
			Node cur = top;
			while (cur.next != null) {
				cur = cur.next;
			}
			cur.next = n;
		}
		size++;
		
//		if ( size() == 0) {
//			top = new Node(data);
//		} else {
//			Node temp = new Node(data);
//			Node current = top;
//			
//			while (current.getNext() != null) {
//				current = current.getNext();
//			}
//			current.setNext(temp);
//		}
//		size++;
	}
	
	/**
	 * checks to see if list is empty
	 * @return
	 */
	public boolean isEmpty(){
		return size() == 0;
	}
	
	/**
	 * adds element at specified index and moves rest of list forward
	 * @param data value to place in node
	 * @param idx specific index to place node
	 */
	public void add(T data, int idx) {
		if ( size() == 0) {
			top = new Node(data);
		} else {
			Node temp = new Node(data);
			Node current = top;
			int count = 0;
			
			while(current.getNext() != null && count < idx - 1) {
				current = current.getNext();
				count++;
			}
			
			temp.setNext(current.getNext());
			current.setNext(temp);
		}
		
		size++;
	}
	/**
	 * replaces element at specified index
	 * @param data value to replace in node
	 * @param idx specific index to replace node
	 */
	public boolean replace(T data, int idx){
		if (idx < 0) {
			//error out of bounds
			return false;
		}
		
		
		//first node to replace
		if (idx == 0) {
			Node newNode = new Node(data, top.getNext());
			top = newNode;
			return true;
		}
		
		//every node after the first
		Node current = top;
		for(int i = 0; i < idx - 1; i++) {
			current = current.getNext();
		}
		Node newNode = new Node(data);
		newNode.setNext(current.getNext().getNext());
		current.setNext(newNode);
		return true;
	}
	
	/**
	 * gets the value at the specified index
	 * @param idx to get value from
	 * @return value of datatype T from index
	 */
	public T get(int idx){
		if (idx < 0) {
			//error out of bounds
			return null;
		}
		
		
		Node current = top;
		for(int i = 0; i < idx; i++) {
			if (current.getNext() == null){
				//idx too high, no element at that index to return null
				return null;
			}
			current = current.getNext();
		}
		return current.getVal();
	}
	
	/**
	 * removes the node at the specified index
	 * @param idx index of node to remove
	 * @return boolean whether node is removed or not
	 */
	public boolean remove(int idx) {
		if (idx < 0 || idx > size()) {
			return false;
		}
		Node current = top;
		if ( idx == 0 ){
			top = top.getNext();
			return true;
		} else if ( idx == (size() - 1) ) {
			for(int i = 0; i < idx - 1; i++) {
				current = current.getNext();
			}
			current.setNext(null);
			size--;
			return true;
		}
		
		for(int i = 0; i < idx - 1; i++) {
			if (current.getNext() == null) {
				break;
			}
			current = current.getNext();
		}
		size--;
		current.setNext(current.getNext().getNext());
		return true;
	}
	
	/**
	 * returns current the size of the list
	 * @return size of the list
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * outputs the entire list as a string
	 */
	public String toString() {
        String thelist = "[ ";
        Node cur = top;
		while (cur != null) {
			thelist+= cur.getVal() + " ";
			cur = cur.next;
		}
		thelist+= "]";
		return thelist;
    }
	
	/**
	 * inner class Node for linked list
	 */
	public class Node {

		/**
		 * next node in the list
		 */
		public Node next;
		/**
		 * value inside the node
		 */
		public T val;
		
		/**
		 * Node constructor
		 * @param valData value to put into new Node being constructed
		 */
		public Node(T valData) {
			this.next = null;
			this.val = valData;
		}
		/**
		 * Node constructor
		 * @param valData value to put into new Node
		 * @param nextVal Node value to put as reference to next Node
		 */
		public Node(T valData, Node nextVal) {
			this.next = nextVal;
			this.val = valData;
		}
		
		/**
		 * gets the val of current Node
		 * @return val inside Node
		 */
		public T getVal() {
			return this.val;
		}
		
		/**
		 * gets ref to Next Node
		 * @return the Node next to this current one
		 */
		public Node getNext(){
			return this.next;
		}
		
		/**
		 * sets the ref to the Next Node
		 * @param nextData the Node that will be set to this.next
		 */
		public void setNext(Node nextData){
			this.next = nextData;
		}
	}
}
