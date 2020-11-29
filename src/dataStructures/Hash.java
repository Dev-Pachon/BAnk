package dataStructures;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class Hash<T, K extends Comparable<K>> implements IHash<T, K> {

	private LinkedList<NodeHash<T,K>>[] hashM;
	private int size;

	@SuppressWarnings("unchecked")
	public Hash(int size) {
		final LinkedList<NodeHash<T,K>>[] hashM = (LinkedList<NodeHash<T,K>>[]) Array.newInstance(LinkedList.class, size);
		this.hashM = hashM;
		size = 0;
	}

	@Override
	public void insert(T t, K k) {
		if(size+1<=hashM.length) {
			int h = hash(k);
			if(hashM[h]==null) {
				hashM[h] = new LinkedList<>();
			}
			hashM[h].add(new NodeHash<T,K>(t, k));
			size ++;
		}
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
	public void delete(T t,K k) {
		int h = hash(k);
		if(hashM[h]!=null) {
			size = (hashM[h].remove(t))? size-1:size;
		}

	}

	@Override
	public int size() {
		return size;
	}

	private int hash(K k) {
		return Math.abs(k.hashCode()%hashM.length);
	}

	public ArrayList<T> getElements() {
		if(size==0) {
			return null;
		}else {
			ArrayList<T> temp = new ArrayList<>();

			for(int i = 0; i< hashM.length;i++) {
				if(hashM[i]!=null) {
					for(NodeHash<T,K> t:hashM[i]) {
						temp.add(t.getValue());
					}
				}
			}

			return temp;
		}

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
