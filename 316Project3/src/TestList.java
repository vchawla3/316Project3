
public class TestList {
	
	public static void main(String[] args){
		LinkedList<Integer> tests = new LinkedList<Integer>();
		
		System.out.println(tests.isEmpty());
		
		tests.add(1);
		tests.add(2);
		tests.add(3);
		//should not add
		tests.add(5,3);
		
		//should be 3
		System.out.println(tests.size());
		System.out.println(tests.toString());
		
		tests.add(6, 2);
		System.out.println(tests.toString());
		
		tests.replace(7, 2);
		System.out.println(tests.size());
		
		
		
		System.out.println(tests.toString());
		
		//should be 3
		System.out.println(tests.get(3));
		//should be 1
		System.out.println(tests.get(0));
		//should be 7
		System.out.println(tests.get(2));
		//should be true
		if (tests.get(4) == null) {
			System.out.println("true");
		}
		
		tests.remove(3);
		
		
		System.out.println(tests.toString());
		
	}
		
}
