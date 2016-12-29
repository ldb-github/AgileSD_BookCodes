package Payroll.AgileSD;

public class CommissionedClassification extends PaymentClassification {

	private double salary;
	private double commissionRate;
	
	public CommissionedClassification(double salary, double commissionRate) {
		this.salary = salary;
		this.commissionRate = commissionRate;
	}

}
