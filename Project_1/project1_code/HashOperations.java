public abstract class HashOperations {
	
	public abstract int get(int key);
	public abstract void put(int key, int value);
	public abstract void remove(int key);
	public abstract void printHashTable();
	public abstract double getLoadFactorPut();
	public abstract double getLoadFactorRemove();
	public abstract void clearHashTable();
}
