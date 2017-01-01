package Payroll.AgileSD;

public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {

	public ChangeMethodTransaction(int empId) {
		super(empId);
	}

	protected abstract PaymentMethod getMethod();
	
	@Override
	protected void change(Employee e) {
		e.setMethod(getMethod());
	}

}
