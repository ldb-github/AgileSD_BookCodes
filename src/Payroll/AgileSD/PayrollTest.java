package Payroll.AgileSD;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class PayrollTest extends TestCase{

	/**
	 * 增加带薪雇员
	 */
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
	/**
	 * 删除雇员
	 */
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
	/**
	 * 按时计算雇员与时间卡片
	 */
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
	/**
	 * 酬金雇员与销售凭条
	 */
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
	/**
	 * 会员与服务费
	 */
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
	/**
	 * 修改雇员姓名
	 */
	public void testChangeNameTransaction(){
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.execute();
		ChangeNameTransaction  ct = new ChangeNameTransaction(empId, "Bill_2");
		ct.execute();
		Employee e = PayrollDatabase.getEmployee(empId);
		assertEquals("Bill_2", e.getName());
	}
	/**
	 * 修改雇员为按小时计算薪水
	 */
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
	/**
	 * 修改雇员为月薪
	 */
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
	/**
	 * 修改雇员为酬金雇员
	 */
	public void testChangeCommissionedTransaction(){
		int empId = 1;
		AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
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
	/**
	 * 修改支付方式为保存到出纳
	 */
	public void testChangeHoldTransaction(){
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
		t.execute();
		ChangeHoldTransaction cht = new ChangeHoldTransaction(empId);
		cht.execute();
		Employee e = PayrollDatabase.getEmployee(empId);
		assertNotNull(e);
		PaymentMethod pm = e.getMethod();
		assertNotNull(pm);
		HoldMethod hm = (HoldMethod) pm;
		assertNotNull(hm);
	}
	/**
	 * 修改支付方式为直接打入银行账户
	 */
	public void testChangeDirectTransaction(){
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
		t.execute();
		ChangeDirectTransaction cdt = new ChangeDirectTransaction("建设银行", "12345", empId);
		cdt.execute();
		Employee e = PayrollDatabase.getEmployee(empId);
		assertNotNull(e);
		PaymentMethod pm = e.getMethod();
		assertNotNull(pm);
		DirectMethod dm = (DirectMethod) pm;
		assertNotNull(dm);
		assertEquals("建设银行", dm.getBank());
		assertEquals("12345", dm.getAccount());
	}
	/**
	 * 修改支付方式为邮寄支票
	 */
	public void testChangeMailTransaction(){
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
		t.execute();
		ChangeMailTransaction cmt = new ChangeMailTransaction("深圳南山凯丽花园", empId);
		cmt.execute();
		Employee e = PayrollDatabase.getEmployee(empId);
		assertNotNull(e);
		PaymentMethod pm = e.getMethod();
		assertNotNull(pm);
		MailMethod mm = (MailMethod) pm;
		assertNotNull(mm);
		assertEquals("深圳南山凯丽花园", mm.getAddress());
	}
	/**
	 * 修改雇员为协会成员
	 */
	public void testChangeMemberTransaction(){
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
		t.execute();
		int memberId = 86;
		ChangeMemberTransaction cmt = new ChangeMemberTransaction(memberId, 12.5, empId);
		cmt.execute();
		Employee e = PayrollDatabase.getEmployee(empId);
		assertNotNull(e);
		Affiliation af = e.getAffiliation();
		assertNotNull(af);;
		UnionAffiliation uaf = (UnionAffiliation) af;
		assertNotNull(uaf);
		assertEquals(12.5, uaf.getDues(), .001);
		Employee e2 = PayrollDatabase.getUnionMember(memberId);
		assertEquals(e, e2);
	}
	/**
	 * 修改雇员为不是协会成员
	 */
	public void testChangeUnaffiliatedTransaction(){
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
		t.execute();
		ChangeUnaffiliatedTransaction cmt = new ChangeUnaffiliatedTransaction(empId);
		cmt.execute();
		Employee e = PayrollDatabase.getEmployee(empId);
		assertNotNull(e);
		Affiliation af = e.getAffiliation();
		assertNotNull(af);;
		NoAffiliation naf = (NoAffiliation) af;
		assertNotNull(naf);
	}
	/**
	 * 发薪日计算月薪雇员薪水
	 */
	public void testPaySingleSalariedEmployee(){
		int empId = 1;
		AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.0);
		t.execute();
		Date payDate = new Date(116, 11, 31);
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.execute();
		Paycheck pc = pt.getPaycheck(empId);
		assertNotNull(pc);
//		assertEquals(payDate, pc.getPayDate());
		assertEquals(1000.0, pc.getGrossPay(), .001);
//		assertEquals("Hold", pc.getField("Disposition"));
		assertEquals(0.0, pc.getDeductions(), .001);
		assertEquals(1000.0, pc.getNetPay(), .001);
	}
	/**
	 * 非发薪日计算月薪雇员薪水
	 */
	public void testPaySingleSalariedEmployeeOnWrongDate(){
		int empId = 1;
		AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.0);
		t.execute();
		Date payDate = new Date(116, 11, 30);
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.execute();
		Paycheck pc = pt.getPaycheck(empId);
		assertNull(pc);
	}
	/**
	 * 发薪日计算没有时间卡的钟点工的薪水
	 */
	public void testPaySingleHourlyEmployeeNoTimeCards(){
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.execute();
		Calendar c = Calendar.getInstance();
		c.set(2016, 11, 30);
		Date payDate = c.getTime();
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.execute();
		validatePaycheck(pt, empId, payDate, 0.0);
	}
	/**
	 * 发薪日计算只有一张时间卡且工时小于8小时的钟点工的薪水
	 */
	public void testPaySingleHourlyEmployeeOneTimeCards(){
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.execute();
		Date payDate = new Date(116, 11, 30); // Friday
		TimeCardTransaction tct = new TimeCardTransaction(payDate, 2.0, empId);
		tct.execute();
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.execute();
		validatePaycheck(pt, empId, payDate, 30.5);
	}
	/**
	 * 发薪日计算只有一张时间卡且工时大于8小时的钟点工的薪水
	 */
	public void testPaySingleHourlyEmployeeOverTimeOneTimeCards(){
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.execute();
		Date payDate = new Date(116, 11, 30); // Friday
		TimeCardTransaction tct = new TimeCardTransaction(payDate, 9.0, empId);
		tct.execute();
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.execute();
		validatePaycheck(pt, empId, payDate, (8 + 1.5) * 15.25);
	}
	/**
	 * 钟点工周末加班薪水计算
	 */
	public void testPaySingleHourlyEmployeeWithWeekendTimeCards(){
		int empId = 2;
		AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
		t.execute();
		Date weekendDate = new Date(116, 11, 25); // Sunday
		TimeCardTransaction tct = new TimeCardTransaction(weekendDate, 9.0, empId);
		tct.execute();
		Date payDate = new Date(116, 11, 30); // Friday
		tct = new TimeCardTransaction(payDate, 2.0, empId);
		tct.execute();
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.execute();
		validatePaycheck(pt, empId, payDate, (2 + 9 * 1.5) * 15.25);
	}
	/**
	 * 酬金雇员薪水计算
	 */
	public void testPaySingleCommissionedEmployeeOneSalesReceipt(){
		int empId = 4;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "LDB", "Home", 9000.0, 100.0);
		t.execute();
		Date workDate = new Date(116, 11, 19);
		SalesReceiptTransaction srt = new SalesReceiptTransaction(workDate, 2, empId);
		srt.execute();
		Employee e = PayrollDatabase.getEmployee(empId);
		assertNotNull(e);
		assertEquals("LDB", e.getName());
		
		PaymentClassification pc = e.getClassification();
		CommissionedClassification cc = (CommissionedClassification) pc;
		assertEquals(9000, cc.getSalary(), .001);
		assertEquals(100, cc.getCommissionRate(), .001);
		SalesReceipt sr = cc.getSalesReceipt(workDate);
		assertNotNull(sr);
		assertEquals(2, sr.getAmount());
		//Date payDate = new Date(116, 11, 30);
		Date payDate = new Date(116, 11, 31);
		PaydayTransaction pt = new PaydayTransaction(payDate);
		pt.execute();
		//validatePaycheck(pt, empId, payDate, 100.0 * 2);
		validatePaycheck(pt, empId, payDate, 9000);
	}
	
	private void validatePaycheck(PaydayTransaction pt, int empId, Date payDate, double pay) {
		Paycheck pc = pt.getPaycheck(empId);
		assertNotNull(pc);
//		assertEquals(payDate, pc.getPayPeriodEndDate());
		assertEquals(pay, pc.getGrossPay(), .001);
//		assertEquals("Hold", pc.getField("Disposition"));
		assertEquals(0.0, pc.getDeductions(), .001);
		assertEquals(pay, pc.getNetPay(), .001);
	}
}
