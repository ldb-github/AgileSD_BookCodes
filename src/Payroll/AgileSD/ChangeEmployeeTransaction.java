package Payroll.AgileSD;

public abstract class ChangeEmployeeTransaction implements Transaction {

	private int empId;
	
	public ChangeEmployeeTransaction(int empId){
		this.empId = empId;
	}
	protected abstract void change(Employee e);
	
	@Override
	public void execute() {
		Employee e = PayrollDatabase.getEmployee(empId);
		if(e != null){
			change(e);
		}else{
			// TODO “Ï≥£¥¶¿Ì
		}
	}

}
