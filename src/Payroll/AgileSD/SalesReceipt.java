package Payroll.AgileSD;

import java.util.Date;

public class SalesReceipt {

	private Date date;
	private int amount;
	
	public SalesReceipt(Date date, int amount){
		this.date = date;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public int getAmount() {
		return amount;
	}
	
	
}
