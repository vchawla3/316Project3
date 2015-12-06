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
		if (top == null){
			//simply puts at top of list
			top = new Node(data);
			size++;
		} else {
			Node n = new Node(data);
			Node cur = top;
			if (idx == 0 ) {
				n.setNext(cur);
				top = n;
				size++;
			} else {
				boolean t = true;
				int c = 0;
				while ( cur.next != null && t) {
					if ( c == idx-1) {
						n.setNext(cur.next);
						cur.setNext(n);
						size++;
						t = false;
					} else {
						cur = cur.next;
						c++;
					}
					
				}
			}
		}
	}
	
	/**
	 * replaces element at specified index
	 * @param data value to replace in node
	 * @param idx specific index to replace node
	 */
	public boolean replace(T data, int idx){
		if (idx < 0 || idx > size() - 1) {
			//error out of bounds
			return false;
		}
		
		
		
		//first node to replace
		if (idx == 0) {
			Node n = new Node(data);
			n.setNext(top.next);
			top = n;
			return true;
		}
		
		//every node after the first
		Node cur = top;
		for(int i = 0; i < idx - 1; i++) {
			if (cur == null){
				return false;
			}
			cur = cur.next;
		}
		Node n = new Node(data);
		n.setNext(cur.next.next);
		cur.setNext(n);
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
		
		if (idx == 0) {
			return top.getVal();
		}
		Node cur = top;
		int c = 0;
		while (cur != null) {
			if (c == idx) {
				return cur.getVal();
			} else {
				cur = cur.next;
				c++;
			}
		}
		return null;
	}
	
	/**
	 * checks if object T is in this list
	 * @param x object T to look for
	 * @return boolean whether object is in the list
	 */
	public boolean contains(T x){
		Node cur = top;
		while (cur != null) {
			if (cur.getVal() == x) {
				return true;
			}
			cur = cur.next;
		}
		return false;
	}
	
	/**
	 * removes and returns head, 
	 * returns null if empty
	 * @return head or null
	 */
	public T removeHead(){
		if (top == null ){
			return null;
		}
		Node cur = top;
		top = top.next;
		return cur.getVal();
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
