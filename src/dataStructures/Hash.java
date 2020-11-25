package dataStructures;

import java.util.LinkedList;

public class Hash<T, K extends Comparable<K>> implements IHash<T, K> {
	
	private LinkedList<NodeHash<T,K>>[] hashM;
	private int size;
	
	@SuppressWarnings("unchecked")
	public Hash(int size) {
		hashM = (LinkedList<NodeHash<T,K>>[]) new Object[size];
		size = 0;
	}
	
	@Override
	public void insert(T t, K k) {
		int h = hash(k);
		if(hashM[h]==null) {
			hashM[h] = new LinkedList<>();
		}
		hashM[h].add(new NodeHash<T,K>(t, k));
		size ++;
	}

	@Override
	public T search(K k) {
		int h = hash(k);
		if(hashM[h]!=null) {
			for(NodeHash<T,K> t:hashM[h]) {
				if(t.getKey().equals(k)) {
					return t.getValue();
				}
			}
		}
		
		return null;
	}

	@Override
	public void delete(T t) {
		size--;
	}

	@Override
	public int size() {
		return size;
	}
	
	//Fix it!!!!
	private int hash(K k) {
		return k.hashCode();
	}

}

class NodeHash<T,K extends Comparable<K>> {
	
	private T value;
	
	private K key;
	
	public NodeHash(T t, K k) {
		value = t;
		key = k;
	}
	
	public K getKey() {
		return key;
	}
	
	public T getValue() {
		return value;
	}
}
