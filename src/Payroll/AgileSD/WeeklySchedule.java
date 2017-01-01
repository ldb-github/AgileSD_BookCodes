package Payroll.AgileSD;

import java.util.Calendar;
import java.util.Date;

public class WeeklySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Date payDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(payDate);
		
		return c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
	}

}
