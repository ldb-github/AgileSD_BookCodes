package Payroll.AgileSD;

public class ChangeMemberTransaction extends ChangeAffiliationTransation {

	private int memberId;
	private double dues;
	
	public ChangeMemberTransaction(int memberId, double dues, int empId) {
		super(empId);
		this.memberId = memberId;
		this.dues = dues;
	}

	@Override
	protected Affiliation getAffiliation() {
		return new UnionAffiliation(memberId, dues);
	}

	@Override
	protected void RecordMembership(Employee e) {
		PayrollDatabase.addUnionMember(memberId, e);
	}

}
