package Payroll.AgileSD;

import java.util.Date;

public class Paycheck {

	private Date payDate;
	private double grossPay;
	private double deductions;
	private double netPay;
	
	public Paycheck(Date payDate) {
		this.payDate = payDate;
	}

	public double getGrossPay() {
		return grossPay;
	}

	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}

	public double getDeductions() {
		return deductions;
	}

	public void setDeductions(double deductions) {
		this.deductions = deductions;
	}

	public double getNetPay() {
		return netPay;
	}

	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}

	public Date getPayDate() {
		return payDate;
	}

	

}
