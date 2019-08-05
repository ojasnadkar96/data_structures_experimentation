public class ChainedHashing extends HashOperations {
	
	MapEntry[] hashtable;
	public int HASHTABLE = 0;
	public int sizeCheck = 0;
	
	public ChainedHashing(int hashTableSize)
	{
		this.HASHTABLE = hashTableSize;
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
		if(hashtable[hash]==null)
		{
			System.out.println("No Key Exists here");
			return -1;
		}
		else 
		{	
			MapEntry pair = hashtable[hash];
			while(pair!=null && pair.key!=hashkey)
			{
				pair = pair.nextValue;
			}
				if(pair==null)
				{
					System.out.println("No Key Exists");
					return -1;
				}
				else
				{
					return pair.value;
				}
		}
	}
	
	public void put(int hashkey, int hashvalue)
	{
		int hash = HashCode(hashkey);
		if(hashtable[hash]==null)
		{
			hashtable[hash] = new MapEntry(hashkey,hashvalue);
		}
		else
		{
			MapEntry pair = hashtable[hash];
			while(pair.nextValue!=null && pair.key!=hashkey)
			{
				pair = pair.nextValue;
			}
			if(pair.key==hashkey)
			{
				pair.value = hashvalue;
			}
			else
			{
				pair.nextValue = new MapEntry(hashkey,hashvalue);
			}
		}
		sizeCheck++;
	}
	
	public void remove(int hashkey)
	{
		int hash = HashCode(hashkey);
		if(hashtable[hash]==null)
		{
			return;
		}
		else if(hashtable[hash]!=null)
		{
			MapEntry prevPair = null;
			MapEntry pair = hashtable[hash];
			while(pair.nextValue!=null && pair.key!=hashkey)
			{
				prevPair = pair;
				pair = pair.nextValue;
			}
			if(pair.key==hashkey)
			{
				if(prevPair==null)
				{
					hashtable[hash] = pair.nextValue;
				}
				else
				{
					prevPair.nextValue = pair.nextValue;
				}
				sizeCheck--;
			}
			else
			{
				return;
			}
		}
	}
	
	public void printHashTable()
	{
		
		for(int i=0; i<HASHTABLE; i++)
		{
			int flag = 0;
			int counter = 1;
			System.out.println();
			System.out.print("Index of HashTable: " + i + " - ");
			MapEntry pair = hashtable[i];
			if(hashtable[i]!=null)
			{
				while(pair!=null)
					{
					if(flag==0)
					{
						System.out.println("Key: " + pair.getKey() + "  " + "Value: " + pair.getValue());
						flag = 1;
					}
					else
					{
						System.out.print(" Index of HashTable: " + i + "." + counter + " - " + "Key: " + pair.getKey() + "  " + "Value: " + pair.getValue());
						System.out.println();
						counter++;
					}
					pair = pair.nextValue;
					}
			}
			else
			{
				System.out.println("Key: NULL  Value: NULL");
			}
		}
		System.out.println();
	}
	
	public double getLoadFactorPut() //no resizing done
	{
		return 0.0;
	}
	
	public double getLoadFactorRemove() //no resizing done
	{
		return 0.0;
	}
	
	public void clearHashTable()
	{
		hashtable = null;
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
