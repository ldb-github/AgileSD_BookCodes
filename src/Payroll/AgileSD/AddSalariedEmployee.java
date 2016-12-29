package Payroll.AgileSD;

public class AddSalariedEmployee extends AddEmployeeTransaction{

	private double salary;
	
	public AddSalariedEmployee(int empId, String name, String address, double salary) {
		super(empId, name, address);
		this.salary = salary;
	}

	@Override
	public PaymentClassification getClassification() {
		return new SalariedClassification(salary);
	}

	@Override
	public PaymentSchedule getSchedule() {
		return new MonthlySchedule();
	}

	
}
