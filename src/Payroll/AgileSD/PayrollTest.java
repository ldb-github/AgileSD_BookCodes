package Payroll.AgileSD;

import junit.framework.TestCase;

public class PayrollTest extends TestCase{

	public void testAddSalariedEmployee(){
		int empId = 1;
		AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
		t.execute();
		
		Employee e = PayrollDatabase.getEmployee(empId);
		assert("Bob" == e.getName());
		
		PaymentsClassification pc = e.getClassification();
		SalariedClassification sc = (SalariedClassification) pc;
		assert(sc);
		
		assertEquals(1000.00, sc.getSalary(), .001);
		PaymentSchedule ps = e.getSchedule();
		MonthlySchedule ms = (MonthlySchedule) ps;
		assert(ms);
		PaymentMethod pm = e.getMethod();
		HoldMethod hm = (HoldMethod) pm;
		assert(hm);
	}
}
