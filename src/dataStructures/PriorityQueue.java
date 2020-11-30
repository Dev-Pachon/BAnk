package dataStructures;

public class PriorityQueue<T extends Comparable<T>> extends Heap<T> implements IPriorityQueue<T> {
	
	public PriorityQueue(Class<T> c,int size) {
		super(size,c);
	}
	
	@Override
	public void offer(T t) {
		insert(t);
	}

	@Override
	public T poll() {
		return extractMax();
		
	}

	@Override
	public T peek() {
		return get(0);
	}

	@Override
	public int size() {
		return heap_size;
	}

	@Override
	public boolean isEmpty() {
		return heap_size==0;
	}

}
