package model;

import java.time.LocalDate;

public class User implements Comparable<User>{
	private String name;
	private String iD;
	private String bankAccountNum;
	private double debitCard;
	private double creditCard;
	private LocalDate dateOfPayCC;
	private LocalDate dateOfJoin;
	
	public User(String name, String iD, String bankAccountNum, double debitCard, double creditCard,LocalDate dateOfPayCC,LocalDate dateOfJoin) {
		this.name = name;
		this.iD = iD;
		this.bankAccountNum = bankAccountNum;
		this.debitCard = debitCard;
		this.creditCard = creditCard;
		this.dateOfPayCC = dateOfPayCC;
		this.dateOfJoin = dateOfJoin;
	}
	
	public String getName() {
		return name;
	}
	
	public String getID() {
		return iD;
	}
	
	public String getBankAccountNum() {
		return bankAccountNum;
	}
	
	public double getDebitCard() {
		return debitCard;
	}
	
	public void setDebitCard(double debitCard) {
		this.debitCard = debitCard;
	}
	
	public double getCreditCard() {
		return creditCard;
	}
	
	public void setCreditCard(double creditCard) {
		this.creditCard = creditCard;
	}

	public LocalDate getDateOfPayCC() {
		return dateOfPayCC;
	}

	public LocalDate getDateOfJoin() {
		return dateOfJoin;
	}

	@Override
	public int compareTo(User o) {
		return this.dateOfJoin.compareTo(o.dateOfJoin);
	}
	
	public boolean equals(User u) {
		return iD.equals(u.iD);
	}
	
}
