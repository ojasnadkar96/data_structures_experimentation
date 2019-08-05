public class CuckooHashing extends HashOperations {
	
	MapEntry[][] hashtable;
	public int HASHTABLE = 0;
	public int sizeCheck = 0;
	//we resize when chain is detected
	
	public CuckooHashing(int hashTableSize)
	{
		this.HASHTABLE = hashTableSize;
		this.hashtable = new MapEntry[2][HASHTABLE];
		for(int i=0; i<2; i++)
		{
			for(int j=0; j<HASHTABLE; j++)
			hashtable[i][j] = null;
		}
	}
	
	public int HashCode(int hashkey, int hashtablenumber)
	{
		if(hashtablenumber==0)
		{
			return hashkey%HASHTABLE;
		}
		else
		{
			return (hashkey/HASHTABLE)%HASHTABLE;
		}
	}
	
	public int get(int hashkey)
	{
		int hashtableone = HashCode(hashkey,0);
        int hashtabletwo = HashCode(hashkey,1);
        if(hashtable[0][hashtableone]!= null && hashtable[0][hashtableone].getKey() == hashkey)
        {
        	return hashtable[0][hashtableone].getValue();
        }
        if(hashtable[1][hashtabletwo]!=null && hashtable[1][hashtabletwo].getKey()==hashkey)
        {
        	return hashtable[1][hashtabletwo].getValue();
        }
        else
        {
        	return -1;
        } 
	}
	
	public void put(int hashkey, int hashvalue)
	{
   	 int count = 0;
   	 int hashtableone = HashCode(hashkey,0);
   	 int hashtabletwo = HashCode(hashkey,1);
   	 int mainkey = hashkey;
   	 if(hashtable[0][hashtableone]!=null && hashtable[0][hashtableone].getKey()==hashkey)
   	 {
   		 hashtable[0][hashtableone].value=hashvalue;
   	 }
   	 if(hashtable[1][hashtabletwo]!=null && hashtable[1][hashtabletwo].getKey()==hashkey)
   	 {
   		 hashtable[1][hashtabletwo].value=hashvalue;
   	 }
   	 if(hashtable[0][hashtableone]!=null && hashtable[0][hashtableone].getKey()!=hashkey)
   	 {
   		 if(!checkForLoops(mainkey,hashkey,hashvalue,0,count))
   		 {
   			 sizeCheck++;
   		 }
   	 }
   	 else
   	 {
   		 hashtable[0][hashtableone]=new MapEntry(hashkey,hashvalue);
   		 sizeCheck++;
   	 }
	}
	
	public void remove(int hashkey)
	{
		int hashtableone = HashCode(hashkey,0);
    	int hashtabletwo = HashCode(hashkey,1);
    	if(hashtable[0][hashtableone]!=null && hashtable[0][hashtableone].getKey()==hashkey)
    	{
    		hashtable[0][hashtableone]=null;
    		sizeCheck--;
    	}
    	else if(hashtable[1][hashtabletwo]!=null && hashtable[1][hashtabletwo].getKey()==hashkey)
    	{
    		hashtable[1][hashtabletwo]=null;
    		sizeCheck--;
    	}
    	else
    	{
    		return;
    	}
	}
	
	public void printHashTable()
	{
		for(int i=0; i<2; i++)
		{
			for(int j=0; j<HASHTABLE; j++)
			{
				System.out.print("HashTable: " + (i+1) + " - " + "Index of HashTable: " + j + " ");
				if(hashtable[i][j]!=null)
				{
					System.out.println("Key: " + hashtable[i][j].getKey() + "  " + "Value: " + hashtable[i][j].getValue());
				}
				else
				{
					System.out.println("Key: NULL  Value: NULL");
				}
			}
			System.out.println();
		}
	}
	
	public double getLoadFactorPut() //we do not consider load factor for resizing
	{
		return 0.0;
	}
	
	public double getLoadFactorRemove() //we do not consider load factor for resizing
	{
		return 0.0;
	}
	
	public void clearHashTable()
	{
		hashtable = null;
	}
	
	public boolean checkForLoops(int mainkey, int hashkey, int hashvalue, int hashtablenumber, int count)
    {
    	if(hashkey==mainkey)
    	{
    		count++;
    	}
    	if(count==3)
    	{
    		MapEntry[][] temp = new MapEntry[2][HASHTABLE];
			for(int i=0; i<hashtable.length; i++)
			{
				for(int j=0; j<hashtable[0].length; j++)
				{
					temp[i][j] = hashtable[i][j];
				}
			}
			HASHTABLE*=2;
			hashtable = new MapEntry[2][HASHTABLE];
			for(int a=0; a<hashtable.length; a++)
			{
				for(int b=0; b<hashtable[0].length; b++)
				{
				hashtable[a][b] = null;
				}
			}
			for(int k=0; k<temp.length; k++)
			{
				for(int l=0; l<temp[0].length; l++)
				{
				if(temp[k][l]!=null)
				{
					put(temp[k][l].getKey(),temp[k][l].getValue());
				}
				}
			}
			temp = null;
    		return true;
    	}
    	int hash=HashCode(hashkey,hashtablenumber);
    	MapEntry pair = hashtable[hashtablenumber][hash];
    	hashtable[hashtablenumber][hash]=new MapEntry(hashkey,hashvalue);
    	if(pair!=null)
    	{
    		return checkForLoops(mainkey,pair.getKey(),pair.getValue(),1-hashtablenumber,count);
    	}
    	return false;
    }
	
	static class MapEntry {
		public int key;
		public int value;
		public MapEntry nextValue;
		
		public MapEntry(int key, int value)
		{
			this.key = key;
			this.value = value;
			this.nextValue = null;
		}
		
		public int getKey()
		{
			return this.key;
		}
		
		public int getValue()
		{
			return this.value;
		}
	}
}