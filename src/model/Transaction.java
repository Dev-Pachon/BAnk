package model;

public class Transaction {
	
	TransactionType type;
	PayType payType;
	User user;
	double money;
	
	public Transaction(TransactionType type, User user, double money, PayType payType) {
		this.type = type;
		this.payType = payType;
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
	
	public PayType getPayType() {
		return payType;
	}
}
