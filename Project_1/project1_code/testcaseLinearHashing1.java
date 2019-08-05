//TESTCASE (LINEAR HASHING) 1: Checks correctness of the algorithm based on a small input example

public class testcaseLinearHashing1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashOperations hashfunc = new LinearHashing(0.5); //default load factor of 0.5
		hashfunc.put(50,1);
		hashfunc.put(700,2);
		hashfunc.put(76,3);
		hashfunc.put(85,4);
		hashfunc.put(92,5);
		hashfunc.put(73,6);
		hashfunc.put(101,5); //end of put operations
		hashfunc.remove(85); //removing a key
		hashfunc.get(85); //this should return "No Key Exists"
		int a = hashfunc.get(76); //this should return the value for 76
		hashfunc.printHashTable(); //prints hash table state after all the above operations
		System.out.println("Value of key just searched:" + " " + a); //we can verify from printed hash table that value of a is correct
	}

}
