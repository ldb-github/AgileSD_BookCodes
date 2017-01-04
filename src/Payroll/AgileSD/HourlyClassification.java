package Payroll.AgileSD;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HourlyClassification implements PaymentClassification {

	private double hourlyRate;
	private List<TimeCard> timeCards;
	
	public HourlyClassification(double hourlyRate) {
		this.hourlyRate = hourlyRate;
		timeCards = new ArrayList<>();
	}

	public TimeCard getTimeCard(Date date) {
		for(TimeCard tc : timeCards){
			if(tc.getDate().getTime() == date.getTime()){
				return tc;
			}
		}
		return null;
	}

	public void addTimeCard(TimeCard timeCard) {
		timeCards.add(timeCard);
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	@Override
	public double calculatePay(Paycheck pc) {
		double grossPay = 0;
		Date payPeriod = pc.getPayDate();
		for(TimeCard tc : timeCards){
			if(isInPeriod(tc, payPeriod)){
				grossPay += calculatePayForTimeCard(tc);
			}
		}
		return grossPay;
	}
	/**
	 * 判断时间卡片是否在结算周期内
	 * @param tc
	 * @param payPeriod
	 * @return
	 */
	private boolean isInPeriod(TimeCard tc, Date payPeriod){
		Date periodEndDate = payPeriod;
		Date periodStartDate = DateUtil.before(payPeriod, 6); 
		Date workDate = tc.getDate();
		
		return (DateUtil.isAfter(workDate, periodStartDate) && 
				DateUtil.isBefore(workDate, periodEndDate));
	}
	/**
	 * 计算一张卡片的薪水
	 * @param tc
	 * @return
	 */
	private double calculatePayForTimeCard(TimeCard tc){
		double hours = tc.getHours();
		double overtime = 0;
		if(DateUtil.isWeekend(tc.getDate())){
			overtime = hours;
		}else{
			overtime = Math.max(0.0, hours - 8.0);
		}
		double straightTime = hours - overtime;
		
		return (straightTime + overtime * 1.5) * hourlyRate; 
	}
	
}
