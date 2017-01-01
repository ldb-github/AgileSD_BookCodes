package Payroll.AgileSD;

import java.util.Calendar;
import java.util.Date;

public class MonthlySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Date payDate) {
		return isLastDayOfMonth(payDate);
	}
	
	private boolean isLastDayOfMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int m1 = c.get(Calendar.MONTH);
		
		c.add(Calendar.DAY_OF_MONTH, 1);
		int m2 = c.get(Calendar.MONTH);
		
		return (m1 != m2);
	}

}
