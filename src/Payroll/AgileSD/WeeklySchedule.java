package Payroll.AgileSD;

import java.util.Date;

public class WeeklySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Date payDate) {
		return DateUtil.isFriday(payDate);
	}

	
}
