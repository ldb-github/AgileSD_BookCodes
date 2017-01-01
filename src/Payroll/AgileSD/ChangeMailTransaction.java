package Payroll.AgileSD;

public class ChangeMailTransaction extends ChangeMethodTransaction {

	private String address;
	
	public ChangeMailTransaction(String address, int empId) {
		super(empId);
		this.address = address;
	}

	@Override
	protected PaymentMethod getMethod() {
		return new MailMethod(address);
	}

}
