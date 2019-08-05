//TESTCASE (AVL) 5: We will analyze the worst-case time for each operation - insert(), search() and delete()

import java.util.*;

public class testcaseAVLTree5 {

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
		List<Integer> testBuffer = new ArrayList<Integer>();
		TreeOperations hashfunc = new AVLTree();
		System.out.println("AVL");
		System.out.println("For " + NUMOPERATIONS + " operations on insert(), search() and delete()");
		System.out.println();
		for(int i=0; i<NUMOPERATIONS; i++)
		{
			int randomOne = rand.nextInt(100);
			testBuffer.add(randomOne);
			startTimePut = System.nanoTime()/1000;
			hashfunc.insert(randomOne);
			endTimePut = System.nanoTime()/1000;
			if((endTimePut-startTimePut)>=maxTimePut) maxTimePut = (endTimePut-startTimePut);
		}
		System.out.println("Insert Done");
		for(int k=0; k<testBuffer.size(); k++)
		{
			startTimeGet = System.nanoTime()/1000;
			hashfunc.search(testBuffer.get(k));
			endTimeGet = System.nanoTime()/1000;
			if((endTimeGet-startTimeGet)>=maxTimeGet) maxTimeGet = (endTimeGet-startTimeGet);
		}
		System.out.println("Search Done");
		for(int j=0; j<testBuffer.size(); j++)
		{
			startTimeRemove = System.nanoTime()/1000;
			hashfunc.delete(testBuffer.get(j));
			endTimeRemove = System.nanoTime()/1000;
			if((endTimeRemove-startTimeRemove)>=maxTimeRemove) maxTimeRemove = (endTimeRemove-startTimeRemove);
		}
		System.out.println("Delete Done");
		System.out.println();
		System.out.println("Worst-case Time per operation (Insert): " + maxTimePut + " microseconds");
		System.out.println();
		System.out.println("Worst-case Time per operation (Search): " + maxTimeGet + " microseconds");
		System.out.println();
		System.out.println("Worst-case Time per operation (Delete): " + maxTimeRemove + " microseconds");
		System.out.println();
		testBuffer.clear();
	}
}