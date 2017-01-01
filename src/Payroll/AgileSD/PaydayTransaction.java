package Payroll.AgileSD;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaydayTransaction implements Transaction {

	private Date payDate;
	private Map<Integer, Paycheck> paychecks;
	
	public PaydayTransaction(Date payDate) {
		this.payDate = payDate;
		paychecks = new HashMap<>();
	}

	@Override
	public void execute() {
		List<Integer> empIds = new ArrayList<>();
		PayrollDatabase.getAllEmployyIds(empIds);
		for(int empId : empIds){
			Employee e = PayrollDatabase.getEmployee(empId);
			if(e != null){
				if(e.isPayDate(payDate)){
					Paycheck pc = new Paycheck(payDate);
					paychecks.put(empId, pc);
					e.payday(pc);
				}
			}
		}
	}

	public Paycheck getPaycheck(int empId) {
		return paychecks.get(empId);
	}
	
	

}
