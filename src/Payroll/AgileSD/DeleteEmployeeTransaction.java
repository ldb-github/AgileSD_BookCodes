package Payroll.AgileSD;

public class DeleteEmployeeTransaction extends Transaction {

	private int empId;
	
	public DeleteEmployeeTransaction(int empId) {
		this.empId = empId;
	}

	@Override
	public void execute() {
		PayrollDatabase.deleteEmployee(empId);
	}

}
