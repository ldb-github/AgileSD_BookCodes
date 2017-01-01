package Payroll.AgileSD;

import java.util.ArrayList;
import java.util.Calendar;
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

	private boolean isInPeriod(TimeCard tc, Date payPeriod){
		Date periodEndDate = payPeriod;
		Calendar c = Calendar.getInstance();
		c.setTime(periodEndDate);
		c.set(Calendar.DAY_OF_MONTH, -5);
		Date periodStartDate = c.getTime();
		Date cardDate = tc.getDate();
		
		return (cardDate.getTime() >= periodStartDate.getTime() && 
				cardDate.getTime() <= periodEndDate.getTime());
	}
	private double calculatePayForTimeCard(TimeCard tc){
		double hours = tc.getHours();
		double overtime = Math.max(0.0, hours - 8.0);
		double straightTime = hours - overtime;
		
		return (straightTime + overtime * 1.5) * hourlyRate; 
	}
	
}
