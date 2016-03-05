
public class HashEntry<K, V> {
	private K key;
	private V value;
	private int hashcode;

	public HashEntry(){
	}
	public HashEntry(K key, V value){
		this.key = key;
		this.value = value;
	}

	public K getKey(){
		return key;
	}

	public int getHashCode() {
		return hashcode;
	}

	public V getValue(){
		return value;
	}

	public void setKey(K k){
		key = k;
	}

	public void setHashCode(int hashcode) {
		this.hashcode = hashcode;
	}

	public void setValue(V v){
		value = v;
	}
	/**
	 * @return True if same key. False otherwise.
	 */
	@Override
	public boolean equals(Object obj){
		if(obj instanceof HashEntry){
			HashEntry<K, V> entry = (HashEntry<K, V>) obj;
			return (key.equals(entry.getKey()));
		}
		return false;
	}
}
