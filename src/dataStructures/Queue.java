package dataStructures;

public class Queue<T> implements IQueue<T> {
	
	private NodeQueue<T> first;
	private NodeQueue<T> last;
	private int size;
	
	public Queue() {
		size = 0;
	}
	
	@Override
	public void offer(T t) {
		if(first==null) {
			NodeQueue<T> temp = new NodeQueue<T>(t); 
			first = temp;
			last = temp;
		}else {
			last.setNext(new NodeQueue<T>(t));
		}
		size++;
	}

	@Override
	public T poll() {
		if(first!=null) {
			NodeQueue<T> firstTemp = first; 
			if(first.getNext()!=null) {
				first = first.getNext();
			}else {
				first = null;
				last = null;
			}
			size--;
			return firstTemp.getValue();
		}
		return null;
		
	}
	
	@Override
	public T peek() {
		if(first!=null)
			return first.getValue();
		else
			return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return first==null;
	}
	
	public T[] toArray() {
		
		if(first==null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		T[] temp = (T[])new Object[size];
		NodeQueue<T> nodeTemp = first;
		for(int i = 0; i<size;i++) {
			temp[i] = nodeTemp.getValue();
			nodeTemp = nodeTemp.getNext();
		}
		return temp;
	}

}

class NodeQueue<T>{
	private T value;
	private NodeQueue<T> next;
	
	public NodeQueue(T t) {
		this.value = t;
	}

	public T getValue() {
		return value;
	}

	public NodeQueue<T> getNext() {
		return next;
	}

	public void setNext(NodeQueue<T> next) {
		this.next = next;
	}
	
}
