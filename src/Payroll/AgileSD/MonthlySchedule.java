package Payroll.AgileSD;

import java.util.Date;

public class MonthlySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Date payDate) {
		return DateUtil.isLastDayOfMonth(payDate);
	}
	
}
