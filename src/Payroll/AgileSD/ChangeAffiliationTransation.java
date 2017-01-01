package Payroll.AgileSD;

public abstract class ChangeAffiliationTransation extends ChangeEmployeeTransaction {

	public ChangeAffiliationTransation(int empId) {
		super(empId);
	}

	protected abstract Affiliation getAffiliation();
	protected abstract void RecordMembership(Employee e);
	
	@Override
	protected void change(Employee e) {
		RecordMembership(e);
		e.setAffiliation(getAffiliation());
	}

}
