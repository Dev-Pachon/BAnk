package model;

public class Transaction {
	
	TransactionType type;
	User user;
	double money;
	
	public Transaction(TransactionType type, User user, double money) {
		this.type = type;
		this.user = user;
		this.money = money;
	}

	public TransactionType getType() {
		return type;
	}

	public User getUser() {
		return user;
	}

	public double getMoney() {
		return money;
	}
}
