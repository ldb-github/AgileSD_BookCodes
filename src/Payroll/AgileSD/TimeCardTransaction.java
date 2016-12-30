package Payroll.AgileSD;

import java.util.Date;

public class TimeCardTransaction extends Transaction {

	private Date date;
	private double hours;
	private int empId;
	
	public TimeCardTransaction(Date date, double hours, int empId) {
		this.date = date;
		this.hours = hours;
		this.empId = empId;
	}

	@Override
	public void execute() {
		Employee e = PayrollDatabase.getEmployee(empId);
		if(e != null){
			PaymentClassification pc = e.getClassification();
			if(pc instanceof HourlyClassification){
				HourlyClassification hc = (HourlyClassification) pc;
				hc.addTimeCard(new TimeCard(date, hours));
			}else{
				// TODO 该用什么方式处理异常呢? 直接抛出的话, 父类要调整,一连串的派生类都得跟着变!
				//throw new Exception("Try to add timecard to non-hourly employee!");
			}
		}else{
			//throw new Exception("Try to add timecard to non-hourly employee!");
		}
	}

}
