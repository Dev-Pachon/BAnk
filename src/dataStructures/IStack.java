package dataStructures;

public interface IStack<T> {
	public void push(T t);
	
	public T pop();
	
	public T peek();
	
	public int size();
	
	public boolean isEmpty();
}
