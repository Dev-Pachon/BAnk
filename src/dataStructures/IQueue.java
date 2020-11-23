package dataStructures;

public interface IQueue<T> {
	
	public void offer(T t);
	
	public T poll();
	
	public T peek();
	
	public int size();
	
	public boolean isEmpty();
	
	
}
