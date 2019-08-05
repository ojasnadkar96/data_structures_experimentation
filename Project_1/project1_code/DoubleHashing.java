public class DoubleHashing extends HashOperations {

	MapEntry[] hashtable;
	public static int HASHTABLE = 16; //start with a hash table of size as 16 (power of 2)
	public static final int PRIME = 7; 
	public double LOADFACTORPUT = 0.5;
	public static final double LOADFACTORREMOVE = 0.25; //we will consider the load factor of remove() to be constant at 0.25
	public int sizeCheck = 0;
	public double loadFactor = 0.0;
	
	public DoubleHashing(double putLoadFactor)
	{
		this.LOADFACTORPUT = putLoadFactor;
		this.hashtable = new MapEntry[HASHTABLE];
		for(int i=0; i<HASHTABLE; i++)
		{
			hashtable[i] = null;
		}
	}
	
	public int HashCodeOne(int hashkey)
	{
		return hashkey%HASHTABLE;
	}
	
	public int HashCodeTwo(int hashkey)
	{
		return (PRIME - (hashkey%PRIME));
	}
	
	public int get(int hashkey)
	{
		int hashone = HashCodeOne(hashkey);
        int hashtwo = HashCodeTwo(hashkey);
        int count = 0;
        while (hashtable[hashone]!=null && hashtable[hashone].key!=hashkey && count<HASHTABLE) 
        {
            hashone = (hashone + hashtwo) % HASHTABLE;
            count++;
        }
        if (hashtable[hashone]==null || count==HASHTABLE || hashtable[hashone].checkValid) 
        {
        	System.out.println("No Key Exists");
            return -1;
        } 
        else
            return hashtable[hashone].value;
	}
	
	public void put(int hashkey, int hashvalue)
	{
		if (sizeCheck==HASHTABLE) 
        {
            System.out.println("No Space in Hash Table");
        }
        int hashone = HashCodeOne(hashkey);
        int hashtwo = HashCodeTwo(hashkey);
        while (hashtable[hashone]!=null && hashtable[hashone].key!=hashkey && !hashtable[hashone].checkValid) 
        {
            hashone = (hashone+hashtwo)%HASHTABLE;
        }
        hashtable[hashone] = new MapEntry(hashkey, hashvalue);
        sizeCheck++;
        loadFactor = (double)sizeCheck/(double)hashtable.length;
		if(loadFactor>=LOADFACTORPUT)
		{
			MapEntry[] temp = new MapEntry[HASHTABLE];
			for(int i=0; i<hashtable.length; i++)
			{
				temp[i] = hashtable[i];
			}
			HASHTABLE*=2;
			hashtable = new MapEntry[HASHTABLE];
			for(int j=0; j<hashtable.length; j++)
			{
				hashtable[j] = null;
			}
			for(int k=0; k<temp.length; k++)
			{
				if(temp[k]!=null)
				{
					put(temp[k].getKey(),temp[k].getValue());
				}
			}
			temp = null;
		}
	}
	
	public void remove(int hashkey)
	{
		int count = 0;
        int hashone = HashCodeOne(hashkey);
        int hashtwo = HashCodeTwo(hashkey);
        while (hashtable[hashone]==null || (hashtable[hashone].key!=hashkey && count<HASHTABLE))
        {
            hashone = (hashone+hashtwo)%HASHTABLE;
            count++;
        }
        if (count==HASHTABLE) 
        {
        	return;
        }
        else 
        {
            hashtable[hashone].checkValid = true;
            sizeCheck--;
            loadFactor = (double)sizeCheck/(double)hashtable.length;
    		if(loadFactor<=LOADFACTORREMOVE)
    		{
    			MapEntry[] temp = new MapEntry[HASHTABLE];
    			for(int i=0; i<hashtable.length; i++)
    			{
    				temp[i] = hashtable[i];
    			}
    			HASHTABLE = HASHTABLE/2 + 1;
    			hashtable = new MapEntry[HASHTABLE];
    			for(int l=0; l<hashtable.length; l++)
    			{
    				hashtable[l] = null;
    			}
    			for(int k=0; k<temp.length; k++)
    			{
    				if(temp[k]!=null)
    				{
    					put(temp[k].getKey(),temp[k].getValue());
    				}
    			}
    			temp = null;
    		}
        }
	}
	
	public void printHashTable()
	{
		for(int i=0; i<HASHTABLE; i++)
		{
			System.out.println();
			System.out.print("Index of HashTable: " + i + " - ");
			if(hashtable[i]!=null)
			{
				System.out.println("Key: " + hashtable[i].getKey() + "  " + "Value: " + hashtable[i].getValue());
			}
			else
			{
				System.out.println("Key: NULL  Value: NULL");
			}
		}
		System.out.println();
	}
	
	public double getLoadFactorPut()
	{
		return LOADFACTORPUT;
	}
	
	public double getLoadFactorRemove()
	{
		return LOADFACTORREMOVE;
	}
	
	public void clearHashTable()
	{
		hashtable = null;
	}
	
	static class MapEntry {
		public int key;
		public int value;
		public boolean checkValid;
		
		public MapEntry(int key, int value)
		{
			this.key = key;
			this.value = value;
			this.checkValid = false;
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
