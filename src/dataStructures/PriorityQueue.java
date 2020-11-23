package dataStructures;

public class PriorityQueue<T extends Comparable<T>> extends Heap<T> implements IPriorityQueue<T> {
	
	public PriorityQueue(Class<T> c,int size) {
		super(size);
	}
	
	@Override
	public void offer(T t) {
		insert(t);
	}

	@Override
	public T poll() {
		T max = null;
		if(!(heap_size<1)) {
			max = get(0);
			set(get(heap_size-1), 0);
			set(max, heap_size-1);
			heap_size--;
			maxHeapify(this, 0);
		}
		return max;
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
