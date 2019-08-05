//TESTCASE (Splay) 2: We analyze time for insert(), search() and delete() over 100 random values

import java.util.*;
import java.text.*;

public class testcaseSplayTree2 {

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
		List<Integer> testBuffer = new ArrayList<Integer>();
		DecimalFormat dec =  new DecimalFormat("#.##");
		TreeOperations treefunc = new SplayTree();
		System.out.println("Splay");
		System.out.println("For " + NUMOPERATIONS + " operations on insert(), search() and delete()");
		System.out.println();
		for(int i=0; i<NUMOPERATIONS; i++)
		{
			int randomOne = rand.nextInt(100);
			testBuffer.add(randomOne);
			startTimePut = System.nanoTime()/1000;
			treefunc.insert(randomOne);
			endTimePut = System.nanoTime()/1000;
			totalTimePut = totalTimePut + (endTimePut-startTimePut);
		}
		System.out.println("Insert Done");
		for(int k=0; k<testBuffer.size(); k++)
		{
			startTimeGet = System.nanoTime()/1000;
			treefunc.search(testBuffer.get(k));
			endTimeGet = System.nanoTime()/1000;
			totalTimeGet = totalTimeGet + (endTimeGet-startTimeGet);
		}
		System.out.println("Search Done");
		for(int j=0; j<testBuffer.size(); j++)
		{
			startTimeRemove = System.nanoTime()/1000;
			treefunc.delete(testBuffer.get(j));
			endTimeRemove = System.nanoTime()/1000;
			totalTimeRemove = totalTimeRemove + (endTimeRemove-startTimeRemove);
		}
		System.out.println("Delete Done");
		System.out.println();
		System.out.println("Average Time per operation (Insert): " + Double.valueOf(dec.format(((double)totalTimePut/(double)testBuffer.size()))) + " microseconds");
		System.out.println();
		System.out.println("Average Time per operation (Search): " + Double.valueOf(dec.format(((double)totalTimeGet/(double)testBuffer.size()))) + " microseconds");
		System.out.println();
		System.out.println("Average Time per operation (Delete): " + Double.valueOf(dec.format(((double)totalTimeRemove/(double)testBuffer.size()))) + " microseconds");
		System.out.println();
		testBuffer.clear();
	}
}
