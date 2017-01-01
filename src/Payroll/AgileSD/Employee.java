package Payroll.AgileSD;

import java.util.Date;
import java.util.List;

public class Employee {

	private int empId;
	private String name;
	private String address;
	private PaymentClassification classification;
	private PaymentSchedule schedule;
	private PaymentMethod method;
	//private List<Affiliation> affiliations;
	private Affiliation affiliation;
	
	public Employee(int empId, String name, String address){
		this.empId = empId;
		this.name = name;
		this.address = address;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PaymentClassification getClassification() {
		return classification;
	}

	public void setClassification(PaymentClassification classification) {
		this.classification = classification;
	}

	public PaymentSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(PaymentSchedule schedule) {
		this.schedule = schedule;
	}

	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}

	public Affiliation getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}

	public boolean isPayDate(Date payDate){
		return schedule.isPayDate(payDate);
	}

	public void payday(Paycheck pc) {
		double grossPay = classification.calculatePay(pc);
		double deductions = affiliation.calculateDeductions(pc);
		double netPay = grossPay - deductions;
		pc.setGrossPay(grossPay);
		pc.setDeductions(deductions);
		pc.setNetPay(netPay);
		method.pay(pc);
	}

	
}
