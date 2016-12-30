package Payroll.AgileSD;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HourlyClassification extends PaymentClassification {

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

	
}
