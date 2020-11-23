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
		NodeQueue<T> firstTemp = null;
		if(first!=null) {
			firstTemp = first;
			if(last.equals(firstTemp)) {
				last = null;
			}
			first = firstTemp.getNext();
			size--;
		}
		return firstTemp.getValue();
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
		return first==null;
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
