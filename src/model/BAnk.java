package model;

import dataStructures.PriorityQueue;
import dataStructures.Queue;

public class BAnk {
	
	public final int SIZE_PRIORITY_QUEUE = 50;
	
	private Queue<User> queue;
	private PriorityQueue<User> prioQueue;
	
	public BAnk() {
		queue = new Queue<User>();
		prioQueue = new PriorityQueue<>(User.class, SIZE_PRIORITY_QUEUE);
	}
}
