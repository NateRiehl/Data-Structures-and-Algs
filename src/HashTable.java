/**
 * Implementation of Hash Table with quadratic probing
 * @author Nate Riehl
 * Fall 2015, CS216
 * HW 4
 */
public class HashTable<K, V> {
	private HashEntry<K, V>[] table;
	private int count;
	private double maxLoadFactor;
	private int capacity = 7;
	/**
	 * Standard constructor for HashTable
	 */
	public HashTable(){
		table = (HashEntry<K, V>[]) new HashEntry[capacity];
		maxLoadFactor = 0.5;
		count = 0;
	}
	/**
	 * Constructor which takes a custom maxLoadFactor
	 * @param lf defines the maxLoadFactor for HashTable
	 */
	public HashTable(double lf){
		table = (HashEntry<K, V>[]) new HashEntry[capacity];
		maxLoadFactor = lf;
		count = 0;
	}
	/**
	 * Constructor which takes a custom capacity for table
	 * @param cap defines the initial capacity for HashTable
	 */
	public HashTable(int cap){
		capacity = cap;
		table = (HashEntry<K, V>[]) new HashEntry[capacity];
		maxLoadFactor = 0.5;
		count = 0;
	}
	/**
	 * Constructor which takes a maxLoadFactor and Initial capacity
	 * @param lf defines the maxLoadFactor for HashTable
	 * @param cap defines the initial capacity for HashTable
	 */
	public HashTable(int cap, double lf){
		capacity = cap;
		table = (HashEntry<K, V>[]) new HashEntry[capacity];
		maxLoadFactor = lf;
		count = 0;
	}
	/**
	 * Computes hashCode for a given key
	 * @param key to compute hashCode
	 * @return hashCode of given key
	 */
	public int hash(K key){
	//	return ((key.hashCode() & 0x7fffffff)% table.length);
		return key.toString().length() % table.length;
	}
	/**
	 * (Helper method)Finds location to place key
	 * Checks primary index then does quadratic probe to locate an open location OR existing key
	 * @param primaryIndex of given key
	 * @param key to find location for
	 * @return Location of empty index or index of existing key. -1 If neither is found
	 */
	private int placeKey(int primaryIndex, K key){
		int i = 0;
		int index = primaryIndex;
		while((i * i) < table.length){
			index = (primaryIndex +( i * i)) % table.length;
			if(table[index] == null || table[index].getKey().equals(key)){
				return index;
			}
			i++;
		}
		return -1;
	}
	/**
	 * (Helper method) Checks primary index then does quadratic probe to locate an existing key
	 * @param primaryIndex of given key
	 * @param key to find location for
	 * @return  location of existing key. -1 if key is not present
	 */
	private int indexOf(K key){
		int i = 0;
		int primaryIndex = hash(key); 
		int index = primaryIndex;
		while((i * i) < table.length){
			index = (primaryIndex + (i * i)) % table.length;
			if(table[index] != null && table[index].getKey().equals(key)){
				return index;
			}
			i++;
		}
		return -1;
	}
	/**
	 * (Helper method) Sets table at index to key-value pair and returns old value.
	 *  If null at index, adds key-value and returns null
	 *  If null key, sets HashEntry at index to null and returns old value
	 * @param index to update key-value pair
	 * @param key to update
	 * @param value to add
	 * @return Old value if present. Null otherwise
	 */
	private V set(int index, HashEntry<K, V> entry){
		if(table[index] != null){
			V temp = table[index].getValue();
			table[index] = entry;
			return temp;
		}
		table[index] = entry;
		return null;
	}
	
	/** 
	 * Adds a new entry (key-value pair) to table if key doesn't exist; otherwise replace the current value with the new value
	 * @param key the target key
	 * @param value associated with key
	 * @return previous value being replaced. Null if key input is null or new key to table
	 */
	public V put(K key, V value){ 
		if(key == null){ //key input is null
			return null;
		}
		double fillRate = 1.0 * count / table.length;
		if(fillRate >= maxLoadFactor){ //Check if resize() is necessary
			resize();
		}
		int hashIndex = hash(key);
		if(table[hashIndex] != null){ 	
			hashIndex = placeKey(hashIndex, key); //Check primary then quad probe for open space/existing key
			if(hashIndex != -1 && table[hashIndex] != null){		
				return set(hashIndex, new HashEntry<K,V>(key, value));
			}
			else if(hashIndex == -1){ //Quadratic probing is unsuccessful. Must resize and call put again
				resize(); 
				return put(key, value);
			}
		}
		count++;
		return set(hashIndex, new HashEntry<K,V>(key, value));
	}
	/**
	 * Returns true if the key is present in the table
	 * @param key to check if present
	 * @return True if key is found. False otherwise
	 */
	public	boolean containsKey(K key){ 
		if(key != null){
			return (indexOf(key) != -1);
		}
		return false;
	}
	/**
	 * Checks if the value is present in the table
	 * @param value to look for in table
	 * @return true if value is found. False otherwise
	 */
	public	boolean containsValue(V value){
		for(int i = 0; i < table.length; i++){
			if(table[i] != null && table[i].getValue().equals(value)){
				return true;
			}
		}
		return false;
	}
	/**
	 * Returns value at given key
	 * @param key to locate
	 * @return Value at key. Null if key is not present
	 */
	public V get(K key){ 
		if(key != null){
			int hashIndex = indexOf(key);
				if(hashIndex != -1){
					return table[hashIndex].getValue();
				}
			}
		return null;
	}
	/**
	 * Removes the specified HashEntry (key-value pair) at a given key from HashTable
	 * @param key of HashEntry
	 * @return Value that is removed. Null if key is not present
	 */
	public V remove(K key){
		if(key != null){
				int hashIndex = indexOf(key);
				if(hashIndex != -1){
					count--;
					return set(hashIndex, null);
				}
			}
		return null;
	}
	/**
	 * Returns the size of table
	 * @return size of table
	 */
	public int size(){
		return count;
	}
	/**
	 * Checks if table is empty
	 * @return True if empty. False otherwise
	 */
	public boolean empty(){
		return ( count == 0 );
	}
	/**
	 * Clears table and sets size to 0
	 */
	public void clear(){
		for(int i = 0; i < table.length; i++){
			table[i] = null;
		}
		count = 0;
	}
	/**
	 * Overrides toString() Method to append each Key-Value pair in the form "Key:Value" or
	 * Appends "E" if Null index
	 * @return String containing data at each index in HashTable
	 */
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder(); //Better performance than using String
		sb.append("[ ");
		if(size() != 0){
			for(int i = 0; i < table.length; i ++){
				if(table[i] != null){
					HashEntry<K, V> elem = table[i];
					sb.append("" + elem.getKey() + ":"+elem.getValue() + " ");
				}
				else{
					sb.append("E ");
				}
			}
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 *Resizes internal table and rehashes old entries according to new size
	 */
	private void resize(){
		int newSize = (table.length * 2) + 1;
		while(!isPrime(newSize)){
			newSize ++;
		}
		HashEntry<K, V>[] temp = table;
		table = (HashEntry<K, V>[]) new HashEntry[newSize];
		count = 0;
		for(HashEntry<K,V> entry : temp){
			if(entry != null){
				put(entry.getKey(), entry.getValue());
			}
		}
	}
	/**
	 * Helper method for resize(). Checks if a given integer is prime
	 * @param n number to check if prime
	 * @return True if prime. False otherwise
	 */
	private boolean isPrime(int n){ 
		int i = 2;
		while(i <= n/2){
			if(n%i == 0){
				return false;
			}
			i++;
		}
		return true;
	}
}
