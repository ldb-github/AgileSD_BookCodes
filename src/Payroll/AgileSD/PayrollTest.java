package Payroll.AgileSD;

import java.util.Date;

import junit.framework.TestCase;

public class PayrollTest extends TestCase{

	public void testAddSalariedEmployee(){
		int empId = 1;
		AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
		t.execute();
		
		Employee e = PayrollDatabase.getEmployee(empId);
		assert("Bob" == e.getName());
		
		PaymentClassification pc = e.getClassification();
		SalariedClassification sc = (SalariedClassification) pc;
		assertNotNull(sc);
		
		assertEquals(1000.00, sc.getSalary(), .001);
		PaymentSchedule ps = e.getSchedule();
		MonthlySchedule ms = (MonthlySchedule) ps;
		assertNotNull(ms);
		PaymentMethod pm = e.getMethod();
		HoldMethod hm = (HoldMethod) pm;
		assertNotNull(hm);
	}
	
	public void testDeleteEmployee(){
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
		t.execute();
		
		Employee e = PayrollDatabase.getEmployee(empId);
		assertNotNull(e);
		assertEquals("Lance", e.getName());
		
		DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId);
		dt.execute();
		e = PayrollDatabase.getEmployee(empId);
		assertNull(e);
	}
	
	public void testTimeCardTransaction(){
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.execute();
		TimeCardTransaction tct = new TimeCardTransaction(new Date(2001, 10, 31), 8.0, empId);
		tct.execute();
		Employee e = PayrollDatabase.getEmployee(empId);
		assertNotNull(e);
		
		PaymentClassification pc = e.getClassification();
		HourlyClassification hc = (HourlyClassification) pc;
		assertNotNull(hc);
		assertEquals(15.25, hc.getHourlyRate());
		TimeCard tc = hc.getTimeCard(new Date(2001, 10, 31));
		assertNotNull(tc);
		assertEquals(8.0, tc.getHours());
	}
	
	public void testSalesReceiptTransaction(){
		int empId = 4;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "LDB", "Home", 9000.0, 100.0);
		t.execute();
		SalesReceiptTransaction srt = new SalesReceiptTransaction(new Date(2001, 10, 31), 2, empId);
		srt.execute();
		Employee e = PayrollDatabase.getEmployee(empId);
		assertNotNull(e);
		assertEquals("LDB", e.getName());
		
		PaymentClassification pc = e.getClassification();
		CommissionedClassification cc = (CommissionedClassification) pc;
		assertEquals(9000, cc.getSalary(), .001);
		assertEquals(100, cc.getCommissionRate(), .001);
		SalesReceipt sr = cc.getSalesReceipt(new Date(2001, 10, 31));
		assertNotNull(sr);
		assertEquals(2, sr.getAmount());
	}
	
	public void testAddServiceCharge(){
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.execute();
		Employee e = PayrollDatabase.getEmployee(empId);
		assertNotNull(e);
		int memberId = 88;
		UnionAffiliation uaf = new UnionAffiliation(memberId, 12.5);
		e.setAffiliation(uaf);
		PayrollDatabase.addUnionMember(memberId, e);
		ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, new Date(2001, 10, 31), 12.95);
		sct.execute();
		ServiceCharge sc = uaf.getServiceCharge(new Date(2001, 10, 31));
		assertNotNull(sc);
		assertEquals(12.95, sc.getAmount(), .001);
	}
	
	public void testChangeNameTransaction(){
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.execute();
		ChangeNameTransaction  ct = new ChangeNameTransaction(empId, "Bill_2");
		ct.execute();
		Employee e = PayrollDatabase.getEmployee(empId);
		assertEquals("Bill_2", e.getName());
	}
	
	public void testChangeHourlyTransaction(){
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
		t.execute();
		ChangeHourlyTransaction cht = new ChangeHourlyTransaction(empId, 27.25);
		cht.execute();
		Employee e = PayrollDatabase.getEmployee(empId);
		assertNotNull(e);
		PaymentClassification pc = e.getClassification();
		assertNotNull(pc);
		HourlyClassification hc = (HourlyClassification) pc;
		assertNotNull(hc);
		assertEquals(27.25, hc.getHourlyRate(), .001);
		PaymentSchedule ps = e.getSchedule();
		WeeklySchedule ws = (WeeklySchedule) ps;
		assertNotNull(ws);
	}
	
	public void testChangeSalariedTransaction(){
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
		t.execute();
		ChangeSalariedTransaction cht = new ChangeSalariedTransaction(empId, 9000);
		cht.execute();
		Employee e = PayrollDatabase.getEmployee(empId);
		assertNotNull(e);
		PaymentClassification pc = e.getClassification();
		assertNotNull(pc);
		SalariedClassification sc = (SalariedClassification) pc;
		assertNotNull(sc);
		assertEquals(9000, sc.getSalary(), .001);
		PaymentSchedule ps = e.getSchedule();
		MonthlySchedule ms = (MonthlySchedule) ps;
		assertNotNull(ms);
	}
	
	public void testChangeCommissionedTransaction(){
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
		t.execute();
		ChangeCommissionedTransaction cht = new ChangeCommissionedTransaction(empId, 9000, 100.0);
		cht.execute();
		Employee e = PayrollDatabase.getEmployee(empId);
		assertNotNull(e);
		PaymentClassification pc = e.getClassification();
		assertNotNull(pc);
		CommissionedClassification cc = (CommissionedClassification) pc;
		assertNotNull(cc);
		assertEquals(9000, cc.getSalary(), .001);
		assertEquals(100.0, cc.getCommissionRate(), .001);
		PaymentSchedule ps = e.getSchedule();
		BiweeklySchedule bs = (BiweeklySchedule) ps;
		assertNotNull(bs);
	}
}
