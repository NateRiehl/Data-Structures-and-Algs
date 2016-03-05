/**
 * 
 * @author Nate Riehl
 * Implementation of HashTable using separate chaining ( Closed addressing )
 */
public class HashTable_SC<K, V> {

	private double maxLoadFactor;
	private int count;
	private DList<HashEntry<K, V>>[] table;
	private int INIT_CAPACITY = 5;

	public HashTable_SC(){
		maxLoadFactor = 1.0;
		count = 0;
		table = (DList<HashEntry<K, V>>[]) new Object[INIT_CAPACITY];
	}
	/**
	 * Computes hashCode for a given key
	 * @param key to hash 
	 * @return hashCode given to key
	 */
	public int hash(K key){
		int i = 0x7fffffff;
		return ((key.hashCode() & i)% table.length);
	}

	/** 
	 * Adds a new entry (key-value pair) to table if key doesn't exist; otherwise replace the current value with the new value
	 * @param key the target key
	 * @param value associated value with key
	 * @return previous value being replaced or null if new
	 */
	public V put(K key, V value) {
		//If the fill rate is reached then resize and rehash
		double fillRate = count / table.length;
		if(fillRate >= maxLoadFactor){
			//TODO Resize and rehash
		}
		int hI = hash(key);
		// if DList does not exist at hI, create and assign to table[hI]
		if (table[hI] == null) {
			table[hI] = new DList<HashEntry<K, V>>();
		}
		// note: be sure to override equals method in HashEntry to compare keys only
		DList<HashEntry<K, V>> list = table[hI];
		HashEntry<K, V> newEntry = new HashEntry<K, V>(key, value);
		// Check if it exists
		int index = list.indexOf(newEntry);
		if (index == -1) {
			list.add(newEntry);
			++count;
			return null;
		}
		else {
			HashEntry<K, V> oldEntry = list.set(index, newEntry); 	
			return oldEntry.getValue();
		}
	}

	public boolean containsKey(K key){
		int hashIndex = hash(key);
		HashEntry<K, V> entry = new HashEntry<K, V>(key, null);
		return table[hashIndex].contains(entry);
	}
	/**
	 * Algorithm:
	 * 1. Determine new table size:
	 * 		(a) double current table size
	 * 		(b) Find smallest prime # greater than (a)
	 * 		(b) Create temp array of size (b)
	 * 2. For existing entry (key-value pair) in old table, put it in the new table (rehash)
	 */
	private void resize(){
		int newSize = (table.length * 2) + 1;
		while(!isPrime(newSize)){
			newSize ++;
		}
		DList<HashEntry<K, V>>[] temp = table;
		DList<HashEntry<K, V>>[] table = (DList<HashEntry<K, V>>[]) new Object[newSize];

		//rehash every entry:
		for(DList<HashEntry<K, V>> dl : temp){ //For each index of old table
			for(HashEntry<K, V> he : dl){ //For each entry in each list @ each index
				put(he.getKey(), he.getValue()); 	//Rehash and put in the new table
			}
		}
	}
	/**
	 * Helper method for resize()
	 * @param n num to check
	 * @return true if boolean. False otherwise
	 */
	private boolean isPrime(int n){
		int i =2;
		while(i <= n/2){
			if(n%i == 0){
				return false;
			}
			i++;
		}
		return true;
	}
	
	public V get(K key){
		int hashIndex = hash(key);
		HashEntry<K, V> entry = new HashEntry<K, V>(key, null);
		int index = table[hashIndex].indexOf(entry);
		if(index < 0){
			return null;
		}
		return table[hashIndex].get(index).getValue();
	}
}
