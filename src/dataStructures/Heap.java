package dataStructures;

import java.lang.reflect.Array;

public class Heap<T extends Comparable<T>> implements IHeap {
	
	private T[] heap;
	public int heap_size;
	
	public Heap(int size,Class<T> c) {
		@SuppressWarnings("unchecked")
		final T[] heap = (T[]) Array.newInstance(c, size);
		this.heap = heap;
		heap_size = 0;
	}
	
	public void insert(T t) {
		if(heap_size==heap.length) {
			throw new IndexOutOfBoundsException("The heap is full");
		}else {
			heap[heap_size++] = t;
			maxHeapify(this, heap_size/2);
		}
	}
	
	public void maxHeapify(Heap<T> heap,int i) {
		int left = i*2;
		int right = i*2+1;
		int largest = -1;
		
		
		if(left<=heap.heap_size&&heap.get(left).compareTo(heap.get(right))>0) {
			largest = left;
		}else
			largest = right;
		
		if(right<=heap.heap_size&&heap.get(right).compareTo(heap.get(largest))>0) {
			largest = right;
		}
		
		if(largest!=i) {
			T temp = heap.get(i);
			heap.set(heap.get(largest), i);
			heap.set(temp, i);
			maxHeapify(heap, largest);
		}
		
	}
	
	public void buildHeap(Heap<T> heap) {
		heap.heap_size = heap.size();
		for(int i = Math.floorDiv(heap.heap_size, 2);i>0;i--) {
			maxHeapify(heap, i);
		}
	}
	
	public void heapSort(T[] t,Class<T> c) {
		Heap<T> heap = new Heap<T>(t.length,c);
		
		buildHeap(heap);
		for(int i = heap.size();i>0;i = i-2) {
			T temp = heap.get(1);
			heap.set(heap.get(i), 1);
			heap.set(temp, 1);
			maxHeapify(heap, 1);
		}
	}
	
	public T get(int i) {
		return heap[i];
	}
	
	public void set(T t, int i) {
		heap[i] = t;
	}
	
	@Override
	public int size() {
		return heap.length;
	}

}
