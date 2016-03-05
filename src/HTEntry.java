
public class HTEntry<K, V> {
	private K key;
	private V value;
	private int hashCode;
	
	public HTEntry(){
	}
	
	public HTEntry(K key, V value){
		this.key = key;
		this.value = value;
	}
	
	public K getKey(){
		return key;
	}
	
	public V getValue(){
		return value;
	}
	
	public void setKey(K k){
		key = k;
	}
	
	public void setValue(V v){
		value = v;
	}
	public int getHashCode(){
		return hashCode;
	}
	public void setHashCode(int hash){
		hashCode = hash;
	}
}
