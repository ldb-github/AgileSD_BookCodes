package Payroll.AgileSD;

public class ChangeDirectTransaction extends ChangeMethodTransaction {

	private String bank;
	private String account;
	
	public ChangeDirectTransaction(String bank, String account, int empId) {
		super(empId);
		this.bank = bank;
		this.account = account;
	}

	@Override
	protected PaymentMethod getMethod() {
		return new DirectMethod(bank, account);
	}

}
