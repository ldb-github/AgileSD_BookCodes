package Payroll.AgileSD;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {

	private String newName;
	
	public ChangeNameTransaction(int empId, String name) {
		super(empId);
		newName = name;
	}

	@Override
	protected void change(Employee e) {
		e.setName(newName);
	}
}
