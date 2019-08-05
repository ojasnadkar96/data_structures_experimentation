//TESTCASE (CUCKOO HASHING) 2: We analyze time for put(), get() and remove() over 100 random values with table size 50

import java.util.*;
import java.text.*;

public class testcaseCuckooHashing2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long totalTimePut = 0;
		long totalTimeGet = 0;
		long totalTimeRemove = 0;
		long startTimePut;
		long startTimeGet;
		long startTimeRemove;
		long endTimePut;
		long endTimeGet;
		long endTimeRemove;
		final int NUMOPERATIONS = 100;
		Random rand = new Random();
		List<Integer> testBuffer = new ArrayList<Integer>(); //arraylist to store the random numbers. We use them for get()
		DecimalFormat dec =  new DecimalFormat("#.##");
		HashOperations hashfunc = new CuckooHashing(50); //table size 50
		System.out.println("CUCKOO HASHING");
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
			totalTimePut = totalTimePut + (endTimePut-startTimePut);
		}
		System.out.println("Put Done");
		for(int k=0; k<testBuffer.size(); k++)
		{
			//we will use all successful  gets in this testcase
			startTimeGet = System.nanoTime()/1000;
			hashfunc.get(testBuffer.get(k));
			endTimeGet = System.nanoTime()/1000;
			totalTimeGet = totalTimeGet + (endTimeGet-startTimeGet);
		}
		System.out.println("Get Done");
		for(int j=0; j<testBuffer.size(); j++)
		{
			startTimeRemove = System.nanoTime()/1000;
			hashfunc.remove(testBuffer.get(j));
			endTimeRemove = System.nanoTime()/1000;
			totalTimeRemove = totalTimeRemove + (endTimeRemove-startTimeRemove);
		}
		System.out.println("Remove Done");
		System.out.println();
		System.out.println("Intial hash table size is considered as half of input values & resized when cycles detected");
		System.out.println();
		System.out.println("Average Time per operation (Put): " + Double.valueOf(dec.format(((double)totalTimePut/(double)testBuffer.size()))) + " microseconds");
		System.out.println();
		System.out.println("Average Time per operation (Get): " + Double.valueOf(dec.format(((double)totalTimeGet/(double)testBuffer.size()))) + " microseconds");
		System.out.println();
		System.out.println("Average Time per operation (Remove): " + Double.valueOf(dec.format(((double)totalTimeRemove/(double)testBuffer.size()))) + " microseconds");
		System.out.println();
		testBuffer.clear();
		hashfunc.clearHashTable();
	}

}
