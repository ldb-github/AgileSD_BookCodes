package Payroll.AgileSD;

import java.util.Date;

public class BiweeklySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Date payDate) {
		return DateUtil.isLastDayOfMonth(payDate) || DateUtil.isFriday(payDate);
	}

}
