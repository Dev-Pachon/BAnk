package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import Exceptions.NotEnoughMoneyException;
import Exceptions.QueueEmptyException;
import Exceptions.UserNotExistException;
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
		canceledAccounts = new Hash<User,String>(SIZE_HASH);
		dataBase = new Hash<User,String>(SIZE_HASH);

		try {
			chargeDatabase();
		} catch (IOException e) {
			System.out.println("Error charging the data");
		}
	}

	public ArrayList<User> getDatabase() {
		return dataBase.getElements();
	}

	public ArrayList<User> sortByName(){
		ArrayList<User> temp = dataBase.getElements();
		User tempUsers[] = new User[temp.size()];
		temp.toArray(tempUsers);
		sort(tempUsers,0,tempUsers.length-1);
		temp.clear();
		for (User user : tempUsers) {
			temp.add(user);
		}
		return temp;
	}

	private void merge(User arr[],int l, int m, int r) {
		int n1 = m-l+1;
		int n2 = r-m;

		User L[] = new User[n1];
		User R[] = new User[n2];

		for(int i = 0; i< n1;++i)
			L[i] = arr[l + i];
		for (int i = 0; i < n2; ++i) 
			R[i] = arr[m+1+i];

		int i = 0;
		int j = 0;
		int k = l;

		while(i < n1 && j < n2) {
			if(L[i].compareTo(R[j])<=0) {
				arr[k] = L[i];
				i++;
			}else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		while(i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		while(j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	private void sort(User arr[], int l, int r) {
		if(l < r) {
			int m = (l + r) / 2;

			sort(arr, l, m);
			sort(arr, m + 1, r);

			merge(arr, l, m, r);
		}
	}

	public ArrayList<User> sortById(){
		ArrayList<User> temp = dataBase.getElements();

		for(int i = 0; i<temp.size()-1;i++) {
			for(int j = 0 ;j<temp.size()-i-1;j++) {
				if(temp.get(j).compareById(temp.get(j+1))>0) {
					User userTemp = temp.get(j);
					temp.set(j, temp.get(j+1));
					temp.set(j+1, userTemp);
				}
			}
		}

		return temp;
	}
	
	public ArrayList<User> sortByAccountNumber() {
		ArrayList<User> temp = dataBase.getElements();
		User tempUsers[] = new User[temp.size()];
		temp.toArray(tempUsers);
		quickSort(tempUsers,0,tempUsers.length-1);
		temp.clear();
		for (User user : tempUsers) {
			temp.add(user);
		}
		return temp;
	}
	
	private int partition(User arr[], int low, int high) {
		User pivot = arr[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) {
            if (arr[j].compareByAccountNumber(pivot)<0) { 
                i++; 

                User temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 

        User temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1;
	}
	
	private void quickSort(User arr[], int low, int high) {
		if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high); 
        } 
	}
	
	public ArrayList<User> sortByDebitCard(){
		ArrayList<User> temp = dataBase.getElements();
		User tempUsers[] = new User[temp.size()];
		temp.toArray(tempUsers);
		heapSort(tempUsers);
		temp.clear();
		for (User user : tempUsers) {
			temp.add(user);
		}
		return temp;
	}
	
	private void heapify(User arr[], int n, int i) {
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;

		if (l < n && arr[l].compareByDebitCard(arr[largest])>0)
			largest = l;

		if (r < n && arr[r].compareByDebitCard(arr[largest])>0)
			largest = r;

		if (largest != i) {
			User swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;

			heapify(arr, n, largest);
		}
	}
	
	private void heapSort(User arr[]) {
		int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {
            User temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
	}

	public void newEntry(String ID,boolean priority) throws UserNotExistException {
		User u = dataBase.search(ID);
		if(u!=null){
			if(priority)
				prioQueue.offer(u);
			else
				queue.offer(u);
		}else
			throw new UserNotExistException();
	}

	public void attendQueueUser() throws QueueEmptyException {
		User u = queue.poll(); 
		if(u==null) {
			throw new QueueEmptyException();
		}
	}

	public void attendPrioQueueUser() throws QueueEmptyException {
		User u = prioQueue.poll(); 
		if(u==null) {
			throw new QueueEmptyException();
		}
	}
	
	public void makeDeposit(String id,double amount) {
		User u = searchUser(id);
		u.setDebitCard(u.getDebitCard()+amount);
		undoStack.push(new Transaction(TransactionType.MAKE_DEPOSIT, u, amount,null));
	}
	
	public void makeWithdrawal(String id, double amount) throws NotEnoughMoneyException {
		User u = searchUser(id);
		if(u.getDebitCard()>=amount) {
			u.setDebitCard(u.getDebitCard()-amount);
		}else
			throw new NotEnoughMoneyException();
		undoStack.push(new Transaction(TransactionType.MAKE_WITHDRAWAL, u, amount,null));
	}
	
	public void cancelAccount(String id) {
		User u = searchUser(id);
		dataBase.delete(u, u.getID());
		canceledAccounts.insert(u, u.getID());
		undoStack.push(new Transaction(TransactionType.CANCEL_ACCOUNT, u, 0,null));
	}
	
	public void payCreditCard(String id, double amount, PayType payType) throws NotEnoughMoneyException {
		User u = searchUser(id);
		if(payType.equals(PayType.PAY_WITH_CARD)) {
			if(amount<=u.getDebitCard()) {
				u.setCreditCard(u.getCreditCard()-amount);
				u.setDebitCard(u.getDebitCard()-amount);
			}else {
				throw new NotEnoughMoneyException();
			}
		}else {
			u.setCreditCard(u.getCreditCard()-amount);
			u.setDebitCard(u.getDebitCard()-amount);
		}
	}
	
	public void undoTransaction() {
		Transaction t = undoStack.pop();
		if(t.getType().equals(TransactionType.MAKE_DEPOSIT)||t.getType().equals(TransactionType.MAKE_WITHDRAWAL)){
			t.getUser().setDebitCard(-t.getMoney());
		}else if(t.getType().equals(TransactionType.CANCEL_ACCOUNT)){
			User u = canceledAccounts.search(t.getUser().getID());
			canceledAccounts.delete(u, u.getID());
			dataBase.insert(u, u.getID());
		}else if(t.getType().equals(TransactionType.PAY_CARD)) {
			if(t.getPayType().equals(PayType.PAY_WITH_CARD)) {
				t.getUser().setDebitCard(t.getUser().getDebitCard()+t.getMoney());
				t.getUser().setCreditCard(t.getUser().getCreditCard()+t.getMoney());
			}
		}
	}

	public User searchUser(String id) {
		User u = dataBase.search(id);
		return u;
	}

	public User peekQueue() {
		return queue.peek();
	}

	public int getQueueSize() {
		return queue.size();
	}

	public int getPrioQueueSize() {
		return prioQueue.size();
	}

	public User peekPrioQueue() {
		return prioQueue.peek();
	}

	private void chargeDatabase() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Data.txt"));
		String[] string = br.readLine().split("ppp");
		while(string!=null){
			String name = string[0];
			String iD = string[1];
			String bankAccountNum = string[2];
			double debitCard = Double.parseDouble(string[3]);
			double creditCard =Double.parseDouble(string[4]);
			String date[] = string[5].split("/");
			LocalDate dateOfPayCC = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
			date = string[6].split("/");
			LocalDate dateOfJoin = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
			User u = new User(name, iD, bankAccountNum, debitCard, creditCard, dateOfPayCC, dateOfJoin);
			dataBase.insert(u, iD);
			String nl = br.readLine();
			if(nl!=null) {
				string = nl.split("ppp");
			}else
				string = null;
		}
		br.close();
	}

}
