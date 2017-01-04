package Payroll.AgileSD;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommissionedClassification implements PaymentClassification {

	private double salary;
	private double commissionRate;
	private List<SalesReceipt> salesReceipts;
	
	public CommissionedClassification(double salary, double commissionRate) {
		this.salary = salary;
		this.commissionRate = commissionRate;
		salesReceipts = new ArrayList<>();
	}

	public SalesReceipt getSalesReceipt(Date date) {
		for(SalesReceipt sr : salesReceipts){
			if(sr.getDate().getTime() == date.getTime()){
				return sr;
			}
		}
		return null;
	}

	public double getSalary() {
		return salary;
	}

	public double getCommissionRate() {
		return commissionRate;
	}

	public void addSalesReceipt(SalesReceipt salesReceipt) {
		salesReceipts.add(salesReceipt);
	}

	@Override
	public double calculatePay(Paycheck pc) {
		double grossPay = 0.0;
		Date payDate = pc.getPayDate();
		
		grossPay += calculateMonthPay(payDate);
		grossPay += calculateCommissionedPay(payDate);
		
		return grossPay;
	}
	/**
	 * 计算月薪
	 * @param payDate
	 * @return
	 */
	private double calculateMonthPay(Date payDate) {
		if(DateUtil.isLastDayOfMonth(payDate)){
			return salary;
		}
		return 0;
	}
	/**
	 * 计算酬金
	 * @param payDate
	 * @return
	 */
	private double calculateCommissionedPay(Date payDate) {
		double commissionedPay = 0.0;
		for(SalesReceipt sr : salesReceipts){
			if(isInPeriod(sr, payDate)){
				commissionedPay += calculateCommissionedPayForSalesReceipt(sr);
			}
		}
		return commissionedPay;
	}
	/**
	 * 计算单个销售凭条的酬金
	 * @param sr
	 * @return
	 */
	private double calculateCommissionedPayForSalesReceipt(SalesReceipt sr) {
		return sr.getAmount() * commissionRate;
	}
	/**
	 * 判断销售凭条是否在结算周期内
	 * @param sr
	 * @param payPeriod
	 * @return
	 */
	private boolean isInPeriod(SalesReceipt sr, Date payPeriod) {
		Date periodEndDate = DateUtil.before(payPeriod, 5);
		Date periodStartDate = DateUtil.before(payPeriod, 11);
		Date workDate = sr.getDate();
		
		return (DateUtil.isAfter(workDate, periodStartDate) && 
				DateUtil.isBefore(workDate, periodEndDate));
	}

}
