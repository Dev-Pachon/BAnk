package model;

import dataStructures.Hash;
import dataStructures.PriorityQueue;
import dataStructures.Queue;
import dataStructures.Stack;

public class BAnk {
	
	public final int SIZE_PRIORITY_QUEUE = 50;
	public final int SIZE_HASH = 100;
	
	private Queue<User> queue;
	private PriorityQueue<User> prioQueue;
	private Stack<Transaction> undoStack;
	private Hash<User,String> canceledAccounts;
	private Hash<User,String> dataBase;
	
	public BAnk() {
		queue = new Queue<User>();
		prioQueue = new PriorityQueue<>(User.class, SIZE_PRIORITY_QUEUE);
		undoStack = new Stack<Transaction>();
	}
	
	
	
}
