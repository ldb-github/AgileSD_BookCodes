package Payroll.AgileSD;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommissionedClassification extends PaymentClassification {

	private double salary;
	private double commissionRate;
	private List<SalesReceipt> salesReceipts;
	
	public CommissionedClassification(double salary, double commissionRate) {
		this.salary = salary;
		this.commissionRate = commissionRate;
		salesReceipts = new ArrayList<>();
	}

	public SalesReceipt getSalesReceipt(Date date) {
		for(SalesReceipt sr : salesReceipts){
			if(sr.getDate().getTime() == date.getTime()){
				return sr;
			}
		}
		return null;
	}

	public double getSalary() {
		return salary;
	}

	public double getCommissionRate() {
		return commissionRate;
	}

	public void addSalesReceipt(SalesReceipt salesReceipt) {
		salesReceipts.add(salesReceipt);
	}

	

}
