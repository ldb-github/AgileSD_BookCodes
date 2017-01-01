package Payroll.AgileSD;

public abstract class AddEmployeeTransaction implements Transaction{

	protected int itsEmpId;
	protected String itsName;
	protected String itsAddress;
	
	public AddEmployeeTransaction(int empId, String name, String address) {
		itsEmpId = empId;
		itsName = name;
		itsAddress = address;
	}
	
	public abstract PaymentClassification getClassification();
	public abstract PaymentSchedule getSchedule();
	
	public void execute(){
		PaymentClassification pc = getClassification();
		PaymentSchedule ps = getSchedule();
		PaymentMethod pm = new HoldMethod();
		Affiliation af = new NoAffiliation();
		Employee e = new Employee(itsEmpId, itsName, itsAddress);
		e.setClassification(pc);
		e.setSchedule(ps);
		e.setMethod(pm);
		e.setAffiliation(af);
		PayrollDatabase.addEmployee(itsEmpId, e);
	}
}
