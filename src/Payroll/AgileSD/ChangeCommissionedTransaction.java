package Payroll.AgileSD;

import javax.swing.plaf.basic.BasicIconFactory;

public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {

	private double salary;
	private double commissionRate;
	
	public ChangeCommissionedTransaction(int empId, double salary, double commissionRate) {
		super(empId);
		this.salary = salary;
		this.commissionRate = commissionRate;
	}

	@Override
	protected PaymentClassification getClassification() {
		return new CommissionedClassification(salary, commissionRate);
	}

	@Override
	protected PaymentSchedule getSchedule() {
		return new BiweeklySchedule();
	}

}
