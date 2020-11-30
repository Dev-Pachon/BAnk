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
	
	public void insert(T t) 
    { 
        heap[heap_size] = t; 
  
        // Traverse up and fix violated property 
        int current = heap_size; 
        while (heap[current].compareTo(heap[current/2])>0) {
        	T tmp = heap[current]; 
            heap[current] = heap[current/2]; 
            heap[current/2] = tmp;
            
            current = current/2; 
        }
        
        heap_size++;
    }
	
	public void maxHeapify(int i) {
		if(isLeaf(i))
			return;
		
		int left = i*2;
		int right = i*2+1;
		
		if (heap[i].compareTo(heap[left]) < 0 || heap[i].compareTo(heap[right])<0) { 
	  
	            if (heap[left].compareTo(heap[right])>0) { 
	            	T tmp = heap[i]; 
	                heap[i] = heap[left]; 
	                heap[left] = tmp;
	                maxHeapify(left); 
	            } 
	            else {
	            	T tmp = heap[i]; 
	                heap[i] = heap[right]; 
	                heap[right] = tmp; 
	                maxHeapify(right); 
	            } 
	        } 
		
	}
	
	public T extractMax(){ 
        T pop = heap[1]; 
        heap[1] = heap[heap_size--]; 
        maxHeapify(1); 
        return pop; 
    }
	
	private boolean isLeaf(int pos) 
    { 
        if (pos >= (heap_size / 2) && pos <= heap_size) { 
            return true; 
        } 
        return false; 
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
