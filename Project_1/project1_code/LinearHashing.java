public class LinearHashing extends HashOperations {
	
	MapEntry[] hashtable;
	public int HASHTABLE = 16; //start with a hash table of size as 16 (power of 2)
	public double LOADFACTORPUT;
	public static final double LOADFACTORREMOVE = 0.25; //we will consider the load factor of remove() to be constant at 0.25
	public int sizeCheck = 0;
	public double loadFactor = 0.0;
	
	public LinearHashing(double putLoadFactor)
	{
		this.LOADFACTORPUT = putLoadFactor;
		this.hashtable = new MapEntry[HASHTABLE];
		for(int i=0; i<HASHTABLE; i++)
		{
			hashtable[i] = null;
		}
	}
	
	public int HashCode(int hashkey)
	{
		return hashkey%HASHTABLE;
	}
	
	public int get(int hashkey)
	{
		int hash = HashCode(hashkey);
		int count = 0;
		while(hashtable[hash]!=null && hashtable[hash].getKey()!=hashkey && count<HASHTABLE)
		{
			hash = (hash+1)%HASHTABLE;
			count++;
		}
		if(hashtable[hash]==null || count>=HASHTABLE)
		{
			System.out.println("No Key Exists");
			return -1;
		}
		else 
		{
			return hashtable[hash].getValue();
		}
	}
	
	public void put(int hashkey, int hashvalue)
	{
		if(sizeCheck==HASHTABLE)
		{
			System.out.println("Hash Table is Full");
		}
		int hash = HashCode(hashkey);
		while(hashtable[hash]!=null && hashtable[hash].getKey()!=hashkey)
		{
			hash = HashCode(hash+1);
		}
		hashtable[hash] = new MapEntry(hashkey,hashvalue);
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
		int hash = HashCode(hashkey);
		int count = 0;
		while(hashtable[hash]!=null && hashtable[hash].getKey()!=hashkey && count<HASHTABLE)
		{
			hash = HashCode(hash+1);
			count++;
		}
		if(hashtable[hash]==null || count==HASHTABLE)
		{
			return; //no key found
		}
		int j = HashCode(hash+1);
		count++;
		while(hashtable[j]!=null && count<HASHTABLE)
		{
			int nextValue = hashtable[j].getKey()%HASHTABLE;
			if(nextValue<=hash || nextValue>j)
			{
				hashtable[hash] = hashtable[j];
				hash = j;
			}
			j = HashCode(j+1);
			count++;
		}
		hashtable[hash] = null;
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
		
		public MapEntry(int key, int value)
		{
			this.key = key;
			this.value = value;
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
