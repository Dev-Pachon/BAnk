package dataStructures;

public interface IHash<T,K extends Comparable<K>> {
	
	public void insert(T t,K k);
	
	public T search(K k);
	
	public int size();
	
}
