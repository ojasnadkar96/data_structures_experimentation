//TESTCASE (DOUBLE HASHING) 5: We will analyze the worst-case time for each operation - put(), get() and remove()

import java.util.*;

public class testcaseDoubleHashing5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long maxTimePut = Integer.MIN_VALUE;
		long maxTimeGet = Integer.MIN_VALUE;
		long maxTimeRemove = Integer.MIN_VALUE;
		long startTimePut;
		long startTimeGet;
		long startTimeRemove;
		long endTimePut;
		long endTimeGet;
		long endTimeRemove;
		final int NUMOPERATIONS = 1000;
		Random rand = new Random();
		List<Integer> testBuffer = new ArrayList<Integer>(); //arraylist to store the random numbers. We use them for get()
		HashOperations hashfunc = new DoubleHashing(0.5); //load factor of 0.5 (default)
		System.out.println("DOUBLE HASHING");
		System.out.println("For " + NUMOPERATIONS + " operations on put(), get() and remove()");
		System.out.println();
		for(int i=0; i<NUMOPERATIONS; i++)
		{
			int randomOne = rand.nextInt(100);
			int randomTwo = rand.nextInt(10);
			testBuffer.add(randomOne);
			startTimePut = System.nanoTime()/1000;
			hashfunc.put(randomOne,randomTwo);
			endTimePut = System.nanoTime()/1000;
			if((endTimePut-startTimePut)>=maxTimePut) maxTimePut = (endTimePut-startTimePut);
		}
		System.out.println("Put Done");
		for(int k=0; k<testBuffer.size(); k++)
		{
			//we will use all successful  gets in this testcase
			startTimeGet = System.nanoTime()/1000;
			hashfunc.get(testBuffer.get(k));
			endTimeGet = System.nanoTime()/1000;
			if((endTimeGet-startTimeGet)>=maxTimeGet) maxTimeGet = (endTimeGet-startTimeGet);
		}
		System.out.println("Get Done");
		for(int j=0; j<testBuffer.size(); j++)
		{
			startTimeRemove = System.nanoTime()/1000;
			hashfunc.remove(testBuffer.get(j));
			endTimeRemove = System.nanoTime()/1000;
			if((endTimeRemove-startTimeRemove)>=maxTimeRemove) maxTimeRemove = (endTimeRemove-startTimeRemove);
		}
		System.out.println("Remove Done");
		System.out.println();
		System.out.println("At a Load Factor of " + hashfunc.getLoadFactorPut());
		System.out.println("Worst-case Time per operation (Put): " + maxTimePut + " microseconds");
		System.out.println();
		System.out.println("Worst-case Time per operation (Get): " + maxTimeGet + " microseconds");
		System.out.println();
		System.out.println("At a Load Factor of " + hashfunc.getLoadFactorRemove());
		System.out.println("Worst-case Time per operation (Remove): " + maxTimeRemove + " microseconds");
		System.out.println();
		testBuffer.clear();
		hashfunc.clearHashTable();
	}

}