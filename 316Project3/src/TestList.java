
public class TestList {
	
	public static void main(String[] args){
		LinkedList<Integer> tests = new LinkedList<Integer>();
		
		System.out.println(tests.isEmpty());
		
		tests.add(1);
		tests.add(2);
		tests.add(3);
		
		//should be 3
		System.out.println(tests.size());
		
		System.out.println(tests.toString());
	}
		
}
