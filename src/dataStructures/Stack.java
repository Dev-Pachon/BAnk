package dataStructures;

public class Stack<T> implements IStack<T> {
	
	private NodeStack<T> first;
	private int size;
	
	@Override
	public void push(T t) {
		if(first == null) {
			first = new NodeStack<T>(t);
		}else {
			NodeStack<T> temp = new NodeStack<T>(t);
			temp.setNext(first);
			first = temp;
		}
		size++;
	}

	@Override
	public T pop() {
		NodeStack<T> temp = first;
		if(temp!=null) {
			first = first.getNext();
			size--;
		}
		
		return temp.getValue();
	}
	
	@Override
	public T peek() {
		return first.getValue();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

}

class NodeStack<T>{
	
	T value;
	NodeStack<T> next;
	
	public NodeStack(T t) {
		value = t;
	}
	
	public NodeStack<T> getNext() {
		return next;
	}
	
	public void setNext(NodeStack<T> next) {
		this.next = next;
	}
	
	public T getValue() {
		return value;
	}
}
