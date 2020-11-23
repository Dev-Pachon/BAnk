package dataStructures;

public class Stack<T> implements IStack<T> {
	
	NodeStack<T> first;
	
	@Override
	public void push(T t) {
		if(first == null) {
			first = new NodeStack<T>(t);
		}else {
			NodeStack<T> temp = new NodeStack<T>(t);
			temp.setNext(first);
			first = temp;
		}
	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
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
